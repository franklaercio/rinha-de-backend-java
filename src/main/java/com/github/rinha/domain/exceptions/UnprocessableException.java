package com.github.rinha.domain.exceptions;

import com.github.rinha.utils.ConstantsUtil;

public class UnprocessableException extends RuntimeException {

    public UnprocessableException() {
        super(ConstantsUtil.INVALID_REQUEST);
    }
}
