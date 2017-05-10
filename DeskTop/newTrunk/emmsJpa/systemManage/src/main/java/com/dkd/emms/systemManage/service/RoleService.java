package com.dkd.emms.systemManage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dkd.emms.systemManage.bo.RoleEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.Constant;
import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Resource;
import com.dkd.emms.systemManage.bo.Role;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.RoleDao;



/**
 * 角色service
 * @author WANGQ
 *
 */

@Service
@Transactional
public class RoleService extends BaseService<Role> {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleResourceService roleResourceService;
	
	@Autowired
	private OrganizationRoleService organizationRoleService;
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 新建(编辑)角色信息
	 * @param roleId
	 * @return
	 */
	@Transactional
	public Role edit(String roleId){
		Role role = new Role();
		if(StringUtils.isNotEmpty(roleId)){
			role = this.selectByPk(roleId);
		}
		return role;
	}
	/**
	 * 保存角色信息
	 * @param role
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveRole(Role role,String resourceIds,User user) {
		// TODO Auto-generated method stub
		this.setDefault(role,user);
		this.validate(role);
		if(StringUtils.isEmpty(role.getRoleId())){
			role.setRoleId(UUIDGenerator.getUUID());
			roleDao.insert(role);
		}else{
			roleDao.update(role);
		}
		roleResourceService.saveRoleResource(role.getRoleId(),resourceIds);
	}
	/**
	 * 设置默认值
	 * @param role
	 */
	private void setDefault(Role role,User currentUser){
		//设置角色默认为未删除状态
		if(StringUtils.isEmpty(role.getIsDel())){
			role.setIsDel(Constant.ROLE_NO_DEL);
		}
		//设置角色所属根组织id
		if(StringUtils.isEmpty(role.getRootOrgId())){
			if(null == currentUser.getEmployee() || null == currentUser.getEmployee().getRootOrganization()){
				role.setRootOrgId(null);
				role.setRoleType(RoleEnum.sys_level.toString());
			}else{
				role.setRootOrgId(currentUser.getEmployee().getOrganization().getOrgId());
				role.setRoleType(RoleEnum.app_level.toString());
			}
		}
	}
	/**
	 * 校验
	 * @param role
	 */
	private void validate(Role role){
		if(StringUtils.isEmpty(role.getRoleName())){
			throw new BusinessException("角色名称不能为空");
		}
		if(StringUtils.isEmpty(role.getIsDel())){
			throw new BusinessException("角色状态不能为空");
		}
		if(StringUtils.isEmpty(role.getRoleType())){
			throw new BusinessException("角色类型不能为空");
		}
	}
	/**
	 * 保存角色权限信息
	 * @param roleId
	 * @param resourceIds
	 */
	public void saveRoleResource(String roleId,String resourceIds){
		roleResourceService.saveRoleResource(roleId,resourceIds);
	}
	
	
	
	
	/**
	 * 根据当前登录用户所属主项目角色信息
	 * @param orgId
	 * @return
	 */
	public Map<String,Object> selectRoleByOrg(String orgId,User currentUser){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//根据orgId查询该组织机构所具有的角色列表
		List<Role> checkRoleList = organizationRoleService.selectCheckRoleByOrg(orgId);
		//根据当前登陆用户的组织机构id获取该组织机构的所有角色列表
		List<Role> allRoleList = new ArrayList<Role>();
		if(StringUtils.equals(currentUser.getIsSysadmin(), "1")){
			allRoleList = roleDao.getRoleByRoleType(RoleEnum.sys_level.toString());
		}else{
			//根据当前组织机构查询属于本组织机构的角色
			allRoleList = roleDao.selectAllRoleByOrg(currentUser.getEmployee().getOrganization().getOrgId());
		}
		for(Role allRole:allRoleList){
			for(Role checkRole:checkRoleList){
				if(StringUtils.equals(checkRole.getRoleId(), allRole.getRoleId())){
					allRole.setChecked(true);
					break;
				}
			}
		}
		resultMap.put("allRoleList", allRoleList);
		return resultMap;
	}
	
	/**
	 * 校验编码是否重复
	 * @param roleCode
	 * @return
	 */
	public boolean checkCode(String roleCode) {
		int count=roleDao.countByRoleCode(roleCode);
		return count>0?false:true;
	}
	/**
	 * 根据角色所在组织的权限加载权限列表并勾选已选择的权限
	 * @return
	 */
	public List<Resource> initRoleResourceTree(String roleId,User user){
		List<Map<String,Object>> mapList = resourceService.initRoleResourceTree(roleId,user);
		List<Resource> resourceList = new ArrayList<Resource>();
		for(Map<String,Object> map :mapList){
			Resource resource = new Resource();
			resource.setId(map.get("RESOURCEID").toString());
			resource.setCode(null == map.get("ROLEID")?null:map.get("ROLEID").toString());
			resource.setName(null == map.get("RESOURCENAME") ? null : map.get("RESOURCENAME").toString());
			resource.setParentId(null ==map.get("PARENT_ID")?null:map.get("PARENT_ID").toString());
			resource.setChecked(null == map.get("Checked")?false:(Boolean)map.get("Checked"));
			resourceList.add(resource);
		}
		return resourceList;
	}
	/**
	 * 根据角色类型查询角色信息
	 * @param roleType
	 * @return
	 */
	public List<Role> getRoleByRoleType(String roleType){
		return roleDao.getRoleByRoleType(roleType);
	}
	
	
	@Override
	public BaseDao<Role> getDao() {
		return roleDao;
	}
	
	
	
	
	

}
