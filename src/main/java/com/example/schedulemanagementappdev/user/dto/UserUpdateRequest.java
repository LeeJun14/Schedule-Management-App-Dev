package com.example.schedulemanagementappdev.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    @NotBlank(message = "유저명은 필수 값 입니다.")
    @Column(nullable = false, unique = true)
    private String username;
}
