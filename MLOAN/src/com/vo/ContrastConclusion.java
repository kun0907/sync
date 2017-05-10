package com.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhankun on 2017/4/9.
 */
public class ContrastConclusion {
    private String title;
    private String lowestEffLink;
    private String effLinkTitle;
    private String exchangeTimeTitle;
    private String exchangeTimeSecondTitle;
    private String avgDnsDelsyTs;
    private String tcpTimeDelayed;
    private String dnsBigIp;
    private String timeToFirst;
    private String offTime;
    private String rttTime;
    private String tcpCount;
    private String packetLenData;

    List<List<String>> table1;
    List<List<String>> table2;
    public List<List<String>> getTable1() {
        return table1;
    }
    public void setTable1(List<List<String>> table1) {
        this.table1 = table1;
    }
    public List<List<String>> getTable2() {
        return table2;
    }
    public void setTable2(List<List<String>> table2) {
        this.table2 = table2;
    }
    public List<List<String>> getTable3() {
        return table3;
    }
    public void setTable3(List<List<String>> table3) {
        this.table3 = table3;
    }
    List<List<String>> table3;


    public String getPacketLenData() {
        return packetLenData;
    }
    public void setPacketLenData(String packetLenData) {
        this.packetLenData = packetLenData;
    }
    public String getTcpCount() {
        return tcpCount;
    }
    public void setTcpCount(String tcpCount) {
        this.tcpCount = tcpCount;
    }
    public String getOffTime() {
        return offTime;
    }
    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }
    public String getRttTime() {
        return rttTime;
    }
    public void setRttTime(String rttTime) {
        this.rttTime = rttTime;
    }
    public String getTimeToFirst() {
        return timeToFirst;
    }
    public void setTimeToFirst(String timeToFirst) {
        this.timeToFirst = timeToFirst;
    }
    public String getDnsBigIp() {
        return dnsBigIp;
    }
    public void setDnsBigIp(String dnsBigIp) {
        this.dnsBigIp = dnsBigIp;
    }
    public String getTcpTimeDelayed() {
        return tcpTimeDelayed;
    }
    public void setTcpTimeDelayed(String tcpTimeDelayed) {
        this.tcpTimeDelayed = tcpTimeDelayed;
    }
    private String avgTimeToFirstByte;
    private String avgOffTimeDelayed;
    private String avgRttTime;
    private String avgExchangeTime;


    public String getExchangeTimeTitle() {
        return exchangeTimeTitle;
    }
    public void setExchangeTimeTitle(String exchangeTimeTitle) {
        this.exchangeTimeTitle = exchangeTimeTitle;
    }
    public String getExchangeTimeSecondTitle() {
        return exchangeTimeSecondTitle;
    }
    public void setExchangeTimeSecondTitle(String exchangeTimeSecondTitle) {
        this.exchangeTimeSecondTitle = exchangeTimeSecondTitle;
    }
    public String getAvgDnsDelsyTs() {
        return avgDnsDelsyTs;
    }
    public void setAvgDnsDelsyTs(String avgDnsDelsyTs) {
        this.avgDnsDelsyTs = avgDnsDelsyTs;
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
    public String getLowestEffLink() {
        return lowestEffLink;
    }
    public void setLowestEffLink(String lowestEffLink) {
        this.lowestEffLink = lowestEffLink;
    }public String getEffLinkTitle() {
        return effLinkTitle;
    }
    public void setEffLinkTitle(String effLinkTitle) {
        this.effLinkTitle = effLinkTitle;
    }public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
    String title1;  //yknorm 业务 可能存在     链路效率低 、  耗时长 的问题。
    String title2;  // 这些问题导致交互时间长。
    String title3;  //yknorm 业务 效率 劣于 ykdelay。 
    String title3_1;//	       	链路效率低： 211.136.65.167 端口号52922 可能存在问题，请深度分析。
    String title3_2;//	       	耗时长：yknorm业务在 DNS时延、建链后首包时延、RTT时间、交互时间 方面可能存在问题，请深度分析。 
List<String> list_title3_2=new ArrayList<String>(); /*链路IP 111.13.82.165 的 DNS时延 指标差，建议深度分析。
													      链路IP 211.136.65.167 端口号 52922 的 建链后首包时延 指标差，建议深度分析。
													      链路IP 117.185.16.116 端口号 43614 的 平均RTT时间 指标差，建议深度分析。*/
List<List<CellData>> table_title3 =new ArrayList<>();//表格"业务名	平均DNS时延(ms)	平均TCP建链时延(ms)	平均建链后首包时延(ms)	平均断链时延(ms)	平均RTT时间(ms)	平均交互时间(ms)"

 
    String title3_3;//	   		小包多："+filename+"业务 （大/中）包比例偏低，建议深度分析 
    List<List<String>> table_title3_3 =new ArrayList<>();//小包、中包、打包比例表格
    String title3_4;//	     	链路数多：yknorm 业务IP链路（24）条，高于竞品，请深度分析。  
    List<List<CellData>> table_title3_4 =new ArrayList<>();//问题“链路数多”下面的表格
    
    int advivcNum=0;//基础的和竞品的比较时产生的基础的问题个数(也即建议数)
    
    
    
    
    
	public int getAdvivcNum() {
		return advivcNum;
	}
	public void setAdvivcNum(int advivcNum) {
		this.advivcNum = advivcNum;
	}
	public List<List<CellData>> getTable_title3() {
		return table_title3;
	}
	public void setTable_title3(List<List<CellData>> table_title3) {
		this.table_title3 = table_title3;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getTitle3_1() {
		return title3_1;
	}
	public void setTitle3_1(String title3_1) {
		this.title3_1 = title3_1;
	}
	public String getTitle3_2() {
		return title3_2;
	}
	public void setTitle3_2(String title3_2) {
		this.title3_2 = title3_2;
	}
	public String getTitle3_3() {
		return title3_3;
	}
	public void setTitle3_3(String title3_3) {
		this.title3_3 = title3_3;
	}
	public String getTitle3_4() {
		return title3_4;
	}
	public void setTitle3_4(String title3_4) {
		this.title3_4 = title3_4;
	}
	public List<String> getList_title3_2() {
		return list_title3_2;
	}
	public void setList_title3_2(List<String> list_title3_2) {
		this.list_title3_2 = list_title3_2;
	}
	public List<List<String>> getTable_title3_3() {
		return table_title3_3;
	}
	public void setTable_title3_3(List<List<String>> table_title3_3) {
		this.table_title3_3 = table_title3_3;
	}
	public List<List<CellData>> getTable_title3_4() {
		return table_title3_4;
	}
	public void setTable_title3_4(List<List<CellData>> table_title3_4) {
		this.table_title3_4 = table_title3_4;
	}
    
	
	
    
    
}
