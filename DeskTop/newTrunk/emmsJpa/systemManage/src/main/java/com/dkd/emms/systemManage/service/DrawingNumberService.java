package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.DrawingNumber;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.DrawingNumberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class DrawingNumberService extends BaseService<DrawingNumber>{

	@Autowired
	private DrawingNumberDao drawingNumberDao;

	public BaseDao<DrawingNumber> getDao() {
		return drawingNumberDao;
	}

	public int countByCode(String drawingNumberCode) {
		return drawingNumberDao.countByCode(drawingNumberCode);
	}

	public void updateByCode(DrawingNumber dn) {
		drawingNumberDao.updateByCode(dn);
	}
	
	public DrawingNumber selectByCode(String drawingNumberCode) {  
		return drawingNumberDao.selectByCode(drawingNumberCode);
	}

	public void insertList(List<DrawingNumber> allDrawingList) {
		drawingNumberDao.insertList(allDrawingList);
	}

	//批量更新图号确认时间
	public void updateConfirmTime(String materialsTableId,User user) {
		List<DrawingNumber> DNlist = drawingNumberDao.selectByMTid(materialsTableId);
		for(DrawingNumber DNitem: DNlist){
			DNitem.setConfirmUserId(user.getUserId());
			DNitem.setConfirmUserName(user.getUserName());
			DNitem.setConfirmTime(new Date());
			drawingNumberDao.updateByCode(DNitem);
		}
	}

	//根据料表id删除图号
	public void deleteByMTid(String materialsTableId) {
		drawingNumberDao.deleteByMTid(materialsTableId);
	}
}
