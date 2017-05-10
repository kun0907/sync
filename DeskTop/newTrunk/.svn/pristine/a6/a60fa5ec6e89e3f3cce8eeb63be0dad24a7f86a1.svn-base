package com.dkd.emms.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dkd.emms.core.util.bean.BeanUtils;




/**
 * 泛型DAO
 * @author SY
 * @param <T>
 */

@Repository
public class BaseDao<T>  {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	public SqlSession sqlSession; 
	
    public static final String SQLID_INSERT = "insert";  //增
    public static final String SQLID_UPDATE = "update";   //改
    public static final String SQLID_DELETE = "delete";  //删
    public static final String SQLID_SELECT_ALL= "selectAll"; //查询全部 
    public static final String SQLID_SELECT_CONDITION= "selectByCondition";  //条件查询
    public static final String SQLID_SELECT_PK = "selectByPk";  //id查询
    public static final String SQLID_COUNT_ALL  = "countAll";  //统计
    public static final String SQLID_COUNT_CONDITION = "countByCondition";   //条件统计
    public static final String SQLID_MAX_NO = "maxNo";  
    public static final String SQLID_CHECK_CODE = "checkCode"; 
    public static final String SQLID_SELECT_REMIND = "selectByRemind"; 

	/**
	 * 获取泛型类型的实体对象类全名
	 */
    
	protected String getDefaultSqlNamespace() {
		Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
		return genericClass == null ? null : genericClass.getSimpleName();
	}
    
    public int insert(T entity) {  
    	return sqlSession.insert(this.getDefaultSqlNamespace() + "." + SQLID_INSERT,  entity);    
    }  
    
    public int delete(String primaryKey) {  
        int rows = sqlSession.update(this.getDefaultSqlNamespace() + "." + SQLID_DELETE, primaryKey);  
        return rows;  
    }  
    
    public int update(T entity) {  
        return sqlSession.update(this.getDefaultSqlNamespace() + "." + SQLID_UPDATE, entity);  
    }  
	
	public List<T> selectAll() {
    	return sqlSession.selectList(this.getDefaultSqlNamespace() + "." + SQLID_SELECT_ALL);  
	}
	
	public List<T> selectByCondition(Map<String, Object> map) {
		return sqlSession.selectList(this.getDefaultSqlNamespace() + "." + SQLID_SELECT_CONDITION, map);  
	}
	
	public T selectByPk(String primaryKey) {  
        return sqlSession.selectOne(this.getDefaultSqlNamespace() + "." + SQLID_SELECT_PK, primaryKey);  
    }

	public int countAll() { 
		 Integer count = (Integer) sqlSession.selectOne(this.getDefaultSqlNamespace()+ "." + SQLID_COUNT_ALL);
		 return count.intValue();  
	}

	public int countByCondition(Object param) { 
		System.out.println(this.getDefaultSqlNamespace()+ "." + SQLID_COUNT_CONDITION);
        Integer count = (Integer) sqlSession.selectOne(this.getDefaultSqlNamespace()+ "." + SQLID_COUNT_CONDITION,param);
		return count.intValue();   
	}
    
	public String maxNo(String date) { 
		 String maxNo = (String) sqlSession.selectOne(this.getDefaultSqlNamespace()+ "." + SQLID_MAX_NO,date);
		 return maxNo;  
	}
	
	public int checkCode(Map<String, Object> map) { 
        Integer count = (Integer) sqlSession.selectOne(this.getDefaultSqlNamespace()+ "." + SQLID_CHECK_CODE,map);
		return count.intValue();   
	}
	
	public List<T> selectByRemind(Map<String, Object> map) {
		return sqlSession.selectList(this.getDefaultSqlNamespace() + "." + SQLID_SELECT_REMIND, map);  
	}
}
