package com.dkd.emms.systemManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Resource;
import com.dkd.emms.systemManage.bo.Role;



/**
 * 权限Dao
 * @author SY
 *
 */

@Repository
public class ResourceDao extends BaseDao<Resource>{


	public List<Map<String, Object>> selectByRoleList(List<Role> roles) {
		return sqlSession.selectList("selectByRoleList", roles);
	}
	
	public List<Map<String, Object>> selectAllForRole(){
		return sqlSession.selectList("selectAllForRole");
	}

	public int countByResourceCode(String resourceCode) {
		return sqlSession.selectOne("Resource.countByResourceCode", resourceCode);
	}
}
