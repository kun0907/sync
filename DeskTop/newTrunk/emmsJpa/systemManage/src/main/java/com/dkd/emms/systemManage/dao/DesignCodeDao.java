package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Design;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class DesignCodeDao extends BaseDao<Design> {
    public List<Design> selectByMatching(String isMatching) {
        return sqlSession.selectList("Design.selectByMatching", isMatching);
    }
    /**
     * 批量插入设计院
     */
    public void insetList(List<Design> list){
        sqlSession.insert("Design.insertList", list);
    }
    /**
     * 校验是否重复
     * @return
     */
    public List<Design> checkDesign(Map<String,Object> paramMap){
        return sqlSession.selectList("Design.checkDesign", paramMap);
    }
}
