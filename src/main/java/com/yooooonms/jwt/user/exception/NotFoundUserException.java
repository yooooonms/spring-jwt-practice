package com.yooooonms.jwt.user.exception;

import com.yooooonms.jwt.error.ErrorCode;
import com.yooooonms.jwt.error.exception.BaseException;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
