package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.Design;
import com.dkd.emms.systemManage.bo.DesignCodeAttachment;
import com.dkd.emms.systemManage.bo.DrawingDetailed;
import com.dkd.emms.systemManage.dao.DesignCodeAttachmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/2/23.
 */
@Service
@Transactional
public class DesignCodeAttachmentService extends BaseService<DesignCodeAttachment> {
    @Autowired
    private DesignCodeAttachmentDao designCodeAttachmentDao;
    public BaseDao<DesignCodeAttachment> getDao() {
        return designCodeAttachmentDao;
    }
    //批量保存
    @Transactional(propagation = Propagation.REQUIRED)
    public void insetList(List<DesignCodeAttachment> list) {
        designCodeAttachmentDao.insetList(list);
    }
    public List<DesignCodeAttachment> selectByEquipment (String designId){
        return designCodeAttachmentDao.selectByEquipment(designId);
    }
}
