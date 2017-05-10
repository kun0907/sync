package com.dkd.emms.systemManage.bo;

import java.util.Set;

/**
 * 角色实体
 *
 * @author WANGQ
 */
public class Role {
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 删除标记位
     */
    private String isDel;
    /**
     * 权限集合(仅限于显示使用)
     */
    private Set<Resource> resources;

    /**
     * 是否被选中
     */
    private boolean checked;
    /**
     * 根组织机构id
     */
    private String rootOrgId;

    /**
     * 角色类型（系统级、应用级）
     */
    private String roleType;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(String rootOrgId) {
        this.rootOrgId = rootOrgId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }


}
