package com.example.schedulemanagementappdev.user.dto;

import lombok.Getter;

@Getter
public class UserSignupRequest {
    private String email;
    private String password;
    private String userName;
}
