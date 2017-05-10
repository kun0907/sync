package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Materials;
import com.dkd.emms.systemManage.bo.MaterialsUnit;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MaterialsUnitDao extends BaseDao<MaterialsUnit> {

    //根据系统物资ID查询单位换算列表
    public List<MaterialsUnit> selectListByMaterialsId(String materialsId) {
        return sqlSession.selectList("MaterialsUnit.selectListByMaterialsId", materialsId);
    }

    public void insertList(List<MaterialsUnit> unitList) {
        sqlSession.insert("MaterialsUnit.insertList", unitList);
    }

    public void deleteByMaterialsId(String materialsId) {
        sqlSession.update("MaterialsUnit.deleteByMaterialsId", materialsId);
    }


}
