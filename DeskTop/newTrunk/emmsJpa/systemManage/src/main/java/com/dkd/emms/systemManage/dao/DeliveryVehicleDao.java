package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.DeliveryVehicle;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class DeliveryVehicleDao extends BaseDao<DeliveryVehicle> {
    /**
     * 根据发货单id查询运输明细信息
     * @param deliveryId
     * @return
     */
    public List<DeliveryVehicle> queryVehicleData(String deliveryId){
        return sqlSession.selectList("DeliveryVehicle.queryVehicleData",deliveryId);
    }

    /**
     * 根据发货单id删除车辆信息
     * @param deliveryId
     */
    public void delDeliveryVehicleDetail(String deliveryId){
        sqlSession.delete("DeliveryVehicle.delDeliveryVehicleDetail",deliveryId);
    }
    /**
     * 批量插入发货--包裹信息
     * @param deliveryVehicleList
     */
    public void insertList(List<DeliveryVehicle> deliveryVehicleList){
        sqlSession.insert("DeliveryVehicle.insertList", deliveryVehicleList);
    }

}
