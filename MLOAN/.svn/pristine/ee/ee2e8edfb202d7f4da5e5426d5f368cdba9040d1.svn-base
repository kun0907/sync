package com.util.task;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Scheduled;  
import org.springframework.stereotype.Component;

import com.util.base.ActionBase;
  
/** 
 * 基于注解的定时器 
 * @author 任宝坤 
 */  
@Component  
public class MyTaskAnnotation extends ActionBase{  
	
	
    /**  
     * 字段   允许值   允许的特殊字符

		秒    0-59    , - * /
		
		分    0-59    , - * /
		
		小时    0-23    , - * /
		
		日期    1-31    , - * ? / L W C
		
		月份    1-12 或者 JAN-DEC    , - * /
		
		星期    1-7 或者 SUN-SAT    , - * ? / L C #
		
		年（可选）    留空, 1970-2099    , - * / 
		
		- 区间  
		
		* 通配符  
		
		? 你不想设置那个字段
     * 
     * CRON表达式    含义 
		
		"0 0 12 * * ?"    每天中午十二点触发 
		
		"0 15 10 ? * *"    每天早上10：15触发 
		
		"0 15 10 * * ?"    每天早上10：15触发 
		
		"0 15 10 * * ? *"    每天早上10：15触发 
		
		"0 15 10 * * ? 2005"    2005年的每天早上10：15触发 
		
		"0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发 
		
		"0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发 
		
		"0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 
		
		"0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发 
		
		"0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发 
		
		"0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发 
     * 定时计算。每周一凌晨 01:00 执行一次  
     * @throws Exception 
     */    
    @Scheduled(cron = "0 0 01 ? * MON")   
    public void ipTaskUpdate() throws Exception{  
    	
    	log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + " IP地址开始三次更新");
    	
    	ExecutorService pool = Executors.newFixedThreadPool(10);
    	
    	//获取Future对象
        Future<Object> f1 = null;

        // 创建多个有返回值的任务
        List<Future<Object>> list = new ArrayList<Future<Object>>();
        
        
        List<Map<String, String>> rs;
    	
        for(int i=0;i<3;i++){
        	
        	log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + " IP地址第"+(i+1)+"次 更新");
        	
        	if(i==0)
        		rs =  getQueryList("SELECT IP FROM SYS_IP");
        	else 
        		rs =  getQueryList("SELECT IP FROM SYS_IP WHERE ISUP IS NULL || ISUP != '0'");
        	
        	if(!rs.isEmpty()){	
    			
    			Iterator<Map<String, String>> it = rs.iterator();
    			while (it.hasNext()) {
    				
    				 // 执行任务
    	       		 f1 = pool.submit(new MyTaskIpThread(it.next().get("ip")));
    	       		 list.add(f1); 
    			}
    		}
            
        	 // 获取所有并发任务的运行结果
            for (Future<Object> f : list) {
               // 从Future对象上获取任务的返回值，并输出到控制台
          	  	f.get();
            }
            log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()) + "IP地址第"+i+1+"次更新完毕结束");
        }
        
        // 关闭线程池
        pool.shutdown();
		
    	log.info("IP地址三次更新结束*************");
    	
    } 
    
    
    /**  
     * 心跳更新。启动时执行一次，之后每隔2秒执行一次  
     */    
   /* @Scheduled(fixedRate = 1000*2)   
    public void print(){  
        System.out.println("Annotation：print run");  
    }*/  
}  