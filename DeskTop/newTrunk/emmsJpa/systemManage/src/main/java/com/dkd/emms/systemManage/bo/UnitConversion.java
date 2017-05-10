package com.dkd.emms.systemManage.bo;

import java.math.BigDecimal;

import com.dkd.emms.core.entity.BaseEntity;



public class UnitConversion extends BaseEntity {

	private String unitConversionId;//id
	private String unitCodeMain;//主计量单位编码
	private String unitCodeSecondary;//辅计量单位编码
	private BigDecimal conversion;//换算率
	
	

	public String getUnitCodeMain() {
		return unitCodeMain;
	}
	public void setUnitCodeMain(String unitCodeMain) {
		this.unitCodeMain = unitCodeMain;
	}
	public String getUnitCodeSecondary() {
		return unitCodeSecondary;
	}
	public void setUnitCodeSecondary(String unitCodeSecondary) {
		this.unitCodeSecondary = unitCodeSecondary;
	}
	public BigDecimal getConversion() {
		return conversion;
	}
	public void setConversion(BigDecimal conversion) {
		this.conversion = conversion;
	}

	public String getUnitConversionId() {
		return unitConversionId;
	}

	public void setUnitConversionId(String unitConversionId) {
		this.unitConversionId = unitConversionId;
	}
}
