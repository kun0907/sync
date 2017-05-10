package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.MaterialsAttachement;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MaterialsAttachementDao extends BaseDao<MaterialsAttachement> {

    /**
     * 插入组织机构类型信息
     * @param materialsAttachementList
     */
    public void insetList(List<MaterialsAttachement> materialsAttachementList){
        sqlSession.insert("MaterialsAttachement.insertList", materialsAttachementList);
    }

}
