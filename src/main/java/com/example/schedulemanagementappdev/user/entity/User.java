package com.example.schedulemanagementappdev.user.entity;

import com.example.schedulemanagementappdev.config.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 8, message = "최소 8자 이상이어야 합니다.")
    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "유저명은 필수 값 입니다.")
    @Column(nullable = false, unique = true)
    private String userName;

    public User(String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public void update(String userName) {
        this.userName = userName;
    }


}
