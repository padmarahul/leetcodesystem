package com.sec.leetcodesystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "code_submissions")
public class CodeSubmission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "problem_id", nullable = false)
	private CodingProblem problem;

	@Lob
	@Column(nullable = false)
	private String code; // Submitted code

	@Column(nullable = false)
	private boolean passedTests; // Whether the code passed the test cases

	@Column(nullable = true)
	private String output; // Output from the code execution

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CodingProblem getProblem() {
		return problem;
	}

	public void setProblem(CodingProblem problem) {
		this.problem = problem;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isPassedTests() {
		return passedTests;
	}

	public void setPassedTests(boolean passedTests) {
		this.passedTests = passedTests;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
