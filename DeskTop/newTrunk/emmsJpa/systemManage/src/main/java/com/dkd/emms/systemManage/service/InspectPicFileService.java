package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.InspectPicFile;
import com.dkd.emms.systemManage.bo.MaterialInspect;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.dao.InspectPicFileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YINXP on 2017/3/10.
 */

@Service
@Transactional
public class InspectPicFileService extends BaseService<InspectPicFile>{

    @Autowired
   private InspectPicFileDao inspectPicFileDao;

    @Autowired
    private QualityInspectDetailService qualityInspectDetailService;
    //保存文件
    public void saveOriginalFile(MultipartFile pic,String materiaInspectId) {
        try {
            InspectPicFile blobField = new InspectPicFile();
            //获取上传文件真实名称
           String realFileName = pic.getOriginalFilename();
            blobField.setMateriaInspectId(materiaInspectId);
            blobField.setRealFileName(realFileName);
            blobField.setContents(pic.getBytes());
            blobField.setInspectPicFileId(UUIDGenerator.getUUID());
            inspectPicFileDao.insert(blobField);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //质检单下的图片信息
    public List<InspectPicFile> selectByInspectId(String materiaInspectId) {
        return inspectPicFileDao.queryMaterialsData(materiaInspectId);
    }

    //质检单下下载预览某个图片信息
    public InspectPicFile selectByInspectPicId(String InspectPicFileId) {
        return inspectPicFileDao.selectByPkPicId(InspectPicFileId);
    }

    @Override
    public BaseDao getDao() {
        return inspectPicFileDao;
    }

}