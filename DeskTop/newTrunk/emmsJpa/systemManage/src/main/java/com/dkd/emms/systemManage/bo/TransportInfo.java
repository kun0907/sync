package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by YINXP on 2017/4/24.
 */
public class TransportInfo extends BaseEntity{
    private String transportInfoId;//运输单ID
    private String jiGangId;//集港单据ID
    private String transportInfoDetailsId;//明细ID
    private String transportNo;//运输单编号
    private String inspectStaus;//运输单状态
    private String jiGangSite;//集港地点
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jiGangTime;//集港时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private List<TransportInfoDetail> transportInfoDetails;//运输单明细

    public List<TransportInfoDetail> getTransportInfoDetails() {
        return transportInfoDetails;
    }

    public void setTransportInfoDetails(List<TransportInfoDetail> transportInfoDetails) {
        this.transportInfoDetails = transportInfoDetails;
    }

    public String getTransportInfoId() {
        return transportInfoId;
    }

    public String getInspectStaus() {
        return inspectStaus;
    }

    public void setInspectStaus(String inspectStaus) {
        this.inspectStaus = inspectStaus;
    }

    public String getTransportInfoDetailsId() {
        return transportInfoDetailsId;
    }

    public void setTransportInfoDetailsId(String transportInfoDetailsId) {
        this.transportInfoDetailsId = transportInfoDetailsId;
    }

    public void setTransportInfoId(String transportInfoId) {
        this.transportInfoId = transportInfoId;
    }

    public String getJiGangId() {
        return jiGangId;
    }

    public void setJiGangId(String jiGangId) {
        this.jiGangId = jiGangId;
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }

    public String getJiGangSite() {
        return jiGangSite;
    }

    public void setJiGangSite(String jiGangSite) {
        this.jiGangSite = jiGangSite;
    }

    public Date getJiGangTime() {
        return jiGangTime;
    }

    public void setJiGangTime(Date jiGangTime) {
        this.jiGangTime = jiGangTime;
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
