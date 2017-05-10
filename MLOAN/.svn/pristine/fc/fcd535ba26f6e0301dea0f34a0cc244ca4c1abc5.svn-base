package com.util.task;

import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;

import com.util.base.ActionBase;
import com.util.base.IpFormAddress;

/** 
 * 基于xml的定时器 
 * @author hj 
 */  
public class MyTaskIpThread extends ActionBase implements Callable<Object>{  
      
	private String ip;

	protected MyTaskIpThread(String ip) {
		this.ip = ip;
	}
	
	 @Override
	public Object call() throws Exception {
    	
    	log.info(Thread.currentThread().getName()+"  启动--子线程---"+ip);
		
		try {
			
			IpFormAddress ipForm = new IpFormAddress();
			String ipInfo = ipForm.ResultJuhe(ip);
			
			String form = "聚合数据";
			if (ipInfo.isEmpty()){
				form = "新浪";
				ipInfo = ipForm.ResultSina(ip);
			}
			if(!ipInfo.isEmpty()){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				execute("UPDATE SYS_IP SET IPADDR='"+ipInfo+"', UPDATETIME='"+df.format(System.currentTimeMillis())+"', FORM='"+form+"', ISUP='0' WHERE (IP='"+ip+"')");
			}
		} catch (Exception e) {
			
			log.info(Thread.currentThread().getName()+"  处理异常");
			Thread.currentThread().interrupt();
			log.error(e);
		}
		log.info(Thread.currentThread().getName()+"  结束--子线程---"+this.ip);
		return null;
	}
}  