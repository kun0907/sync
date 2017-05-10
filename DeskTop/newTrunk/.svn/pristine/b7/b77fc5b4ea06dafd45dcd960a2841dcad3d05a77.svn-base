package com.dkd.emms.web.purchase.delivery;


import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.service.DeliveryPackageService;
import com.dkd.emms.systemManage.service.OrderDetailService;
import com.dkd.emms.web.purchase.delivery.queryCondition.DeliveryPackageCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 包装Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/purchase/delivery/package.do")
@SessionAttributes("currentUser")
public class DeliveryPackageController {

	@Autowired
	private DeliveryPackageService deliveryPackageService;
	@Autowired
	private OrderDetailService orderDetailService;
	/**
	 * frame
	 * @return
	 * 初始化包装明细Frame
	 */

	@RequestMapping( params = {"cmd=deliveryDetail"},produces = "text/html",method = RequestMethod.GET)
	public String deliveryDetail(ModelMap model,String deliveryId){
		model.addAttribute("westFrameUrl", "purchase/delivery/package.do?cmd=editPackage&deliveryId="+deliveryId);
		model.addAttribute("centerFrameUrl", "purchase/delivery/packageDetail.do?cmd=queryDeliveryDetail");
		return "purchase/delivery/detailFrame";
	}
	/**
	 * 加载包装部分的页面(TreeGrid)
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=editPackage"},produces = "text/html",method = RequestMethod.GET)
	public String editPackage(ModelMap model,String deliveryId,@ModelAttribute("currentUser")User user){
		if(null != user.getEmployee()){
			if(!user.getEmployee().getOrganization().getOrgType().contains("owner")){
				model.addAttribute("supplierId",user.getEmployee().getOrganization().getOrgId());
			}
		}
		model.addAttribute("deliveryId",deliveryId);
		return "purchase/delivery/editPackage";
	}

	/**
	 * 根据发货单id查询其中的包装信息
	 * @param deliveryId
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryPackage"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<DeliveryPacking> queryPackageByDeliveryId (String deliveryId){
		return deliveryPackageService.queryPackageByDeliveryId(deliveryId);
	}
	/**
	 * 发货订单包装公共弹出框
	 *
	 * @return
	 */
	@RequestMapping(params = {"cmd=modal"}, method = RequestMethod.GET)
	public String modal(String supplierId,String deliveryNo,ModelMap model) {
		model.put("supplierId",supplierId);
		model.put("deliveryNo",deliveryNo);
		return "purchase/delivery/modalDeliveryPackage";
	}
	/**
	 * 根据发货单id查询其中的包装信息
	 * @param condition
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryPackageModal"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<DeliveryPacking> queryPackageModal (Integer start,Integer length,DeliveryPackageCondition condition){
		List<DeliveryPacking> deliveryPackingList = deliveryPackageService.selectByCondition(condition, deliveryPackageService.countByCondition(condition), start, length);
		setDefault(deliveryPackingList);
		return deliveryPackingList;
	}
	/**
	 * 物资明细赋值
	 * @return
	 */
	public void setDefault(List<DeliveryPacking>list){
		for(DeliveryPacking packing:list){
			for(DeliveryPackageDetail detail:packing.getDeliveryPackageDetailList()){
			  OrderDetail orderDetail = orderDetailService.selectByPk(detail.getDocSourceDetailId());
				if(null !=orderDetail){
					detail.setMaterialsDescribe(orderDetail.getMaterialsDescribe());
					detail.setAdditional1(orderDetail.getAdditional1());
					detail.setAdditional2(orderDetail.getAdditional2());
					detail.setAdditional3(orderDetail.getAdditional3());
					detail.setAdditional4(orderDetail.getAdditional4());
					detail.setDeliveryCount(orderDetail.getDeliveryCount());
				}
			}
			if(packing.getChildPacking().size()>0){
				setDefault(packing.getChildPacking());
			}
		}
	}
}
