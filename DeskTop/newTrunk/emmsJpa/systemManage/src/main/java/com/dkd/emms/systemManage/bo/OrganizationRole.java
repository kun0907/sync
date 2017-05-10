package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

/**
 * 组织机构角色
 *
 * @author WANGQ
 */
public class OrganizationRole extends BaseEntity {
    /**
     * 组织机构id
     */
    private Organization organization;
    /**
     * 角色id
     */
    private Role role;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
