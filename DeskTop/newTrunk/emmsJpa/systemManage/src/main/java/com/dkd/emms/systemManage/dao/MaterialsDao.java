package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Materials;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class MaterialsDao extends BaseDao<Materials> {

    /**
     * 根据用户名校验用户名是否重复
     * @param materialsCode
     * @return
     */
    public int checkMaterials(String materialsCode){
        return sqlSession.selectOne("Materials.checkMaterials", materialsCode);
    }

    /**
     * 批量插入系统物料
     * @param materialsList
     * @return
     */
    public void insertList(List<Materials> materialsList){
        sqlSession.insert("Materials.insertList", materialsList);
    }

    /**
     * 根据物料编码判重校验
     * @param materialsCode
     * @return
     */
    public Materials selectByCode(String materialsCode){
        return sqlSession.selectOne("Materials.selectByCode",materialsCode);
    }

    //根据条件查找部件重复项
    public Materials findRepeatParts(Map<String, Object> condition) {
        return sqlSession.selectOne("Materials.findRepeatParts",condition);
    }

    //根据设计院编码查询已有条目
    public Materials select4design(Map<String, String> condition) {
        return sqlSession.selectOne("Materials.select4design",condition);
    }

    public void markDelete(String materialsId) {
        sqlSession.update("Materials.markDelete", materialsId);
    }
}
