package com.example.schedulemanagementappdev.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleCreateResponse {
    private final Long scheduleId;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public ScheduleCreateResponse(Long scheduleId, String userName, String title, String content, LocalDateTime createdAt) {
        this.scheduleId = scheduleId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
