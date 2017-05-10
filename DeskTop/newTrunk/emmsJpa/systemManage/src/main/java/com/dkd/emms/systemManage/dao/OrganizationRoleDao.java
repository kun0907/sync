package com.dkd.emms.systemManage.dao;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OrganizationRole;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class OrganizationRoleDao extends BaseDao<OrganizationRole> {
	
	/**
	 * 批量插入组织机构角色信息
	 * @param orgRoleList
	 */
	public void insertList(List<OrganizationRole> orgRoleList){
		sqlSession.insert("OrganizationRole.insertList", orgRoleList);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<OrganizationRole> selectCheckRoleByOrg(String orgId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId",orgId);
		return sqlSession.selectList("OrganizationRole.selectCheckRoleByOrg", map);
	}
	/**
	 * 根据组织机构id删除组织机构角色信息
	 */
	public void deleteOrgRole(OrganizationRole orgRole){
		sqlSession.delete("OrganizationRole.deleteOrgRole",orgRole);
	}
}
