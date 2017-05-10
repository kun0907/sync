package com.dkd.emms.systemManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dkd.emms.systemManage.bo.DrawingDetailed;
import com.dkd.emms.systemManage.dao.DrawingDetailedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;


@Service
@Transactional
public class DrawingDetailedService extends BaseService<DrawingDetailed> {
	
	@Autowired
	private DrawingDetailedDao drawingDetailedDao;

	public BaseDao<DrawingDetailed> getDao() {
		return drawingDetailedDao;
	}
	
	public List<DrawingDetailed> selectByDetarledCondition(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(object.getClass().getSimpleName(), object);
		List<DrawingDetailed> list = drawingDetailedDao.selectByCondition(map);
		return list;
	}
	
	//物资 查找重复
	public List<String> findRepeatW(List<String> codeNo) {
		List<String> list = drawingDetailedDao.findRepeatW(codeNo);
		return list;
	}

	//设备 查找重复
	public List<String> findRepeatS(List<String> codeNo) {
		List<String> list = drawingDetailedDao.findRepeatS(codeNo);
		return list;
	}
	/**
	 * 直达现场物资校验
	 * @return
	 */
	public boolean checkStoNo(Map<String,Object>paramMap){
		int count=drawingDetailedDao.checkStoNo(paramMap);
		return count>0?false:true;
	}
	//批量插入
	public void insertList(List<DrawingDetailed> allDetailList) {
		drawingDetailedDao.insertList(allDetailList);
	}

	//根据错误类型与料表检索错误信息
	public List<DrawingDetailed> selectByErrorType(Object ddc) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("DrawingDetailedCondition", ddc);
		return drawingDetailedDao.selectByErrorType(map);
	}
	
	//判断设计院材料是否全部匹配
	public List<DrawingDetailed> selectNotConform(Map map) {
		List<DrawingDetailed> list = drawingDetailedDao.selectNotConform(map);
		return list;
	}

	//根据料表id删除图号明细
	public void deleteByMTid(String materialsTableId) {
		drawingDetailedDao.deleteByMTid(materialsTableId);
	}

	//批量更新
	public void batchUpdate(List<DrawingDetailed> detailList) {
		for(DrawingDetailed dd : detailList){
			drawingDetailedDao.update(dd);
		}
	}

	//根据设备id删除部件
	public void deletePartsBySid(String drawingDetailedId) {
		drawingDetailedDao.deletePartsBySid(drawingDetailedId);
	}


	public List<DrawingDetailed> selectByConditionNoPage(Map<String, Object> map) {
		return drawingDetailedDao.selectByConditionNoPage(map);
	}

	public int countByConditionPage(Map<String, Object> map) {
		return drawingDetailedDao.countByConditionPage(map);
	}
	//根据错误类型与料表检索错误信息
	public List<DrawingDetailed> selectByEquipmentId(String drawingDetailedId) {
		return drawingDetailedDao.selectByEquipmentId(drawingDetailedId);
	}
}
