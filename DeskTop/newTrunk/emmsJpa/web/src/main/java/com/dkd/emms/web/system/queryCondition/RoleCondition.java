package com.dkd.emms.web.system.queryCondition;
/**
 * 项目查询条件
 * @author WANGQ
 *
 */
public class RoleCondition {
	/**
	 * 角色Code
	 */
	private String roleCode;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色类型
	 */
	private String roleType;
	
	/**
	 * 角色所属组织机构id
	 */
	private String orgId;
	/**
	 * 当前页
	 */
	private String pageNow;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPageNow() {
		return pageNow;
	}
	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
}
