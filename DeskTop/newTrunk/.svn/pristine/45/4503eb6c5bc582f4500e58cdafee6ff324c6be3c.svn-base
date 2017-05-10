package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OutWarehouse;
import com.dkd.emms.systemManage.bo.OutWarehouseDetail;
import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.PickNoticeDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 领料通知单
 * @author wangqian
 *
 */
@Repository
public class OutWarehouseDetailDao extends BaseDao<OutWarehouseDetail> {
    /**
     * 根据出库单删除出库单明细
     * @param outWarehouse
     */
    public void deleteOutWarehouseDetail(OutWarehouse outWarehouse) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("outWarehouse",outWarehouse);
        sqlSession.delete("OutWarehouseDetail.deleteOutWarehouseDetail",paramMap);
    }

    /**
     * 批量插入出库单明细信息
     * @param outWarehouseDetailList
     */
    public void insertList(List<OutWarehouseDetail> outWarehouseDetailList) {
        sqlSession.insert("OutWarehouseDetail.insertList", outWarehouseDetailList);
    }
}
