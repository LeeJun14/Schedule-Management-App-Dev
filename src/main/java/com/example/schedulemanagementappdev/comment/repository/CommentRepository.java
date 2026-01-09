package com.example.schedulemanagementappdev.comment.repository;

import com.example.schedulemanagementappdev.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
