package com.example.schedulemanagementappdev.schedule.dto;

import com.example.schedulemanagementappdev.comment.dto.CommentGetResponse;
import com.example.schedulemanagementappdev.comment.entity.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"scheduleId", "userName", "title", "content", "createdAt", "modifiedAt", "comments"})
public class ScheduleGetResponse {
    private final Long scheduleId;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentGetResponse> comments;

    public ScheduleGetResponse(Long scheduleId, String userName, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = null;
    }

    public ScheduleGetResponse(Long scheduleId, String userName, String title, String content,  LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentGetResponse> comments) {
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
