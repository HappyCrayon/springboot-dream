package com.springboot.admin.demo.facade;

import com.springboot.admin.demo.entity.TRequest;

/**
 * 交易接口
 * @param
 */
public interface TransService<REQ extends TRequest> {

    /**
     * 交易处理方法
     * @param request
     * @return
     */
     String doProcess(REQ request);
}
