package com.example.schedulemanagementappdev.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserBadRequestException extends UserException {
    public UserBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
