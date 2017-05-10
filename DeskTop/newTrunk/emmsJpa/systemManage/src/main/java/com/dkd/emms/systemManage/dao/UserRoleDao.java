package com.dkd.emms.systemManage.dao;




/**
 * 用户角色Dao
 * @author WANGQ
 *
 */
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.UserRole;



@Repository
public class UserRoleDao extends BaseDao<UserRole> {
	/**
	 * 根据用户id查询用户角色关联信息
	 * @param userId
	 * @return
	 */
	public List<UserRole> selectCheckRoleByUser(String userId){
		return sqlSession.selectList("UserRole.selectCheckRoleByUser", userId);
	}
	/**
	 * 批量插入用户权限角色表
	 * @param userRoleList
	 */
	public void insertList(List<UserRole> userRoleList) {
		// TODO Auto-generated method stub
		sqlSession.insert("UserRole.insertList",userRoleList);
	}
	/**
	 * 根据用户id删除用户角色表
	 * @param userId
	 */
	public void deleteUserRoleByUserId(String userId) {
		// TODO Auto-generated method stub
		sqlSession.insert("UserRole.deleteUserRoleByUserId",userId);
	}
}
