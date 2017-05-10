package com.dkd.emms.web.system;




import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.dkd.emms.systemManage.bo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.systemManage.bo.Employee;
import com.dkd.emms.systemManage.service.EmployeeService;
import com.dkd.emms.web.system.queryCondition.EmployeeCondition;
import com.dkd.emms.web.util.page.PageBean;




/**
 * 人员信息Controller
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/system/employee.do")
@SessionAttributes("currentUser")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	/**
	 * 根据组织机构加载人员列表页面
	 * @param orgId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=query"},produces = "text/html",method = RequestMethod.GET)
	
	public String query(String orgId,Model model) throws IOException{
		model.addAttribute("orgId",orgId);
		return "system/employee/queryEmployee";
	}
	
	/**
	 * 查询本机构直接下属机构(加载数据)
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=loadEmpListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Employee> loadEmpListData(@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows",required = false) Integer length,
			EmployeeCondition empCondition,String orgId,@ModelAttribute("currentUser")User currentUser){
		if(!(StringUtils.equals(currentUser.getIsSysadmin(), "1") || currentUser.getEmployee().getOrganization().getOrgType().contains("owner"))){
			empCondition.setOrgId(currentUser.getEmployee().getOrganization().getOrgId());
		}else{
			empCondition.setOrgId(orgId);
		}
		PageBean<Employee> pageBean = new PageBean<Employee>();
		pageBean.setTotal(employeeService.countByCondition(empCondition));
		pageBean.setRows(employeeService.selectByCondition(empCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}
	
	/**
	 * 人员Code是否重复
	 * @throws IOException
	 */
	@RequestMapping("/checkEmpNo.do")
	@ResponseBody
	public boolean  checkEmpNo(String empNo,String empId) throws IOException{
		return employeeService.checkEmpNo(empNo,empId);
	}
}
