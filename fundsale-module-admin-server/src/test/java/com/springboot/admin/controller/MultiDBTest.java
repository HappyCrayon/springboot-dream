package com.springboot.admin.controller;

import com.springboot.admin.service.DepartmentService;
import com.springboot.admin.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MultiDBTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testMultiDB() {
        //DbContextHolder.setDbType(DBTypeEnum.db2);

        employeeService.getEmployeeList().stream().forEach(item -> System.out.println(item));

        departmentService.list(null).stream().forEach(item -> System.out.println(item));
    }
}
