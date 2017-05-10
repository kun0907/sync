package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Delivery;
import com.dkd.emms.systemManage.bo.DeliveryPacking;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class DeliveryPackageDao extends BaseDao<DeliveryPacking> {

    /**
     * 根据发货单id查询发货包装信息
     * @param deliveryId
     * @return
     */
    public List<DeliveryPacking> queryPackageByDeliveryId(String deliveryId){
        return sqlSession.selectList("DeliveryPacking.queryPackageByDeliveryId",deliveryId);
    }

    /**
     * 根据发货单id删除包裹信息
     * @param deliveryId
     */
    public void delDeliveryPackage(String deliveryId){
        sqlSession.selectList("DeliveryPacking.delDeliveryPackage",deliveryId);
    }

    /**
     * 批量插入发货--包裹信息
     * @param deliveryPackingList
     */
    public void insertList(List<DeliveryPacking> deliveryPackingList){
        sqlSession.insert("DeliveryPacking.insertList", deliveryPackingList);
    }
}
