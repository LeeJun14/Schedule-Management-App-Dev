package com.example.schedulemanagementappdev.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponse {
    private final Long scheduleId;
    private final Long commentId;
    private final String userName;
    private final String content;
    private final LocalDateTime createdAt;

    public  CommentCreateResponse(Long scheduleId, Long commentId, String userName, String content, LocalDateTime createdAt) {
        this.scheduleId = scheduleId;
        this.commentId = commentId;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
    }
}
