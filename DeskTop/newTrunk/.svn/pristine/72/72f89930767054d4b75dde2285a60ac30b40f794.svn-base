package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.OutWarehouse;
import com.dkd.emms.systemManage.bo.OutWarehouseDetail;
import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.PickNoticeDetail;
import com.dkd.emms.systemManage.dao.OutWarehouseDetailDao;
import com.dkd.emms.systemManage.dao.PickNoticeDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 承包商管理
 * @author wangqian
 *
 */
@Service
public class OutWarehouseDetailService extends BaseService<OutWarehouseDetail> {
	@Autowired
	private OutWarehouseDetailDao outWarehouseDetailDao;

	@Override
	public BaseDao<OutWarehouseDetail> getDao() {
		return outWarehouseDetailDao;
	}

	/**
	 * 保存出库单明细
	 * @param outWarehouse
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOutWarehouseList(OutWarehouse outWarehouse) {
		this.deleteOutWarehouse(outWarehouse);
		List<OutWarehouseDetail> outWarehouseDetailList= new ArrayList<OutWarehouseDetail>();
		for(OutWarehouseDetail outWarehouseDetail:outWarehouse.getOutWarehouseDetailList()){
			outWarehouseDetail.setWarehouseDetailId(UUIDGenerator.getUUID());
			outWarehouseDetail.setOutWarehouse(outWarehouse);
			outWarehouseDetailList.add(outWarehouseDetail);
		}
		if(outWarehouseDetailList.size()>0){
			outWarehouseDetailDao.insertList(outWarehouseDetailList);
		}
	}

	/**
	 * 删除出库单明细
	 * @param outWarehouse
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOutWarehouse(OutWarehouse outWarehouse){
		outWarehouseDetailDao.deleteOutWarehouseDetail(outWarehouse);
	}
}
