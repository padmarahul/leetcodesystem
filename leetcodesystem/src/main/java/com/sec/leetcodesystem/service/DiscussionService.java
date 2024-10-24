package com.sec.leetcodesystem.service;

import java.util.List;

import com.sec.leetcodesystem.dto.DiscussionDTO;
import com.sec.leetcodesystem.entities.Discussion;

public interface DiscussionService {
	DiscussionDTO createDiscussion(int customerId, String title, String content);
    List<DiscussionDTO> getAllDiscussions();
}
