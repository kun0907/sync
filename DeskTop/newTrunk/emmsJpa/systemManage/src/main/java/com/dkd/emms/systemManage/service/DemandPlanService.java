package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.Common;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.DemandPlanDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YUZH on 2017/3/16.
 */
@Service
@Transactional
public class DemandPlanService extends BaseService<DemandPlan> {
    @Autowired
    private DemandPlanDao demandPlanDao;

    @Override
    public BaseDao<DemandPlan> getDao() {
        return demandPlanDao;
    }
    @Autowired
    private DemandDetailService demandDetailService;
    @Autowired
    private DemandChangeRecordService demandChangeRecordService;
    @Autowired
    private DemandDrawingService demandDrawingService;
    @Autowired
    private DrawingDetailedService drawingDetailedService;
    @Autowired
    private SequenceService sequenceService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(DemandPlan demandPlan,User user) {
        this.setDefault(demandPlan, user);
        if(StringUtils.isEmpty(demandPlan.getDemandId())){
            demandPlan.setDemandId(UUIDGenerator.getUUID());
            demandPlan.setIsChange(false);
            demandPlan.setDemandCode(sequenceService.getFlowNoByJudge("demand", "PMBXY" + dateFormat.format(new Date()), 5));
            demandPlanDao.insert(demandPlan);
        }else{
            if(demandPlan.getDemandState().equals("demandPass")|| demandPlan.getDemandState().equals("demandNoPass")){
                demandPlan.setIsChange(true);
                demandPlan.setDemandState("demandCheck");
            }
            demandPlanDao.update(demandPlan);
        }
        if(demandPlan.getDemandState().equals("demandPass")|| demandPlan.getDemandState().equals("demandNoPass")){
            if(demandPlan.getDetailList().size()>0){
                for(DemandDetail detail:demandPlan.getDetailList()){
                    demandDetailService.update(detail);
                }
            }
        }else{
            demandDetailService.delete(demandPlan.getDemandId());
            List<DemandDetail> detailList = new ArrayList<DemandDetail>();
            if(demandPlan.getDetailList().size()>0){
                for(DemandDetail detail:demandPlan.getDetailList()){
                    detail.setDemandDetailId(UUIDGenerator.getUUID());
                    detail.setDemandId(demandPlan.getDemandId());
                    detailList.add(detail);
                }
            }
            demandDetailService.insetList(detailList);
        }
    }
    public void setDefault(DemandPlan demandPlan,User user){
        if(StringUtils.isEmpty(demandPlan.getDemandId())){
            demandPlan.setCreateTime(new Date());
            demandPlan.setCreateUserId(user.getUserId());
            if(null == user.getEmployee()){
                demandPlan.setCreateUserName(user.getUserName());
            }else{
                demandPlan.setCreateUserName(user.getEmployee().getEmpName());
            }
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateState(String demandId,String state){
        DemandPlan demandPlan = demandPlanDao.selectByPk(demandId);
        demandPlan.setDemandState(state);
        demandPlanDao.update(demandPlan);
        //当审批时需要修改料表需用数量表中需用总数量
        List<DemandDetail> detailList = demandDetailService.selectByDemandId(demandId);
        if(state.equals("demandPass") &&detailList.size()>0){
            for(DemandDetail detail:detailList){
                DrawingDetailed drawingDetailed = drawingDetailedService.selectByPk(detail.getDrawingDetailedId());
                DemandDrawing drawing = this.selectByDemand(detail);
                if(!demandPlan.getIsChange()){
                    if(demandPlan.getDataSource().equals("demandDesign")){
                        drawing.setDemandCount((drawing.getDemandCount()==null?new BigDecimal(0):drawing.getDemandCount()).add(detail.getDemandCount()));
                        demandDrawingService.save(drawing);
                        drawingDetailed.setUsedCount((drawingDetailed.getUsedCount()==null?new BigDecimal(0):drawingDetailed.getUsedCount()).add(detail.getDemandCount()));
                    }
                }else{
                    if(demandPlan.getDataSource().equals("demandDesign")){
                        drawing.setDemandCount(drawing.getDemandCount().subtract(detail.getDemandCount()).add(detail.getChangeCount()));
                        demandDrawingService.save(drawing);
                        drawingDetailed.setUsedCount((drawingDetailed.getUsedCount()==null?new BigDecimal(0):drawingDetailed.getUsedCount()).subtract(detail.getDemandCount()).add(detail.getChangeCount()));
                    }
                    detail.setDemandCount(detail.getChangeCount());
                    detail.setChangeCount(null);
                    demandDetailService.update(detail);
                }
                drawingDetailedService.update(drawingDetailed);
            }
                demandChangeRecordService.insetList(detailList);
        }
    }
    //查找料表需用数量表
    public DemandDrawing selectByDemand(DemandDetail detail){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("wbsId",detail.getWbsId());
        map.put("materialsId",detail.getMaterialsId());
        map.put("designCode",detail.getDesignOrgCode());
        map.put("drawingNumberCode",detail.getDrawingNumberCode());
        map.put("drawingNumberVersion",detail.getDrawingNumberVersion());
        DemandDrawing drawing = demandDrawingService.selectByDemand(map);
        if(null == drawing){
            DemandDrawing demandDrawing  = new DemandDrawing();
            demandDrawing.setDesignCode(detail.getDesignOrgCode());
            demandDrawing.setWbsId(detail.getWbsId());
            demandDrawing.setMaterialsId(detail.getMaterialsId());
            demandDrawing.setDrawingNumberCode(detail.getDrawingNumberCode());
            demandDrawing.setDrawingNumberVersion(detail.getDrawingNumberVersion());
            demandDrawing.setDesignCount(detail.getDesignCount());
            demandDrawing.setDemandCount(new BigDecimal(0));
            return demandDrawing;
        }
        return drawing;
    }


    public void updateUsedCount(List<PickNoticeDetail> details){
        for(PickNoticeDetail detail:details){
            DemandDetail demandDetail = demandDetailService.selectByPk(detail.getDemanddetail().getDemandDetailId());
            if(null != demandDetail){
                demandDetail.setUsedCount((demandDetail.getUsedCount()==null?new BigDecimal(0):demandDetail.getUsedCount()).add(detail.getPickNum()==null?new BigDecimal(0):detail.getPickNum()));
                demandDetailService.update(demandDetail);
            }
        }
    }

    public void subStractUsedCount(List<PickNoticeDetail> pickNoticeDetailList) {
        for(PickNoticeDetail detail:pickNoticeDetailList){
            DemandDetail demandDetail = demandDetailService.selectByPk(detail.getDemanddetail().getDemandDetailId());
            if(null != demandDetail){
                demandDetail.setUsedCount((demandDetail.getUsedCount()==null?new BigDecimal(0):demandDetail.getUsedCount()).subtract(detail.getPickNum()==null?new BigDecimal(0):detail.getPickNum().subtract(detail.getTallyedNum()==null?new BigDecimal(0):detail.getTallyedNum())));
                demandDetailService.update(demandDetail);
            }
        }
    }
}
