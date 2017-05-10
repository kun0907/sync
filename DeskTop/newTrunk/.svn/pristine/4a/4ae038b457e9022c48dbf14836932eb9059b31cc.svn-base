package com.dkd.emms.systemManage.service;



import java.util.*;

import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.systemManage.bo.OrganizationType;
import com.dkd.emms.systemManage.bo.OrganizationTypeWBS;
import com.dkd.emms.systemManage.dao.OrganizationTypeWBSDao;




/**
 * 组织机构类型配置WBSService
 * @author WANGQ
 *
 */
@Service
public class OrganizationTypeWBSService extends BaseService<OrganizationTypeWBS> {

	@Autowired
	private OrganizationTypeWBSDao organizationTypeWBSDao;
	@Override
	public BaseDao<OrganizationTypeWBS> getDao() {
		// TODO Auto-generated method stub
		return organizationTypeWBSDao;
	}
	/**
	 * 保存组织机构类型WBs关系表
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrgTypeAndWBS(Organization organization) {
		organizationTypeWBSDao.deleteOrgTypeWBSByOrg(organization.getOrgId());
		// TODO Auto-generated method stub
		/*List<String>  wbsIdList= Arrays.asList(wbsIds.split(","));*/
		List<OrganizationTypeWBS> orgTypeWBSList =organization.getOrgTypeWBSList();

		/*for(String wbsId:wbsIdList){
			OrganizationType orgType = new OrganizationType();
			Project project = new Project();
			OrganizationTypeWBS organizationTypeWBS = new OrganizationTypeWBS();
			organizationTypeWBS.setOrgId(orgId);
			orgType.setOrgTypeId(orgTypeId);
			organizationTypeWBS.setOrganizationType(orgType);
			project.setProjectId(wbsId);
			organizationTypeWBS.setProject(project);
			orgTypeWBSList.add(organizationTypeWBS);
		}*/
		for(OrganizationTypeWBS organizationTypeWBS:orgTypeWBSList){
			organizationTypeWBS.setOrgId(organization.getOrgId());
		}
		if(orgTypeWBSList.size()>0){
			organizationTypeWBSDao.insertList(orgTypeWBSList);
		}
	}
	/**
	 * 根据组织机构id和机构类型查询WBS
	 * @param orgTypeId
	 * @param orgId
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Map<String,Object>> selectOrgTypeWBSByType(String orgTypeId, String orgId) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("orgTypeId", orgTypeId);
		paramMap.put("orgId", orgId);
		// TODO Auto-generated method stub
		return organizationTypeWBSDao.selectOrgTypeWBSByType(paramMap);
	}

	/**
	 * 根据组织机构id查询WBS
	 * @param orgId
	 * @return
	 */
	public List<Project> 	selectOrgTypeWBSByOrg( String orgId,String orgTypeCode) {
		List<Project> projectList = new ArrayList<Project>();
		List<Map<String,Object>> organizationTypeWBSList = organizationTypeWBSDao.selectOrgTypeWBSByOrg(orgId,orgTypeCode);
		Set<String> wbsSet = new HashSet<String>();
		if (organizationTypeWBSList.size() > 0) {
			for (Map<String,Object> map : organizationTypeWBSList) {
				if (null != map.get("PROJECT_ID")) {
					Project project = new Project();
					if (wbsSet.add(map.get("PROJECT_ID").toString())) {
						project.setProjectId(map.get("PROJECT_ID").toString());
						project.setProjectName(map.get("PROJECT_NAME").toString());
						project.setProjectCode(map.get("PROJECT_CODE").toString());
						projectList.add(project);
					}
				}
			}
		}
		return projectList;
	}
}
