package com.yooooonms.jwt.security;

import com.yooooonms.jwt.error.ErrorCode;
import com.yooooonms.jwt.user.domain.User;
import com.yooooonms.jwt.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.NOT_FOUND_ENTITY.getMessage()));
        log.info("[CustomUserDetailsService] email= {}", email);
        log.info("[CustomUserDetailsService] user= {}", user);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().getKey())
                .build();
    }

}
