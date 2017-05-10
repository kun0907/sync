package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/3/6.
 */
public class ReceiptGoods extends BaseEntity {
    private String receiptId;
    private String receiptCode;
    private String receiptState;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arriveTime;
    private String supplierId;
    private String supplierName;
    private String receiptType;
    private String deliveryId;
    private String deliveryNo;
    private List<ReceiptPacking> receiptPackingList;
    private List<ReceiptPackingDetail> detailList;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime ;
    private String orderNoSeq; //当单据类型为采购订单，存入采购订单编号
    private String inStorage;  //入库类型(收货，直达现场)
    private String receiptOrgId;
    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(String receiptState) {
        this.receiptState = receiptState;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
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

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public List<ReceiptPacking> getReceiptPackingList() {
        return receiptPackingList;
    }

    public void setReceiptPackingList(List<ReceiptPacking> receiptPackingList) {
        this.receiptPackingList = receiptPackingList;
    }

    public List<ReceiptPackingDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ReceiptPackingDetail> detailList) {
        this.detailList = detailList;
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

    public String getOrderNoSeq() {
        return orderNoSeq;
    }

    public void setOrderNoSeq(String orderNoSeq) {
        this.orderNoSeq = orderNoSeq;
    }

    public String getInStorage() {
        return inStorage;
    }

    public void setInStorage(String inStorage) {
        this.inStorage = inStorage;
    }

    public String getReceiptOrgId() {
        return receiptOrgId;
    }

    public void setReceiptOrgId(String receiptOrgId) {
        this.receiptOrgId = receiptOrgId;
    }
}
