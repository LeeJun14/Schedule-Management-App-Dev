package com.example.schedulemanagementappdev.comment.controller;

import com.example.schedulemanagementappdev.comment.dto.CommentCreateRequest;
import com.example.schedulemanagementappdev.comment.dto.CommentCreateResponse;
import com.example.schedulemanagementappdev.comment.dto.CommentGetResponse;
import com.example.schedulemanagementappdev.comment.service.CommentService;
import com.example.schedulemanagementappdev.user.dto.SessionUser;
import com.example.schedulemanagementappdev.user.exception.UserUnauthorizedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentCreateResponse> create(@SessionAttribute(name = "user", required = false) SessionUser sessionUser, @PathVariable Long scheduleId, @Valid @RequestBody CommentCreateRequest request) {
        if(sessionUser == null){
            throw new UserUnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(sessionUser, scheduleId, request));
    }

    // 댓글 전체 조회
    @GetMapping("/comments")
    public ResponseEntity<List<CommentGetResponse>> getAll() {
        return ResponseEntity.ok(commentService.findAll());
    }
}
