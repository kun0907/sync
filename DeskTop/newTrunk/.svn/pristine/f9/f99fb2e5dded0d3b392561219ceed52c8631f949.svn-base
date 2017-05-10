package com.dkd.emms.web.outstorage.picknotice;


import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.PickNoticeDetail;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.PickNoticeService;
import com.dkd.emms.web.outstorage.picknotice.queryCondition.PickNoticeCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * 领料管理Controller
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/outstorage/pickNotice.do")
@SessionAttributes("currentUser")
public class PickNoticeController {

	@Autowired
	private PickNoticeService pickNoticeService;

	/**
	 * 加载领料查询页面
	 * @return
	 */
	@RequestMapping( params = {"cmd=query"},method = RequestMethod.GET)
	public String query(){
		return "outstorage/picknotice/query";
	}

	/**
	 * 加载领料数据
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadPickNoticeListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<PickNotice> loadContractorListData(@RequestParam(value = "page") int start,@RequestParam(value = "rows") int length,
													   PickNoticeCondition condition){
		PageBean<PickNotice> pageBean = new PageBean<PickNotice>();
		pageBean.setTotal(pickNoticeService.countByCondition(condition));
		pageBean.setRows(pickNoticeService.selectByCondition(condition,pageBean.getTotal(),start,length));
		return pageBean;
	}


	/**
	 * 编辑领料信息(页面)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=editPickNoticePage"},produces = "text/html",method = RequestMethod.GET)
	public String  editPickNoticePage(String pickNoticeId,ModelMap model) throws IOException{
		model.addAttribute("pickNoticeId",pickNoticeId);
		return "outstorage/picknotice/edit";
	}

	/**
	 * 查看领料信息(页面)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=viewPickNoticePage"},produces = "text/html",method = RequestMethod.GET)
	public String  viewPickNoticePage(String pickNoticeId,ModelMap model) throws IOException{
		model.addAttribute("pickNoticeId",pickNoticeId);
		return "outstorage/picknotice/view";
	}


	/**
	 * 编辑领料信息(数据)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=editPickNotice"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public PickNotice  editPickNotice(String pickNoticeId){
		return pickNoticeService.editPickNotice(pickNoticeId);
	}

	/**
	 * 保存领料信息
	 * @param pickNotice
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=savePickNotice"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String savePickNotice(@RequestBody PickNotice pickNotice,@ModelAttribute("currentUser")User user){
		pickNoticeService.savePickNotice(pickNotice, user);
		return "true";
	}

	/**
	 * 删除领料
	 * @param pickId
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=deletePickNotice"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String deletePickNotice(String pickId) throws IOException{
		pickNoticeService.deletePickNotice(pickId);
		return "true";
	}

	/**
	 * 领料通知弹出框
	 * @return
	 */
	@RequestMapping( params = {"cmd=modalPickNotice"},produces = "text/html",method = RequestMethod.GET)
	public String dialogDemand(ModelMap model){
		return "outstorage/picknotice/modalPickNotice";
	}

	/**
	 * 提交领料通知单
	 * @param pickId
	 */
	@RequestMapping( params = {"cmd=commit"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String commit(String pickId){
		pickNoticeService.commit(pickId);
		return "true";
	}


	/**
	 * 弹出框领料通知数据
	 */
	@RequestMapping( params = {"cmd=loadPickDetailListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<PickNoticeDetail> loadPickDetailListData(PickNoticeCondition pickNoticeCondition)
	{
		return pickNoticeService.loadPickDetailListData(pickNoticeCondition);
	}
}
