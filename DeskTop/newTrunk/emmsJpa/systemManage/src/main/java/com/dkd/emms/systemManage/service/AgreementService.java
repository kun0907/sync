package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Agreement;
import com.dkd.emms.systemManage.bo.AgreementDetail;
import com.dkd.emms.systemManage.bo.AgreementSupplier;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.AgreementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
@Service
@Transactional
public class AgreementService extends BaseService <Agreement> {
    @Autowired
    private AgreementDetailService agreementDetailService;
    @Autowired
    private AgreementSupplierService agreementSupplierService;
    @Autowired
    private AgreementDao agreementDao;
    @Autowired
    private SequenceService sequenceService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    @Override
    public BaseDao<Agreement> getDao() {
        return agreementDao;
    }

    //储存或更新一个实体
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAgreement(Agreement agreement,User user) {
        this.setDefault(agreement,user);
        if( agreement.getAgreementId()==null || agreement.getAgreementId().equals("")){
            agreement.setAgreementId(UUIDGenerator.getUUID());
            agreement.setAgreementCode(sequenceService.getFlowNoByJudge("agreement", "PMB"+"KJ"+dateFormat.format(new Date()),5));
            agreementDao.insert(agreement);
        }else{
            agreementDao.update(agreement);
        }
        agreementDetailService.delete(agreement.getAgreementId());
        agreementSupplierService.delete(agreement.getAgreementId());
        List<AgreementDetail> list = new ArrayList<AgreementDetail>();
        for(AgreementDetail detail:agreement.getAgreementDetailList()){
            detail.setAgreementId(agreement.getAgreementId());
            BigDecimal totalPrice = detail.getUnitPrice().multiply(detail.getNumberMain());
            detail.setTotalPrice(totalPrice);
            list.add(detail);
        }
        //agreementDetailService.insert(detail);
        List<AgreementSupplier> list1 = new ArrayList<AgreementSupplier>();
        for(AgreementSupplier supplier:agreement.getAgreementSupplierList()) {
            supplier.setSourceId(agreement.getAgreementId());
            list1.add(supplier);
        }
        if(list.size()>0){
           agreementDetailService.insertList(list);

        }
        if(list1.size()>0){
            agreementSupplierService.insertList(list1);
        }
    }
    /**
     * 设置默认值
     * @param
     */
    private void setDefault(Agreement agreement,User user){
        if(org.apache.commons.lang.StringUtils.isEmpty(agreement.getAgreementId())){
            agreement.setCreateTime(new Date());
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(agreement.getCreateUserId())){
            agreement.setCreateUserId(user.getUserId());
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(agreement.getCreateUserName())){
            if(null == user.getEmployee()){
                agreement.setCreateUserName(user.getUserName());
            }else{
                agreement.setCreateUserName(user.getEmployee().getEmpName());
            }
        }
    }
}