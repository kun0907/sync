package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Agreement;
import com.dkd.emms.systemManage.bo.JiGangInfo;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.JiGangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YINXP on 2017/4/25.
 */
@Service
@Transactional
public class JiGangService extends BaseService<JiGangInfo>{
   @Autowired
    private JiGangDao jiGangDao;

    @Override
    public BaseDao<JiGangInfo> getDao() {
        return jiGangDao;
    }


}
