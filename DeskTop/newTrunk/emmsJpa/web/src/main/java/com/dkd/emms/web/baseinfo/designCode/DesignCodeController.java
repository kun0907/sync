/**
 * 
 */
package com.dkd.emms.web.baseinfo.designCode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dkd.emms.core.util.JsonUtil;
import com.dkd.emms.systemManage.bo.Design;
import com.dkd.emms.systemManage.bo.DesignCodeAttachment;
import com.dkd.emms.systemManage.service.DesignCodeAttachmentService;
import com.dkd.emms.systemManage.service.DesignCodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dkd.emms.core.entity.ExcleFileForm;
import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.systemManage.bo.Organization;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.web.baseinfo.designCode.queryCondition.DesignCondition;
import com.dkd.emms.web.baseinfo.materials.queryCondition.MaterialsCondition;
import com.dkd.emms.web.util.page.PageBean;

/**
 * @Title: DesignCodeController
 * @Description:
 * @param
 * @author:YUZH
 * @data 2017年2月21日
 */

@Controller
@RequestMapping(value="/baseinfo/designCode.do")
public class DesignCodeController {

	@Autowired
	private DesignCodeService designCodeService;
	@Autowired
	private DesignCodeAttachmentService designCodeAttachmentService;
	/**
	 * 加载设计院编码查询页面
	 * @return
	 */
	@RequestMapping( params = {"cmd=query"},method = RequestMethod.GET)
	public String query(){
		return "baseinfo/designCode/query";
	}
	/**
	 * 加载设计院编码数据	
	 */
	@RequestMapping( params = {"cmd=loadDesignCodeListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Design> loadDesignCodeListData(@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows",required = false) Integer length,
												   DesignCondition designCondition,String designId){
		designCondition.setDesignId(designId);
		PageBean<Design> pageBean = new PageBean<Design>();
		pageBean.setTotal(designCodeService.countByCondition(designCondition));
		pageBean.setRows(designCodeService.selectByCondition(designCondition,pageBean.getTotal(),start,length));
		return pageBean;
	}
	/**
	 * 编辑页面
	 * @return
	 */
	@RequestMapping( params = {"cmd=edit"},method = RequestMethod.GET)
	public String edit(String designId, ModelMap model){
		model.addAttribute("designId", designId);
		return "baseinfo/designCode/edit";
	}
	/**
	 * 编辑页面加载数据
	 *
	 */
	@RequestMapping( params = {"cmd=loadDesignCodeData"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Design loadDesignCodeData(String designId) throws IOException{
		Design design = new Design();
		if(StringUtils.isNotEmpty(designId)){
			design = designCodeService.selectByPk(designId);
		}
		return design;
	}
	/**
	 * 保存
	 */
	@RequestMapping( params = {"cmd=save"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String save(Design design) throws IOException{
		designCodeService.update(design);
		return "保存成功";
	}
	/**
	 * 逻辑删除设计院
	 */
	@RequestMapping( params = {"cmd=deleteDesignCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String deleteDesignCode(String designId) throws IOException{
		Design design = designCodeService.selectByPk(designId);
		design.setIsdel("0");
		designCodeService.update(design);
		return "删除完成";
	}
	//物料匹配
	@RequestMapping( params = {"cmd=dialogMatchingMaterials"},produces = "text/html",method = RequestMethod.GET)
	public String dialogMatchingMaterials(String designId,ModelMap model){
		model.addAttribute("designId",designId);
		model.addAttribute("centerFrameUrl", "baseinfo/materials.do?cmd=modal");
		return "baseinfo/designCode/isMatchingMaterials";
	}

	//人工匹配物资编码
	@RequestMapping( params = {"cmd=manualMatching"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String manualMatching(String systemcodeId, String designId) throws IOException{
		Design design = designCodeService.selectByPk(designId);
		design.setSystemcodeId(systemcodeId);
		design.setIsMatching("1");
		designCodeService.update(design);
		return "保存成功";
	}
	//查看设备以及部件
	@RequestMapping( params = {"cmd=dialogEquipmentTree"},produces = "text/html",method = RequestMethod.GET)
	public String dialogEquipmentTree(String designId,ModelMap model){
		model.addAttribute("designId",designId);
		return "baseinfo/designCode/dialogEquipmentTree";
	}
	/**
	 * 编辑页面加载数据
	 *
	 */
	@RequestMapping( params = {"cmd=loadEquipmentTree"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> loadEquipmentTree(String designId){
		Map<String,Object> map = new HashMap<String,Object>();
		List<DesignCodeAttachment> dcList = new ArrayList<DesignCodeAttachment>();
		Design design = designCodeService.selectByPk(designId);
		String code="";
		List<DesignCodeAttachment> bjList = designCodeAttachmentService.selectByEquipment(designId);
		for(DesignCodeAttachment att:bjList){
			code = att.getWbsId();
			att.setDesignType("材料");
			att.set_parentId(designId);
		}
		dcList.addAll(bjList);
		DesignCodeAttachment ds = new DesignCodeAttachment();
		ds.setDesignMId(designId);
		ds.setDesignEId(design.getDesignCode());
		ds.setDesignType("设备");
		ds.setEquipmentNo(design.getDeviceNo());
		ds.setAttachmentNumber(design.getDesignCount());
		ds.setWbsId(code);
		dcList.add(ds);
		map.put("rows",dcList);
		return map;
	}
}
