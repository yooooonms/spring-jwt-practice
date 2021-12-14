package com.yooooonms.jwt.user.controller;

import com.yooooonms.jwt.security.jwt.LoginUser;
import com.yooooonms.jwt.user.controller.dto.LoginRequest;
import com.yooooonms.jwt.user.controller.dto.ProfileResponse;
import com.yooooonms.jwt.user.controller.dto.SignupRequest;
import com.yooooonms.jwt.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private static final String MAIN_PAGE = "/";

    private final UserService userService;

    @PostMapping(path = "/api/v1/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody final SignupRequest signupRequest) {
        log.info("[UserController] signupReequest= {}", signupRequest);
        userService.signup(signupRequest);

        return ResponseEntity.created(URI.create(MAIN_PAGE)).build();
    }

    @PostMapping(path = "/auth/login")
    public ResponseEntity<Void> login(@Valid @RequestBody final LoginRequest loginRequest) {
        log.info("[UserController] loginRequest= {}", loginRequest);
        String token = userService.login(loginRequest);
        log.info("[UserController] token= {}", token);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/api/v1/profile/{email}")
    public ResponseEntity<ProfileResponse> profile(@PathVariable(value = "email") final String email) {
        log.info("[UserController] email= {}", email);

        ProfileResponse profile = userService.profile(email);
        log.info("[UserController] profle= {}", profile);

        return ResponseEntity.ok(profile);
    }

    @GetMapping(path = "/api/v1/profile")
    public ResponseEntity<ProfileResponse> profileV2(@LoginUser String email) {
        log.info("[UserController] email= {}", email);

        ProfileResponse profile = userService.profile(email);
        log.info("[UserController] profle= {}", profile);

        return ResponseEntity.ok(profile);
    }

}
