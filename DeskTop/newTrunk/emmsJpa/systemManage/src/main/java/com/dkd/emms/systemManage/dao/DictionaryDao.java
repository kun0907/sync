package com.dkd.emms.systemManage.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Dictionary;




/**
 * 字典Dao
 * @author SY
 *
 */

@Repository
public class DictionaryDao extends BaseDao<Dictionary>{
	
	public List<Dictionary> selectByParentId(String dictionaryId){
		return sqlSession.selectList("Dictionary.selectByParentId", dictionaryId);
	}
	
	public Dictionary selectByCode(String dictionaryCode){
		return sqlSession.selectOne("Dictionary.selectByCode", dictionaryCode);
	}
	
	public List<Dictionary> selectAllDictionary(String dictionaryType){
		return sqlSession.selectList("Dictionary.selectAllDictionary", dictionaryType);
	}
	
}
