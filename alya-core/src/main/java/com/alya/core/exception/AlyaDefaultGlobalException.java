package com.alya.core.exception;

import com.alya.core.exception.enums.ErrorType;

/**
 * @author gfye
 */
public class AlyaDefaultGlobalException extends RuntimeException {

    private final ErrorType errorType;

    public AlyaDefaultGlobalException() {
        this.errorType = ErrorType.SYSTEM_ERROR;
    }

    public AlyaDefaultGlobalException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AlyaDefaultGlobalException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public AlyaDefaultGlobalException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}
