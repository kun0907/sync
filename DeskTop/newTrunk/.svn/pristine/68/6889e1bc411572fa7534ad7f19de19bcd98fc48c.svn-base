package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Delivery;
import com.dkd.emms.systemManage.bo.DeliveryVehicle;
import com.dkd.emms.systemManage.dao.DeliveryVehicleDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class DeliveryVehicleService extends BaseService<DeliveryVehicle> {

    @Autowired
    private DeliveryVehicleDao deliveryVehicleDao;
    @Override
    public BaseDao<DeliveryVehicle> getDao() {
        return deliveryVehicleDao;
    }


    public List<DeliveryVehicle> queryVehicleData(String deliveryId){

        return deliveryVehicleDao.queryVehicleData(deliveryId);
    }


    @Transactional
    public List<DeliveryVehicle> saveDeliveryVehicle(Delivery delivery){
        deliveryVehicleDao.delDeliveryVehicleDetail(delivery.getDeliveryId());
        List<DeliveryVehicle> deliveryVehicleList = new ArrayList<DeliveryVehicle>();
        for(DeliveryVehicle deliveryVehicle:delivery.getDeliveryVehicleList()){
            this.setDefault(deliveryVehicle,delivery);
            deliveryVehicleList.add(deliveryVehicle);
        }
        if(deliveryVehicleList.size()>0){
            deliveryVehicleDao.insertList(deliveryVehicleList);
        }
        return  this.queryVehicleData(delivery.getDeliveryId());
    }


    /**
     * 设置默认值
     * @param deliveryVehicle
     */
    private  void setDefault(DeliveryVehicle deliveryVehicle,Delivery delivery){
        if(StringUtils.isEmpty(deliveryVehicle.getDeliveryId())){
            deliveryVehicle.setDeliveryId(delivery.getDeliveryId());
        }
        if(StringUtils.isEmpty(deliveryVehicle.getDeliveryNo())){
            deliveryVehicle.setDeliveryNo(delivery.getDeliveryNo());
        }
        if(StringUtils.isEmpty(deliveryVehicle.getDeVehicleState())){
            deliveryVehicle.setDeVehicleState("new");//新建状态
        }
    }
}
