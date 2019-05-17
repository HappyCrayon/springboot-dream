package com.springboot.trade.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.trade.component.SpringContextUtils;
import com.springboot.trade.entity.T6CustInfo;
import com.springboot.trade.mapper.T6CustInfoMapper;
import com.springboot.trade.service.T6CustInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-05-05
 */
@RestController
@Api(description = "客户信息Controller")
@RequestMapping("/trade/t6-cust-info")
public class T6CustInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private T6CustInfoService custInfoService;

    @PostMapping("/queryCustList")
    @ApiOperation(value = "查询客户列表", notes = "查询客户列表")
    public List<T6CustInfo> queryCustList(@RequestBody JSONObject request) {
        log.info(request.toJSONString());
        return custInfoService.list();
    }

    @PostMapping("/testBeanUtil")
    @ApiOperation(value = "测试BeanUtil", notes = "测试BeanUtil")
    public List<T6CustInfo> testBeanUtil() {
        T6CustInfoMapper custInfoMapper = SpringContextUtils.getBean(T6CustInfoMapper.class);
        return custInfoMapper.selectList(null);
    }
}

