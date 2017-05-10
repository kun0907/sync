package com.dkd.emms.systemManage.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.OrganizationType;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class OrganizationTypeDao extends BaseDao<OrganizationType> {
	
	/**
	 * 插入组织机构类型信息
	 * @param organizationTypeList
	 */
	public void insetList(List<OrganizationType> organizationTypeList){
		sqlSession.insert("OrganizationType.insertList", organizationTypeList);
	}
	/**
	 * 根据组织机构Id删除组织类型中间表
	 * @param orgId
	 */
	public void deleteOrgTypeByOrgId(String orgId){
		sqlSession.delete("OrganizationType.deleteOrgTypeByOrgId", orgId);
	}
	/**
	 * 根据组织机构id查询组织机构类型
	 * @param orgId
	 * @return
	 */
	public List<OrganizationType> selectOrgType(String orgId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("OrganizationType.selectOrgType", orgId);
	}
}
