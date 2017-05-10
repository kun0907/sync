package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.constant.RedisStorageConfig;
import com.constant.SysInfoConfig;
import com.service.IGraphDateService;
import com.service.IIpAddService;
import com.service.ILogsAnalyzeService;
import com.service.indexDataServiceImpl;
import com.util.StringUtil;
import com.util.base.ActionBase;
import com.util.base.LogThreads;
import com.util.base.PDFUti;
import com.util.base.ZipUtil;
import com.util.ftl.PdfHelper;
import com.util.ftl.PdfUtils;
import com.vo.BasicConclusion;
import com.vo.BasicConclusions;
import com.vo.CompareInfo;
import com.vo.FileInfo;
import com.vo.ServiceIP;
import com.vo.SysInfo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Scope("prototype")
@RequestMapping("reportOut")
public class AnalyzeController extends ActionBase {
	
	@Autowired
	IGraphDateService service;
	@Autowired
	ILogsAnalyzeService logsAnalyzeService;
	@Autowired
	IIpAddService ipAddService;
	@Autowired
	private ShardedJedisPool shardedJedisPool; 
	
	
	@RequestMapping(value="/oneKey.do",method=RequestMethod.POST)
	public void export2html(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info("报告输出html........");
		Map<String, Object> data_map = analysis(request,response);
		renderData(request, response, JSON.toJSONString(data_map));
		log.info("分析结果数据以返回值前端........");
	}
	
