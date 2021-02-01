package com.dxc.opentalk.springboottest.controller;

import java.io.Serializable;

/**
 * @author dingchenchen
 * @since 2020/12/29
 */
public class Result<T> implements Serializable {
    private boolean success = false;
    private T data = null;
    private String msg = "";
    private String code = "500";

    public Result() {
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<T>();
        r.setData(data);
        r.setSuccess(true);
        r.setCode("200");
        r.setMsg("success");
        return r;
    }

    public static Result success() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode("200");
        r.setMsg("success");
        return r;
    }

    public static <T> Result<T> fail(String code, String msg) {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static <T> Result<T> fail( String msg) {
        return fail("500",msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public String getCode() {
        return code;
    }
    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }
}
