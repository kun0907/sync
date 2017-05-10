package com.dkd.emms.web.purchase.delivery;


import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.service.DeliveryService;
import com.dkd.emms.web.baseinfo.materials.queryCondition.MaterialsCondition;
import com.dkd.emms.web.purchase.delivery.queryCondition.DeliveryCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.http.MediaType.*;


/**
 * 供应商发货Controller
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/purchase/supplierDelivery.do")
@SessionAttributes("currentUser")
public class DeliveryController {

	@Autowired
		private DeliveryService deliveryService;

	/**
	 * 加载系统物料查询页面
	 *
	 * @return
	 */
	@RequestMapping(params = {"cmd=query"}, method = RequestMethod.GET)
	public String query() {
		return "purchase/delivery/query";
	}


	/**
	 * 加载数据
	 *
	 * @return
	 */
	@RequestMapping(params = {"cmd=loadDeliveryListData"}, produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Delivery> loadMaterialListData(@RequestParam(value = "page") int start, @RequestParam(value = "rows") int length,
													DeliveryCondition deliveryCondition) {
		PageBean<Delivery> pageBean = new PageBean<Delivery>();
		pageBean.setTotal(deliveryService.countByCondition(deliveryCondition));
		pageBean.setRows(deliveryService.selectByCondition(deliveryCondition, pageBean.getTotal(), start, length));
		return pageBean;
	}

	/**
	 * 编辑发货单页面
	 * @param deliveryId
	 * @param model
	 * @return
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=editDeliveryPage"},produces = "text/html",method = RequestMethod.GET)
	public String  editUserPage(String deliveryId,ModelMap model,@ModelAttribute("currentUser")User user) throws IOException {
		model.addAttribute("deliveryId",deliveryId);
		if(null == user.getEmployee()){
			model.addAttribute("supplierId",null);
		}else {
			Organization organization = user.getEmployee().getOrganization();

			if(organization.getOrgType().contains("supplier")){
				model.addAttribute("supplierId",organization.getOrgId());
			}
		}
		return "purchase/delivery/edit";
	}

	/**
	 * 供应商发货查看页面
	 * @param deliveryId
	 * @param model
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=viewDeliveryPage"},produces = "text/html",method = RequestMethod.GET)
	public String  viewDeliveryPage(String deliveryId,ModelMap model,@ModelAttribute("currentUser")User user) throws IOException {
		model.addAttribute("deliveryId",deliveryId);
		if(null == user.getEmployee()){
			model.addAttribute("supplierId",null);
		}else {
			Organization organization = user.getEmployee().getOrganization();

			if(organization.getOrgType().contains("supplier")){
				model.addAttribute("supplierId",organization.getOrgId());
			}
		}
		return "purchase/delivery/view";
	}

	/**
	 * 新建编辑发货单明细
	 * @param deliveryId
	 * @return
	 */
	@RequestMapping( params = {"cmd=editDelivery"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Delivery  editDelivery(String deliveryId){
		Delivery delivery = new Delivery();
		if (StringUtils.isNoneEmpty(deliveryId)){
			delivery = deliveryService.selectByPk(deliveryId);
		}
		return delivery;
	}

	/**
	 * 保存发货单主表
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=saveDelivery"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveDelivery(@RequestBody Delivery delivery,@ModelAttribute("currentUser")User user) throws IOException{
		deliveryService.saveDelivery(delivery, user);
		return "true";
	}
	/**
	 * 检验code是否存在
	 */
	@RequestMapping( params = {"cmd=checkCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkCode(String code){
		return	deliveryService.checkStoNo(code,null);
	}

	/**
	 * 发货订单发货
	 * @return
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=delivery"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String delivery(String deliveryId) throws IOException{
		deliveryService.delivery(deliveryId);
		return "true";
	}
}
