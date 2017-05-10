package com.dkd.emms.web.purchase.agreement;

/**
* Created by Administrator on 2017/2/24.3333
*/

import com.dkd.emms.core.util.JsonUtil;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.service.*;
import com.dkd.emms.systemManage.service.AgreementService;
import com.dkd.emms.web.purchase.agreement.queryCondition.AgreementCondition;
import com.dkd.emms.web.purchase.agreement.queryCondition.AgreementDetailCondition;
import com.dkd.emms.web.purchase.agreement.queryCondition.AgreementSupplierCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/purchase/agreement.do")
@SessionAttributes("currentUser")
public class AgreementController {
    @Autowired
    private AgreementService agreementService;
    @Autowired
    private AgreementDetailService agreementDetailService;
    @Autowired
    private AgreementSupplierService agreementSupplierService;
    @Autowired
    private AgreementService organizationService;

    /**
     * 加载框架协议查询页面
     * @return
     */
    @RequestMapping( params = {"cmd=query"},method = RequestMethod.GET)
    public String query(){return "purchase/agreement/query";}


    /**
     * 加载框架协议数据
     * @return
     */
    @RequestMapping( params ={"cmd=loadAgreementListData"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public PageBean<Agreement> loadAgreementListData(@RequestParam(value = "page") Integer start,@RequestParam(value = "rows") Integer length,
                                                     AgreementCondition agreementCondition){
        PageBean<Agreement> pageBean = new PageBean<Agreement>();
        pageBean.setTotal(agreementService.countByCondition(agreementCondition));
        pageBean.setRows(agreementService.selectByCondition(agreementCondition,pageBean.getTotal(),start,length));
        return pageBean;
    }
    /**
     *删除框架协议数据
     *
     * @param
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping( params = {"cmd=deleteAgreement"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String deleteAgreement(String agreementId) throws IOException {
        agreementService.delete(agreementId);
        agreementDetailService.delete(agreementId);
        agreementSupplierService.delete(agreementId);
        return "删除完成";

    }
    /**
     * 提交框架协议数据
     */
    @RequestMapping( params = {"cmd=updateAgreementState"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String updateAgreementState(String agreementId,String state) throws IOException {
        Agreement agreement = agreementService.selectByPk(agreementId);
        agreement.setAgreementState(state);
        agreementService.update(agreement);
        return "提交成功";
    }
    /**
     * 跳转框架协议编辑页面
     * @return
     */
    @RequestMapping( params = {"cmd=queryAgreementDetail"},produces = "text/html",method = RequestMethod.GET)
    public String queryAgreementDetail(String agreementId,ModelMap model){
        model.addAttribute("agreementId", agreementId);
        return "purchase/agreement/edit";
    }
    /**
     * 查看
     * @return
     */
    @RequestMapping( params = {"cmd=view"},produces = "text/html",method = RequestMethod.GET)
    public String view(String agreementId, ModelMap model){
        model.addAttribute("agreementId", agreementId);
        return "purchase/agreement/view";
    }
    /**
     * 框架协议编辑页面加载数据
     *
     */
    @RequestMapping( params = {"cmd=agreementDetail"},produces = "application/json",method = RequestMethod.GET)
    @ResponseBody
    public Agreement agreementDetail(String agreementId) {
        Agreement agreement = new Agreement();
        AgreementDetailCondition agreementDetailCondition = new AgreementDetailCondition();
        AgreementSupplierCondition agreementSupplierCondition = new AgreementSupplierCondition();
        if(StringUtils.isNotEmpty(agreementId)){
            agreement = agreementService.selectByPk(agreementId);
            agreementDetailCondition.setAgreementId(agreementId);
            agreementSupplierCondition.setSourceId(agreementId);
            List<AgreementDetail> list = agreementDetailService.selectByCondition( agreementDetailCondition,0,1,-1);
            List<AgreementSupplier> list1 = agreementSupplierService.selectByCondition( agreementSupplierCondition,0,1,-1);
            agreement.setAgreementDetailList(list);
            agreement.setAgreementSupplierList(list1);
        }
        return agreement;
    }
    /**
     * 框架协议编辑页面保存
     *
     */
    @RequestMapping( params = {"cmd=saveAgreement"}, produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String saveAgreement( @ModelAttribute("currentUser")User user,@RequestBody Agreement agreement) throws IOException{
        agreementService.saveAgreement(agreement,user);
        return "保存成功";
    }


    /**
     * 物资明细弹出框
     *
     * @return
     */
    @RequestMapping(params = {"cmd=modal"}, method = RequestMethod.GET)
    public String modal() {
        return "baseinfo/materials/modalMaterials";
    }
    /**
     * 框架协议选择弹出框
     *
     * @return
     */
    @RequestMapping(params = {"cmd=modalagreement"}, method = RequestMethod.GET)
    public String modalagreement(String supplier,ModelMap model) {
        model.addAttribute("supplier", supplier);
        return "purchase/agreement/modalAgreement";
}


    /**
     * 加载物资列表数据
     * @return
     */
    @RequestMapping( params ={"cmd=loadAgreementDetailListData"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public PageBean<AgreementDetail> loadAgreementDetailListData(@RequestParam(value = "page") Integer start,@RequestParam(value = "rows")Integer length,
                                                                 AgreementDetailCondition agreementDetailCondition){
        PageBean<AgreementDetail> pageBean = new PageBean<AgreementDetail>();
        pageBean.setTotal(agreementDetailService.countByAgreementDetail(agreementDetailCondition));
        pageBean.setRows(agreementDetailService.selectByAgreementDetail(agreementDetailCondition,pageBean.getTotal(),start,length));
        return pageBean;
    }

    /**
     * 供应商弹出框
     *
     * @return
     */
    @RequestMapping(params = {"cmd=create"}, method = RequestMethod.GET)
    public String create() {
        return "purchase/agreement/modalAgreementSupplier";
    }
}
