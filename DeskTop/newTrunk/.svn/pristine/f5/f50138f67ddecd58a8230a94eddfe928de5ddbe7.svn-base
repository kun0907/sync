package com.dkd.emms.systemManage.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 出库单实体
 */
public class OutWarehouse {
    /**
     *  出库单id
     */
    private String outWarehouseId;

    /**
     * 出库单编号
     */
    private String outWarehouseNo;

    /**
     *  施工单位
     */
    private Organization contractor;

    /**
     *  创建用户id
     */
    private String createUserId;

    /**
     * 创建用户
     */
    private String createUserName;

    /**
     *  创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     *  出库时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outTime;

    /**
     *  出库单状态
     */
    private String outWarehouseState;
    /**
     * 来源单据类别
     */
    private String docSourceType;
    /**
     * 来源单据id
     */
    private String docSourceId;
    /**
     * 来源单据号
     */
    private String docSourceNo;

    private List<OutWarehouseDetail> outWarehouseDetailList= new ArrayList<OutWarehouseDetail>();

    public String getOutWarehouseId() {
        return outWarehouseId;
    }

    public void setOutWarehouseId(String outWarehouseId) {
        this.outWarehouseId = outWarehouseId;
    }

    public String getOutWarehouseNo() {
        return outWarehouseNo;
    }

    public void setOutWarehouseNo(String outWarehouseNo) {
        this.outWarehouseNo = outWarehouseNo;
    }

    public Organization getContractor() {
        return contractor;
    }

    public void setContractor(Organization contractor) {
        this.contractor = contractor;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOutWarehouseState() {
        return outWarehouseState;
    }

    public void setOutWarehouseState(String outWarehouseState) {
        this.outWarehouseState = outWarehouseState;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public List<OutWarehouseDetail> getOutWarehouseDetailList() {
        return outWarehouseDetailList;
    }

    public void setOutWarehouseDetailList(List<OutWarehouseDetail> outWarehouseDetailList) {
        this.outWarehouseDetailList = outWarehouseDetailList;
    }

    public String getDocSourceType() {
        return docSourceType;
    }

    public void setDocSourceType(String docSourceType) {
        this.docSourceType = docSourceType;
    }

    public String getDocSourceId() {
        return docSourceId;
    }

    public void setDocSourceId(String docSourceId) {
        this.docSourceId = docSourceId;
    }

    public String getDocSourceNo() {
        return docSourceNo;
    }

    public void setDocSourceNo(String docSourceNo) {
        this.docSourceNo = docSourceNo;
    }
}