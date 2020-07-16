package com.springboot.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.admin.mapper.ProductMapper;
import com.springboot.admin.service.ProductService;
import com.springboot.api.entity.Product;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-04-15
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


}
