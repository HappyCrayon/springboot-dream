package com.springboot.admin.controller;


import com.springboot.api.entity.Employee;
import com.springboot.admin.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author DGD
 * @date 2018/2/10.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Transactional
    public void insertEmployee() {
        Employee employee = new Employee();
        employee.setLastName("lisi");
        employee.setEmail("lisi@qq.com");
        employee.setGender(1);
        employee.setdId(30);
        employeeService.save(employee);
        System.out.println(employee);
    }

    @Test
    public void listEmployee() {
        employeeService.getEmployeeList().stream().forEach(item -> System.out.println(item));
    }

    @Test
    public void searchEmployee(){
        Employee employee = employeeService.getEmployeeById(1);
        System.out.println(employee);
    }

}
