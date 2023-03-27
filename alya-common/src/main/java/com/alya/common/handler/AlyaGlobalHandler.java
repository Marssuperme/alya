package com.alya.common.handler;

import com.alya.common.exception.AlyaException;
import com.alya.common.vo.AlyaError;
import com.alya.common.vo.AlyaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author alya
 */
@RestControllerAdvice
public class AlyaGlobalHandler {

    @ExceptionHandler(value = {AlyaException.class})
    public Object globalError(AlyaException ex) {
        return AlyaResult.fail(ex.getErrorType(), ex.getMessage());
    }

}
