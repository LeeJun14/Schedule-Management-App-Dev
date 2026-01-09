package com.example.schedulemanagementappdev.comment.service;

import com.example.schedulemanagementappdev.comment.dto.CommentCreateRequest;
import com.example.schedulemanagementappdev.comment.dto.CommentCreateResponse;
import com.example.schedulemanagementappdev.comment.dto.CommentGetResponse;
import com.example.schedulemanagementappdev.comment.entity.Comment;
import com.example.schedulemanagementappdev.comment.repository.CommentRepository;
import com.example.schedulemanagementappdev.schedule.entity.Schedule;
import com.example.schedulemanagementappdev.schedule.exception.ScheduleNotFoundException;
import com.example.schedulemanagementappdev.schedule.repository.ScheduleRepository;
import com.example.schedulemanagementappdev.user.dto.SessionUser;
import com.example.schedulemanagementappdev.user.entity.User;
import com.example.schedulemanagementappdev.user.exception.UserSystemException;
import com.example.schedulemanagementappdev.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성
    @Transactional
    public CommentCreateResponse save(SessionUser sessionUser, Long scheduleId, CommentCreateRequest request) {
        User user = userRepository.findById(sessionUser.getUserId()).orElseThrow(
                () -> new UserSystemException("시스템 오류가 발생했습니다.")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new ScheduleNotFoundException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment(user, schedule, request.getContent());
        Comment savedComment = commentRepository.save(comment);
        return new CommentCreateResponse(savedComment.getCommentId(), savedComment.getUser().getUserName(),savedComment.getSchedule().getScheduleId(), savedComment.getCreatedAt(), savedComment.getModifiedAt());
    }

    // 댓글 전체 조회
    @Transactional(readOnly = true)
    public List<CommentGetResponse> findAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentGetResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentGetResponse dto = new CommentGetResponse(comment.getCommentId(),comment.getContent(),comment.getUser().getUserName(), comment.getCreatedAt(), comment.getModifiedAt());
            dtos.add(dto);
        }
        return dtos;
    }
}
