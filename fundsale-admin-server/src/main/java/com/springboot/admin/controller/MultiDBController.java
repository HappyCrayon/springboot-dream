package com.springboot.admin.controller;

import com.google.common.collect.Maps;
import com.springboot.admin.mapper.DepartmentMapper;
import com.springboot.admin.mapper.ProductMapper;
import com.springboot.api.entity.Department;
import com.springboot.api.entity.Product;
import com.springboot.common.datasource.DBTypeEnum;
import com.springboot.common.datasource.DbContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/multidb")
@Api(description = "多数据源测试")
public class MultiDBController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    ProductMapper productMapper;

    @ApiOperation(value = "查询部门和产品", notes = "查询部门和产品")
    @RequestMapping(path="/queryDeptAndProd", method = RequestMethod.GET)
    public Map<String, Object> queryDeptAndProd() {
        Map<String, Object> resultMap = Maps.newHashMap();

        DbContextHolder.setDbType(DBTypeEnum.db1);
        List<Department> departments = departmentMapper.selectAllDepts();
        resultMap.put("departments", departments);

        DbContextHolder.setDbType(DBTypeEnum.db2);
        List<Product> products = productMapper.selectAllProds();
        resultMap.put("products", products);


        return resultMap;
    }

    @ApiOperation(value = "使用MybatisPlus查询部门", notes = "使用MybatisPlus查询部门")
    @RequestMapping(path="/queryDeptByMybatisPlus", method = RequestMethod.GET)
    public List<Department> testMyBatisPlus() {
        return departmentMapper.selectList(null);
    }

}
