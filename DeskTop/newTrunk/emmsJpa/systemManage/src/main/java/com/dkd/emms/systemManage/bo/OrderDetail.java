package com.dkd.emms.systemManage.bo;

import java.math.BigDecimal;

/**
 * Created by YUZH on 2017/2/24.
 */
public class OrderDetail {
    private String orderDetailId;
    private String orderId;
    private String orderCode;
    private String supplierId;
    private String supplierName;
    private String orderDetailSequence;
    private String materialsId;
    private String materialsCode;
    private String wbsId;
    private String wbsCode;
    private String orderDetailUnit;
    private BigDecimal orderDetailCount;
    private BigDecimal orderDetailUnitPrice;
    private BigDecimal orderDetailTotalPrice;
    private String materialsDescribe;
    private String  additional1;
    private String  additional2;
    private String  additional3;
    private String  additional4;
    private BigDecimal orderDetailPlanCount;
    private Boolean isNewRecord;
    private BigDecimal deliveryCount = new BigDecimal(0);
    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailSequence() {
        return orderDetailSequence;
    }

    public void setOrderDetailSequence(String orderDetailSequence) {
        this.orderDetailSequence = orderDetailSequence;
    }

    public String getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(String materialsId) {
        this.materialsId = materialsId;
    }

    public String getMaterialsCode() {
        return materialsCode;
    }

    public void setMaterialsCode(String materialsCode) {
        this.materialsCode = materialsCode;
    }

    public String getWbsId() {
        return wbsId;
    }

    public void setWbsId(String wbsId) {
        this.wbsId = wbsId;
    }

    public String getWbsCode() {
        return wbsCode;
    }

    public void setWbsCode(String wbsCode) {
        this.wbsCode = wbsCode;
    }

    public String getOrderDetailUnit() {
        return orderDetailUnit;
    }

    public void setOrderDetailUnit(String orderDetailUnit) {
        this.orderDetailUnit = orderDetailUnit;
    }

    public BigDecimal getOrderDetailCount() {
        return orderDetailCount;
    }

    public void setOrderDetailCount(BigDecimal orderDetailCount) {
        this.orderDetailCount = orderDetailCount;
    }

    public BigDecimal getOrderDetailUnitPrice() {
        return orderDetailUnitPrice;
    }

    public void setOrderDetailUnitPrice(BigDecimal orderDetailUnitPrice) {
        this.orderDetailUnitPrice = orderDetailUnitPrice;
    }

    public BigDecimal getOrderDetailTotalPrice() {
        return orderDetailTotalPrice;
    }

    public void setOrderDetailTotalPrice(BigDecimal orderDetailTotalPrice) {
        this.orderDetailTotalPrice = orderDetailTotalPrice;
    }

    public String getMaterialsDescribe() {
        return materialsDescribe;
    }

    public void setMaterialsDescribe(String materialsDescribe) {
        this.materialsDescribe = materialsDescribe;
    }

    public String getAdditional1() {
        return additional1;
    }

    public void setAdditional1(String additional1) {
        this.additional1 = additional1;
    }

    public String getAdditional2() {
        return additional2;
    }

    public void setAdditional2(String additional2) {
        this.additional2 = additional2;
    }

    public String getAdditional3() {
        return additional3;
    }

    public void setAdditional3(String additional3) {
        this.additional3 = additional3;
    }

    public String getAdditional4() {
        return additional4;
    }

    public void setAdditional4(String additional4) {
        this.additional4 = additional4;
    }

    public BigDecimal getOrderDetailPlanCount() {
        return orderDetailPlanCount;
    }

    public void setOrderDetailPlanCount(BigDecimal orderDetailPlanCount) {
        this.orderDetailPlanCount = orderDetailPlanCount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Boolean getIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(Boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
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

    public BigDecimal getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(BigDecimal deliveryCount) {
        this.deliveryCount = deliveryCount;
    }
}
