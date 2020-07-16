package com.springboot.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.api.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    @Select("select * from product where prod_id=#{id}")
    Product getProdById(Integer id);

    @Delete("delete from product where prod_id=#{id}")
    int deleteProdById(Integer id);

//    @Options(useGeneratedKeys = true, keyProperty = "prodId")
//    @Insert("insert into product(prod_name) values(#{prodName})")
//    int insertProd(Product product);

    @Select("select * from product")
    List<Product> selectAllProds();

    int insertProd(@Param("prod") Product product);

    int insertProdFor(@Param("prodList") List<Product> prodList);
}
