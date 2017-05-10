package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.DemandDrawing;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/3/21.
 */
@Repository
public class DemandDrawingDao extends BaseDao<DemandDrawing> {
    public List<DemandDrawing> selectByDemand(Map<String,Object> map) {
        return sqlSession.selectList("DemandDrawing.selectByDemand", map);
    }
}
