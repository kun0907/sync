package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.JiGangTimeDao;
import com.dkd.emms.systemManage.dao.JiGangTripDao;
import com.dkd.emms.systemManage.dao.TransportInfoDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YINXP on 2017/4/26.
 */
@Service
@Transactional
public class JiGangTripService extends BaseService<JiGangTrip> {

    @Autowired
    private JiGangTripDao jiGangTripDao;

    @Autowired
    private TransportInfoDao transportInfoDao;

    @Autowired
    private SequenceService sequenceService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public BaseDao<JiGangTrip> getDao() {
        return jiGangTripDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveJiGangTrip(JiGangTrip jiGangTrip,TransportInfo transportInfo,User user) {
        if( jiGangTrip.getJiGangTripId() ==null || jiGangTrip.getJiGangTripId().equals("")){
            jiGangTrip.setJiGangTripId(UUIDGenerator.getUUID());
            jiGangTrip.setTransportNo(sequenceService.getFlowNoByJudge("jiGangTrip", "PMB" + "YSD" + dateFormat.format(new Date()), 5));
            jiGangTripDao.insert(jiGangTrip);
            createTransport(transportInfo,user);
        }else{
            jiGangTripDao.update(jiGangTrip);
        }

    }


    //生成运输单
    public void createTransport(TransportInfo transportInfo, User user) {
        this.setDefault(transportInfo,user);
        if (transportInfo.getTransportInfoId() == null || transportInfo.getTransportInfoId().equals("")) {
            transportInfo.setTransportInfoId(UUIDGenerator.getUUID());
            transportInfo.setTransportNo(sequenceService.getFlowNoByJudge("transportInfo", "PMB" + "YSD" + dateFormat.format(new Date()), 5));
            transportInfoDao.insert(transportInfo);
        } else {
            transportInfoDao.update(transportInfo);
        }
    }

    private void setDefault(TransportInfo transportInfo, User user) {
        if(StringUtils.isEmpty(transportInfo.getCreateUserId())){
            transportInfo.setCreateUserId(user.getUserId());
            if(null != user.getEmployee()){
                transportInfo.setCreateUserName(user.getEmployee().getEmpName());
            }else {
                transportInfo.setCreateUserName(user.getUserName());
            }
        }
        if(null == transportInfo.getCreateTime()){
            transportInfo.setCreateTime(new Date());
        }
    }
}
