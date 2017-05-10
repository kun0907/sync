package com.util.base;

import com.alibaba.fastjson.JSON;
import com.constant.GraphTypeConfig;
import com.constant.RedisStorageConfig;
import com.util.StringUtil;
import com.vo.ServiceIP;
import com.vo.ServiceIPInfo;
import org.apache.log4j.Logger;
import redis.clients.jedis.ShardedJedis;
import resolve.analysis.DNSStream;
import resolve.analysis.HTTP;
import resolve.analysis.Result;
import resolve.analysis.TCPStream;
import resolve.decoder.Packet;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 文件 解析结果
 */
public class AnalyzeResult extends Result{

	public AnalyzeResult(Packet[] packets, double logEndTs) {
		super(packets,logEndTs);
	}
	public static Logger log = Logger.getLogger(AnalyzeResult.class);
	
	private Double logFileData1=null;
	private Double logFileData9=null;
	private Map<String,Integer> mapIpFlow = new HashMap<String, Integer>();      //IP流量分布图
	private Map<String, Map<String, Object>> mapDnsIP = new HashMap<String, Map<String, Object>>();
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> tcp(int ipId, String fileCur, String username, ShardedJedis jedis) throws IOException {
		
		log.info("日志分析........    "+fileCur);
		dns();
		
		Map<String, Object> tcpIPs = new HashMap<String, Object>();
		Map<String, Object> tcpGraph = new HashMap<String, Object>();
		List<List<Integer>> ethLen = new ArrayList<List<Integer>>();
		List<String> others = new ArrayList<>();
		List<String> othersIp = new ArrayList<>();
		Map<Integer, Integer> mapRate = new HashMap<>(); //吞吐流量
		Map<Integer, Integer> mapRate1 = new HashMap<>(); //吞吐流量
		
		Map<String, Object> tcpInfos = new HashMap<String, Object>();
		double avgRtts = 0;
		
		List<ServiceIP> serviceIPs = new ArrayList<ServiceIP>();
		List<ServiceIPInfo> serviceIPInfos;
		ServiceIPInfo serviceIPInfo;
		NumberFormat df = new DecimalFormat("0.00");
		Double delay = 0.0;
		
		double fillData1Ts = 0;
		double fillData9Ts = 0; 
		double firstFinUpTime = 0; 
		double lastFinUpTime = 0; 
		double time1Ts = 0;
		double eachTs = 0;
		double close33Time = 0;
		double setup1Ts = 0;   //第一次握手时间
		double setup3Ts = 0;   //第三次握手时间
		double startTs = 0;    //请求 	开始时间  (单位s)
		double endTs = 0;    //请求 	结束时间  (单位s)
		double t1 = 0; 
		double t2 = 0; 
		int retranNum = 0;
		int packetCnt = 0;  // 包数
		String host = "";
		List<HTTP> linkHttpInfo = null;
		int i=0;
		List<String> ipAddrIds = new ArrayList<String>();
		String ipAddrId;
		String xchangeFlow;
		String permissableParameter = "";//可计算的参数.

		for (TCPStream tcp : tcpStreams) {
			
			ServiceIP serviceIP = new ServiceIP();
			
			String addB = tcp.getAddB().toString().substring(1);	//	Server IP
//			permissableParameter = isContaisParameter(permissableParameter, "Server IP");
			int portA = tcp.getPortA();
			permissableParameter = isContaisParameter(permissableParameter, "服务器IP归属");
			permissableParameter = isContaisParameter(permissableParameter, "链路数");
			lastFinUpTime = tcp.getLastFinUpTime();
			time1Ts = tcp.getTime1Ts();
			eachTs = lastFinUpTime - time1Ts;
			xchangeFlow = String.valueOf(tcp.getAllByteLen()-tcp.getHandsEachByteLen()-tcp.getEndAllLen());
			if(tcp.getPayloadLength()>0 && !tcp.isFirstFin() && eachTs>0){
				if(mapIpFlow.containsKey(addB))
					mapIpFlow.put(addB,mapIpFlow.get(addB)+(int)tcp.getAllByteLen());
				else
					mapIpFlow.put(addB,(int)tcp.getAllByteLen());
			}else{
				if(mapIpFlow.containsKey("other")){
					mapIpFlow.put("other", mapIpFlow.get("other")+(int)tcp.getAllByteLen());
				}else{
					mapIpFlow.put("other", (int)tcp.getAllByteLen());
				}
				if (othersIp == null || othersIp.size() ==0) {
					othersIp.add(addB);
				} else {
					for (int j=0; j<othersIp.size(); j++) {
						if ((othersIp.get(j)).equals(addB)) {
							break;
						}
					}
					othersIp.add(addB);
				}
				others.add(addB+"("+portA+")");
				permissableParameter = isContaisParameter(permissableParameter, "无效IP");
				continue;
			}
			Map<String, Object> tcpIP = new HashMap<String, Object>();
			Map<String, String> tcpInfo = new HashMap<String, String>();
			
			fillData1Ts = tcp.getFillData1Ts();
			fillData9Ts = tcp.getFillData9Ts(); 
			firstFinUpTime = tcp.getFirstFinUpTime(); 
			close33Time = tcp.getClose33Time();
			setup1Ts = tcp.getSetup1Ts();   //第一次握手时间
			setup3Ts = tcp.getSetup3Ts();   //第三次握手时间
			startTs = tcp.getStartTs();    //请求 	开始时间  (单位s)
			endTs = tcp.getEndTs();    //请求 	结束时间  (单位s)
			t1 = tcp.getT1();
			t2 = tcp.getT2();
			retranNum = tcp.getRetranNum();
			packetCnt = tcp.getPacketCnt();  // 包数
			host = tcp.getHost();
			linkHttpInfo = tcp.getLinkHttpInfo();

			if(!linkHttpInfo.isEmpty() && fillData1Ts>0){
				if(logFileData1 == null || logFileData1 >fillData1Ts)
					logFileData1 = fillData1Ts;
			}else if(linkHttpInfo.isEmpty()){
				if(logFileData1 == null || logFileData1 >fillData1Ts)
					logFileData1 = fillData1Ts;
			}
				
			if(logFileData9 == null || logFileData9  <fillData9Ts)
				logFileData9 = fillData9Ts;
			
			serviceIPInfo = new ServiceIPInfo();
			
			ethLen.add(tcp.getTcpEthLen());	// TCP包长集合
			avgRtts = avgRtts(tcp);
			double eff_time = -1;
			if(tcp.isHasSyn() && fillData1Ts>0){
				if(fillData9Ts>firstFinUpTime)
					eff_time = (fillData9Ts-fillData1Ts)/(tcp.endTs-tcp.startTs);
				else
					eff_time = (firstFinUpTime-fillData1Ts)/(tcp.endTs-tcp.startTs);
			}
			if(tcpInfos.containsKey(addB)){
				
				tcpIP = (Map<String, Object>) tcpInfos.get(addB);
				Map<String, Object> tcpInfoUp = (Map<String, Object>)tcpIP.get(String.valueOf(tcpIP.size()-1));
				
//				serviceIPInfo.setVideo(videoType(tcp.getContentType()));
				serviceIPInfo.setServerIP(addB);
				serviceIPInfo.setPorts(portA);
				serviceIPInfo.setPacketCount(packetCnt);
				serviceIPInfo.setFileDataTimes(fillData9Ts - fillData1Ts);
				serviceIPInfo.setKeepTimes(endTs - startTs);
//				serviceIPInfo.setHost(host);
				serviceIPInfo.setLinkHttpHeader(tcp.getLinkHttpHeader());
				serviceIPInfo.setKind(tcp.getKind());
				serviceIPInfo.setHasHttpInfo(!linkHttpInfo.isEmpty());
				serviceIPInfo.setT1(Double.parseDouble(df.format(t1*1000)));
				serviceIPInfo.setT2(Double.parseDouble(df.format(t2*1000)));
				tcpInfo.put("端口号", String.valueOf(portA));
				tcpInfo.put("包数", String.valueOf(packetCnt));
				permissableParameter = isContaisParameter(permissableParameter,"包数");
				double dnsStartTs = 0;
				double dnsEndTs = 0;
				if(mapDnsIP.containsKey(addB)){
					dnsStartTs = (double) mapDnsIP.get(addB).get("startTs");
					dnsEndTs = (double) mapDnsIP.get(addB).get("endTs");
				}
				tcpInfo.put("dnsStartTs", String.valueOf(dnsStartTs));
				tcpInfo.put("dnsEndTs", String.valueOf(dnsEndTs));
				tcpInfo.put("startTs", String.valueOf(startTs));
				tcpInfo.put("endTs", String.valueOf(endTs));
				tcpInfo.put("fillData1Ts", String.valueOf(fillData1Ts));
				tcpInfo.put("fillData9Ts", String.valueOf(fillData9Ts));
				
				delay = startTs-Double.parseDouble(tcpInfoUp.get("UPendTs").toString());
				if(fillData9Ts>firstFinUpTime)
					tcpInfo.put("UPendTs", String.valueOf(fillData9Ts));
				else
					tcpInfo.put("UPendTs", String.valueOf(firstFinUpTime));
				if(delay>0) {
					serviceIPInfo.setLastRelationship("串行");	//与上条链路串并行关系
				}
				else {
					serviceIPInfo.setLastRelationship("并行");
				}
				if (delay != null && !delay.equals("")) {
					permissableParameter = isContaisParameter(permissableParameter, "与上条链路串并行关系");
				}
				serviceIPInfo.setLastTimeDelayed(df.format(delay*1000));  //与上条链路间隔时延
				permissableParameter = isContaisParameter(permissableParameter,"与上条链路间隔时延");

				if(setup3Ts==0){
					serviceIPInfo.setTcpTimeDelayed("N/A");	//TCP建链时延
				}else{
					serviceIPInfo.setTcpTimeDelayed(df.format((setup3Ts-setup1Ts)*1000));
					permissableParameter = isContaisParameter(permissableParameter,"TCP建链时延");
				}
				
				if(setup3Ts!=0.0 && fillData1Ts>setup3Ts) {   //Time to First Byte
					permissableParameter = isContaisParameter(permissableParameter,"建链后首包时延");
					serviceIPInfo.setTimeToFirstByte(df.format((fillData1Ts-setup3Ts)*1000));
				}
				else {
					serviceIPInfo.setTimeToFirstByte("N/A");
				}
				if(close33Time!=0){
					permissableParameter = isContaisParameter(permissableParameter,"断链时延");
					if(fillData9Ts>firstFinUpTime && close33Time>fillData9Ts){   // 断链时延
						serviceIPInfo.setOffTimeDelayed(df.format((close33Time-fillData9Ts)*1000));
					}else if(firstFinUpTime!=0){
						if(close33Time>firstFinUpTime)
							serviceIPInfo.setOffTimeDelayed(df.format((close33Time-firstFinUpTime)*1000));
						else
							serviceIPInfo.setOffTimeDelayed("ERROR");
					}else{
						serviceIPInfo.setOffTimeDelayed("N/A");
					}
				}else{
					serviceIPInfo.setOffTimeDelayed("N/A");
				}
				if(eff_time==-1 || eff_time==0){
					serviceIPInfo.setTimeEfficiency("N/A");  //链路时间有效率
				}else{
					serviceIPInfo.setTimeEfficiency(df.format(eff_time*100));
					permissableParameter = isContaisParameter(permissableParameter,"链路时间有效率");
					permissableParameter = isContaisParameter(permissableParameter,"平均链路时间有效率");
				}
				String requestCategoryBreviary = "";
				if(!linkHttpInfo.isEmpty()) {
					serviceIPInfo.setRequestCategory(httpInfoToString(linkHttpInfo));  // 请求类别
					permissableParameter = isContaisParameter(permissableParameter,"请求类别");
					if (JSON.toJSONString(httpInfo(linkHttpInfo)).substring(2) != null && !JSON.toJSONString(httpInfo(linkHttpInfo)).substring(2).equals("")) {
						requestCategoryBreviary = (httpInfo(linkHttpInfo).toString().substring(9, httpInfo(linkHttpInfo).toString().indexOf('/')));
						if (requestCategoryBreviary.toLowerCase().contains("get")) {
							serviceIPInfo.setRequestCategoryBreviary(requestCategoryBreviary.substring(0, 3));
						}
						if (requestCategoryBreviary.toLowerCase().contains("post")) {
							serviceIPInfo.setRequestCategoryBreviary(requestCategoryBreviary.substring(0, 4));
						}
					} else {
						serviceIPInfo.setRequestCategoryBreviary("N/A");
					}
				} else {
					serviceIPInfo.setRequestCategoryBreviary("N/A");
					serviceIPInfo.setRequestCategory("N/A");
				}
				tcpInfo.put("setup3Ts", String.valueOf(setup3Ts));
				tcpInfo.put("laseFinUpTime", String.valueOf(lastFinUpTime));
				tcpInfo.put("close33Time", String.valueOf(close33Time));
				tcpInfo.put("port", String.valueOf(portA));
				serviceIPInfo.setExchangeFlow(xchangeFlow.substring(0,xchangeFlow.indexOf("."))); // 交互流量
				serviceIPInfo.setExchangeTime(df.format((eachTs)*1000));  // 交互时间
				serviceIPInfo.setRttTime(df.format(avgRtts*1000));   // 平均RTT时间
				permissableParameter = isContaisParameter(permissableParameter,"交互流量");
				permissableParameter = isContaisParameter(permissableParameter,"交互时间");
				permissableParameter = isContaisParameter(permissableParameter,"平均RTT时间");
				 // 重传识别
				if(retranNum==0){
					serviceIPInfo.setAgainRequestCount("N/A");
				}else{
					serviceIPInfo.setAgainRequestCount(String.valueOf(tcp.getRetranNum()));
					permissableParameter = isContaisParameter(permissableParameter,"重传识别");
				}
				tcpIP.put(String.valueOf(tcpIP.size()), tcpInfo);	// 根据mapTcpIP.size()将初始调整为1(else中key判断)
				
				for(int k=0; k<serviceIPs.size(); k++) {
					if (addB.equals(serviceIPs.get(k).getServiceIP())) {
						serviceIPs.get(k).getServiceIPInfo().add(serviceIPInfo);
					}
					
				}
				
			}else{
				
				serviceIP.setServiceIP(addB);
				serviceIPInfos = new ArrayList<ServiceIPInfo>();
				
//				serviceIPInfo.setVideo(videoType(tcp.getContentType()));
				serviceIPInfo.setServerIP(addB);
				serviceIPInfo.setPorts(portA);
				serviceIPInfo.setPacketCount(packetCnt);
				serviceIPInfo.setHost(host);
				serviceIPInfo.setLinkHttpHeader(tcp.getLinkHttpHeader());
				serviceIPInfo.setKind(tcp.getKind());
				serviceIPInfo.setHasHttpInfo(!linkHttpInfo.isEmpty());
				serviceIPInfo.setT1(Double.parseDouble(df.format(t1*1000)));
				serviceIPInfo.setT2(Double.parseDouble(df.format(t2*1000)));
				tcpInfo.put("端口号", String.valueOf(portA));
				tcpInfo.put("包数", String.valueOf(packetCnt));
				permissableParameter = isContaisParameter(permissableParameter,"包数");
				double dnsStartTs = 0.0;
				double dnsEndTs = 0.0;
				if(mapDnsIP.containsKey(addB)){
					dnsStartTs = (double) mapDnsIP.get(addB).get("startTs");
					dnsEndTs = (double) mapDnsIP.get(addB).get("endTs");
				}
				tcpInfo.put("dnsStartTs", String.valueOf(dnsStartTs));
				tcpInfo.put("dnsEndTs", String.valueOf(dnsEndTs));
				tcpInfo.put("startTs", String.valueOf(startTs));
				tcpInfo.put("endTs", String.valueOf(endTs));
				tcpInfo.put("fillData1Ts", String.valueOf(fillData1Ts));
				tcpInfo.put("fillData9Ts", String.valueOf(fillData9Ts));
				if(fillData9Ts>firstFinUpTime) 
					tcpInfo.put("UPendTs", String.valueOf(fillData9Ts));
				else 
					tcpInfo.put("UPendTs", String.valueOf(firstFinUpTime));
				if(setup3Ts==0){
					serviceIPInfo.setTcpTimeDelayed("N/A");  // TCP建链时延
				}else{
					serviceIPInfo.setTcpTimeDelayed(df.format((setup3Ts-setup1Ts)*1000));
					permissableParameter = isContaisParameter(permissableParameter,"TCP建链时延");
				}
				if(setup3Ts!=0.0 && fillData1Ts>setup3Ts) {   // Time to First Byte
					serviceIPInfo.setTimeToFirstByte(df.format((fillData1Ts-setup3Ts)*1000));
					permissableParameter = isContaisParameter(permissableParameter,"建链后首包时延");
				}
				else{
					serviceIPInfo.setTimeToFirstByte("N/A");
				}
				if(close33Time!=0){
					if(fillData9Ts>firstFinUpTime && close33Time>fillData9Ts){   // 断链时延
						permissableParameter = isContaisParameter(permissableParameter,"断链时延");
						serviceIPInfo.setOffTimeDelayed(df.format((close33Time-fillData9Ts)*1000));
					}else if(firstFinUpTime!=0){
						if(close33Time>firstFinUpTime)
							serviceIPInfo.setOffTimeDelayed(df.format((close33Time-firstFinUpTime)*1000));
						else
							serviceIPInfo.setOffTimeDelayed("ERROR");
					}else{
						serviceIPInfo.setOffTimeDelayed("N/A");
					}
				}else{
					serviceIPInfo.setOffTimeDelayed("N/A");
				}
				if(eff_time==-1 || eff_time==0){
					serviceIPInfo.setTimeEfficiency("N/A");  // 链路时间有效率
				}else{
					permissableParameter = isContaisParameter(permissableParameter,"链路时间有效率");
					permissableParameter = isContaisParameter(permissableParameter,"平均链路时间有效率");
					serviceIPInfo.setTimeEfficiency(df.format(eff_time*100));
				}
				String requestCategoryBreviary = "";
				if(!linkHttpInfo.isEmpty()) {  // 请求类别
					serviceIPInfo.setRequestCategory(httpInfoToString(linkHttpInfo));
					permissableParameter = isContaisParameter(permissableParameter,"请求类别");
					if (JSON.toJSONString(httpInfo(linkHttpInfo)).substring(2) != null && !JSON.toJSONString(httpInfo(linkHttpInfo)).substring(2).equals("")) {
						requestCategoryBreviary = (httpInfo(linkHttpInfo).toString().substring(9, httpInfo(linkHttpInfo).toString().indexOf('/')));
						if (requestCategoryBreviary.toLowerCase().contains("get")) {
							serviceIPInfo.setRequestCategoryBreviary(requestCategoryBreviary.substring(0, 3));
						}
						if (requestCategoryBreviary.toLowerCase().contains("post")) {
							serviceIPInfo.setRequestCategoryBreviary(requestCategoryBreviary.substring(0, 4));
						}
					} else {
						serviceIPInfo.setRequestCategoryBreviary("N/A");
					}
				} else {
					serviceIPInfo.setRequestCategory("N/A");
					serviceIPInfo.setRequestCategoryBreviary("N/A");
				}
				tcpInfo.put("setup3Ts", String.valueOf(setup3Ts));
				tcpInfo.put("laseFinUpTime", String.valueOf(lastFinUpTime));
				tcpInfo.put("close33Time", String.valueOf(close33Time));
				tcpInfo.put("port", String.valueOf(portA));
				if(mapDnsIP.containsKey(addB) && startTs>=dnsEndTs){   // DNS时延
					permissableParameter = isContaisParameter(permissableParameter,"DNS时延");
					serviceIPInfo.setDnsDelsyTs(df.format((dnsEndTs-dnsStartTs)*1000));
				}else{
					serviceIPInfo.setDnsDelsyTs("N/A");
				}
					
				serviceIPInfo.setExchangeFlow(xchangeFlow.substring(0,xchangeFlow.indexOf(".")));	// 交互流量
				serviceIPInfo.setExchangeTime(df.format((eachTs)*1000));	// 交互时间
				serviceIPInfo.setRttTime(df.format(avgRtts*1000));	// 平均RTT时间
				tcpIP.put("0", tcpInfo);
				 //  重传识别
				if(retranNum==0){
					serviceIPInfo.setAgainRequestCount("N/A");
				}else{
					serviceIPInfo.setAgainRequestCount(String.valueOf(tcp.getRetranNum()));
					permissableParameter = isContaisParameter(permissableParameter,"重传识别");
				}

				permissableParameter = isContaisParameter(permissableParameter,"交互时间");
				permissableParameter = isContaisParameter(permissableParameter,"交互流量");
				permissableParameter = isContaisParameter(permissableParameter,"平均RTT时间");

				serviceIPInfos.add(serviceIPInfo);
				serviceIP.setServiceIPInfo(serviceIPInfos);
				serviceIPs.add(serviceIP);
				
				ipAddrId = String.valueOf(ipId)+String.valueOf(i++)+"-"+addB;
				serviceIPInfo.setIpAddrId(ipAddrId);
				if (StringUtil.isEmpty(ipAddrId)) {
					log.info("ipAddrId是空--------");
				} else {
					jedis.rpush(username + "_" + fileCur + RedisStorageConfig.IP_ADDRESS, ipAddrId);
				}
				ipAddrIds.add(ipAddrId);
			}
			
			tcpInfos.put(addB, tcpIP);	
			avgRtts(tcp);
			//吞吐曲线图 (秒)
			Map<Integer, Integer> getMapRate = tcp.getMapRate();
			mapRate = mapRate(getMapRate,mapRate);
			Map<Integer, Integer> getMapRate1 = tcp.getMapRate1();
			mapRate1 = mapRate(getMapRate1,mapRate1);
			
		}
		tcpGraph.put(GraphTypeConfig.GRAPHTYPE_02, ethLen);
		tcpGraph.put(GraphTypeConfig.GRAPHTYPE_01, mapIpFlow);	
		tcpGraph.put(GraphTypeConfig.GRAPHTYPE_03, mapRateFill(mapRate));
		tcpGraph.put(GraphTypeConfig.GRAPHTYPE_03_ms, mapRateFill(mapRate1));
		tcpGraph.put("tcpInfos", tcpInfos);
		
		tcpIPs.put("tcpGraph", tcpGraph);
		tcpIPs.put("无效IP", others);
		tcpIPs.put("othersIp", othersIp);
		tcpIPs.put("serviceIPs", serviceIPs);
		tcpIPs.put("permissableParameter", permissableParameter);
		if(logFileData1 == null){
			tcpIPs.put("logFileData9", "0");
			tcpIPs.put("logFileData1", "0");
		}else{
			tcpIPs.put("logFileData9", logFileData9);
			tcpIPs.put("logFileData1", logFileData1);
		}
		tcpIPs.put("ipAddrIds", ipAddrIds);
		return tcpIPs;
	}
	private Map<Integer, Integer> mapRate(Map<Integer, Integer> getMapRate, Map<Integer, Integer> mapRate) {

		if(getMapRate!=null){
			for (Integer key : getMapRate.keySet()) {
				
				if(mapRate!=null && mapRate.containsKey(key))
					mapRate.put(key, mapRate.get(key)+getMapRate.get(key));
				else
					mapRate.put(key, getMapRate.get(key));
			}
		}
		return mapRate;
	}
	/**
	 * dns信息采集
	 */
	private void dns() {
		
		for (DNSStream dns : dnsStreams) {	
			for (InetAddress ip : dns.getIPs()) {
				if(!mapDnsIP.containsKey(ip.toString().substring(1))){
					Map<String, Object> dnsInfo = new HashMap<String, Object>();
					dnsInfo.put("startTs", dns.getStartTs());	//  dns询问时间
					dnsInfo.put("endTs", dns.getEndTs());	//  dns结束时间
					dnsInfo.put("duration", dns.getDuration());		//  DNS时延
					mapDnsIP.put(ip.toString().substring(1), dnsInfo);
				}
			}
		}
	}
	/**
	 * 平均RTT时间
	 * @return 
	 */
	private double avgRtts(TCPStream tcp) {
		double rtts = 0.0;	// rtts （Time_收到ACK包-Time_发送包）总时间
		double rttsNum = 0.0;	// rtts时间  个数
		double avgNum = 0;
		for (Double arg : tcp.getRTTs()) {
			rtts += arg;
			rttsNum++;
		}
		if(rttsNum!=0.0){
			avgNum = rtts/rttsNum;
		}
		return avgNum;
	}
	/**
	 * 请求类别
	 * @return 
	 */
	private Map<Integer, Object> httpInfo(List<HTTP> linkHttpInfo) {

		Map<Integer,Object> https = new HashMap<Integer,Object>();
		int i=0;
		for (HTTP http : linkHttpInfo) {
			String info = http.getInfo();
            switch(info.substring(0, 4)){
             case "HTTP": break;
             default: 
        	    Map<String,String> infos = new HashMap<String,String>();
            	infos.put("info",info);
				https.put(i++,infos);
            }
		}
		return https;
	}
	
