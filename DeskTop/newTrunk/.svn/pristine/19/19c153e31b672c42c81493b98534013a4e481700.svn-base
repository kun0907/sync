package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.OutWarehouseDao;
import com.dkd.emms.systemManage.dao.PickNoticeDao;
import org.apache.commons.lang3.StringUtils;
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
public class OutWarehouseService extends BaseService<OutWarehouse> {
	@Autowired
	private OutWarehouseDao outWarehouseDao;

	@Autowired
	private OutWarehouseDetailService outWarehouseDetailService;

	@Autowired
	private TallyingService tallyingService;

	@Autowired
	private StockService stockService;


	@Override
	public BaseDao<OutWarehouse> getDao() {
		return outWarehouseDao;
	}
	@Autowired
	private SequenceService sequenceService;

	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 生成出库单信息
	 * @param outWarehouse
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void saveOutWarehouse(OutWarehouse outWarehouse, User user) {
		this.setDefault(outWarehouse,user);
		if(StringUtils.isEmpty(outWarehouse.getOutWarehouseId())){
			outWarehouse.setOutWarehouseId(UUIDGenerator.getUUID());
			this.insert(outWarehouse);
		}else{
			this.update(outWarehouse);
		}
		outWarehouseDetailService.saveOutWarehouseList(outWarehouse);
	}

	/**
	 * 理货信息生成出库单信息
	 * @param outWarehouse
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOutWarehouseForTally(OutWarehouse outWarehouse, User user) {
		outWarehouse.setDocSourceType(OutWarehouseEnum.tallying.toString());//来源单据类别--理货单
		this.saveOutWarehouse(outWarehouse,user);
		for (OutWarehouseDetail outWarehouseDetail : outWarehouse.getOutWarehouseDetailList()) {
			Tallying tallying = tallyingService.selectByPk(outWarehouseDetail.getTallying().getTallyingId());
			//将理货单变为已生成出库单
			tallying.setIsOut("1");
			tallyingService.update(tallying);
		}
	}
	/**
	 * 直达现场信息生成出库单信息
	 * @param outWarehouse
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOutWarehouseForDirect(MaterialInspect materialInspect,OutWarehouse outWarehouse, User user) {
		outWarehouse.setDocSourceId(materialInspect.getMateriaInspectId());
		outWarehouse.setDocSourceNo(materialInspect.getInspectNo());
		outWarehouse.setDocSourceType(OutWarehouseEnum.direct.toString());
		this.saveOutWarehouse(outWarehouse, user);
	}

	private void setDefault(OutWarehouse outWarehouse, User user) {
		if(StringUtils.isEmpty(outWarehouse.getOutWarehouseState())){
			outWarehouse.setOutWarehouseState(OutWarehouseEnum.outNew.toString());//新建状态
		}
		if(StringUtils.isEmpty(outWarehouse.getOutWarehouseNo())){//生成编码
			outWarehouse.setOutWarehouseNo(sequenceService.getFlowNoByJudge("outWarehouse", "PMB" + "CK" + format.format(new Date()), 5));
		}
		if(StringUtils.isEmpty(outWarehouse.getCreateUserId())){
			outWarehouse.setCreateUserId(user.getUserId());
			if(null != user.getEmployee()){
				outWarehouse.setCreateUserName(user.getEmployee().getEmpName());
			}else {
				outWarehouse.setCreateUserName(user.getUserName());
			}
		}
		if(null == outWarehouse.getCreateTime()){
			outWarehouse.setCreateTime(new Date());
		}
	}

	/**
	 * 根据出库单id查询出库单信息
	 * @param outWarehouseId
	 * @return
	 */
	public OutWarehouse editOutWarehouse(String outWarehouseId) {
		OutWarehouse outWarehouse = new OutWarehouse();
		if(StringUtils.isNotEmpty(outWarehouseId)){
			outWarehouse = this.selectByPk(outWarehouseId);
		}
		return  outWarehouse;
	}

	/**
	 * 提交出库单信息
	 * @param outWarehouseId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void commit(String outWarehouseId) {
		OutWarehouse outWarehouse = this.selectByPk(outWarehouseId);
		outWarehouse.setOutWarehouseState(OutWarehouseEnum.outCommit.toString());
		this.update(outWarehouse);
	}

	/**
	 * 删除出库单
 	 * @param outWarehouseId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteOutWarehouse(String outWarehouseId) {
		OutWarehouse outWarehouse = this.selectByPk(outWarehouseId);
		for(OutWarehouseDetail outWarehouseDetail:outWarehouse.getOutWarehouseDetailList()){
			Tallying tallying = outWarehouseDetail.getTallying();
			tallying.setIsOut("0");//将理货明细的是否出库置为未出库
			tallyingService.update(tallying);
		}
		this.delete(outWarehouseId);
	}

	/**
	 * 出库确认
	 * @param outWarehouseId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void confirm(String outWarehouseId) {
		OutWarehouse outWarehouse = this.selectByPk(outWarehouseId);
		outWarehouse.setOutWarehouseState(OutWarehouseEnum.outFinish.toString());
		outWarehouse.setOutTime(new Date());
		this.update(outWarehouse);
		//出库确认扣减库存
		stockService.deductionStock(outWarehouse);
	}

}
