/**
 * 
 */
package com.dkd.emms.systemManage.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Sequence;



	/**
 * @Title: SequenceDao
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月23日
 */

@Repository
public class SequenceDao extends BaseDao<Sequence>{
	/**
	 * 获取流水码
	 * @param sequence
	 * @return
	 */
	public String getFlowNoByJudge(Map<String, Object> paramMap){
		 sqlSession.selectOne("Sequence.getFlowNo", paramMap);
		 return (String) paramMap.get("flowNo");
	}
}
