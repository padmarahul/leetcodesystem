package com.sec.leetcodesystem.service.impl;


import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sec.leetcodesystem.dto.TestCase;
import com.sec.leetcodesystem.service.CodeExecutionService;

import java.util.List;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    private final String JDoodle_API_URL = "https://api.jdoodle.com/v1/execute";
    private final String CLIENT_ID = "36622bb6f066ae7a85a5cb6a442624dc";  // Your JDoodle client ID
    private final String CLIENT_SECRET = "6c6741e667afcd6ba23f50af660a5c439b9b851fa41dbe9eabf3c190fbecb28c";  // Your JDoodle client secret
    public String executeCode(String code, List<TestCase> testCases, String language, String versionIndex) {
        RestTemplate restTemplate = new RestTemplate();
        int passedCount = 0;

        // Loop through each test case
        for (TestCase testCase : testCases) {
            try {
                System.out.println("Running test case with input: " + testCase.getInput());

                // Create request payload
                JSONObject request = new JSONObject();
                request.put("clientId", CLIENT_ID);
                request.put("clientSecret", CLIENT_SECRET);
                request.put("script", code);
                request.put("stdin", testCase.getInput());  // Test case input
                request.put("language", language);
                request.put("versionIndex", versionIndex);

                System.out.println("JDoodle Request: " + request.toString());

                // Set headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

                // Send request to JDoodle API
                ResponseEntity<String> response = restTemplate.postForEntity(JDoodle_API_URL, entity, String.class);

                System.out.println("JDoodle Response: " + response.getBody());

                if (response.getStatusCode() == HttpStatus.OK) {
                    JSONObject responseBody = new JSONObject(response.getBody());

                    // Compare actual output with expected output
                    String actualOutput = responseBody.getString("output").trim();
                    String expectedOutput = testCase.getExpectedOutput().trim();

                    System.out.println("Actual Output: " + actualOutput);
                    System.out.println("Expected Output: " + expectedOutput);

                    if (actualOutput.equals(expectedOutput)) {
                        passedCount++;
                    } else {
                        System.out.println("Test case failed. Expected: " + expectedOutput + ", but got: " + actualOutput);
                    }
                }

            } catch (Exception e) {
                return "Error executing code: " + e.getMessage();
            }
        }

        // Return the number of passed test cases
        return "Test Cases Passed: " + passedCount + "/" + testCases.size();
    }

}
