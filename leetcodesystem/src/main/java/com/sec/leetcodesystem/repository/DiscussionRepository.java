package com.sec.leetcodesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sec.leetcodesystem.entities.Discussion;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    List<Discussion> findAllByOrderByCreatedAtDesc();
}

