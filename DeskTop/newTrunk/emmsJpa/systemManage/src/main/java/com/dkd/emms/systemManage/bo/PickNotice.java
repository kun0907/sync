package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PickNotice extends BaseEntity {
    /**
     *  领料通知单id
     */
    private String pickId;

    /**
     *  领料通知单No
     */
    private String pickNo;

    /**
     *  领料时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pickTime;

    /**
     *  施工单位
     */
    private Organization supplier;
    /**
     *  创建时间
     */
    /*@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;*/
    /**
     * 单据状态
     */
    private String pickNoticeState;
    /**
     * 领料通知明细列表
     */
    private List<PickNoticeDetail> pickNoticeDetailList;

    public String getPickId() {
        return pickId;
    }

    public void setPickId(String pickId) {
        this.pickId = pickId;
    }

    public String getPickNo() {
        return pickNo;
    }

    public void setPickNo(String pickNo) {
        this.pickNo = pickNo;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getPickTime() {
        return pickTime;
    }

    public void setPickTime(Date pickTime) {
        this.pickTime = pickTime;
    }

    public Organization getSupplier() {
        return supplier;
    }

    public void setSupplier(Organization supplier) {
        this.supplier = supplier;
    }
   /* @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }*/

    public String getPickNoticeState() {
        return pickNoticeState;
    }

    public void setPickNoticeState(String pickNoticeState) {
        this.pickNoticeState = pickNoticeState;
    }

    public List<PickNoticeDetail> getPickNoticeDetailList() {
        return pickNoticeDetailList;
    }

    public void setPickNoticeDetailList(List<PickNoticeDetail> pickNoticeDetailList) {
        this.pickNoticeDetailList = pickNoticeDetailList;
    }
}