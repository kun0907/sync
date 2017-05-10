package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class InWarehouse extends BaseEntity {
    /**
     *
     * 入库单ID（系统生成）
     *
     *
     */
    private String inWarehouseId;
    /**
     *
     * 入库单编号
     *
     *
     */
    private String inWarehouseCode;
    /**
     *
     * 供应商ID
     *
     *
     */
    private String supplierId;
    /**
     *
     * 供应商名称
     *
     *
     */
    private String supplierName;
    /**
     *
     * 入库状态
     *
     *
     */
    private String inWarehouseState;
    /**
     *
     * 采购订单ID
     *
     *
     */
    private String orderId;
    /**
     *
     * 采购订单编号
     *
     *
     */
    private String orderCode;
    /**
     *
     *创建时间
     *
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     *
     *入库时间
     *
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inWarehouseTime;
    /**
     *
     *创建人ID
     *
     *
     */
    private String createUserId;
    /**
     *
     *创建人姓名
     *
     *
     */
    private String createUserName;
    /**
     *
     *
     *质检明细列表
     *
     */
    private List<InWarehouseDetail> inWarehouseDetailList;
    /**
     *
     *入库人员
     *
     *
     *
     */
    private String inWorker;



    public String getInWarehouseId() {
        return inWarehouseId;
    }

    public void setInWarehouseId(String inWarehouseId) {
        this.inWarehouseId = inWarehouseId;
    }

    public String getInWarehouseCode() {
        return inWarehouseCode;
    }

    public void setInWarehouseCode(String inWarehouseCode) {
        this.inWarehouseCode = inWarehouseCode;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getInWarehouseState() {
        return inWarehouseState;
    }

    public void setInWarehouseState(String inWarehouseState) {
        this.inWarehouseState = inWarehouseState;
    }


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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


    public String getCreateUserID() {
        return createUserId;
    }

    public void setCreateUserID(String createUserID) {
        this.createUserId = createUserID;
    }

    public List<InWarehouseDetail> getInWarehouseDetailList() {
        return inWarehouseDetailList;
    }

    public void setInWarehouseDetailList(List<InWarehouseDetail> inWarehouseDetailList) {
        this.inWarehouseDetailList = inWarehouseDetailList;
    }



    public String getCreateUserName() {
        return createUserName;
    }


    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getInWarehouseTime() {
        return inWarehouseTime;
    }

    public void setInWarehouseTime(Date inWarehouseTime) {
        this.inWarehouseTime = inWarehouseTime;
    }

    public String getInWorker() {
        return inWorker;
    }

    public void setInWorker(String inWorker) {
        this.inWorker = inWorker;
    }
}
