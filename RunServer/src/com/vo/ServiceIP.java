package com.vo;

import java.util.List;

/**
 * DNS+对应有效链路的详细信息
 */

public class ServiceIP {
	
	private String DNStimeDelayed;	//DNS时延(ms)
//	private String attribution;
	
	private String ipAddr;
	private String serviceIP;	//service IP
	private List<ServiceIPInfo> serviceIPInfo;	//有效链路详细信息
	
	
	
	
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getDNStimeDelayed() {
		return DNStimeDelayed;
	}
	public void setDNStimeDelayed(String dNStimeDelayed) {
		DNStimeDelayed = dNStimeDelayed;
	}
	public String getServiceIP() {
		return serviceIP;
	}
	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}
	public List<ServiceIPInfo> getServiceIPInfo() {
		return serviceIPInfo;
	}
	public void setServiceIPInfo(List<ServiceIPInfo> serviceIPInfo) {
		this.serviceIPInfo = serviceIPInfo;
	}
	
}
