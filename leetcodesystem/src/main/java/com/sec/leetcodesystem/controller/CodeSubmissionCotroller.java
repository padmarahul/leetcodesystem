package com.sec.leetcodesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sec.leetcodesystem.dto.CodeExecutionRequest;
import com.sec.leetcodesystem.service.CodeExecutionService;

import java.util.List;

@RestController
@RequestMapping("/lcms/coding-submission")
public class CodeSubmissionCotroller {

    @Autowired
    private CodeExecutionService codeExecutionService;

    @PostMapping("/execute")
    public ResponseEntity<String> executeCode(@RequestBody CodeExecutionRequest request) {
        // Validate the request
        if (request.getCode() == null || request.getTestCases() == null || request.getTestCases().isEmpty()) {
            return ResponseEntity.badRequest().body("No code or test cases provided.");
        }

        // Execute the code with the provided test cases
        String result = codeExecutionService.executeCode(request.getCode(), request.getTestCases(), request.getLanguage(), request.getVersionIndex());

        return ResponseEntity.ok(result);
    }
}
