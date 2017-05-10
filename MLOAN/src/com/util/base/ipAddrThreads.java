package com.util.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.constant.RedisStorageConfig;

import redis.clients.jedis.ShardedJedis;

/**
 * 资源共享多线程
 * @author 任宝坤
 */
public class ipAddrThreads extends ActionBase implements Runnable{  
	private String ip;
	private String username;
	private String fileCur;
	private ShardedJedis jedis;
	
	public ipAddrThreads(String ip, ShardedJedis jedis, String username, String fileCur) {
		this.ip = ip;
		this.username = username;
		this.fileCur = fileCur;
		this.jedis = jedis;
	}
	
	public static Map<String, String> IPS = new HashMap<String, String>();
    private static String baiduKey = "442b4cd117fb3636794603d67b8fcfd8";
    
	public void run() {
		log.info(Thread.currentThread().getName()+"  启动--子线程---"+ip);
		
		String ipCur = ip;
		if(baiduKey.equals("cb4b9b822ef517861e0392b5b727631d"))
			  baiduKey = "442b4cd117fb3636794603d67b8fcfd8";
		else 
			  baiduKey = "cb4b9b822ef517861e0392b5b727631d";
		try {
			IPS.put(ipCur, ipInfo(Arrays.asList(ipCur.split("-")).get(1),baiduKey));
		} catch (Exception e) {
			
			jedis.set(username + "_" + fileCur + RedisStorageConfig.IP_ADDRESS_ASCRIPTION_STATE, "0");
			
			log.info(Thread.currentThread().getName()+"  处理异常");
			Thread.currentThread().interrupt();
			log.error(e);
		}
		log.info(Thread.currentThread().getName()+"  结束--子线程---"+ipCur);
	}

}

