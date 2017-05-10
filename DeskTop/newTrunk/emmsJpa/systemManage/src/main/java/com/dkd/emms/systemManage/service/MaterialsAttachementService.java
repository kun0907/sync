package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.MaterialsAttachement;
import com.dkd.emms.systemManage.dao.MaterialsAttachementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MaterialsAttachementService extends BaseService<MaterialsAttachement> {

	@Autowired
	private MaterialsAttachementDao materialsAttachementDao;

	/**
	 * 保存系统物料表中设备与物料之间的关联关系
	 * @param materialsAttachementList
	 */
	public void saveOrgAndType(List<MaterialsAttachement> materialsAttachementList){
		if(materialsAttachementList.size()>0){
			materialsAttachementDao.insetList(materialsAttachementList);
		}
	}
	@Override
	public BaseDao<MaterialsAttachement> getDao() {
		return materialsAttachementDao;
	}

	//保存关系表
	public void insetList(List<MaterialsAttachement> allNewMA) {
		materialsAttachementDao.insetList(allNewMA);
	}
}
