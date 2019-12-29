package com.springboot.common.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springboot.common.error.CommonErrorCode;
import org.apache.commons.lang.builder.ToStringBuilder;

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
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private static <T> Result<T> build(CommonErrorCode commonErrorCode, T data) {
        Result<T> result = new Result<T>();
        result.setCode(commonErrorCode.getCode());
        result.setMsg(commonErrorCode.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return build(CommonErrorCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return build(CommonErrorCode.SUCCESS, data);
    }

    public static Result fail(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result fail(CommonErrorCode commonErrorCode) {
        return build(commonErrorCode, null);
    }

    /**
     * 获取 json
     */
    public String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", this.code);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("msg", msg)
                .append("data", data)
                .toString();
    }
}
