package com.vo;

/**
 *@description 系统信息
 */
public class SysInfo {
	
	private String userName;	//用户名
	private String startTime;	//登录时间
	private String businessType;	//业务类型
	private String version;	//版本号
//	private String terminalBrand;	//终端型号
//	private String terminalModel;	//终端名称
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
