package com.dkd.emms.systemManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.UnitConversion;
import com.dkd.emms.systemManage.dao.UnitConversionDao;


@Service
public class UnitConversionService extends BaseService<UnitConversion> {

	@Resource
	private UnitConversionDao unitConversionDao;
	
	public BaseDao<UnitConversion> getDao(){
		return unitConversionDao;
	}
	
}
