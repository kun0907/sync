package com.dkd.emms.web.contractor;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dkd.emms.systemManage.bo.Contractor;
import com.dkd.emms.systemManage.service.ContractorService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.web.contractor.queryCondition.ContractorCondition;
import com.dkd.emms.web.util.page.PageBean;



/**
 * 承包商Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/baseinfo/contractor.do")
@SessionAttributes("currentUser")
public class ContractorController {

	@Autowired
	private ContractorService contractorService;
	
	/**
	 * 加载承包商查询页面
	 * @return
	 */
	@RequestMapping( params = {"cmd=query"},method = RequestMethod.GET)
	public String query(){
		return "baseinfo/contractor/query";
	}
	
	/**
	 * 加载数据	
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadContractorListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Contractor> loadContractorListData(@RequestParam(value = "page") int start,@RequestParam(value = "rows") int length,
				ContractorCondition contractorCondition){
		PageBean<Contractor> pageBean = new PageBean<Contractor>();
		pageBean.setTotal(contractorService.countByCondition(contractorCondition));
		pageBean.setRows(contractorService.selectByCondition(contractorCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}
	
	/**
	 * 加载承包商编辑页面
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping( params = {"cmd=editFrame"},produces = "text/html",method = RequestMethod.GET)
	public String editFrame(String contractorId,String title,Model model){
		if(StringUtils.isNotEmpty(contractorId)){
			Contractor contractor = contractorService.selectByPk(contractorId);
			model.addAttribute("contractor",contractor);
		}
		model.addAttribute("contractorId", contractorId);
		model.addAttribute("title", title);
		return "baseinfo/contractor/editFrame";
	}
	
	/**
	 * 加载承包商编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=edit"},produces = "text/html",method = RequestMethod.GET)
	public String edit(String contractorId,Model model){
		Contractor contractor = new Contractor();
		model.addAttribute("contractorId", contractorId);
		if(StringUtils.isNotEmpty(contractorId)){
			contractor = contractorService.selectByPk(contractorId);
		}
		if(null != contractor && null != contractor.getOrganization()){
			model.addAttribute("orgId",contractor.getOrganization().getOrgId());
			model.addAttribute("initTreeUrl", "system/organization.do?cmd=initWBSTree&orgId="+contractor.getOrganization().getOrgId());
		}else{
			model.addAttribute("initTreeUrl", "system/organization.do?cmd=initWBSTree");
		}
		return "baseinfo/contractor/edit";
	}
	/**
	 * 加载承包商查看页面
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=view"},produces = "text/html",method = RequestMethod.GET)
	public String view(String contractorId,Model model){
		Contractor contractor = new Contractor();
		model.addAttribute("contractorId", contractorId);
		if(StringUtils.isNotEmpty(contractorId)){
			contractor = contractorService.selectByPk(contractorId);
		}
		if(null != contractor && null != contractor.getOrganization()){
			model.addAttribute("orgId",contractor.getOrganization().getOrgId());
			model.addAttribute("initTreeUrl", "system/organization.do?cmd=initWBSTree&orgId="+contractor.getOrganization().getOrgId());
		}else{
			model.addAttribute("initTreeUrl", "system/organization.do?cmd=initWBSTree");
		}
		return "baseinfo/contractor/view";
	}
	
	/**
	 * 加载承包商编辑页面
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadContractorData"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Contractor loadContractorData(String contractorId){
		Contractor contractor = new Contractor();
		if(StringUtils.isNotEmpty(contractorId)){
			contractor = contractorService.selectByPk(contractorId);
		}
		return contractor;
	}
	
	/**
	 * 加载承包商编辑页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=save"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("currentUser")User user,@RequestBody Contractor contractor) throws IOException{
		contractorService.save(contractor,user);
		return "true";
	}

	/**
	 * 承包商列表的提交请求
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=commit"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String commitData(String contractorId,String state) throws IOException{
		Contractor contractor = contractorService.selectByPk(contractorId);
		contractor.setContractorState(state);
		contractorService.update(contractor);
		return "true";
	}

	/**
	 * 承包商列表的删除请求
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=deleteContractor"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String deleteContractor(String contractorId) throws IOException {
		contractorService.delete(contractorId);
		return "true";
	}
	/**
	 * 承包商列表的不通过请求
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=CheckNotPass"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String CheckNotPassContractor(String contractorId) throws IOException {
		Contractor contractor = contractorService.selectByPk(contractorId);
		contractor.setContractorState("contractorNoPass");
		contractorService.update(contractor);
		return "true";
	}

}
