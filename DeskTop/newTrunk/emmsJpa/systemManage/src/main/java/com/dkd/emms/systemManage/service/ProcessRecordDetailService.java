package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.ProcessDetail;
import com.dkd.emms.systemManage.bo.ProcessRecordDetail;
import com.dkd.emms.systemManage.dao.ProcessRecordDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YUZH on 2017/4/26.
 */
@Service
@Transactional
public class ProcessRecordDetailService extends BaseService<ProcessRecordDetail> {
    @Autowired
    private ProcessRecordDetailDao processRecordDetailDao;

    @Override
    public BaseDao<ProcessRecordDetail> getDao() {
        return processRecordDetailDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<ProcessRecordDetail> list) {
        processRecordDetailDao.insetList(list);
    }
    public List<ProcessRecordDetail> selectByParentId(String id){
        return processRecordDetailDao.selectByParentId(id);
    }
}
