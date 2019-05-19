package com.springboot.trade.controller.comn;

import com.alibaba.fastjson.JSONObject;
import com.springboot.api.facade.comn.ComnServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/product")
public class ComnController implements ComnServiceApi {

    private Logger logger = LoggerFactory.getLogger(ComnController.class);

    @Override
    @PostMapping(path = "/perform")
    public String perform(@RequestBody JSONObject request) {
        logger.info(request.toJSONString());
        return "yes";
    }
}
