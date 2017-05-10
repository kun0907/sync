package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YINXP on 2017/4/25.
 */
public class JiGangInfoDetail extends BaseEntity{
    private String jiGangId;//集港单据ID
    private String jiGangInfoDetailId;// 集港明细ID
    private String deliveryNo;//发货单号
    private String jiGangSite;//集港地点
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jiGangTime;//集港时间




    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getJiGangId() {
        return jiGangId;
    }

    public void setJiGangId(String jiGangId) {
        this.jiGangId = jiGangId;
    }

    public String getJiGangInfoDetailId() {
        return jiGangInfoDetailId;
    }

    public void setJiGangInfoDetailId(String jiGangInfoDetailId) {
        this.jiGangInfoDetailId = jiGangInfoDetailId;
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
}
