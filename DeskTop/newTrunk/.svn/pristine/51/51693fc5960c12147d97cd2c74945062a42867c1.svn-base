package com.dkd.emms.systemManage.service;


import com.dkd.emms.systemManage.bo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.dao.UserDao;

import java.util.List;


/**
 * 用户Service
 * @author WANGQ
 *
 */
@Service
public class UserService extends BaseService<User> {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private  HashedCredentialsMatcher credentialsMatcher;
	/**
	 * 根据username登录系统认证
	 * @param username
	 * @return
	 */

	public User userLogin(String username) {
		return userDao.userLogin(username);
	}
	/**
	 * 保存用户信息
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public User saveUser(User user){
		this.setDefault(user);
		this.validate(user);
		if(StringUtils.isEmpty(user.getUserId())){
			user.setUserId(UUIDGenerator.getUUID());
			Employee employee =  employeeService.insertEmp(user.getEmployee());
			user.setEmployee(employee);
			this.insert(user);
		}else{
			this.update(user);
			employeeService.update(user.getEmployee());
		}
		return user;
	}
	/**
	 * 为用户信息设置默认值
	 * @param user
	 */
	private void setDefault(User user){
		if(StringUtils.isEmpty(user.getPassword())){
			user.setPassword((new SimpleHash(credentialsMatcher.getHashAlgorithmName(), "1111", ByteSource.Util.bytes(user.getUserName()), credentialsMatcher.getHashIterations())).toString());
		}else{
			user.setPassword((new SimpleHash(credentialsMatcher.getHashAlgorithmName(), user.getPassword(), ByteSource.Util.bytes(user.getUserName()), credentialsMatcher.getHashIterations())).toString());
		}
		if(StringUtils.isEmpty(user.getIsValidate())){
			user.setIsValidate("1");
		}
		if(StringUtils.isEmpty(user.getIsDel())){
			user.setIsDel("0");
		}
		if(StringUtils.isEmpty(user.getIsSysadmin())){
			user.setIsSysadmin("0");
		}
		if(StringUtils.isEmpty(user.getIsOrgadmin())){
			user.setIsOrgadmin("0");
		}
	}
	/**
	 * 校验用户信息的正确性
	 * @param user
	 */
	private void validate(User user){
		if(StringUtils.isEmpty(user.getIsValidate())){
			throw new BusinessException("用户是否有效不能为空");
		}
		if(StringUtils.isEmpty(user.getIsDel())){
			throw new BusinessException("用户是否删除不能为空");
		}
		if(StringUtils.isEmpty(user.getIsSysadmin())){
			throw new BusinessException("用户是否是超级管理员不能为空");
		}
		if(StringUtils.isEmpty(user.getIsOrgadmin())){
			throw new BusinessException("用户是否是组织管理员不能为空");
		}
	}
	
	/**
	 * 根据组织机构生成组织admin用户
	 * @param organization
	 */
	public void saveUser(Organization organization) {
		//将系统角色的权限赋值给该用户
		User user = new User();
		user.setUserName(organization.getOrgCode()+"admin");
		user.setIsOrgadmin("1");
		Employee employee = new Employee();
		employee.setEmpNo(user.getUserName());
		employee.setEmpName(organization.getOrgCode()+"机构管理员");
		employee.setOrganization(organization);
		employee.setRootOrganization(organization);
		user.setEmployee(employee);
		user = this.saveUser(user);
		userRoleService.saveUserRole(user);
	}
	/**
	 * 新建(或编辑用户)
	 * @param userId
	 * @return
	 */
	public User editUser(String userId,String orgId){
		User user = new User();
		if(StringUtils.isNotEmpty(userId)){
			user = this.selectByPk(userId);
		}else{
			Employee employee = new Employee();
			user.setEmployee(employee);
			employee.setOrganization(organizationService.selectByPk(orgId));
		}
		return user;
	}
	
	/**
	 * 禁用用户(人员)
	 */
	@Transactional
	public void forbidUser(String userId){
		User user  = this.selectByPk(userId);
		user.setIsValidate("0");
		this.update(user);
		employeeService.forbidEmp(user.getEmployee());
	}
	/**
	 * 启用用户(人员)
	 */
	public void reuseUser(String userId){
		User user  = this.selectByPk(userId);
		user.setIsValidate("1");
		this.update(user);
		employeeService.reuseEmp(user.getEmployee());
	}
	/**
	 * 根据用户Id修改用户密码
	 * @param userId
	 * @param newPwd
	 */
	public void savePwd(String userId, String newPwd) {
		User user = this.selectByPk(userId);
		user.setPassword(newPwd);
		this.saveUser(user);
	}
	/**
	 * 校验原密码是否正确
	 * @param userId
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean checkOldPwd(String userId, String oldPwd) {
		User user = this.selectByPk(userId);
		int count=userDao.checkOldPwd(userId,(new SimpleHash(credentialsMatcher.getHashAlgorithmName(), oldPwd, ByteSource.Util.bytes(user.getUserName()), credentialsMatcher.getHashIterations())).toString());
		return count>0?false:true;
	}
	
	
	/**
	 * 校验Code是否重复
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean  checkUserName(String userName){
		int count=userDao.checkUserName(userName);
		return count>0?false:true;
	}
	@Override
	public BaseDao<User> getDao() {
		// TODO Auto-generated method stub
		return userDao;
	}
	
	
	
	

}
