package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

/**
 * Created by YUZH on 2017/4/25.
 */
public class ProcessDetail extends BaseEntity {
    private String processDetailId;
    private String processId;
    private Integer sequence;
    private String processDetailLevel;
    private String roleId;
    private Boolean isCheck;

    public String getProcessDetailId() {
        return processDetailId;
    }

    public void setProcessDetailId(String processDetailId) {
        this.processDetailId = processDetailId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessDetailLevel() {
        return processDetailLevel;
    }

    public void setProcessDetailLevel(String processDetailLevel) {
        this.processDetailLevel = processDetailLevel;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
