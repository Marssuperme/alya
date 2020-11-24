package com.alya.core.exception.enums;

/**
 * @author gfye
 */
public enum ErrorType {

    /**
     * Data Not Found
     */
    DATA_NOT_FOUND(404001, "Data Not Found!"),

    /**
     * Parameter Not Found
     */
    PARAM_NOT_FOUND(404002, "Parameter Not Found!"),

    /**
     * Data Saved Error
     */
    DATA_SAVE_ERROR(500001, "Data Saved Error!"),

    /**
     * System Error
     */
    SYSTEM_ERROR(500, "System Error!");

    private final Integer code;

    private final String msg;

    ErrorType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
