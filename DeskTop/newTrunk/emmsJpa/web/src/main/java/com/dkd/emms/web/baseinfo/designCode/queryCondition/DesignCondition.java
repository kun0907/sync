/**
 * 
 */
package com.dkd.emms.web.baseinfo.designCode.queryCondition;

import java.util.List;

import com.dkd.emms.systemManage.bo.Design;
import com.dkd.emms.systemManage.bo.Organization;

/**
	 * @Title: DesignCondition
	 * @Description:
	 * @param 
	 * @author:YUZH 
	 * @data 2017年2月21日
	 */
public class DesignCondition extends Design {
	private List<Organization> designList;

	public List<Organization> getDesignList() {
		return designList;
	}

	public void setDesignList(List<Organization> designList) {
		this.designList = designList;
	}
	
}
