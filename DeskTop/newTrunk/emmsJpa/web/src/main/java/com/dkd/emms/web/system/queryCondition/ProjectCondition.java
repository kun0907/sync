package com.dkd.emms.web.system.queryCondition;
/**
 * 项目查询条件
 * @author WANGQ
 *
 */
public class ProjectCondition {

	/**
	 * 项目Code
	 */
	private String projectCode;
	/**
	 * 项目Name
	 */
	private String projectName;
	/**
	 * 是否是主项目
	 */
	private String isMain;
	/**
	 * 当前页
	 */
	private String pageNow;
	/**
	 * 父节点
	 */
	private String parentId;
	/**
	 * 状态
	 */
	private String projectState;
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public String getPageNow() {
		return pageNow;
	}
	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getProjectState() {
		return projectState;
	}
	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}
	
}
