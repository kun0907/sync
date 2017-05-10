package com.dkd.emms.web.system;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.Project;
import com.dkd.emms.systemManage.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.OrganizationType;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.OrganizationService;
import com.dkd.emms.web.system.queryCondition.OrganizationCondition;
import com.dkd.emms.web.util.page.PageBean;


/**
 * 组织机构Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/system/organization.do")
@SessionAttributes("currentUser")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private RoleService roleService;
	/**
	 * frame
	 * @return
	 * 初始化组织机构Frame
	 */

	@RequestMapping( params = {"cmd=frame"},produces = "text/html",method = RequestMethod.GET)
	public String orgFrame(ModelMap model,String parentId){
		model.addAttribute("westFrameUrl", "system/organization.do?cmd=orgTree");
		if(StringUtils.isEmpty(parentId)){
			model.addAttribute("centerFrameUrl", "system/organization.do?cmd=query&parentId=0");
		}else{
			model.addAttribute("centerFrameUrl", "system/organization.do?cmd=query&parentId="+parentId+"&selfId="+parentId);
		}
		return "system/common/frame";
	}
	/**
	 * 加载组织机构树
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=orgTree"},produces = "text/html",method = RequestMethod.GET)
	public String orgTree(ModelMap model){
		model.addAttribute("initTreeUrl", "system/organization.do?cmd=initTree");
		model.addAttribute("navUrl", "system/organization.do?cmd=query&parentId=");
		return "system/organization/orgTree";
	}
	/**
	 * 初始化树节点(用户所在机构及所有下属机构)
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=initTree"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Organization> initTree(@ModelAttribute("currentUser")User user) throws IOException{
		return organizationService.selectOrgByUser(user);
	}
	
	/**
	 * 初始化查询页面
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=query"},produces = "text/html",method = RequestMethod.GET)
	public String query(@ModelAttribute("currentUser")User user,String parentId,String selfId,Model model) throws IOException{
		model.addAttribute("parentId", parentId);
		model.addAttribute("orgId", selfId);
		if(null != user.getEmployee()){
			model.addAttribute("userOrgId", user.getEmployee().getOrganization().getOrgId());
		}
		//判断如果是业主或者sysadmin的话可以新建一级组织
		model.addAttribute("isNew",true);
		if((StringUtils.equals(selfId,"0") || StringUtils.isEmpty(selfId))&&
				!(StringUtils.equals(user.getIsSysadmin(), "1") || user.getEmployee().getOrganization().getOrgType().contains("owner"))){
			model.addAttribute("isNew",false);
		}
		model.addAttribute("organization", organizationService.selectByPk(parentId));
		//组织机构角色信息
		Map<String,Object> map = roleService.selectRoleByOrg(selfId,user);
		model.addAttribute("allRoleList", map.get("allRoleList"));
		//wbs树请求地址
		model.addAttribute("initTreeUrl", "system/organization.do?cmd=initWBSTree&orgId="+selfId);
		return "system/organization/query";
	}
	/**
	 * 新建(编辑)组织机构页面
	 * @param parentId
	 * @param orgId
	 * @param model
	 */
	@RequestMapping( params = {"cmd=editOrg"},produces = "text/html",method = RequestMethod.GET)
	public String editOrg(String parentId,String orgId,Model model,String type) throws IOException{
		model.addAttribute("parentId", parentId);
		model.addAttribute("orgId", orgId);
		model.addAttribute("type", type);
		model.addAttribute("initTreeUrl", "system/organization.do?cmd=initWBSTree&orgId="+orgId);
		return "system/organization/editOrg";
	}
	/**
	 * 加载组织机构数据 
	 * @param parentId
	 * @param orgId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=loadOrgData"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Organization loadOrgData(String parentId,String orgId) throws IOException{
		Organization organization = new Organization();
		if(StringUtils.isEmpty(orgId)){
			organization.setParentId(parentId);
		}else{
			organization = organizationService.selectByPk(orgId);
		}
		return organization;
	}
	/**
	 * 保存组织机构信息
	 * @param organization
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=saveOrg"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrg(@RequestBody Organization organization,@ModelAttribute("currentUser")User user) throws IOException{
		organizationService.saveOrg(organization,user,true);
		return "true";
	}
	/**
	 * 查询本机构直接下属机构(加载页面)
	 * @param parentId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=selectOrgQuery"},produces = "text/html",method = RequestMethod.GET)
	public String selectOrgQuery(String parentId,ModelMap model) throws IOException{
		model.addAttribute("parentId", parentId);
		return "system/organization/queryOrg";
	}
	
	
	/**
	 * 查询本机构直接下属机构(加载数据)
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=selectOrg"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Organization> selectOrgByParentId(@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows",required = false) Integer length,
			OrganizationCondition orgCondition,String parentId,@ModelAttribute("currentUser")User currentUser) throws IOException{
		if(!(StringUtils.equals(currentUser.getIsSysadmin(), "1") || currentUser.getEmployee().getOrganization().getOrgType().contains("owner"))){
			orgCondition.setOrgId(currentUser.getEmployee().getOrganization().getOrgId());
		}
		orgCondition.setParentId(parentId);
		PageBean<Organization> pageBean = new PageBean<Organization>();
		pageBean.setTotal(organizationService.countByCondition(orgCondition));
		pageBean.setRows(organizationService.selectByCondition(orgCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}
	
	/**
	 * 组织机构配置WBS页面(Frame页面)
	 * @param orgId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=configWBSQuery"},produces = "text/html",method = RequestMethod.GET)
	public String configWBSQuery(String orgId,String contractorId,String type,ModelMap model) throws IOException{
		model.addAttribute("orgId", orgId);
		model.addAttribute("contractorId", contractorId);
		model.addAttribute("westFrameUrl", "system/organization.do?cmd=WBSTree&type="+type+"&orgId="+orgId+"&contractorId="+contractorId);
		model.addAttribute("centerFrameUrl", "system/organization.do?cmd=selectOrgTypeQuery&type="+type+"&orgId="+orgId+"&contractorId="+contractorId);
		return "system/organization/orgWBSFrame";
	}
	
	
	
	/**
	 * 根据组织机构id查询组织机构类型(页面)
	 * @param orgId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=selectOrgTypeQuery"},produces = "text/html",method = RequestMethod.GET)
	public String selectOrgTypeQuery(String contractorId,String orgId,String type,ModelMap model) throws IOException{
		model.put("orgId",orgId);
		model.put("contractorId",contractorId);
		model.put("type",type);
		return "system/organization/queryOrgType";
	}
	
	/**
	 * 根据组织机构id查询组织机构类型(数据)
	 * @param orgId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=selectOrgType"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<OrganizationType> selectOrgType(String orgId) throws IOException{
		return organizationService.selectOrgType(orgId);
	}

	/**
	 * 初始化WBS树
	 * @param model
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=initWBSTree"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Project> initWBSTree(ModelMap model,String orgTypeCode,String orgTypeId,String orgId, HttpServletResponse response) throws IOException{
		return organizationService.initWBSTree(orgTypeId,orgId,orgTypeCode);
	}
	
	
	/**
	 * 删除组织机构
	 * @param orgId
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=deleteOrg"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String deleteOrg(String orgId) throws IOException{
		organizationService.deleteOrg(orgId);
		return "true";
	}
	/**
	 * 禁用组织机构
	 * @param orgId
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=forbidOrg"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String forbidOrg(String orgId) throws IOException{
		organizationService.forbidOrg(orgId);
		return "true";
	}
	/**
	 * 启用组织机构
	 * @param orgId
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=reuseOrg"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String reuseOrg(String orgId) throws IOException{
		organizationService.reuseOrg(orgId);
		return "true";
	}
	/**
	 * 校验组织机构代码是否重复
	 * @param orgCode
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=checkOrgCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkOrgCode(String orgCode,String orgId,ModelMap model) throws IOException{
		return organizationService.checkOrgCode(orgCode);
	}
	
	/**
	 * 校验组织机构代码是否重复
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=saveOrgRole"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveOrgRole(String roleIds,String orgId,ModelMap model) throws IOException{
		organizationService.saveOrgRole(roleIds,orgId);
		return "true";
	}
	
	/**
	 * 获取所有的组织机构信息
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=selectAll"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Organization> selectAllOrg() throws IOException{
		return organizationService.selectAll();
		
	}
	/**
	 * 根据组织机构类别Code查询组织机构列表
	 * @return
	 */
	@RequestMapping( params = {"cmd=selectOrgByType"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Organization> selectOrgByType(String orgTypeCode){
		return organizationService.selectOrgByType(orgTypeCode);
		
	}	
}
