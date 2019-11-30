package com.springboot.admin.demo.business;

import com.alibaba.fastjson.JSON;
import com.springboot.admin.demo.entity.OpenAccountRequest;
import com.springboot.admin.demo.service.TServiceBase;

public class OpenAccountBusiness extends TServiceBase<OpenAccountRequest> {

    @Override
    protected void process(OpenAccountRequest request) {
        System.out.println("====openaccount process");
        System.out.println(JSON.toJSONString(request));
    }
}
