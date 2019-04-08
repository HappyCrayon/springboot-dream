package com.springboot.product.controller;

import com.springboot.api.facade.ProductApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController implements ProductApi {

    @Override
    @RequestMapping(path = "/invoke_product/{name}", method = RequestMethod.GET)
    public String invokeProdHello(@PathVariable String name) {
        return "Product Sevice response: Hello " + name;
    }
}
