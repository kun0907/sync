/**
 * 
 */
package com.dkd.emms.web.baseinfo.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.Project;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.web.system.queryCondition.ProjectCondition;
import com.dkd.emms.web.util.page.PageBean;

/**
	 * @Title: ProjectController
	 * @Description:
	 * @param 
	 * @author:YUZH 
	 * @data 2017年2月9日
	 */

@Controller
@RequestMapping("/baseinfo/project.do")
@SessionAttributes("currentUser")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	//初始化Frame
	@RequestMapping( params = {"cmd=project"},produces = "text/html",method = RequestMethod.GET)
	public String project(ModelMap model){
		model.addAttribute("westFrameUrl", "baseinfo/project.do?cmd=projectTree");
		model.addAttribute("centerFrameUrl", "baseinfo/project.do?cmd=queryProject&parentId=0");
		return "system/common/frame";
	}
			
	//初始化树界面
	@RequestMapping( params = {"cmd=projectTree"},produces = "text/html",method = RequestMethod.GET)
	public String projectTree(ModelMap model){
		model.addAttribute("initTreeUrl", "baseinfo/project.do?cmd=initTree");
		model.addAttribute("navUrl", "baseinfo/project.do?cmd=queryProject&parentId=");
		return "system/common/tree";
	}
	//获取整棵权限树数据]
	@RequestMapping( params = {"cmd=initTree"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Project> initTree() throws Exception{
		List<Project> list = projectService.selectAll();
		for(Project project:list){
			project.setId(project.getProjectId());
			project.setName(project.getProjectName());
		}
		return list;
	}
	//搜索当前节点下的项目
	@RequestMapping( params = {"cmd=queryProject"},method = RequestMethod.GET)
	public String queryProject(String projectId,String parentId, ModelMap model){
		model.addAttribute("projectId",projectId);
		model.addAttribute("parentId",parentId);
		return "baseinfo/project/queryProject";
	}
	/**
	 * 加载数据	
	 * @return
	 */
	@RequestMapping( params = {"cmd=selectProject"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Project> selectWareHouse(@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows",required = false) Integer length,
			String parentId,ProjectCondition projectCondition){
		projectCondition.setParentId(parentId);
		projectCondition.setIsMain("0");
		PageBean<Project> pageBean = new PageBean<Project>();
		pageBean.setTotal(projectService.countByCondition(projectCondition));
		pageBean.setRows(projectService.selectByCondition(projectCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}	
	//搜索当前节点下的项目
	@RequestMapping( params = {"cmd=queryProjectInfo"},method = RequestMethod.GET)
	public String queryProjectInfo(String projectId,String parentId, ModelMap model){
		model.addAttribute("parentId",parentId);
		model.addAttribute("projectId",projectId);
		return "baseinfo/project/editProject";
	}
	//搜索当前节点项目
	@RequestMapping( params = {"cmd=queryThisProjectInfo"},method = RequestMethod.GET)
	public String queryThisProjectInfo(String projectId,ModelMap model){
		model.addAttribute("projectId",projectId);
		Project project = projectService.selectByPk(projectId);
		if(project != null){
			model.addAttribute("parentId",project.getParentId());
		}
		return "baseinfo/project/editProject";
	}
	//跳转到编辑页面并获取相应数据
	@RequestMapping( params = {"cmd=editProject"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> projectInfo(String projectId,String parentId){
		Project  project = new Project();
		Project  parent = new Project();
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(projectId)){
			project = projectService.selectByPk(projectId);
		}
		if(StringUtils.isNotEmpty(parentId)){
			parent = projectService.selectByPk(parentId);
		}
		map.put("parent", parent);
		map.put("project", project);
		return map;
	}
	@RequestMapping( params = {"cmd=projectSave"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String wareHouseEdit(Project project,@ModelAttribute("currentUser")User user) throws IOException{
		/*Project oldproject = projectService.selectByPk(project.getProjectId());
		oldproject.setProjectName(project.getProjectName());
		oldproject.setProjectType(project.getProjectType());*/
		projectService.saveProject(project,user);
		return "true";
	}

	/**
	 * 启用项目
	 * @param id
	 * @return
	 */
	@RequestMapping( params = {"cmd=startProject"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String startProject(String id){
		projectService.startProject(id);
		return "true";
	}


	//项目完结
	@RequestMapping( params = {"cmd=finishProject"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String finishProject(String id) throws IOException{
		projectService.finishProject(id);
		return "true";
	}


    public List<Map<String,Object>> insertChilddrens(List<Project> childrens){
		List<Map<String,Object>> childs = new ArrayList<Map<String,Object>>();
		for(Project children:childrens){
			Map<String,Object> child = new HashMap<String,Object>();
			child.put("id",children.getProjectId());
			child.put("text",children.getProjectName());
			child.put("code",children.getProjectCode());
			child.put("children",insertChilddrens(projectService.selectProjectByParentId(children.getProjectId())));
			childs.add(child);
		}
		return childs;
	}
	//下拉树
	@RequestMapping( params = {"cmd=selectToOrderCombotree"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String,Object>> selectToOrderCombotree(){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<Project> roots =  projectService.selectProjectByParentId("0");
		for(Project root:roots){
			Map<String,Object> node = new HashMap<String,Object>();
			node.put("id",root.getProjectId());
			node.put("text",root.getProjectName());
			node.put("code",root.getProjectCode());
			List<Map<String,Object>> childs =insertChilddrens(projectService.selectProjectByParentId(root.getProjectId()));
			node.put("children",childs);
			result.add(node);
		}
		return result;
	}

	/**
	 * 用户Code是否重复
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=checkProjectCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean  checkProjectCode(String projectCode,HttpServletResponse response) throws IOException{
		return projectService.checkProjectCode(projectCode);
	}
}
