package com.dkd.emms.systemManage.service;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dkd.emms.systemManage.bo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.page.Page;
import com.dkd.emms.systemManage.dao.UserRoleDao;



/**
 * 用户授权Service
 * @author WANGQ
 *
 */
@Service
public class UserRoleService extends BaseService<UserRole> {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@Autowired
	private RoleResourceService roleResourceService;

	@Autowired
	private OrganizationRoleService organizationRoleService;
	@Override
	public BaseDao<UserRole> getDao() {
		// TODO Auto-generated method stub
		return userRoleDao;
	}

	public int countEmpByCondition(Object employeeCondition) {
		return employeeService.countByCondition(employeeCondition);
	}
	
	
	/**
	 * 根据组织机构查询其下的所有员工信息
	 * @param object
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Map<String,Object>> selectEmployeeByOrg(Object object,Integer total,Integer start,Integer length) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page(total,start,length);
		map.put(object.getClass().getSimpleName(), object);
		map.put("page", page);
		return  employeeService.getEmployeeByOrg(map);
	}
	
	
	/**
	 * 初始化当前登录用户所在组织机构下角色
	 * @param orgId
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Role> selectEmployeeByOrg(String orgId,User user,String userId) {
		List<Role> orgRoleList = organizationRoleService.selectCheckRoleByOrg(orgId);
		List<UserRole> userCheckRoleList = userRoleDao.selectCheckRoleByUser(userId);
		for(Role orgRole:orgRoleList){
			for(UserRole userRole:userCheckRoleList){
				if(StringUtils.equals(userRole.getRole().getRoleId(), orgRole.getRoleId())){
					orgRole.setChecked(true);
				}
			}
		}
		return orgRoleList;
	}
	//当前用户所配置的橘色
	public List<UserRole> selectCheckRoleByUser(String userId){
		return userRoleDao.selectCheckRoleByUser(userId);
	}
	/**
	 * 保存用户授权信息
	 * @param roleIds
	 * @param userId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveAuthorizeUser(String roleIds, String userId) {
		//根据用户id删除用户角色信息
		List<RoleResource> roleResourceList = roleResourceService.selectRoleResourceByRoleIds(roleIds);
		this.saveRoleForUser(userId,roleResourceList);
	}

	/**
	 * 保存用户角色信息
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUserRole(User user) {
		//为组织机构管理员设置非单据级角色
		List<RoleResource> roleResourceList = roleResourceService.findRoleResourceByRoleType(RoleEnum.noDoc_level.toString());
		this.saveRoleForUser(user.getUserId(),roleResourceList);
	}

	private void saveRoleForUser(String userId,List<RoleResource> roleResourceList){
		userRoleDao.deleteUserRoleByUserId(userId);
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		for(RoleResource roleResource:roleResourceList){
			Resource resource = new Resource();
			Role role = new Role();
			UserRole userRole = new UserRole();
			resource.setResourceId(roleResource.getResourceId());
			role.setRoleId(roleResource.getRoleId());
			userRole.setUserId(userId);
			userRole.setResource(resource);
			userRole.setRole(role);
			userRoleList.add(userRole);
		}
		if(userRoleList.size()>0){
			userRoleDao.insertList(userRoleList);
		}

	}

}