	@SuppressWarnings({ "unchecked", "static-access", "deprecation" })
	@RequestMapping(value="/export2ftl.do",method=RequestMethod.POST)
	public void export2ftl(HttpServletRequest request,HttpServletResponse response) throws Exception {
			log.info("报告输出ftl........");
			Map<String, Object> data_map = analysis(request,response);
			
			String jingpinFileName = request.getParameter("jingpinFileName");
			String whoShowMap = request.getParameter("whoShowMap");
			
			PDFUti pdfUti = new PDFUti();
			PdfUtils pdfUtils = new PdfUtils();
			
			boolean isCompare=Boolean.parseBoolean(request.getParameter("compare"));
			if(isCompare){//比较时
					initData4ftlWhenCompare(data_map,jingpinFileName,request,whoShowMap);
					try {
							String path=PdfHelper.getPath();
							
							String webpath = request.getRealPath("/");
					    	String outputPath=webpath+ "tempPdf//"+request.getSession().getAttribute("username")+"//";
					    	File pdfDir = new File(outputPath);
					    	if(pdfDir.exists()){
					    		pdfDir.delete();
					    	}
					    	pdfDir.mkdirs();
					    	outputPath+="details_whenCompare.pdf";
							
							pdfUtils.generateToFile(path, "resources/details_whenCompare.html",path+"resources/", data_map, outputPath);
							
							List<FileInfo> fileInfoList = (List<FileInfo>) data_map.get("fileInfos");
							String baseFileName="";
							if(fileInfoList.get(0).getFileName().equals(jingpinFileName)){
								baseFileName=fileInfoList.get(1).getFileName();
							}else{
								baseFileName=fileInfoList.get(0).getFileName();
							}
							
							String finalPath = pdfUti.pdfMethod_whenCompare_byFtl(request,
															   response,
															   request.getParameter("watermark"),
															   request.getParameter("title"),
															   baseFileName, 
															   jingpinFileName, 
															   (String)request.getSession().getAttribute("username"));
							
							PDFUti.download(response, finalPath);
							
					} catch (Exception e) {
							e.printStackTrace();
					}
			}else{//非比较时
					initData4ftlWhenSingle(data_map,request,whoShowMap);
					
					List<FileInfo> fileInfoList = (List<FileInfo>) data_map.get("fileInfos");
					List<BasicConclusion> basicConclusionList = (List<BasicConclusion>) data_map.get("basicConclusions");
					net.sf.json.JSONObject whoShowMap_jsonobj = net.sf.json.JSONObject.fromObject(whoShowMap);
					
					if(fileInfoList!=null&&fileInfoList.size()>0){
							String path=PdfHelper.getPath();
							
							String webpath = request.getRealPath("/");
					    	String outputPath=webpath+ "tempPdf//"+request.getSession().getAttribute("username")+"//";
					    	File pdfDir = new File(outputPath);
					    	if(pdfDir.exists()){
					    		pdfDir.delete();
					    	}
					    	pdfDir.mkdirs();
					    	
					    	List<String>  filePathInZips=new ArrayList<String>();
				    	
							for (int i=0;i< fileInfoList.size();i++) {
							        FileInfo fileInfo = fileInfoList.get(i);
							        BasicConclusion basicConclusion = basicConclusionList.get(i);
									try {
											HashMap<String, Object> data = new HashMap<String,Object>();
											data.put("fileInfo", fileInfo);
											data.put("basicConclusion", basicConclusion);
											data.put("whoShow", whoShowMap_jsonobj);
											
									    	String currOutputPath=outputPath+"details_single_"+fileInfo.getFileName()+".pdf";
											pdfUtils.generateToFile(path, "resources/details_single.html",path+"resources/", data, currOutputPath);
											
											
											String finalPath = pdfUti.pdfMethod_single_byFtl(request,
																			   response,
																			   request.getParameter("watermark"),
																			   request.getParameter("title"),
																			   fileInfo.getFileName(), 
																			   (String)request.getSession().getAttribute("username"));
											filePathInZips.add(finalPath);
									} catch (Exception e) {
											e.printStackTrace();
									}
							}
	
							if (filePathInZips.size() > 1) {
									ZipUtil.downloadZip(response, filePathInZips);
							} else if (filePathInZips.size() == 1) {
									PDFUti.download(response, filePathInZips.get(0));
							}
					}
			}
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void initData4ftlWhenSingle(Map<String, Object> data, HttpServletRequest request, String whoShowMapStr) {
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
	
		List<FileInfo> fileInfoList = (List<FileInfo>) data.get("fileInfos");//fileInfo即是baowen
		ArrayList<BasicConclusion> basicConclusionList = new ArrayList<BasicConclusion>();//基本结论(比较时有有2个，第一个是基础的，第二个是竞品)，非比较时，个数不确定
		
		net.sf.json.JSONObject whoShowMap_jsonobj = net.sf.json.JSONObject.fromObject(whoShowMapStr);
		
		data.put("whoShow",whoShowMap_jsonobj);
		
		BasicConclusions basicConclusions = new BasicConclusions();
		
		//ip归属地begin************************************
		HttpSession session = request.getSession();
		String[] filecheck = (String[]) session.getAttribute("filecheck");
		String userName = (String)session.getAttribute("username");
		ShardedJedis jedis = null;
		jedis = shardedJedisPool.getResource();
		Map<String, String> tempMap = ipVerify(jedis,filecheck,userName);
		
		HashMap<String, String> ipAddrMap = new HashMap<String,String>();
	    Set<Entry<String, String>> entrySet2 = tempMap.entrySet();
	    for (Entry<String, String> entry : entrySet2) {
			   String key = entry.getKey();
			   String value = entry.getValue();
			   if(value!=null){
				   value=value.replaceAll("</?[^>]+>", "");
			   }
			   ipAddrMap.put( key.substring(key.indexOf("-")+1), value);
		}
		//ip归属地end************************************
		for(int i=0;i<fileInfoList.size();i++){
				 FileInfo currFileInfo = fileInfoList.get(i);
				 
				 List<ServiceIP> serviceIPList = currFileInfo.getServiceIP();
				 if(serviceIPList!=null&&tempMap.size()>0){
						 for (ServiceIP serviceIP : serviceIPList) {
							 	String ip = serviceIP.getServiceIP();
							 	serviceIP.setIpAddr(ipAddrMap.get(ip));
						 }
				 }
				 
				 BasicConclusion basicConclusion = new BasicConclusion();
				 basicConclusion.setAdviceNum(0);//建议个数(也即问题个数)
				 
				 basicConclusion.setFileName(currFileInfo.getFileName());
				 basicConclusion.setDnsTimeDelayedcount(currFileInfo.getDnsTimeDelayedcount());
				 basicConclusion.setPacketCount(currFileInfo.getPacketCount());//包数,
				 basicConclusion.setTcpCount(currFileInfo.getTcpCount());//链路数
				 basicConclusion.setExchangeTimeCount(currFileInfo.getExchangeTimeCount());//交互时间
				 
				 basicConclusion.setIpCount(currFileInfo.getIpCount());
				 basicConclusion.setOthersIpCount(currFileInfo.getOthersIpCount());
				 basicConclusion.setOthersTcpCount(currFileInfo.getOthersTcpCount());
				 basicConclusion.setPermissableParameterCount(currFileInfo.getPermissableParameterCount());
				 basicConclusion.setNoPermissablePara(currFileInfo.getNoPermissablePara());
				 basicConclusion.setNoPermissableParaNum(currFileInfo.getNoPermissableParaNum());
				 
				 if(currFileInfo.getProviceIpFlag()!=null&&currFileInfo.getProviceIpFlag().equals("0")){
					 	basicConclusion.setCrossProviceNum(Integer.valueOf(currFileInfo.getProviceIpaddrNum()));//获取跨区域个数
					 	basicConclusion.setCrossProvice(currFileInfo.getProviceIpAddr());//获取跨区域省份
				 }
				 
				 if(basicConclusion.getCrossProviceNum()>1){//存在"访问服务器跨区域"问题
					 	basicConclusion.setAdviceNum(basicConclusion.getAdviceNum()+1);
				 }
				 
				 basicConclusion.setDownloadRate(currFileInfo.getDownloadRate());//TCP windos问题
				 if(basicConclusion.getDownloadRate()!=null&&!basicConclusion.getDownloadRate().equals("N/A")){//存在"TCP windos问题"问题
					 	basicConclusion.setAdviceNum(basicConclusion.getAdviceNum()+1);
				 }
				 String res="";
				 
				 if(basicConclusion.getCrossProviceNum()>1){
					 	res+="、访问服务器跨区域 ";
				 }
				 
				 if(currFileInfo.getSumOftenOffVerdict()>0){
					 	res += "、频繁拆建链 ";
				 }
				 
				 if(basicConclusion.getDownloadRate()!=null&&!basicConclusion.getDownloadRate().equals("N/A")){
					 	res += "、链路不支持 TCP WINDOW SCALE OPTION参数";
				 }
						
				 if(res!=null&&res.length()>0){
					 basicConclusion.setConclusionRes(res.substring(1));
				 }
				 basicConclusion.setSumOftenOffVerdict(currFileInfo.getSumOftenOffVerdict());//频繁拆建链共涉及多少次
				 
				 if(basicConclusion.getSumOftenOffVerdict()!=0){//存在"频繁拆建链"问题
					 	basicConclusion.setAdviceNum(basicConclusion.getAdviceNum()+1);
				 }
				 basicConclusionList.add(basicConclusion);
		}
		data.put("basicConclusions", basicConclusionList);
	}

	
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void initData4ftlWhenCompare(Map<String, Object> data,String jingpinFileName,HttpServletRequest request,String whoShowMapStr) {
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
		
			List<FileInfo> fileInfoList = (List<FileInfo>) data.get("fileInfos");//fileInfo即是baowen
			ArrayList<BasicConclusion> basicConclusionList = new ArrayList<BasicConclusion>();//基本结论(比较时有有2个，第一个是基础的，第二个是竞品)，非比较时，个数不确定
			
			String norm_graph_str = (String) data.get("norm_graph");
			
			net.sf.json.JSONObject norm_graph_jsonobj = net.sf.json.JSONObject.fromObject(norm_graph_str);
			net.sf.json.JSONObject whoShowMap_jsonobj = net.sf.json.JSONObject.fromObject(whoShowMapStr);
			
			data.put("whoShow",whoShowMap_jsonobj);
			
			String graph_pcakageLen_str = (String) norm_graph_jsonobj.get("包长分布图");
			
			JSONArray graph_pcakageLen_jsonarr = net.sf.json.JSONArray.fromObject(graph_pcakageLen_str);
			
			//var crossProvice;
			boolean mark=true;
			BasicConclusions basicConclusions = new BasicConclusions();
			
			
			
			//ip归属地begin************************************
			//美国/华盛顿州西雅图市亚马逊(Amazon)公司数据中心美国/华盛顿州西雅图市亚马逊</br>(Amazon)公司数据中心
			HttpSession session = request.getSession();
			String[] filecheck = (String[]) session.getAttribute("filecheck");
			String userName = (String)session.getAttribute("username");
			ShardedJedis jedis = null;
			jedis = shardedJedisPool.getResource();
			Map<String, String> tempMap = ipVerify(jedis,filecheck,userName);
			
			HashMap<String, String> ipAddrMap = new HashMap<String,String>();
		    Set<Entry<String, String>> entrySet2 = tempMap.entrySet();
		    for (Entry<String, String> entry : entrySet2) {
				   String key = entry.getKey();
				   String value = entry.getValue();
				   if(value!=null){
					   value=value.replaceAll("</?[^>]+>", "");
				   }
				   ipAddrMap.put( key.substring(key.indexOf("-")+1), value);
			}
			//ip归属地end************************************
			
			for(int i=0;i<fileInfoList.size();i++){
					 FileInfo currFileInfo = fileInfoList.get(i);
					 
					 List<ServiceIP> serviceIPList = currFileInfo.getServiceIP();
					 if(serviceIPList!=null&&tempMap.size()>0){
							 for (ServiceIP serviceIP : serviceIPList) {
								 	String ip = serviceIP.getServiceIP();
								 	serviceIP.setIpAddr(ipAddrMap.get(ip));
							 }
					 }
					 
					 BasicConclusion basicConclusion = new BasicConclusion();
					 basicConclusion.setAdviceNum(0);//建议个数(也即问题个数)
				
					 String curr_graph_pcakageLen_str = (String) graph_pcakageLen_jsonarr.get(i);
					 net.sf.json.JSONObject curr_graph_pcakageLen_jsonobj = net.sf.json.JSONObject.fromObject(curr_graph_pcakageLen_str);
					 
					 String list_y_arrStr = (String) curr_graph_pcakageLen_jsonobj.get("list_y");
					 
					 JSONArray list_y_jsonarr = net.sf.json.JSONArray.fromObject(list_y_arrStr);
					 net.sf.json.JSONObject smallPacket = (net.sf.json.JSONObject) list_y_jsonarr.get(0);
					 net.sf.json.JSONObject mediumPacket = (net.sf.json.JSONObject) list_y_jsonarr.get(1);
					 net.sf.json.JSONObject largePacket = (net.sf.json.JSONObject) list_y_jsonarr.get(2);
					 
					 float smallPacketCount = Float.parseFloat(smallPacket.get("value")+"");
					 float mediumPacketCount =Float.parseFloat( mediumPacket.get("value")+"");
					 float largePacketCount = Float.parseFloat( largePacket.get("value")+"");
					   					 
					 float  pcaketCount=smallPacketCount+mediumPacketCount+largePacketCount;
					 
					 float smallPacketPercent=smallPacketCount/pcaketCount*100;
					 float mediumPacketPercent=mediumPacketCount/pcaketCount*100;
					 float largePacketPercent=largePacketCount/pcaketCount*100;
					 
					 basicConclusion.setSmallPacketPercent(df.format(smallPacketPercent));
					 basicConclusion.setMediumPacketPercent(df.format(mediumPacketPercent));
					 basicConclusion.setLargePacketPercent(df.format(largePacketPercent));
					 
					 basicConclusion.setFileName(currFileInfo.getFileName());
					 basicConclusion.setDnsTimeDelayedcount(currFileInfo.getDnsTimeDelayedcount());
					 basicConclusion.setPacketCount(currFileInfo.getPacketCount());//包数,
					 basicConclusion.setTcpCount(currFileInfo.getTcpCount());//链路数
					 basicConclusion.setExchangeTimeCount(currFileInfo.getExchangeTimeCount());//交互时间
					 
					 basicConclusion.setIpCount(currFileInfo.getIpCount());
					 basicConclusion.setOthersIpCount(currFileInfo.getOthersIpCount());
					 basicConclusion.setOthersTcpCount(currFileInfo.getOthersTcpCount());
					 basicConclusion.setPermissableParameterCount(currFileInfo.getPermissableParameterCount());
					 basicConclusion.setNoPermissablePara(currFileInfo.getNoPermissablePara());
					 basicConclusion.setNoPermissableParaNum(currFileInfo.getNoPermissableParaNum());
					 
					 if(currFileInfo.getProviceIpFlag()!=null&&currFileInfo.getProviceIpFlag().equals("0")){
						 	basicConclusion.setCrossProviceNum(Integer.valueOf(currFileInfo.getProviceIpaddrNum()));//获取跨区域个数
						 	basicConclusion.setCrossProvice(currFileInfo.getProviceIpAddr());//获取跨区域省份
					 }
					 
					 if(basicConclusion.getCrossProviceNum()>1){//存在"访问服务器跨区域"问题
						 	basicConclusion.setAdviceNum(basicConclusion.getAdviceNum()+1);
					 }
					 
					 basicConclusion.setDownloadRate(currFileInfo.getDownloadRate());//TCP windos问题
					 if(basicConclusion.getDownloadRate()!=null&&!basicConclusion.getDownloadRate().equals("N/A")){//存在"TCP windos问题"问题
						 	basicConclusion.setAdviceNum(basicConclusion.getAdviceNum()+1);
					 }
					 
					 String res="";
					 
					 if(basicConclusion.getCrossProviceNum()>1){
						 	res+="、访问服务器跨区域 ";
					 }
					 
					 if(currFileInfo.getSumOftenOffVerdict()>0){
						 	res += "、频繁拆建链 ";
					 }
					 
					 if(basicConclusion.getDownloadRate()!=null&&!basicConclusion.getDownloadRate().equals("N/A")){
						 	res += "、链路不支持 TCP WINDOW SCALE OPTION参数";
					 }
							
					 if(res!=null&&res.length()>0){
						 basicConclusion.setConclusionRes(res.substring(1));
					 }
					 
					 if(mark && res.length()>0){
						 	mark=false;
					 }
							
					 basicConclusion.setSumOftenOffVerdict(currFileInfo.getSumOftenOffVerdict());//频繁拆建链共涉及多少次
					 
					 if(basicConclusion.getSumOftenOffVerdict()!=0){//存在"频繁拆建链"问题
						 	basicConclusion.setAdviceNum(basicConclusion.getAdviceNum()+1);
					 }
					 
					//DNS时延(ms)	建链时延(ms)	建链后首包时延(ms)	TCP键连时延(ms)	平均RTT时间(ms)	交互时间(ms)
					 basicConclusion.setAvgDnsDelsyTs(currFileInfo.getAvgDnsDelsyTs());
					 basicConclusion.setAvgOffTimeDelayed(currFileInfo.getAvgOffTimeDelayed());
					 basicConclusion.setAvgTimeToFirstByte(currFileInfo.getAvgTimeToFirstByte());
					 basicConclusion.setAvgTcpTimeDelayed(currFileInfo.getAvgTcpTimeDelayed());
					 basicConclusion.setAvgRttTime(currFileInfo.getAvgRttTime());
					 basicConclusion.setAvgExchangeTime(currFileInfo.getAvgExchangeTime());
					 basicConclusion.setTimeEfficiencyAVG(currFileInfo.getTimeEfficiencyAVG());
					 basicConclusion.setLowestEffLink(currFileInfo.getLowestEffLink());
					 basicConclusion.setExchangeFlowCount(currFileInfo.getExchangeFlowCount());//交互流量
					 basicConclusion.setFlowBigIp(currFileInfo.getFlowBigIp());
					 basicConclusion.setFlowBigPort(currFileInfo.getFlowBigPort());
					 //	basicConclusion.flow=currBaowen.flow;?????
					 basicConclusion.setDnsBigIp(currFileInfo.getDnsBigIp());
					 basicConclusion.setDnsBig(currFileInfo.getDnsBig());
					 basicConclusion.setOffTimeBigIp(currFileInfo.getOffTimeBigIp());
					 basicConclusion.setOffTimeBigPort( currFileInfo.getOffTimeBigPort());
					 basicConclusion.setOffTimeBig(currFileInfo.getOffTimeBig());
					 basicConclusion.setTimeToFirstBigIp(currFileInfo.getTimeToFirstBigIp());
					 basicConclusion.setTimeToFirstBigPort(currFileInfo.getTimeToFirstBigPort());
					 basicConclusion.setTimeToFirstBig(currFileInfo.getTimeToFirstBig());
					 basicConclusion.setTcpTimeBigIp(currFileInfo.getTcpTimeBigIp());
					 basicConclusion.setTcpTimeBigPort(currFileInfo.getTcpTimeBigPort());
					 basicConclusion.setTcpTimeBig(currFileInfo.getTcpTimeBig());
					 basicConclusion.setRttTimeBigIp( currFileInfo.getRttTimeBigIp());
					 basicConclusion.setRttTimeBigPort(currFileInfo.getRttTimeBigPort());
					 basicConclusion.setRttTimeBig(currFileInfo.getRttTimeBig());
					 basicConclusion.setTimeEfficiencyLowest(currFileInfo.getTimeEfficiencyLowest());
					 basicConclusion.setMaxFlowIP(currFileInfo.getMaxFlowIP());
					 basicConclusion.setMaxFlowPort(currFileInfo.getMaxFlowPort());
					 basicConclusion.setAllFileDataTimes(currFileInfo.getAllFileDataTimes());
					 basicConclusion.setExchangeTimeBigIp(currFileInfo.getExchangeTimeBigIp());
					 basicConclusion.setExchangeTimeBigPort(currFileInfo.getExchangeTimeBigPort());
					 basicConclusion.setExchangeTimeBig(currFileInfo.getExchangeTimeBig());
					 
					 basicConclusionList.add(basicConclusion);
			}
			
			basicConclusions.setData(basicConclusionList);
			
			if(!mark){
					basicConclusions.setStr1("基本结论");
			}
			//if($('#checkbox_compareConclusion')[0].checked){//此时basicConclusions长度为2
			List array=new ArrayList(); 
			boolean isExchange = false;
			if(basicConclusions.getData().get(0).getFileName().equals(jingpinFileName)){//交换顺序使得第一个是基础的，第二个是竞品
					  BasicConclusion basicConclusion_a = basicConclusions.getData().get(0);
					  BasicConclusion basicConclusion_b = basicConclusions.getData().get(1);
					  
					  ArrayList<BasicConclusion> list = new ArrayList<BasicConclusion>();
					  list.add(basicConclusion_b);
					  list.add(basicConclusion_a);
					  
					  basicConclusions.setData(list);
					  isExchange = true;
			}
			
			
			
			CompareInfo compareInfo = new CompareInfo();
			
			//交互流量的对比信息
			putCompareResult(compareInfo,"smallPacketPercent",basicConclusions.getData().get(0).getSmallPacketPercent(),basicConclusions.getData().get(1).getSmallPacketPercent(),-1);
			putCompareResult(compareInfo,"mediumPacketPercent",basicConclusions.getData().get(0).getMediumPacketPercent(),basicConclusions.getData().get(1).getMediumPacketPercent(),-1);
			putCompareResult(compareInfo,"largePacketPercent",basicConclusions.getData().get(0).getLargePacketPercent(),basicConclusions.getData().get(1).getLargePacketPercent(),-1);
			putCompareResult(compareInfo,"packetCount",basicConclusions.getData().get(0).getPacketCount()+"",basicConclusions.getData().get(1).getPacketCount()+"",-1);
			
			String pcakageCompareMsg=null;
		    if(compareInfo.getSmallPacketPercent().equals("劣于")){//小包低
				  	pcakageCompareMsg="小包 比例多于竞品";
			  	  	if(compareInfo.getMediumPacketPercent().equals("劣于")){
			  	  			pcakageCompareMsg+="、中包 比例多于竞品";
			  	  	}
		    }
		    if(pcakageCompareMsg!=null){
		    		compareInfo.setPcakageCompareMsg(pcakageCompareMsg);
		    }
		    
		    //比较“包数(个)	IP数(个)	链路数(个)	交互流量(byte)”
			putCompareResult(compareInfo,"packetCount",basicConclusions.getData().get(0).getPacketCount()+"",basicConclusions.getData().get(1).getPacketCount()+"",-1);
			putCompareResult(compareInfo,"ipCountRes",basicConclusions.getData().get(0).getIpCount()+"",basicConclusions.getData().get(1).getIpCount()+"",-1);
			putCompareResult(compareInfo,"exchangeFlowCount",basicConclusions.getData().get(0).getExchangeFlowCount()+"",basicConclusions.getData().get(1).getExchangeFlowCount()+"",-1);
			
			compareInfo.setPercentDiff_avgExchangeTime(calcuPercentDiff(Float.parseFloat(basicConclusions.getData().get(0).getAvgExchangeTime()),Float.parseFloat(basicConclusions.getData().get(1).getAvgExchangeTime())));
			
			putCompareResult(compareInfo,"tcpCountRes",basicConclusions.getData().get(0).getTcpCount()+"",basicConclusions.getData().get(1).getTcpCount()+"",-1);
			
			
		    String tcpCountCompareMsg=null;
		    if(compareInfo.getTcpCountRes().equals("劣于") || compareInfo.getIpCountRes().equals("劣于")){
		    		tcpCountCompareMsg="链路数多：";
		    }
		    
		    compareInfo.setTcpCount(basicConclusions.getData().get(0).getTcpCount());
	    	compareInfo.setFileName(basicConclusions.getData().get(0).getFileName());
	    	
	    	float tcpCount1 = basicConclusions.getData().get(0).getTcpCount();
	    	float tcpCount2 = basicConclusions.getData().get(1).getTcpCount();
		    if(compareInfo.getTcpCountRes().equals("劣于")){//链路数(tcpCount)多
		    		compareInfo.setTcpCountPercent(df.format((tcpCount1/tcpCount2-1)*100));
		    }else{
		    		compareInfo.setTcpCountPercent(df.format((1-tcpCount1/tcpCount2)*100));
		    }
		    if(tcpCountCompareMsg!=null){
		    		compareInfo.setTcpCountCompareMsg(tcpCountCompareMsg);
		    }
		    
		    
			//“交互时间”下面的对比信息
			putCompareResult(compareInfo,"timeEfficiencyAVG",basicConclusions.getData().get(0).getTimeEfficiencyAVG(),basicConclusions.getData().get(1).getTimeEfficiencyAVG(),1);
			//列出效率最低的链路端口号
			if(compareInfo.getTimeEfficiencyAVG().equals("劣于")){
					compareInfo.setLowestEffLink(basicConclusions.getData().get(0).getLowestEffLink());
			}
			
			putCompareResult(compareInfo,"avgDnsDelsyTs",basicConclusions.getData().get(0).getAvgDnsDelsyTs(),basicConclusions.getData().get(1).getAvgDnsDelsyTs(),-1);
			putCompareResult(compareInfo,"avgTcpTimeDelayed",basicConclusions.getData().get(0).getAvgTcpTimeDelayed(),basicConclusions.getData().get(1).getAvgTcpTimeDelayed(),-1);
			putCompareResult(compareInfo,"avgTimeToFirstByte",basicConclusions.getData().get(0).getAvgTimeToFirstByte(),basicConclusions.getData().get(1).getAvgTimeToFirstByte(),-1);
			putCompareResult(compareInfo,"avgOffTimeDelayed",basicConclusions.getData().get(0).getAvgOffTimeDelayed(),basicConclusions.getData().get(1).getAvgOffTimeDelayed(),-1);
			putCompareResult(compareInfo,"avgRttTime",basicConclusions.getData().get(0).getAvgRttTime(),basicConclusions.getData().get(1).getAvgRttTime(),-1);
			putCompareResult(compareInfo,"avgExchangeTime",basicConclusions.getData().get(0).getAvgExchangeTime(),basicConclusions.getData().get(1).getAvgExchangeTime(),-1);//-1表示越小越优，显然‘延迟’是越小越优
			putCompareResult(compareInfo,"exchangeTimeCount",basicConclusions.getData().get(0).getExchangeTimeCount(),basicConclusions.getData().get(1).getExchangeTimeCount(),-1);
			
			
			array=new ArrayList();
			if(compareInfo.getTimeEfficiencyAVG().equals("劣于")){
					basicConclusions.getData().get(0).setAdviceNum(basicConclusions.getData().get(0).getAdviceNum()+1);
					
					float timeEfficiencyAVG1 = Float.parseFloat(basicConclusions.getData().get(0).getTimeEfficiencyAVG());
					float timeEfficiencyAVG2 = Float.parseFloat(basicConclusions.getData().get(1).getTimeEfficiencyAVG());
					
					compareInfo.setTimeEfficiencyAVGNum(df.format((timeEfficiencyAVG1/timeEfficiencyAVG2)*100));
					array.add("链路效率");
			}
			if(compareInfo.getExchangeTimeCount().equals("劣于")){
					basicConclusions.getData().get(0).setAdviceNum(basicConclusions.getData().get(0).getAdviceNum()+1);
			}	
				
			float a0 = Float.parseFloat(basicConclusions.getData().get(0).getExchangeTimeCount());
			float a1 = Float.parseFloat(basicConclusions.getData().get(1).getExchangeTimeCount());
			float  a0_a1 = Math.abs(a0/a1);
			
			float res;
			if(compareInfo.getExchangeTimeCount().equals("劣于")){
					res = Math.abs(a0_a1-1);
			}else{
					res = Math.abs(1-a0_a1);
			}
				
			compareInfo.setExchangeTimeCountNum(df.format(res*100));
			array.add("时间消耗");
			
			if(compareInfo.getExchangeFlowCount().equals("劣于")){
					basicConclusions.getData().get(0).setAdviceNum(basicConclusions.getData().get(0).getAdviceNum()+1);
					float exchangeFlowCount1 = (float) basicConclusions.getData().get(0).getExchangeFlowCount();
					float exchangeFlowCount2 = (float) basicConclusions.getData().get(1).getExchangeFlowCount();
					compareInfo.setExchangeFlowCountNum(df.format((exchangeFlowCount1/exchangeFlowCount2-1)*100));
					array.add("流量消耗");
			}
			if(pcakageCompareMsg!=null){
					basicConclusions.getData().get(0).setAdviceNum(basicConclusions.getData().get(0).getAdviceNum()+1);
					array.add("报文结构");
			}
			if(compareInfo.getTcpCountRes().equals("劣于")){
					basicConclusions.getData().get(0).setAdviceNum(basicConclusions.getData().get(0).getAdviceNum()+1);
					array.add("链路多");
			}
				
			compareInfo.setWholeRes(array);
			
			//列出平均值比较结果，效率的指标
			String res_="";
			if(compareInfo.getAvgDnsDelsyTs().equals("劣于")){
					float avgDnsDelsyTs1 = Float.parseFloat(basicConclusions.getData().get(0).getAvgDnsDelsyTs());
					float avgDnsDelsyTs2 = Float.parseFloat(basicConclusions.getData().get(1).getAvgDnsDelsyTs());
					compareInfo.setAvgDnsDelsyTsNum(df.format((avgDnsDelsyTs1/avgDnsDelsyTs2-1)*100));
					res_ += "、DNS时延";
			}
			
			if(compareInfo.getAvgTcpTimeDelayed().equals("劣于")){
					float avgTcpTimeDelayed1 = Float.parseFloat(basicConclusions.getData().get(0).getAvgTcpTimeDelayed());
					float avgTcpTimeDelayed2 = Float.parseFloat(basicConclusions.getData().get(1).getAvgTcpTimeDelayed());
					compareInfo.setAvgTcpTimeDelayedNum(df.format((avgTcpTimeDelayed1/avgTcpTimeDelayed2-1)*100));
					res_ += "、TCP建链时延";
			}
			

			if(compareInfo.getAvgTimeToFirstByte().equals("劣于")){
					float avgTimeToFirstByte1 = Float.parseFloat(basicConclusions.getData().get(0).getAvgTimeToFirstByte());
					float avgTimeToFirstByte2 = Float.parseFloat(basicConclusions.getData().get(1).getAvgTimeToFirstByte());
					compareInfo.setAvgTimeToFirstByteNum(df.format((avgTimeToFirstByte1/avgTimeToFirstByte2-1)*100));
					res_ += "、建链后首包时延";
			}
			

			if(compareInfo.getAvgOffTimeDelayed().equals("劣于")){
					float avgOffTimeDelayed1 = Float.parseFloat(basicConclusions.getData().get(0).getAvgOffTimeDelayed());
					float avgOffTimeDelayed2 = Float.parseFloat(basicConclusions.getData().get(1).getAvgOffTimeDelayed());
					compareInfo.setAvgOffTimeDelayedNum(df.format((avgOffTimeDelayed1/avgOffTimeDelayed2-1)*100));
					res_ += "、断链时延";
			}
			
			if(compareInfo.getAvgRttTime().equals("劣于")){
					float avgRttTime1 = Float.parseFloat(basicConclusions.getData().get(0).getAvgRttTime());
					float avgRttTime2 = Float.parseFloat(basicConclusions.getData().get(1).getAvgRttTime());
					compareInfo.setAvgRttTimeNum(df.format((avgRttTime1/avgRttTime2-1)*100));
					res_ += "、RTT时间";
			}
			
			if(compareInfo.getAvgExchangeTime().equals("劣于")){
					float avgExchangeTime1 = Float.parseFloat(basicConclusions.getData().get(0).getAvgExchangeTime());
					float avgExchangeTime2 = Float.parseFloat(basicConclusions.getData().get(1).getAvgExchangeTime());
					compareInfo.setAvgExchangeTimeNum(df.format((avgExchangeTime1/avgExchangeTime2-1)*100));
					res_ += "、交互时间";
			}
			
			if(res_!=null&&res_.length()>0){
				compareInfo.setCompareRes(res_.substring(1));
			}
			
			basicConclusions.setCompareInfo(compareInfo);
			
			data.put("basicConclusions", basicConclusions);
			//}
	}
	
	//计算相差百分比
	private String calcuPercentDiff(float num1,float num2){
			float  percentDiff= 0;
			if(num1>num2){
					percentDiff = num2/num1*100;
			}else{
					percentDiff = num1/num2*100;
			}
			DecimalFormat df = new DecimalFormat("0.00");//格式化小数，不足的补0
			return df.format(percentDiff)+"%";
	}
	private void putCompareResult(CompareInfo compareInfo,String compareField,String data_base,String data_jingpin,int compareRule){//1和-1
			String res="";
			try {
					float  data_base_f=Float.parseFloat(data_base);
					float  data_jingpin_f=Float.parseFloat(data_jingpin);
				    if((data_base_f*compareRule)>(data_jingpin_f*compareRule)){//基础"优于"竞品
				    		res="优于";
					}else if((data_base_f*compareRule)<(data_jingpin_f*compareRule)){
							res="劣于";
					}else if(data_base==data_jingpin){
							res="相似于";
					}
			} catch (Exception e) {
					res="N/A";
			}
			
			Field field;
			try {
					
					field = CompareInfo.class.getDeclaredField(compareField);
					field.setAccessible(true);
				    field.set(compareInfo, res);
			} catch (Exception e) {
					e.printStackTrace();
			}
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) throws InterruptedException, ExecutionException {
		
	
		
		if(!effecTive(request, response)) {
			return null;
		}
		
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("username");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = sdf.format(new Date());
		LogThreads.username = userName;
		ShardedJedis jedis = shardedJedisPool.getResource();
		
		String[] filechecks = request.getParameter("filecheck").split(",");//已选中文件
		List<String>  list = new ArrayList<String>();
		int fiSi = 0;
		while (fiSi<filechecks.length) {
			list.add(filechecks[fiSi++].replace(".pcap", ""));
		}
		String[] filecheck = list.toArray(new String[list.size()]); 
		session.setAttribute("filecheck", filecheck);
		
		List<String> redisNoFile = new ArrayList<String>();//redis中没有的文件
		String[] fileNames = new String[filecheck.length];
		String normData = request.getParameter("norm");

		
		String redisFileTcpGraph = "";//redis中图形数据
		String redisFileFileInfo = "";//redis中详细数据
		//循环选中文件 filecheck, 判断redis是否已有文件数据, 选出redis中没有的文件数据进行解析
		log.info("全部选中文件-------" + JSONArray.fromObject(filecheck));
		for(int i=0; i<filecheck.length; i++) {
			redisFileFileInfo = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.FILE_INFO);
			redisFileTcpGraph = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.TCP_GRAPH);
			//判断从jedis中取出的数据是否为空, 如果为空则需要解析
			if (StringUtil.isEmpty(redisFileFileInfo) && StringUtil.isEmpty(redisFileTcpGraph)) {
				redisNoFile.add(filecheck[i]);
			}
			fileNames[i] = filecheck[i].substring(filecheck[i].indexOf("@") + 1);
		}
		
