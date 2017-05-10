package com.dkd.emms.systemManage.bo;

/**
 * 角色资源关系
 *
 * @author SY
 */

public class RoleResource {

    private String roleId;//角色id
    private String resourceId;//权限id
    private String parentRoleId;//上级角色Id


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }
}
