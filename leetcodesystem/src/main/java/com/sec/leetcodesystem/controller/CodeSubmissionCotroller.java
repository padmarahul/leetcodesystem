package com.sec.leetcodesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.leetcodesystem.dto.CodeExecutionRequest;
import com.sec.leetcodesystem.dto.CodingProblemDTO;
import com.sec.leetcodesystem.dto.TestCase;
import com.sec.leetcodesystem.entities.CodingProblem;
import com.sec.leetcodesystem.service.CodeExecutionService;
import com.sec.leetcodesystem.service.CodingProblemService;

import java.util.List;

@RestController
@RequestMapping("/lcms/coding-submission")
public class CodeSubmissionCotroller {

    @Autowired
    private CodeExecutionService codeExecutionService;
    
    @Autowired
    private CodingProblemService codingProblemService;

    @PostMapping("/execute/{id}")
    public ResponseEntity<String> executeCode(@RequestBody CodeExecutionRequest request,  @PathVariable Long id) {
    	System.out.println("rrr"+request.getTestCases());
    	CodingProblemDTO cp =codingProblemService.getProblemById(id);
    	List<TestCase> cs=cp.getTestCases();
        // Validate the request
        if (request.getCode() == null || request.getTestCases() == null || request.getTestCases().isEmpty()) {
            return ResponseEntity.badRequest().body("No code or test cases provided.");
        }
        

        // Execute the code with the provided test cases
        String result = codeExecutionService.executeCode(request.getCode(), cs, request.getLanguage(), request.getVersionIndex());

        return ResponseEntity.ok(result);
    }
}
