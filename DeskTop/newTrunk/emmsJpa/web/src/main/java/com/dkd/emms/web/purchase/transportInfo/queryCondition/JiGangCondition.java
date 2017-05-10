package com.dkd.emms.web.purchase.transportInfo.queryCondition;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YINXP on 2017/4/25.
 */
public class JiGangCondition {
    private String jiGangId;//集港单据ID
    private String deliveryId;// 发货单ID
    private String deliveryNo;//发货单号
    private String jiGangSite;//集港地点

    public Date getJigangtime() {
        return jigangtime;
    }

    public void setJigangtime(Date jigangtime) {
        this.jigangtime = jigangtime;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jigangtime;//集港时间
}
