package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

/**
 * 组织机构类型实体类
 *
 * @author WANGQ
 */

public class OrganizationTypeWBS extends BaseEntity {
    /**
     * 组织机构类型id
     */
    private OrganizationType organizationType;
    /**
     * 机构id
     */
    private String orgId;
    /**
     * 主项目ID
     */
    private Project project;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(OrganizationType organizationType) {
        this.organizationType = organizationType;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
