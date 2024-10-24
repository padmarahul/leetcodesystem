package com.sec.leetcodesystem.dto;

import java.time.LocalDateTime;

public class DiscussionDTO {

    private Long id;
    private String title;
    private String content;
    private String customerName;
    private LocalDateTime createdAt;

    // Constructors
    public DiscussionDTO() {
    }

    public DiscussionDTO(Long id, String title, String content, String customerName, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.customerName = customerName;
        this.createdAt = createdAt;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
