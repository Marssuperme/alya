package com.alya.web.exception;

import com.alya.web.exception.enums.ErrorType;

/**
 * @author alya
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
