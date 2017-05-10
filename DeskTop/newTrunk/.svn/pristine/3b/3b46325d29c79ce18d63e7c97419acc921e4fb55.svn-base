package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.ReceiptPacking;
import com.dkd.emms.systemManage.bo.ReceiptPackingDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/3/8.
 */
@Repository
public class ReceiptPackingDetailDao extends BaseDao<ReceiptPackingDetail> {
    public List<ReceiptPackingDetail> selectByReceiptId(String receiptId) {
        return sqlSession.selectList("ReceiptPackingDetail.selectByReceiptId", receiptId);
    }
    public List<ReceiptPackingDetail> selectOrderByCondition(Map<String, Object> map) {
        return sqlSession.selectList("ReceiptPackingDetail.selectOrderByCondition", map);
    }
    /**
     * 批量插入
     */
    public void insetList(List<ReceiptPackingDetail> list){
        sqlSession.insert("ReceiptPackingDetail.insertList", list);
    }
}
