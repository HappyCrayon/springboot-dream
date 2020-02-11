package com.springboot.admin.controller.comn;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.springboot.common.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 通用请求处理，统一入口，向具体逻辑实现的服务转发
 */
@RestController
@Api(description = "适配层透传控制器")
public class ComnAdapterController {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "通用功能调用", notes = "通用功能调用")
    @PostMapping(path="/callFunc")
    public Result invokeProductHello(@RequestBody JSONObject params) {
        String funcName = params.getString("funcName");
        if (Strings.isNullOrEmpty(funcName)) {
            return Result.fail("COMN-00001", "funcName is empty!");
        }

        String funcUrl = params.getString("funcUrl");
        HttpHeaders headers = new HttpHeaders();
        //设置接收响应的处理application/json，否则默认可能是application/xml导致数据解析不正确
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<Result> responseEntity = restTemplate.exchange(funcUrl, HttpMethod.POST, requestEntity, Result.class);
        return responseEntity.getBody();
    }
}
