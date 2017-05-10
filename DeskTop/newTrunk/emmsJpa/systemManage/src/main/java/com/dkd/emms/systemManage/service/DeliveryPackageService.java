package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Delivery;
import com.dkd.emms.systemManage.bo.DeliveryPacking;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.DeliveryPackageDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 承包商管理
 * @author wangqian
 *
 */
@Service
public class DeliveryPackageService extends BaseService<DeliveryPacking> {

	@Autowired
	private DeliveryPackageDao deliveryPackageDao;
	@Override
	public BaseDao<DeliveryPacking> getDao() {
		// TODO Auto-generated method stub
		return deliveryPackageDao;
	}


	/**
	 * 根据发货单id查询发货包装信息
	 * @param deliveryId
	 * @return
	 */
	public List<DeliveryPacking> queryPackageByDeliveryId(String deliveryId){
		List<DeliveryPacking> packageList = deliveryPackageDao.queryPackageByDeliveryId(deliveryId);
		for (DeliveryPacking deliveryPacking:packageList){
			deliveryPacking.setId(deliveryPacking.getPackingId());
			deliveryPacking.setCode(deliveryPacking.getPackingNo());
		}
		return  packageList;
	}

	/**
	 *保存发货单--包裹信息
	 * @param delivery
	 */
	@Transactional
	public void saveDeliveryPackage(Delivery delivery,User user){
		deliveryPackageDao.delDeliveryPackage(delivery.getDeliveryId());
		List<DeliveryPacking> deliveryPackingList = new ArrayList<DeliveryPacking>();
		for(DeliveryPacking deliveryPacking:delivery.getDeliveryPackingList()){
			deliveryPacking.setPackingId(deliveryPacking.getId());
			this.setDefault(deliveryPacking,delivery,user);
			deliveryPackingList.add(deliveryPacking);
		}
		if(deliveryPackingList.size()>0){
			deliveryPackageDao.insertList(deliveryPackingList);
		}
	}
	/**
	 * 设置默认值
	 * @param deliveryPacking
	 */
	private  void setDefault(DeliveryPacking deliveryPacking,Delivery delivery,User user){
		if(StringUtils.isEmpty(deliveryPacking.getDeliveryId())){
			deliveryPacking.setDeliveryId(delivery.getDeliveryId());
		}
		if(StringUtils.isEmpty(deliveryPacking.getDeliveryNo())){
			deliveryPacking.setDeliveryNo(delivery.getDeliveryNo());
		}
		if(StringUtils.isEmpty(deliveryPacking.getSupplierId())){
			deliveryPacking.setSupplierId(delivery.getSupplierId());
		}
		if(StringUtils.isEmpty(deliveryPacking.getSupplierName())){
			deliveryPacking.setSupplierName(delivery.getSupplierName());
		}
		if(StringUtils.isEmpty(deliveryPacking.getPackingState())){
			deliveryPacking.setPackingState("new");//新建
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
		if(StringUtils.isEmpty(deliveryPacking.get_parentId())){
			deliveryPacking.set_parentId("0");
		}
	}

}
