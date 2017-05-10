package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.page.Page;
import com.dkd.emms.systemManage.bo.ReceiptPacking;
import com.dkd.emms.systemManage.bo.ReceiptPackingDetail;
import com.dkd.emms.systemManage.dao.ReceiptPackingDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/3/8.
 */
@Service
@Transactional
public class ReceiptPackingDetailService extends BaseService<ReceiptPackingDetail> {
    @Autowired
    private ReceiptPackingDetailDao receiptPackingDetailDao;

    @Override
    public BaseDao<ReceiptPackingDetail> getDao() {
        return receiptPackingDetailDao;
    }
    public List<ReceiptPackingDetail> selectByReceiptId(String receiptId) {

        return receiptPackingDetailDao.selectByReceiptId(receiptId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<ReceiptPackingDetail> list) {
        if(list.size()>0){
            receiptPackingDetailDao.insetList(list);
        }
    }
    public List<ReceiptPackingDetail> selectOrderByCondition(Object object,Integer total,Integer start,Integer length) {
        Page page = new Page(total,start,length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(object.getClass().getSimpleName(), object);
        map.put("page", page);
        return receiptPackingDetailDao.selectOrderByCondition(map);
    }
}
