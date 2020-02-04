package com.springboot.trade.business;


import com.springboot.trade.entity.TRequest;
import com.springboot.trade.service.TransService;

public abstract class TServiceBase<REQ extends TRequest> implements TransService<REQ> {

    public String doProcess(REQ request) {
        process(request);
        return "成功";
    }

    protected abstract void process(REQ request);
}
