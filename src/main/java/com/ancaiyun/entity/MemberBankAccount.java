package com.ancaiyun.entity;

import java.io.Serializable;
import java.util.Date;

public class MemberBankAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    private String id;

    //供应商身份银行编号
    private String agentId;

    //核心企业银行编号
    private String tradeId;

    //创建时间
    private Date createTime;

    //删除标志位 0:正常 1：删除 2：待删除
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
