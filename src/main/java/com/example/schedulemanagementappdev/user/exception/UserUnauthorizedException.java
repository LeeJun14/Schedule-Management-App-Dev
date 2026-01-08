package com.example.schedulemanagementappdev.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserUnauthorizedException extends UserException {
    public UserUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