		if (redisNoFile != null && redisNoFile.size() > 0) {
			//解析redis中没有的文件, 并存入redis中
			log.info("redis中没有的文件-------" + JSONArray.fromObject(redisNoFile));
			logsAnalyzeService.logAscription(normData,redisNoFile, service);
		} 
		
		Map<String, Object> list_maps = new HashMap<String, Object>();
		Map<String, Object> redisResultGraphMap = new HashMap<String, Object>();
		FileInfo fileInfo;
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		
		String fileName = null;
		String fileIpAddr = null;
		String ipState;
		List<String> redisFileSerIP;
		Map<String, String> map0 = new HashMap<String, String>();		
		//再次遍历所有选中文件, 从redis中将数据取出
		for(int i=0; i<filecheck.length; i++) {
			redisFileFileInfo = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.FILE_INFO);
			redisFileTcpGraph = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.TCP_GRAPH);
			redisResultGraphMap = JSONObject.parseObject(redisFileTcpGraph, Map.class);
			list_maps.putAll(redisResultGraphMap);
			fileInfo = JSONObject.parseObject(redisFileFileInfo, FileInfo.class);
			String proviceIpflag = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.CROSS_PROVICE_IPFLAG);
			
			//从redis中取出redis中没有ip归属地的ip,进行ip归属地查询

			fileName = filecheck[i].split("@")[1];
			ipState = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS_ASCRIPTION_STATE);
			if(ipState==null || ipState.equals("1")){
				redisFileSerIP = jedis.lrange(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS, 0, -1);
				try {
					ipAddService.ipAscription(redisFileSerIP, filecheck[i], userName);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("IP归属地查询异常------------------------\n"+e);
				}
				//取出相应 IP 以及归属地
				fileIpAddr = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS_ASCRIPTION);
				map0 = JSONObject.parseObject(fileIpAddr, Map.class);
				if(fileIpAddr != null){
					map0 = crossProvice(jedis, map0,fileName,userName);
					fileInfo.setProviceIpFlag("0");
					fileInfo.setProviceIpAddr(map0.get(fileName));
					fileInfo.setProviceIpaddrNum(map0.get(fileName+"Num"));
				}
			}else{
				fileIpAddr = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS_ASCRIPTION);
				map0 = JSONObject.parseObject(fileIpAddr, Map.class);
				if(proviceIpflag==null || proviceIpflag.equals("1")){
					map0 = crossProvice(jedis, map0,fileName,userName);
					fileInfo.setProviceIpFlag("0");
					fileInfo.setProviceIpAddr(map0.get(fileName));
					fileInfo.setProviceIpaddrNum(map0.get(fileName+"Num"));
				}else{
					fileInfo.setProviceIpFlag("0");
					fileInfo.setProviceIpAddr(jedis.get(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPADDR));
					fileInfo.setProviceIpaddrNum(jedis.get(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPADDRNUM));
				}
			}
			fileInfos.add(fileInfo);
		}
		       
		//系统信息
		SysInfo sysInfo = new SysInfo();
		sysInfo.setBusinessType(SysInfoConfig.BUSINESS_TYPE_01);
		sysInfo.setUserName(userName);
		sysInfo.setVersion(SysInfoConfig.VERSION);
