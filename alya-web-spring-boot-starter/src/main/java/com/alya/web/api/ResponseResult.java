package com.alya.web.api;

import com.alya.web.exception.enums.ErrorType;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author alya
 */
public class ResponseResult<T> {

    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MESG = "success";

    private Integer code;

    private String mesg;

    private Instant time;

    private T body;

    public ResponseResult() {
        this.code = SUCCESS_CODE;
        this.mesg = SUCCESS_MESG;
        this.time = ZonedDateTime.now().toInstant();
    }

    public ResponseResult(ErrorType errorType) {
        this.code = errorType.getCode();
        this.mesg = errorType.getMsg();
        this.time = ZonedDateTime.now().toInstant();
    }

    public ResponseResult(T body) {
        this();
        this.body = body;
    }

    public ResponseResult(ErrorType errorType, T body) {
        this(errorType);
        this.body = body;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> success(T body) {
        return new ResponseResult<>(body);
    }

    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(ErrorType.SYSTEM_ERROR);
    }

    public static <T> ResponseResult<T> fail(T body) {
        return new ResponseResult<>(ErrorType.SYSTEM_ERROR, body);
    }

    public static <T> ResponseResult<T> fail(ErrorType errorType, T body) {
        return new ResponseResult<>(errorType, body);
    }

    public boolean isSuccess() {
        return Objects.equals(SUCCESS_CODE, this.code);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseResult<?> that = (ResponseResult<?>) o;
        return Objects.equals(code, that.code) && Objects.equals(mesg, that.mesg) && Objects.equals(time, that.time) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, mesg, time, body);
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", mesg='" + mesg + '\'' +
                ", time=" + time +
                ", body=" + body +
                '}';
    }
}
