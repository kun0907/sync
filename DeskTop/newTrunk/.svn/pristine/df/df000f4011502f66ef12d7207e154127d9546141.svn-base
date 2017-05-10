package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.bo.Process;
import com.dkd.emms.systemManage.dao.ProcessDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/4/25.
 */
@Service
@Transactional
public class ProcessService extends BaseService<Process> {
    @Autowired
    private ProcessDao processDao;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ProcessRecordService processRecordService;

    @Override
    public BaseDao<Process> getDao() {
        return processDao;
    }
    @Autowired
    private ProcessDetailService processDetailService;
    public List<Process> selectAll() {
        return getDao().selectAll();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Process process,User user){
        this.validate(process);
        if(StringUtils.isEmpty(process.getProcessId())){
            process.setCreateTime(new Date());
            process.setCreateUserId(user.getUserId());
            if(null != user.getEmployee()){
                process.setCreateUserName(user.getEmployee().getEmpName());
            }else {
                process.setCreateUserName(user.getUserName());
            }
            process.setProcessId(UUIDGenerator.getUUID());
            processDao.insert(process);
        }else{
            processDao.update(process);
        }
    }
    public void validate(Process process){
        List<Process>  list = processDao.checkType(process.getProcessType());
        if(StringUtils.isEmpty(process.getProcessId()) && list.size()>0 || StringUtils.isNotEmpty(process.getProcessId()) && list.size()>1){
            throw new BusinessException("该记录流程类型已创建");
        }
    }
    public Boolean isApprove(Object object,String id){
        List<Process>list = processDao.checkType(object.getClass().getSimpleName());
        if(list.size()>0){
          List<ProcessDetail> detailList = processDetailService.selectByParentId(list.get(0).getProcessId());
          if(detailList.size()>0){
              list.get(0).setProcessDetailList(detailList);
              processRecordService.createProcessRecord(list.get(0),id);
              return true;
          }else{
              return false;
          }
        }else{
            return false;
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean isCanApprove(User user,String id,String state){
        List<String>roleIds = new ArrayList<String>();
        List<UserRole> roles = userRoleService.selectCheckRoleByUser(user.getUserId());
        for(UserRole ur:roles){
            roleIds.add(ur.getRole().getRoleId());
        }
        ProcessRecord processRecord = processRecordService.selectByEntityId(id);
        Boolean flag=false;
        Boolean returnFlag =false;
        for(ProcessRecordDetail recordDetail:processRecord.getProcessRecordDetailList()){
            if(processRecord.getSequence()==recordDetail.getSequence() && roleIds.contains(recordDetail.getRoleId())){
                recordDetail.setIsApprove(true);
                recordDetail.setApproveTime(new Date());
                recordDetail.setApproveUserId(user.getUserId());
                if(null != user.getEmployee()){
                    recordDetail.setApproveUserName(user.getEmployee().getEmpName());
                }else {
                    recordDetail.setApproveUserName(user.getUserName());
                }
                flag= true;
                break;
            }
        }
        if(!flag){
            throw new BusinessException("当前用户没有审批权限或审批流程没有审批到此节点");
        }else{
            if(state.equals("pass")){
                if(processRecord.getProcessRecordDetailList().size()==processRecord.getSequence()){
                    processRecord.setIsFinish(true);
                    returnFlag = true;
                }else{
                    processRecord.setSequence(processRecord.getSequence()+1);
                }
            }else{
                processRecord.setIsFinish(true);
                returnFlag = true;
            }
            processRecordService.save(processRecord);
        }
        return returnFlag;
    }
}
