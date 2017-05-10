package com.dkd.emms.web.purchase.transportInfo.queryCondition;

import com.dkd.emms.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YINXP on 2017/4/25.
 */
public class TransportInfoCondition extends BaseEntity {
    private String transportNo;//运输单编号
    private String inspectStaus;//运输单状态
    private String jiGangSite;//集港地点
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jiGangTime;//集港时间

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo;
    }

    public String getInspectStaus() {
        return inspectStaus;
    }

    public void setInspectStaus(String inspectStaus) {
        this.inspectStaus = inspectStaus;
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
