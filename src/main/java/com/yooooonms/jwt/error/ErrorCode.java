package com.yooooonms.jwt.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOT_FOUND_ENTITY(400, "C000", "not found entity"),

    DUPLICATE_EMAIL(400, "U000", "dulicate email");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
