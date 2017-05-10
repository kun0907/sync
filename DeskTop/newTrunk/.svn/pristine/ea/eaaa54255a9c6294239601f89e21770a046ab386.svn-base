/**
 * 
 */
package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.WareHouse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
* @Title: WareHouseDao
* @Description:
* @param
* @author:YUZH
* @data 2017年1月14日
*/
@Repository
public class WareHouseDao extends BaseDao<WareHouse>{
/**
 * 校验Code是否重复
 * @param empNo
 * @return
 */
public int checkStoNo(Map<String,Object> paramMap){
    return sqlSession.selectOne("WareHouse.checkStoNo", paramMap);
}
//查询公共仓库
public List<WareHouse> selectCommon() {
    return sqlSession.selectList("WareHouse.selectAllCommon");
}
}
