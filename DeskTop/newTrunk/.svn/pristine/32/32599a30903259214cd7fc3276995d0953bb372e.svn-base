package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.ProcessDetail;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.ProcessDetailDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/4/26.
 */
@Service
@Transactional
public class ProcessDetailService extends BaseService<ProcessDetail> {
    @Autowired
    private ProcessDetailDao processDetailDao;

    @Override
    public BaseDao<ProcessDetail> getDao() {
        return processDetailDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByParentId(String parentId) {
         processDetailDao.deleteByParentId(parentId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(ProcessDetail processDetail,User user,String parentId){
        if(StringUtils.isEmpty(processDetail.getProcessDetailId())){
            processDetail.setProcessDetailId(UUIDGenerator.getUUID());
            processDetail.setProcessId(parentId);
            processDetailDao.insert(processDetail);
        }else{
            processDetailDao.update(processDetail);
        }
    }
    public List<ProcessDetail> selectByParentId(String parentId){
        return processDetailDao.selectByParentId(parentId);
    }
}
