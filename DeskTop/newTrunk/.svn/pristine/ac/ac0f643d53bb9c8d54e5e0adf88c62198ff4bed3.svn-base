package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.dkd.emms.core.entity.Tree;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryPacking extends Tree{
    /**
     * 发货包装ID（系统生成）
     */
     private String packingId;

    /**
     *包装号或集装箱号（录入）
     */
    private String packingNo;

    /**
     *包装序号
     */
    private int packingOrder;

    /**
     *发货单ID（发货单带入）
     */
    private String deliveryId;

    /**
     *发货单号（发货单带入）
     */
    private String deliveryNo;

    /**
     *供应商名称（发货单带入）
     */
    private String supplierName;

    /**
     *供应商ID（发货单带入）
     */
    private String supplierId;

    /**
     *状态（新建、包装完成，包装出厂）
     */
    private String packingState;

    /**
     *车辆ID（新建车辆后，带入更新）
     */
    private String vehicleId;

    /**
     *包装类型（包装、散件）（包装发货时，默认实物发货；散件发货时，默认虚拟包装发货）
     */
    private String packingType;

    /**
     * 包装重量
     */
    private BigDecimal packingWeight;
    /**
     * 包装尺寸
     */
    private BigDecimal packingSize;

    /**
     * easy ui 页面显示需要
     */
    private String _parentId;
    /**
     * 是否收货
     */
    private String isReceipt;
    /**
     * 子包装信息
     */
    private List<DeliveryPacking> childPacking = new ArrayList<DeliveryPacking>();

    /**
     * 包装明细信息
     */
    List<DeliveryPackageDetail> deliveryPackageDetailList = new ArrayList<DeliveryPackageDetail>();

    public String getPackingId() {
        return packingId;
    }

    public void setPackingId(String packingId) {
        this.packingId = packingId;
    }

    public String getPackingNo() {
        return packingNo;
    }

    public void setPackingNo(String packingNo) {
        this.packingNo = packingNo;
    }

    public int getPackingOrder() {
        return packingOrder;
    }

    public void setPackingOrder(int packingOrder) {
        this.packingOrder = packingOrder;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPackingState() {
        return packingState;
    }

    public void setPackingState(String packingState) {
        this.packingState = packingState;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public BigDecimal getPackingWeight() {
        return packingWeight;
    }

    public void setPackingWeight(BigDecimal packingWeight) {
        this.packingWeight = packingWeight;
    }

    public BigDecimal getPackingSize() {
        return packingSize;
    }

    public void setPackingSize(BigDecimal packingSize) {
        this.packingSize = packingSize;
    }
    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    public List<DeliveryPacking> getChildPacking() {
        return childPacking;
    }

    public void setChildPacking(List<DeliveryPacking> childPacking) {
        this.childPacking = childPacking;
    }

    public List<DeliveryPackageDetail> getDeliveryPackageDetailList() {
        return deliveryPackageDetailList;
    }

    public void setDeliveryPackageDetailList(List<DeliveryPackageDetail> deliveryPackageDetailList) {
        this.deliveryPackageDetailList = deliveryPackageDetailList;
    }

    public String getIsReceipt() {
        return isReceipt;
    }

    public void setIsReceipt(String isReceipt) {
        this.isReceipt = isReceipt;
    }
}