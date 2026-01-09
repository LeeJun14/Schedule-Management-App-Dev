package com.example.schedulemanagementappdev.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    @NotBlank
    @Size(max = 100)
    private String content;
}
