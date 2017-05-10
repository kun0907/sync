package com.dkd.emms.systemManage.service;

import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.dao.MaterialInspectDao;
import com.dkd.emms.systemManage.dao.QualityInspectDetailDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by YINXP on 2017/3/8.
 */
@Service
@Transactional
public class MaterialInspectService extends BaseService<MaterialInspect> {
    @Autowired
    private MaterialInspectDao materialInspectDao;

    @Autowired
    private QualityInspectDetailDao qualityInspectDetailDao;

    @Autowired
    private InWarehouseService inWarehouseService;

    @Autowired
    private OutWarehouseService outWarehouseService;

    @Autowired
    private SequenceService sequenceService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private QualityInspectDetailService qualityInspectDetailService;


    @Override
    public BaseDao<MaterialInspect> getDao() {
        return materialInspectDao;
    }

    public BaseDao<QualityInspectDetail> getDetialDao() {
        return qualityInspectDetailDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    //质检单明细信息
    public List<QualityInspectDetail> selectByInspectId(String materiaInspectId) {
        return qualityInspectDetailDao.queryMaterialsData(materiaInspectId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveMaterialInspect(MaterialInspect materialInspect,User user) {
        this.setDefault(materialInspect,user);
        if( materialInspect.getMateriaInspectId()==null ||"".equals(materialInspect.getMateriaInspectId())){
            materialInspect.setMateriaInspectId(UUIDGenerator.getUUID());
            materialInspect.setInspectNo(sequenceService.getFlowNoByJudge("inspect", "PMB" + "ZJ" + dateFormat.format(new Date()), 5));//materialInspect
            materialInspect.setQualityInspectId(UUIDGenerator.getUUID());
            materialInspectDao.insert(materialInspect);
        }else{
            materialInspect.setQualityInspectId(UUIDGenerator.getUUID());
            materialInspectDao.update(materialInspect);
        }
        if( materialInspect.getMateriaInspectId()!=null || !"".equals(materialInspect.getMateriaInspectId())){
            MaterialInpectPicSingleton.getMaterialInpectPicSingleton().setMaterialInspectId(materialInspect.getMateriaInspectId());
        }
        qualityInspectDetailService.delete(materialInspect.getMateriaInspectId());
        List<QualityInspectDetail> list = new ArrayList<QualityInspectDetail>();
        for(QualityInspectDetail detail:materialInspect.getQualityInspectDetailList()){
            detail.setMateriaInspectId(materialInspect.getMateriaInspectId());
            detail.setQualityInspectId(materialInspect.getQualityInspectId());
            detail.setInspectNo(materialInspect.getInspectNo());
            list.add(detail);
        }
        if(list.size()>0){
            qualityInspectDetailService.insertList(list);
        }


    }
    /*
    * 当为直达现场时，对于有位号的设备，需要校验有无设备料表或者需用计划；
    * 对于没有位号的设备或者普通材料需要校验有无需用计划；
    *
    * */
//    public void validate(materiaInspectId){
//        List<QualityInspectDetail> InspectDetailList = selectByInspectId(materiaInspectId);//查询质检明细信息
//        for(QualityInspectDetail detail:detailList){
//            Map<String,Object> map = new HashMap<String,Object>();
//            if(StringUtils.isNotEmpty(detail.getDeviceNo())){
//                map.put("materialsId",detail.getMaterialsId());//物资编码ID
//                map.put("drawingNumberDeviceNo",detail.getDeviceNo());//位号
//                map.put("wbsId",detail.getWbsId());//wbsID
//                Boolean drawingFlag = drawingDetailedService.checkStoNo(map);
//                List<DemandDetail>demandDetailList = demandDetailService.selectByReceipt(map);
//                if(demandDetailList.size()==0){
//                    drawingFlag=false;
//                }
//                if(!drawingFlag){
//                    throw new BusinessException("需用计划或料表中"+detail.getMaterialsCode()+"物资不存在");
//                }
//            }else{
//                map.put("materialsId",detail.getMaterialsId());//物资编码ID
//                map.put("demandOrgId",orgId);//供应商ID
//                map.put("wbsId",detail.getWbsId());//wbsId
//                List<DemandDetail>demandDetailList = demandDetailService.selectByReceipt(map);
//                if(demandDetailList.size()==0){
//                    throw new BusinessException("需用计划中"+detail.getMaterialsCode()+"物资不存在");
//                }
//            }
//        }
//    }

    //生成入库单
    public void CreateInToEntity(String materiaInspectId,MaterialInspect materialInspect,User user){
        List<QualityInspectDetail> InspectDetailList = selectByInspectId(materiaInspectId);//查询质检明细信息
        InWarehouse inWarehouse = new InWarehouse();
        inWarehouse.setInWarehouseId("");//入库单ID（系统自动生成）
        inWarehouse.setInWarehouseCode("");//生成入库单编号(自动生成)
        inWarehouse.setSupplierId(materialInspect.getSupplierId());//供应商ID
        inWarehouse.setSupplierName(materialInspect.getSupplierName());//供应商名称
        inWarehouse.setInWarehouseState("yiruku");//入库状态:入库完成
        inWarehouse.setOrderId("");//采购订单ID
        inWarehouse.setOrderCode("");//采购订单编号
        inWarehouse.setCreateTime(materialInspect.getCreateTime());//创建时间
        inWarehouse.setInWarehouseTime(materialInspect.getCreateTime());//入库时间
        inWarehouse.setCreateUserID(materialInspect.getCreateUserId());//创建人ID
        inWarehouse.setCreateUserName(materialInspect.getCreateUserName());//创建人姓名
        inWarehouse.setInWorker(materialInspect.getInspector());//质检员
        materialInspect.setQualityInspectDetailList(InspectDetailList);
        List<InWarehouseDetail> list = new ArrayList<InWarehouseDetail>();//入库明细信息
        for(QualityInspectDetail detail:materialInspect.getQualityInspectDetailList()){
            InWarehouseDetail inWarehouseDetail = new InWarehouseDetail();
            inWarehouseDetail.setInDetailId("");//入库明细ID（系统生成）
            inWarehouseDetail.setInDetailCode("");//入库明细编号
            inWarehouseDetail.setSupplierId(materialInspect.getSupplierId());//供应商ID
            inWarehouseDetail.setSupplierName(materialInspect.getSupplierName());//供应商名称
            inWarehouseDetail.setInDetailState("yiruku");//入库明细状态
            inWarehouseDetail.setInWarehouseId("");//入库单ID（系统生成）
            inWarehouseDetail.setInWarehouseCode("");//入库单编号
            inWarehouseDetail.setCreateUserId(materialInspect.getCreateUserId());//创建人ID
            inWarehouseDetail.setCreateUserName(materialInspect.getCreateUserName());//创建人姓名
            inWarehouseDetail.setInWorker(materialInspect.getInspector());//入库人员
            inWarehouseDetail.setStoragelocationId(detail.getStorageId());//储位ID
            inWarehouseDetail.setStoragelocationCode(detail.getStorageCode());//储位编码
            inWarehouseDetail.setMaterialsId(detail.getMaterialsId());//物料编码ID（明细单据带入）
            inWarehouseDetail.setMaterialsCode(detail.getMaterialsCode());//物料编码（明细单据带入）
            inWarehouseDetail.setWbsId(detail.getWbsId());// WBS编码ID（明细单据带入）
            inWarehouseDetail.setWbsCode(detail.getWbsCode());//WBS编码（明细单据带入）
            inWarehouseDetail.setMaterialsDescribe(detail.getMaterialsDescribe());//物料描述（明细单据带入）
            inWarehouseDetail.setAdditional1(detail.getAdditional1());//附加1
            inWarehouseDetail.setAdditional2(detail.getAdditional2());//附加2
            inWarehouseDetail.setAdditional3(detail.getAdditional3());//附加3
            inWarehouseDetail.setAdditional4(detail.getAdditional4());//附加4
            inWarehouseDetail.setMaterialsUnitMain(detail.getMaterialsUnitMain());//采购计量单位
            inWarehouseDetail.setProductionDate(detail.getProductDate());//生产日期（年月日）
            inWarehouseDetail.setBzq(detail.getQualityDate());//保质期
            BigDecimal e_purchaseCount = detail.getPurchaseCount();
            inWarehouseDetail.setPurchaseCount(e_purchaseCount);//采购数量
            BigDecimal  e_DeliveryQty= detail.getDeliveryQty();
            inWarehouseDetail.setDeliveryCount(e_DeliveryQty);//已发货数量
            BigDecimal e_currentDeliveryQty = detail.getCurrentDeliveryQty();
            inWarehouseDetail.setThisDeliveryCount(e_currentDeliveryQty);// 这次发货数量
            BigDecimal e_DianshouCount = detail.getDianshouCount();
            inWarehouseDetail.setDianshouCount(e_DianshouCount);//点收数量
            BigDecimal e_QualifiedCount = detail.getQualifiedQty();
            inWarehouseDetail.setQualifiedCount(e_QualifiedCount);//合格数量
            BigDecimal e_unQualifiedQty = detail.getUnQualifiedQty();
            inWarehouseDetail.setUnqualifiedCount(e_unQualifiedQty);//不合格数量
            inWarehouseDetail.setVisualInspection(detail.getAppearanceInspect());//外观检查
            inWarehouseDetail.setReview(detail.getRecheckInspect());//需要复检
            inWarehouseDetail.setInWarehouseCount(e_QualifiedCount);//入库数量
            inWarehouseDetail.setZhijianId(materialInspect.getMateriaInspectId());//质检单ID
            inWarehouseDetail.setZhijianCode(materialInspect.getInspectNo());//质检单编号
            inWarehouseDetail.setOrderId("");//采购订单ID
            inWarehouseDetail.setOrderCode("");//采购订单编号
            inWarehouseDetail.setWarehouseId("");//仓库ID
            inWarehouseDetail.setReservoirareaId("");//库区ID
            list.add(inWarehouseDetail);
        }
            inWarehouse.setInWarehouseDetailList(list);//质检明细信息
        inWarehouseService.saveInWarehouse(inWarehouse,user);//调用入库保存方法
    }

    //生成出库单
    public void CreateOuToEntity(String materiaInspectId,MaterialInspect materialInspect,User user){
        List<QualityInspectDetail> InspectDetailList = selectByInspectId(materiaInspectId);//查询质检明细信息
        materialInspect.setQualityInspectDetailList(InspectDetailList);
        OutWarehouse outWarehouse = new OutWarehouse();
        outWarehouse.setOutWarehouseId("");//出库单id（系统生成）
        outWarehouse.setOutWarehouseNo("");//出库单编号(系统生成)
        Organization organization = new Organization();
        for(QualityInspectDetail detail:materialInspect.getQualityInspectDetailList()){
            organization.setOrgId(detail.getReceiptOrgId());//------组织机构id
        }
        outWarehouse.setContractor(organization);//施工单位
        outWarehouse.setCreateUserId(materialInspect.getCreateUserId());//创建人ID
        outWarehouse.setCreateUserName(materialInspect.getCreateUserName());//创建人姓名
        outWarehouse.setCreateTime(materialInspect.getCreateTime());//创建时间
        outWarehouse.setOutTime(materialInspect.getCreateTime());//出库时间
        outWarehouse.setOutWarehouseState(OutWarehouseEnum.outFinish.toString());//出库单状态
        List<OutWarehouseDetail> outList= new ArrayList<OutWarehouseDetail>();//出库明细信息
        for(QualityInspectDetail detail:materialInspect.getQualityInspectDetailList()){
            OutWarehouseDetail OutDetail = new OutWarehouseDetail();
            OutDetail.setWarehouseDetailId("");//出库单明细id
            OutWarehouse outWarehouse1 = new OutWarehouse();
            outWarehouse1.setOutWarehouseId("");//------------- 出库单id
            OutDetail.setOutWarehouse(outWarehouse1);//出库单
            Materials materials = new Materials();
            materials.setMaterialsId(detail.getMaterialsId());//------------------系统物资id
            OutDetail.setMaterials(materials);//系统物料
            Project project = new Project();
            String projectStr =  project.getProjectId();
            project.setProjectId(detail.getWbsId());//---------------项目表信息(WBS) id
            OutDetail.setWbs(project);//工程WBS
            PickNotice pickNotice = new PickNotice();
            String pickNoticeStr = pickNotice.getPickId();
            pickNotice.setPickId("");//-------------- 领料通知单id
            OutDetail.setPickNotice(pickNotice);//领料通知
            PickNoticeDetail pickNoticeDetail = new PickNoticeDetail();
            pickNoticeDetail.setPickDetailId("");//-------领料明细id
            OutDetail.setPickNoticeDetail(pickNoticeDetail);//领料通知明细
            BigDecimal hege = detail.getQualifiedQty();
            OutDetail.setOutNum(hege);//出库数量
            Tallying tallying = new Tallying();
            tallying.setTallyingId("");//---------理货id
            OutDetail.setTallying(tallying);//理货明细信息
            Storagelocation storagelocation = new Storagelocation();
            storagelocation.setStoragelocationId(detail.getStorageId());//---------储位ID
            OutDetail.setStoragelocation(storagelocation);//来源储位
            outList.add(OutDetail);
        }
        outWarehouse.setOutWarehouseDetailList(outList);//添加出库明细信息
        outWarehouseService.saveOutWarehouseForDirect(materialInspect, outWarehouse, user);//调用出库保存方法
    }


    /**
     * 设置默认值
     * @param
     */
    private void setDefault(MaterialInspect materialInspect,User user){
        if (null == materialInspect.getCreateTime()) {
            materialInspect.setCreateTime(new Date());
        }

        if(org.apache.commons.lang.StringUtils.isEmpty(materialInspect.getCreateUserId())){
            materialInspect.setCreateUserId(user.getUserId());
        }
        if(org.apache.commons.lang.StringUtils.isEmpty(materialInspect.getCreateUserName())){
            if(null == user.getEmployee()){
                materialInspect.setCreateUserName(user.getUserName());
            }else{
                materialInspect.setCreateUserName(user.getEmployee().getEmpName());
            }
        }

    }


}
