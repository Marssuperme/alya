package com.alya.core.handler;

import com.alya.core.api.ResponseResult;
import com.alya.core.exception.DataNotFoundException;
import com.alya.core.exception.DataSaveException;
import com.alya.core.exception.ParamNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author alya
 */
public class DefaultExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandlerAdvice.class);

    public boolean isDefaultExceptionResponse(Object o) {
        return o instanceof ResponseResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Object> exception(Exception e) {
        LOGGER.error("Base exception: {}", e.getMessage());
        return ResponseResult.fail();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult<Object> throwable(Throwable t) {
        LOGGER.error("Base throwable: {}", t.getMessage());
        return ResponseResult.fail();
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseResult<String> dataNotFound(DataNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseResult.fail(ex.getErrorType(), ex.getMessage());
    }

    @ExceptionHandler(ParamNotFoundException.class)
    public ResponseResult<Object> paramNotFound(ParamNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseResult.fail(ex.getErrorType(), ex.getMessage());
    }

    @ExceptionHandler(DataSaveException.class)
    public ResponseResult<Object> dataSaveError(DataSaveException ex) {
        LOGGER.error(ex.getMessage());
        return ResponseResult.fail(ex.getErrorType(), ex.getMessage());
    }


}
