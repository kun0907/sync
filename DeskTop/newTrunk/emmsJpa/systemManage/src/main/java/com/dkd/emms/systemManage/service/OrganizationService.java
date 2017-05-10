package com.dkd.emms.systemManage.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dkd.emms.systemManage.bo.*;
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
import com.dkd.emms.systemManage.dao.OrganizationDao;




/**
 * 组织机构Service
 * @author WANGQ
 *
 */
@Service
public class OrganizationService extends BaseService<Organization> {

	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private OrganizationTypeService organizationTypeService;
	
	@Autowired
	private OrganizationRoleService organizationRoleService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private OrganizationTypeWBSService organizationTypeWBSService;
	
	@Autowired
	private ContractorService contractService;
	/**
	 * 根据当前登录用户所处的组织机构查询
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Organization> selectOrgByUser(User currentUser){
		List<Organization> list = new ArrayList<Organization>();
		if(StringUtils.equals(currentUser.getIsSysadmin(), "1") || currentUser.getEmployee().getOrganization().getOrgType().contains("owner")){
			list = organizationDao.selectAll();
		}else{
			list = organizationDao.selectOrgByUser(currentUser.getEmployee().getOrganization().getOrgId());
		}
		for(Organization organization:list){
			organization.setId(organization.getOrgId());
			organization.setName(organization.getOrgName());
		}
		return list;
	}
	/**
	 * 保存组织机构信息
	 * @param organization
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrg(Organization organization,User user,boolean isSaveContractor) {
		String uuid = UUIDGenerator.getUUID();
		// TODO Auto-generated method stub
		Organization parentOrganization = this.setDefault(organization,uuid,user);
		this.validate(organization);
		if(StringUtils.isEmpty(organization.getOrgId())){
			organization.setOrgId(uuid);
			//组织机构是1级的需要生成组织admin用户,人员信息,根组织信息和组织机构信息(包括业主和供应商)
			if(StringUtils.equals(organization.getLevel(), "1")){
				userService.saveUser(organization);
				if(organization.getOrgType().contains("owner") || StringUtils.isEmpty(organization.getOrgType())){//1级组织并且组织类型为即为业主
					List<Role> roleList= roleService.getRoleByRoleType(RoleEnum.sys_level.toString());
					for(Role role:roleList){
						organizationRoleService.saveOrgRole(role.getRoleId(),organization.getOrgId());
					}
				}
				if(isSaveContractor){//保存组织机构时需要将此值设为true,保存承包商后不需要再保存供应商,此值设为false
					if(StringUtils.isNotEmpty(organization.getOrgType()) && (!organization.getOrgType().contains("owner"))){
						organization.setParentId("0");
						Contractor contractor = new Contractor();
						contractor.setOrganization(organization);
						contractService.save(contractor,user);
					}
				}
			}
			if(null != parentOrganization){
				parentOrganization.setIsLeaf("0");
				this.update(parentOrganization);
			}
			this.insert(organization);
		}else{
			this.update(organization);
		}
		this.saveOrgAndType(organization);
		this.saveOrgTypeAndWBS(organization);
	}
	
	
	/**
	 * 插入组织机构类型表
	 * @param organization
	 */
	public void saveOrgAndType(Organization organization){
		organizationTypeService.saveOrgAndType(organization);
	}
	
	
	/**
	 * 为组织机构赋默认值
	 * @param organization
	 */
	private Organization setDefault(Organization organization,String uuid,User user){
		if(StringUtils.isEmpty(organization.getIsDel())){
			organization.setIsDel("0");
		}
		Organization parentOrganization = this.selectByPk(organization.getParentId());
		if(null != parentOrganization){
			if(StringUtils.isEmpty(organization.getLevel())){
				organization.setLevel((Integer.parseInt(parentOrganization.getLevel())+1)+"");
			}
			if(StringUtils.isEmpty(organization.getRootOrgId())){
				organization.setRootOrgId(parentOrganization.getRootOrgId());
			}
			if(StringUtils.isEmpty(organization.getOrgSeq())){
				organization.setOrgSeq(parentOrganization.getOrgSeq()+"."+uuid);
			}
		}else{
			organization.setLevel("1");
//			organization.setParentId("0");
			organization.setRootOrgId(uuid);
			organization.setOrgSeq(".0."+uuid);
		}
		if(StringUtils.isEmpty(organization.getIsLeaf())){
			organization.setIsLeaf("1");
		}
		if(StringUtils.isEmpty(organization.getCreateUserId())){
			organization.setCreateUserId(user.getUserId());
		}
		if(StringUtils.isEmpty(organization.getCreateUserName())){
			if(StringUtils.equals(user.getIsSysadmin(), "1")){
				organization.setCreateUserName(user.getUserName());
			}else{
				organization.setCreateUserName(user.getEmployee().getEmpName());
			}
		}
		if(StringUtils.isEmpty(organization.getIsDel())){
			organization.setIsDel(Constant.ORG_NO_DEL);
		} 
		if(StringUtils.isEmpty(organization.getIsValidate())){
			organization.setIsValidate(Constant.ORG_IS_VALIDATE);
		}
		for(OrganizationType organizationType : organization.getOrgTypeList()){
			organization.setOrgType(organizationType.getOrgType()+","+organizationType.getOrgType());
		}
		return parentOrganization;
	}
	/**
	 * 校验组织机构各项信息
	 * @param organization
	 */
	private void validate(Organization organization){
		if(StringUtils.isEmpty(organization.getOrgId())&& StringUtils.isEmpty(organization.getOrgCode())){
			throw new BusinessException("社会统一信用代码不能为空");
		}
		if(StringUtils.isEmpty(organization.getOrgName())){
			throw new BusinessException("组织名称不能为空");
		}
	}
	/**
	 * 根据组织id删除组织信息
	 * @param orgId
	 */
	public void deleteOrg(String orgId){
		Organization organization = this.selectByPk(orgId);
		List<Employee> employeeList = employeeService.getEmployeeByOrg(orgId);
		if(StringUtils.equals(organization.getIsLeaf(), "0")){
			List<Organization> orgList = organizationDao.getSubOrg(orgId);
			if(orgList.size() >0){
				throw new BusinessException("该组织存在子组织,不能删除");
			}
		}
		if(employeeList.size()>0){
			throw new BusinessException("该组织下存在人员,不能删除");
		}
		organization.setIsDel("1");
		this.update(organization);
	}
	/**
	 * 禁用组织机构
	 * @param orgId
	 */
	public void forbidOrg(String orgId){
		Organization organization = this.selectByPk(orgId);
		organization.setIsValidate("0");
		this.update(organization);
	}
	/**
	 * 启用组织机构
	 * @param orgId
	 */
	public void reuseOrg(String orgId){
		Organization organization = this.selectByPk(orgId);
		organization.setIsValidate("1");
		this.update(organization);
	}
	/**
	 * 校验Code是否重复
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean checkOrgCode(String OrgCode){
		int count=organizationDao.countByOrgCode(OrgCode);
		return count>0?false:true;
	}
	/**
	 * 保存组织机构角色信息
	 * @param roleIds
	 * @param orgId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrgRole(String roleIds,String orgId){
		organizationRoleService.saveOrgRole(roleIds,orgId);
	}
	/**
	 * 根据组织机构id查询组织机构类型
	 * @param orgId
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<OrganizationType> selectOrgType(String orgId) {
		// TODO Auto-generated method stub
		return organizationTypeService.selectOrgType(orgId);
	}
	/**
	 * 获取WBS树信息
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Project> initWBSTree(String orgTypeId,String orgId,String orgTypeCode) {
		Organization organization = this.selectByPk(orgId);
		List<Project> projectList = projectService.selectAll();
		for(Project project:projectList) {
			project.setChkDisabled(true);//true为被禁止不能选择的
			if(StringUtils.isNotEmpty(orgTypeId)){
				//直属上级机构拥有的WBS(可被下级机构选择的wbs)
				if(null == organization || StringUtils.equals(organization.getParentId(),"0")){
					//根节点为0的，如果orgTypeId不为空才可选择
					project.setChkDisabled(false);
				}else{
					List<Project> checkProjectList = organizationTypeWBSService.selectOrgTypeWBSByOrg(organization.getParentId(),orgTypeCode);
					for(Project disabledProject:checkProjectList){
						if(StringUtils.equals(project.getProjectId(),disabledProject.getProjectId())){
							project.setChkDisabled(false);//false是未被禁止的
						}
					}
				}
				/*if(!StringUtils.equals(organization.getParentId(),"0")) {

				}else{

				}*/
			}
		}
		List<Map<String,Object>> organizationTypeWBSList = organizationTypeWBSService.selectOrgTypeWBSByType(orgTypeId,orgId);
		for(Project project:projectList){
			project.setId(project.getProjectId());
			project.setName(project.getProjectName());
			for(Map<String,Object> map:organizationTypeWBSList){
				if(null != map.get("WBS_ID")){
					if(StringUtils.equals(project.getProjectId(), map.get("WBS_ID").toString())){
						if(null != map.get("ORG_TYPE_ID")){
							project.setOrgType(map.get("ORG_TYPE_ID").toString());
						}
						project.setChecked(true);
					}else{
						continue;
					}
				}
			}
		}
		// TODO Auto-generated method stub
		return projectList;
	}
	/**
	 * 保存组织机构id和wbs信息
	 */
	public void saveOrgTypeAndWBS(Organization organization) {
		// TODO Auto-generated method stub
		organizationTypeWBSService.saveOrgTypeAndWBS(organization);
	}
	/**
	 * 根据组织机构类别Code查询组织机构列表
	 * @param orgTypeCode
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Organization> selectOrgByType(String orgTypeCode) {
		// TODO Auto-generated method stub
		return organizationDao.selectOrgByType(orgTypeCode);
	}
	@Override
	public BaseDao<Organization> getDao() {
		// TODO Auto-generated method stub
		return organizationDao;
	}
	
	
}
