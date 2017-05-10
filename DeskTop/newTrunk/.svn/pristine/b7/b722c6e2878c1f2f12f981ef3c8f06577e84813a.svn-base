package com.dkd.emms.web.design.materialsTableImport.queryCondition;

import java.util.Date;
import java.util.List;

import com.dkd.emms.systemManage.bo.MaterialsTable;
import com.dkd.emms.systemManage.bo.Organization;
import org.springframework.format.annotation.DateTimeFormat;


public class MaterialsTableCondition extends MaterialsTable {
	
	private List<Organization> designOrgs;//设计院列表
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startCreatTime;//创建时间条件开始
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endCreatTime;//创建时间条件结束

	
	public List<Organization> getDesignOrgs() {
		return designOrgs;
	}
	public void setDesignOrgs(List<Organization> designOrgs) {
		this.designOrgs = designOrgs;
	}

	public Date getStartCreatTime() {
		return startCreatTime;
	}

	public void setStartCreatTime(Date startCreatTime) {
		this.startCreatTime = startCreatTime;
	}

	public Date getEndCreatTime() {
		return endCreatTime;
	}

	public void setEndCreatTime(Date endCreatTime) {
		this.endCreatTime = endCreatTime;
	}
}
