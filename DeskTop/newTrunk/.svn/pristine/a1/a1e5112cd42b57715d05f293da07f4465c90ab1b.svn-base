/**
 * 
 */
package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.bo.WareHouse;
import com.dkd.emms.systemManage.bo.WareHouseOrg;
import com.dkd.emms.systemManage.dao.WareHouseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
* @Title: WareHouseService
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
@Service
@Transactional
public class WareHouseService extends BaseService<WareHouse> {
@Autowired
private WareHouseOrgService wareHouseOrgService;
@Autowired
private WareHouseDao wareHouseDao;

public BaseDao<WareHouse> getDao() {
    return wareHouseDao;
}
//储存或更新一个实体
@Transactional(propagation = Propagation.REQUIRED)
public void save(WareHouse wareHouse,User user){
    this.setDefault(wareHouse,user);
    this.vailidate(wareHouse);
    if(StringUtils.isEmpty(wareHouse.getWarehouseId())){
        wareHouse.setWarehouseId(UUIDGenerator.getUUID());
        addOrgToWarehouse(wareHouse);
        wareHouseDao.insert(wareHouse);
    }else{
        addOrgToWarehouse(wareHouse);
        wareHouseDao.update(wareHouse);
    }
}
//仓库添加组织机构
@Transactional(propagation = Propagation.REQUIRED)
public void addOrgToWarehouse(WareHouse wareHouse){
    if(null != wareHouse.getOrgId()){
        String [] orgIds = wareHouse.getOrgId().split(",");
        List<WareHouseOrg> list = new ArrayList<WareHouseOrg>();
        for(String orgId :orgIds){
            WareHouseOrg wareHouseOrg = new WareHouseOrg();
            Organization organization = new Organization();
            organization.setOrgId(orgId);
            wareHouseOrg.setOrganization(organization);
            wareHouseOrg.setWareHouse(wareHouse);
            list.add(wareHouseOrg);
        }
        wareHouseOrgService.saveOrgAndType(list, wareHouse.getWarehouseId());
    }
}
/**
 * 赋默认值
 * @param wareHouse
 */
private void setDefault(WareHouse wareHouse,User user){
    if(StringUtils.isEmpty(wareHouse.getWarehouseId())){
        wareHouse.setCreateTime(new Date());
        wareHouse.setCreateUserId(user.getUserId());
        if(null == user.getEmployee()){
            wareHouse.setCreateUserName(user.getUserName());
        }else{
            wareHouse.setCreateUserName(user.getEmployee().getEmpName());
        }
    }
}
//校验
public void vailidate(WareHouse wareHouse){
    if(StringUtils.isEmpty(wareHouse.getWarehouseCode()) ){
        throw new BusinessException("仓库编码不能为空");
    }
    if(StringUtils.isEmpty(wareHouse.getWarehouseName())){
        throw new BusinessException("仓库名称不能为空");
    }
    if(StringUtils.isEmpty(wareHouse.getWarehouseType())){
        throw new BusinessException("仓库类型不能为空");
    }
}
/**
 * 校验Code是否重复
 * @return
 */
public boolean checkStoNo(String stoNo,String stoId){
    Map<String,Object> paramMap = new HashMap<String,Object>();
    paramMap.put("stoNo", stoNo);
    paramMap.put("stoId", stoId);
    int count=wareHouseDao.checkStoNo(paramMap);
    return count>0?false:true;
}
/**
 * 查询公共仓库
 * @return
 */
public List<WareHouse> selectCommom(){
    return wareHouseDao.selectCommon();
}
}
