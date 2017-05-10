package com.dkd.emms.web.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dkd.emms.systemManage.bo.Dictionary;
import com.dkd.emms.systemManage.service.DictionaryService;
import com.dkd.emms.web.util.page.PageBean;


/**
 * 字典controller
 * @author 5z
 *
 */

@Controller
@RequestMapping(value="/system/businessDictionary.do")
public class BusinessDictionaryController {

	@Resource
	private DictionaryService dictionaryServiceImpl;
	
	//初始化窗体frame
	@RequestMapping( params = {"cmd=dictionary"},produces = "text/html",method = RequestMethod.GET)
	public String dictionary(ModelMap model){
		model.addAttribute("westFrameUrl", "system/businessDictionary.do?cmd=dictionaryTree");
		model.addAttribute("centerFrameUrl", "system/businessDictionary.do?cmd=query&parentId=0");
		return "system/common/frame";
	}
	
	//初始化tree界面
	@RequestMapping( params = {"cmd=dictionaryTree"},produces = "text/html",method = RequestMethod.GET)
	public String dictionaryTree(ModelMap model){
		model.addAttribute("initTreeUrl", "system/businessDictionary.do?cmd=initTree");
		model.addAttribute("navUrl", "system/businessDictionary.do?cmd=query&parentId=");
		return "system/common/tree";
	}
	
	//初始化tree获取全部节点数据
	@RequestMapping( params = {"cmd=initTree"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Dictionary> initTree() throws Exception{
		List<Dictionary> list = dictionaryServiceImpl.selectAllDictionary("0");
		for(Dictionary dictionary:list){
			dictionary.setId(dictionary.getDictionaryId());
			dictionary.setName(dictionary.getDictionaryName());
		}
		return list;
	}
	
	//跳转到字典列表页面
	@RequestMapping( params = {"cmd=query"},produces = "text/html",method = RequestMethod.GET)
	public String query(String parentId, ModelMap model){
		model.addAttribute("parentId", parentId);
		model.addAttribute("dicType", "0");
		return "system/dictionary/query";
	}
	
	//查询以选择节点的ID作为parentId的全部节点
	@RequestMapping( params = {"cmd=selectDictionaryByParentId"},produces = "application/json")
	@ResponseBody
	public PageBean<Dictionary> selectDictionaryByParentId(@RequestParam(value = "page",required = false) Integer start,@RequestParam(value = "rows", required = false) Integer length,
			String parentId) {
		Dictionary dictionary = new Dictionary();
		dictionary.setParentId(parentId);
		dictionary.setDictionaryType(0);
		PageBean<Dictionary> pageBean = new PageBean<Dictionary>();
		pageBean.setTotal(dictionaryServiceImpl.countByCondition(dictionary));
		if(start==null)
			start = 0;
		if(length == null)
			length = 10;
		pageBean.setRows(dictionaryServiceImpl.selectByCondition(dictionary,pageBean.getTotal(),start,length));
		return pageBean;
	}
	
	//跳转到字典详细信息页面
	@RequestMapping( params = {"cmd=dictionaryInfo"},produces = "text/html",method = RequestMethod.GET)
	public String dictionaryInfo(String parentId, String dictionaryId, ModelMap model){
		model.addAttribute("parentId", parentId);
		model.addAttribute("dictionaryId", dictionaryId);
		model.addAttribute("dicType", "0");
		return "system/dictionary/edit";
	}

	
	//字典信息页 新建或编辑
	@RequestMapping( params = {"cmd=getDictionaryInfo"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Dictionary getDictionaryInfo(String parentId, String dictionaryId) {
		Dictionary dictionary = new Dictionary();
		dictionary.setParentId(parentId);
		if(StringUtils.isNotEmpty(dictionaryId)){
			dictionary = dictionaryServiceImpl.selectByPk(dictionaryId);
		}
		return dictionary;
	}
	
	//保存或更新字典信息
	@RequestMapping( params = {"cmd=dictionaryEdit"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String dictionaryEdit(@Valid @ModelAttribute("dictionary") Dictionary dictionary, BindingResult errors) throws IOException {
		if(errors.hasErrors()) {
			//do something...
        }
		dictionary.setIsDel(1);//默认可用
		dictionary.setDictionaryType(0);//默认业务字典
		dictionaryServiceImpl.edit(dictionary, dictionary.getDictionaryId());
		return "字典编辑完成";
	}
	
	//删除
	@RequestMapping( params = {"cmd=deleteDictionary"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String deleteDictionary(String ids) throws IOException{
		dictionaryServiceImpl.delete(ids);
		return "字典删除完成";
	}
	
	//校验Code 判重
	@RequestMapping( params = {"cmd=checkCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkCode(String dictionaryCode) throws IOException{
		Dictionary d = new Dictionary();
		d.setDictionaryCode(dictionaryCode);
		int count = dictionaryServiceImpl.countByCondition(d);
		return count>0?false:true;
	}
	
	
	/**
	 * 加载承包商编辑页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=loadDicByCode"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Dictionary> loadDicByCode(String dicCode) throws IOException{
		 return dictionaryServiceImpl.loadDicByCode(dicCode);
	}
}
