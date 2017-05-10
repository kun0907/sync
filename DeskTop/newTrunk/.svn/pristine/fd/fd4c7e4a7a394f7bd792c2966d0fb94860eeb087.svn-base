package com.dkd.emms.systemManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Role;



/**
 * 角色dao
 * @author WANGQ
 *
 */

@Repository
public class RoleDao extends BaseDao<Role>{
	/**
	 * 校验编码是否重复
	 * @param roleCode
	 * @return
	 */
	public int countByRoleCode(String roleCode) {
		return sqlSession.selectOne("Role.countByRoleCode", roleCode);
	}
	/**
	 * 根据组织机构id查询角色列表信息
	 * @param orgId
	 * @return
	 */
	public List<Role> selectAllRoleByOrg(String orgId){
		return sqlSession.selectList("Role.selectAllRoleByOrg", orgId);
	}
	/**
	 * 角色类型
	 * @param roleType
	 * @return
	 */
	public List<Role> getRoleByRoleType(String roleType){
		return sqlSession.selectList("Role.getRoleByRoleType", roleType);
	}
}
