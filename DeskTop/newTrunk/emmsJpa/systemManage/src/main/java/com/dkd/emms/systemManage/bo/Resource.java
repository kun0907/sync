package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.Tree;

/**
 * 资源类
 *
 * @author WUJY
 */

public class Resource extends Tree {
    /**
     * 权限id
     */
    private String resourceId;
    /**
     * 权限编码
     */
    private String resourceCode;
    /**
     * 权限名称
     */
    private String resourceName;
    /**
     * 权限类型(菜单资源、请求资源)
     */
    private String resourceType;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
