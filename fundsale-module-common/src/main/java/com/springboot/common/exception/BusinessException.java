package com.springboot.common.exception;


import com.springboot.common.error.BusinessErrorEnum;
import com.springboot.common.error.CommonErrorEnum;


/**
 * {@link RuntimeException} 通用业务异常
 *
 * @author purgeyao
 * @since 1.0
 */
public class BusinessException extends RuntimeException {

    private String code;

    /**
     * 使用BusinessError枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(BusinessErrorEnum errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    /**
     * 使用CommonError枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(CommonErrorEnum errorCode) {
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
