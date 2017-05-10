package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by YINXP on 2017/4/26.
 */
public class JiGangTime extends BaseEntity {
    private String jiGangTimeId;
    private String jiGangSite;//集港地点
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date jiGangTime;//集港时间

    public String getJiGangTimeId() {
        return jiGangTimeId;
    }

    public void setJiGangTimeId(String jiGangTimeId) {
        this.jiGangTimeId = jiGangTimeId;
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
