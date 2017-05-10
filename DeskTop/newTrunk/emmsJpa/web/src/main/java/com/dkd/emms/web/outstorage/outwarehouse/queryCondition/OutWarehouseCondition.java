package com.dkd.emms.web.outstorage.outwarehouse.queryCondition;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 出库单查询条件
 * Created by wangqian on 2017/2/24.
 */
public class OutWarehouseCondition {
    /**
     * 领料通知编号
     */
    private String outWarehouseNo;
    /**
     * 施工单位
     */
    private String supplierId;
    /**
     * 出库单状态
     */
    private String outWarehouseState;
    /**
     * 录入人
     */
    private String createUserName;
    /**
     * 录入开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    /**
     * 录入结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    /**
     * wbsId
     */
    private String wbsId;

    /**
     * 物资编码
     */
    private String materialsCode;


    /**
     * 物资描述
     */
    private String materialsDescribe;


    public String getOutWarehouseNo() {
        return outWarehouseNo;
    }

    public void setOutWarehouseNo(String outWarehouseNo) {
        this.outWarehouseNo = outWarehouseNo;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getOutWarehouseState() {
        return outWarehouseState;
    }

    public void setOutWarehouseState(String outWarehouseState) {
        this.outWarehouseState = outWarehouseState;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWbsId() {
        return wbsId;
    }

    public void setWbsId(String wbsId) {
        this.wbsId = wbsId;
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
}


