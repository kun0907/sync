package com.vo;

import java.util.List;

public class CompareInfo {

	private String smallPacketPercent;
	private String mediumPacketPercent;
	private String largePacketPercent;
	private String packetCount;
	private String ipCountRes;
	private String exchangeFlowCount;
	private String tcpCountRes;
	private String avgDnsDelsyTs;
	private String avgTcpTimeDelayed;
	private String avgTimeToFirstByte;
	private String avgOffTimeDelayed;
	private String avgRttTime;
	private String avgExchangeTime;
	private String exchangeTimeCount;
	private String pcakageCompareMsg;
	
	private String percentDiff_avgExchangeTime;
	
	private int tcpCount;
	private String fileName;
	
	private String tcpCountPercent;
	private String tcpCountCompareMsg;
	private String timeEfficiencyAVG;
	
	private String lowestEffLink;
	private String timeEfficiencyAVGNum;
	private String exchangeTimeCountNum;
	private String exchangeFlowCountNum;
	private String avgDnsDelsyTsNum;
	private String avgTcpTimeDelayedNum;
	private String avgTimeToFirstByteNum;
	private String avgOffTimeDelayedNum;
	private String avgRttTimeNum;
	private String avgExchangeTimeNum;
	private String compareRes;
	
	
	private List  wholeRes;
	
	
	
	
	
	
	public String getCompareRes() {
		return compareRes;
	}
	public void setCompareRes(String compareRes) {
		this.compareRes = compareRes;
	}
	public String getAvgTimeToFirstByteNum() {
		return avgTimeToFirstByteNum;
	}
	public void setAvgTimeToFirstByteNum(String avgTimeToFirstByteNum) {
		this.avgTimeToFirstByteNum = avgTimeToFirstByteNum;
	}
	public String getAvgOffTimeDelayedNum() {
		return avgOffTimeDelayedNum;
	}
	public void setAvgOffTimeDelayedNum(String avgOffTimeDelayedNum) {
		this.avgOffTimeDelayedNum = avgOffTimeDelayedNum;
	}
	public String getAvgRttTimeNum() {
		return avgRttTimeNum;
	}
	public void setAvgRttTimeNum(String avgRttTimeNum) {
		this.avgRttTimeNum = avgRttTimeNum;
	}
	public String getAvgExchangeTimeNum() {
		return avgExchangeTimeNum;
	}
	public void setAvgExchangeTimeNum(String avgExchangeTimeNum) {
		this.avgExchangeTimeNum = avgExchangeTimeNum;
	}
	public String getAvgTcpTimeDelayedNum() {
		return avgTcpTimeDelayedNum;
	}
	public void setAvgTcpTimeDelayedNum(String avgTcpTimeDelayedNum) {
		this.avgTcpTimeDelayedNum = avgTcpTimeDelayedNum;
	}
	public String getAvgDnsDelsyTsNum() {
		return avgDnsDelsyTsNum;
	}
	public void setAvgDnsDelsyTsNum(String avgDnsDelsyTsNum) {
		this.avgDnsDelsyTsNum = avgDnsDelsyTsNum;
	}
	public List getWholeRes() {
		return wholeRes;
	}
	public void setWholeRes(List wholeRes) {
		this.wholeRes = wholeRes;
	}
	public String getExchangeFlowCountNum() {
		return exchangeFlowCountNum;
	}
	public void setExchangeFlowCountNum(String exchangeFlowCountNum) {
		this.exchangeFlowCountNum = exchangeFlowCountNum;
	}
	public String getExchangeTimeCountNum() {
		return exchangeTimeCountNum;
	}
	public void setExchangeTimeCountNum(String exchangeTimeCountNum) {
		this.exchangeTimeCountNum = exchangeTimeCountNum;
	}
	public String getTimeEfficiencyAVGNum() {
		return timeEfficiencyAVGNum;
	}
	public void setTimeEfficiencyAVGNum(String timeEfficiencyAVGNum) {
		this.timeEfficiencyAVGNum = timeEfficiencyAVGNum;
	}
	public String getLowestEffLink() {
		return lowestEffLink;
	}
	public void setLowestEffLink(String lowestEffLink) {
		this.lowestEffLink = lowestEffLink;
	}
	public String getTimeEfficiencyAVG() {
		return timeEfficiencyAVG;
	}
	public void setTimeEfficiencyAVG(String timeEfficiencyAVG) {
		this.timeEfficiencyAVG = timeEfficiencyAVG;
	}
	public String getTcpCountCompareMsg() {
		return tcpCountCompareMsg;
	}
	public void setTcpCountCompareMsg(String tcpCountCompareMsg) {
		this.tcpCountCompareMsg = tcpCountCompareMsg;
	}
	public String getTcpCountPercent() {
		return tcpCountPercent;
	}
	public void setTcpCountPercent(String tcpCountPercent) {
		this.tcpCountPercent = tcpCountPercent;
	}
	public int getTcpCount() {
		return tcpCount;
	}
	public void setTcpCount(int tcpCount) {
		this.tcpCount = tcpCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPercentDiff_avgExchangeTime() {
		return percentDiff_avgExchangeTime;
	}
	public void setPercentDiff_avgExchangeTime(String percentDiff_avgExchangeTime) {
		this.percentDiff_avgExchangeTime = percentDiff_avgExchangeTime;
	}
	public String getPcakageCompareMsg() {
		return pcakageCompareMsg;
	}
	public void setPcakageCompareMsg(String pcakageCompareMsg) {
		this.pcakageCompareMsg = pcakageCompareMsg;
	}
	public String getSmallPacketPercent() {
		return smallPacketPercent;
	}
	public void setSmallPacketPercent(String smallPacketPercent) {
		this.smallPacketPercent = smallPacketPercent;
	}
	public String getMediumPacketPercent() {
		return mediumPacketPercent;
	}
	public void setMediumPacketPercent(String mediumPacketPercent) {
		this.mediumPacketPercent = mediumPacketPercent;
	}
	public String getLargePacketPercent() {
		return largePacketPercent;
	}
	public void setLargePacketPercent(String largePacketPercent) {
		this.largePacketPercent = largePacketPercent;
	}
	public String getPacketCount() {
		return packetCount;
	}
	public void setPacketCount(String packetCount) {
		this.packetCount = packetCount;
	}
	public String getIpCountRes() {
		return ipCountRes;
	}
	public void setIpCountRes(String ipCountRes) {
		this.ipCountRes = ipCountRes;
	}
	public String getExchangeFlowCount() {
		return exchangeFlowCount;
	}
	public void setExchangeFlowCount(String exchangeFlowCount) {
		this.exchangeFlowCount = exchangeFlowCount;
	}
	public String getTcpCountRes() {
		return tcpCountRes;
	}
	public void setTcpCountRes(String tcpCountRes) {
		this.tcpCountRes = tcpCountRes;
	}
	public String getAvgDnsDelsyTs() {
		return avgDnsDelsyTs;
	}
	public void setAvgDnsDelsyTs(String avgDnsDelsyTs) {
		this.avgDnsDelsyTs = avgDnsDelsyTs;
	}
	public String getAvgTcpTimeDelayed() {
		return avgTcpTimeDelayed;
	}
	public void setAvgTcpTimeDelayed(String avgTcpTimeDelayed) {
		this.avgTcpTimeDelayed = avgTcpTimeDelayed;
	}
	public String getAvgTimeToFirstByte() {
		return avgTimeToFirstByte;
	}
	public void setAvgTimeToFirstByte(String avgTimeToFirstByte) {
		this.avgTimeToFirstByte = avgTimeToFirstByte;
	}
	public String getAvgOffTimeDelayed() {
		return avgOffTimeDelayed;
	}
	public void setAvgOffTimeDelayed(String avgOffTimeDelayed) {
		this.avgOffTimeDelayed = avgOffTimeDelayed;
	}
	public String getAvgRttTime() {
		return avgRttTime;
	}
	public void setAvgRttTime(String avgRttTime) {
		this.avgRttTime = avgRttTime;
	}
	public String getAvgExchangeTime() {
		return avgExchangeTime;
	}
	public void setAvgExchangeTime(String avgExchangeTime) {
		this.avgExchangeTime = avgExchangeTime;
	}
	public String getExchangeTimeCount() {
		return exchangeTimeCount;
	}
	public void setExchangeTimeCount(String exchangeTimeCount) {
		this.exchangeTimeCount = exchangeTimeCount;
	}
	
	
	
	
	
	
}
