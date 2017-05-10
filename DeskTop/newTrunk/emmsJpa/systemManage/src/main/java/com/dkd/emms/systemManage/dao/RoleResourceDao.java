package com.dkd.emms.systemManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.RoleResource;



@Repository
public class RoleResourceDao extends BaseDao<RoleResource> {

	public void batchInsert(List<RoleResource> roleResourceList){
		sqlSession.insert("RoleResource.batchInsert", roleResourceList);
	}
	/**
	 * 根据给定的roles信息查询角色权限信息
	 * @param roleList
	 * @return
	 */
	public List<RoleResource> selectRoleResourceByRoleIds(List<String> roleList) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("RoleResource.selectRoleResourceByRoleIds", roleList);
	}

	public int findRoleResourceByResource(String resourceId) {
		return sqlSession.selectOne("RoleResource.findRoleResourceByResource", resourceId);
	}
	/**
	 * 根据角色类型查询角色权限信息
	 * @return
	 */
	public List<RoleResource> findRoleResourceByRoleType(String roleType) {
		return sqlSession.selectList("RoleResource.findRoleResourceByRoleType",roleType);
	}
}
