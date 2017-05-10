package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/2/24.
 */
@Repository
public class OrderDao extends BaseDao<Order> {
    public List<Order> selectBySupplier( Map<String,Object> map) {
        return sqlSession.selectList("Order.selectBySupplier", map);
    }
}
