package com.example.schedulemanagementappdev.user.service;

import com.example.schedulemanagementappdev.config.PasswordEncoder;
import com.example.schedulemanagementappdev.user.dto.*;
import com.example.schedulemanagementappdev.user.entity.User;
import com.example.schedulemanagementappdev.user.exception.UserBadRequestException;
import com.example.schedulemanagementappdev.user.exception.UserNotFoundException;
import com.example.schedulemanagementappdev.user.exception.UserSystemException;
import com.example.schedulemanagementappdev.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public UserSignupResponse save(@Valid UserSignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new UserBadRequestException("현재 사용 중인 이메일 입니다.");
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getEmail(), encodedPassword, request.getUserName());
        User savedUser = userRepository.save(user);
        return new UserSignupResponse(savedUser.getUserId(),savedUser.getEmail(), savedUser.getUserName(),savedUser.getCreatedAt());
    }

    // 로그인
    @Transactional
    public SessionUser login(@Valid UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UserBadRequestException("이메일 또는 비밀번호가 일치하지 않습니다.")
        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserBadRequestException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return new SessionUser(user.getUserId(), user.getEmail(), user.getUserName());
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public List<UserGetResponse> findAll() {
        List<User> users = userRepository.findAll();
        List<UserGetResponse> dtos = new ArrayList<>();
        for (User user : users) {
            UserGetResponse dto = new UserGetResponse(user.getUserId(), user.getEmail(), user.getUserName());
            dtos.add(dto);
        }
        return dtos;
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public UserGetResponse findOne(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UserNotFoundException("해당 이름의 유저는 존재하지 않습니다.")
        );
        return new UserGetResponse(user.getUserId(), user.getEmail(), user.getUserName());
    }

    // 유저 수정
    @Transactional
    public UserUpdateResponse update(UserUpdateRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserSystemException("시스템 오류가 발생했습니다.")
        );
        boolean existence = userRepository.existsByUserName(user.getUserName());
        if (existence) {
            throw new UserBadRequestException("이미 존재하는 유저명입니다.");
        }
        user.update(request.getUsername());
        return new UserUpdateResponse(user.getUserId(), user.getEmail(), user.getUserName(), user.getModifiedAt());
    }

    // 유저 삭제
    @Transactional
    public void delete(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserSystemException("시스템 오류가 발생했습니다.");
        }
        userRepository.deleteById(userId);
    }
}
