package com.springboot.product.controller;

import com.springboot.api.facade.ProductApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController implements ProductApi {

    @Value("${server.port}")
    String port;

    @Override
    @RequestMapping(path = "/invoke_product/{name}", method = RequestMethod.GET)
    public String invokeProdHello(@PathVariable String name) {
//        throw new BusinessException("hahaha", "我错了");
        return "hi " + name + " ,i am from product port:" + port;
    }
}
