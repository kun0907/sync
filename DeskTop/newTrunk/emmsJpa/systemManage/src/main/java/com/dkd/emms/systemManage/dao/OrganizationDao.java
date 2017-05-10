package com.dkd.emms.systemManage.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Organization;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class OrganizationDao extends BaseDao<Organization> {
	/**
	 * 根据当前登录用户所属组织机构查询本组织及下属组织
	 * @param currentOrg
	 * @return
	 */
	public List<Organization> selectOrgByUser(String currentOrg){
		return sqlSession.selectList("Organization.selectOrgByUser", currentOrg);		
	}
	
	/**
	 * 校验组织机构代码是否重复
	 * @param orgCode
	 * @return
	 */
	public int countByOrgCode(String orgCode){
		return sqlSession.selectOne("Organization.countByOrgCode", orgCode);
	}
	
	/**
	 * 根据组织id查询子组织信息
	 * @param orgId
	 * @return
	 */
	public List<Organization> getSubOrg(String orgId){
		return sqlSession.selectList("Organization.getSubOrg", orgId);
	}
	/**
	 * 根据组织机构类别查询组织机构
	 * @param orgTypeCode
	 * @return
	 */
	public List<Organization> selectOrgByType(String orgTypeCode) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("Organization.selectOrgByType", orgTypeCode);
	}
}
