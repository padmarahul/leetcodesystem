package com.sec.leetcodesystem.service;

import com.sec.leetcodesystem.dto.Judge0SubmissionRequest;
import com.sec.leetcodesystem.dto.Judge0SubmissionResponse;
import com.sec.leetcodesystem.entities.CodeSubmission;

public interface CodeSubmissionService {

	public CodeSubmission submitCode(int customerId, Long problemId, String code, int languageId) throws Exception;
	public Judge0SubmissionResponse executeCodeOnJudge0(Judge0SubmissionRequest judge0Request) throws Exception;
}
