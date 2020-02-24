package com.springboot.common.exceptions;

/**
 * 内部接口异常
 */
public class InternalApiException extends Exception {

    /**
     * 错误码
     */
    private String code;

    private String message;

    public InternalApiException(String code, String message) {
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
