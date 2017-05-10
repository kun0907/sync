package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.bo.Process;
import com.dkd.emms.systemManage.dao.ProcessRecordDao;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/4/26.
 */
@Service
@Transactional
public class ProcessRecordService extends BaseService<ProcessRecord> {
    @Autowired
    private ProcessRecordDao processRecordDao;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ProcessRecordDetailService processRecordDetailService;

    @Override
    public BaseDao<ProcessRecord> getDao() {
        return processRecordDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void createProcessRecord(Process process,String id) {
        List<ProcessRecordDetail> detailList= new ArrayList<ProcessRecordDetail>();
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setPrecessRecordId(UUIDGenerator.getUUID());
        processRecord.setProcessRecordEntity(process.getProcessType());
        processRecord.setProcessRecordEntityId(id);
        processRecord.setSequence(1);
        processRecord.setIsFinish(false);
        processRecord.setSummitTime(new Date());
        processRecordDao.insert(processRecord);
        for(ProcessDetail processDetail:process.getProcessDetailList()){
            ProcessRecordDetail detail = new ProcessRecordDetail();
            detail.setRecordDetailId(UUIDGenerator.getUUID());
            detail.setPrecessRecordId(processRecord.getPrecessRecordId());
            detail.setProcessDetailId(processDetail.getProcessDetailId());
            detail.setIsApprove(false);
            detailList.add(detail);
        }
        processRecordDetailService.insetList(detailList);
    }
    public ProcessRecord selectByEntityId(String entityId){
        ProcessRecord processRecord = processRecordDao.selectByEntityId(entityId);
        if(null !=processRecord){
            List<ProcessRecordDetail> recordDetails = processRecordDetailService.selectByParentId(processRecord.getPrecessRecordId());
            processRecord.setProcessRecordDetailList(recordDetails);
        }
        return processRecord;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void save( ProcessRecord processRecord){
        processRecordDao.update(processRecord);
        for(ProcessRecordDetail detail:processRecord.getProcessRecordDetailList()){
            processRecordDetailService.update(detail);
        }
    }
    public Boolean buttonAuthority(User user,String id){
        List<String>roleIds = new ArrayList<String>();
        List<UserRole> roles = userRoleService.selectCheckRoleByUser(user.getUserId());
        for(UserRole ur:roles){
            roleIds.add(ur.getRole().getRoleId());
        }
        ProcessRecord processRecord = this.selectByEntityId(id);
        Boolean flag=false;
        if(null != processRecord){
            for(ProcessRecordDetail recordDetail:processRecord.getProcessRecordDetailList()){
                if(processRecord.getSequence()==recordDetail.getSequence() && roleIds.contains(recordDetail.getRoleId())){
                    flag= true;
                    break;
                }
            }
        }
        return flag;
    }}
