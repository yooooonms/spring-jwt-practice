package com.yooooonms.jwt.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"name", "email", "password"})
public class SignupRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @JsonProperty(value = "password")
    private String password;

}
