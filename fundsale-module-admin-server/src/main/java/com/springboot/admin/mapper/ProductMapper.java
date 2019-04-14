package com.springboot.admin.mapper;

import com.springboot.api.entity.Department;
import com.springboot.api.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface ProductMapper {

    @Select("select * from product where prod_id=#{id}")
    public Department getProdById(Integer id);

    @Delete("delete from product where prod_id=#{id}")
    public int deleteProdById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "prodId")
    @Insert("insert into product(prod_name) values(#{prodName})")
    public int insertProd(Product product);
}
