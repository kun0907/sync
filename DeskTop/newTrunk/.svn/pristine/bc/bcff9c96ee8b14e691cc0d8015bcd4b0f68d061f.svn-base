package com.dkd.emms.web.purchase.order.queryCondition;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YUZH on 2017/2/24.
 */
public class OrderCondition {
    private String orderCode;
    private String orderContractNo;//采购合同号
    private String supplierName;//供应商名称
    private String orderWay;   //生成方式
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryStartDate; //交货日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryEndDate; //交货日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createStartTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createEndTime ;
    private String createUserName;
    private String orderState;  //状态

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderContractNo() {
        return orderContractNo;
    }

    public void setOrderContractNo(String orderContractNo) {
        this.orderContractNo = orderContractNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    public Date getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(Date deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public Date getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(Date deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
