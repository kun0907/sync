package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.DemandDetail;
import com.dkd.emms.systemManage.bo.DemandPlan;
import com.dkd.emms.systemManage.bo.ReceiptGoods;
import com.dkd.emms.systemManage.bo.ReceiptPackingDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/3/20.
 */
@Repository
public class DemandDetailDao extends BaseDao<DemandDetail> {
    public List<DemandDetail> selectByDemandId(String demandId) {
        return sqlSession.selectList("DemandDetail.selectByDemandId", demandId);
    }
    /**
     * 批量插入
     */
    public void insetList(List<DemandDetail> list){
        sqlSession.insert("DemandDetail.insertList", list);
    }
    public List<DemandDetail> selectByReceipt(Map<String,Object> map) {
        return sqlSession.selectList("DemandDetail.selectByReceipt", map);
    }
}
