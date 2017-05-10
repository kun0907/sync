package com.dkd.emms.web.purchase.agreement.queryCondition;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/24.
 */
public class AgreementCondition {
    /**
     *
     * 编号
     *
     *
     */
    private String agreementId;
    /**
     *
     * 框架协议号
     *
     *
     */
    private String agreementCode;
    /**
     *
     * 创建人
     *
     *
     */
    private String createUserName;

    /**
     *
     * 录入时间
     *
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createStartTime ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createEndTime ;
    /**
     *
     * 供应商名称
     *
     *
     */
    private String orgName;

    /**
     *
     * 合同执行开始时间
     *
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date performStartDate;

    /**
     *
     * 合同执行结束时间
     *
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date performEndDate;
    /**
     *
     * 状态（新建、提交）
     *
     *
     */
    private String agreementState;
    /**
     *
     * 供应商框架协议号
     *
     *
     */
    private String supplierAgreementCode;


    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getAgreementCode() {
        return agreementCode;
    }


    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getCreateUserName() {
        return createUserName;
    }


    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }



    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getPerformStartDate() {
        return performStartDate;
    }


    public void setPerformStartDate(Date performStartDate) {
        this.performStartDate = performStartDate;
    }


    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getPerformEndDate() {
        return performEndDate;
    }


    public void setPerformEndDate(Date performEndDate) {
        this.performEndDate = performEndDate;
    }


    public String getOrgName() {
        return orgName;
    }


    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAgreementState() {
        return agreementState;
    }


    public void setAgreementState(String agreementState) {
        this.agreementState = agreementState;
    }


    public String getSupplierAgreementCode() {
        return supplierAgreementCode;
    }


    public void setSupplierAgreementCode(String supplierAgreementCode) {
        this.supplierAgreementCode = supplierAgreementCode;
    }

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

}

