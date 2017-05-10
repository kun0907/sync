package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OrderDetail;
import com.dkd.emms.systemManage.bo.ProcessRecordDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/4/26.
 */
@Repository
public class ProcessRecordDetailDao extends BaseDao<ProcessRecordDetail> {
    /**
     * 批量插入
     */
    public void insetList(List<ProcessRecordDetail> list){
        sqlSession.insert("ProcessRecordDetail.insertList", list);
    }
    public List<ProcessRecordDetail> selectByParentId(String id) {
        return sqlSession.selectList("ProcessRecordDetail.selectByParentId", id);
    }
}
