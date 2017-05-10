package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.OrganizationType;
import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.PickNoticeDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 领料通知单
 * @author wangqian
 *
 */
@Repository
public class PickNoticeDetailDao extends BaseDao<PickNoticeDetail> {
    /**
     * 根据领料通知删除明细信息
     * @param pickNotice
     */
    public void deleteDetailByPickId(PickNotice pickNotice){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pickNotice",pickNotice);
        sqlSession.delete("PickNoticeDetail.deleteDetailByPickId", paramMap);
    }

    /**
     * 批量插入领料通知明细信息
     * @param pickNoticeDetailList
     */
    public void insertList(List<PickNoticeDetail> pickNoticeDetailList){
        sqlSession.insert("PickNoticeDetail.insertList", pickNoticeDetailList);
    }

    /**
     * 根据查询条件查询领料明细
     * @param map
     * @return
     */
    public List<PickNoticeDetail> loadPickDetailListData(Map<String, Object> map){
        return sqlSession.selectList("PickNoticeDetail.loadPickDetailListData",map);
    }
}
