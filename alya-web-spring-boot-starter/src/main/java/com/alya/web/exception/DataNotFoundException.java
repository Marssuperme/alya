package com.alya.web.exception;

import com.alya.web.exception.enums.ErrorType;

import java.io.Serializable;

/**
 * @author alya
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
