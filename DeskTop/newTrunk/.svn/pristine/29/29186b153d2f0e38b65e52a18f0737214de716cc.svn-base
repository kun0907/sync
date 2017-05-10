package com.dkd.emms.web.baseinfo.materials;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dkd.emms.core.util.uuid.UUIDGenerator;
import com.dkd.emms.systemManage.bo.Materials;
import com.dkd.emms.systemManage.bo.MaterialsUnit;
import com.dkd.emms.systemManage.service.MaterialsService;
import com.dkd.emms.systemManage.service.MaterialsUnitService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.web.baseinfo.materials.queryCondition.MaterialsCondition;
import com.dkd.emms.web.util.page.PageBean;


@Controller
@RequestMapping(value = "/baseinfo/materials.do")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;
    @Autowired
    private MaterialsUnitService materialsUnitService;

    /**
     * 加载系统物料查询页面
     *
     * @return
     */
    @RequestMapping(params = {"cmd=query"}, method = RequestMethod.GET)
    public String query() {
        return "baseinfo/materials/queryMaterials";
    }

    /**
     * 加载数据
     *
     * @return
     */
    @RequestMapping(params = {"cmd=loadMaterialListData"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<Materials> loadMaterialListData(@RequestParam(value = "page") int start, @RequestParam(value = "rows") int length,
                                                    MaterialsCondition materialsCondition) {
        PageBean<Materials> pageBean = new PageBean<Materials>();
        pageBean.setTotal(materialsService.countByCondition(materialsCondition));
        pageBean.setRows(materialsService.selectByCondition(materialsCondition, pageBean.getTotal(), start, length));
        return pageBean;
    }

    /**
     * 加载数据弹出框数据
     *
     * @return
     */
    @RequestMapping(params = {"cmd=loadModalMaterial"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<Materials> loadModalMaterial(@RequestParam(value = "page") int start, @RequestParam(value = "rows") int length,
                                             MaterialsCondition materialsCondition) {
        materialsCondition.setMaterialsState("success");
        PageBean<Materials> pageBean = new PageBean<Materials>();
        pageBean.setTotal(materialsService.countByCondition(materialsCondition));
        pageBean.setRows(materialsService.selectByCondition(materialsCondition, pageBean.getTotal(), start, length));
        return pageBean;
    }

    /**
     * 编辑系统物料(页面)
     *
     * @param materialsId
     * @param model
     * @return
     */
    @RequestMapping(params = {"cmd=edit"}, method = RequestMethod.GET)
    public String materialsInfo(String materialsId, ModelMap model) {
        model.put("materialsId", materialsId);
        return "baseinfo/materials/editMaterials";
    }

    /**
     * 根据系统物料id加载物料信息
     *
     * @param materialsId
     * @return
     * @throws IOException
     */
    @RequestMapping(params = {"cmd=loadMaterialsData"}, produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Materials loadMaterialsData(String materialsId) throws IOException {
        Materials materials = new Materials();
        if (StringUtils.isNotEmpty(materialsId)) {
            materials = materialsService.selectByPk(materialsId);
        }
        return materials;
    }

    /**
     * 保存物资信息
     *
     * @param materials
     * @throws IOException
     */
    @RequestMapping(params = {"cmd=saveMaterials"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public String materialsEdit(@RequestBody Materials materials) throws IOException {
        String materialsId = materialsService.save(materials);
        List<MaterialsUnit> unitList = materials.getUnitList();
        for(int i=0; i<unitList.size(); i++){
            unitList.get(i).setMaterialsId(materialsId);
            unitList.get(i).setMaterialsUnitId(UUIDGenerator.getUUID());
        }
        materialsUnitService.deleteByMaterialsId(materialsId);
        materialsUnitService.insertList(materials.getUnitList());
        return "true";
    }
    /**
     * 保存物资信息
     *
     * @throws IOException
     */
    @RequestMapping(params = {"cmd=updateMaterialsState"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public String updateMaterialsState(String id) {
        Materials materials = materialsService.selectByPk(id);
        materials.setMaterialsState("commit");
        materialsService.save(materials);
        return "true";
    }
    /**
     * 系统物料公共弹出框
     *
     * @return
     */
    @RequestMapping(params = {"cmd=modal"}, method = RequestMethod.GET)
    public String modal() {
        return "baseinfo/materials/modalMaterials";
    }


    /**
     * 用户Code是否重复
     * @throws IOException
     */
    @RequestMapping( params = {"cmd=checkMaterials"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkMaterials(String materialsCode) throws IOException{
        return materialsService.checkMaterials(materialsCode);
    }

    //加载系统物资单位换算数据
    @RequestMapping(params = {"cmd=loadMaterialsUnitData"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public List<MaterialsUnit> loadMaterialsUnitData(String materialsId){
        if (materialsId!=null && !materialsId.equals("")) {
            return materialsUnitService.selectListByMaterialsId(materialsId);
        }
        return null;
    }

    //标记删除
    @RequestMapping(params = {"cmd=delete"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public String delete(String materialsId){
        materialsService.markDelete(materialsId);
        return "true";
    }


    //物料提交
    @RequestMapping(params = {"cmd=commit"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public String commit(String materialsId){
        materialsService.commit(materialsId);
        return "true";
    }

}
