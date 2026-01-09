package com.example.schedulemanagementappdev.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserSignupRequest {
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank
    private String email;
    @Size(min = 8, message = "최소 8자 이상이어야 합니다.")
    @NotBlank
    private String password;
    @NotBlank(message = "유저명은 필수 값 입니다.")
    @Column(nullable = false, unique = true)
    private String userName;
}
