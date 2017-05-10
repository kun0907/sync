package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YUZH on 2017/3/8.
 */
public class ReceiptPackingDetail extends BaseEntity{
    private String packingDetailId;
    private String packingId;
    private String receiptId;
    private String receiptCode;
    private String packingCode;
    private BigDecimal deliveryCount;   //已发货数量
    private BigDecimal thisDeliveryCount; //本次发货数量
    private BigDecimal purchaseCount; //采购数量
    private BigDecimal dianshouCount;  //点收数量
    private String wbsId;
    private String wbsCode;
    private String storageId;//储位ID
    private String storageCode;//储位
    private String materialsId;
    private String materialsCode;
    private String materialsDescribe;
    private String additional1;
    private String additional2;
    private String additional3;
    private String additional4;
    private String docSourceDetailId;    //采购单据
    private String docSourceType;//采购类型
    private String docSourceNo;  //采购单据号
    private String deMainUnit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bzq;         //保质期
    private String deviceNo;
    private String receiptOrgId;
    public BigDecimal getThisDeliveryCount() {
        return thisDeliveryCount;
    }

    public void setThisDeliveryCount(BigDecimal thisDeliveryCount) {
        this.thisDeliveryCount = thisDeliveryCount;
    }

    public BigDecimal getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(BigDecimal deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public BigDecimal getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(BigDecimal purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public BigDecimal getDianshouCount() {
        return dianshouCount;
    }

    public void setDianshouCount(BigDecimal dianshouCount) {
        this.dianshouCount = dianshouCount;
    }

    public String getPackingDetailId() {
        return packingDetailId;
    }

    public void setPackingDetailId(String packingDetailId) {
        this.packingDetailId = packingDetailId;
    }

    public String getPackingId() {
        return packingId;
    }

    public void setPackingId(String packingId) {
        this.packingId = packingId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getPackingCode() {
        return packingCode;
    }

    public void setPackingCode(String packingCode) {
        this.packingCode = packingCode;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
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

    public String getDocSourceDetailId() {
        return docSourceDetailId;
    }

    public void setDocSourceDetailId(String docSourceDetailId) {
        this.docSourceDetailId = docSourceDetailId;
    }

    public String getDocSourceType() {
        return docSourceType;
    }

    public void setDocSourceType(String docSourceType) {
        this.docSourceType = docSourceType;
    }

    public String getDocSourceNo() {
        return docSourceNo;
    }

    public void setDocSourceNo(String docSourceNo) {
        this.docSourceNo = docSourceNo;
    }

    public String getDeMainUnit() {
        return deMainUnit;
    }

    public void setDeMainUnit(String deMainUnit) {
        this.deMainUnit = deMainUnit;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getBzq() {
        return bzq;
    }

    public void setBzq(Date bzq) {
        this.bzq = bzq;
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

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getReceiptOrgId() {
        return receiptOrgId;
    }

    public void setReceiptOrgId(String receiptOrgId) {
        this.receiptOrgId = receiptOrgId;
    }
}
