package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 供应商发货主表
 */
public class Delivery extends BaseEntity {
    /**
     * 发货id
     */
    private String deliveryId;

    /**
     * 发货单号
     */
    private String deliveryNo;

    /**
     * 发货状态
     */
    private String deliveryState;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商id
     */
    private String supplierId;
    /**
     * 发货日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 预到货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectedArrivalDate;
    /**
     * 包装数量
     */
    private int packageNum;

    /**
     * 包装列表
     */
    private List<DeliveryPacking> deliveryPackingList;

    /**
     * 车辆明细
     */
    private  List<DeliveryVehicle> deliveryVehicleList;

    /**
     * 物料明细
     */
    private List<DeliveryPackageDetail> packageDetail;

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

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getExpectedArrivalDate() {
        return expectedArrivalDate;
    }

    public void setExpectedArrivalDate(Date expectedArrivalDate) {
        this.expectedArrivalDate = expectedArrivalDate;
    }

    public int getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(int packageNum) {
        this.packageNum = packageNum;
    }

    public List<DeliveryPacking> getDeliveryPackingList() {
        return deliveryPackingList;
    }

    public void setDeliveryPackingList(List<DeliveryPacking> deliveryPackingList) {
        this.deliveryPackingList = deliveryPackingList;
    }

    public List<DeliveryVehicle> getDeliveryVehicleList() {
        return deliveryVehicleList;
    }

    public void setDeliveryVehicleList(List<DeliveryVehicle> deliveryVehicleList) {
        this.deliveryVehicleList = deliveryVehicleList;
    }

    public List<DeliveryPackageDetail> getPackageDetail() {
        return packageDetail;
    }

    public void setPackageDetail(List<DeliveryPackageDetail> packageDetail) {
        this.packageDetail = packageDetail;
    }
}