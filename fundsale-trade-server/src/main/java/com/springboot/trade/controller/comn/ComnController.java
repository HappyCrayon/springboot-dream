package com.springboot.trade.controller.comn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.api.facade.comn.ComnServiceApi;
import com.springboot.common.util.SpringUtil;
import com.springboot.trade.mapper.T6CustInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


@RestController
//@RequestMapping("/product")
public class ComnController implements ComnServiceApi {

    private Logger logger = LoggerFactory.getLogger(ComnController.class);

    @Override
    @PostMapping(path = "/perform")
    public String perform(@RequestBody JSONObject request) {
        logger.info(request.toJSONString());
        T6CustInfoMapper custInfoMapper = SpringUtil.getBean(T6CustInfoMapper.class);
        try {
            Method method = custInfoMapper.getClass().getMethod("selectCustInfo");
            return JSON.toJSONString(method.invoke(custInfoMapper));
        } catch (Exception e) {
            logger.error("错误", e);
        }
        return "yes";
    }
}
