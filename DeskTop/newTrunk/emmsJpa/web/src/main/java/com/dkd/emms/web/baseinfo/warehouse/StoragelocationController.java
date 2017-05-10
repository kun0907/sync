/**
 * 
 */
package com.dkd.emms.web.baseinfo.warehouse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.Reservoirarea;
import com.dkd.emms.systemManage.bo.Storagelocation;
import com.dkd.emms.systemManage.bo.WareHouse;
import com.dkd.emms.systemManage.service.ReservoirareaService;
import com.dkd.emms.systemManage.service.StoragelocationService;
import com.dkd.emms.systemManage.service.WareHouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.systemManage.bo.User;

/**
	 * @Title: StoragelocationController
	 * @Description:
	 * @param 
	 * @author:YUZH 
	 * @data 2017年2月8日
	 */
@Controller
@RequestMapping(value="/baseinfo/storagelocation.do")
@SessionAttributes("currentUser")
public class StoragelocationController {
	@Autowired
	private WareHouseService wareHouseService;
	@Autowired
	private ReservoirareaService reservoirareaService;
	@Autowired
	private StoragelocationService storagelocationService;
	//跳转储位编辑页面
	@RequestMapping( params = {"cmd=queryStoragelocationInfo"},method = RequestMethod.GET)
	public String queryStoragelocationInfo(String checkedNodeId,String storagelocation_id,ModelMap model){
		model.addAttribute("checkedNodeId", checkedNodeId);
		model.addAttribute("storagelocation_id", storagelocation_id);
		return "baseinfo/warehouse/editStoragelocation";
	}
	//跳转批量添加储位页面
	@RequestMapping( params = {"cmd=addBatchStoragelocation"},method = RequestMethod.GET)
	public String addBatchStoragelocation(String checkedNodeId,String storagelocation_id,ModelMap model){
		model.addAttribute("checkedNodeId", checkedNodeId);
		model.addAttribute("storagelocation_id", storagelocation_id);
		return "baseinfo/warehouse/editBatchStoragelocation";
	}
	//储位编辑页面获取相应数据
	@RequestMapping( params = {"cmd=storagelocationInfo"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Storagelocation storagelocationInfo(String checkedNodeId,String storagelocation_id){
		Reservoirarea reservoirarea = reservoirareaService.selectByPk(checkedNodeId);
		Storagelocation storagelocation = new Storagelocation();
		if(StringUtils.isNotEmpty(storagelocation_id)){
			storagelocation = storagelocationService.selectByPk(storagelocation_id);
			String [] code = storagelocation.getStoragelocationCode().split("_");
			if(code.length==3){
				storagelocation.setStoragelocationCode(code[2]);
			}
		}
		storagelocation.setWarehouseId(reservoirarea.getWarehouseId());
		storagelocation.setReservoirareaId(reservoirarea.getReservoirareaId());
		return storagelocation;
	}
	//保存储位信息
	@RequestMapping( params = {"cmd=storagelocationEdit"},produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String storagelocationEdit(@ModelAttribute("currentUser")User user,Storagelocation storagelocation) throws IOException{
		WareHouse wareHouse = wareHouseService.selectByPk(storagelocation.getWarehouseId());
		Reservoirarea reservoirarea = reservoirareaService.selectByPk(storagelocation.getReservoirareaId());
		String storagelocationCode = storagelocationService.pingjieCode(wareHouse.getWarehouseCode(), reservoirarea.getReservoirareaCode(),storagelocation.getStoragelocationCode());
		storagelocation.setStoragelocationCode(storagelocationCode);
		storagelocationService.save(storagelocation,user);
		return "保存成功";
	}
	//批量保存储位信息
	@RequestMapping( params = {"cmd=saveBatchStoragelocation"},produces = "text/html; charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String saveBatchStoragelocation(@ModelAttribute("currentUser")User user,Storagelocation storagelocation) throws IOException, IllegalAccessException, InvocationTargetException{
		Set <String> set = new HashSet<String>(); 
		List <Storagelocation> list = new ArrayList<Storagelocation>(); 
		WareHouse wareHouse = wareHouseService.selectByPk(storagelocation.getWarehouseId());
		Reservoirarea reservoirarea = reservoirareaService.selectByPk(storagelocation.getReservoirareaId());
		String[] codes = storagelocation.getStoragelocationCode().split("\r\n");
		String regex = "^[0-9a-zA-Z-]{0,32}$";
		for(String code :codes){
			String storagelocationCode = storagelocationService.pingjieCode(wareHouse.getWarehouseCode(), reservoirarea.getReservoirareaCode(),code.trim());
			if(code.trim().length()>32){
				throw new BusinessException("储位编码长度必须介于0-20");
			}else if(code.indexOf(" ") != -1){
				throw new BusinessException("储位编码不能有空格");
			}else if(code.equals("")){
				continue;
			}else if(!Pattern.matches(regex, code.trim())){
				throw new BusinessException("储位编码只能输入数字、字母、中划线");
			}else if(!storagelocationService.checkStoNo(storagelocationCode,null)){
				throw new BusinessException(code+" 编码重复");
			}else{
				Storagelocation storage = storagelocationService.setDeFault(storagelocationCode,storagelocation,user);
				Boolean flag = set.add(code);
				if(flag){
					set.add(code);
					list.add(storage);
				}
			}
		}
		storagelocationService.saveBatchStoragelocation(list);
		return "保存成功";
	}
	/**
	 * 物理删除库区
	 * @param parentId
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=deleteStoragelocation"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String deleteWareHouse(String parentId,String id) throws IOException{
		storagelocationService.delete(id);
		return "删除完成";
	}
	/**
	 * 校验是否重复
	 */
	@RequestMapping( params = {"cmd=checkStoNo"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkStoNo(String storagelocationCode,String parentId) throws IOException{
		Reservoirarea reservoirarea = reservoirareaService.selectByPk(parentId);
		String code = reservoirarea.getReservoirareaCode()+"_"+storagelocationCode;
		return storagelocationService.checkStoNo(code,null);
	}	
}
