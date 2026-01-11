package com.example.schedulemanagementappdev.comment.repository;

import com.example.schedulemanagementappdev.comment.entity.Comment;
import com.example.schedulemanagementappdev.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleScheduleId(Long scheduleId);
}
