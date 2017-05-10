package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.Common;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.DeliveryDao;
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
public class PickNoticeService extends BaseService<PickNotice> {
	@Autowired
	private PickNoticeDao pickNoticeDao;

	@Autowired
	private PickNoticeDetailService pickNoticeDetailService;

	@Autowired
	private DemandPlanService demandPlanService;

	@Autowired
	private SequenceService sequenceService;

	@Autowired
	private StockService stockService;


	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 编辑(新建)领料通知
	 * @param pickNoticeId
	 * @return
	 */
	public PickNotice editPickNotice(String pickNoticeId){
		PickNotice pickNotice = new PickNotice();
		if(StringUtils.isNotEmpty(pickNoticeId)){
			pickNotice = this.selectByPk(pickNoticeId);
		}
		return  pickNotice;
	}

	/**
	 * 保存领料通知
	 * @param pickNotice
	 * @param user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePickNotice(PickNotice pickNotice,User user){
		this.setDefault(pickNotice, user);
		if(StringUtils.isEmpty(pickNotice.getPickId())){
			pickNotice.setPickId(UUIDGenerator.getUUID());
			this.insert(pickNotice);
		}else{
			/*for(PickNoticeDetail pickNoticeDetail : pickNotice.getPickNoticeDetailList()){
				if(pickNoticeDetail.getPickNum().compareTo(pickNoticeDetail.getTallyedNum()) == -1){
					throw new BusinessException("物料编码为:"+pickNoticeDetail.getMaterials().getMaterialsCode()+",wbs编码为:"+pickNoticeDetail.getWbs().getProjectCode()+"的明细,领用数量不能小于已理货数量");
				}
			}*/
			this.update(pickNotice);
		}
		pickNoticeDetailService.savePickNoticeDetail(pickNotice);
	}

	private void setDefault(PickNotice pickNotice,User user){
		if(StringUtils.isEmpty(pickNotice.getPickNoticeState())){
			pickNotice.setPickNoticeState(PickNoticeEnum.pickNoticeNew.toString());//新建状态
		}
		if(StringUtils.isEmpty(pickNotice.getPickNo())){//生成编码
			pickNotice.setPickNo(sequenceService.getFlowNoByJudge("pickNotice", "PMB"+"LL"+format.format(new Date()),5));
		}
		if(null == pickNotice.getSupplier()){
			if(null != user.getEmployee()){
				pickNotice.setSupplier(user.getEmployee().getOrganization());
			}else{
				pickNotice.setSupplier(null);
			}
		}
		if(StringUtils.isEmpty(pickNotice.getCreateUserId())){
			pickNotice.setCreateUserId(user.getUserId());
			if(null != user.getEmployee()){
				pickNotice.setCreateUserName(user.getEmployee().getEmpName());
			}else {
				pickNotice.setCreateUserName(user.getUserName());
			}
		}
		if(null == pickNotice.getCreateTime()){
			pickNotice.setCreateTime(new Date());
		}
	}

	/**
	 * 删除领料通知
	 * @param pickId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void deletePickNotice(String pickId){
		PickNotice pickNotice = this.selectByPk(pickId);
		pickNoticeDao.deletePickNotice(pickNotice);
		pickNoticeDetailService.delePickNoticeDetail(pickNotice);
	}

	/**
	 * 根据施工单位查询领料通知明细
	 * @return
	 */
	public List<PickNoticeDetail> loadPickDetailListData(Object object){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(object.getClass().getSimpleName(), object);
		return pickNoticeDetailService.loadPickDetailListData(map);
	}

	/**
	 * 提交领料通知
	 * @param pickId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void commit(String pickId) {
		//根据现有库存校验(处理并发)
		PickNotice pickNotice = this.selectByPk(pickId);
		List<Stock> stockList = stockService.queryStockSumNum();
		for(PickNoticeDetail pickNoticeDetail:pickNotice.getPickNoticeDetailList()){
			for(Stock stock : stockList){
				if(StringUtils.equals(pickNoticeDetail.getMaterials().getMaterialsId(),stock.getMaterials().getMaterialsId()) &&  StringUtils.equals(pickNoticeDetail.getWbs().getProjectId(),stock.getWbs().getProjectId())){
					if((stock.getStockNum().subtract(stock.getLockNum())).compareTo(pickNoticeDetail.getPickNum()) == -1){
						throw new BusinessException("物料编码为:"+pickNoticeDetail.getMaterials().getMaterialsCode()+",wbs编码为:"+pickNoticeDetail.getWbs().getProjectCode()+"的明细,库存数量不足");
					}
				}
			}
		}
		pickNotice.setPickNoticeState(PickNoticeEnum.pickNoticeCommit.toString());
		this.update(pickNotice);
		demandPlanService.updateUsedCount(pickNotice.getPickNoticeDetailList());
	}

	/**
	 * 领料通知过时失效
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void abatePickNotice() {
		int days = 1;
		List<PickNotice> pickNoticeList = pickNoticeDao.selectAbateList(days);
		for(PickNotice pickNotice:pickNoticeList){
			pickNotice.setPickNoticeState(PickNoticeEnum.pickNoticeInvalid.toString());
			this.update(pickNotice);
			demandPlanService.subStractUsedCount(pickNotice.getPickNoticeDetailList());
		}
	}

	@Override
	public BaseDao<PickNotice> getDao() {
		return pickNoticeDao;
	}



}
