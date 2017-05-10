package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.Constant;
import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Employee;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.Tallying;
import com.dkd.emms.systemManage.dao.EmployeeDao;
import com.dkd.emms.systemManage.dao.TallyingDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 人员信息Service
 * @author WANGQ
 *
 */
@Service
public class TallyingService extends BaseService<Tallying> {

	@Autowired
	private TallyingDao tallyingDao;

	@Autowired
	private PickNoticeDetailService pickNoticeDetailService;

	@Autowired
	private StockService stockService;
	@Override
	public BaseDao<Tallying> getDao() {
		// TODO Auto-generated method stub
		return tallyingDao;
	}

	/**
	 * 保存理货明细信息
	 * @param tallyingList
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveTallying(List<Tallying> tallyingList) {
		for (Tallying tallying:tallyingList){
			tallying.setTallyingId(UUIDGenerator.getUUID());
		}
		tallyingDao.insertList(tallyingList);
		pickNoticeDetailService.updateTallyNum(tallyingList);

		//库存锁定
		stockService.lockStock(tallyingList);
	}

	/**
	 * 查询理货明细信息
	 * @return
	 */
	public List<Tallying> queryTallyList() {
		return tallyingDao.queryTallyList();
	}

	/**
	 * 删除领料通知明细
	 * @param tallyId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delTally(String tallyId) {
		List<Tallying> tallyingList = new ArrayList<Tallying>();
		Tallying tallying = this.selectByPk(tallyId);
		tallyingList.add(tallying);
		this.delete(tallyId);
		tallying.setTallyingNum(tallying.getTallyingNum().multiply(new BigDecimal(-1)));
		pickNoticeDetailService.updateTallyNum(tallyingList);
	}
}
