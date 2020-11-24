package com.alya.core.exception;

import com.alya.core.exception.enums.ErrorType;

import java.io.Serializable;

/**
 * @author gfye
 */
public class DataSaveException extends AlyaDefaultGlobalException {

    public DataSaveException() {
        super(ErrorType.DATA_SAVE_ERROR);
    }

    public DataSaveException(String message) {
        super(ErrorType.DATA_SAVE_ERROR, message);
    }

    public DataSaveException(Serializable id) {
        super(ErrorType.DATA_SAVE_ERROR, String.format("id：%s", id));
    }
}
