package com.springboot.admin.demo.entity;

/**
 * 签约参数Entity
 */
public class SignRequest extends TRequest{

    /**
     * 证件类型
     */
    private String certificateType;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 银行卡号
     */
    private String accountNo;

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "SignRequest{" +
                "certificateType='" + certificateType + '\'' +
                ", certificateNo='" + certificateNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                "} " + super.toString();
    }
}
