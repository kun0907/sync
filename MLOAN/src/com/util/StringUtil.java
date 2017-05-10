package com.util;


public class StringUtil {

	/**
	 * 判断一个字符串是否为空  null 或者 "" 如果为空返回true,否则返回false
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		boolean result;
		if (str == null || str.equals("")) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
}	
