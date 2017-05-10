/**
 * 
 */
package com.dkd.emms.web.design.materialsTableImport.queryCondition;


import com.dkd.emms.systemManage.bo.DrawingDetailed;

/**
 * @Title: DrawingDetailedCondition
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月25日
 */
public class DrawingDetailedCondition extends DrawingDetailed {
	private String materialsTableId;//设计料表ID（系统生成）
	private String drawingNumberCode;//图号（导入、唯一）
	
	public String getMaterialsTableId() {
		return materialsTableId;
	}
	public void setMaterialsTableId(String materialsTableId) {
		this.materialsTableId = materialsTableId;
	}
	public String getDrawingNumberCode() {
		return drawingNumberCode;
	}
	public void setDrawingNumberCode(String drawingNumberCode) {
		this.drawingNumberCode = drawingNumberCode;
	}
	
}
