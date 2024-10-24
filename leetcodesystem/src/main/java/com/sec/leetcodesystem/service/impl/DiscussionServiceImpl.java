package com.sec.leetcodesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.leetcodesystem.dto.DiscussionDTO;
import com.sec.leetcodesystem.entities.Customer;
import com.sec.leetcodesystem.entities.Discussion;
import com.sec.leetcodesystem.exceptions.ResourceNotFoundException;
import com.sec.leetcodesystem.repository.CustomerRepository;
import com.sec.leetcodesystem.repository.DiscussionRepository;
import com.sec.leetcodesystem.service.DiscussionService;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DiscussionDTO createDiscussion(int customerId, String title, String content) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        
        Discussion discussion = new Discussion();
        discussion.setCustomer(customer);
        discussion.setTitle(title);
        discussion.setContent(content);
        
        Discussion savedDiscussion = discussionRepository.save(discussion);
        
        return mapToDTO(savedDiscussion);
    }

    @Override
    public List<DiscussionDTO> getAllDiscussions() {
        List<Discussion> discussions = discussionRepository.findAllByOrderByCreatedAtDesc();
        return discussions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Method to map Discussion entity to DTO
    private DiscussionDTO mapToDTO(Discussion discussion) {
        return new DiscussionDTO(
            discussion.getId(),
            discussion.getTitle(),
            discussion.getContent(),
            discussion.getCustomer().getFullName(),
            discussion.getCreatedAt()
        );
    }
}
