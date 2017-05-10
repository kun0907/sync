package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.DemandChangeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YUZH on 2017/3/21.
 */
@Repository
public class DemandChangeRecordDao extends BaseDao<DemandChangeRecord> {
    /**
     * 批量插入
     */
    public void insetList(List<DemandChangeRecord> list){
        sqlSession.insert("DemandChangeRecord.insertList", list);
    }
}
