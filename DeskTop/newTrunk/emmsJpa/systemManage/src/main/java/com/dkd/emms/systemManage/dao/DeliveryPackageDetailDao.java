package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.DeliveryPackageDetail;
import com.dkd.emms.systemManage.bo.DeliveryPacking;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 发货包装明细Dao
 * @author WANGQ
 *
 */
@Repository
public class DeliveryPackageDetailDao extends BaseDao<DeliveryPackageDetail> {
    /**
     * 根据包装id查询其中的物料明细
     * @param packageId
     * @return
     */
    public List<DeliveryPackageDetail> queryDetailByPackageId(String packageId){
        return sqlSession.selectList("DeliveryPackageDetail.queryDetailByPackageId",packageId);
    }


    public void delDeliveryPackageDetail(String deliveryId){
        sqlSession.delete("DeliveryPackageDetail.delDeliveryPackageDetail",deliveryId);
    }

    /**
     * 批量插入发货--包裹信息
     * @param deliveryPackingList
     */
    public void insertList(List<DeliveryPackageDetail> deliveryPackingList){
        sqlSession.insert("DeliveryPackageDetail.insertList", deliveryPackingList);
    }

    /**
     * 根据发货单id查询所有的明细信息
     * @param deliveryId
     * @return
     */
    public List<DeliveryPackageDetail> queryDetailByDeliveryId(String deliveryId){
        return sqlSession.selectList("DeliveryPackageDetail.queryDetailByDeliveryId",deliveryId);
    }
}
