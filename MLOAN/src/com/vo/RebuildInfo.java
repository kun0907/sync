package com.vo;

/**
 *@description 频繁拆建链相关信息
 */

public class RebuildInfo {
	
	private int repeatCount;	//拆建链的数目
	private String minLastTimeDelayed;	//最短键连间隔
	private String avgLastTimeDelayed;	//平均建立时间
	private String serviceIp;	//service Ip
	private String host;	//域名解析地址
//	private String portAs;
	public int getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	public String getMinLastTimeDelayed() {
		return minLastTimeDelayed;
	}
	public void setMinLastTimeDelayed(String minLastTimeDelayed) {
		this.minLastTimeDelayed = minLastTimeDelayed;
	}
	public String getAvgLastTimeDelayed() {
		return avgLastTimeDelayed;
	}
	public void setAvgLastTimeDelayed(String avgLastTimeDelayed) {
		this.avgLastTimeDelayed = avgLastTimeDelayed;
	}
	
	public String getServiceIp() {
		return serviceIp;
	}
	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}
