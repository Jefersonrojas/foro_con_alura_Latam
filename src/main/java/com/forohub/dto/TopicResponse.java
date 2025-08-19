package com.forohub.dto;

import java.time.LocalDateTime;

public class TopicResponse {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private String status;
    private String author;
    private String course;

    public TopicResponse(Long id, String title, String message, LocalDateTime createdAt, String status, String author, String course) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
        this.author = author;
        this.course = course;
    }

    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public String getMessage(){ return message; }
    public LocalDateTime getCreatedAt(){ return createdAt; }
    public String getStatus(){ return status; }
    public String getAuthor(){ return author; }
    public String getCourse(){ return course; }
}
