package com.springboot.admin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.api.dto.EmployeeDTO;
import com.springboot.api.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-04-12
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee getEmployeeById(Integer id);

    Page<Employee> selectEmployeePage(Page<Employee> page, EmployeeDTO employeeDTO);
}
