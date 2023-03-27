package com.alya.common.vo;

import com.alya.common.enums.ErrorType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author alya
 */
public class AlyaResult<T> {

    private Integer errcode;

    private String errmsg;

    private T body;

    private String time;

    public AlyaResult() {
        this.errcode = ErrorType.SUCCESS.getCode();
        this.errmsg = ErrorType.SUCCESS.getMsg();
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.body = null;
    }

    public AlyaResult(T body) {
        this();
        this.body = body;
    }

    public AlyaResult(ErrorType errorType, T body) {
        this.errcode = errorType.getCode();
        this.errmsg = body instanceof String ? errorType.getMsg() + body : errorType.getMsg();
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.body = body;
    }

    public static <T> AlyaResult<T> success() {
        return new AlyaResult<>();
    }

    public static <T> AlyaResult<T> success(T body) {
        return new AlyaResult<>(body);
    }

    public static <T> AlyaResult<T> fail(ErrorType errorType, T body) {
        return new AlyaResult<>(errorType, body);
    }

    public boolean isSuccess() {
        return ErrorType.SUCCESS.getCode().equals(this.errcode);
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
        AlyaResult<?> alyaResult = (AlyaResult<?>) o;
        return Objects.equals(errcode, alyaResult.errcode) && Objects.equals(errmsg, alyaResult.errmsg) && Objects.equals(body, alyaResult.body) && Objects.equals(time, alyaResult.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errcode, errmsg, body, time);
    }

    @Override
    public String toString() {
        return "{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", body=" + body +
                ", time='" + time + '\'' +
                '}';
    }
}
