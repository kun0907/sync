/**
 * 
 */
package com.dkd.emms.systemManage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import com.dkd.emms.systemManage.bo.MaterialsTable;
import com.dkd.emms.systemManage.dao.MaterialsTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;

import com.dkd.emms.systemManage.bo.Organization;


	/**
 * @Title: MaterialsTableService
 * @Description:
 * @param 
 * @author:YUZH 
 * @data 2017年1月24日
 */
@Service
@Transactional
public class MaterialsTableService extends BaseService<MaterialsTable>{
	
	@Autowired
	private MaterialsTableDao materialsTableDao;
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private DrawingNumberService drawingNumberService;

	@Autowired
	private DrawingDetailedService drawingDetailedService;

	@Autowired
	private ExcelParsingService excelParsingService;

	@Autowired
	private MaterialsTableDesignCodeService MtDcService;

	@Autowired
	private DesignCodeService designCodeService;

	public BaseDao<MaterialsTable> getDao() {
		return materialsTableDao;
	}
	
	//返回符合当前用户数据权限的设计院列表
	public List<Organization> selectOrgByUser(){
		List<Organization> designOrgs = organizationService.selectOrgByType("design");
		return designOrgs;
	}
	//根据ID返回组织实体
	public Organization selectOrgById(String designOrgId){
		Organization org = organizationService.selectByPk(designOrgId);
		return org;
	}
}
