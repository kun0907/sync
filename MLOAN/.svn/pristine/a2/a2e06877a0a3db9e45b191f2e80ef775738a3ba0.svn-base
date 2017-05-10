package com.util.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.util.interceptor.CommonInterceptor;

import net.sf.json.JSONObject;

public class IpFormAddress {
	
	/**
	 * log4j日志打印
	 */
	public static Logger log = Logger.getLogger(CommonInterceptor.class);  			//........
	
	/**
	 * 地址查询   -- 百度
	 *  See http://apistore.baidu.com/apiworks/servicedetail/114.html	IP归属地查询
	 *  k1 cb4b9b822ef517861e0392b5b727631d
	 *  k2 442b4cd117fb3636794603d67b8fcfd8
	 *  查询次数限制     200次/秒
	 * @throws Exception 
	 */
	@Deprecated
	private JSONObject baiduIPs(String ip,String apikey) throws Exception {
		
			if(apikey==null)
				apikey = "cb4b9b822ef517861e0392b5b727631d";

		    BufferedReader reader = null;
		    String jsonStr = null;
		    StringBuffer sbf = new StringBuffer();
		    try {
		        URL url = new URL("http://apis.baidu.com/apistore/iplookupservice/iplookup?ip="+ip);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setRequestMethod("GET");
		        connection.setRequestProperty("apikey", apikey);// 填入apikey到HTTP header
		        //设置连接超时时间 单位:ms
		        connection.setConnectTimeout(5000);
		        connection.connect();
		        
		        InputStream is = connection.getInputStream();
		        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		        String strRead = null;
		        while ((strRead = reader.readLine()) != null) {
		            sbf.append(strRead);
		            sbf.append("\r\n");
		        }
		        reader.close();
		        jsonStr = sbf.toString();
		    } catch (Exception e) {
		    	log.info("百度查询IP 归属 ： 网络异常");
		    }
			JSONObject json = JSONObject.fromObject(jsonStr);
			log.info(ip+" 百度  ："+json);
			return json;
	}
	@Deprecated
	public String ResultBaiDu(String ip,String baiduKey) {

		JSONObject jsonBaidu = null;
		try {
			jsonBaidu = this.baiduIPs(ip,baiduKey);
		} catch (Exception e) {
			log.error(e);
		}
		String ipInfo = null;
		if(jsonBaidu !=null && jsonBaidu.get("errNum").equals(0)){
			JSONObject retData = JSONObject.fromObject(jsonBaidu.get("retData"));
			String province = retData.getString("province");
			String city = retData.getString("city");
			String carrier = retData.getString("carrier");
			if(!province.equals("None"))
				ipInfo = province;
			if(!city.equals("None") && !ipInfo.equals(""))
				ipInfo = ipInfo+"/"+city;
			if(!carrier.equals("None") && !ipInfo.equals(""))
				ipInfo = ipInfo+"/"+carrier;
		}
		return ipInfo;
	}
	/**
	 * 地址查询   -- 新浪
	 */
	private JSONObject sinaIPs(String ip) throws IOException {
		
		HttpClient httpClient = new HttpClient();
		//设置http连接超时时间ms
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		
		GetMethod getMethod = new GetMethod("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ip);
		//设置get请求超时时间ms
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        httpClient.executeMethod(getMethod);
        JSONObject json = null;
        try {
        	Integer statusCode = getMethod.getStatusCode();
        	if(statusCode != null && statusCode == HttpStatus.SC_OK){
        		String res = getMethod.getResponseBodyAsString();
        		String asc = res.substring(21,res.length()-1);
        		json = JSONObject.fromObject(asc);
        		log.info(ip+" 新浪 : "+json);
        	}else
        		log.info(ip+" 新浪 : 网络异常");        	
			
		} catch (Exception e) {
			log.error(e);
		} finally {
			getMethod.releaseConnection();//关闭连接
		}
        return json;
	}
	public String ResultSina(String ip) {

		JSONObject jsonSina = null;
		try {
			jsonSina = this.sinaIPs(ip);
		} catch (IOException e) {
			log.error(e);
		}
		String ipInfo = "";
		if(jsonSina !=null && jsonSina.get("ret").equals(1)){
			String province = jsonSina.getString("province");
			String city = jsonSina.getString("city");
			String isp = jsonSina.getString("isp");
			if(!province.equals(""))
				ipInfo = province;
			if(!city.equals("") && !ipInfo.equals(""))
				ipInfo = ipInfo+"/"+city;
			if(!isp.equals("") && !ipInfo.equals(""))
				ipInfo = ipInfo+"/"+isp;
		}
		return ipInfo;
	}
	/**
	 * 地址查询   -- 聚合数据
	 */
	private JSONObject juheIPs(String ip) throws IOException {
		String appkey = "8aa46d6cba98b535304ca24f73c9749a";
		HttpClient httpClient = new HttpClient();
		//设置http连接超时时间ms
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		
		GetMethod getMethod = new GetMethod("http://apis.juhe.cn/ip/ip2addr?ip="+ip+"&key="+appkey);
		//设置get请求超时时间ms
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		JSONObject json = null;
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				log.error("聚合查询ip地址错误--------" + getMethod.getStatusLine());
			}
			String res = convertStreamToString(getMethod.getResponseBodyAsStream());
			
			if (res != null && !res.equals("") && !"<html><head><title>".equals(res.substring(0, 19))) {
				log.info(ip+"聚合数据"+res);
				json = JSONObject.fromObject(res);
			}else
				log.info(ip+"聚合数据处理异常"+res);
			
		} catch (HttpException e) {
			log.error(e);
		} finally{
			getMethod.releaseConnection();//关闭连接
		}
        
        return json;
	}
	public String ResultJuhe(String ip){
		
		JSONObject jsonJuhe = null;
		try {
			jsonJuhe = this.juheIPs(ip);
		} catch (IOException e) {
			log.error(e);
		}
		String ipInfo = "";
		if (jsonJuhe != null) {
			JSONObject result = JSONObject.fromObject(jsonJuhe.get("result"));
			if (!result.isNullObject() && !result.equals("")) {
				String area = result.getString("area");
				String location = result.getString("location");
				if (area != null && !area.equals("")) 
					ipInfo = ipInfo+area;
				if (location != null && !location.equals("")) 
					ipInfo = ipInfo + "/"+ location;
				if(ipInfo!=null && ipInfo.length()>25 && ipInfo.contains("(")){
					String[] ipIn = ipInfo.split("\\(");
					for(int i=0;i<ipIn.length;i++){
						if(i==0)
							ipInfo += ipIn[i];
						else
							ipInfo += "</br>("+ipIn[i];
					}
				}
			}
		}
		return ipInfo; 
	}
	public String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				log.error(e);
			}
		}

		return sb.toString();
	}   
}
