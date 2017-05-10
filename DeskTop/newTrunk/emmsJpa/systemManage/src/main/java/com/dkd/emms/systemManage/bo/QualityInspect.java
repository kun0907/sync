package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

import java.sql.Date;

/**
 * Created by YINXP on 2017/3/7.
 * 新建质检单(buyong)
 */
public class QualityInspect extends BaseEntity {
    private String inspectId;//质检单id
    private String qualityInspectId;//质检明细单id
    private String inspectNo;//质检单编号
    private String inspectPer;//质检员
    private String inspectStatus;//单据状态
    private String deliveryNo;//收货单编号
    private String materialNo;//物资编码
    private String materDesc;//物资描述
    private String wbsNo;//工程（WBS）编码
    private String procurementUnit;//采购计量单位
    private String additional1;//附加1
    private String additional2;//附加2
    private String additional3;//附加3
    private String additional4;//附加4
    private Date productDate;//生产日期
    private String qualityDate;//保质期
    private String quantity;//采购数量
    private String deliveryQty;//已发货数量
    private String currentDeliveryQty;//本次发货数量
    private String clickQty;//点击数量
    private String qualifiedQty;//合格数量
    private String unQualifiedQty;//不合格数量
    private String appearanceInspect;//外观检查(0 不合格 1 合格)
    private String recheckInspect;//需要复检（0 是 1 否）

    public String getInspectId() {
        return inspectId;
    }

    public void setInspectId(String inspectId) {
        this.inspectId = inspectId;
    }

    public String getRecheckInspect() {
        return recheckInspect;
    }

    public void setRecheckInspect(String recheckInspect) {
        this.recheckInspect = recheckInspect;
    }

    public String getAppearanceInspect() {
        return appearanceInspect;
    }

    public void setAppearanceInspect(String appearanceInspect) {
        this.appearanceInspect = appearanceInspect;
    }

    public String getInspectPer() {
        return inspectPer;
    }

    public void setInspectPer(String inspectPer) {
        this.inspectPer = inspectPer;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getMaterDesc() {
        return materDesc;
    }

    public void setMaterDesc(String materDesc) {
        this.materDesc = materDesc;
    }

    public String getWbsNo() {
        return wbsNo;
    }

    public void setWbsNo(String wbsNo) {
        this.wbsNo = wbsNo;
    }

    public String getProcurementUnit() {
        return procurementUnit;
    }

    public void setProcurementUnit(String procurementUnit) {
        this.procurementUnit = procurementUnit;
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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getQualityDate() {
        return qualityDate;
    }

    public void setQualityDate(String qualityDate) {
        this.qualityDate = qualityDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryQty() {
        return deliveryQty;
    }

    public void setDeliveryQty(String deliveryQty) {
        this.deliveryQty = deliveryQty;
    }

    public String getCurrentDeliveryQty() {
        return currentDeliveryQty;
    }

    public void setCurrentDeliveryQty(String currentDeliveryQty) {
        this.currentDeliveryQty = currentDeliveryQty;
    }

    public String getClickQty() {
        return clickQty;
    }

    public void setClickQty(String clickQty) {
        this.clickQty = clickQty;
    }

    public String getQualifiedQty() {
        return qualifiedQty;
    }

    public void setQualifiedQty(String qualifiedQty) {
        this.qualifiedQty = qualifiedQty;
    }

    public String getUnQualifiedQty() {
        return unQualifiedQty;
    }

    public void setUnQualifiedQty(String unQualifiedQty) {
        this.unQualifiedQty = unQualifiedQty;
    }

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo;
    }

    public String getQualityInspectId() {
        return qualityInspectId;
    }

    public void setQualityInspectId(String qualityInspectId) {
        this.qualityInspectId = qualityInspectId;
    }

    public String getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(String inspectStatus) {
        this.inspectStatus = inspectStatus;
    }
}