//		sysInfo.setTerminalBrand(""); 
//		sysInfo.setTerminalModel("");	
		sysInfo.setStartTime(startTime);
//		String[] graphFi = recursion(4, fileNames);
		
		//文件解析结果
		Map<String, Object> data_map = new HashMap<String, Object>();
//		list_maps.put("graphFi", graphFi);
		list_maps.put("filecheck", fileNames);
		indexDataServiceImpl ids = new indexDataServiceImpl();
		data_map.put("norm_graph", ids.norm_graph(list_maps,fileInfos));       //  图形展示
		data_map.put("filecheck", JSONObject.toJSONString(filecheck));       //  文件名称列表
		data_map.put("sysInfo", sysInfo);         
		
//		data_map.put("fileInfos", infos.get("fileInfos"));  
		data_map.put("fileInfos", fileInfos);  
		return data_map;
	}
	
	
	/**
	 * @param max	单个文件名最大数组长度
	 * @param fileNames	文件名
	 * @return
	 */
	@SuppressWarnings("unused")
	private String[] recursion(int max, String[] fileNames) {
		
		log.info("文件名根据业务缩减**************begin");
		
		List<String[]> arrNames =new ArrayList<>(); 
		int fiInSi = fileNames.length;
		for(int i=0;i<fiInSi;i++){
			arrNames.add(fileNames[i].split("_"));
		}
		String[] graphFi = fileNames.clone();
		
		int 	no = 0;
		int 	k=0;
		int 	mark;
		String 	fileName;
		while(no<max){
			
			for(int i=0;i<arrNames.size();i++){
				mark=0;
				fileName=arrNames.get(i)[no];
				for(int j=0;j<arrNames.size();j++){
					if(mark>1)
						break;
					if(fileName.equals(arrNames.get(j)[no]))
						++mark;
				}
				if(mark==1){
					fileName = arrNames.get(i)[0];
					while (k<no)
						fileName += arrNames.get(i)[k++];
					graphFi[i]=fileName;
				}
			}
			if(arrNames.isEmpty())
				break;
			no++;
		}
		log.info("文件名根据业务缩减--------------end");
		return graphFi;
	}
	@RequestMapping(value="/ipAddr.do",method=RequestMethod.GET)
	public void ipAddr(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info("IP 归属........");
		
		HttpSession session = request.getSession();
		String[] filecheck = (String[]) session.getAttribute("filecheck");
		String userName = (String)session.getAttribute("username");
		
		if(!effecTive(request, response)) {
			return;
		}
		
		ShardedJedis jedis = null;
		jedis = shardedJedisPool.getResource();
		
		Map<String, String> map = ipVerify(jedis,filecheck,userName);
		
		if(map != null && map.size() > 1) {
			renderData(request, response, JSON.toJSONString(map));
			return;
		} else {
			log.error("ip地址查询出错***************");
		}
	}
	/**
	 * IP归属地查证
	 * @param jedis 
	 * @param filecheck 
	 * @param userName 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> ipVerify(ShardedJedis jedis, String[] filecheck, String userName) {
		
		log.info("IP归属地查证........");
		String fileIpAddr = null;
		String ipState;
		List<String> redisFileSerIP;
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map0 = new HashMap<String, String>();
		
		String fileName = null;
		//从redis中取出redis中没有ip归属地的ip,进行ip归属地查询
		for(int i=0; i<filecheck.length; i++) {
			
			fileName = filecheck[i].split("@")[1];
			ipState = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS_ASCRIPTION_STATE);
			if(ipState==null || ipState.equals("1")){
				redisFileSerIP = jedis.lrange(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS, 0, -1);
				try {
					ipAddService.ipAscription(redisFileSerIP, filecheck[i], userName);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("IP归属地查询异常------------------------\n"+e);
				}
				//取出相应 IP 以及归属地
				fileIpAddr = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS_ASCRIPTION);
				map0 = JSONObject.parseObject(fileIpAddr, Map.class);
				if(fileIpAddr != null){
					
					map.putAll(map0);
					map.putAll(crossProvice(jedis, map0,fileName,userName));
				}
				
			}else{
				fileIpAddr = jedis.get(userName + "_" + filecheck[i] + RedisStorageConfig.IP_ADDRESS_ASCRIPTION);
				map0 = JSONObject.parseObject(fileIpAddr, Map.class);
				if(fileIpAddr != null)
					map.putAll(map0);
				String ipflag = jedis.get(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPFLAG);
				if(ipflag==null || ipflag.equals("1")){
					map.putAll(crossProvice(jedis, map0,fileName,userName));
				}else{
					map.put(fileName, jedis.get(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPADDR));
				}
			}
		}
		log.info("IP归属地查证结结束--------------------");
		return map;
	}
	/**
	 * 归属地跨区
	 * @param userName 
	 * @param filecheck 
	 * @return 
	 */
	private Map<String, String> crossProvice(ShardedJedis jedis, Map<String, String> map, String fileName, String userName) {

		log.info("归属地跨区........   userName    "+userName+"     fileName    "+fileName);
		StringBuffer ipAddr = new StringBuffer();
		for (String key : map.keySet()) {
			ipAddr.append(map.get(key));
			ipAddr.append("@");
		}
		String[] ipaddrs = ipAddr.toString().split("@");
		int proviceCount = 0;
		String allProvice = "";
		String provice = "";
		String addr;
		for(int i=0; i<ipaddrs.length; i++) {
			addr = ipaddrs[i];
			if (addr != null && !"".equals(addr) && !allProvice.contains(addr.substring(0,2))) {
				++proviceCount;
				if (addr.contains("/")) {
					addr = addr.substring(0,addr.indexOf("/"));
				}
				if (addr.contains("国")) {
					provice = addr.substring(0,(addr.indexOf("国") + 1));//main测试---------
					allProvice = allProvice + provice + "、";
					continue;
				} else if (addr.contains("省")) {
					provice = addr.substring(0,(addr.indexOf("省") + 1));//main测试---------
					allProvice = allProvice + provice + "、";
					continue;
				} else if (addr.contains("市")) {
					provice = addr.substring(0,(addr.indexOf("市") + 1));//main测试---------
					allProvice = allProvice + provice + "、";
					continue;
				} else {
					provice = addr;
					allProvice = allProvice + provice + "、";
				}
			}
		} 
		allProvice = allProvice.substring(0, allProvice.length()-1);
		jedis.set(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPADDRNUM, String.valueOf(proviceCount));
		jedis.set(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPADDR, allProvice);
		jedis.set(userName + "_" + fileName + RedisStorageConfig.CROSS_PROVICE_IPFLAG, "0");
		map.clear();
		map.put(fileName+"Num", String.valueOf(proviceCount));
		map.put(fileName, allProvice);
		log.info("归属地跨区判断结束----------   userName    "+userName+"     fileName    "+fileName);
		return map;
	}
	
	
	@SuppressWarnings("unused")
	private static boolean isNumeric(String str){ 
		   if(str==null){
			   return false;
		   }
		   Pattern pattern =null;
		   if(str.contains(".")){
			   pattern= Pattern.compile("-?[0-9]+.?[0-9]+"); 
		   }else{
			   pattern= Pattern.compile("-?[0-9]+"); 
		   }
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
	}
}






