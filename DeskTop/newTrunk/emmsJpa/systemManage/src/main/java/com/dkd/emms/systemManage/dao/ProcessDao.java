package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.bo.Process;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/4/25.
 */
@Repository
public class ProcessDao extends BaseDao<Process> {
    public List<Process> checkType(String type) {
        return sqlSession.selectList("Process.checkType",type);
    }
}
