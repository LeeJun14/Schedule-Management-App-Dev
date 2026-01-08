package com.example.schedulemanagementappdev.user.exception;

import org.springframework.http.HttpStatus;

public class UserSystemException extends UserException{
    public UserSystemException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
