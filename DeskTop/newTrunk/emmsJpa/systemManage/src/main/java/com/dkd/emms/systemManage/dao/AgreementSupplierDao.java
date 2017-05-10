package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;

import com.dkd.emms.systemManage.bo.AgreementSupplier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
@Repository
public class AgreementSupplierDao extends BaseDao<AgreementSupplier>{
    /**
     * 批量插入
     */
    public void insertList(List<AgreementSupplier> list){
        sqlSession.insert("AgreementSupplier.insertList", list);
    }

    /**
     * 根据供应商id查询供应商信息
     * @param
     * @return
     */
    public List<AgreementSupplier> queryAgreementSupplierData(String supplierOrgId){
        return sqlSession.selectList("DeliveryVehicle.queryAgreementSupplierData",supplierOrgId);
    }
}
