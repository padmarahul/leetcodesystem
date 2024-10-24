package com.sec.leetcodesystem.dto;

public class Judge0SubmissionRequest {

    private String source_code;  // Code submitted by the user
    private int language_id;     // Language ID for the code (e.g., 71 for Python)
    private String stdin;        // Input for the code

    // Getters and Setters
    public String getSource_code() {
        return source_code;
    }

    public void setSource_code(String source_code) {
        this.source_code = source_code;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }
}