	private String httpInfoToString(List<HTTP> linkHttpInfo) {
		StringBuffer httpInfos = new StringBuffer();
		
		Map<Integer,Object> https = new HashMap<Integer,Object>();
		int i=0;
		for (HTTP http : linkHttpInfo) {
			String info = http.getInfo();
			switch(info.substring(0, 4)){
			case "HTTP": break;
			default: 
				Map<String,String> infos = new HashMap<String,String>();
				infos.put("info",info);
				https.put(i++,infos);
				httpInfos.append(info);
			}
		}
		return httpInfos.toString();
	}
	private Map<String, Integer> mapRateFill(Map<Integer, Integer> mapRate) {
		
		Map<String, Integer> map = new HashMap<>();
		if(mapRate != null){
			
			if(mapRate.get(0)==null)
				mapRate.put(0, 0);
			int tmp=0;
			int diff =0;
			Set<Integer> keys = mapRate.keySet();
			for (Integer key : keys) {
				
				if(!mapRate.containsKey(key-1) && key != 0){
					diff = key - tmp;
					int avg = (mapRate.get(key)-mapRate.get(tmp))/diff;
					while(diff>0){
						
						map.put(String.valueOf(tmp+diff), mapRate.get(tmp)+avg*diff);
						diff--;
					}
				}else
					map.put(key.toString(), mapRate.get(key));
				tmp = key;
			}
		}
		return map;
	}
	/*private boolean videoType(String arg) {
		
		boolean state = false;
		List<String> video = new ArrayList<String>();
		video.add("video/x-ms-asf");
		video.add("video/avi");
		video.add("video/x-ivf");
		video.add("video/x-mpeg");
		video.add("video/mpeg4 ");
		video.add("video/x-sgi-movie");
		video.add("video/mpeg ");
		video.add("video/mpeg4");
		video.add("video/x-mpg");
		video.add("video/x-mpeg");
		video.add("video/mpg");
		video.add("video/vnd.rn-realvideo");
		video.add("video/x-ms-wm");
		video.add("video/x-ms-wmv");
		video.add("video/x-ms-wmx");
		video.add("video/x-ms-wvx");
		if(video.contains(arg))
			state = true;
		return state;
		
	}*/
	/*private boolean pageType(String arg) {
		
		boolean state = true;
		
		List<String> pageType = new ArrayList<String>();
		pageType.add("");
		if(pageType.contains(arg))
			state = false;
		return state;
	}*/

	private String isContaisParameter(String permissable, String parameter) {
		if (permissable.contains(parameter)){
			return permissable;
		} else {
			return permissable + parameter + ",";
		}
	}
}
