package com.sec.leetcodesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.leetcodesystem.dto.CodingProblemDTO;
import com.sec.leetcodesystem.entities.CodingProblem;
import com.sec.leetcodesystem.exceptions.ResourceNotFoundException;
import com.sec.leetcodesystem.repository.CodingProblemRepository;
import com.sec.leetcodesystem.service.CodingProblemService;

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
    
    
 // Helper method to convert CodingProblem entity to CodingProblemDTO
    private CodingProblemDTO convertToDTO(CodingProblem problem) {
        return new CodingProblemDTO(
                problem.getId(),
                problem.getTitle(),
                problem.getDifficulty(),
                problem.getCategory(),
                problem.getDescription(),
                problem.getInputFormat(),
                problem.getOutputFormat(),
                problem.getExampleTestCases()
        );
    }

	@Override
	public List<CodingProblemDTO> findAll() {
		List<CodingProblem> problems = codingProblemRepository.findAll();
        return problems.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
}
