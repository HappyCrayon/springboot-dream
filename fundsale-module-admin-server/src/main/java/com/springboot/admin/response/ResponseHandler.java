package com.springboot.admin.response;

import com.alibaba.fastjson.JSON;
import com.springboot.common.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//basePackages如果不加的话，它可是对整个系统的Controller做了扩展功能，它会对某些特殊功能产生冲突，例如 不加的话，在使用swagger时会出现空白页异常。
@ControllerAdvice(basePackages = "com.springboot.admin.controller")
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     * treu=支持，false=不支持
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     *
     * 处理response的具体业务方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //Controller的返回值为String的时候，它是直接返回String,不是json，故我们要手工做下json转换处理
        if (body instanceof String) {
            return JSON.toJSONString(Result.success(body));
        }
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
}
