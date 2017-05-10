package com.dkd.emms.systemManage.bo;

import java.io.Serializable;
import java.util.List;


/**
 * 系统用户表
 *
 * @author wangq
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户编码(登录名)
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否有效
     */
    private String isValidate;
    /**
     * 是否删除
     */
    private String isDel;

    /**
     * 是否是超级管理员
     */
    private String isSysadmin;

    /**
     * 是否是超级管理员
     */
    private String isOrgadmin;
    /**
     * 人员信息表
     */
    private Employee employee;
    /**
     * 用户所属主项目
     */
    private Project mainProject;
    /**
     * 用户角色List
     */
    private List<UserRole> userRoleList;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(String isValidate) {
        this.isValidate = isValidate;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getIsSysadmin() {
        return isSysadmin;
    }

    public void setIsSysadmin(String isSysadmin) {
        this.isSysadmin = isSysadmin;
    }

    public String getIsOrgadmin() {
        return isOrgadmin;
    }

    public void setIsOrgadmin(String isOrgadmin) {
        this.isOrgadmin = isOrgadmin;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getMainProject() {
        return mainProject;
    }

    public void setMainProject(Project mainProject) {
        this.mainProject = mainProject;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户下角色的全部资源
     * @return
     @Override public Collection<GrantedAuthority> getAuthorities() {
     Set<GrantedAuthority> autthorities = new HashSet<GrantedAuthority>();
     Set<String> resoueceSet = new HashSet<String>();
     for (UserRole userRole : this.userRoleList) {//循环用户的角色
     Resource resources = userRole.getResource();//当前角色的全部资源
     if(null != resources){
     if(resoueceSet.add(resources.getResourceId())){
     autthorities.add(new GrantedAuthorityImpl(resources.getResourceCode()));//该用户具有的资源编码
     System.out.println("该用户具备如下权限**********"+resources.getResourceCode());
     }
     }
     }
     return autthorities;
     } **/
}
