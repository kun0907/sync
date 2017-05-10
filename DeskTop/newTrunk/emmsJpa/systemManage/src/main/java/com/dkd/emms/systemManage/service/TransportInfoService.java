package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.TransportInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YINXP on 2017/4/25.
 */
@Service
@Transactional
public class TransportInfoService extends BaseService<TransportInfo> {

    @Autowired
    private TransportInfoDao transportDao;

    @Autowired
    private TransportInfoDetailService transportInfoDetailService;
    @Override
    public BaseDao<TransportInfo> getDao() {
        return transportDao;
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private SequenceService sequenceService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveTransportInfo(TransportInfo transportInfo,User user) {
        this.setDefault(transportInfo,user);
        if( transportInfo.getTransportInfoId()==null ||"".equals(transportInfo.getTransportInfoId())){
            transportInfo.setTransportInfoId(UUIDGenerator.getUUID());
            transportInfo.setTransportNo(sequenceService.getFlowNoByJudge("inspect", "PMB" + "ZJ" + dateFormat.format(new Date()), 5));//materialInspect
            transportInfo.setTransportInfoDetailsId(UUIDGenerator.getUUID());
            transportDao.insert(transportInfo);
        }else{
            transportInfo.setTransportInfoDetailsId(UUIDGenerator.getUUID());
            transportDao.update(transportInfo);
        }
        transportInfoDetailService.delete(transportInfo.getTransportInfoId());
        List<TransportInfoDetail> list = new ArrayList<>();
        for(TransportInfoDetail detail:transportInfo.getTransportInfoDetails()){
            detail.setTransportInfoId(transportInfo.getTransportInfoId());
            detail.setTransportInfoDetailId(transportInfo.getTransportInfoDetailsId());
//            detail.setTra(transportInfo.getTransportNo());
            list.add(detail);
        }
        if(list.size()>0){
            transportInfoDetailService.insertList(list);
        }


    }

    /**
     * 设置默认值
     * @param
     */
    private void setDefault(TransportInfo transportInfo,User user){
        if (null == transportInfo.getCreateTime()) {
            transportInfo.setCreateTime(new Date());
        }

        if(org.apache.commons.lang.StringUtils.isEmpty(transportInfo.getCreateUserId())){
            transportInfo.setCreateUserId(user.getUserId());
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(transportInfo.getCreateUserName())){
            if(null == user.getEmployee()){
                transportInfo.setCreateUserName(user.getUserName());
            }else{
                transportInfo.setCreateUserName(user.getEmployee().getEmpName());
            }
        }

    }
}
