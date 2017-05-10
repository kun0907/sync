package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.AgreementSupplier;
import com.dkd.emms.systemManage.dao.AgreementSupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */
@Service
@Transactional
public class AgreementSupplierService extends BaseService<AgreementSupplier> {
    @Autowired
    private AgreementSupplierDao agreementSupplierDao;

    @Override
    public BaseDao<AgreementSupplier> getDao() {
        return agreementSupplierDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertList(List<AgreementSupplier> list1) {
        agreementSupplierDao.insertList(list1);
    }

    public List<AgreementSupplier> queryAgreementSupplierData(String sourceId){

        return agreementSupplierDao.queryAgreementSupplierData(sourceId);
    }
    public AgreementSupplier setDefault(AgreementSupplier agreementSupplier){
        return agreementSupplier;
    }


}
