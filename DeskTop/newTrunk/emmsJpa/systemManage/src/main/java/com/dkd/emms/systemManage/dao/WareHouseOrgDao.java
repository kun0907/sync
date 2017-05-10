/**
 * 
 */
package com.dkd.emms.systemManage.dao;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.WareHouseOrg;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
* @Title: WareHouseDao
* @Description:
* @param
* @author:YUZH
* @data 2017年1月14日
*/
@Repository
public class WareHouseOrgDao extends BaseDao<WareHouseOrg>{
/**
 * 插入仓库机构信息
 * @param organizationTypeList
 */
public void insetList(List<WareHouseOrg> warehouseOrgList){
    sqlSession.insert("WarehouseOrg.insertList", warehouseOrgList);
}
/**
 * 根据仓库id删除仓库机构信息
 * @param wareHouseId
 */
public void deleteOrgBywareHouseId(String wareHouseId) {
    // TODO Auto-generated method stub
    sqlSession.insert("WarehouseOrg.deleteOrgBywareHouseId", wareHouseId);
}
}
