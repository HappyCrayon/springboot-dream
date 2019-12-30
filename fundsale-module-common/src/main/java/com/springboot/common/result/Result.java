package com.springboot.common.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springboot.common.error.CommonErrorEnum;
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

    private static <T> Result<T> build(CommonErrorEnum commonErrorEnum, T data) {
        Result<T> result = new Result<T>();
        result.setCode(commonErrorEnum.getCode());
        result.setMsg(commonErrorEnum.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success() {
        return build(CommonErrorEnum.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return build(CommonErrorEnum.SUCCESS, data);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(CommonErrorEnum commonErrorEnum) {
        return build(commonErrorEnum, null);
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
