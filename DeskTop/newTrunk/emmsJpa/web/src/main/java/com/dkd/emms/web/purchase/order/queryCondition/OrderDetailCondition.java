package com.dkd.emms.web.purchase.order.queryCondition;

import java.util.List;

/**
 * Created by YUZH on 2017/2/28.
 */
public class OrderDetailCondition {
    private String orderId;
    private List<String> orderIdList;
    private String wbsCode;
    private String supplierId;
    private String materialsCode;
    private String materialsDescribe;
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public String getWbsCode() {
        return wbsCode;
    }

    public void setWbsCode(String wbsCode) {
        this.wbsCode = wbsCode;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
}
