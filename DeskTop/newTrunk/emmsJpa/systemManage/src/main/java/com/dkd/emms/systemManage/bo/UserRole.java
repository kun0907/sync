package com.dkd.emms.systemManage.bo;

/**
 * 用户角色授权
 *
 * @author WANGQ
 */
public class UserRole {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 权限id
     */
    private Resource resource;
    /**
     * 来源角色id
     */
    private Role role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
