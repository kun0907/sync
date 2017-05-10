package com.dkd.emms.systemManage.bo;

import com.dkd.emms.core.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;


public class Materials extends BaseEntity {

    private String materialsId;//系统物资id
    private String materialsCode;//系统物资编码
    private String materialsDescribe;//系统物资总描述
    private String materialsState;//系统物资状态
    private String materialsUnitMain;//主计量单位
    private String materialsUnitSecondary;//辅计量单位
    private BigDecimal conversion;//主副计量单位换算率
    private String tempMark;//临时物资编码标示(0否，1是) 默认0
    private String materialsCategory;//物料类别（设备、物料） 默认物料
    private String wbsCode;//WBS编码（是主设备时，需要记录WBS编码）
    private String isdel;//是否有效(0否，1是) 默认1
    /**
     * 系统物资名称
     */
    private String materialsName;
    /**
     * 附加1
     */
    private String additional1;
    /**
     * 附加2
     */
    private String additional2;
    /**
     * 附加3
     */
    private String additional3;
    /**
     * 附加4
     */
    private String additional4;
    /**
     * 是否被占用(0否，1是)
     */
    private String isTake;
    /**
     * 是否被占用(0否，1是)
     */
    private List<MaterialsUnit> unitList;

    public List<MaterialsUnit> getUnitList() {return unitList;}

    public void setUnitList(List<MaterialsUnit> unitList) {this.unitList = unitList;}

    public String getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(String materialsId) {
        this.materialsId = materialsId;
    }

    public String getMaterialsCode() {
        return materialsCode;
    }

    public void setMaterialsCode(String materialsCode) {
        this.materialsCode = materialsCode;
    }

    public String getMaterialsDescribe() {
        return materialsDescribe;
    }

    public void setMaterialsDescribe(String materialsDescribe) {
        this.materialsDescribe = materialsDescribe;
    }

    public String getMaterialsState() {
        return materialsState;
    }

    public void setMaterialsState(String materialsState) {
        this.materialsState = materialsState;
    }

    public String getMaterialsUnitMain() {
        return materialsUnitMain;
    }

    public void setMaterialsUnitMain(String materialsUnitMain) {
        this.materialsUnitMain = materialsUnitMain;
    }

    public String getMaterialsUnitSecondary() {
        return materialsUnitSecondary;
    }

    public void setMaterialsUnitSecondary(String materialsUnitSecondary) {
        this.materialsUnitSecondary = materialsUnitSecondary;
    }

    public BigDecimal getConversion() {
        return conversion;
    }

    public void setConversion(BigDecimal conversion) {
        this.conversion = conversion;
    }

    public String getTempMark() {
        return tempMark;
    }

    public void setTempMark(String tempMark) {
        this.tempMark = tempMark;
    }

    public String getMaterialsCategory() {
        return materialsCategory;
    }

    public void setMaterialsCategory(String materialsCategory) {
        this.materialsCategory = materialsCategory;
    }

    public String getWbsCode() {
        return wbsCode;
    }

    public void setWbsCode(String wbsCode) {
        this.wbsCode = wbsCode;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getMaterialsName() {
        return materialsName;
    }

    public void setMaterialsName(String materialsName) {
        this.materialsName = materialsName;
    }

    public String getAdditional1() {
        return additional1;
    }

    public void setAdditional1(String additional1) {
        this.additional1 = additional1;
    }

    public String getAdditional2() {
        return additional2;
    }

    public void setAdditional2(String additional2) {
        this.additional2 = additional2;
    }

    public String getAdditional3() {
        return additional3;
    }

    public void setAdditional3(String additional3) {
        this.additional3 = additional3;
    }

    public String getAdditional4() {
        return additional4;
    }

    public void setAdditional4(String additional4) {
        this.additional4 = additional4;
    }

    public String getIsTake() {
        return isTake;
    }

    public void setIsTake(String isTake) {
        this.isTake = isTake;
    }
}
