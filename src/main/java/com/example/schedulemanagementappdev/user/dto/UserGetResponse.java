package com.example.schedulemanagementappdev.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserGetResponse {
    private final Long userId;
    private final String userName;
    private final LocalDateTime signupAt;

    public UserGetResponse(Long userId, String userName,  LocalDateTime signupAt) {
        this.userId = userId;
        this.userName = userName;
        this.signupAt = signupAt;
    }
}
