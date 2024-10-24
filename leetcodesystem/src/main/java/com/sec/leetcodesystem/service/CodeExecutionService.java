package com.sec.leetcodesystem.service;

import java.util.List;

import com.sec.leetcodesystem.dto.TestCase;

public interface CodeExecutionService {

	  String executeCode(String code, List<TestCase> testCases, String language, String versionIndex);
}
