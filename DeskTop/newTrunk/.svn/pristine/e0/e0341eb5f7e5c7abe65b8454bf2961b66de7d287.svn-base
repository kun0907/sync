package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Design;
import com.dkd.emms.systemManage.bo.Materials;
import com.dkd.emms.systemManage.bo.MaterialsUnit;
import com.dkd.emms.systemManage.dao.MaterialsDao;
import com.dkd.emms.systemManage.dao.MaterialsUnitDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MaterialsUnitService extends BaseService<MaterialsUnit> {
	
	@Autowired
	private MaterialsUnitDao materialsUnitDao;

	public BaseDao<MaterialsUnit> getDao() {
		return materialsUnitDao;
	}

	//根据系统物资ID查询单位换算列表
	public List<MaterialsUnit> selectListByMaterialsId(String materialsId) {
		return materialsUnitDao.selectListByMaterialsId(materialsId);
	}
	//批量插入
	public void insertList(List<MaterialsUnit> unitList) {
		if(unitList.size()>0){
			materialsUnitDao.insertList(unitList);
		}
	}

	//根据系统物资ID删除
	public void deleteByMaterialsId(String materialsId) {
		materialsUnitDao.deleteByMaterialsId(materialsId);
	}


}
