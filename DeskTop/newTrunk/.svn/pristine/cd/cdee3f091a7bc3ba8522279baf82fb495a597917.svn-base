package com.dkd.emms.web.system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.dkd.emms.core.Constant;
import com.dkd.emms.systemManage.bo.Resource;
import com.dkd.emms.systemManage.bo.Role;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.RoleService;
import com.dkd.emms.web.system.queryCondition.RoleCondition;
import com.dkd.emms.web.util.page.PageBean;


/**
 * 角色Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping(value = "/system/role.do")
@SessionAttributes("currentUser")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * frame
	 * @return
	 * 初始化组织机构Frame
	 */
	@RequestMapping( params = {"cmd=roleFrame"},produces = "text/html",method = RequestMethod.GET)
	public String orgFrame(ModelMap model){
		model.addAttribute("centerFrameUrl", "system/role.do?cmd=roleResourceTree");
		model.addAttribute("westFrameUrl", "system/role.do?cmd=query");
		return "system/role/roleFrame";
	}
	/**
	 * 根据组织机构角色查询权限
	 * @param model
	 * @param roleId
	 * @param isSave
	 * @return
	 */
	@RequestMapping( params = {"cmd=roleResourceTree"},produces = "text/html",method = RequestMethod.GET)
	public String roleResourceTree(ModelMap model,String roleId,String isSave){
		model.addAttribute("initTreeUrl","system/role.do?cmd=initRoleResourceTree");
		model.put("isSave", isSave);
		model.put("roleId", roleId);
		return "system/common/tree";
	}
	
	/**
	 * 初始化角色权限树
	 * @param roleId
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=initRoleResourceTree"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Resource> initRoleResourceTree(@ModelAttribute("currentUser")User user,String roleId) throws IOException{
		return roleService.initRoleResourceTree(roleId,user);
	}
	/**
	 * 角色分页查询(页面)
	 * @return
	 */
	@RequestMapping( params = {"cmd=query"},produces = "text/html",method = RequestMethod.GET)
	public String query(){
		return "system/role/query";
	}
	/**
	 * 角色分页查询(数据)
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadRoleListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Role> loadRoleListData(@ModelAttribute("currentUser")User user,@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows",required = false) Integer length,
			RoleCondition roleCondition){
		if(null != user.getEmployee() && null != user.getEmployee().getOrganization().getOrgId()){
			roleCondition.setOrgId(user.getEmployee().getOrganization().getOrgId());
			roleCondition.setRoleType(RoleEnum.app_level.toString());
		}
		PageBean<Role> pageBean = new PageBean<Role>();
		pageBean.setTotal(roleService.countByCondition(roleCondition));
		pageBean.setRows(roleService.selectByCondition(roleCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}
	
	/**
	 * 新建(编辑)角色Frame信息
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=editFrame"},produces = "text/html",method = RequestMethod.GET)
	public String editFrame(String roleId,ModelMap model){
		model.addAttribute("westFrameUrl", "system/role.do?cmd=editRolePage&roleId="+roleId);
		model.addAttribute("centerFrameUrl", "system/role.do?cmd=editAuthTree&roleId="+roleId);
		return "system/role/roleFrame";
	}
	
	/**
	 * 新建(编辑)角色信息(页面)
	 * @param roleId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping( params = {"cmd=editRolePage"},produces = "text/html",method = RequestMethod.GET)
	public String edit(String roleId,ModelMap model,HttpServletRequest request){
		model.put("roleId",roleId);
		return "system/role/edit";
	}
	
	/**
	 * 新建(编辑)角色信息(数据)
	 * @param roleId
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadRoleData"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Role loadRoleData(String roleId){
		return roleService.edit(roleId);
	}
	
	/**
	 * 权限树编辑页面
	 * @return
	 * 初始化树界面
	 */

	@RequestMapping( params = {"cmd=editAuthTree"},produces = "text/html",method = RequestMethod.GET)
	public String editAuthTree(ModelMap model,String roleId,String isSave){
		model.addAttribute("initTreeUrl","system/role.do?cmd=initRoleResourceTree&roleId="+roleId);
		model.put("isSave", isSave);
		model.put("roleId", roleId);
		return "system/role/roletree";
	}
	
	/**
	 * 校验编码是否重复
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=checkCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkCode(String roleCode) throws IOException{
		return roleService.checkCode(roleCode);
	}
	
	/**
	 * 保存角色信息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=saveRole"},produces ="text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String saveRole(Role role,String resourceIds,@ModelAttribute("currentUser")User user) throws IOException{
		roleService.saveRole(role,resourceIds,user);
		return "true";
	}
	/**
	 * 保存角色权限信息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=saveRoleResource"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveRoleResource(String roleId,String resourceIds) throws IOException{
		roleService.saveRoleResource(roleId,resourceIds);
		return "为角色设置权限成功";
	}
	
	/**
	 * 根据组织机构id查询角色(加载页面)
	 * @return
	 */
	@RequestMapping( params = {"cmd=selectRoleByOrgQuery"},produces = "text/html",method = RequestMethod.GET)
	public String selectRoleByOrgQuery(@ModelAttribute("currentUser")User user,String orgId,ModelMap model){
		model.put("orgId",orgId);
		Map<String,Object> map = roleService.selectRoleByOrg(orgId,user);
		model.put("allRoleList", map.get("allRoleList"));
		model.put("orgId",orgId);
		return "system/role/allocateRoleToOrg";
	}
	/**
	 * 根据组织机构id查询角色(加载数据)
	 * @param orgId
	 * @return
	 */
	/*@RequestMapping( params = {"cmd=selectRoleByOrg"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectRoleByOrg(String orgId){
		return roleService.selectRoleByOrg(orgId);
	}*/
}
