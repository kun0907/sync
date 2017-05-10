package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Contractor;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.ContractorDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * 承包商管理
 * @author wangqian
 *
 */
@Service
public class ContractorService extends BaseService<Contractor> {
	
	@Autowired
	private ContractorDao contractorDao;
	
	@Autowired
	private OrganizationService organizationService;
	
	/**
	 * 保存承包商信息
	 * @param contractor
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Contractor contractor,User user) {
		this.setDefault(contractor,user);
		this.validate(contractor);
		if(StringUtils.isEmpty(contractor.getContractorId())){
			if(null == contractor.getOrganization() || StringUtils.isEmpty(contractor.getOrganization().getOrgId())){
				organizationService.saveOrg(contractor.getOrganization(),user,false);
			}
			contractor.setContractorId(UUIDGenerator.getUUID());
			this.insert(contractor);
		}else{
			organizationService.update(contractor.getOrganization());
			this.update(contractor);
		}
		organizationService.saveOrgAndType(contractor.getOrganization());
		organizationService.saveOrgTypeAndWBS(contractor.getOrganization());
	}
	/**
	 * 设置默认值
	 * @param contractor
	 */
	private void setDefault(Contractor contractor,User user){
		if(null == contractor.getCreateTime()){
			contractor.setCreateTime(new Date());
		}
		if(StringUtils.isEmpty(contractor.getCreateUserId())){
			contractor.setCreateUserId(user.getUserId());
		}
		if(StringUtils.isEmpty(contractor.getCreateUserName())){
			if(null == user.getEmployee()){
				contractor.setCreateUserName(user.getUserName());
			}else{
				contractor.setCreateUserName(user.getEmployee().getEmpName());
			}
		}
	}
	
	/**
	 * 后台校验
	 * @param contractor
	 */
	private void validate(Contractor contractor){
		/*if(StringUtils.isEmpty(contractor.getLinkMan())){
			throw new BusinessException("联系人不能为空");
		}
		if(StringUtils.isEmpty(contractor.getLinkPhone())){
			throw new BusinessException("联系人电话不能为空");
		}
		if(StringUtils.isEmpty(contractor.getEmail())){
			throw new BusinessException("邮箱不能为空");
		}*/
	}
	@Override
	public BaseDao<Contractor> getDao() {
		// TODO Auto-generated method stub
		return contractorDao;
	}
	


}
