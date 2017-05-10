package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.InWarehouseDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Repository
public class InWarehouseDetailDao extends BaseDao<InWarehouseDetail> {
    /**
     * 批量插入
     */
    public void insertList(List<InWarehouseDetail> list){
        sqlSession.insert("InWarehouseDetail.insertList", list);
    }
    public List<InWarehouseDetail> selectByInWarehouseId(String inWarehouseId) {
        return sqlSession.selectList("InWarehouseDetail.selectByInWarehouseId", inWarehouseId);
    }

}
