package com.springboot.trade.entity;

/**
 * 交易实体基础类
 */
public class TRequest {

    /**
     * 流水号
     */
    private String serno;

    /**
     * 渠道标识
     */
    private String channelFlag;

    public String getSerno() {
        return serno;
    }

    public void setSerno(String serno) {
        this.serno = serno;
    }

    public String getChannelFlag() {
        return channelFlag;
    }

    public void setChannelFlag(String channelFlag) {
        this.channelFlag = channelFlag;
    }

    @Override
    public String toString() {
        return "TRequest{" +
                "serno='" + serno + '\'' +
                ", channelFlag='" + channelFlag + '\'' +
                '}';
    }
}
