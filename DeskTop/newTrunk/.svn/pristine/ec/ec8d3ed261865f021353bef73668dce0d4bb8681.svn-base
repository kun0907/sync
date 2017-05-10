package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 人员信息表
 *
 * @author WANGQ
 */
public class Employee extends BaseEntity {
    /**
     * 人员ID
     */
    private String empId;
    /**
     * 人员No
     */
    private String empNo;
    /**
     * 人员姓名
     */
    private String empName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期（年月日）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 状态（数据字典：在岗、离职）
     */
    private String empState;
    /**
     * 手机号码
     */
    private String cellPhone;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 所属组织id
     */
    private Organization organization;
    /**
     * 所属根组织id
     */
    private Organization rootOrganization;
    /**
     * 祖先组织机构id合集
     */
    private String orgSeq;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmpState() {
        return empState;
    }

    public void setEmpState(String empState) {
        this.empState = empState;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getRootOrganization() {
        return rootOrganization;
    }

    public void setRootOrganization(Organization rootOrganization) {
        this.rootOrganization = rootOrganization;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }
}
