package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/3/16.
 */
public class DemandPlan extends BaseEntity {
    private String demandId;
    private String demandCode;
    private String demandSource;
    private String dataSource;//数据来源
    private String demandState;  //状态
    private Boolean isChange;
    private String demandOrgId;//施工单位
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime; //创建日期
    private String demandDirection;
    private String wbsId;
    private String wbsCode;
    private String wbsName;
    private String reason;//当数据来源选择其他时，说明
    private String changeReason;
    private List<DemandDetail> detailList;
    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDemandState() {
        return demandState;
    }

    public void setDemandState(String demandState) {
        this.demandState = demandState;
    }

    public Boolean getIsChange() {
        return isChange;
    }

    public void setIsChange(Boolean isChange) {
        this.isChange = isChange;
    }

    public String getDemandOrgId() {
        return demandOrgId;
    }

    public void setDemandOrgId(String demandOrgId) {
        this.demandOrgId = demandOrgId;
    }

    @Override
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDemandDirection() {
        return demandDirection;
    }

    public void setDemandDirection(String demandDirection) {
        this.demandDirection = demandDirection;
    }

    public String getWbsCode() {
        return wbsCode;
    }

    public void setWbsCode(String wbsCode) {
        this.wbsCode = wbsCode;
    }

    public String getWbsName() {
        return wbsName;
    }

    public void setWbsName(String wbsName) {
        this.wbsName = wbsName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<DemandDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DemandDetail> detailList) {
        this.detailList = detailList;
    }

    public String getWbsId() {
        return wbsId;
    }

    public void setWbsId(String wbsId) {
        this.wbsId = wbsId;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }
}
