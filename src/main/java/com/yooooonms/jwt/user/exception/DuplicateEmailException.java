package com.yooooonms.jwt.user.exception;

import com.yooooonms.jwt.error.ErrorCode;
import com.yooooonms.jwt.error.exception.BaseException;

public class DuplicateEmailException extends BaseException {

    public DuplicateEmailException(ErrorCode errorCode) {
        super(errorCode);
    }

}
