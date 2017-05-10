package com.dkd.emms.systemManage.bo;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by YINXP on 2017/3/10.
 */
public class InspectPicFile {
    private String inspectPicFileId;
    private String materiaInspectId;//质检单id
    private String realFileName;//文件原始名称
    private byte[] contents;//二进制文件数据
    private String picType;//图片类型
    private MultipartFile pic;//文件对象


    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
    }

    public String getInspectPicFileId() {
        return inspectPicFileId;
    }

    public void setInspectPicFileId(String inspectPicFileId) {
        this.inspectPicFileId = inspectPicFileId;
    }

    public String getMateriaInspectId() {
        return materiaInspectId;
    }

    public void setMateriaInspectId(String materiaInspectId) {
        this.materiaInspectId = materiaInspectId;
    }



    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }
}
