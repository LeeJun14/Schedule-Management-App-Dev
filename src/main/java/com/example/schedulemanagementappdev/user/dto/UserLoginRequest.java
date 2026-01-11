package com.example.schedulemanagementappdev.user.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
