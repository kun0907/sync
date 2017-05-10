package com.dkd.emms.web.purchase.delivery;


import com.dkd.emms.systemManage.bo.DeliveryPackageDetail;
import com.dkd.emms.systemManage.service.DeliveryPackageDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


/**
 * 包装Controller
 * @author WANGQ
 *
 */

@Controller
@RequestMapping("/purchase/delivery/packageDetail.do")
public class DeliveryPackageDetailController {

	@Autowired
	private DeliveryPackageDetailService deliveryPackageDetailService;
	/**
	 * 根据包装id加载其中的明细信息
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryDeliveryDetail"},produces = "text/html",method = RequestMethod.GET)
	public String editPackage(ModelMap model,String packageId){
		model.addAttribute("packageId",packageId);
		return "purchase/delivery/editPackageDetail";
	}

	/**
	 * 根据包装id查询明细信息
	 * @param packageId
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryPackageByPackage"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<DeliveryPackageDetail> queryPackageByPackageId (String packageId){
		return deliveryPackageDetailService.queryDetailByPackageId(packageId);
	}

	/**
	 * 根据发货单id查询所有的明细信息
	 * @param deliveryId
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryPackageByDelivery"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<DeliveryPackageDetail> queryPackageByDeliveryId (String deliveryId){
		return deliveryPackageDetailService.queryDetailByDeliveryId(deliveryId);
	}

}
