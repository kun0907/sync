/**
 *
 */
package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.bean.DozerMapperSingleton;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Storagelocation;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.StoragelocationDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @param
 * @Title: StoragelocationService
 * @Description:
 * @author:YUZH
 * @data 2017年1月16日
 */
@Service
@Transactional
public class StoragelocationService extends BaseService<Storagelocation> {
    @Autowired
    private StoragelocationDao storagelocationDao;

    public BaseDao<Storagelocation> getDao() {
        return storagelocationDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByWareId(String warehouse_id) {
        storagelocationDao.deleteByWareId(warehouse_id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByResId(String reservoirarea_id) {
        storagelocationDao.deleteByResId(reservoirarea_id);
    }

    //储存或更新一个实体
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Storagelocation storagelocation, User user) {
        this.setDefault(storagelocation, user);
        this.vailidate(storagelocation);
        if (StringUtils.isEmpty(storagelocation.getStoragelocationId())) {
            storagelocation.setStoragelocationId(UUIDGenerator.getUUID());
            storagelocationDao.insert(storagelocation);
        } else {
            storagelocationDao.update(storagelocation);
        }
    }

    /**
     * 赋默认值
     *
     * @param storagelocation
     */
    private void setDefault(Storagelocation storagelocation, User user) {
        if (StringUtils.isEmpty(storagelocation.getStoragelocationId())) {
            storagelocation.setCreateTime(new Date());
            storagelocation.setCreateUserId(user.getUserId());
            if (null == user.getEmployee()) {
                storagelocation.setCreateUserName(user.getUserName());
            } else {
                storagelocation.setCreateUserName(user.getEmployee().getEmpName());
            }
        }
    }

    //校验
    public void vailidate(Storagelocation storagelocation) {
        if (StringUtils.isEmpty(storagelocation.getStoragelocationCode())) {
            throw new BusinessException("储位编码不能为空");
        }
        if (StringUtils.isEmpty(storagelocation.getStoragelocationName())) {
            throw new BusinessException("储位名称不能为空");
        }
        if (StringUtils.isEmpty(storagelocation.getStoragelocationType())) {
            throw new BusinessException("储位类型不能为空");
        }
    }

    //拼接code
    public String pingjieCode(String str1, String str2, String str3) {
        StringBuilder str = new StringBuilder();
        str.append(str1);
        str.append("_");
        if (str2.split("_").length == 2) {
            str.append(str2.split("_")[1]);
        } else {
            str.append(str2);
        }
        str.append("_");
        str.append(str3);
        return str.toString();
    }

    /**
     * 校验Code是否重复
     *
     * @param empNo
     * @return
     */
    public boolean checkStoNo(String stoNo, String stoId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("stoNo", stoNo);
        paramMap.put("stoId", stoId);
        int count = storagelocationDao.checkStoNo(paramMap);
        return count > 0 ? false : true;
    }

    public Storagelocation setDeFault(String code, Storagelocation storagelocation, User currentUser) throws IllegalAccessException, InvocationTargetException {
        Storagelocation storage = new Storagelocation();
        setDefault(storagelocation, currentUser);
        DozerMapperSingleton.map(storagelocation, storage);//拷贝
        storage.setStoragelocationCode(code);
        storage.setStoragelocationId(UUIDGenerator.getUUID());
        return storage;
    }

    //批量保存
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveBatchStoragelocation(List<Storagelocation> list) {
        storagelocationDao.insetList(list);
    }
}
