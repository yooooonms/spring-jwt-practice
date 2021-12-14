package com.yooooonms.jwt.error.handler;

import com.yooooonms.jwt.error.ErrorResponse;
import com.yooooonms.jwt.error.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        log.info("[GlobalExceptionHandler] Error!! BaseException");
        return null;
    }

}
