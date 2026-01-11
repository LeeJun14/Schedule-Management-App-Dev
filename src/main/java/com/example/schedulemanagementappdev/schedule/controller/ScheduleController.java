package com.example.schedulemanagementappdev.schedule.controller;

import com.example.schedulemanagementappdev.schedule.dto.*;
import com.example.schedulemanagementappdev.schedule.service.ScheduleService;
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
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(@SessionAttribute(name = "user", required = false) SessionUser sessionUser, @Valid @RequestBody ScheduleCreateRequest request) {
        if(sessionUser == null){
            throw new UserUnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(sessionUser.getUserId(), request));
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findOne(scheduleId));
    }

    // 일정 수정
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(@SessionAttribute(name = "user", required = false) SessionUser sessionUser, @PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequest request) {
        if(sessionUser == null){
            throw new UserUnauthorizedException("로그인이 필요합니다.");
        }
        return ResponseEntity.ok(scheduleService.update(sessionUser.getUserId(), scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> delete(@SessionAttribute(name = "user", required = false) SessionUser sessionUser, @PathVariable Long scheduleId) {
        if(sessionUser == null){
            throw new UserUnauthorizedException("로그인이 필요합니다.");
        }
        scheduleService.delete(sessionUser.getUserId(), scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
