package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by YINXP on 2017/3/8.
 * 质检单
 */
public class MaterialInspect extends BaseEntity {
    private String materiaInspectId;//质检单id
    private String inspectNo;//质检单编号
    private String inspectPicFileId;
    private String qualityInspectId;//质检明细单id
    private String deliveryId;//收货单编号
    private String inspectDetailNo;//质检明细编号
    private String inspectStaus;//质检单状态
    private String qualityInspectCheck;//质检合格
    private String inspector;//质检员
    private String supplierId;//供应商id
    private String supplierName;//供应商名称
    private String createUserName;//创建人
    private String dataSource;//数据来源（直达、物资）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;//创建时间
    private List<QualityInspectDetail> qualityInspectDetailList;//质检明细列表
    private List<InspectPicFile> inspectPicFileList;//图片明细
    private String receiptOrgId;//施工单位
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<QualityInspectDetail> getQualityInspectDetailList() {
        return qualityInspectDetailList;
    }

    public void setQualityInspectDetailList(List<QualityInspectDetail> qualityInspectDetailList) {
        this.qualityInspectDetailList = qualityInspectDetailList;
    }

    @Override
    public String getCreateUserName() {
        return createUserName;
    }

    @Override
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getMateriaInspectId() {
        return materiaInspectId;
    }

    public void setMateriaInspectId(String materiaInspectId) {
        this.materiaInspectId = materiaInspectId;
    }

    public String getInspectNo() {
        return inspectNo;
    }

    public void setInspectNo(String inspectNo) {
        this.inspectNo = inspectNo;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getInspectDetailNo() {
        return inspectDetailNo;
    }

    public void setInspectDetailNo(String inspectDetailNo) {
        this.inspectDetailNo = inspectDetailNo;
    }

    public String getInspectStaus() {
        return inspectStaus;
    }

    public void setInspectStaus(String inspectStaus) {
        this.inspectStaus = inspectStaus;
    }

    public String getQualityInspectCheck() {
        return qualityInspectCheck;
    }

    public void setQualityInspectCheck(String qualityInspectCheck) {
        this.qualityInspectCheck = qualityInspectCheck;
    }

    public String getInspectPicFileId() {
        return inspectPicFileId;
    }

    public void setInspectPicFileId(String inspectPicFileId) {
        this.inspectPicFileId = inspectPicFileId;
    }

    public String getQualityInspectId() {
        return qualityInspectId;
    }

    public void setQualityInspectId(String qualityInspectId) {
        this.qualityInspectId = qualityInspectId;
    }


    public List<InspectPicFile> getInspectPicFileList() {
        return inspectPicFileList;
    }

    public void setInspectPicFileList(List<InspectPicFile> inspectPicFileList) {
        this.inspectPicFileList = inspectPicFileList;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getReceiptOrgId() {
        return receiptOrgId;
    }

    public void setReceiptOrgId(String receiptOrgId) {
        this.receiptOrgId = receiptOrgId;
    }
}
