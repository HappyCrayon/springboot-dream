package com.springboot.admin.controller;

import com.springboot.api.entity.Department;
import com.springboot.admin.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/get/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/insert")
    public Department insertDepartment(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }
}
