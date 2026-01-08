package com.example.schedulemanagementappdev.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSignupResponse {
    private final Long userId;
    private final String email;
    private final String userName;
    private final LocalDateTime signupDate;


    public UserSignupResponse(Long userId, String email, String userName, LocalDateTime signupDate) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.signupDate = signupDate;
    }
}
