package com.dkd.emms.web.outstorage.tallying;


import com.dkd.emms.systemManage.bo.PickNotice;
import com.dkd.emms.systemManage.bo.Tallying;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.PickNoticeService;
import com.dkd.emms.systemManage.service.TallyingService;
import com.dkd.emms.web.outstorage.picknotice.queryCondition.PickNoticeCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * 理货管理Controller
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/outstorage/tallying.do")
@SessionAttributes("currentUser")
public class TallyingController {

	@Autowired
	private TallyingService tallyingService;

	/**
	 * 编辑领料信息(页面)
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=tally"},produces = "text/html",method = RequestMethod.GET)
	public String  editPickNoticePage() throws IOException{
		return "outstorage/tallying/edit";
	}
	/**
	 * 编辑领料查询
	 * @throws java.io.IOException
	 */
	@RequestMapping( params = {"cmd=query"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public List<Tallying> queryTallyList(@ModelAttribute("currentUser") User user){
		return tallyingService.queryTallyList();
	}

	/**
	 * 保存理货明细信息
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=saveTallying"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public String saveTallying(@RequestBody List<Tallying> tallyingList){
		tallyingService.saveTallying(tallyingList);
		return "true";
	}
	/**
	 * 删除理货明细
	 * @param tallyId
	 * @throws IOException
	 */
	@RequestMapping( params = {"cmd=delTally"},produces = "application/json",method = RequestMethod.GET)
	@ResponseBody
	public String delTally(String tallyId) throws IOException{
		tallyingService.delTally(tallyId);
		return "true";
	}
}
