package com.dkd.emms.systemManage.bo;

/**
 * Created by SHAHJY on 2017/4/11.
 */
public class MaterialInpectPicSingleton {

    private String MaterialInspectId;
    private static MaterialInpectPicSingleton materialInpectPicSingleton = null;

    public static MaterialInpectPicSingleton getMaterialInpectPicSingleton(){
        if(materialInpectPicSingleton == null){
            materialInpectPicSingleton = new MaterialInpectPicSingleton();
        }
        return  materialInpectPicSingleton;
    }

    public String getMaterialInspectId() {
        return MaterialInspectId;
    }

    public void setMaterialInspectId(String materialInspectId) {
        MaterialInspectId = materialInspectId;
    }
}
