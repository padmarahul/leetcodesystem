package com.sec.leetcodesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sec.leetcodesystem.dto.CodingProblemDTO;
import com.sec.leetcodesystem.entities.CodingProblem;
import com.sec.leetcodesystem.exceptions.ResourceNotFoundException;
import com.sec.leetcodesystem.service.CodingProblemService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/lcms/coding-problems")
public class CodingProblemController {

    @Autowired
    private CodingProblemService codingProblemService;

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<CodingProblemDTO>> getProblemsByDifficulty(@PathVariable String difficulty) {
        try {
            List<CodingProblemDTO> problems = codingProblemService.getProblemsByDifficulty(difficulty);
            return ResponseEntity.ok(problems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
   
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CodingProblemDTO>> getProblemsByCategory(@PathVariable String category) {
        try {
            List<CodingProblemDTO> problems = codingProblemService.getProblemsByCategory(category);
            return ResponseEntity.ok(problems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/problem/{id}")
    public ResponseEntity<CodingProblemDTO> getProblemById(@PathVariable Long id) {
        try {
        	CodingProblemDTO problem = codingProblemService.getProblemById(id);
            return ResponseEntity.ok(problem);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/problems")
    public ResponseEntity<List<CodingProblemDTO>> getAllProblems() {
        try {
        	List<CodingProblemDTO> problem = codingProblemService.findAll();
            return ResponseEntity.ok(problem);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
