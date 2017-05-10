package com.dkd.emms.web.purchase.delivery.queryCondition;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 供应商发货查询条件
 * Created by wangqian on 2017/2/24.
 */
public class DeliveryCondition {
    /**
     * 发货单号
     */
    private String deliveryNo;
    /**
     * 组织机构id
     */
    private String orgId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 发货单状态
     */
    private String deliveryState;
    /**
     * 创建人
     */
    private String createUserName;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;


    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}


