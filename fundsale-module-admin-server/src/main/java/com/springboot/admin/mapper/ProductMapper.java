package com.springboot.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.api.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select * from product where prod_id=#{id}")
    public Product getProdById(Integer id);

    @Delete("delete from product where prod_id=#{id}")
    public int deleteProdById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "prodId")
    @Insert("insert into product(prod_name) values(#{prodName})")
    public int insertProd(Product product);
}
