package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YINXP on 2017/4/25.
 */
public class TransportInfoDetail extends BaseEntity {
    private String transportInfoDetailId;//运输单明细Id
    private String transportInfoId;//运输单ID
    private String shipName;//船名
    private String oceanVessel;//航次
    private String createUserName;
    private String inspectStaus;//单据状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jiGangTime;//集港日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectDepartureDate;//预计离港时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualDepartureDate;//实际离港时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expectArrivalDate;//预计到港时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualArrivalDate;//实际到港时间
    public Date getQingGuanFangXingDate() {
        return qingGuanFangXingDate;
    }

    public void setQingGuanFangXingDate(Date qingGuanFangXingDate) {
        this.qingGuanFangXingDate = qingGuanFangXingDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date qingGuanFangXingDate;//清关放行时间

    public Date getActualArrivalDate() {
        return actualArrivalDate;
    }

    public void setActualArrivalDate(Date actualArrivalDate) {
        this.actualArrivalDate = actualArrivalDate;
    }

    public String getTransportInfoDetailId() {
        return transportInfoDetailId;
    }

    public void setTransportInfoDetailId(String transportInfoDetailId) {
        this.transportInfoDetailId = transportInfoDetailId;
    }

    public String getInspectStaus() {
        return inspectStaus;
    }

    public void setInspectStaus(String inspectStaus) {
        this.inspectStaus = inspectStaus;
    }

    public String getTransportInfoId() {
        return transportInfoId;
    }

    public void setTransportInfoId(String transportInfoId) {
        this.transportInfoId = transportInfoId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getOceanVessel() {
        return oceanVessel;
    }

    public void setOceanVessel(String oceanVessel) {
        this.oceanVessel = oceanVessel;
    }

    public Date getJiGangTime() {
        return jiGangTime;
    }

    public void setJiGangTime(Date jiGangTime) {
        this.jiGangTime = jiGangTime;
    }

    public Date getExpectDepartureDate() {
        return expectDepartureDate;
    }

    public void setExpectDepartureDate(Date expectDepartureDate) {
        this.expectDepartureDate = expectDepartureDate;
    }

    public Date getActualDepartureDate() {
        return actualDepartureDate;
    }

    public void setActualDepartureDate(Date actualDepartureDate) {
        this.actualDepartureDate = actualDepartureDate;
    }

    public Date getExpectArrivalDate() {
        return expectArrivalDate;
    }

    public void setExpectArrivalDate(Date expectArrivalDate) {
        this.expectArrivalDate = expectArrivalDate;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCreateUserName() {
        return createUserName;
    }

    @Override
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
}
