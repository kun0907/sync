package com.dkd.emms.systemManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Dictionary;
import com.dkd.emms.systemManage.dao.DictionaryDao;



@Service
public class DictionaryService extends BaseService<Dictionary>{

	@Resource
	private DictionaryDao dictionaryDao;
	
	public BaseDao<Dictionary> getDao() {
		return dictionaryDao;
	}
	
	public List<Dictionary> selectAllDictionary(String dictionaryType){
		return dictionaryDao.selectAllDictionary(dictionaryType);
	}
	/**
	 * 根据parentCode加载数据字典列表
	 * @param dicCode
	 * @return
	 */
	public List<Dictionary> loadDicByCode(String dicCode) {
		Dictionary dictionary = dictionaryDao.selectByCode(dicCode);
		// TODO Auto-generated method stub
		return dictionaryDao.selectByParentId(dictionary.getDictionaryId());
	}
	
}
