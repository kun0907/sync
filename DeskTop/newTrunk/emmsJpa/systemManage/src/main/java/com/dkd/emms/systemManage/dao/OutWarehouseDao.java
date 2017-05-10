package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OutWarehouse;
import com.dkd.emms.systemManage.bo.PickNotice;
import org.springframework.stereotype.Repository;

/**
 * 领料通知单
 * @author wangqian
 *
 */
@Repository
public class OutWarehouseDao extends BaseDao<OutWarehouse> {

    public void deletePickNotice(PickNotice pickNotice){
        sqlSession.delete("PickNotice.deletePickNotice",pickNotice);
    }
}
