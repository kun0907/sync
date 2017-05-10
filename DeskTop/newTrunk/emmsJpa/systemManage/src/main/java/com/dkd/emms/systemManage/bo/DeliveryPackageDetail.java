package com.dkd.emms.systemManage.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class DeliveryPackageDetail {
    /**
     * 发货包装ID（包装表带入）
     */
    private String packingId;

    /**
     * 发货包装行号（包装表带入）
     */
    private int packingRowNo;

    /**
     * 发货单ID（包装表带入）
     */
    private String deliveryId;

    /**
     * 发货单号（包装表带入）
     */
    private String deliveryNo;

    /**
     * WBS编码ID（明细单据带入）
     */
    private String wbsId;

    /**
     * WBS编码（明细单据带入）
     */
    private String wbsCode;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 发货包装明细ID（系统生成）
     */
    private String deDetailId;

    /**
     * 发货包装明细序号（系统生成）
     */
    private int deDetailRowno;

    /**
     * 来源单据明细ID（采购订单）
     */
    private String docSourceDetailId;

    /**
     * 来源单据明细行号
     */
    private String docSourceDetailRowno;

    /**
     * 来源单据类型（采购订单）
     */
    private String docSourceType;

    /**
     * 来源单据号（采购订单号）
     */
    private String docSourceNo;

    /**
     * 物料编码ID（明细单据带入）
     */
    private String materialsId;
    /**
     * 物料编码（明细单据带入）
     */
    private String materialsCode;

    /**
     * 采购数量
     */
    public BigDecimal purchaseNum;
    /**
     * 发货单计量单位编码（明细单据带入）
     */
    private String deMainUnit;

    /**
     * 辅助发货单计量单位编码（明细单据带入）
     */
    private String deSecUnit;

    /**
     * 发货数量
     */
    private BigDecimal deliveryMainCount;

    /**
     * 辅助发货数量
     */
    private int deliverySecCount;

    /**
     * 发货主副计量单位换算率
     */
    private BigDecimal conversion;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 币种编码
     */
    private String currencyCode;

    /**
     * 生产日期（年月日）
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date productionDate;

    /**
     * 保质期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bzq;

    /**
     * 到期时间（年月日）
     */
    private Date expirationDate;
    /**
     * 物料描述（明细单据带入）
     */
    private String materialsDescribe;
    /**
     * 附加1（明细单据带入）
     */
    private String additional1;
    /**
     * 附加2（明细单据带入）
     */
    private String additional2;
    /**
     * 附加3（明细单据带入）
     */
    private String additional3;
    /**
     * 附加4（明细单据带入）
     */
    private String additional4;
    /**
     * 已发货数量（明细单据带入）
     */
    private BigDecimal deliveryCount;

    /**
     * 已收货数量
     */
    private BigDecimal receivedNum = new BigDecimal(0);

    public String getPackingId() {
        return packingId;
    }

    public void setPackingId(String packingId) {
        this.packingId = packingId;
    }

    public int getPackingRowNo() {
        return packingRowNo;
    }

    public void setPackingRowNo(int packingRowNo) {
        this.packingRowNo = packingRowNo;
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

    public String getDeDetailId() {
        return deDetailId;
    }

    public void setDeDetailId(String deDetailId) {
        this.deDetailId = deDetailId;
    }

    public int getDeDetailRowno() {
        return deDetailRowno;
    }

    public void setDeDetailRowno(int deDetailRowno) {
        this.deDetailRowno = deDetailRowno;
    }

    public String getDocSourceDetailId() {
        return docSourceDetailId;
    }

    public void setDocSourceDetailId(String docSourceDetailId) {
        this.docSourceDetailId = docSourceDetailId;
    }

    public String getDocSourceDetailRowno() {
        return docSourceDetailRowno;
    }

    public void setDocSourceDetailRowno(String docSourceDetailRowno) {
        this.docSourceDetailRowno = docSourceDetailRowno;
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

    public String getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(String materialsId) {
        this.materialsId = materialsId;
    }

    public String getDeMainUnit() {
        return deMainUnit;
    }

    public void setDeMainUnit(String deMainUnit) {
        this.deMainUnit = deMainUnit;
    }

    public String getDeSecUnit() {
        return deSecUnit;
    }

    public void setDeSecUnit(String deSecUnit) {
        this.deSecUnit = deSecUnit;
    }

    public BigDecimal getDeliveryMainCount() {
        return deliveryMainCount;
    }

    public void setDeliveryMainCount(BigDecimal deliveryMainCount) {
        this.deliveryMainCount = deliveryMainCount;
    }

    public int getDeliverySecCount() {
        return deliverySecCount;
    }

    public void setDeliverySecCount(int deliverySecCount) {
        this.deliverySecCount = deliverySecCount;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(BigDecimal purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getProductionDate() {
        return productionDate;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMaterialsCode() {
        return materialsCode;
    }

    public void setMaterialsCode(String materialsCode) {
        this.materialsCode = materialsCode;
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

    public BigDecimal getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(BigDecimal deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public BigDecimal getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(BigDecimal receivedNum) {
        this.receivedNum = receivedNum;
    }
}