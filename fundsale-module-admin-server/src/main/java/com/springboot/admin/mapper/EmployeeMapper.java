package com.springboot.admin.mapper;

import com.springboot.admin.entity.Employee;
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

}
