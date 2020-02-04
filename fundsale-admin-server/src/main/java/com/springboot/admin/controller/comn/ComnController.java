package com.springboot.admin.controller.comn;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.springboot.api.facade.comn.ComnServiceApi;
import com.springboot.common.util.ResponseUtil;
import feign.Feign;
import feign.gson.GsonEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Api(description = "通用Controller")
public class ComnController {

//    @Autowired
//    private ComnServiceApi comnServiceApi;


    @ApiOperation(value = "通用服务", notes = "通用服务")
    @PostMapping(path="/callService")
    public String invokeProductHello(@RequestBody JSONObject request) {
        String serviceName = request.getString("serviceName");
        if (Strings.isNullOrEmpty(serviceName)) {
            return ResponseUtil.buildFailureRes("COMN-00001", "serviceName is empty!");
        }
        // url(server name) + Mapper + method
        ComnServiceApi comnServiceApi = Feign.builder().encoder(new GsonEncoder()).target(ComnServiceApi.class, "http://localhost:9400");
        return comnServiceApi.perform(request);
    }
}
