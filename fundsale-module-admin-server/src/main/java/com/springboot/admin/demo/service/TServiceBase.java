package com.springboot.admin.demo.service;

import com.springboot.admin.demo.entity.TRequest;
import com.springboot.admin.demo.facade.TransService;

public abstract class TServiceBase<REQ extends TRequest> implements TransService<REQ> {

    public String doProcess(REQ request) {
        process(request);
        return "成功";
    }

    protected abstract void process(REQ request);
}
