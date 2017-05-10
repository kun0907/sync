package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Delivery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class DeliveryDao extends BaseDao<Delivery> {
    /**
     * 校验Code是否重复
     * @param empNo
     * @return
     */
    public int checkStoNo(Map<String,Object> paramMap){
        return sqlSession.selectOne("Delivery.checkStoNo", paramMap);
    }
}
