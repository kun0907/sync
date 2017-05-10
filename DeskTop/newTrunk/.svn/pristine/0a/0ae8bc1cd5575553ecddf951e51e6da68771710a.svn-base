package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Order;
import com.dkd.emms.systemManage.bo.OrderDetail;
import com.dkd.emms.systemManage.dao.OrderDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/2/24.
 */
@Service
@Transactional
public class OrderDetailService extends BaseService<OrderDetail> {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public BaseDao<OrderDetail> getDao() {
        return orderDetailDao;
    }
    //批量保存
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<OrderDetail> list) {
        orderDetailDao.insetList(list);
    }
    public List<OrderDetail> selectDetailByOrderId(Map<String,Object>map){
        return orderDetailDao.selectDetailByOrderId(map);
    }
}
