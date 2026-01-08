package com.example.schedulemanagementappdev;

import com.example.schedulemanagementappdev.user.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException e){
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(response);
    }
}
