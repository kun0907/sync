package com.dkd.emms.web.outstorage.picknotice.queryCondition;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 领料通知查询条件
 * Created by wangqian on 2017/2/24.
 */
public class PickNoticeCondition {
    /**
     * 领料通知编号
     */
    private String pickNoticeNo;
    /**
     * 施工单位
     */
    private String supplierId;
    /**
     * 领料通知单状态
     */
    private String pickNoticeState;
    /**
     * 录入人
     */
    private String createUserName;
    /**
     * 录入开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    /**
     * 录入结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /**
     * wbsId
     */
    private String wbsId;

    /**
     * 物资编码
     */
    private String materialsCode;


    /**
     * 物资描述
     */
    private String materialsDescribe;


    public String getPickNoticeNo() {
        return pickNoticeNo;
    }

    public void setPickNoticeNo(String pickNoticeNo) {
        this.pickNoticeNo = pickNoticeNo;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPickNoticeState() {
        return pickNoticeState;
    }

    public void setPickNoticeState(String pickNoticeState) {
        this.pickNoticeState = pickNoticeState;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWbsId() {
        return wbsId;
    }

    public void setWbsId(String wbsId) {
        this.wbsId = wbsId;
    }

    public String getMaterialsCode() {
        return materialsCode;
    }

    public void setMaterialsCode(String materialsCode) {
        this.materialsCode = materialsCode;
    }

    public String getMaterialsDescribe() {
        return materialsDescribe;
    }

    public void setMaterialsDescribe(String materialsDescribe) {
        this.materialsDescribe = materialsDescribe;
    }
}


