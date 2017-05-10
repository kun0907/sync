package com.dkd.emms.systemManage.dao;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Employee;


/**
 * 人员Dao
 * @author WANGQ
 *
 */
@Repository
public class EmployeeDao extends BaseDao<Employee> {
	
	/**
	 * 根据组织id查询人员信息
	 * @param orgId
	 * @return
	 */
	public List<Employee> getEmployeeByOrg(String orgId){
		return sqlSession.selectList("Employee.getEmployeeByOrg", orgId);
	}
	/**
	 * 校验Code是否重复
	 * @param empNo
	 * @return
	 */
	public int checkEmpNo(Map<String,Object> paramMap){
		return sqlSession.selectOne("Employee.checkEmpNo", paramMap);
	}
	/**
	 * 根据组织机构查询员工信息(分页查询)
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getEmployeeByOrg(Map<String, Object> map){
		return sqlSession.selectList("Employee.getEmployeeByOrgRole", map);
	}
	
	/**
	 *  根据组织机构查询员工信息(数量查询)
	 * @param map
	 * @return
	 */
	public int countEmployeeByOrg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Integer count = (Integer) sqlSession.selectOne("Employee.countEmployeeByOrg",map);
		return count.intValue();
	}
}
