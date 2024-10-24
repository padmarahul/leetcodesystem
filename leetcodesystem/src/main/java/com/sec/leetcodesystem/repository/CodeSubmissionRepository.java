package com.sec.leetcodesystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.leetcodesystem.entities.CodeSubmission;
import com.sec.leetcodesystem.entities.CodingProblem;
import com.sec.leetcodesystem.entities.Customer;

public interface CodeSubmissionRepository extends JpaRepository<CodeSubmission, Long> {
    List<CodeSubmission> findByCustomer(Customer customer);
    List<CodeSubmission> findByProblem(CodingProblem problem);
}
