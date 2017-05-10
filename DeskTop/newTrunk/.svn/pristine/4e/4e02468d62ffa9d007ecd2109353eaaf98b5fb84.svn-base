package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.AgreementDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 */
@Repository
public class AgreementDetailDao extends BaseDao<AgreementDetail> {
    /**
     * 批量插入
     */
    public void insertList(List<AgreementDetail> list){
        sqlSession.insert("AgreementDetail.insertList", list);
    }

    /**
     * 根据框架协议id查询物资明细信息
     * @param agreementId
     * @return
     */
    public List<AgreementDetail> queryMaterialsData(String agreementId){
        return sqlSession.selectList("<AgreementDetail.queryMaterialsData",agreementId);
    }
    public List<AgreementDetail> selectByAgreementDetail(Map<String, Object> map) {
        return sqlSession.selectList("AgreementDetail.selectByAgreementDetail",map);
    }

    public int countByAgreementDetail(Object param) {

        Integer count = (Integer) sqlSession.selectOne("AgreementDetail.countByAgreementDetail",param);
        return count.intValue();
    }
}
