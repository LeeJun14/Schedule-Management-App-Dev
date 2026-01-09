package com.example.schedulemanagementappdev.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponse {
    private final Long commentId;
    private final String userName;
    private final Long scheduleId;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentCreateResponse(Long commentId, String userName, Long scheduleId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.userName = userName;
        this.scheduleId = scheduleId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
