package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.InspectPicFile;
import com.dkd.emms.systemManage.bo.QualityInspectDetail;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YINXP on 2017/3/10.
 */
@Repository
public class InspectPicFileDao extends BaseDao<InspectPicFile> {

    /**
     * 批量插入
     */
    public void insertListP(List<InspectPicFile> list){
        sqlSession.insert("InspectPicFile.insertListP", list);
    }

    /**
     * 根据质检单id查询质检单下的图片信息
     * @param materiaInspectId
     * @return
     */
    public List<InspectPicFile> queryMaterialsData(String materiaInspectId){
        return sqlSession.selectList("InspectPicFile.selectByPk",materiaInspectId);
    }

    /**
     * 根据图片id查询图片信息
     * @param inspectPicFileId
     * @return
     */
    public InspectPicFile selectByPkPicId(String inspectPicFileId){
        return sqlSession.selectOne("InspectPicFile.selectByPkPicId", inspectPicFileId);
    }

}
