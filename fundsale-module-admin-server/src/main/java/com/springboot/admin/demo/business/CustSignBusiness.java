package com.springboot.admin.demo.business;

import com.alibaba.fastjson.JSON;
import com.springboot.admin.demo.entity.SignRequest;
import com.springboot.admin.demo.service.TServiceBase;

public class CustSignBusiness extends TServiceBase<SignRequest> {

    @Override
    protected void process(SignRequest request) {
        System.out.println("====custsign process");
        System.out.println(JSON.toJSONString(request));
    }
}
