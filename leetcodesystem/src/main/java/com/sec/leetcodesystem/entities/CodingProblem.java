package com.sec.leetcodesystem.entities;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "coding_problems")
public class CodingProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;  // Title of the coding problem

    @Column(nullable = false)
    private String difficulty;  // Difficulty level (easy, medium, hard)

    @Column(nullable = false)
    private String category;  // Category like Array, Stack, etc.

    @Lob
    @Column(nullable = false)
    private String description;  // The coding problem description

    @Lob
    @Column(nullable = false)
    private String inputFormat;  // Input format for the coding question

    @Lob
    @Column(nullable = false)
    private String outputFormat;  // Output format for the coding question

    @Lob
    @Column(nullable = false)
    private String exampleTestCases;  // Example test cases for the problem
    
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CodeSubmission> codeSubmissions = new ArrayList<>();


    // Getters and Setters

    public List<CodeSubmission> getCodeSubmissions() {
		return codeSubmissions;
	}

	public void setCodeSubmissions(List<CodeSubmission> codeSubmissions) {
		this.codeSubmissions = codeSubmissions;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getExampleTestCases() {
        return exampleTestCases;
    }

    public void setExampleTestCases(String exampleTestCases) {
        this.exampleTestCases = exampleTestCases;
    }
}
