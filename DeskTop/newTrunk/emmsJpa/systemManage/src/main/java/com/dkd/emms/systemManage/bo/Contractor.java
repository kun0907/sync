package com.dkd.emms.systemManage.bo;


import com.dkd.emms.core.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 承包商实体信息
 *
 * @author wangqian
 */
public class Contractor extends BaseEntity {
    /**
     * 供应商id（供应商）
     */
    private String contractorId;
    /**
     * 承包商列表操作状态
     */
    private String contractorState;
    /**
     * 关联组织机构
     */
    private Organization organization;
    /**
     * 合同号
     */
    private String contractNo;
    /**
     * 经营范围
     */
    private String operateArea;
    /**
     * 办公地址
     */
    private String workAddress;
    /**
     * 注册资金
     */
    private BigDecimal registerCapital;
    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 联系人
     */
    private String linkMan;
    /**
     * 联系电话
     */
    private String linkPhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 法人代表
     */
    private String legalRepresentative;
    /**
     * 企业性质：国有企业、集体所有制企业、联营企业、三资企业、私营企业、其他企业
     */
    private String enterpriseProperty;


    public String getContractorId() {
        return contractorId;
    }

    public void setContractorId(String contractorId) {
        this.contractorId = contractorId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getOperateArea() {
        return operateArea;
    }

    public void setOperateArea(String operateArea) {
        this.operateArea = operateArea;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(BigDecimal registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getEnterpriseProperty() {
        return enterpriseProperty;
    }

    public void setEnterpriseProperty(String enterpriseProperty) {
        this.enterpriseProperty = enterpriseProperty;
    }

    public String getContractorState() {
        return contractorState;
    }

    public void setContractorState(String contractorState) {
        this.contractorState = contractorState;
    }

}
