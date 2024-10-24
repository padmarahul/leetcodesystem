package com.sec.leetcodesystem.dto;

public class CodingProblemDTO {
    
    private Long id;
    private String title;
    private String difficulty;
    private String category;
    private String description;
    private String inputFormat;
    private String outputFormat;
    private String exampleTestCases;

    // Constructor
    public CodingProblemDTO(Long id, String title, String difficulty, String category, String description, String inputFormat, String outputFormat, String exampleTestCases) {
        this.id = id;
        this.title = title;
        this.difficulty = difficulty;
        this.category = category;
        this.description = description;
        this.inputFormat = inputFormat;
        this.outputFormat = outputFormat;
        this.exampleTestCases = exampleTestCases;
    }

    // Getters and Setters
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
