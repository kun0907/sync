package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.QualityInspectDetail;
import com.dkd.emms.systemManage.bo.TransportInfoDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YINXP on 2017/4/25.
 */
@Repository
public class TransportInfoDetailDao extends BaseDao<TransportInfoDetail> {
    /**
     * 批量插入
     */
    public void insertList(List<TransportInfoDetail> list){
        sqlSession.insert("TransportInfoDetail.insertList", list);
    }
}
