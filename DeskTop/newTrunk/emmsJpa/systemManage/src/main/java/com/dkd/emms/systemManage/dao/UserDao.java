package com.dkd.emms.systemManage.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.User;



/**
 * 用户dao
 * @author WANGQ
 *
 */

@Repository
public class UserDao extends BaseDao<User>{
	
	/**
	 * 登录
	 * @param username
	 * @return
	 */
	
	public User userLogin(String username) {
		return sqlSession.selectOne("User.userLogin", username);
	}
	/**
	 * 根据用户名校验用户名是否重复
	 * @param username
	 * @return
	 */
	public int checkUserName(String username){
		return sqlSession.selectOne("User.checkUserName", username);
	}
	/**
	 * 校验原密码是否正确
	 * @param userId
	 * @param newPwd
	 * @return
	 */
	public int checkOldPwd(String userId, String oldPwd) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("userId", userId);
		paramMap.put("oldPwd", oldPwd);
		return sqlSession.selectOne("User.checkOldPwd", paramMap);
	}
}
