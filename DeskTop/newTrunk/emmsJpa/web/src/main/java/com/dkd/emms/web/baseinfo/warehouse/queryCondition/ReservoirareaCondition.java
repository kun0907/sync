/**
 * 
 */
package com.dkd.emms.web.baseinfo.warehouse.queryCondition;

import com.dkd.emms.core.entity.BaseEntity;

	/**
 * @Title: ReservoirareaCondition
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年2月8日
 */
public class ReservoirareaCondition extends BaseEntity{
	private String warehouseId;//仓库ID

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
}
