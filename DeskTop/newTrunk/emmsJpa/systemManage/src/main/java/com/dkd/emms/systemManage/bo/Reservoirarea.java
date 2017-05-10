/**
 * 
 */
package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.Tree;

import java.math.BigDecimal;


/**
* @Title: Reservoirarea
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
public class Reservoirarea extends Tree{
private String reservoirareaId;//库区ID
private String warehouseId;//仓库ID
private String reservoirareaCode;//区域编码
private String reservoirareaName;//区域名称
private String reservoirareaType;//区域类型
private String reservoirareaState;//状态
private BigDecimal acreage;//面积
private String contacts;//联系人
private String phone;//电话
private String fax;//传真
private String remark;//备注


public String getReservoirareaId() {
    return reservoirareaId;
}
public void setReservoirareaId(String reservoirareaId) {
    this.reservoirareaId = reservoirareaId;
}
public String getWarehouseId() {
    return warehouseId;
}
public void setWarehouseId(String warehouseId) {
    this.warehouseId = warehouseId;
}
public String getReservoirareaCode() {
    return reservoirareaCode;
}
public void setReservoirareaCode(String reservoirareaCode) {
    this.reservoirareaCode = reservoirareaCode;
}
public String getReservoirareaName() {
    return reservoirareaName;
}
public void setReservoirareaName(String reservoirareaName) {
    this.reservoirareaName = reservoirareaName;
}
public String getReservoirareaType() {
    return reservoirareaType;
}
public void setReservoirareaType(String reservoirareaType) {
    this.reservoirareaType = reservoirareaType;
}

public String getReservoirareaState() {
    return reservoirareaState;
}
public void setReservoirareaState(String reservoirareaState) {
    this.reservoirareaState = reservoirareaState;
}
public BigDecimal getAcreage() {
    return acreage;
}
public void setAcreage(BigDecimal acreage) {
    this.acreage = acreage;
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
