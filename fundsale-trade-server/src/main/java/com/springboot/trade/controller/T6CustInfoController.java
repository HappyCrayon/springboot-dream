package com.springboot.trade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.springboot.common.util.GenericsUtils;
import com.springboot.common.util.Objects;
import com.springboot.common.util.SQLUtil;
import com.springboot.trade.entity.T6CustInfo;
import com.springboot.trade.mapper.T6CustInfoMapper;
import com.springboot.trade.service.T6CustInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private SQLUtil sqlUtil;


    @PostMapping("/queryCustList1")
    @ApiOperation(value = "查询客户列表1", notes = "查询客户列表1")
    public List<T6CustInfo> queryCustList1(@RequestBody JSONObject request) {
        Class clazz = GenericsUtils.getSuperClassGenricType(Objects.getTargetClass(custInfoService));
        System.out.println(clazz.getName());
        return custInfoService.list();
    }

    @PostMapping("/queryCustList2")
    @ApiOperation(value = "查询客户列表2", notes = "查询客户列表2")
    public List<T6CustInfo> queryCustList2(@RequestBody JSONObject request) {
        return custInfoMapper.selectCustInfo();
    }

    @PostMapping("/comnSelect")
    @ApiOperation(value = "comnSelect", notes = "comnSelect")
    public String comnSelect(@RequestBody JSONObject request) {
        String sql = "SELECT CUST_NO1, cust_name, ID_TYPE, ID_CODE, CREATE_DATE, CREATE_TIME FROM t6_cust_info";
        try {
            Map sParams = Maps.newHashMap();
            sParams.put("sql_content", "select 100 from dual where 1 <> 1");
            sqlSessionTemplate.selectOne("com.springboot.common.mapper.ComnBaseMapper.comnSelect111", sParams);
            List<Map> resultList = sqlUtil.selectList(sql, request);
            return JSON.toJSONString(resultList);
        } catch (DataAccessException e) {
            log.error("数据库异常", e);
            return null;
        }
    }

    @PostMapping("/selectEntity")
    @ApiOperation(value = "selectEntity", notes = "selectEntity")
    public String selectEntity(@RequestBody JSONObject request) {
        String statementId = "com.springboot.trade.mapper.T6CustInfoMapper.selectEntity";
        List<Map> resultList = sqlSessionTemplate.selectList(statementId, request.getInnerMap());
        return JSON.toJSONString(resultList);
    }

    @PostMapping("/insertEntity")
    @ApiOperation(value = "insertEntity", notes = "insertEntity")
    public String insertEntity(@RequestBody T6CustInfo t6CustInfo) {
        String statementId = "com.springboot.trade.mapper.T6CustInfoMapper.insertEntity";
        int result = sqlSessionTemplate.insert(statementId, t6CustInfo);
        return "yes";
    }

    @PostMapping("/deleteCustInfo")
    @ApiOperation(value = "deleteCustInfo", notes = "deleteCustInfo")
    public int deleteCustInfo(@RequestBody JSONObject request) {
        return custInfoMapper.deleteCustInfo("1");
    }

}

