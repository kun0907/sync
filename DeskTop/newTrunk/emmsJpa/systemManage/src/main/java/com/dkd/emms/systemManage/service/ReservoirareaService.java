/**
 * 
 */
package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Reservoirarea;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.ReservoirareaDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
* @Title: ReservoirareaService
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
@Service
@Transactional
public class ReservoirareaService extends BaseService<Reservoirarea>{
@Autowired
private ReservoirareaDao reservoirareaDao;

public BaseDao<Reservoirarea> getDao() {
    return reservoirareaDao;
}
@Transactional(propagation = Propagation.REQUIRED)
public void  deleteByWareId(String warehouse_id){
    reservoirareaDao.deleteByWareId(warehouse_id);
}
//储存或更新一个实体
@Transactional(propagation = Propagation.REQUIRED)
public void save(Reservoirarea reservoirarea,User user){
    this.setDefault(reservoirarea,user);
    this.vailidate(reservoirarea);
    if(StringUtils.isEmpty(reservoirarea.getReservoirareaId())){
        reservoirarea.setReservoirareaId(UUIDGenerator.getUUID());
        reservoirareaDao.insert(reservoirarea);
    }else{
        reservoirareaDao.update(reservoirarea);
    }
}
/**
 * 赋默认值
 * @param reservoirarea
 */
private void setDefault(Reservoirarea reservoirarea,User user){
    if(StringUtils.isEmpty(reservoirarea.getReservoirareaId())){
        reservoirarea.setCreateTime(new Date());
        reservoirarea.setCreateUserId(user.getUserId());
        if(null == user.getEmployee()){
            reservoirarea.setCreateUserName(user.getUserName());
        }else{
            reservoirarea.setCreateUserName(user.getEmployee().getEmpName());
        }
    }
}
//校验
public void vailidate(Reservoirarea reservoirarea){
    if(StringUtils.isEmpty(reservoirarea.getReservoirareaCode()) ){
        throw new BusinessException("库区编码不能为空");
    }
    if(StringUtils.isEmpty(reservoirarea.getReservoirareaName())){
        throw new BusinessException("库区名称不能为空");
    }
    if(StringUtils.isEmpty(reservoirarea.getReservoirareaType())){
        throw new BusinessException("库区类型不能为空");
    }
}
/**
 * 校验Code是否重复
 * @param empNo
 * @return
 */
public boolean checkStoNo(String stoNo,String stoId){
    Map<String,Object> paramMap = new HashMap<String,Object>();
    paramMap.put("stoNo", stoNo);
    paramMap.put("stoId", stoId);
    int count=reservoirareaDao.checkStoNo(paramMap);
    return count>0?false:true;
}
}
