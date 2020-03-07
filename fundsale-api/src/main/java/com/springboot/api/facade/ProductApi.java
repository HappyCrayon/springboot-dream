package com.springboot.api.facade;


import com.springboot.common.exceptions.InternalApiException;
import com.springboot.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/product")
@FeignClient("fundsale-product-server")
public interface ProductApi {

    @RequestMapping(path = "/invoke_product/{name}", method = RequestMethod.GET)
    String invokeProdHello(@PathVariable String name) throws InternalApiException;

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    Result test();
}
