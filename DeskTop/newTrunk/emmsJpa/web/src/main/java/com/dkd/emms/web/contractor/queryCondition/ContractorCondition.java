package com.dkd.emms.web.contractor.queryCondition;
/**
 * 承包商查询条件
 * @author wangqian
 *
 */
public class ContractorCondition {
	/**
	 * 统一社会信用代码
	 */
	private String orgCode;
	/**
	 * 组织名称
	 */
	private String orgName;
	/**
	 * 组织类型
	 */
	private String orgType;
	/**
	 * 提交开始时间
	 */
	private String submitBeginTime;	
	/**
	 * 提交结束时间
	 */
	private String submitEndTime;
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getSubmitBeginTime() {
		return submitBeginTime;
	}
	public void setSubmitBeginTime(String submitBeginTime) {
		this.submitBeginTime = submitBeginTime;
	}
	public String getSubmitEndTime() {
		return submitEndTime;
	}
	public void setSubmitEndTime(String submitEndTime) {
		this.submitEndTime = submitEndTime;
	}
}
