package com.alya.core.exception;

import com.alya.core.exception.enums.ErrorType;

import java.io.Serializable;

/**
 * @author gfye
 */
public class DataNotFoundException extends AlyaDefaultGlobalException {

    public DataNotFoundException() {
        super(ErrorType.DATA_NOT_FOUND);
    }

    public DataNotFoundException(String message) {
        super(ErrorType.DATA_NOT_FOUND, message);
    }

    public DataNotFoundException(Serializable id) {
        super(ErrorType.DATA_NOT_FOUND, String.format("id：%s", id));
    }
}
