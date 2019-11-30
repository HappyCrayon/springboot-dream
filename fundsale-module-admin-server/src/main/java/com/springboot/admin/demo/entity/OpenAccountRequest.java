package com.springboot.admin.demo.entity;

/**
 * 开户参数Entity
 */
public class OpenAccountRequest extends TRequest{

    /**
     * 交易账号
     */
    private String transactionAccountNo;

    /**
     * TA代码
     */
    private String tano;

    public String getTransactionAccountNo() {
        return transactionAccountNo;
    }

    public void setTransactionAccountNo(String transactionAccountNo) {
        this.transactionAccountNo = transactionAccountNo;
    }

    public String getTano() {
        return tano;
    }

    public void setTano(String tano) {
        this.tano = tano;
    }

    @Override
    public String toString() {
        return "OpenAccountRequest{" +
                "transactionAccountNo='" + transactionAccountNo + '\'' +
                ", tano='" + tano + '\'' +
                "} " + super.toString();
    }
}
