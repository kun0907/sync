package com.dkd.emms.core.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring的工具类，这个类很重要，要在普通类里调用spring注入的bean对象必须通过这个类
 * 通过注解是没用的，因为普通类不符合spring 或者 mvc机制
 * @author Administrator
 *
 */

public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;  
	
	@Override  
    public void setApplicationContext(ApplicationContext context)  
        throws BeansException {  
        SpringUtil.applicationContext = context;  
    }  
	
    public static Object getBean(String name){  
        return applicationContext.getBean(name);  
    }  

}
