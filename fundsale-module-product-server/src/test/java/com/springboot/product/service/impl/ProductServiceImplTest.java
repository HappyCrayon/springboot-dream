package com.springboot.product.service.impl;

import com.springboot.api.entity.Product;
import com.springboot.product.service.IProductService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ProductServiceImplTest {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImplTest.class);

    @Test
    public void testGetProductList() {
        String address = "http://localhost:9300/services/productService?wsdl";
        //代理工厂
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        //设置代理地址
        factoryBean.setAddress(address);
        //设置接口类型
        factoryBean.setServiceClass(IProductService.class);
        //创建一个代理接口实现
        IProductService productService = (IProductService) factoryBean.create();
        List<Product> productList = productService.getProductList();
        for (Product product : productList) {
            System.out.println(product);
        }
        Assert.assertTrue(true);
    }
}