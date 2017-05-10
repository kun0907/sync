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
	private String userAgent;
	
	
	
	
	
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
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
		if(serviceIPInfo!=null&&serviceIPInfo.size()>0){
			  for (ServiceIPInfo sii : serviceIPInfo) {
				   if(sii.getUserAgent()!=null){
					      this.userAgent=sii.getUserAgent();
					      break;
				   }
			   }
		}
	}
	
}
