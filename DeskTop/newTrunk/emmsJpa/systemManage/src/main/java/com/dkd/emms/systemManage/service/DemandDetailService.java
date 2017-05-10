package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.DemandDetail;
import com.dkd.emms.systemManage.dao.DemandDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/3/20.
 */
@Service
@Transactional
public class DemandDetailService extends BaseService<DemandDetail> {
    @Autowired
    private DemandDetailDao demandDetailDao;

    @Override
    public BaseDao<DemandDetail> getDao() {
        return demandDetailDao;
    }
    public List<DemandDetail> selectByDemandId(String demandId) {
        return demandDetailDao.selectByDemandId(demandId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<DemandDetail> list) {
        if(list.size()>0){
            demandDetailDao.insetList(list);
        }
    }
    public List<DemandDetail> selectByReceipt(Map<String,Object> map) {
        return demandDetailDao.selectByReceipt(map);
    }
}
