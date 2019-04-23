package com.springboot.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.api.dto.EmployeeDTO;
import com.springboot.api.entity.Employee;
import com.springboot.admin.mapper.EmployeeMapper;
import com.springboot.admin.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-04-12
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployeeList() {
        return list(null);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public Page<Employee> selectEmployeePage(Page<Employee> page, EmployeeDTO employeeDTO) {
        return employeeMapper.selectEmployeePage(page, employeeDTO);
    }
}
