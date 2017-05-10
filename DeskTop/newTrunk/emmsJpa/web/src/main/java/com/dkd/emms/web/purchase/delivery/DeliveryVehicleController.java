package com.dkd.emms.web.purchase.delivery;


import com.dkd.emms.systemManage.bo.DeliveryPackageDetail;
import com.dkd.emms.systemManage.bo.DeliveryVehicle;
import com.dkd.emms.systemManage.service.DeliveryPackageDetailService;
import com.dkd.emms.systemManage.service.DeliveryVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 包装Controller
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/purchase/delivery/vehicle.do")
public class DeliveryVehicleController {

	@Autowired
	private DeliveryVehicleService deliveryVehicleService;
	/**
	 * 根据包装id加载其中的明细信息
	 * @param model
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryDeliveryVehicle"},produces = "text/html",method = RequestMethod.GET)
	public String editPackage(ModelMap model,String deliveryId){
		model.addAttribute("deliveryId",deliveryId);
		return "purchase/delivery/queryDeliveryVehicle";
	}

	/**
	 * 根据发货单id查询其中的包装信息
	 * @param deliveryId
	 * @return
	 */
	@RequestMapping( params = {"cmd=queryVehicleData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<DeliveryVehicle> queryVehicleData (String deliveryId){
		return deliveryVehicleService.queryVehicleData(deliveryId);
	}
}
