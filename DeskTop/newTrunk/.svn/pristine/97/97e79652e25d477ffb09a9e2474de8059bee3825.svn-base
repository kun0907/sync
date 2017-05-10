package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.Common;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.DeliveryDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 承包商管理
 * @author wangqian
 *
 */
@Service
public class DeliveryService extends BaseService<Delivery> {

	@Autowired
	private DeliveryDao deliveryDao;

	@Autowired
	private DeliveryPackageService deliveryPackageService;

	@Autowired
	private DeliveryPackageDetailService deliveryPackageDetailService;

	@Autowired
	private DeliveryVehicleService deliveryVehicleService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SequenceService sequenceService;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	@Override
	public BaseDao<Delivery> getDao() {
		// TODO Auto-generated method stub
		return deliveryDao;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveDelivery(Delivery delivery,User user){
		this.setDefault(delivery,user);
		this.validate(delivery);
		if(StringUtils.isEmpty(delivery.getDeliveryId())){
			delivery.setDeliveryId(UUIDGenerator.getUUID());
			delivery.setDeliveryNo(sequenceService.getFlowNoByJudge("delivery", "PMB"+"FH"+dateFormat.format(new Date()),5));
			this.insert(delivery);
		}else{
			this.update(delivery);
		}
		//保存发货单--车辆信息
		deliveryVehicleService.saveDeliveryVehicle(delivery);
		/*for(DeliveryVehicle deliveryVehicle:delivery.getDeliveryVehicleList()){
			String []packageNoArray = deliveryVehicle.getPackageNo().split(",");
			for(String packageNo:packageNoArray){
				for(DeliveryPacking deliveryPacking:delivery.getDeliveryPackingList()){
					if(StringUtils.equals(deliveryPacking.getPackingNo(),packageNo)){
						deliveryPacking.setVehicleId(deliveryVehicle.getDeVehicleId());
					}
				}
			}
		}*/
		//保存发货单--包裹信息
		deliveryPackageService.saveDeliveryPackage(delivery,user);
		//保存包裹--明细信息
		deliveryPackageDetailService.saveDeliveryPackageDetail(delivery);

	}
	/**
	 * 设置默认值
	 * @param delivery
	 */
	private void setDefault(Delivery delivery,User user){
		if(StringUtils.isEmpty(delivery.getDeliveryState())){
			delivery.setDeliveryState(DeliveryEnum.deliveryNew.toString());//新建状态
		}
		if(StringUtils.isEmpty(delivery.getSupplierId())){
			if(null != user.getEmployee()){
				delivery.setSupplierId(user.getEmployee().getOrganization().getOrgId());
				delivery.setSupplierName(user.getEmployee().getOrganization().getOrgName());
			}else{
				delivery.setSupplierId("sysadmin");
				delivery.setSupplierName(user.getUserName());
			}
		}
		if(StringUtils.isEmpty(delivery.getCreateUserId())){
			delivery.setCreateUserId(user.getUserId());
			if(null != user.getEmployee()){
				delivery.setCreateUserName(user.getEmployee().getEmpName());
			}else {
				delivery.setCreateUserName(user.getUserName());
			}
		}
		if(null == delivery.getCreateTime()){
			delivery.setCreateTime(new Date());
		}
	}

	/**
	 * 后台校验
	 * @param delivery
	 */
	private void validate(Delivery delivery){
		if(StringUtils.isEmpty(delivery.getDeliveryState())){
			throw  new BusinessException("发货单状态不能为空");
		}
		if(StringUtils.isEmpty(delivery.getSupplierId())){
			throw  new BusinessException("发货单供应商不能为空");
		}
		/*if(StringUtils.isEmpty(delivery.getDeliveryNo())){
			throw  new BusinessException("发货单单号不能为空");
		}*/
	}
	/**
	 * 校验发货单号是否重复
	 * @return
	 */
	public boolean checkStoNo(String stoNo,String stoId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("stoNo", stoNo);
		paramMap.put("stoId", stoId);
		int count=deliveryDao.checkStoNo(paramMap);
		return count>0?false:true;
	}

	/**
	 * 发货单发货
	 * @param deliveryId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delivery(String deliveryId) {
		Delivery delivery = this.selectByPk(deliveryId);
		delivery.setDeliveryState(DeliveryEnum.delivery.toString());
		delivery.setDeliveryDate(new Date());
		this.update(delivery);
		List<DeliveryPackageDetail> detailList = deliveryPackageDetailService.queryDetailByDeliveryId(deliveryId);
		orderService.updateDeliveryCount(detailList);
	}
}
