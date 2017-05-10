package com.dkd.emms.systemManage.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.Resource;
import com.dkd.emms.systemManage.bo.Role;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.ResourceDao;



/**
 * 权限Service接口实现
 * @author sy
 *
 */

@Service
@Transactional
public class ResourceService extends BaseService<Resource>  {
	
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private OrganizationRoleService organizationRoleService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleResourceService roleResourceService;
	public BaseDao<Resource> getDao() {
		return resourceDao;
	}
	
	//根据角色所在组织的权限加载权限列表并勾选已选择的权限
	/**
	 * @param roleId
	 * @param user 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> initRoleResourceTree(String roleId, User user){
		//取出当前用户所在组织的角色
		List<Map<String, Object>> resourcelist = new ArrayList<Map<String,Object>>();
		if(user.getIsSysadmin().equals("1")){
			resourcelist = resourceDao.selectAllForRole();
		}else if(user.getIsOrgadmin().equals("1")){
			Organization org = user.getEmployee().getOrganization();
			List<Role> roles = organizationRoleService.selectCheckRoleByOrg(org.getOrgId());
			resourcelist = resourceDao.selectByRoleList(roles);
		}
		//查出所选角色下的选中权限
		Set<Resource> resources = new HashSet<Resource>();
		if(StringUtils.isNotEmpty(roleId)){
			Role role = roleService.selectByPk(roleId);
			resources = role.getResources();
		}
		//默认选中该角色已勾选的权限
		for(Map<String, Object> resourceMap:resourcelist){
			if(resources.size()!=0){
				Resource removeItem = new Resource();
				for(Resource res:resources){
					if(resourceMap.get("RESOURCEID").equals(res.getResourceId())){
						resourceMap.put("Checked", true);
						removeItem = res;
					}
				}
				resources.remove(removeItem);
			}
		}
		return resourcelist;
	}

	/**
	 * 加载编辑页面数据
	 * @param resourceId
	 */
	public Resource editResource(String resourceId) {
		Resource resource = new Resource();
		if(StringUtils.isNotEmpty(resourceId)){
			resource = this.selectByPk(resourceId);
		}
		return resource;
	}

	public boolean checkCode(String resourceCode) {
		int count=resourceDao.countByResourceCode(resourceCode);
		return count>0?false:true;
	}

	/**
	 * 根据权限id删除权限信息
	 * @param resourceId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteResource(String resourceId) {
		int count = roleResourceService.findRoleResourceByResource(resourceId);
		if(count>0){
			throw new BusinessException("该权限已被配置角色,不能删除");
		}
		this.delete(resourceId);
	}

	/**
	 * 保存权限
	 * @param resource
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveResource(Resource resource) {
		if(StringUtils.isEmpty(resource.getResourceId())){
			resource.setResourceId(UUIDGenerator.getUUID());
			resourceDao.insert(resource);
		}else{
			resourceDao.update(resource);
		}
	}
}
