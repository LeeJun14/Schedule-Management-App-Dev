package com.example.schedulemanagementappdev.user.controller;

import com.example.schedulemanagementappdev.user.dto.*;
import com.example.schedulemanagementappdev.user.exception.UserUnauthorizedException;
import com.example.schedulemanagementappdev.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponse> signup(@Valid @RequestBody UserSignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody UserLoginRequest request, HttpSession session) {
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("user", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 유저 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserGetResponse>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 유저 단건 조회
    @GetMapping("/users/{userName}")
    public ResponseEntity<UserGetResponse> getOne(@PathVariable String userName) {
        return ResponseEntity.ok(userService.findOne(userName));
    }

    // 유저 수정
    @PatchMapping("/users")
    public ResponseEntity<UserUpdateResponse> update(@SessionAttribute(name = "user", required = false) SessionUser sessionUser, @Valid @RequestBody UserUpdateRequest request){
        if(sessionUser == null){
            throw new UserUnauthorizedException("유저 수정 권한이 없습니다.");
        }
        return ResponseEntity.ok(userService.update(request, sessionUser.getUserId()));
    }

    // 유저 삭제
    @DeleteMapping("/users")
    public ResponseEntity<Void> delete(@SessionAttribute(name = "user", required = false) SessionUser sessionUser){
        if(sessionUser == null){
            throw new UserUnauthorizedException("유저 삭제 권한이 없습니다.");
        }
        userService.delete(sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
