package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.ProcessRecord;
import org.springframework.stereotype.Repository;

/**
 * Created by YUZH on 2017/4/26.
 */
@Repository
public class ProcessRecordDao extends BaseDao<ProcessRecord> {
    //根据单据id查询
    public ProcessRecord selectByEntityId(String id) {
        return sqlSession.selectOne("ProcessRecord.selectByEntityId", id);
    }
}
