package com.example.schedulemanagementappdev.user.dto;

import lombok.Getter;


import java.time.LocalDateTime;

@Getter
public class UserUpdateResponse {
    private final Long userId;
    private final String email;
    private final String userName;
    private final LocalDateTime modifiedAt;

    public UserUpdateResponse(Long userId, String email, String userName, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.modifiedAt = modifiedAt;
    }
}
