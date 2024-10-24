package com.sec.leetcodesystem.service.impl;



import com.sec.leetcodesystem.dto.Judge0SubmissionRequest;
import com.sec.leetcodesystem.dto.Judge0SubmissionResponse;
import com.sec.leetcodesystem.entities.CodeSubmission;
import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.repository.CodeSubmissionRepository;
import com.sec.leetcodesystem.repository.CodingProblemRepository;
import com.sec.leetcodesystem.repository.CustomerRepository;
import com.sec.leetcodesystem.entities.CodingProblem;
import com.sec.leetcodesystem.service.CodeSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Optional;

@Transactional
@Service
public class CodeSubmissionServiceImpl implements CodeSubmissionService {

    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CodingProblemRepository codingProblemRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Base URL for Judge0 API (configured in application.properties)
    private static final String JUDGE0_URL = "https://judge0-ce.p.rapidapi.com/submissions";
    private static final String JUDGE0_KEY = "YOUR_JUDGE0_API_KEY";  // Optional if using a free key

    /**
     * Submit code to be executed and validated via Judge0 API.
     * 
     * @param customerId ID of the customer submitting the code
     * @param problemId  ID of the coding problem for which the code is submitted
     * @param code       The code solution to be executed
     * @param languageId ID representing the programming language (e.g., 71 = Python, 62 = Java)
     * @return The saved CodeSubmission entity
     */
    public CodeSubmission submitCode(int customerId, Long problemId, String code, int languageId) throws Exception {
        // Fetch the customer and problem from the database
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<CodingProblem> problemOptional = codingProblemRepository.findById(problemId);

        if (!customerOptional.isPresent()) {
            throw new Exception("Customer not found with ID: " + customerId);
        }

        if (!problemOptional.isPresent()) {
            throw new Exception("Problem not found with ID: " + problemId);
        }

        Customer customer = customerOptional.get();
        CodingProblem problem = problemOptional.get();

        // Extract example test cases from the coding problem (using the field `example_test_cases`)
        String testCasesInput = problem.getExampleTestCases();  // This contains both input and expected output

        // Create a submission request to send to Judge0
        Judge0SubmissionRequest judge0Request = new Judge0SubmissionRequest();
        judge0Request.setSource_code(code);
        judge0Request.setLanguage_id(languageId);
        judge0Request.setStdin(testCasesInput);  // Use the example test cases as input

        // Send code to Judge0 API for execution
        Judge0SubmissionResponse judge0Response = executeCodeOnJudge0(judge0Request);

        // Check the result of the execution
        boolean passedTests = judge0Response.getStatus_id() == 3;  // Status ID 3 means the code passed
        String output = passedTests ? judge0Response.getStdout() : judge0Response.getStderr();

        // Create and save the CodeSubmission entity
        CodeSubmission submission = new CodeSubmission();
        submission.setCustomer(customer);
        submission.setProblem(problem);
        submission.setCode(code);
        submission.setPassedTests(passedTests);
        submission.setOutput(output);

        return codeSubmissionRepository.save(submission);
    }

   
    public Judge0SubmissionResponse executeCodeOnJudge0(Judge0SubmissionRequest judge0Request) throws Exception {
        // Get properties from application.properties
        String url = JUDGE0_URL + "?base64_encoded=false&wait=true";
        String apiKey = "YOUR_RAPIDAPI_JUDGE0_KEY";  // Add your Judge0 API key here
        String apiHost = "judge0-ce.p.rapidapi.com"; // Add Judge0 API host (if using RapidAPI)

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        if (apiKey != null && !apiKey.isEmpty()) {
            headers.set("x-rapidapi-key", apiKey);
            headers.set("x-rapidapi-host", apiHost);
        }

        HttpEntity<Judge0SubmissionRequest> entity = new HttpEntity<>(judge0Request, headers);

        ResponseEntity<Judge0SubmissionResponse> response = restTemplate.postForEntity(url, entity, Judge0SubmissionResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new Exception("Error executing code on Judge0: " + response.getStatusCode());
        }
    }

}
