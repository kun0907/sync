package com.dkd.emms.systemManage.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.OrganizationType;
import com.dkd.emms.systemManage.dao.OrganizationTypeDao;



/**
 * 组织机构Service
 * @author WANGQ
 *
 */
@Service
public class OrganizationTypeService extends BaseService<OrganizationType> {

	@Autowired
	private OrganizationTypeDao organizationTypeDao;

	/**
	 * 保存组织机构类型信息
	 * @param organization
	 */
	public void saveOrgAndType(Organization organization){
		organizationTypeDao.deleteOrgTypeByOrgId(organization.getOrgId());
		List<OrganizationType> organizationTypeList = organization.getOrgTypeList();
		for(OrganizationType organizationType : organizationTypeList){
			organizationType.setIsAllwbs("0");
			organizationType.setOrgId(organization.getOrgId());
		}
		if(organizationTypeList.size()>0){
			organizationTypeDao.insetList(organizationTypeList);
		}
	}
	/**
	 * 根据组织机构id查询组织机构类型
	 * @param orgId
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<OrganizationType> selectOrgType(String orgId) {
		// TODO Auto-generated method stub
		return organizationTypeDao.selectOrgType(orgId);
	}
	
	
	@Override
	public BaseDao<OrganizationType> getDao() {
		// TODO Auto-generated method stub
		return organizationTypeDao;
	}
	
}
