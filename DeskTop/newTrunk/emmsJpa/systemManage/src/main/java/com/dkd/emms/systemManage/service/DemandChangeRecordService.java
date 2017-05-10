package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.DemandChangeRecord;
import com.dkd.emms.systemManage.bo.DemandDetail;
import com.dkd.emms.systemManage.dao.DemandChangeRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/3/21.
 */
@Service
@Transactional
public class DemandChangeRecordService extends BaseService<DemandChangeRecord> {
    @Autowired
    private DemandChangeRecordDao demandChangeRecordDao;

    @Override
    public BaseDao<DemandChangeRecord> getDao() {
        return demandChangeRecordDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<DemandDetail> list) {
        List<DemandChangeRecord> recordList = new ArrayList<DemandChangeRecord>();
        for(DemandDetail detail:list){
            DemandChangeRecord record = new DemandChangeRecord();
            record.setChangeId(UUIDGenerator.getUUID());
            record.setDemandId(detail.getDemandId());
            record.setDemandDetailId(detail.getDemandDetailId());
            record.setChangeDate(new Date());
            record.setChangeCount(detail.getChangeCount());
            recordList.add(record);
        }
        if(recordList.size()>0){
            demandChangeRecordDao.insetList(recordList);
        }
    }
}
