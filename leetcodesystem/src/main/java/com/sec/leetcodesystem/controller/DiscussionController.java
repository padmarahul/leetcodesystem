package com.sec.leetcodesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sec.leetcodesystem.dto.DiscussionDTO;
import com.sec.leetcodesystem.exceptions.ResourceNotFoundException;
import com.sec.leetcodesystem.service.DiscussionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") 
@RestController
@RequestMapping("/lcms/discussions")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @PostMapping("/creatediscussion")
    public ResponseEntity<DiscussionDTO> createDiscussion(
            @RequestParam int customerId,
            @RequestParam String title,
            @RequestParam String content) throws ResourceNotFoundException {

        try {
            DiscussionDTO discussionDTO = discussionService.createDiscussion(customerId, title, content);
            return ResponseEntity.ok(discussionDTO);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/getalldiscussions")
    public ResponseEntity<List<DiscussionDTO>> getAllDiscussions() {
        try {
            List<DiscussionDTO> discussions = discussionService.getAllDiscussions();
            return ResponseEntity.ok(discussions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
