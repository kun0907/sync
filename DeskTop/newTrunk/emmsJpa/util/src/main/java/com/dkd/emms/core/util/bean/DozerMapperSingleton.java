/*
 *劲升(天津)科技有限公司 版权所有 2008
 *Copyright(C) 2008 CrimsonLogic Technology(Tianjin)Co.,Ltd. All rights reserved. 
 */
package com.dkd.emms.core.util.bean;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * Dozer属性复制工具类
 * @author qujx
 * @version 2011-11-10
 */
public class DozerMapperSingleton {

	private static  Mapper instance;

	private DozerMapperSingleton() {
		//shoudn't invoke.
	}

	public static synchronized Mapper getInstance() {
		if (instance == null) {
			instance = new DozerBeanMapper();
		}
		return instance;
	}
	
	/**
	 * 从源对象复制并创建新的对象
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return (T) getInstance().map(source, destinationClass);
	}

	/**
	 * 从源对象复制属性到新的对象
	 */
	public static void map(Object source, Object destination) {
		getInstance().map(source, destination);
	}

}
