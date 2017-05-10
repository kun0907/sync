/**
 * 
 */
package com.dkd.emms.systemManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Sequence;
import com.dkd.emms.systemManage.dao.SequenceDao;

import java.util.HashMap;
import java.util.Map;


/**
 * @Title: SequenceService
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月23日
 */
@Service
public class SequenceService extends BaseService<Sequence>{
	@Resource
	private SequenceDao sequenceDao;
	public BaseDao<Sequence> getDao() {
		return sequenceDao;
	}
	public String getFlowNoByJudge(String entityType,String flowJudgeValue,int length){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("entityType",entityType);
		paramMap.put("flowJudgeValue",flowJudgeValue);
		paramMap.put("length",length);
		return sequenceDao.getFlowNoByJudge(paramMap);
	}
}
