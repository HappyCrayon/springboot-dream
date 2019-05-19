package com.springboot.api.facade.comn;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "product-service", url = "http://localhost:9300")
public interface ComnServiceApi {

    @PostMapping(path = "/perform")
    String perform(@RequestBody JSONObject request);
}
