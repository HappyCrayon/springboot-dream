package com.springboot.trade.business;

import com.alibaba.fastjson.JSON;
import com.springboot.trade.entity.OpenAccountRequest;


public class OpenAccountBusiness extends TServiceBase<OpenAccountRequest> {

    @Override
    protected void process(OpenAccountRequest request) {
        System.out.println("====openaccount process");
        System.out.println(JSON.toJSONString(request));
    }
}
