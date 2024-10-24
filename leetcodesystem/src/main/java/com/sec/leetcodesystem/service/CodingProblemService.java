package com.sec.leetcodesystem.service;

import java.util.List;

import com.sec.leetcodesystem.dto.CodingProblemDTO;
import com.sec.leetcodesystem.entities.CodingProblem;

public interface CodingProblemService {
    List<CodingProblemDTO> getProblemsByDifficulty(String difficulty);
    List<CodingProblemDTO> getProblemsByCategory(String category);
    CodingProblemDTO getProblemById(Long id);
    List<CodingProblemDTO>findAll();
}