/**
 * 
 */
package com.dkd.emms.web.design.materialsTableImport.queryCondition;

import java.util.List;

import com.dkd.emms.systemManage.bo.Organization;


	/**
 * @Title: DrawingNumberCondition
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月24日
 */
public class DrawingNumberCondition {
	private String drawingNumberCode;//图号（导入、唯一）
	private String drawingNumberType;//类型（设备、材料）
	private String projectId;//WBS编码ID（系统查询后，补录）
	private String designOrgId;//设计院ID（系统查询后，补录）
	private String drawingNumberState;//状态（未确认、部分确认、全部确认）
	private List<Organization> designOrgs;//设计院列表
	private String pageNow;
	public String getDrawingNumberCode() {
		return drawingNumberCode;
	}
	public void setDrawingNumberCode(String drawingNumberCode) {
		this.drawingNumberCode = drawingNumberCode;
	}
	public String getDrawingNumberType() {
		return drawingNumberType;
	}
	public void setDrawingNumberType(String drawingNumberType) {
		this.drawingNumberType = drawingNumberType;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getDesignOrgId() {
		return designOrgId;
	}
	public void setDesignOrgId(String designOrgId) {
		this.designOrgId = designOrgId;
	}
	public String getDrawingNumberState() {
		return drawingNumberState;
	}
	public void setDrawingNumberState(String drawingNumberState) {
		this.drawingNumberState = drawingNumberState;
	}
	public List<Organization> getDesignOrgs() {
		return designOrgs;
	}
	public void setDesignOrgs(List<Organization> designOrgs) {
		this.designOrgs = designOrgs;
	}
	public String getPageNow() {
		return pageNow;
	}
	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}
	
}
