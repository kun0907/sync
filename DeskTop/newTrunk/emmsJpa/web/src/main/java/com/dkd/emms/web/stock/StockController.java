package com.dkd.emms.web.stock;


import com.dkd.emms.systemManage.bo.Delivery;
import com.dkd.emms.systemManage.bo.Stock;
import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.systemManage.service.DeliveryService;
import com.dkd.emms.systemManage.service.StockService;
import com.dkd.emms.web.purchase.delivery.queryCondition.DeliveryCondition;
import com.dkd.emms.web.stock.queryCondition.StockCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 库存台账--库存明细查询
 * @author WANGQ
 *
 */
@Controller
@RequestMapping("/stock/stock.do")
public class StockController {

	@Autowired
	private StockService stockService;
	/**
	 * 加载承包商查询页面
	 * @return
	 */
	@RequestMapping( params = {"cmd=query"},method = RequestMethod.GET)
	public String query(){
		return "stock/stock/query";
	}

	/**
	 * 加载数据
	 * @return
	 */
	@RequestMapping( params = {"cmd=loadStockListData"},produces = "application/json",method = RequestMethod.POST)
	@ResponseBody
	public PageBean<Stock> loadContractorListData(@RequestParam(value = "page") int start,@RequestParam(value = "rows") int length,
													   StockCondition condition){
		PageBean<Stock> pageBean = new PageBean<Stock>();
		pageBean.setTotal(stockService.countByCondition(condition));
		pageBean.setRows(stockService.selectByCondition(condition,pageBean.getTotal(),start,length));
		return pageBean;
	}
}
