package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.ProcessDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/4/26.
 */
@Repository
public class ProcessDetailDao extends BaseDao<ProcessDetail> {
    public void deleteByParentId(String parentId) {
        sqlSession.update("ProcessDetail.deleteByParentId", parentId);
    }
    public List<ProcessDetail> selectByParentId(String parentId) {
        return sqlSession.selectList("ProcessDetail.selectByParentId", parentId);
    }
}
