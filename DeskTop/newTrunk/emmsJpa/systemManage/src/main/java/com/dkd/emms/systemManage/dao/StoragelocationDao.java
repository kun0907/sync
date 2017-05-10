/**
 * 
 */
package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Storagelocation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @Title: StoragelocationDao
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
@Repository
public class StoragelocationDao extends BaseDao<Storagelocation>{
/**
 * 校验Code是否重复
 * @param empNo
 * @return
 */
public int checkStoNo(Map<String,Object> paramMap){
    return sqlSession.selectOne("Storagelocation.checkStoNo", paramMap);
}
/**
 * 批量插入储位
 * @param List
 */
public void insetList(List<Storagelocation> list){
    sqlSession.insert("Storagelocation.insertList", list);
}
public int deleteByWareId(String warehouse_id){
     int rows = sqlSession.update("Storagelocation.deleteByWareId", warehouse_id);
     return rows;
}
public int deleteByResId(String reservoirarea_id){
     int rows = sqlSession.update("Storagelocation.deleteByResId", reservoirarea_id);
     return rows;
}
}
