package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.dkd.emms.core.entity.Tree;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/2/24.
 */
public class Order extends BaseEntity {
    private String orderId;
    private String orderCode;
    private String orderContractNo;//采购合同号
    private String orderState;  //状态
    private String orderWay;   //生成方式
    private String dataToSources;//数据来源
    private String supplierId; //供应商
    private String supplierName; //供应商
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate; //交货日期
    private String orderOpinion;//审核不通过意见
    private List<OrderDetail> orderDetailList;
    private List<Project> projectList;
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDataToSources() {
        return dataToSources;
    }

    public void setDataToSources(String dataToSources) {
        this.dataToSources = dataToSources;
    }

    public String getOrderOpinion() {
        return orderOpinion;
    }

    public void setOrderOpinion(String orderOpinion) {
        this.orderOpinion = orderOpinion;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

}
