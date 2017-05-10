package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;

import com.dkd.emms.core.util.page.Page;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.InspectPicFileDao;
import com.dkd.emms.systemManage.dao.MaterialInspectDao;
import com.dkd.emms.systemManage.dao.QualityInspectDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YINXP on 2017/3/10.
 */
@Service
@Transactional
public class QualityInspectDetailService extends BaseService<QualityInspectDetail> {
    @Override
    public BaseDao<QualityInspectDetail> getDao() {
        return qualityInspectDetailDao;

    }


    @Autowired
    private MaterialInspectDao materialInspectDao;

    @Autowired
    private QualityInspectDetailDao qualityInspectDetailDao;

    @Autowired
    private InspectPicFileDao inspectPicFileDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertList(List<QualityInspectDetail> list) {
        qualityInspectDetailDao.insertList(list);
    }
    public List<QualityInspectDetail> queryMaterialsData(String materiaInspectId){

        return qualityInspectDetailDao.queryMaterialsData(materiaInspectId);
    }
    public QualityInspectDetail setDefault(QualityInspectDetail qualityInspectDetail){
        return qualityInspectDetail;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void insertListP(List<InspectPicFile> listP) {
        inspectPicFileDao.insertListP(listP);
    }

    public InspectPicFileDao getInspectPicFileDao() {
        return inspectPicFileDao;
    }

    public void setInspectPicFileDao(InspectPicFileDao inspectPicFileDao) {
        this.inspectPicFileDao = inspectPicFileDao;
    }

    /**
     * 筛选质检单明细中合格的数据
     * */
    public List<QualityInspectDetail> selectQualityInspectDetail(Object object,Integer total,Integer start,Integer length) {
        Page page = new Page(total,start,length);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(object.getClass().getSimpleName(), object);
        map.put("page", page);
        return qualityInspectDetailDao.selectQualityInspectDetail(map);
    }

    /**
     * 提供质检单明细【合格字段】数据进行筛选
     * */
    public String selectInspectDetailCheckOut(QualityInspectDetail detail) {
        String flag = "";
        BigDecimal SafeCount = new BigDecimal(0);//合格总数量
        BigDecimal DianshouCount = new BigDecimal(0);//点收数量
        BigDecimal displayData = detail.getQualifiedQty();//前台填写的合格数量
        List<QualityInspectDetail> DetailList = qualityInspectDetailDao.selectInspectDetailValidate(detail.getDeliveryId());
        if(DetailList.size() > 0){
            for(QualityInspectDetail QualityDetail:DetailList) {
                BigDecimal SafeCount1 = QualityDetail.getQualifiedQty();//合格数量
                SafeCount = SafeCount1.add(SafeCount);//将合格数量累计相加
                DianshouCount = QualityDetail.getDianshouCount();//点收数量
            }
            BigDecimal QCount = SafeCount.add(displayData);//【合格总数量 + 前台填写的合格数量】
            int result = DianshouCount.compareTo(QCount);
            if (result == -1){
                flag = "-1";
            }else if (result == 1){
                flag = "";
            }
        }

        return flag;
    }


}
