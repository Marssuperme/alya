package com.alya.common.enums;

/**
 * @author alya
 */
public enum ErrorType {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(500, "系统错误！"),

    /**
     * 登录会话失效
     */
    LOGIN_INVALID(5001, "会话过期，请重新登录！"),

    /**
     * 无效访问
     */
    USER_NOT_AUTH(5002, "无效访问，用户权限不足！"),

    /**
     * 数据不存在
     */
    DATA_NOT_FOUND(5004, "数据不存在！"),

    /**
     * 缺少参数
     */
    PARAM_NOT_FOUND(5005, "缺少参数！"),

    /**
     * 用户不存在或者已被禁用
     */
    USER_INVALID(5006, "用户不存在或者已被禁用！"),

    /**
     * 非法请求
     */
    ILLEGAL_REQUEST(5007, "非法请求！"),

    /**
     * 数据保存失败
     */
    DATA_SAVE_ERROR(5008, "数据保存失败！"),

    ;

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


    @Override
    public String toString() {
        return "{\"code\": \"" + this.code + "\", \"msg\": \"" + this.msg + "\"}";
    }

}
