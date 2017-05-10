package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.PickNoticeDetail;
import com.dkd.emms.systemManage.bo.PickNoticeEnum;
import com.dkd.emms.systemManage.bo.User;
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
 * 定时任务Service
 * @author wangqian
 *
 */
@Service
public class JobService{
	@Autowired
	private PickNoticeService pickNoticeService;

	/**
	 * 定时任务
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void doJob(){
		pickNoticeService.abatePickNotice();
	}
}
