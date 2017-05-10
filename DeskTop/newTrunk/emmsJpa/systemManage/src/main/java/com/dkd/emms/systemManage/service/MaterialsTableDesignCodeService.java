package com.dkd.emms.systemManage.service;

import java.util.List;

import com.dkd.emms.systemManage.bo.DrawingDetailed;
import com.dkd.emms.systemManage.dao.DrawingDetailedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class MaterialsTableDesignCodeService {
	
	@Autowired
	private DrawingDetailedDao drawingDetailedDao;

	//返回匹配项Id列表
	public List<DrawingDetailed> findMatching(List<DrawingDetailed> conditionList) {
		List<DrawingDetailed> list = drawingDetailedDao.findMatching(conditionList);
		return list;
	}

	//返回未匹配项Id列表
	public List<DrawingDetailed> findMismatch(List<DrawingDetailed> conditionList) {
		List<DrawingDetailed> list = drawingDetailedDao.findMismatch(conditionList);
		return list;
	}

	//校验单位 （暂不开发）
	public void checkUnit(List<DrawingDetailed> matchingItem) {
		// TODO Auto-generated method stub
		
	}

	//为已同步的明细回填信息
	public void makeInformationComplete(List<DrawingDetailed> matchingItem) {
		for(DrawingDetailed dd: matchingItem){
			drawingDetailedDao.makeInformationComplete(dd);
		}
	}

	
	
}
