package com.dkd.emms.systemManage.service;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.core.service.BaseService;
import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Design;
import com.dkd.emms.systemManage.bo.Materials;
import com.dkd.emms.systemManage.bo.MaterialsAttachement;
import com.dkd.emms.systemManage.dao.MaterialsDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class MaterialsService extends BaseService<Materials> {
	
	@Autowired
	private MaterialsDao materialsDao;
	@Autowired
	private MaterialsAttachementService materialsAttachementService;

	public BaseDao<Materials> getDao() {
		return materialsDao;
	}
	
	//储存或更新一个实体
	public String save(Materials materials) {
		materials = setDefault(materials);
		vailidate(materials);
		if(materials.getMaterialsId()!=null && !materials.getMaterialsId().equals("")){
			materialsDao.update(materials);
		}else{
			materials.setMaterialsId(UUIDGenerator.getUUID());
			materialsDao.insert(materials);
		}
		return materials.getMaterialsId();
	}
	
	
	//校验
	public void vailidate(Materials materials){
		if(StringUtils.isEmpty(materials.getMaterialsCode()) ){
			throw new BusinessException("物资编码不能为空");
		}
		if(StringUtils.isEmpty(materials.getMaterialsDescribe())){
			throw new BusinessException("物资总描述不能为空");
		}
		if(StringUtils.isEmpty(materials.getMaterialsState())){
			throw new BusinessException("物资状态不能为空");
		}
		if(StringUtils.isEmpty(materials.getMaterialsUnitMain())){
			throw new BusinessException("物资主计量单位不能为空");
		}
		if(StringUtils.isEmpty(materials.getMaterialsCategory())){
			throw new BusinessException("物资类别不能为空");
		}
	}
	
	//添加系统维护字段相应值
	public Materials setDefault(Materials materials){
		if(StringUtils.isEmpty(materials.getIsdel())){
			materials.setIsdel("1");
		}
		if(StringUtils.isEmpty(materials.getMaterialsState())){
			materials.setMaterialsState("new");
		}
		if(StringUtils.isEmpty(materials.getTempMark())){
			materials.setTempMark("0");
		}
		return materials;
	}

	/**
	 * 批量插入物料明细
	 * @param materialsList
	 * @return
	 */
	public List<Materials> saveMaterial(List<Materials> materialsList){
		for(Materials materials:materialsList){
			materials.setMaterialsId(UUIDGenerator.getUUID());
			setDefault(materials);
			vailidate(materials);
		}
		 materialsDao.insertList(materialsList);
		 for(Materials materials:materialsList){
			 System.out.print(materials.getMaterialsId());
		 }
		return materialsList;
	}

	/**
	 * 根据设计院编码插入系统物料
	 * @param designList
	 * @return
	 */
	public Map<String, String> saveMaterialByDesign(List<Design> designList, Map<String,String> equipmentNoMap, Map<String,String> ComponentRelationship){
		//返回 列表
		Map<String, String> returnList = new HashMap<String, String>();
		//明细插入总列表
		List<Materials> allNewMaterials = new ArrayList<Materials>();
		//关系插入总列表
		List<MaterialsAttachement> allNewMA = new ArrayList<MaterialsAttachement>();

		//临时存放部件
		List<Design> tempParts = new ArrayList<Design>();
		//新的设备
		Map<String,Materials> noRepeatS = new HashMap<String,Materials>();
		//设备新旧ID对照
		Map<String,String> sIdMap = new HashMap<String, String>();

		for(Design d: designList){
			Materials materials = null;
			Map<String,String> condition = new HashMap<String, String>();
			if(d.getDesignType().equals("s")){//当前为设备
				condition.put("designCode", d.getDesignCode());
				condition.put("equipmentNo", equipmentNoMap.get(d.getDesignId()));
				materials = materialsDao.select4design(condition);
				if(materials!=null){//该条设备已存在
					//更新返回列表
					returnList.put(d.getDesignId(), materials.getMaterialsId());
					//存入设备新旧id
					sIdMap.put(d.getDesignId(), materials.getMaterialsId());
				}else{//该条设备需要插入
					//将设计院设备转换为系统设备
					materials = convert2materials(d);
					//更新返回列表
					returnList.put(d.getDesignId(), materials.getMaterialsId());
					//转换后放入新设备map
					noRepeatS.put(d.getDesignId(), materials);
				}
			}else if(d.getDesignType().equals("w")){//当前为物料
				condition.put("designCode",d.getDesignCode());
				condition.put("designDescribe",d.getDesignDescribe());
				condition.put("additional1",d.getAdditional1());
				condition.put("additional2",d.getAdditional2());
				condition.put("additional3",d.getAdditional3());
				condition.put("additional4",d.getAdditional4());
				materials = materialsDao.select4design(condition);
				if(materials!=null){//改条物料已存在
					//更新返回列表
					returnList.put(d.getDesignId(), materials.getMaterialsId());
				}else{
					//将设计院物料转换为系统物料
					materials = convert2materials(d);
					//更新返回列表
					returnList.put(d.getDesignId(), materials.getMaterialsId());
					//转换后放入插入列表
					allNewMaterials.add(materials);
				}
			}else{//当前为部件
				tempParts.add(d);
			}
		}
		for(Design p: tempParts){//处理部件
			//判断该部件的父节点设备是否已存在
			String sid = null;
			sid = sIdMap.get(ComponentRelationship.get(p.getDesignId()));
			if(sid != null){//该设备已存在
				Map<String,Object> condition = new HashMap<String, Object>();//查询条件
				condition.put("DesignParts", p);//该条部件全部数据
				condition.put("sId", sid);//该部件的父节点设备id
				Materials parts = null;
				parts = materialsDao.findRepeatParts(condition);//根据条件查找部件重复项
				if( parts!=null ){//该部件已存在
					//更新返回列表
					returnList.put(p.getDesignId(), parts.getMaterialsId());
				}else{//该部件不存在 需要插入
					//将设计院部件转换为系统部件
					Materials tp = convert2materials(p);
					//将准备插入的系统部件放入插入列表
					allNewMaterials.add(tp);
					//更新返回列表中的部件
					returnList.put(p.getDesignId(), parts.getMaterialsId());
					//获取该部件的父节点设备
					Materials ts = materialsDao.selectByPk(sid);
					//生成一条关系数据 并添加入插入列表
					MaterialsAttachement ma = new MaterialsAttachement();
					ma.setMaterialsEId(ts.getMaterialsId());
					ma.setMaterialsMId(tp.getMaterialsId());
					ma.setAttachmentNumber(p.getDesignCount());
					ma.setWbsId(ts.getWbsCode());
					ma.setEquipmentNo( equipmentNoMap.get(ComponentRelationship.get(p.getDesignId())) );
					allNewMA.add(ma);
				}
			}else{//该设备尚未存在 则其下部件必插入
				//将设计院部件转换为系统部件
				Materials tp = convert2materials(p);
				//获取该条部件对应的系统设备
				Materials ts = noRepeatS.get(ComponentRelationship.get(p.getDesignId()));
				allNewMaterials.add(ts);//将准备插入的系统设备放入插入列表
				allNewMaterials.add(tp);//将准备插入的系统部件放入插入列表
				//更新返回列表中的部件
				returnList.put(p.getDesignId(), tp.getMaterialsId());
				//生成一条关系数据 并添加入插入列表
				MaterialsAttachement ma = new MaterialsAttachement();
				ma.setMaterialsEId(ts.getMaterialsId());
				ma.setMaterialsMId(tp.getMaterialsId());
				ma.setAttachmentNumber(p.getDesignCount());
				ma.setWbsId(ts.getWbsCode());
				ma.setEquipmentNo( equipmentNoMap.get(ComponentRelationship.get(p.getDesignId())) );
				allNewMA.add(ma);
			}
		}
		//保存系统物料
		materialsDao.insertList(allNewMaterials);
		//保存关系表
		materialsAttachementService.insetList(allNewMA);
		return returnList;
		/*
		//王倩原先写的
		List<Materials> returnmaterialsList = new ArrayList<Materials>();
		List<Materials> insertmaterialsList = new ArrayList<Materials>();
		for(Design design : designList){
			Materials materials = new Materials();
			materials.setMaterialsCode(design.getDesignCode());
			materials = materialsDao.selectByCode(materials.getMaterialsCode());
			if(StringUtils.isEmpty(materials.getMaterialsId())){
				materials.setMaterialsDescribe(design.getDesignDescribe());
				materials.setMaterialsUnitMain(design.getDesignUnitMain());
				materials.setMaterialsCategory(design.getDesignType());
				insertmaterialsList.add(materials);
			}
			returnmaterialsList.add(materials);
		}
		this.saveMaterial(insertmaterialsList);
		return returnmaterialsList;
		*/
	}

	public Materials convert2materials(Design d){
		Materials m = new Materials();
		m.setMaterialsId(UUIDGenerator.getUUID());
		m.setMaterialsCode(d.getDesignCode());
		m.setMaterialsDescribe(d.getDesignDescribe());
		m.setMaterialsState("new");
		m.setMaterialsUnitMain(d.getDesignUnitMain());
		m.setMaterialsUnitSecondary(d.getDesignUnitSecondary());
		m.setConversion(new BigDecimal(d.getDesignConversion()));
		m.setTempMark("0");
		m.setMaterialsCategory(d.getDesignType());
		m.setIsdel("1");
		m.setMaterialsName(d.getDesignName());
		m.setAdditional1(d.getAdditional1());
		m.setAdditional2(d.getAdditional2());
		m.setAdditional3(d.getAdditional3());
		m.setAdditional4(d.getAdditional4());
		if(d.getDesignType().equals("s")){
			m.setWbsCode(d.getWbsCode());
		}
		return m;
	}

	/**
	 * 校验Code是否重复
	 * @param materialsCode
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean  checkMaterials(String materialsCode){
		int count=materialsDao.checkMaterials(materialsCode);
		return count>0?false:true;
	}

	public void markDelete(String materialsId) {
		materialsDao.markDelete(materialsId);
	}

	/**
	 * 提交物料信息
	 * @param materialsId
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void commit(String materialsId) {
		Materials materials = this.selectByPk(materialsId);
		materials.setMaterialsState("success");
		this.update(materials);
	}


}
