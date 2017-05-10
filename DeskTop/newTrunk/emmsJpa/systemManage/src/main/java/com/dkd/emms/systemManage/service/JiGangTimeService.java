package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.JiGangTime;
import com.dkd.emms.systemManage.dao.JiGangTimeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YINXP on 2017/4/26.
 */
@Service
@Transactional
public class JiGangTimeService extends BaseService<JiGangTime> {
   @Autowired
   private JiGangTimeDao jiGangTimeDao;

    @Override
    public BaseDao<JiGangTime> getDao() {
        return jiGangTimeDao;
    }
}
