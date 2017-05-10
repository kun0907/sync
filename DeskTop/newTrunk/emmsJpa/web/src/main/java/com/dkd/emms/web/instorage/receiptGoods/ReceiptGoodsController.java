package com.dkd.emms.web.instorage.receiptGoods;

import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.service.ReceiptGoodsService;
import com.dkd.emms.systemManage.service.ReceiptPackingDetailService;
import com.dkd.emms.systemManage.service.ReceiptPackingService;
import com.dkd.emms.web.instorage.receiptGoods.queryCondition.ReceiptGoodsCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YUZH on 2017/3/6.
 */
@Controller
@RequestMapping(value="/instorage/receiptGoods.do")
@SessionAttributes("currentUser")
public class ReceiptGoodsController {
    @Autowired
    private ReceiptGoodsService receiptGoodsService;
    @Autowired
    private ReceiptPackingService receiptPackingService;
    @Autowired
    private ReceiptPackingDetailService receiptPackingDetailService;
    /**
     * 加载收货单查询页面
     * @return
     */
    @RequestMapping( params = {"cmd=query"},produces = "text/html",method = RequestMethod.GET)
    public String query(){
        return "instorage/receiptGoods/query";
    }
    /**
     * 加载直达现场查询页面
     * @return
     */
    @RequestMapping( params = {"cmd=directQuery"},produces = "text/html",method = RequestMethod.GET)
    public String directQuery(){
        return "instorage/direct/query";
    }
    /**
     * 加载采购单收货查询页面
     * @return
     */
    @RequestMapping( params = {"cmd=orderQuery"},produces = "text/html",method = RequestMethod.GET)
    public String orderQuery(){
        return "instorage/receiptGoods/orderQuery";
    }
    /**
     * 加载发货单数据
     */
    @RequestMapping( params = {"cmd=loadReceiptListData"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public PageBean<ReceiptGoods> loadReceiptListData(@RequestParam(value = "page") Integer start,
                                             @RequestParam(value = "rows") Integer length,ReceiptGoodsCondition receiptGoodsCondition)
    {
        PageBean<ReceiptGoods> pageBean = new PageBean<ReceiptGoods>();
        pageBean.setTotal(receiptGoodsService.countByCondition(receiptGoodsCondition));
        pageBean.setRows(receiptGoodsService.selectByCondition(receiptGoodsCondition, pageBean.getTotal(), start, length));
        return pageBean;
    }
    /**
     * 编辑
     * @return
     */
    @RequestMapping( params = {"cmd=edit"},produces = "text/html",method = RequestMethod.GET)
    public String edit(String receiptId,String materiaReceiptStuate, String receiptType,String inStorage , ModelMap model){
        model.addAttribute("receiptId", receiptId);
        model.addAttribute("materiaReceiptStuate",materiaReceiptStuate);
        if(receiptType.equals("delivery")&& inStorage.equals("receipt")){ //有发货单收货
            return "instorage/receiptGoods/edit";
        }else if(receiptType.equals("order")&& inStorage.equals("receipt")){//无发货单收货
            return "instorage/receiptGoods/orderEdit";
        }else if(receiptType.equals("delivery")&& inStorage.equals("direct")){//无发货单现场直达
            return "instorage/direct/edit";
        }else if(receiptType.equals("order")&& inStorage.equals("direct")){//无发货单直达现场
            return "instorage/direct/orderEdit";
        }
        return "";
    }
    //编辑页面加载数据
    @RequestMapping( params = {"cmd=loadReceiptGoodsData"},produces = "application/json",method = RequestMethod.GET)
    @ResponseBody
    public ReceiptGoods loadReceiptGoodsData(String receiptId){
        ReceiptGoods receiptGoods = new ReceiptGoods();
        if(StringUtils.isNotEmpty(receiptId)){
            receiptGoods = receiptGoodsService.selectByPk(receiptId);
            List<ReceiptPacking> receiptPackingList = receiptPackingService.selectByReceiptId(receiptId);
            receiptGoods.setReceiptPackingList(receiptPackingList);
            List<ReceiptPackingDetail> detailList = receiptPackingDetailService.selectByReceiptId(receiptId);
            receiptGoods.setDetailList(detailList);
        }
        return receiptGoods;
    }
    /**
     * 保存
     */
    @RequestMapping( params = {"cmd=save"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String save(@ModelAttribute("currentUser")User user,@RequestBody ReceiptGoods receiptGoods) throws IOException {
        if(StringUtils.isEmpty(receiptGoods.getReceiptId())){
            receiptGoods.setCreateTime(new Date());
            receiptGoods.setCreateUserId(user.getUserId());
            if(null == user.getEmployee()){
                receiptGoods.setCreateUserName(user.getUserName());
            }else{
                receiptGoods.setCreateUserName(user.getEmployee().getEmpName());
            }
        }
        receiptGoodsService.saveReceiptGoods(receiptGoods);
        return "true";
    }
    /**
     * 查看
     * @return
     */
    @RequestMapping( params = {"cmd=view"},produces = "text/html",method = RequestMethod.GET)
    public String view(String receiptId,String receiptType,String inStorage, ModelMap model){
        model.addAttribute("receiptId", receiptId);
        if(receiptType.equals("delivery")&& inStorage.equals("receipt")){ //有发货单收货
            return "instorage/receiptGoods/view";
        }else if(receiptType.equals("order")&& inStorage.equals("receipt")){//无发货单收货
            return "instorage/receiptGoods/orderView";
        }else if(receiptType.equals("delivery")&& inStorage.equals("direct")){//无发货单现场直达
            return "instorage/direct/view";
        }else if(receiptType.equals("order")&& inStorage.equals("direct")){//无发货单直达现场
            return "instorage/direct/orderView";
        }
        return "";
    }
    /**
     * 删除收货单
     */
    @RequestMapping( params = {"cmd=deleteReceiptGoods"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String deleteWareHouse(String receiptId) throws IOException {
        receiptGoodsService.delete(receiptId);
        receiptPackingService.delete(receiptId);
        receiptPackingDetailService.delete(receiptId);
        return "删除完成";
    }
    /**************************************物资明细弹出框*************************************/
    /**
     * 收货单公共弹出框
     *
     * @return
     */
    @RequestMapping(params = {"cmd=dialogReceiptGoods"}, method = RequestMethod.GET)
    public String dialogReceiptGoods(String dataSource,String supplierId, ModelMap model) {
        model.addAttribute("supplierId", supplierId);
        model.addAttribute("dataSource", dataSource);
        return "instorage/receiptGoods/dialogReceiptGoods";
    }
    /**
     * 加载采购订单明细数据
     */
   @RequestMapping( params = {"cmd=loadReceiptDetailListData"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public PageBean<ReceiptPackingDetail> loadReceiptDetailListData(@RequestParam(value = "page",required = false) Integer start,
                                                         @RequestParam(value = "rows",required = false) Integer length,ReceiptGoodsCondition receiptGoodsCondition)
    {
        PageBean<ReceiptPackingDetail> pageBean = new PageBean<ReceiptPackingDetail>();
        List<ReceiptPackingDetail> receipt = new ArrayList<ReceiptPackingDetail>();
        List<ReceiptPackingDetail> deliveryList = receiptPackingDetailService.selectByCondition(receiptGoodsCondition, pageBean.getTotal(), start, length);
        List<ReceiptPackingDetail> orderList = receiptPackingDetailService.selectOrderByCondition(receiptGoodsCondition, pageBean.getTotal(), start, length);
        receipt.addAll(deliveryList);
        receipt.addAll(orderList);
        pageBean.setRows(receipt);
        return pageBean;
    }
}
