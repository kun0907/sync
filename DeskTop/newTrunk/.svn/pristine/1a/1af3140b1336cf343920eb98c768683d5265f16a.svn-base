package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.DemandDrawing;
import com.dkd.emms.systemManage.dao.DemandDrawingDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by YUZH on 2017/3/21.
 */
@Service
@Transactional
public class DemandDrawingService extends BaseService<DemandDrawing> {
    @Autowired
    private DemandDrawingDao demandDrawingDao;

    @Override
    public BaseDao<DemandDrawing> getDao() {
        return demandDrawingDao;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(DemandDrawing demandDrawing) {
        if(StringUtils.isEmpty(demandDrawing.getDrawingId())){
            demandDrawing.setDrawingId(UUIDGenerator.getUUID());
            insert(demandDrawing);
        }else{
            update(demandDrawing);
        }
    }
    public DemandDrawing selectByDemand(Map<String,Object> map){
        List<DemandDrawing> drawingList = demandDrawingDao.selectByDemand(map);
        if(drawingList.size()>0){
            return drawingList.get(0);
        }else{
            return null;
        }
    }
}
