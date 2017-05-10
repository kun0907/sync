package com.dkd.emms.core.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 对Bean进行操作的相关工具方法
 * @author sy
 *
 */

public class BeanUtils {

	/**
	 * 获取接口的泛型类型，如果不存在则返回null
	 * @param clazz
	 * @return
	 */
	
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type t = clazz.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			return ((Class<?>) p[0]);
		}
		return null;
	}
	
	/**    
	 * java反射bean的get方法    
	 * @param objectClass    
	 * @param fieldName    
	 * @return    
	 */       
	
    public static Method getGetMethod(Class<? extends Object> objectClass, String fieldName) {        
        StringBuffer sb = new StringBuffer();       
        sb.append("get");       
        sb.append(fieldName.substring(0, 1).toUpperCase());       
        sb.append(fieldName.substring(1));       
        try {       
            return objectClass.getMethod(sb.toString());       
        } catch (Exception e) { 
        	e.printStackTrace();
        }       
        return null;       
    }
    
    /**    
     * 执行get方法    
     * @param o执行对象    
     * @param fieldName属性    
     */       
      
    public static Object invokeGet(Object o, String fieldName) {      
        Method method = getGetMethod(o.getClass(), fieldName);       
        try {       
            return method.invoke(o, new Object[0]);       
        } catch (Exception e) {       
            e.printStackTrace();       
        }         
        return null;       
    } 
    
	/**    
	 * java反射bean的set方法    
	 * @param objectClass    
	 * @param fieldName      
	 * @return     
	 */       
        
    public static Method getSetMethod(Class<? extends Object> objectClass, String fieldName) {       
	    try {       
	        @SuppressWarnings("rawtypes")
			Class[] parameterTypes = new Class[1];       
	        Field field = objectClass.getDeclaredField(fieldName);       
	        parameterTypes[0] = field.getType();       
	        StringBuffer sb = new StringBuffer();       
	        sb.append("set");       
	        sb.append(fieldName.substring(0, 1).toUpperCase());       
	        sb.append(fieldName.substring(1));       
	        Method method = objectClass.getMethod(sb.toString(), parameterTypes);       
	        return method;       
	    } catch (Exception e) {       
	        e.printStackTrace();       
	    }       
	    return null;       
    }       
   
    /**    
     * 执行set方法    
     * @param o执行对象    
     * @param fieldName属性    
     * @param value值     
     */       
      
    public static void invokeSet(Object o, String fieldName, Object value) {       
        Method method = getSetMethod(o.getClass(), fieldName);       
        try {       
            method.invoke(o, new Object[] { value });       
        } catch (Exception e) {       
            e.printStackTrace();        
        }       
    }   
	
}