package com.dkd.emms.systemManage.bo;

/**
 * Created by Administrator on 2017/2/22.
 */


        import java.math.BigDecimal;
        import java.util.Date;
        import java.util.List;

        import com.dkd.emms.core.entity.BaseEntity;
        import com.fasterxml.jackson.annotation.JsonFormat;
        import org.springframework.format.annotation.DateTimeFormat;

public class Agreement extends BaseEntity {


    /**
     *
     * 框架协议ID（系统生成）
     *
     *
     */
    private String agreementId;

    /**
     *
     * 框架协议编号
     *
     *
     */
    private String agreementCode;

    /**
     *
     * 状态（新建、提交）
     *
     *
     */
    private String agreementState;

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
     * 录入人id
     *
     *
     */
    private String createUserId;

    /**
     *
     * 录入人
     *
     *
     */
    private String createUserName;

    /**
     *
     * 录入时间
     *
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     *
     *
     *物资明细列表
     *
     */
    private List<AgreementDetail> agreementDetailList;
    /**
     *
     *供应商列表
     *
     *
     */
    private List<AgreementSupplier> agreementSupplierList;
    /**
     *
     * 供应商名称
     *
     *
     */
    private String orgName;
    /**
     *
     * 框架协议号
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


    public String getAgreementState() {
        return agreementState;
    }


    public void setAgreementState(String agreementState) {
        this.agreementState = agreementState;
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


    public String getCreateUserId() {
        return createUserId;
    }


    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }


    public String getCreateUserName() {
        return createUserName;
    }


    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }



    public List<AgreementDetail>getAgreementDetailList() {return agreementDetailList;}


    public void setAgreementDetailList(List<AgreementDetail>agreementDetailList){this.agreementDetailList=agreementDetailList;}


    public List<AgreementSupplier>getAgreementSupplierList() {return agreementSupplierList;}


    public void setAgreementSupplierList(List<AgreementSupplier>agreementSupplierList){this.agreementSupplierList=agreementSupplierList;}

    public String getOrgName() {
        return orgName;
    }


    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public String getSupplierAgreementCode() {
        return supplierAgreementCode;
    }


    public void setSupplierAgreementCode(String supplierAgreementCode) {
        this.supplierAgreementCode = supplierAgreementCode;
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
}
