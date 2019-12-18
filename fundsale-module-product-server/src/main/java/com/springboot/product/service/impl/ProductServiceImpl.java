package com.springboot.product.service.impl;

import com.google.common.collect.Lists;
import com.springboot.api.entity.Product;
import com.springboot.product.service.IProductService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "productService"//服务名
        , targetNamespace = "http://service.product.springboot.com/" //报名倒叙，并且和接口定义保持一致
        , endpointInterface = "com.springboot.product.service.IProductService")//包名
@Component
public class ProductServiceImpl implements IProductService {

    @Override
    public List<Product> getProductList() {
        List<Product> products = Lists.newArrayList();
        Product product = new Product();
        product.setProdId(123);
        product.setProdName("Prod-123");
        products.add(product);
        return products;
    }
}
