package com.alya.core.exception;

import com.alya.core.exception.enums.ErrorType;

/**
 * @author gfye
 */
public class ParamNotFoundException extends AlyaDefaultGlobalException {

    public ParamNotFoundException() {
        super(ErrorType.PARAM_NOT_FOUND);
    }

    public ParamNotFoundException(String message) {
        super(ErrorType.PARAM_NOT_FOUND, message);
    }

}
