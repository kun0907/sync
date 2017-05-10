package com.dkd.emms.systemManage.bo;

import java.math.BigDecimal;

/**
 * Created by WUJY on 2017/3/15.
 */
public class MaterialsUnit {
    private String materialsUnitId;
    private String materialsId;
    private String unitOfMeasurement;
    private BigDecimal materialsConversion;

    public String getMaterialsUnitId() {
        return materialsUnitId;
    }

    public void setMaterialsUnitId(String materialsUnitId) {
        this.materialsUnitId = materialsUnitId;
    }

    public String getMaterialsId() {
        return materialsId;
    }

    public void setMaterialsId(String materialsId) {
        this.materialsId = materialsId;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public BigDecimal getMaterialsConversion() {
        return materialsConversion;
    }

    public void setMaterialsConversion(BigDecimal materialsConversion) {
        this.materialsConversion = materialsConversion;
    }
}
