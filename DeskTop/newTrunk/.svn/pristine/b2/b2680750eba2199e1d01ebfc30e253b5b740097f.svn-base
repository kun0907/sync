package com.dkd.emms.web.system;






import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dkd.emms.systemManage.bo.Role;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.UserRoleService;
import com.dkd.emms.web.system.queryCondition.EmployeeCondition;
import com.dkd.emms.web.util.page.PageBean;





/**
 * 用户授权Controller
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/system/userRole.do")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;
	/**
	 * frame
	 * @return
	 * 初始化当前登录用户所在组织机构下的所有角色和人员
	 */

	@RequestMapping( params = {"cmd=frame"},produces = "text/html",method = RequestMethod.GET)
	public String userRoleFrame(ModelMap model,HttpSession session){
		User currentUser= (User) session.getAttribute("currentUser");
		if(StringUtils.equals(currentUser.getIsSysadmin(), "1")){
			model.addAttribute("westFrameUrl", "system/userRole.do?cmd=selectEmployeeByOrgPage&orgId");
			model.addAttribute("centerFrameUrl", "system/userRole.do?cmd=selectRoleByOrgPage&orgId");
		}else{
			model.addAttribute("westFrameUrl", "system/userRole.do?cmd=selectEmployeeByOrgPage&orgId="+currentUser.getEmployee().getOrganization().getOrgId());
			model.addAttribute("centerFrameUrl", "system/userRole.do?cmd=selectRoleByOrgPage&orgId="+currentUser.getEmployee().getOrganization().getOrgId());
		}
		return "system/userRole/frame";
	}
	
	/**
	 * 初始化当前登录用户所在组织机构下的人员(页面)
	 * @param model
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@RequestMapping( params = {"cmd=selectEmployeeByOrgPage"},produces = "text/html",method = RequestMethod.GET)
	public String selectEmployeeByOrg(ModelMap model,@RequestParam("orgId") String orgId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		model.addAttribute("orgId",orgId);
		return "system/userRole/empQuery";
	}
	
	/**
	 * 初始化当前登录用户所在组织机构下的人员(数据)
	 * @param employeeCondition
	 * @param start
	 * @param length
	 * @param orgId
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@RequestMapping( params = {"cmd=selectEmployeeByOrgData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Map<String,Object>> selectEmployeeByOrgData(EmployeeCondition employeeCondition,
			@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows",required = false) Integer length,@RequestParam("orgId") String orgId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		PageBean<Map<String,Object>> pageBean = new PageBean<Map<String,Object>>();
		pageBean.setTotal(userRoleService.countEmpByCondition(employeeCondition));
		pageBean.setRows(userRoleService.selectEmployeeByOrg(employeeCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}
	/**
	 * 初始化当前登录用户所在组织机构下角色(页面)
	 * @param model
	 * @param orgId
	 * @param userId
	 * @return
	 */
	@RequestMapping( params = {"cmd=selectRoleByOrgPage"},produces = "text/html",method = RequestMethod.GET)
	public String selectRoleByOrgPage(ModelMap model,@RequestParam("orgId") String orgId,String userId){
		model.put("orgId",orgId);
		model.put("userId", userId);
		return "system/userRole/roleQuery";
	}
	
	
	/**
	 * 初始化当前登录用户所在组织机构下角色(数据)
	 * @param orgId
	 * @param userId
	 * @return
	 */
	@RequestMapping( params = {"cmd=selectRoleByOrgData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Role> selectRoleByOrgData(HttpSession session,@RequestParam("orgId") String orgId,String userId){
		User user = (User) session.getAttribute("currentUser");
		return userRoleService.selectEmployeeByOrg(orgId,user,userId);
	}
	
	/**
	 * 保存用户授权信息
	 * @param model
	 * @param roleIds
	 * @param userId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=saveAuthorizeUser"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveAuthorizeUser(@RequestParam("roleIds") String roleIds,@RequestParam("userId") String userId) throws IOException {
		userRoleService.saveAuthorizeUser(roleIds,userId);
		return "用户授权成功";
	}
}
