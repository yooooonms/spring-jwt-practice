package com.yooooonms.jwt.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @JsonProperty(value = "password")
    private String password;

}
