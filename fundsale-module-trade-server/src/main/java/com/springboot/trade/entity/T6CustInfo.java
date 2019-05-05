package com.springboot.trade.entity;

import java.io.Serializable;

/**
 * <p>
 *  客户信息表
 * </p>
 *
 * @author HappyCrayon
 * @since 2019-05-05
 */
public class T6CustInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String custNo;

    private String custName;

    private String idType;

    private String idCode;

    private String createDate;

    private String createTime;


    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "T6CustInfo{" +
        "custNo=" + custNo +
        ", custName=" + custName +
        ", idType=" + idType +
        ", idCode=" + idCode +
        ", createDate=" + createDate +
        ", createTime=" + createTime +
        "}";
    }
}
