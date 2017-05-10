package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Contractor;
import com.dkd.emms.systemManage.bo.PickNotice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 领料通知单
 * @author wangqian
 *
 */
@Repository
public class PickNoticeDao extends BaseDao<PickNotice> {

    public void deletePickNotice(PickNotice pickNotice){
        sqlSession.delete("PickNotice.deletePickNotice",pickNotice);
    }

    /**
     * 根据定义的失效时间查询需要失效的领料通知单
     * @param days
     * @return
     */
    public List<PickNotice> selectAbateList(int days) {
        return sqlSession.selectList("PickNotice.selectAbateList",days);
    }
}
