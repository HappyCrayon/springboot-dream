package com.springboot.common.exceptions;

/**
 * 外部接口异常
 */
public class ApiException extends Exception {

    /**
     * 错误码
     */
    private String code;

    private String message;

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
