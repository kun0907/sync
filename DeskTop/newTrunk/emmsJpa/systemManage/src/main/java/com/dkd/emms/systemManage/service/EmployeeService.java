package com.dkd.emms.systemManage.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkd.emms.core.Constant;
import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Employee;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.dao.EmployeeDao;



/**
 * 人员信息Service
 * @author WANGQ
 *
 */
@Service
public class EmployeeService extends BaseService<Employee> {
	
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private OrganizationService organizationService;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 根据组织id查询人员信息
	 * @param orgId
	 * @return
	 */
	public List<Employee> getEmployeeByOrg(String orgId){
		return employeeDao.getEmployeeByOrg(orgId);
	}
	/**
	 * 校验Code是否重复
	 * @param empNo
	 * @return
	 */
	public boolean checkEmpNo(String empNo,String empId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("empNo", empNo);
		paramMap.put("empId", empId);
		int count=employeeDao.checkEmpNo(paramMap);
		return count>0?false:true;
	}
	/**
	 * 插入人员信息
	 */
	public Employee insertEmp(Employee employee){
		this.setDefault(employee);
		this.validate(employee);
		if(StringUtils.isEmpty(employee.getEmpId())){
			employee.setEmpId(UUIDGenerator.getUUID());
			employeeDao.insert(employee);
		}else{
			employeeDao.update(employee);
		}
		return employee;
	}
	/**
	 * 设置默认值
	 * @param employee
	 */
	private void setDefault(Employee employee){
		if(StringUtils.isEmpty(employee.getSex())){
			employee.setSex(Constant.EMP_SEX);
		}
		if(null == employee.getBirthday()){
			employee.setBirthday(new Date());
		}
		if(StringUtils.isEmpty(employee.getCellPhone())){
			employee.setCellPhone(" ");
		}
		if(StringUtils.isEmpty(employee.getEmail())){
			employee.setEmail(" ");
		}
		Organization organization = organizationService.selectByPk(employee.getOrganization().getOrgId());
		if(StringUtils.isEmpty(employee.getEmpState())){
			employee.setEmpState(Constant.EMP_WORKING);
		}
		if(null != organization){
			Organization rootOrg = new Organization();
			rootOrg.setOrgId(organization.getRootOrgId());
			employee.setRootOrganization(rootOrg);
			employee.setOrgSeq(organization.getOrgSeq());
		}
	}
	/**
	 * 后台校验
	 * @param employee
	 */
	private void validate(Employee employee){
		/*if(StringUtils.isEmpty(employee.getEmpNo())){
			throw new BusinessException("人员工号不能为空");
		}
		if(StringUtils.isEmpty(employee.getEmpName())){
			throw new BusinessException("人员姓名不能为空");
		}
		if(StringUtils.isEmpty(employee.getSex())){
			throw new BusinessException("性别不能为空");
		}
		if(StringUtils.isEmpty(employee.getBirthday())){
			throw new BusinessException("出生日期不能为空");
		}
		if(StringUtils.isEmpty(employee.getEmpState())){
			throw new BusinessException("状态不能为空");
		}
		if(StringUtils.isEmpty(employee.getCellPhone())){
			throw new BusinessException("手机号码不能为空");
		}
		if(StringUtils.isEmpty(employee.getEmail())){
			throw new BusinessException("电子邮箱不能为空");
		}
		if(null == employee.getOrganization()){
			throw new BusinessException("所属组织不能为空");
		}
		if(null == employee.getRootOrganization()){
			throw new BusinessException("所属根组织不能为空");
		}*/
	}
	
	/**
	 * 禁用用户(人员)
	 */
	public void forbidEmp(Employee employee){
		employee.setEmpState(Constant.EMP_QUIT);
		this.update(employee);
	}
	/**
	 * 启用用户(人员)
	 */
	public void reuseEmp(Employee employee){
		employee.setEmpState(Constant.EMP_WORKING);
		this.update(employee);
	}
	
	
	/**
	 * 根据组织机构查询员工信息(分页查询)
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getEmployeeByOrg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeeByOrg(map);
	}
	/**
	 * 根据组织机构查询员工信息(数量查询)
	 * @param map
	 * @return
	 */
	public int countEmployeeByOrg(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return  employeeDao.countEmployeeByOrg(map);
	}
	@Override
	public BaseDao<Employee> getDao() {
		// TODO Auto-generated method stub
		return employeeDao;
	}
	

}
