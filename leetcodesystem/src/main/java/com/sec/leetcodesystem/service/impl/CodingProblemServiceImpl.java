package com.sec.leetcodesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.leetcodesystem.dto.CodingProblemDTO;
import com.sec.leetcodesystem.dto.TestCase;
import com.sec.leetcodesystem.entities.CodingProblem;
import com.sec.leetcodesystem.exceptions.ResourceNotFoundException;
import com.sec.leetcodesystem.repository.CodingProblemRepository;
import com.sec.leetcodesystem.service.CodingProblemService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CodingProblemServiceImpl implements CodingProblemService {

    @Autowired
    private CodingProblemRepository codingProblemRepository;

    @Override
    public List<CodingProblemDTO> getProblemsByDifficulty(String difficulty) {
    	  List<CodingProblem> problems = codingProblemRepository.findByDifficulty(difficulty);
          return problems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CodingProblemDTO> getProblemsByCategory(String category) {
    	  List<CodingProblem> problems = codingProblemRepository.findByCategory(category);
          return problems.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public CodingProblemDTO getProblemById(Long id) {
    	 CodingProblem problem = codingProblemRepository.findById(id)
    	            .orElseThrow(() -> new ResourceNotFoundException("Problem not found with ID: " + id));
    	        return convertToDTO(problem);
    }
    
    
    private CodingProblemDTO convertToDTO(CodingProblem problem) {
        CodingProblemDTO dto = new CodingProblemDTO();
        dto.setId(problem.getId());
        dto.setTitle(problem.getTitle());
        dto.setDifficulty(problem.getDifficulty());
        dto.setCategory(problem.getCategory());
        dto.setDescription(problem.getDescription());
        dto.setInputFormat(problem.getInputFormat());
        dto.setOutputFormat(problem.getOutputFormat());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String exampleTestCasesJson = problem.getExampleTestCases();
            if (exampleTestCasesJson != null && !exampleTestCasesJson.isEmpty()) {
                // Parse the exampleTestCases JSON string
                JsonNode testCasesNode = objectMapper.readTree(exampleTestCasesJson).get("testCases");
                List<TestCase> testCases = new ArrayList<>();

                // Iterate through each test case in the JSON array
                for (JsonNode testCaseNode : testCasesNode) {
                    // Extract "input" and "expectedOutput" from each JSON object
                    String input = testCaseNode.get("input").asText();
                    String expectedOutput = testCaseNode.get("expectedOutput").asText();

                    // Create the TestCase object and set the extracted values
                    TestCase testCase = new TestCase();
                    testCase.setInput(input);
                    testCase.setExpectedOutput(expectedOutput);

                    // Add the TestCase object to the list
                    testCases.add(testCase);
                }

                // Set the parsed test cases to the DTO
                dto.setTestCases(testCases);
            } else {
                // If no test cases are provided, set an empty list
                dto.setTestCases(Collections.emptyList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            dto.setTestCases(Collections.emptyList());
        }

        return dto;
    }

	@Override
	public List<CodingProblemDTO> findAll() {
		List<CodingProblem> problems = codingProblemRepository.findAll();
        return problems.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
