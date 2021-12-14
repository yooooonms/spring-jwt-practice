package com.yooooonms.jwt.user.service;

import com.yooooonms.jwt.error.ErrorCode;
import com.yooooonms.jwt.security.jwt.JwtTokenProvider;
import com.yooooonms.jwt.user.controller.dto.LoginRequest;
import com.yooooonms.jwt.user.controller.dto.ProfileResponse;
import com.yooooonms.jwt.user.controller.dto.SignupRequest;
import com.yooooonms.jwt.user.domain.User;
import com.yooooonms.jwt.user.exception.DuplicateEmailException;
import com.yooooonms.jwt.user.exception.NotFoundUserException;
import com.yooooonms.jwt.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        log.info("[UserService] signupRequest= {}", signupRequest);

        checkEmailDuplicate(signupRequest.getEmail());

        String encoded = passwordEncryption(signupRequest.getPassword());
        log.info("[UserService] encoded= {}", encoded);

        User user = User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .password(encoded)
                .build();
        log.info("[UserService] user= {}", user);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public ProfileResponse profile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_ENTITY));
        return new ProfileResponse(user);
    }

    @Transactional(readOnly = true)
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_ENTITY));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return jwtTokenProvider.createToken(loginRequest.getEmail(), user.getRole());
    }

    private void checkEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private String passwordEncryption(String password) {
        return passwordEncoder.encode(password);
    }

}
