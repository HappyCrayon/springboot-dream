package com.springboot.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.api.dto.EmployeeDTO;
import com.springboot.api.entity.Employee;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-04-12
 */
public interface EmployeeService extends IService<Employee> {

    List<Employee> getEmployeeList();

    Employee getEmployeeById(Integer id);

    Page<Employee> selectEmployeePage(Page<Employee> page, EmployeeDTO employeeDTO);

}
