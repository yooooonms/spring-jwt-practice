package com.yooooonms.jwt.user.controller.dto;

import com.yooooonms.jwt.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"name", "email"})
public class ProfileResponse {

    private String name;
    private String email;

    public ProfileResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

}
