package com.springboot.admin.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.admin.service.EmployeeService;
import com.springboot.api.dto.EmployeeDTO;
import com.springboot.api.entity.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-04-12
 */
@RestController
@RequestMapping("/admin/employee")
@Api(description = "职员Controller")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/insertEmployee")
    @ApiOperation(value = "新增职员", notes = "新增职员")
    public Employee insertEmployee(@RequestBody @ApiParam(name="职员对象", value="JSON格式数据", required=true) Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @PostMapping("/queryEmployeePage")
    @ApiOperation(value = "分页查询职员", notes = "分页查询职员")
    public String queryEmployeePage(@RequestBody EmployeeDTO employeeDTO) {
        Page<Employee> page = new Page<Employee>(1,10);
        Page<Employee> employeePage = employeeService.selectEmployeePage(page, employeeDTO);
        return JSON.toJSONString(employeePage, true);
    }

}
