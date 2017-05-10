/**
 * 
 */
package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Reservoirarea;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
* @Title: ReservoirareaDao
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
@Repository
public class ReservoirareaDao extends BaseDao<Reservoirarea>{
public int deleteByWareId(String warehouse_id){
 int rows = sqlSession.update("Reservoirarea.deleteByWareId", warehouse_id);
return rows;
}
/**
* 校验Code是否重复
* @param empNo
* @return
*/
public int checkStoNo(Map<String,Object> paramMap){
return sqlSession.selectOne("Reservoirarea.checkStoNo", paramMap);
}
}
