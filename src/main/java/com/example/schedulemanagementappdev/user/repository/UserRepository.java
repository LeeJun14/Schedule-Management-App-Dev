package com.example.schedulemanagementappdev.user.repository;

import com.example.schedulemanagementappdev.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(@Valid @NotBlank String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(@Valid @NotBlank String userName);
    boolean existsByUserName(@Valid @NotBlank String userName);
}
