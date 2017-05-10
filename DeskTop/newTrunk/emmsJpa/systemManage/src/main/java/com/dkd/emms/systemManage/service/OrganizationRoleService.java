package com.dkd.emms.systemManage.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.OrganizationRole;
import com.dkd.emms.systemManage.bo.Role;
import com.dkd.emms.systemManage.dao.OrganizationRoleDao;




/**
 * 组织机构Service
 * @author WANGQ
 *
 */
@Service
public class OrganizationRoleService extends BaseService<OrganizationRole> {
	
	@Autowired
	private OrganizationRoleDao  organizationRoleDao;
	/**
	 *  保存组织机构角色信息
	 * @param roleIds
	 * @param orgId
	 */
	@Transactional
	public void saveOrgRole(String roleIds,String orgId){
		this.deleteOrgRoleByOrg(orgId);
		List<OrganizationRole> orgRoleList = new ArrayList<OrganizationRole>();
		String []roleIdArray = roleIds.split(",");
		for(String roleId:roleIdArray){
			if(StringUtils.isNotEmpty(roleId)){
				Organization org = new Organization();
				Role role = new Role();
				OrganizationRole orgRole = new OrganizationRole();
				org.setOrgId(orgId);
				orgRole.setOrganization(org);
				role.setRoleId(roleId);
				orgRole.setRole(role);
				orgRoleList.add(orgRole);
			}
		}
		if(orgRoleList.size()>0){
			organizationRoleDao.insertList(orgRoleList);
		}
	}
	/**
	 * 根据组织机构id删除组织机构角色信息
	 * @param orgId
	 */
	private void deleteOrgRoleByOrg(String orgId){
		List<OrganizationRole> checkRole = organizationRoleDao.selectCheckRoleByOrg(orgId);
		for(OrganizationRole orgRole:checkRole){
			organizationRoleDao.deleteOrgRole(orgRole);
		}
	}
	/**
	 * 查询此组织机构选过的角色信息
	 * @param orgId
	 * @return
	 */
	public List<Role> selectCheckRoleByOrg(String orgId){
		List<Role> roleList = new ArrayList<Role>();
		List<OrganizationRole> checkRole = organizationRoleDao.selectCheckRoleByOrg(orgId);
		for(OrganizationRole orgRole:checkRole){
			roleList.add(orgRole.getRole());
		}
		return roleList;
	}
	
	
	@Override
	public BaseDao<OrganizationRole> getDao() {
		// TODO Auto-generated method stub
		return organizationRoleDao;
	}

	

}
