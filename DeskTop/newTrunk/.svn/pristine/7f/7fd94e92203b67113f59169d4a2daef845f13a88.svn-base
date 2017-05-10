package com.dkd.emms.systemManage.dao;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OrganizationTypeWBS;

/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class OrganizationTypeWBSDao extends BaseDao<OrganizationTypeWBS> {
	/**
	 * 批量插入组织机构类型WBS信息
	 * @param orgTypeWBSList
	 */
	public void insertList(List<OrganizationTypeWBS> orgTypeWBSList) {
		// TODO Auto-generated method stub
		sqlSession.insert("OrganizationTypeWBS.insertList",orgTypeWBSList);
	}
	/**
	 * 根据组织机构id删除机构类型wbs表
	 * @param orgId
	 */
	public void deleteOrgTypeWBSByOrg(String orgId) {
		// TODO Auto-generated method stub
		sqlSession.insert("OrganizationTypeWBS.deleteOrgTypeWBSByOrg",orgId);
	}
	/**
	 * 根据组织机构id和机构类型查询WBS
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,Object>> selectOrgTypeWBSByType(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("OrganizationTypeWBS.selectOrgTypeWBSByType",paramMap);
	}

	/**
	 * 根据组织机构id查询WBS
	 * @param orgId
	 * @return
	 */
	public List<Map<String,Object>> selectOrgTypeWBSByOrg(String orgId,String orgTypeCode) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgId",orgId);
		paramMap.put("orgTypeCode",orgTypeCode);
		// TODO Auto-generated method stub
		return sqlSession.selectList("OrganizationTypeWBS.selectOrgTypeWBSByOrg",paramMap);
	}
	
}
