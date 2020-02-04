package com.springboot.api.facade.comn;

import com.alibaba.fastjson.JSONObject;
import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;


//@FeignClient(value = "product-service", url = "http://localhost:9300")
public interface ComnServiceApi {

//    @PostMapping(path = "/perform")
    @RequestLine("POST /perform")
    @Headers("Content-type: application/json")
    String perform(@RequestBody JSONObject request);
}
