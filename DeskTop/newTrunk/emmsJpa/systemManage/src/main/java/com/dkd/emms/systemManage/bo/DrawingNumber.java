/**
 * 
 */
package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

import java.util.Date;

/**
 * @Title: DrawingNumber
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月24日
 */
public class DrawingNumber extends BaseEntity{
	private String drawingNumberId;//设计料表图号主表ID（系统生成）
	private String drawingNumberCode;//图号（导入、唯一）
	private String drawingNumberVersion;//版次（只有升版时进行更新）
	private String projectId;//WBS编码ID（系统查询后，补录）
	private String projectCodeSeq;//WBS编码（导入、唯一）
	private String materialsTableId;//设计料表ID（系统生成）
	
	private String materialsTableCode;//料表编号
	private String designOrgId;//设计院ID（系统查询后，补录）
	private String designOrgName;//设计院名称（当前用户的机构有设计院类型，不选择设计院，没有设计院类型，下拉框选择设计院）
	private String drawingNumberDeviceNo;//位号
	private String drawingNumberState;//状态（未确认、部分确认、全部确认）
	private String drawingNumberType;//类型（设备、材料）
	
	private String confirmUserId;//确认人
	private String confirmUserName;//确认人名称
	private Date confirmTime;//确认时间
	public String getDrawingNumberId() {
		return drawingNumberId;
	}
	public void setDrawingNumberId(String drawingNumberId) {
		this.drawingNumberId = drawingNumberId;
	}
	public String getDrawingNumberCode() {
		return drawingNumberCode;
	}
	public void setDrawingNumberCode(String drawingNumberCode) {
		this.drawingNumberCode = drawingNumberCode;
	}
	public String getDrawingNumberVersion() {
		return drawingNumberVersion;
	}
	public void setDrawingNumberVersion(String drawingNumberVersion) {
		this.drawingNumberVersion = drawingNumberVersion;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectCodeSeq() {
		return projectCodeSeq;
	}
	public void setProjectCodeSeq(String projectCodeSeq) {
		this.projectCodeSeq = projectCodeSeq;
	}
	public String getMaterialsTableId() {
		return materialsTableId;
	}
	public void setMaterialsTableId(String materialsTableId) {
		this.materialsTableId = materialsTableId;
	}
	public String getMaterialsTableCode() {
		return materialsTableCode;
	}
	public void setMaterialsTableCode(String materialsTableCode) {
		this.materialsTableCode = materialsTableCode;
	}
	
	public String getDesignOrgId() {
		return designOrgId;
	}
	public void setDesignOrgId(String designOrgId) {
		this.designOrgId = designOrgId;
	}
	
	public String getDesignOrgName() {
		return designOrgName;
	}
	public void setDesignOrgName(String designOrgName) {
		this.designOrgName = designOrgName;
	}
	public String getDrawingNumberDeviceNo() {
		return drawingNumberDeviceNo;
	}
	public void setDrawingNumberDeviceNo(String drawingNumberDeviceNo) {
		this.drawingNumberDeviceNo = drawingNumberDeviceNo;
	}
	public String getDrawingNumberState() {
		return drawingNumberState;
	}
	public void setDrawingNumberState(String drawingNumberState) {
		this.drawingNumberState = drawingNumberState;
	}
	public String getDrawingNumberType() {
		return drawingNumberType;
	}
	public void setDrawingNumberType(String drawingNumberType) {
		this.drawingNumberType = drawingNumberType;
	}
	public String getConfirmUserId() {
		return confirmUserId;
	}
	public void setConfirmUserId(String confirmUserId) {
		this.confirmUserId = confirmUserId;
	}
	public String getConfirmUserName() {
		return confirmUserName;
	}
	public void setConfirmUserName(String confirmUserName) {
		this.confirmUserName = confirmUserName;
	}
	public Date getConfirmTime() {return confirmTime;}
	public void setConfirmTime(Date confirmTime) {this.confirmTime = confirmTime;}
}
