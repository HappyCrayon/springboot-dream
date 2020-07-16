package com.springboot.admin.controller;

import com.google.common.collect.Lists;
import com.springboot.admin.mapper.ProductMapper;
import com.springboot.api.entity.Product;
import com.springboot.common.datasource.DBTypeEnum;
import com.springboot.common.datasource.DbContextHolder;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        return productMapper.getProdById(id);
    }

    @GetMapping("/insertProd")
    public Product insertProd(Product product) {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        productMapper.insertProd(product);
        return product;
    }

    @GetMapping("/insertProdBatch1")
    public boolean insertProdBatch1() {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        long start = System.currentTimeMillis();

        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        for (int i=6; i<20000; i++) {
            Product product = new Product();
            product.setProdId(i);
            product.setProdName(i+"");
            productMapper.insertProd(product);
        }
        sqlSession.commit();
        sqlSession.close();

        System.out.println("执行耗时: " + (System.currentTimeMillis() - start) + "ms");
        return true;
    }

    @GetMapping("/insertProdBatch2")
    public boolean insertProdBatch2() {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        long start = System.currentTimeMillis();

        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        for (int i=6; i<20000; i++) {
            Product product = new Product();
            product.setProdId(i);
            product.setProdName(i+"");
            productMapper.insertProd(product);
            if (i%1000 ==0 || i == 9999) {
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }
        sqlSession.close();

        System.out.println("执行耗时: " + (System.currentTimeMillis() - start) + "ms");
        return true;
    }

    @GetMapping("/insertProdBatch3")
    public boolean insertProdBatch3() {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        long start = System.currentTimeMillis();

        List<Product> prodList = Lists.newArrayList();
        for (int i=6; i<20000; i++) {
            Product product = new Product();
            product.setProdId(i);
            product.setProdName(i+"");
            prodList.add(product);
            if (i%1000 ==0 || i == 9999) {
                productMapper.insertProdFor(prodList);
                prodList.clear();
            }
        }

        System.out.println("执行耗时: " + (System.currentTimeMillis() - start) + "ms");
        return true;
    }


    @GetMapping("/insertProdBatch4")
    public boolean insertProdBatch4() {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        long start = System.currentTimeMillis();

        for (int i=6; i<20000; i++) {
            Product product = new Product();
            product.setProdId(i);
            product.setProdName(i+"");
            productMapper.insertProd(product);
        }

        System.out.println("执行耗时: " + (System.currentTimeMillis() - start) + "ms");
        return true;
    }
}
