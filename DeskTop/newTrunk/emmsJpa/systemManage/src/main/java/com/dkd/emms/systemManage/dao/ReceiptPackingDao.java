package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OrderDetail;
import com.dkd.emms.systemManage.bo.ReceiptGoods;
import com.dkd.emms.systemManage.bo.ReceiptPacking;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YUZH on 2017/3/7.
 */
@Repository
public class ReceiptPackingDao extends BaseDao<ReceiptPacking> {
    public List<ReceiptPacking> selectByReceiptId(String receiptId) {
        return sqlSession.selectList("ReceiptPacking.selectByReceiptId", receiptId);
    }
    /**
     * 批量插入
     */
    public void insetList(List<ReceiptPacking> list){
        sqlSession.insert("ReceiptPacking.insertList", list);
    }
}
