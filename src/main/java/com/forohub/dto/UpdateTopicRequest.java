package com.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateTopicRequest {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150)
    private String title;

    @NotBlank(message = "El mensaje es obligatorio")
    private String message;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 30)
    private String status;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
