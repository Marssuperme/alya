package com.alya.common.vo;

import com.alya.common.enums.ErrorType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author alya
 */
public class AlyaError {

    private Integer errcode;

    private String errmsg;

    private String time;

    public AlyaError() {
        this.errcode = ErrorType.SUCCESS.getCode();
        this.errmsg = ErrorType.SUCCESS.getMsg();
        this.time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public AlyaError(String errmsg) {
        this();
        this.errmsg = this.errmsg + errmsg;
    }

    public AlyaError(ErrorType errorType, String errmsg) {
        this();
        this.errcode = errorType.getCode();
        this.errmsg = errorType.getMsg();
        this.errmsg = this.errmsg + errmsg;
    }

    public static AlyaError fail() {
        return new AlyaError();
    }

    public static AlyaError fail(String errmsg) {
        return new AlyaError(errmsg);
    }

    public static AlyaError fail(ErrorType errorType, String errmsg) {
        return new AlyaError(errorType, errmsg);
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
        AlyaError alyaResult = (AlyaError) o;
        return Objects.equals(errcode, alyaResult.errcode) && Objects.equals(errmsg, alyaResult.errmsg) && Objects.equals(time, alyaResult.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errcode, errmsg, time);
    }

    @Override
    public String toString() {
        return "{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
