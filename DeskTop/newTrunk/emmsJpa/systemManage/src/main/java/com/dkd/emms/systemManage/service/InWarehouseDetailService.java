package com.dkd.emms.systemManage.service;

        import com.dkd.emms.core.dao.BaseDao;
        import com.dkd.emms.core.service.BaseService;
        import com.dkd.emms.systemManage.bo.InWarehouseDetail;
        import com.dkd.emms.systemManage.dao.InWarehouseDetailDao;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Propagation;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service
@Transactional
public class InWarehouseDetailService extends BaseService<InWarehouseDetail> {
    @Autowired
    private InWarehouseDetailDao InWarehouseDetailDao;

    @Override
    public BaseDao<InWarehouseDetail> getDao() {
        return InWarehouseDetailDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertList(List<InWarehouseDetail> list) {
        InWarehouseDetailDao.insertList(list);
    }

}
