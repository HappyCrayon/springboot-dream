package com.springboot.admin.controller;

import com.springboot.admin.mapper.ProductMapper;
import com.springboot.api.entity.Product;
import com.springboot.common.datasource.DBTypeEnum;
import com.springboot.common.datasource.DbContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prod")
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable("id") Integer id) {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        return productMapper.getProdById(id);
    }

    @GetMapping("/insert")
    public Product insertDepartment(Product product) {
        DbContextHolder.setDbType(DBTypeEnum.db2);
        productMapper.insertProd(product);
        return product;
    }
}
