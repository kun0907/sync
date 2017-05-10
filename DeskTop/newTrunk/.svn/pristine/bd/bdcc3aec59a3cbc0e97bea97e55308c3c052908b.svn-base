package com.dkd.emms.web.system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.Project;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.core.Constant;
import com.dkd.emms.web.system.queryCondition.ProjectCondition;
import com.dkd.emms.web.util.page.PageBean;


/**
 * 主项目Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/system/mainProject.do")
@SessionAttributes("currentUser")
public class MainProjectController {

	@Autowired
	private ProjectService projectService;
	
	/**
	 * 主项目分页查询
	 * @return
	 */
	@RequestMapping( params = {"cmd=mainProjectList"},method = RequestMethod.GET)
	public String query(){
		return "system/mainProject/query";
	}
	
	/**
	 * 加载数据	
	 * @param projectCondition
	 * @return
	 */
	@RequestMapping( params = {"cmd=query"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Project> query(ProjectCondition projectCondition){
		PageBean<Project> pageBean = new PageBean<Project>();
		projectCondition.setIsMain("1");
//		pageBean.setRows(projectService.selectByCondition(projectCondition));
		pageBean.setTotal(projectService.countByCondition(projectCondition));
		return pageBean;
	}
	/**
	 * 新建主项目信息
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=edit"},produces = "text/html",method = RequestMethod.GET)
	public String edit(String projectId,Model model){
		model.addAttribute("projectId", projectId);
		return "system/mainProject/edit";
	}
	
	/**
	 * 根据项目id加载项目信息
	 * @param projectId
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadProject"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Project loadProject(String projectId){
		Project project = new Project();
		if(StringUtils.isNotEmpty(projectId)){
			project = projectService.selectProjectOrgById(projectId);
		}
		return project;
	}
	/**
	 * 启用项目
	 * @param projectId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/startProject.do")
	@ResponseBody
	public String startProject(String projectId) throws IOException{
		projectService.startProject(projectId);
		return "启用成功";
	}
	
	
	
	/**
	 * 保存主项目信息
	 * @param project
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=saveProject"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String  saveProject(Project project,@ModelAttribute("currentUser")User user) throws IOException{
		projectService.saveProject(project,user);
		return "true";
	}
	
	/**
	 * 检查项目Code是否重复
	 * @param projectCode
	 * @throws IOException 
	 */
	@RequestMapping("/checkProjectCode.do")
	@ResponseBody
	public boolean  checkProjectCode(String projectCode) throws IOException{
		return projectService.checkProjectCode(projectCode);
	}
}
