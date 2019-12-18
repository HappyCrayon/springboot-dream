package com.springboot.product.service;

import com.springboot.api.entity.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;


@WebService(targetNamespace = "http://service.product.springboot.com/") //命名一般是接口类的包名倒序
public interface IProductService {

    @WebMethod //声明暴露服务的方法，可以不写
    List<Product> getProductList();
}
