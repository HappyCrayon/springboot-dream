package com.springboot.trade.business;

import com.alibaba.fastjson.JSON;
import com.springboot.trade.entity.SignRequest;


public class CustSignBusiness extends TServiceBase<SignRequest> {

    @Override
    protected void process(SignRequest request) {
        System.out.println("====custsign process");
        System.out.println(JSON.toJSONString(request));
    }
}
