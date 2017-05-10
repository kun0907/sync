/**
 * 
 */
package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.WareHouseOrg;
import com.dkd.emms.systemManage.dao.WareHouseOrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @Title: WareHouseService
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
@Service
@Transactional
public class WareHouseOrgService extends BaseService<WareHouseOrg> {

    @Autowired
    private WareHouseOrgDao wareHouseOrgDao;

    /**
     * 保存组织机构类型信息
     * @param organization
     */
    public void saveOrgAndType(List<WareHouseOrg> wareHouseOrgList,String wareHouseId){
        wareHouseOrgDao.deleteOrgBywareHouseId(wareHouseId);
        if(wareHouseOrgList.size()>0){
            wareHouseOrgDao.insetList(wareHouseOrgList);
        }
    }

    @Override
    public BaseDao<WareHouseOrg> getDao() {
        // TODO Auto-generated method stub
        return wareHouseOrgDao;
    }
}
