package com.dkd.emms.systemManage.bo;

import java.util.List;

public class DrawingDetailedList {
	
	private List<DrawingDetailed> detailList;
	private String designOrgId;

	public List<DrawingDetailed> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<DrawingDetailed> detailList) {
		this.detailList = detailList;
	}

	public String getDesignOrgId() {
		return designOrgId;
	}

	public void setDesignOrgId(String designOrgId) {
		this.designOrgId = designOrgId;
	}
}
