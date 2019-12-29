package com.springboot.common.exception;


import com.springboot.common.error.BusinessErrorCode;
import com.springboot.common.error.CommonErrorCode;


/**
 * {@link RuntimeException} 通用业务异常
 *
 * @author purgeyao
 * @since 1.0
 */
public class BusinessException extends RuntimeException {

    private String code;

    /**
     * 使用枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 使用CommonErrorCode枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(CommonErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 使用自定义消息
     *
     * @param code 值
     * @param msg  详情
     */
    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
