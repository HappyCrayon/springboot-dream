package com.springboot.admin.controller;

import com.springboot.admin.mapper.DepartmentMapper;
import com.springboot.admin.mapper.ProductMapper;
import com.springboot.common.datasource.DBTypeEnum;
import com.springboot.common.datasource.DbContextHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MultiDBTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    @Transactional
    public void testMultiDB() {
        //TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            DbContextHolder.setDbType(DBTypeEnum.db1);
            departmentMapper.selectList(null).stream().forEach(item -> System.out.println(item));

//            DbContextHolder.setDbType(DBTypeEnum.db2);
//            productMapper.selectList(null).stream().forEach(item -> System.out.println(item));

            //transactionManager.commit(status);
        } catch (Exception e) {
            //transactionManager.rollback(status);
            throw e;
        }

    }
}
