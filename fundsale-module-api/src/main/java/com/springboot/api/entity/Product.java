package com.springboot.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-04-15
 */
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "prod_id", type = IdType.AUTO)
    private Integer prodId;

    private String prodName;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public String toString() {
        return "Product{" +
        "prodId=" + prodId +
        ", prodName=" + prodName +
        "}";
    }
}
