package com.dkd.emms.systemManage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dkd.emms.core.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.RoleResource;
import com.dkd.emms.systemManage.dao.RoleResourceDao;





@Service
@Transactional
public class RoleResourceService extends BaseService<RoleResource> {

	@Autowired
	private RoleResourceDao roleResourceDao;

	public BaseDao<RoleResource> getDao() {
		return roleResourceDao;
	}
	
	//保存角色权限表
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveRoleResource(String roleId, String resourceIds){
//		JSONArray jsonArr = JSONArray.fromObject(resourceIds);		
		List<RoleResource> resultRoleResourceList = new ArrayList<RoleResource>();
//		Iterator<JSONObject> it = jsonArr.iterator();
		List<Map<String,Object>> roleResourceList = JsonUtil.json2List(resourceIds, null);
		for(Map<String,Object> map :roleResourceList){
			RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(map.get("resourceId").toString());
			if(null != map.get("roleIds") && (!StringUtils.equals(map.get("roleIds").toString(), "undefined")) && (!StringUtils.equals(map.get("roleIds").toString(), ""))){
				String []roleIdArray = map.get("roleIds").toString().split(",");
				for(String pRoleId:roleIdArray){
					roleResource.setParentRoleId(pRoleId);
					resultRoleResourceList.add(roleResource);
				}
			}else{
				roleResource.setParentRoleId(null);
				resultRoleResourceList.add(roleResource);
			}
		}
		roleResourceDao.delete(roleId);
		if(resultRoleResourceList.size()>0){
			roleResourceDao.batchInsert(resultRoleResourceList);
		}
	}
	/**
	 * 根据给定的roles信息查询角色权限信息
	 * @param roleIds
	 * @return
	 */
	public List<RoleResource> selectRoleResourceByRoleIds(String roleIds) {
		// TODO Auto-generated method stub
		List<String> roleList = Arrays.asList(roleIds.split(","));
		return roleResourceDao.selectRoleResourceByRoleIds(roleList) ;
	}

	public int findRoleResourceByResource(String resourceId) {
		return roleResourceDao.findRoleResourceByResource(resourceId);
	}

	/**
	 * 根据角色类型查询角色权限信息
	 * @return
	 */
	public List<RoleResource> findRoleResourceByRoleType(String roleType) {
		return roleResourceDao.findRoleResourceByRoleType(roleType);
	}
}
