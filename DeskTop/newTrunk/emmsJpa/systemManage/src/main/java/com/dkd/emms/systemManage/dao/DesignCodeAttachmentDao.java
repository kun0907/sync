package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Design;
import com.dkd.emms.systemManage.bo.DesignCodeAttachment;
import com.dkd.emms.systemManage.bo.WareHouse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/2/23.
 */
@Repository
public class DesignCodeAttachmentDao extends BaseDao<DesignCodeAttachment> {
    /**
     * 批量插入设计院关系表
     */
    public void insetList(List<DesignCodeAttachment> list){
        sqlSession.insert("DesignCodeAttachment.insertList", list);
    }
    //根据设备查询部件
    public List<DesignCodeAttachment> selectByEquipment(String designId) {
        return sqlSession.selectList("DesignCodeAttachment.selectByEAndM",designId);
    }
}

