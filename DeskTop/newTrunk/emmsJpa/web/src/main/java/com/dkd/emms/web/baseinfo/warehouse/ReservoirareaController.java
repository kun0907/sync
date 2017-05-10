/**
 * 
 */
package com.dkd.emms.web.baseinfo.warehouse;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.Reservoirarea;
import com.dkd.emms.systemManage.bo.WareHouse;
import com.dkd.emms.systemManage.service.ReservoirareaService;
import com.dkd.emms.systemManage.service.StoragelocationService;
import com.dkd.emms.systemManage.service.WareHouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dkd.emms.systemManage.bo.User;

/**
	 * @Title: ReservoirareaController
	 * @Description:
	 * @param
	 * @author:YUZH
	 * @data 2017年2月8日
	 */
@Controller
@RequestMapping(value="/baseinfo/reservoirarea.do")
@SessionAttributes("currentUser")
public class ReservoirareaController {
	@Autowired
	private WareHouseService wareHouseService;
	@Autowired
	private ReservoirareaService reservoirareaService;
	@Autowired
	private StoragelocationService storagelocationService;
	//跳转仓库查询页面
	@RequestMapping( params = {"cmd=queryreservoirareaInfo"},method = RequestMethod.GET)
	public String queryreservoirareaInfo(String checkedNodeId,String reservoirarea_id,ModelMap model){
		model.addAttribute("checkedNodeId", checkedNodeId);
		model.addAttribute("reservoirarea_id", reservoirarea_id);
		return "baseinfo/warehouse/editReservoirarea";
	}
	//跳转到物资类别编辑页面并获取相应数据
	@RequestMapping( params = {"cmd=reservoirareaInfo"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public Reservoirarea reservoirareaInfo(String checkedNodeId,String reservoirarea_id){
		Reservoirarea reservoirarea = new Reservoirarea();
		if(StringUtils.isNotEmpty(reservoirarea_id)){
			reservoirarea = reservoirareaService.selectByPk(reservoirarea_id);
			String [] code = reservoirarea.getReservoirareaCode().split("_");
			if(code.length==2){
				reservoirarea.setReservoirareaCode(code[1]);
			}
		}
		reservoirarea.setWarehouseId(checkedNodeId);
		return reservoirarea;
	}
	//保存仓库信息
	@RequestMapping( params = {"cmd=reservoirareaEdit"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String reservoirareaEdit(@ModelAttribute("currentUser")User user,Reservoirarea reservoirarea) throws IOException{
		WareHouse wareHouse = wareHouseService.selectByPk(reservoirarea.getWarehouseId());
		StringBuilder str =new StringBuilder();
		str.append(wareHouse.getWarehouseCode());
		str.append("_");
		str.append(reservoirarea.getReservoirareaCode());
		reservoirarea.setReservoirareaCode(str.toString());
		reservoirareaService.save(reservoirarea,user);
		return "保存成功";
	}
	/**
	 * 物理删除库区
	 * @param parentId
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping( params = {"cmd=deleteReservoirarea"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String deleteWareHouse(String id) throws IOException{
		reservoirareaService.delete(id);
		storagelocationService.deleteByResId(id);
		return "删除完成";
	}
	/**
	 * 校验是否重复
	 */
	@RequestMapping( params = {"cmd=checkStoNo"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public boolean checkStoNo(String reservoirareaCode,String parentId) throws IOException{
		WareHouse wareHouse = wareHouseService.selectByPk(parentId);
		String code = wareHouse.getWarehouseCode()+"_"+reservoirareaCode;
		return reservoirareaService.checkStoNo(code,null);
	}
}
