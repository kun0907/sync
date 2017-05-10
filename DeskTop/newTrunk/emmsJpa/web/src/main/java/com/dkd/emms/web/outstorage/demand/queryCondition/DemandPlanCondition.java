package com.dkd.emms.web.outstorage.demand.queryCondition;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YUZH on 2017/3/16.
 */
public class DemandPlanCondition {
    private String demandCode;
    private String demandSource;
    private String demandState;  //状态
    private String demandOrgId;//施工单位
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createStartTime; //创建日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createEndTime; //创建日期
    private String createUserName;
    private String wbsName;

    public String getDemandCode() {
        return demandCode;
    }

    public void setDemandCode(String demandCode) {
        this.demandCode = demandCode;
    }

    public String getDemandSource() {
        return demandSource;
    }

    public void setDemandSource(String demandSource) {
        this.demandSource = demandSource;
    }

    public String getDemandState() {
        return demandState;
    }

    public void setDemandState(String demandState) {
        this.demandState = demandState;
    }

    public String getDemandOrgId() {
        return demandOrgId;
    }

    public void setDemandOrgId(String demandOrgId) {
        this.demandOrgId = demandOrgId;
    }

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getWbsName() {
        return wbsName;
    }

    public void setWbsName(String wbsName) {
        this.wbsName = wbsName;
    }
}
