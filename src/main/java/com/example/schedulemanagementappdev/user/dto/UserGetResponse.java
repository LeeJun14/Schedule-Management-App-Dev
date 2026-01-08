package com.example.schedulemanagementappdev.user.dto;

import lombok.Getter;

@Getter
public class UserGetResponse {
    private final Long userId;
    private final String email;
    private final String userName;

    public UserGetResponse(Long userId, String email, String userName) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }
}
