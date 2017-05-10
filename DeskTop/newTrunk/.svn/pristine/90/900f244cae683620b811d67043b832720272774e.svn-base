package com.dkd.emms.core.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
	 private static ObjectMapper objectMapper = new ObjectMapper();
	 public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {     
		 return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);     
	 }   
	 /**
	  * 将list转换为json
	  * @param list
	  * @return
	  */
	 public static String list2Json(List list) {  
	        try {  
	            return objectMapper.writeValueAsString(list);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return "";  
	} 
	 /**
	  * JSON转换List
	  * @param json
	  * @param beanClass
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public static <T> List<T> json2List(String json, Class<T> beanClass) {  
	    try {  
	         if(null == beanClass){
	        	 return (List<T>)objectMapper.readValue(json,List.class); 	        	 
	         }else{
	        	 return (List<T>)objectMapper.readValue(json, getCollectionType(List.class, beanClass));  
	         }
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return null;  
	}  
}
