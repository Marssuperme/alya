package com.alya.web.exception;

import com.alya.web.exception.enums.ErrorType;

/**
 * @author alya
 */
public class ParamNotFoundException extends AlyaDefaultGlobalException {

    public ParamNotFoundException() {
        super(ErrorType.PARAM_NOT_FOUND);
    }

    public ParamNotFoundException(String message) {
        super(ErrorType.PARAM_NOT_FOUND, message);
    }

}
