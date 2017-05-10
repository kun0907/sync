package com.dkd.emms.web.system.queryCondition;
/**
 * 人员查询Class类
 * @author WANGQ
 *
 */
public class EmployeeCondition {
	/**
	 * 人员工号
	 */
	private String empCode;
	/**
	 * 人员姓名
	 */
	private String empName;
	/**
	 * 组织机构id
	 */
	private String orgId;
	
	/**
	 * 组织机构Name
	 */
	private String orgName;
	
	/**
	 * 当前页
	 */
	private String pageNow;
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
