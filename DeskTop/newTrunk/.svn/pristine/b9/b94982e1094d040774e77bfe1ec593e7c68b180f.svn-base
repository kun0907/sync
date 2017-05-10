/**
 * 
 */
package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.DrawingNumber;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
* @Title: DrawingNumberDao
* @Description:
* @param
* @author:YUZH
* @data 2017年1月24日
*/
@Repository
public class DrawingNumberDao extends BaseDao<DrawingNumber>{

    public int countByCode(String drawingNumberCode) {
        Integer count = (Integer)sqlSession.selectOne("DrawingNumber.countByCode", drawingNumberCode);
        return count.intValue();
    }

    public void updateByCode(DrawingNumber dn) {
        sqlSession.update("DrawingNumber.updateByCode", dn);
    }

    public DrawingNumber selectByCode(String drawingNumberCode) {
        return sqlSession.selectOne("DrawingNumber.selectByCode", drawingNumberCode);
    }

    public void insertList(List<DrawingNumber> allDrawingList) {
        sqlSession.insert("DrawingNumber.insertList", allDrawingList);
    }

    public List<DrawingNumber> selectByMTid(String materialsTableId) {
        return sqlSession.selectList("DrawingNumber.selectByMTid", materialsTableId);
    }

    //根据料表id删除图号
    public void deleteByMTid(String materialsTableId) {
        sqlSession.update("DrawingNumber.deleteByMTid", materialsTableId);
    }
}
