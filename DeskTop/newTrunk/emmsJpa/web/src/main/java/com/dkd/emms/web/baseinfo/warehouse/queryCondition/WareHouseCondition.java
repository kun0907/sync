/**
 * 
 */
package com.dkd.emms.web.baseinfo.warehouse.queryCondition;

import java.util.List;

import com.dkd.emms.core.entity.BaseEntity;

	/**
 * @Title: WareHouseCondition
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年2月7日
 */
public class WareHouseCondition extends BaseEntity{
	private String warehouseCode;//仓库编码
	private String warehouseName;//仓库名称
	private String warehouseType;//仓库类型
	private String warehouseState;//状态
	private List<String> warehouseIds;
	
	public List<String> getWarehouseIds() {
		return warehouseIds;
	}
	public void setWarehouseIds(List<String> warehouseIds) {
		this.warehouseIds = warehouseIds;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseType() {
		return warehouseType;
	}
	public void setWarehouseType(String warehouseType) {
		this.warehouseType = warehouseType;
	}
	public String getWarehouseState() {
		return warehouseState;
	}
	public void setWarehouseState(String warehouseState) {
		this.warehouseState = warehouseState;
	}
	
}
