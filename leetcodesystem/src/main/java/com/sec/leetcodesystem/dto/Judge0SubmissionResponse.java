package com.sec.leetcodesystem.dto;


public class Judge0SubmissionResponse {

    private String stdout;       // Output from the code execution
    private String stderr;       // Error, if any
    private String compile_output;  // Compilation output, if there's a compilation error
    private int status_id;       // Status of the execution (3 = accepted, 6 = compilation error, etc.)

    // Getters and Setters
    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    public String getCompile_output() {
        return compile_output;
    }

    public void setCompile_output(String compile_output) {
        this.compile_output = compile_output;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
