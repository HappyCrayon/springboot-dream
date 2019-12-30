package com.springboot.common.response;

import com.alibaba.fastjson.JSON;
import com.springboot.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 返回统一数据结构
 *
 * @author purgeyao
 * @since 1.0
 */
public class Result<T> implements Serializable {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;

    /**
     * 成功数据
     */
    private T data;

    private Result() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static <T> Result<T> success() {
        return build(ResultCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return build(ResultCode.SUCCESS, data);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result error(ResultCode resultCode) {
        return build(resultCode, null);
    }

    private static <T> Result<T> build(ResultCode resultCode, T data) {
        Result<T> result = new Result<T>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData(data);
        return result;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
