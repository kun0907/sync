package com.dkd.emms.systemManage.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.dkd.emms.core.entity.Tree;


/**
 * 组织机构实体类
 *
 * @author WANGQ
 */

public class Organization extends Tree {
    /**
     * 组织机构id
     */
    private String orgId;
    /**
     * 组织机构Code
     */
    private String orgCode;
    /**
     * 组织机构Name
     */
    private String orgName;
    /**
     * 组织机构序号
     */
    private String orgOrder;
    /**
     * 是否删除
     */
    private String isDel;
    /**
     * 所属根组织Id
     */
    private String rootOrgId;

    /**
     * 是否有效
     */
    private String isValidate;

    /**
     * 每个节点到根节点的路径
     */
    private String orgSeq;
    /**
     * 子组织机构列表
     */
    private List<Organization> childOrgList = new ArrayList<Organization>();
    /**
     * 机构类型WBS关联信息
     *
     * @return
     */
    private List<OrganizationTypeWBS> orgTypeWBSList = new ArrayList<OrganizationTypeWBS>();
    /**
     * 机构类型List
     *
     * @return
     */
    private List<OrganizationType> orgTypeList = new ArrayList<OrganizationType>();




    //辅助信息
    /**
     * 组织类型(使用,拼接)
     */
    private String orgType;
    /**
     * 组织类型(数组)
     */
    private String[] orgTypeArray = new String[0];
    /**
     * 组织类型Name
     */
    private String orgTypeName;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(String orgOrder) {
        this.orgOrder = orgOrder;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(String rootOrgId) {
        this.rootOrgId = rootOrgId;
    }

    public List<Organization> getChildOrgList() {
        return childOrgList;
    }

    public void setChildOrgList(List<Organization> childOrgList) {
        this.childOrgList = childOrgList;
    }

    public String getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(String isValidate) {
        this.isValidate = isValidate;
    }

    public String getOrgSeq() {
        return orgSeq;
    }

    public void setOrgSeq(String orgSeq) {
        this.orgSeq = orgSeq;
    }


    public List<OrganizationTypeWBS> getOrgTypeWBSList() {
        return orgTypeWBSList;
    }

    public void setOrgTypeWBSList(List<OrganizationTypeWBS> orgTypeWBSList) {
        this.orgTypeWBSList = orgTypeWBSList;
    }
    public List<OrganizationType> getOrgTypeList() {
        return orgTypeList;
    }

    public void setOrgTypeList(List<OrganizationType> orgTypeList) {
        this.orgTypeList = orgTypeList;
    }





   //辅助字段信息
    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String[] getOrgTypeArray() {
        if (StringUtils.isEmpty(this.orgType)) {
            return orgTypeArray;
        } else {
            return this.orgType.split(",");
        }
    }

    public void setOrgTypeArray(String[] orgTypeArray) {
        for (String orgType : orgTypeArray) {
            if (StringUtils.isEmpty(this.orgType)) {
                this.orgType = orgType;
            } else {
                this.orgType += "," + orgType;
            }
        }
    }

    public String getOrgType() {
        for(OrganizationType organizationType:this.getOrgTypeList()){
            if(StringUtils.isEmpty(orgType)){
                orgType = organizationType.getOrgType();
            }else{
                orgType += ","+organizationType.getOrgType();
            }

        }
        return orgType;
    }
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }


    /**
     * 根据组织机构获取所有的Wbs
     *
     * @return
     */
    public List<Project> getAllWbsForOrg() {
        List<Project> projectList = new ArrayList<Project>();
        Set<String> wbsSet = new HashSet<String>();
        if (orgTypeWBSList.size() > 0) {
            for (OrganizationTypeWBS orgTypeWbs : orgTypeWBSList) {
                if (null != orgTypeWbs.getProject()) {
                    if (wbsSet.add(orgTypeWbs.getProject().getId())) {
                        projectList.add(orgTypeWbs.getProject());
                    }
                }
            }
        }
        return projectList;
    }





    //仓库暂时不需要配置组织机构，注掉内容
     /**
     * 机构类型WBS关联信息
     *
     * @return
     */
    /*private List<WareHouseOrg> wareHouseOrgList = new ArrayList<WareHouseOrg>();

    public List<WareHouseOrg> getWareHouseOrgList() {
        return wareHouseOrgList;
    }

    public void setWareHouseOrgList(List<WareHouseOrg> wareHouseOrgList) {
        this.wareHouseOrgList = wareHouseOrgList;
    }
    public List<WareHouse> getAllWareHouseForOrg() {
        List<WareHouse> wareHouseList = new ArrayList<WareHouse>();
        Set<String> wareHouseSet = new HashSet<String>();
        if (wareHouseList.size() > 0) {
            for (WareHouseOrg wareHouseOrg : wareHouseOrgList) {
                if (null != wareHouseOrg.getWareHouse()) {
                    if (wareHouseSet.add(wareHouseOrg.getWareHouse().getWarehouseId())) {
                        wareHouseList.add(wareHouseOrg.getWareHouse());
                    }
                }
            }
        }
        return wareHouseList;
    }*/
}
