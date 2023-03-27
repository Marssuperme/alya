package com.alya.common.exception;

import com.alya.common.enums.ErrorType;

/**
 * @author alya
 */
public class AlyaException extends RuntimeException {

    private final ErrorType errorType;

    public AlyaException() {
        this.errorType = ErrorType.SYSTEM_ERROR;
    }

    public AlyaException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AlyaException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}
