package com.dkd.emms.web.outstorage.outwarehouse;


import com.dkd.emms.systemManage.bo.OutWarehouse;
import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.OutWarehouseDetailDao;
import com.dkd.emms.systemManage.service.OutWarehouseService;
import com.dkd.emms.systemManage.service.PickNoticeService;
import com.dkd.emms.web.outstorage.outwarehouse.queryCondition.OutWarehouseCondition;
import com.dkd.emms.web.outstorage.picknotice.queryCondition.PickNoticeCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * 理货管理Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/outstorage/outwarehouse.do")
@SessionAttributes("currentUser")
public class OutWarehouseController {

	@Autowired
	private OutWarehouseService outWarehouseService;
	/**
	 * 编辑领料信息(页面)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=query"},produces = "text/html",method = RequestMethod.GET)
	public String  editPickNoticePage() throws IOException{
		return "outstorage/outwarehouse/query";
	}

	/**
	 * 加载领料数据
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadOutWarehouseListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<OutWarehouse> loadContractorListData(@RequestParam(value = "page") int start,@RequestParam(value = "rows") int length,
													   OutWarehouseCondition condition){
		PageBean<OutWarehouse> pageBean = new PageBean<OutWarehouse>();
		pageBean.setTotal(outWarehouseService.countByCondition(condition));
		pageBean.setRows(outWarehouseService.selectByCondition(condition,pageBean.getTotal(),start,length));
		return pageBean;
	}

	/**
	 * 编辑出库单信息(页面)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=editOutWarehousePage"},produces = "text/html",method = RequestMethod.GET)
	public String  editOutWarehousePage(String outWarehouseId,ModelMap model) throws IOException{
		model.addAttribute("outWarehouseId",outWarehouseId);
		return "outstorage/outwarehouse/edit";
	}

	/**
	 * 编辑领料信息(数据)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=editOutWarehouse"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public OutWarehouse  editOutWarehouse(String outWarehouseId){
		return outWarehouseService.editOutWarehouse(outWarehouseId);
	}
	/**
	 * 理货信息生成出库单信息
	 * @param outWarehouse
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=saveOutWarehouse"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveOutWarehouse(@RequestBody OutWarehouse outWarehouse,@ModelAttribute("currentUser")User user){
		outWarehouseService.saveOutWarehouseForTally(outWarehouse, user);
		return "true";
	}

	/**
	 * 提交出库单
	 * @param outWarehouseId
	 */
	@RequestMapping( params = {"cmd=commit"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String commit(String outWarehouseId){
		outWarehouseService.commit(outWarehouseId);
		return "true";
	}

	/**
	 * 出库确认
	 * @param outWarehouseId
	 */
	@RequestMapping( params = {"cmd=confirm"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String confirm(String outWarehouseId){
		outWarehouseService.confirm(outWarehouseId);
		return "true";
	}

	/**
	 * 删除出库单
	 * @param outWarehouseId
	 */
	@RequestMapping( params = {"cmd=delete"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String delete(String outWarehouseId){
		outWarehouseService.deleteOutWarehouse(outWarehouseId);
		return "true";
	}

}
