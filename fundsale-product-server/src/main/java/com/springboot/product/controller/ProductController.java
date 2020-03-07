package com.springboot.product.controller;

import com.springboot.api.entity.Product;
import com.springboot.api.facade.ProductApi;
import com.springboot.common.exceptions.InternalApiException;
import com.springboot.common.response.Result;
import com.springboot.common.util.MessageUtils;
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
    public String invokeProdHello(@PathVariable String name) throws InternalApiException {
        System.out.println("==========");
        System.out.println(MessageUtils.getMessage("user.welcome"));
//        throw new BusinessException("hahaha", "我错了");
        throw new InternalApiException("hahaha", "我错了");
//        return "hi " + name + " ,i am from product port:" + port;
    }

    @Override
    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public Result test() {
        Product product = new Product();
        product.setProdId(1);
        product.setProdName("xxx");
        System.out.println("1111");
        return Result.success(product);
    }
}
