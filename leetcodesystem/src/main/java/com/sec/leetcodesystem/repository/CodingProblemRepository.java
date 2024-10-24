package com.sec.leetcodesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.leetcodesystem.entities.CodingProblem;

import java.util.List;

public interface CodingProblemRepository extends JpaRepository<CodingProblem, Long> {
    List<CodingProblem> findByDifficulty(String difficulty);
    List<CodingProblem> findByCategory(String category);
}
