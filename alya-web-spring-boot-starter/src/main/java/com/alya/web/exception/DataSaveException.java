package com.alya.web.exception;

import com.alya.web.exception.enums.ErrorType;

import java.io.Serializable;

/**
 * @author alya
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
