package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/2/24.
 */
@Repository
public class OrderDetailDao extends BaseDao<OrderDetail> {
    /**
     * 批量插入
     */
    public void insetList(List<OrderDetail> list){
        sqlSession.insert("OrderDetail.insertList", list);
    }
    public List<OrderDetail> selectDetailByOrderId( Map<String,Object> map) {
        return sqlSession.selectList("OrderDetail.selectDetailByOrderId", map);
    }
}
