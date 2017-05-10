package com.util.base;

import com.alibaba.fastjson.JSONObject;
import com.constant.GraphTypeConfig;
import com.constant.RedisStorageConfig;
import com.service.IGraphDateService;
import com.vo.FileInfo;
import com.vo.RebuildInfo;
import com.vo.ServiceIP;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.ShardedJedis;
import resolve.decoder.Packet;
import resolve.decoder.Pcap;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 分析log 资源共享多线程
 * @author 任宝坤
 */
@Scope("prototype")
public class LogThreads extends ActionBase implements Callable<Object>{  
	
	private String fileName;
	private ShardedJedis jedis;

	public ShardedJedis getJedis() {
		return jedis;
	}

	public void setJedis(ShardedJedis jedis) {
		this.jedis = jedis;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LogThreads(String fileName, ShardedJedis jedis) {
		this.fileName = fileName;
		this.jedis = jedis;
	}
	
	public static IGraphDateService service0;
	
	public static Map<String, Object> list_maps0 = new HashMap<String, Object>();
	public static NumberFormat df = new DecimalFormat("0.00");
	public static int ipId0;
	public static String serverpath;
	public static String username;
	public static String normData0;
	public static boolean logSta = true;
	public static String file;
	public static List<String> ipAddrIds = new ArrayList<String>();
	
	@SuppressWarnings("unchecked")
	public FileInfo call() throws Exception {
		log.info(Thread.currentThread().getName()+"  启动--子线程---"+fileName);
		
		String fileCur = fileName.substring(fileName.indexOf("@") + 1);
		int ipId = ipId0++;
		logSta = true;
		
		String host=null;
//		String ports = "";
		
		String dnsDelsyTs;
		String serviceIp;
		int DNSNum = 0;
		double DNStimeDelayedcount = 0.0;//sum DNS时延(ms)
		int tcpNum = 0;
	    double tcpTimeDelayedcount = 0;	//sum TCP建链时延(ms)
	    int ttfNum = 0;
	    double timeToFirstBytecount = 0;	//sum 建链后首包时延(ms)
	    int otdNum = 0;
	    double offTimeDelayedcount = 0;	//sum 断链时延(ms)
	    double rttTimecount = 0;	//sum RTT时间(ms)   平均报文请求响应时间
	    double exchangeTimecount = 0;	//sum 交互时间(ms)
	    String timeEfficiencyLowest = "0";	//最低链路时间有效率IP+port
		int tcpCount = 0;
		int packetCount = 0;
		double exchangeFlowCount = 0.0;
		double timeEfficiencyCount = 0.0;
		double lastTimeDelayed = 0.0;
		List<RebuildInfo> rebuildInfos = new ArrayList<RebuildInfo>();
		Pcap pcap = new Pcap();
		Packet[] packets = pcap.decode(serverpath+username + "/" + fileCur+".pcap");  
	    AnalyzeResult result = new AnalyzeResult(packets,pcap.getLogEndTs());
	    result.assembleStreams();
	    Map<String, Object> maps = new HashMap<String, Object>();
		try {
			maps = result.tcp(ipId,fileName,username,this.jedis);
		} catch (IOException e) {
			log.error("log日志分析异常"+fileName+"\n"+e);
		}
	    FileInfo fileInfo = new FileInfo();
	    RebuildInfo rebuildInfo = new RebuildInfo();
	    
		List<ServiceIP> serviceIPs = new ArrayList<ServiceIP>();
		serviceIPs = (List<ServiceIP>) maps.get("serviceIPs");
	    maps.remove("serviceIPs");
		String permissableParameter = (String) maps.get("permissableParameter");
		maps.remove("permissableParameter");



		ipAddrIds.addAll((List<String>)maps.get("ipAddrIds"));
		maps.remove("ipAddrIds");
		Map<String, Object> tcpGraph = (Map<String, Object>) maps.get("tcpGraph");
		List<List<Integer>> tcpGraphList = (List<List<Integer>>) tcpGraph.get(GraphTypeConfig.GRAPHTYPE_02);
		fileInfo.setPacketLenData(tcpGraphList);
		list_maps0.put(fileCur, maps.get("tcpGraph"));
		maps.remove("tcpGraph");

		fileInfo.setIpCount(serviceIPs.size());
		List<String> others = (List<String>)maps.get("无效IP");//无效ip + 端口号
		List<String> othersIp = (List<String>)maps.get("othersIp");//无效ip
		Set<String> othersIpSet = null;
		int othersIpCount = 0;
		if (others == null) {
			fileInfo.setOthersTcpCount(0);
		} else {
			fileInfo.setOthersTcpCount(others.size());
		}
		log.info("清洗掉-----------" + fileInfo.getOthersTcpCount() + "------------条链路");
		if (othersIp == null) {
			fileInfo.setOthersIpCount(0);
		} else {
			othersIpSet = new HashSet<String>(othersIp);
			othersIpCount = othersIpSet.size();
		}
		fileInfo.setOthers(others);
	    fileInfo.setFileName(fileCur);
	    int maxFlowsIndex = 0;
	    int maxTCPipIndex = 0;
	    int serIPInfoSize= 0;
	    int maxSerIPInfoSize = 0;
	    double maxFlows = 0.0;
	    double t1 = 0;
	    double t2 = 0;
	    int num = 0;
	    double flows = 0.0;
		String globalVarStr=null;
	    
	    StringBuffer downloadRate = new StringBuffer();
	    int links = 0;
	    String TimeEfficiency2="0";
	    String TimeEfficiency3="100";
	    
	    double a1 = 0;
	    String dnsBigIp = "N/A";
	    String dnsBig = "N/A";
	    
	    double b1 = 0;
	    String offTimeBigIp = "N/A";	
	    long offTimeBigPort = 0;	
	    String offTimeBig = "N/A";	
	     
	    double c1 = 0;
	    String timeToFirstBigIp = "N/A";	
	    long timeToFirstBigPort = 0;
	    String timeToFirstBig = "N/A";
	    
	    double d1 = 0;
		String tcpTimeBigIp = "N/A";	
		long tcpTimeBigPort = 0;
		String tcpTimeBig = "N/A";	
		
		double e1 = 0;
		String rttTimeBigIp = "N/A";	
		long rttTimeBigPort = 0;
		String rttTimeBig = "N/A";	
		
		String flowBigIp = null;
		long flowBigPort = 0;
		double flowBig = 0;	
        
		String exchangeTimeBigIp = null;
		long exchangeTimeBigPort = 0;
		double exchangeTimeBig = 0;	
		
		String serverIp = null;
		long ports;
		
	    int sumOftenOffVerdict = 0;
	    for (int i=0; i<serviceIPs.size(); i++) {
            
	    	double allLastTimeDelayed = 0.0;
	    	double minLastTimeDelayed = 1000;

	    	int repeatCount = 1;
	    	dnsDelsyTs = serviceIPs.get(i).getServiceIPInfo().get(0).getDnsDelsyTs();
	    	if (dnsDelsyTs!=null && !dnsDelsyTs.equals("N/A")){
	    		DNSNum++;
	    		DNStimeDelayedcount += Double.parseDouble(dnsDelsyTs);
	    		if(Double.parseDouble(dnsDelsyTs) > a1){
	    			
	    			a1 = Double.parseDouble(dnsDelsyTs);
	    			dnsBigIp = serviceIPs.get(i).getServiceIPInfo().get(0).getServerIP();
	    			dnsBig = serviceIPs.get(i).getServiceIPInfo().get(0).getDnsDelsyTs();
	    		}
	    	} 
	    	
	    	serIPInfoSize = serviceIPs.get(i).getServiceIPInfo().size();
	    	if (serIPInfoSize > maxSerIPInfoSize) {
	    		maxSerIPInfoSize = serIPInfoSize;
	    		maxTCPipIndex = i;
			}
	    	tcpCount += serviceIPs.get(i).getServiceIPInfo().size();
	    	serviceIp = serviceIPs.get(i).getServiceIP();
	    	//去掉重复IP
	    	for(String ip : othersIpSet){  
	    		if (ip.contains(serviceIp)) {
	    			othersIpCount--;
					break;
				}
	        }
	    	
	    	flows = 0.0;
	    	
	    	for(int j=0; j<serIPInfoSize; j++) {
	    		links++;
	    		serverIp = serviceIPs.get(i).getServiceIPInfo().get(j).getServerIP();
	    		ports = serviceIPs.get(i).getServiceIPInfo().get(j).getPorts();
	    		
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getTimeEfficiency();
	    		if(!globalVarStr.equals("N/A") && Double.parseDouble(globalVarStr)!=0)
	    			TimeEfficiency2 = serviceIPs.get(i).getServiceIPInfo().get(j).getTimeEfficiency();
	    		if(Double.parseDouble(TimeEfficiency2)!=0 && Double.parseDouble(TimeEfficiency2) < Double.parseDouble(TimeEfficiency3)){
	    			
	    			timeEfficiencyLowest = serverIp+" 端口号("+ports+")";
	    			TimeEfficiency3=TimeEfficiency2;
	    		}
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getTcpTimeDelayed();
	    		if(!globalVarStr.equals("N/A")){
	    			tcpNum++;
	    			tcpTimeDelayedcount += Double.parseDouble(globalVarStr);	
	    			if(Double.parseDouble(globalVarStr) > d1){
		    			
		    			d1 = Double.parseDouble(globalVarStr);
		    			tcpTimeBigIp = serverIp;
		    			tcpTimeBigPort = ports;
		    			tcpTimeBig = globalVarStr;
		    		}
	    		}
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getExchangeTime();
	    		if(!globalVarStr.equals("N/A")){
	    			
	    			exchangeTimeBigIp = serverIp;
	    			exchangeTimeBigPort = ports;
	    			exchangeTimeBig = Double.parseDouble(globalVarStr);
	    		}
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getExchangeFlow();
    			if(!globalVarStr.equals("N/A") && Double.parseDouble(globalVarStr) > flowBig){
	    			
	    			flowBigIp = serverIp;
	    			flowBigPort = ports;
	    			flowBig = Double.parseDouble(globalVarStr);
	    		}
	    		
    			globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getTimeToFirstByte();
	    		if(!globalVarStr.equals("N/A")){
	    			ttfNum++;
	    			timeToFirstBytecount += Double.parseDouble(globalVarStr);
	    			if(Double.parseDouble(globalVarStr) > c1){
		    			
		    			c1 = Double.parseDouble(globalVarStr);
		    			timeToFirstBigIp = serverIp;
		    			timeToFirstBigPort = ports;
		    			timeToFirstBig =  serviceIPs.get(i).getServiceIPInfo().get(j).getTimeToFirstByte();
		    		}
	    		}	
	    		
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getOffTimeDelayed();
	    		if(!globalVarStr.equals("N/A") && !globalVarStr.equals("ERROR")){
	    			otdNum++;
	    			offTimeDelayedcount += Double.parseDouble(globalVarStr);
	    			if(Double.parseDouble(globalVarStr) > b1){
		    			
		    			b1 = Double.parseDouble(globalVarStr);
		    			offTimeBigIp = serverIp;
		    			offTimeBigPort = ports;
		    			offTimeBig  = serviceIPs.get(i).getServiceIPInfo().get(j).getOffTimeDelayed();
		    		}
	    		}
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getRttTime();
				rttTimecount += Double.parseDouble(serviceIPs.get(i).getServiceIPInfo().get(j).getRttTime());
				if(Double.parseDouble(globalVarStr) > e1){
	    			
	    			e1 = Double.parseDouble(globalVarStr);
	    			rttTimeBigIp = serverIp;
	    			rttTimeBigPort = ports;
	    			rttTimeBig = serviceIPs.get(i).getServiceIPInfo().get(j).getRttTime();
	    		}
				
				exchangeTimecount += Double.parseDouble(serviceIPs.get(i).getServiceIPInfo().get(j).getExchangeTime());
	    		
	    		packetCount += serviceIPs.get(i).getServiceIPInfo().get(j).getPacketCount();
	    		flows += Double.parseDouble(serviceIPs.get(i).getServiceIPInfo().get(j).getExchangeFlow());
	    		globalVarStr = serviceIPs.get(i).getServiceIPInfo().get(j).getTimeEfficiency();
	    		if(!globalVarStr.equals("N/A"))
	    			timeEfficiencyCount += Double.parseDouble(serviceIPs.get(i).getServiceIPInfo().get(j).getTimeEfficiency());
	    		if(j>0)
	    			lastTimeDelayed = Double.parseDouble(serviceIPs.get(i).getServiceIPInfo().get(j).getLastTimeDelayed());
	    		
	    		if(serviceIPs.get(i).getServiceIPInfo().get(j).getKind()<0){
	    			downloadRate.append("、"+serviceIp);
	    			downloadRate.append("("+ports+")");
	    		}
	    		
	    		if(serviceIPs.get(i).getServiceIPInfo().get(j).isHasHttpInfo()){
	    			t1 += serviceIPs.get(i).getServiceIPInfo().get(j).getT1();
	    			t2 += serviceIPs.get(i).getServiceIPInfo().get(j).getT2();
	    			num++;
	    		}
	    		if(0<=lastTimeDelayed && lastTimeDelayed<=1000 && j>0){
	    			
	    			repeatCount++;
	    			allLastTimeDelayed += lastTimeDelayed;
	    			if(minLastTimeDelayed>lastTimeDelayed)
	    				minLastTimeDelayed = lastTimeDelayed;
	    			host = serviceIPs.get(i).getServiceIPInfo().get(j).getHost();
//    				ports += "、"+String.valueOf(ports);
    		
    				if(j==serIPInfoSize-1 && repeatCount>=3){
    					
    					sumOftenOffVerdict = sumOftenOffVerdict+repeatCount;
    					
		    			rebuildInfo.setServiceIp(serviceIp);
		    			rebuildInfo.setRepeatCount(repeatCount);
		    			rebuildInfo.setMinLastTimeDelayed(df.format(minLastTimeDelayed));
		    			rebuildInfo.setAvgLastTimeDelayed(df.format(allLastTimeDelayed/(repeatCount-1)));
		    			rebuildInfo.setHost(host);
//		    			rebuildInfo.setPortAs(ports);
		    			rebuildInfos.add(rebuildInfo);
		    			rebuildInfo = new RebuildInfo();
		    			while (repeatCount != 1) 
		    				serviceIPs.get(i).getServiceIPInfo().get(j+1-repeatCount--).setOftenOffVerdict("Y");
		    		}
	    		}else if(repeatCount>=3){
	    			
					sumOftenOffVerdict = sumOftenOffVerdict+repeatCount;
	    			rebuildInfo.setServiceIp(serviceIp);
	    			rebuildInfo.setRepeatCount(repeatCount);
	    			rebuildInfo.setMinLastTimeDelayed(df.format(minLastTimeDelayed));
	    			rebuildInfo.setAvgLastTimeDelayed(df.format(allLastTimeDelayed/(repeatCount-1)));
	    			rebuildInfo.setHost(host);
//	    			rebuildInfo.setPortAs(ports);
	    			rebuildInfos.add(rebuildInfo);
	    			rebuildInfo = new RebuildInfo();
//	    			ports = "、"+String.valueOf(ports);
	    			allLastTimeDelayed = 0;
	    			minLastTimeDelayed = 2000;
	    			while (repeatCount != 1) 
	    				serviceIPs.get(i).getServiceIPInfo().get(j+1-repeatCount--).setOftenOffVerdict("Y");
	    		}else{
	    			
	    			repeatCount = 1;
//	    			ports = "、"+String.valueOf(ports);
	    			allLastTimeDelayed = 0;
	    			minLastTimeDelayed = 2000;
	    		}
	    		if (!permissableParameter.contains("串行链路频繁拆建链判断")) 
					permissableParameter = permissableParameter + "串行链路频繁拆建链判断,";
	    	}
//	    	ports = "";
	    	exchangeFlowCount += flows;
	    	if ((flows - maxFlows) > 0.0) {
	    		maxFlows = flows;
	    		maxFlowsIndex = i;
	    	}
		}
	    log.info("清洗掉-----" + othersIpCount + "-----条IP");
	    fileInfo.setOthersIpCount(othersIpCount);
	    fileInfo.setSumOftenOffVerdict(sumOftenOffVerdict);
	    
	    fileInfo.setDnsBigIp(dnsBigIp);
	    fileInfo.setDnsBig(dnsBig);
	    
	    fileInfo.setOffTimeBigIp(offTimeBigIp);
	    fileInfo.setOffTimeBigPort(String.valueOf(offTimeBigPort));
	    fileInfo.setOffTimeBig(String.valueOf(offTimeBig));
	    
	    fileInfo.setTimeToFirstBigIp(timeToFirstBigIp);
	    fileInfo.setTimeToFirstBigPort(String.valueOf(timeToFirstBigPort));
	    fileInfo.setTimeToFirstBig(String.valueOf(timeToFirstBig));
	    
	    fileInfo.setTcpTimeBigIp(tcpTimeBigIp);
    	fileInfo.setTcpTimeBig(String.valueOf(tcpTimeBig));
	    if(tcpTimeBigPort != 0)
	    	fileInfo.setTcpTimeBigPort(String.valueOf(tcpTimeBigPort));
	    

	    fileInfo.setRttTimeBigIp(rttTimeBigIp);
	    fileInfo.setRttTimeBigPort(String.valueOf(rttTimeBigPort));
	    fileInfo.setRttTimeBig(String.valueOf(rttTimeBig));
	    
	    fileInfo.setFlowBigIp(flowBigIp);
	    fileInfo.setFlowBigPort(flowBigPort);
	    fileInfo.setFlowBig(flowBig);
	    
		fileInfo.setTimeEfficiencyLowest(TimeEfficiency3);
		fileInfo.setLowestEffLink(timeEfficiencyLowest);
		
		fileInfo.setExchangeTimeBigIp(exchangeTimeBigIp);
	    fileInfo.setExchangeTimeBigPort(exchangeTimeBigPort);
	    fileInfo.setExchangeTimeBig(exchangeTimeBig);
		
		
		if(DNSNum>0)
			fileInfo.setAvgDnsDelsyTs(df.format(DNStimeDelayedcount/DNSNum));
		if(tcpNum>0)
			fileInfo.setAvgTcpTimeDelayed(df.format(tcpTimeDelayedcount/tcpNum));
		if(ttfNum>0)
			fileInfo.setAvgTimeToFirstByte(df.format(timeToFirstBytecount/ttfNum));
		if(otdNum>0)
			fileInfo.setAvgOffTimeDelayed(df.format(offTimeDelayedcount/otdNum));
		fileInfo.setAvgRttTime(df.format(rttTimecount/links));
		fileInfo.setAvgExchangeTime(df.format(exchangeTimecount/links));
		
	    StringBuffer maxFlowPort = new StringBuffer();
	    for(int i=0; i<serviceIPs.get(maxFlowsIndex).getServiceIPInfo().size(); i++) {
	    	maxFlowPort.append(serviceIPs.get(maxFlowsIndex).getServiceIPInfo().get(i).getPorts());
	    	maxFlowPort.append(",");
	    }
	    
	    maxFlowPort.deleteCharAt(maxFlowPort.length() - 1);
	    if(downloadRate.length()>0)
	    	downloadRate.deleteCharAt(0);
	    
		if(t1==0)
			fileInfo.setavgT1("N/A");
		else
			fileInfo.setavgT1(df.format((double)t1/num));
		if(t2==0)
			fileInfo.setavgT2("N/A"); 
		else
		    fileInfo.setavgT2(df.format((double)t2/num));
		
	    if(downloadRate.length() == 0)
	    	downloadRate.append("N/A");
	    fileInfo.setDownloadRate(downloadRate.toString());
	    fileInfo.setIpTCPCounts(maxSerIPInfoSize);
	    fileInfo.setMaxFlowIP(serviceIPs.get(maxFlowsIndex).getServiceIP());
	    fileInfo.setMaxTCPip(serviceIPs.get(maxTCPipIndex).getServiceIP());
	    fileInfo.setMaxFlowPort(maxFlowPort.toString());
	    fileInfo.setAllKeepTimes(df.format(pcap.getLogEndTs() * 1000));
	    fileInfo.setAllFileDataTimes(df.format(((double)maps.get("logFileData9")-(double)maps.get("logFileData1"))*1000));
	    if(DNStimeDelayedcount==0)
		    fileInfo.setDnsTimeDelayedcount("N/A");
	    else
	    	fileInfo.setDnsTimeDelayedcount(df.format(DNStimeDelayedcount));
	    fileInfo.setTcpCount(tcpCount);
	   
	    fileInfo.setPacketCount(packetCount);
	    fileInfo.setExchangeFlowCount(exchangeFlowCount);
	    fileInfo.setExchangeTimeCount(df.format(exchangeTimecount));
	    if(timeEfficiencyCount==0)
	    	fileInfo.setTimeEfficiencyAVG("N/A");
	    else
	    	fileInfo.setTimeEfficiencyAVG(df.format(timeEfficiencyCount/tcpCount));
	    fileInfo.setServiceIP(serviceIPs);
	    if (rebuildInfos != null && rebuildInfos.size() > 0) {
	    	rebuildInfos = summaryRebuildInfos(rebuildInfos);
		}
		String[] parameters = permissableParameter.split(",");
		List<String> allPara = new ArrayList<String>();
		allPara.add("交互时间");
		allPara.add("交互流量");
		allPara.add("包数");
		allPara.add("链路数");
		allPara.add("服务器IP归属");
		allPara.add("无效IP");
		allPara.add("请求类别");
		allPara.add("建链后首包时延");
		allPara.add("断链时延");
		allPara.add("DNS时延");
		allPara.add("平均RTT时间");
		allPara.add("TCP建链时延");
		allPara.add("重传识别");
		allPara.add("与上条链路串并行关系");
		allPara.add("与上条链路间隔时延");
		allPara.add("串行链路频繁拆建链判断");
		allPara.add("链路时间有效率");
		allPara.add("平均链路时间有效率");
		log.info("这是可计算参数***"+parameters.length+"个***" + permissableParameter);
		StringBuffer noPermissablePara = new StringBuffer();
		for(int i=0; i<allPara.size(); i++) {
			if (!permissableParameter.contains(allPara.get(i))) {
				noPermissablePara.append(allPara.get(i) + ",");
			}
		}
		String noPermPara = noPermissablePara.toString().trim();
		fileInfo.setPermissableParameterCount(parameters.length);
		
		if (noPermPara != null && !noPermPara.equals("")) {
			String[] noPermParas = noPermPara.split(",");
			if (noPermParas.length < 4) {
				fileInfo.setNoPermissableParaNum(noPermParas.length);
				fileInfo.setNoPermissablePara(noPermPara.substring(0, noPermPara.length()-1));
			} else {
				noPermissablePara.delete(0, noPermissablePara.length());
				for(int k=0; k<3; k++) {
					noPermissablePara.append(noPermParas[k]);
					noPermissablePara.append(",");
				}
				noPermissablePara.deleteCharAt(noPermissablePara.length()-1);
				noPermissablePara.append("等");
				fileInfo.setNoPermissablePara(noPermissablePara.toString());
				fileInfo.setNoPermissableParaNum(noPermParas.length);
			}
			log.info("这是不可计算参数**************************" + (noPermPara.substring(0, noPermPara.length()-1)));
		} else {
			log.info("这是不可计算参数*************************空*");
			fileInfo.setNoPermissablePara("0个参数无法计算");
		}
		log.info("这是页面显示" + fileInfo.getNoPermissablePara());
	    fileInfo.setRebuildInfo(rebuildInfos);
	    //每个文件加入redis
//	    Jedis jedis = new Jedis("218.206.177.170",6379);//初始化Jedis
	    Map<String, Object> redisFileTcpGraph = new HashMap<String, Object>();
	    redisFileTcpGraph.put(fileCur, list_maps0.get(fileCur));
	    jedis.set(username + "_" + fileName + RedisStorageConfig.TCP_GRAPH, JSONObject.toJSONString(redisFileTcpGraph));
	    jedis.set(username + "_" + fileName + RedisStorageConfig.FILE_INFO, JSONObject.toJSONString(fileInfo));
		log.info(Thread.currentThread().getName()+"  结束--子线程---"+fileCur);

		return fileInfo;
	}
	private List<RebuildInfo> summaryRebuildInfos(List<RebuildInfo> rebuildInfos) {
		List<RebuildInfo> list = new ArrayList<RebuildInfo>();
		RebuildInfo rebuild;
		int flag;
		for(RebuildInfo rebuildInfo : rebuildInfos) {
			flag = 0;
			for(int i=0; i<list.size(); i++) {
				if (list.get(i).getServiceIp().equals(rebuildInfo.getServiceIp())) {
					flag = 1;
					//计算实体类中数据
					rebuild = list.get(i);
					rebuild.setAvgLastTimeDelayed(String.valueOf(df.format(((Double.parseDouble(rebuild.getAvgLastTimeDelayed()) * rebuild.getRepeatCount()) +
																			 Double.parseDouble(rebuildInfo.getAvgLastTimeDelayed()) * rebuildInfo.getRepeatCount()) /
												   							(rebuild.getRepeatCount()+rebuildInfo.getRepeatCount()))));
					rebuild.setRepeatCount(rebuild.getRepeatCount() + rebuildInfo.getRepeatCount());
					if (Double.parseDouble(rebuild.getMinLastTimeDelayed()) > Double.parseDouble(rebuildInfo.getMinLastTimeDelayed())) {
						rebuild.setMinLastTimeDelayed(rebuildInfo.getMinLastTimeDelayed());
					}
					break;
				}
			}
			if (flag == 0) {
				list.add(rebuildInfo);
			}
		}
		return list;
	}

}

