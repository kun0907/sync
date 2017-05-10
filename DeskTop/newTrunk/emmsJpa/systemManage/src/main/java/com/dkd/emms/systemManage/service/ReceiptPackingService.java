package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.ReceiptGoods;
import com.dkd.emms.systemManage.bo.ReceiptPacking;
import com.dkd.emms.systemManage.dao.ReceiptGoodsDao;
import com.dkd.emms.systemManage.dao.ReceiptPackingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YUZH on 2017/3/7.
 */
@Service
@Transactional
public class ReceiptPackingService extends BaseService<ReceiptPacking> {
    @Autowired
    private ReceiptPackingDao receiptPackingDao;

    @Override
    public BaseDao<ReceiptPacking> getDao() {
        return receiptPackingDao;
    }
    public List<ReceiptPacking> selectByReceiptId(String receiptId) {

        return receiptPackingDao.selectByReceiptId(receiptId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<ReceiptPacking> list) {
        if(list.size()>0){
            receiptPackingDao.insetList(list);
        }
    }
}
