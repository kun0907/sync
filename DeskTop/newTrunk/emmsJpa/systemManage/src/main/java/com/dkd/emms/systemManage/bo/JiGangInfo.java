package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by YINXP on 2017/4/25.
 */
public class JiGangInfo extends BaseEntity {
    private String jiGangId;//集港单据ID
    private String deliveryId;// 发货单ID
    private String deliveryNo;//发货单号
    private String jiGangSite;//集港地点
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jiGangTime;//集港时间
    private String inspectStaus;//集港状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private List<JiGangInfoDetail> jiGangInfoDetails;//集港明细


    public String getInspectStaus() {
        return inspectStaus;
    }

    public void setInspectStaus(String inspectStaus) {
        this.inspectStaus = inspectStaus;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public List<JiGangInfoDetail> getJiGangInfoDetails() {
        return jiGangInfoDetails;
    }

    public void setJiGangInfoDetails(List<JiGangInfoDetail> jiGangInfoDetails) {
        this.jiGangInfoDetails = jiGangInfoDetails;
    }

    public String getJiGangId() {
        return jiGangId;
    }

    public void setJiGangId(String jiGangId) {
        this.jiGangId = jiGangId;
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
