package com.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.constant.RedisStorageConfig;
import com.util.base.ActionBase;
import com.util.base.ipAddrThreads;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class IIpAddServiceImpl extends ActionBase implements IIpAddService{

	private static final Logger log = Logger.getLogger(LogsAnalyzeServiceImpl.class);
	@Autowired
	private ShardedJedisPool shardedJedisPool; 
	private TaskExecutorUtil taskExecutorUtil;
	
	public TaskExecutorUtil getTaskExecutorUtil() {
		return taskExecutorUtil;
	}

	public void setTaskExecutorUtil(TaskExecutorUtil taskExecutorUtil) {
		this.taskExecutorUtil = taskExecutorUtil;
	}
	@Override
	public void ipAscription(List<String> ipAddrIds, String fileCur, String username) throws Exception {
		log.info("ip地址查询    username:"+username+"     fileCur:   "+fileCur);
		ShardedJedis jedis = null;
		jedis = shardedJedisPool.getResource();
		jedis.set(username + "_" + fileCur + RedisStorageConfig.IP_ADDRESS_ASCRIPTION_STATE, "1");
		
		Map<String, String> ipMap = new HashMap<String, String>();
		List<Map<String, String>> rs = getQueryList("SELECT IP,IPADDR FROM SYS_IP");
		
		if(!rs.isEmpty()){
			
			Iterator<Map<String, String>> it = rs.iterator();
			while (it.hasNext()) {
				
				Map<String, String> itNext = it.next();
				ipMap.put(itNext.get("ip"), itNext.get("ipaddr"));
			}
		}
		if(!ipMap.isEmpty()){
			
			for (String ipNo : ipAddrIds) {
				String ip = Arrays.asList(ipNo.split("-")).get(1);
				if(ipMap.containsKey(ip))
					ipAddrThreads.IPS.put(ipNo, ipMap.get(ip));
				else{
					ipAddrThreads lt = new ipAddrThreads(ipNo,jedis,username,fileCur);
					taskExecutorUtil.startThread(lt);
				}
			}
		}else{
			for (String ip : ipAddrIds) {
				ipAddrThreads lt = new ipAddrThreads(ip, jedis, ip, ip);
				taskExecutorUtil.startThread(lt);
			}
		}
		
		while(true) {
			if (taskExecutorUtil.sysActiveCou() == 0 ) {
				log.info("ip地址查询子线程执行完毕");
				break;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					log.error(e);
					log.error(e);
				}
			}
		}
		if(!ipAddrThreads.IPS.isEmpty()){
			jedis.set(username + "_" + fileCur + RedisStorageConfig.IP_ADDRESS_ASCRIPTION, JSONObject.toJSONString(ipAddrThreads.IPS));
		}else{
			jedis.set(username + "_" + fileCur + RedisStorageConfig.IP_ADDRESS_ASCRIPTION_STATE, "0");
		}
		ipAddrThreads.IPS.clear();
		log.info("ip地址查询完毕    username:"+username+"     fileCur:   "+fileCur);
	}
	
}
