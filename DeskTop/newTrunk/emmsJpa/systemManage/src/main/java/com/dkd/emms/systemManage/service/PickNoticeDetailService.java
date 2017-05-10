package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.PickNoticeDao;
import com.dkd.emms.systemManage.dao.PickNoticeDetailDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 承包商管理
 * @author wangqian
 *
 */
@Service
public class PickNoticeDetailService extends BaseService<PickNoticeDetail> {
	@Autowired
	private PickNoticeDetailDao pickNoticeDetailDao;

	@Override
	public BaseDao<PickNoticeDetail> getDao() {
		return pickNoticeDetailDao;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void delePickNoticeDetail(PickNotice pickNotice){
		pickNoticeDetailDao.deleteDetailByPickId(pickNotice);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePickNoticeDetail(PickNotice pickNotice){
		//编辑保存需要校验如果有已理货数量,则修改的领用数量不能小于已理货数量
		this.delePickNoticeDetail(pickNotice);
		List<PickNoticeDetail> pickNoticeDetailList = new ArrayList<PickNoticeDetail>();
		for(PickNoticeDetail pickNoticeDetail:pickNotice.getPickNoticeDetailList()){
			pickNoticeDetail.setPickDetailId(UUIDGenerator.getUUID());
			pickNoticeDetail.setPickNotice(pickNotice);
			pickNoticeDetailList.add(pickNoticeDetail);
		}
		if(pickNoticeDetailList.size()>0){
			pickNoticeDetailDao.insertList(pickNoticeDetailList);
		}
	}

	/**
	 * 根据查询条件查询领料明细
	 * @param map
	 * @return
	 */
	public List<PickNoticeDetail> loadPickDetailListData(Map<String, Object> map){
		return pickNoticeDetailDao.loadPickDetailListData(map);
	}

	/**
	 * 更新已领用数量
	 * @param tallyingList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateTallyNum(List<Tallying> tallyingList) {
		for(Tallying tallying : tallyingList){
			PickNoticeDetail pickNoticeDetail = this.selectByPk(tallying.getPickNoticeDetail().getPickDetailId());
			pickNoticeDetail.setTallyedNum(pickNoticeDetail.getTallyedNum().add(tallying.getTallyingNum()));
			this.update(pickNoticeDetail);
		}
	}
}
