/**
 * 
 */
package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: MaterialsTable
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月24日
 */
public class MaterialsTable extends BaseEntity{
	
	private String materialsTableId;//设计料表ID（系统生成）
	private String materialsTableCode;//料表编号
	private String designOrgId;//设计院ID（系统查询后，补录）
	private String designOrgName;//设计院名称（当前用户的机构有设计院类型，不选择设计院，没有设计院类型，下拉框选择设计院）
	private String materialsTableState;//状态（校验通过、校验不通过、已生成图号）
	private String materialsTableType;//类型（设备、材料）
	private String isMatch;//是否同步（1.是，2.否）
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date createTime ;

	public String getIsMatch() {
		return isMatch;
	}
	public void setIsMatch(String isMatch) {
		this.isMatch = isMatch;
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
	public String getMaterialsTableState() {
		return materialsTableState;
	}
	public void setMaterialsTableState(String materialsTableState) {
		this.materialsTableState = materialsTableState;
	}
	public String getMaterialsTableType() {
		return materialsTableType;
	}
	public void setMaterialsTableType(String materialsTableType) {
		this.materialsTableType = materialsTableType;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
