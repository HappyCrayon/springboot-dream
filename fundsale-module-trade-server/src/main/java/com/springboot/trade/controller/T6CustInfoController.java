package com.springboot.trade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.trade.entity.T6CustInfo;
import com.springboot.trade.mapper.T6CustInfoMapper;
import com.springboot.trade.service.T6CustInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private T6CustInfoMapper custInfoMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;;


    @PostMapping("/queryCustList")
    @ApiOperation(value = "查询客户列表", notes = "查询客户列表")
    public List<T6CustInfo> queryCustList(@RequestBody JSONObject request) {
        log.info(request.toJSONString());
        return custInfoService.list();
    }

    @PostMapping("/test")
    @ApiOperation(value = "test", notes = "test")
    public String test(@RequestBody JSONObject request) {
        List<Map> resultList = sqlSessionTemplate.selectList("com.springboot.trade.mapper.T6CustInfoMapper.selectEntity123", request.getInnerMap());
        return JSON.toJSONString(resultList);
    }

    @PostMapping("/testInsert")
    @ApiOperation(value = "testInsert", notes = "testInsert")
    public String testInsert(@RequestBody T6CustInfo t6CustInfo) {
        int result = sqlSessionTemplate.insert("com.springboot.trade.mapper.T6CustInfoMapper.insertEntity", t6CustInfo);
        return "yes";
    }
}

