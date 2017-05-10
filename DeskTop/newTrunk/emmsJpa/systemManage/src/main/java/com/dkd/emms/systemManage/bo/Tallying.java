package com.dkd.emms.systemManage.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Tallying {
    /**
     *  理货id
     */
    private String tallyingId;

    /**
     * wbs
     */
    private Project wbs;

    /**
     *  施工单位
     */
    private Organization contractor;

    /**
     *  系统物料
     */
    private Materials materials;

    /**
     *  理货数量
     */
    private BigDecimal tallyingNum;

    /**
     *  理货日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tallyingDate;

    /**
     *  理货人
     */
    private String  tallyingUser;

    /**
     *  领料通知
     */
    private PickNotice pickNotice;

    /**
     *  领料通知明细
     */
    private PickNoticeDetail pickNoticeDetail;

    /**
     * 是否生成出库单
     */
    private String isOut;

    /**
     * 来源储位
     */
    private Storagelocation storagelocation;

    public Project getWbs() {
        return wbs;
    }

    public void setWbs(Project wbs) {
        this.wbs = wbs;
    }

    public String getTallyingId() {
        return tallyingId;
    }

    public void setTallyingId(String tallyingId) {
        this.tallyingId = tallyingId;
    }

    public Organization getContractor() {
        return contractor;
    }

    public void setContractor(Organization contractor) {
        this.contractor = contractor;
    }

    public Materials getMaterials() {
        return materials;
    }

    public void setMaterials(Materials materials) {
        this.materials = materials;
    }

    public BigDecimal getTallyingNum() {
        return tallyingNum;
    }

    public void setTallyingNum(BigDecimal tallyingNum) {
        this.tallyingNum = tallyingNum;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getTallyingDate() {
        return tallyingDate;
    }

    public void setTallyingDate(Date tallyingDate) {
        this.tallyingDate = tallyingDate;
    }

    public String getTallyingUser() {
        return tallyingUser;
    }

    public void setTallyingUser(String tallyingUser) {
        this.tallyingUser = tallyingUser;
    }

    public PickNotice getPickNotice() {
        return pickNotice;
    }

    public void setPickNotice(PickNotice pickNotice) {
        this.pickNotice = pickNotice;
    }

    public PickNoticeDetail getPickNoticeDetail() {
        return pickNoticeDetail;
    }

    public void setPickNoticeDetail(PickNoticeDetail pickNoticeDetail) {
        this.pickNoticeDetail = pickNoticeDetail;
    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public Storagelocation getStoragelocation() {
        return storagelocation;
    }

    public void setStoragelocation(Storagelocation storagelocation) {
        this.storagelocation = storagelocation;
    }
}