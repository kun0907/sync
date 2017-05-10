package com.dkd.emms.web.system;




import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.UserService;


/**
 * 用户Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/system/user.do")
@SessionAttributes("currentUser")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 保存用户人员信息
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=saveUser"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String  saveProject(User user) throws IOException{
		userService.saveUser(user);
		return "true";
	}
	/**
	 * 编辑用户人员信息(页面)
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=editUserPage"},produces = "text/html",method = RequestMethod.GET)
	public String  editUserPage(String orgId,String userId,ModelMap model) throws IOException{
		model.addAttribute("orgId",orgId);
		model.addAttribute("userId",userId);
		return "system/employee/editEmployee";
	}
	
	/**
	 * 编辑用户人员信息(数据)
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=editUser"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public User  editUser(String orgId,String userId) throws IOException{
		return userService.editUser(userId,orgId);
	}
	/**
	 * 禁用用户(人员)
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=forbidUser"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String forbidUser(String userId) throws IOException{
		userService.forbidUser(userId);
		return "人员禁用成功";
	}
	
	/**
	 * 启用用户(人员)
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=reuseUser"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String reuseUser(String userId) throws IOException{
		userService.reuseUser(userId);
		return "人员启用成功";
	}
	
	/**
	 * 修改用户信息
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=changeUser"},produces = "text/html",method = RequestMethod.GET)
	public String changeUser(String userId,ModelMap model,@ModelAttribute("currentUser")User user){
		model.put("userId",userId);
		if(null != user.getEmployee()){
			model.put("orgId", user.getEmployee().getOrganization().getOrgId());
		}
		return "system/user/changeUser";
	}
	
	/**
	 * 修改用户基本信息(页面)
	 * @param userId
	 * @param model
	 * @return
	 */
	/*@RequestMapping( params = {"cmd=userInfoPage"},produces = "text/html",method = RequestMethod.GET)
	public String userInfoPage(String userId,ModelMap model,@ModelAttribute("currentUser")User user){
		model.put("userId", userId);
		if(null != user.getEmployee()){
			model.put("orgId", user.getEmployee().getOrganization().getOrgId());
		}
		return "system/user/editUserInfo";
	}*/
	
	/**
	 * 修改用户基本信息(数据)
	 * @param userId
	 * @return
	 */
	@RequestMapping( params = {"cmd=userInfo"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public User userInfo(String userId){
		return userService.selectByPk(userId);
	}
	
	/**
	 * 修改用户基本信息(页面)
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=passwordPage"},produces = "text/html",method = RequestMethod.GET)
	public String passwordPage(String userId,ModelMap model,HttpSession session){
		model.put("userId", userId);
		return "system/user/editPassWord";
	}
	
	
	/**
	 * 修改用户基本信息(页面)
	 * @param userId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=savePwd"},produces ="text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String savePwd(String userId,String newPwd) throws IOException{
		userService.savePwd(userId,newPwd);
		return "true";
	}
	
	
	/**
	 * 校验原密码是否正确
	 * @param userId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=checkOldPwd"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public boolean checkOldPwd(String userId,String oldPwd) throws IOException{
		return userService.checkOldPwd(userId,oldPwd);
	}
	
	
	/**
	 * 用户Code是否重复
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=checkUserName"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean  checkUserName(String userName,HttpServletResponse response) throws IOException{
		return userService.checkUserName(userName);
	}
}
