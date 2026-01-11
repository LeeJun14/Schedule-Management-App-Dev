package com.example.schedulemanagementappdev.schedule.service;

import com.example.schedulemanagementappdev.comment.dto.CommentGetResponse;
import com.example.schedulemanagementappdev.comment.entity.Comment;
import com.example.schedulemanagementappdev.comment.repository.CommentRepository;
import com.example.schedulemanagementappdev.schedule.dto.*;
import com.example.schedulemanagementappdev.schedule.entity.Schedule;
import com.example.schedulemanagementappdev.schedule.exception.ScheduleNotFoundException;
import com.example.schedulemanagementappdev.schedule.repository.ScheduleRepository;
import com.example.schedulemanagementappdev.user.entity.User;
import com.example.schedulemanagementappdev.user.exception.UserSystemException;
import com.example.schedulemanagementappdev.user.exception.UserUnauthorizedException;
import com.example.schedulemanagementappdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 일정 생성
    @Transactional
    public ScheduleCreateResponse save(Long userId, ScheduleCreateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserSystemException("시스템 오류가 발생했습니다.")
        );
        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(savedSchedule.getScheduleId(), savedSchedule.getUser().getUserName(), savedSchedule.getTitle(), savedSchedule.getContent(), savedSchedule.getCreatedAt());
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public Page<ScheduleGetResponse> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modifiedAt"));
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);

        return schedules.map(schedule -> new ScheduleGetResponse(schedule.getScheduleId(), schedule.getUser().getUserName(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()));
    }

    // 일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        List<Comment> comments = commentRepository.findByScheduleScheduleId(scheduleId);
        List<CommentGetResponse> responses = comments.stream().map(comment -> new CommentGetResponse(comment.getCommentId(), comment.getContent(), comment.getUser().getUserName(), comment.getCreatedAt(), comment.getModifiedAt())).toList();
        return new ScheduleGetResponse(schedule.getScheduleId(), schedule.getUser().getUserName(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt(), responses);
    }

    // 일정 수정
    @Transactional
    public ScheduleUpdateResponse update(Long userId, Long scheduleId, ScheduleUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserSystemException("시스템 오류가 발생했습니다.")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        if(!Objects.equals(user.getUserId(), schedule.getUser().getUserId())) {
            throw new UserUnauthorizedException("해당 일정을 수정할 수 있는 권한이 없습니다.");
        }
        schedule.update(request.getTitle(), request.getContent());
        return new ScheduleUpdateResponse(schedule.getScheduleId(), schedule.getUser().getUserName(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    // 일정 삭제
    @Transactional
    public void delete(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserSystemException("시스템 오류가 발생했습니다.")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        if(user.getUserId() != schedule.getUser().getUserId()) {
            throw new UserUnauthorizedException("해당 일정을 삭제할 수 있는 권한이 없습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
