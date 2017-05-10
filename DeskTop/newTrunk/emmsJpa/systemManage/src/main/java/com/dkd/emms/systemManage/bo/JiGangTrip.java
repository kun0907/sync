package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

/**
 * Created by YINXP on 2017/4/26.
 */
public class JiGangTrip extends BaseEntity {
    private String jiGangTripId;
    private String transportNo;//运输单编号
    private String shipName;//航次
    private String oceanVessel;//航次

    public String getJiGangTripId() {
        return jiGangTripId;
    }

    public void setJiGangTripId(String jiGangTripId) {
        this.jiGangTripId = jiGangTripId;
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
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
}
