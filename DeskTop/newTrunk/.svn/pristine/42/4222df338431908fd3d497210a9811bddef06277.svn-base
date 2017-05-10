package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

import java.math.BigDecimal;


/*
 * 设计料表明细表
 * */
public class DrawingDetailed extends BaseEntity {

	private String drawingDetailedId;//设计料表明细ID（系统生成）
	private Integer drawingDetailedNo;//序号（导入，记录序号，以便后期升版时定位，排序）
	private String materialsTableId;//设计料表ID（系统生成）
	private String materialsTableCode;//料表编号
	private String designOrgId;//设计院ID（系统查询后，补录）
	private String designOrgCode;
	//设计院名称（当前用户的机构有设计院类型，不选择设计院，没有设计院类型，下拉框选择设计院）
	private String drawingNumberId;//设计料表图号主表ID（系统生成）
	private String drawingNumberCode;//图号（导入、唯一）
	private BigDecimal drawingNumberVersion;//版次（只有升版时进行更新）
	private String projectId;//WBS编码ID（系统查询后，补录）
	private String projectCodeSeq;//WBS编码（导入、唯一）
	private Integer changeNumber;//变更次数
	private String drawingDetailedState;//设计院材料状态（已匹配、未匹配）
	private String drawingDetailedType;//设计院材料类型（材料、设备）
	private String parentId;//上一级明细ID(当类型是部件时，记录关系)
	private String designId;//设计院物料编码ID
	private String materialsId;//系统编码ID
	private String designCode;//设计院物料编码（导入）
	private String designDescribe;//设计院物资描述（导入）
	private String extra1;//附加1
	private String extra2;//附加2
	private String extra3;//附加3
	private String extra4;//附加4
	private String designUnit;
	//设计院物资计量单位（中文）（导入）（匹配操作后，设计料表计量单位和系统计量单位不同时，要进行提示）
	private BigDecimal designCount;//设计数量（导入）
	private BigDecimal usedCount;//已使用设计数量（1、允许超出；2、来源（需用数量+采购计划数量）；）
	private BigDecimal overrun;//裕量
	private BigDecimal totalCount;//等于 裕量+设计数量
	private String errorMessage;//错误信息
	private String drawingNumberDeviceNo;//位号
	private String errorType;//错误信息类型  1:A,B类; 2:C类; 3.D类;
	//部件额外附加属性
	private BigDecimal unitWeight;//单位重量
	private BigDecimal totalWeight;//总重
	private String partAttributes;//部件属性(备件:sparePart，配件:parts)
	private String remark;//备注
	private String  _parentId;//treegrid
	private String materialsCode;

	public String getMaterialsCode() {
		return materialsCode;
	}

	public void setMaterialsCode(String materialsCode) {
		this.materialsCode = materialsCode;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public BigDecimal getUnitWeight() {return unitWeight;}
	public void setUnitWeight(BigDecimal unitWeight) {this.unitWeight = unitWeight;}
	public BigDecimal getTotalWeight() {return totalWeight;}
	public void setTotalWeight(BigDecimal totalWeight) {this.totalWeight = totalWeight;}
	public String getPartAttributes() {return partAttributes;}
	public void setPartAttributes(String partAttributes) {this.partAttributes = partAttributes;}
	public String getRemark() {return remark;}
	public void setRemark(String remark) {this.remark = remark;}
	public String getDrawingDetailedId() {
		return drawingDetailedId;
	}
	public void setDrawingDetailedId(String drawingDetailedId) {
		this.drawingDetailedId = drawingDetailedId;
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
	public String getDesignOrgCode() {
		return designOrgCode;
	}
	public void setDesignOrgCode(String designOrgCode) {
		this.designOrgCode = designOrgCode;
	}
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
	public String getDrawingDetailedState() {
		return drawingDetailedState;
	}
	public void setDrawingDetailedState(String drawingDetailedState) {
		this.drawingDetailedState = drawingDetailedState;
	}
	public String getDrawingDetailedType() {
		return drawingDetailedType;
	}
	public void setDrawingDetailedType(String drawingDetailedType) {
		this.drawingDetailedType = drawingDetailedType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDesignId() {
		return designId;
	}
	public void setDesignId(String designId) {
		this.designId = designId;
	}
	public String getMaterialsId() {
		return materialsId;
	}
	public void setMaterialsId(String materialsId) {
		this.materialsId = materialsId;
	}
	public String getDesignCode() {
		return designCode;
	}
	public void setDesignCode(String designCode) {
		this.designCode = designCode;
	}
	public String getDesignDescribe() {
		return designDescribe;
	}
	public void setDesignDescribe(String designDescribe) {
		this.designDescribe = designDescribe;
	}
	public String getExtra1() {
		return extra1;
	}
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}
	public String getExtra2() {
		return extra2;
	}
	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}
	public String getExtra3() {
		return extra3;
	}
	public void setExtra3(String extra3) {
		this.extra3 = extra3;
	}
	public String getExtra4() {
		return extra4;
	}
	public void setExtra4(String extra4) {
		this.extra4 = extra4;
	}
	public String getDesignUnit() {
		return designUnit;
	}
	public void setDesignUnit(String designUnit) {
		this.designUnit = designUnit;
	}
	public BigDecimal getOverrun() {
		return overrun;
	}
	public void setOverrun(BigDecimal overrun) {
		this.overrun = overrun;
	}
	public BigDecimal getDesignCount() {
		return designCount;
	}
	public void setDesignCount(BigDecimal designCount) {
		this.designCount = designCount;
	}
	public BigDecimal getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(BigDecimal usedCount) {
		this.usedCount = usedCount;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getDrawingNumberDeviceNo() {
		return drawingNumberDeviceNo;
	}
	public void setDrawingNumberDeviceNo(String drawingNumberDeviceNo) {this.drawingNumberDeviceNo = drawingNumberDeviceNo;}
	public BigDecimal getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public Integer getDrawingDetailedNo() {
		return drawingDetailedNo;
	}
	public void setDrawingDetailedNo(Integer drawingDetailedNo) {
		this.drawingDetailedNo = drawingDetailedNo;
	}

	public BigDecimal getDrawingNumberVersion() {
		return drawingNumberVersion;
	}

	public void setDrawingNumberVersion(BigDecimal drawingNumberVersion) {
		this.drawingNumberVersion = drawingNumberVersion;
	}

	public Integer getChangeNumber() {
		return changeNumber;
	}
	public void setChangeNumber(Integer changeNumber) {
		this.changeNumber = changeNumber;
	}
	
}
