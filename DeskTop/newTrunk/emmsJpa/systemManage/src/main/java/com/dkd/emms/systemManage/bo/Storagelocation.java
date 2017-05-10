/**
 * 
 */
package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

import java.math.BigDecimal;


/**
* @Title: Storagelocation
* @Description:
* @param
* @author:YUZH
* @data 2017年1月16日
*/
public class Storagelocation extends BaseEntity{
private String storagelocationId;//储位ID
private String warehouseId;//仓库ID
private String reservoirareaId;//库区ID
private String storagelocationCode;//储位编码
private String storagelocationName;//储位名称
private String storagelocationType;//储位类型
private String state;//状态
private BigDecimal longs;//长
private BigDecimal widths;//宽
private BigDecimal height;//高
private BigDecimal volume;//体积（立方米）限额
private BigDecimal bearing;//承重（吨）限额

public String getStoragelocationId() {
    return storagelocationId;
}
public void setStoragelocationId(String storagelocationId) {
    this.storagelocationId = storagelocationId;
}
public String getWarehouseId() {
    return warehouseId;
}
public void setWarehouseId(String warehouseId) {
    this.warehouseId = warehouseId;
}
public String getReservoirareaId() {
    return reservoirareaId;
}
public void setReservoirareaId(String reservoirareaId) {
    this.reservoirareaId = reservoirareaId;
}
public String getStoragelocationCode() {
    return storagelocationCode;
}
public void setStoragelocationCode(String storagelocationCode) {
    this.storagelocationCode = storagelocationCode;
}
public String getStoragelocationName() {
    return storagelocationName;
}
public void setStoragelocationName(String storagelocationName) {
    this.storagelocationName = storagelocationName;
}
public String getStoragelocationType() {
    return storagelocationType;
}
public void setStoragelocationType(String storagelocationType) {
    this.storagelocationType = storagelocationType;
}
public String getState() {
    return state;
}
public void setState(String state) {
    this.state = state;
}
public BigDecimal getLongs() {
    return longs;
}
public void setLongs(BigDecimal longs) {
    this.longs = longs;
}
public BigDecimal getWidths() {
    return widths;
}
public void setWidths(BigDecimal widths) {
    this.widths = widths;
}
public BigDecimal getHeight() {
    return height;
}
public void setHeight(BigDecimal height) {
    this.height = height;
}
public BigDecimal getVolume() {
    return volume;
}
public void setVolume(BigDecimal volume) {
    this.volume = volume;
}
public BigDecimal getBearing() {
    return bearing;
}
public void setBearing(BigDecimal bearing) {
    this.bearing = bearing;
}

}
