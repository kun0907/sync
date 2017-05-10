package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.page.Page;
import com.dkd.emms.systemManage.bo.AgreementDetail;
import com.dkd.emms.systemManage.bo.AgreementSupplier;
import com.dkd.emms.systemManage.dao.AgreementDetailDao;
import com.dkd.emms.systemManage.dao.AgreementSupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/24.
 */
@Service
@Transactional
public class AgreementDetailService extends BaseService<AgreementDetail> {
    @Autowired
    private AgreementDetailDao agreementDetailDao;

    @Override
    public BaseDao<AgreementDetail> getDao() {
        return agreementDetailDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertList(List<AgreementDetail> list) {
        agreementDetailDao.insertList(list);
    }
    public List<AgreementDetail> queryMaterialsData(String agreementId){

        return agreementDetailDao.queryMaterialsData(agreementId);
    }
    public AgreementDetail setDefault(AgreementDetail agreementDetail){
        return agreementDetail;
    }

    public List<AgreementDetail> selectByAgreementDetail(Object object,Integer total,Integer start,Integer length) {
        Page page = new Page(total,start,length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(object.getClass().getSimpleName(), object);
        map.put("page", page);
        return agreementDetailDao.selectByAgreementDetail(map);
    }
    public int countByAgreementDetail(Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(object.getClass().getSimpleName(), object);
        return agreementDetailDao.countByAgreementDetail(map);
    }


}
