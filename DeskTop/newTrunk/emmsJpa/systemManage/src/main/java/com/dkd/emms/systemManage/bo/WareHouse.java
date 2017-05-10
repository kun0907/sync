/**
 * 
 */
package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.Tree;

import java.math.BigDecimal;


/**
* @Title: WareHouse
* @Description:
* @param
* @author:YUZH
* @data 2017年1月14日
*/
public class WareHouse extends Tree{
private String warehouseId;//仓库ID
private String warehouseCode;//仓库编码
private String warehouseName;//仓库名称
private String country;//国家
private String city;//市
private String area;//区
private BigDecimal acreage;//面积
private String warehouseType;//仓库类型
private String warehouseState;//状态
private String contacts;//联系人
private String phone;//电话
private String fax;//传真
private String remark;//备注
private String orgId;//机构Id
public String getWarehouseId() {
    return warehouseId;
}
public String getOrgId() {
    return orgId;
}
public void setOrgId(String orgId) {
    this.orgId = orgId;
}
public void setWarehouseId(String warehouseId) {
    this.warehouseId = warehouseId;
}
public String getWarehouseCode() {
    return warehouseCode;
}
public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
}
public String getWarehouseName() {
    return warehouseName;
}
public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
}
public String getCountry() {
    return country;
}
public void setCountry(String country) {
    this.country = country;
}
public String getCity() {
    return city;
}
public void setCity(String city) {
    this.city = city;
}
public String getArea() {
    return area;
}
public void setArea(String area) {
    this.area = area;
}
public BigDecimal getAcreage() {
    return acreage;
}
public void setAcreage(BigDecimal acreage) {
    this.acreage = acreage;
}

public String getWarehouseType() {
    return warehouseType;
}
public void setWarehouseType(String warehouseType) {
    this.warehouseType = warehouseType;
}

public String getWarehouseState() {
    return warehouseState;
}
public void setWarehouseState(String warehouseState) {
    this.warehouseState = warehouseState;
}
public String getContacts() {
    return contacts;
}
public void setContacts(String contacts) {
    this.contacts = contacts;
}
public String getPhone() {
    return phone;
}
public void setPhone(String phone) {
    this.phone = phone;
}
public String getFax() {
    return fax;
}
public void setFax(String fax) {
    this.fax = fax;
}
public String getRemark() {
    return remark;
}
public void setRemark(String remark) {
    this.remark = remark;
}
}
