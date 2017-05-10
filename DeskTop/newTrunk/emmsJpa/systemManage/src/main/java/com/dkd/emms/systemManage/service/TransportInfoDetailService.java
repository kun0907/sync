package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.QualityInspectDetail;
import com.dkd.emms.systemManage.bo.TransportInfoDetail;
import com.dkd.emms.systemManage.dao.TransportInfoDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YINXP on 2017/4/25.
 */
@Service
@Transactional
public class TransportInfoDetailService extends BaseService<TransportInfoDetail> {

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertList(List<TransportInfoDetail> list) {
        transportInfoDetailDao.insertList(list);
    }

    @Autowired
    private TransportInfoDetailDao transportInfoDetailDao;
    @Override
    public BaseDao<TransportInfoDetail> getDao() {
        return transportInfoDetailDao;
    }


}
