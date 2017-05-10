package com.dkd.emms.web.purchase.transportInfo;

import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.service.*;
import com.dkd.emms.web.instorage.materialInspect.queryCondition.MaterialInspectCondition;
import com.dkd.emms.web.purchase.transportInfo.queryCondition.JiGangCondition;
import com.dkd.emms.web.purchase.transportInfo.queryCondition.TransportInfoCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by YINXP on 2017/4/24.
 */
@Controller
@RequestMapping(value="/purchase/transportInfo.do")
@SessionAttributes("currentUser")
public class TransportInfoController {

    @Autowired
    private JiGangService jiGangService;

    @Autowired
    private JiGangTimeService jiGangTimeService;

    @Autowired
    private JiGangTripService jiGangTripService;

    @Autowired
    private TransportInfoService transportInfoService;

    @Autowired
    private TransportInfoDetailService transportInfoDetailService;

    @RequestMapping( params = {"cmd=query"},method = RequestMethod.GET)
    public String query(){  //集港查询页

        return "purchase/transportInfo/query";
    }

    @RequestMapping( params = {"cmd=queryTrans"},method = RequestMethod.GET)
    public String queryTrans(){  //运输单查询页
        return "purchase/transportInfo/queryTrans";
    }

    @RequestMapping( params = {"cmd=dialogJiGangTime"},method = RequestMethod.GET)
    public String jiGangDialogTime(){  //运输单查询页
        return "purchase/transportInfo/jiGangTimeDialog";
    }

    @RequestMapping( params = {"cmd=dialogJiGangShip"},method = RequestMethod.GET)
    public String jiGangDialogShip(){  //运输单查询页
        return "purchase/transportInfo/jiGangTripDialog";
    }

    /**
     * 编辑运输单
     *
     * @return
     */
    @RequestMapping(params = {"cmd=edit"}, method = RequestMethod.GET)
    public String modal() {
        return "purchase/transportInfo/editTrans";
    }

    /**
     * 加载集港信息数据
     * @return
     */
    @RequestMapping( params ={"cmd=loadJigangInfoListData"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public PageBean<JiGangInfo> loadJigangListData(@RequestParam(value = "page") Integer start,@RequestParam(value = "rows") Integer length,
                                                          JiGangCondition jiGangCondition){
        PageBean<JiGangInfo> pageBean = new PageBean<>();
        pageBean.setTotal(jiGangService.countByCondition(jiGangCondition));
        pageBean.setRows(jiGangService.selectByCondition(jiGangCondition,pageBean.getTotal(),start,length));
        return pageBean;
    }

    /**
     * 加载运输单数据
     * @return
     */
    @RequestMapping( params ={"cmd=loadTransportInfoListData"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public PageBean<TransportInfo> loadTransportInfoListData(@RequestParam(value = "page") Integer start,@RequestParam(value = "rows") Integer length,
                                                   TransportInfoCondition transportInfoCondition){
        PageBean<TransportInfo> pageBean = new PageBean<>();
        pageBean.setTotal(transportInfoService.countByCondition(transportInfoCondition));
        pageBean.setRows(transportInfoService.selectByCondition(transportInfoCondition, pageBean.getTotal(), start, length));
        return pageBean;
    }

    /**
     * 保存集港时间地点
     * @return
     */
    @RequestMapping( params ={"cmd=saveTime"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String saveTime(@RequestBody JiGangTime jiGangTime,@ModelAttribute("currentUser")User user){
        if(StringUtils.isEmpty(jiGangTime.getJiGangTimeId())){
            jiGangTime.setCreateTime(new Date());
            jiGangTime.setCreateUserId(user.getUserId());
            if(null == user.getEmployee()){
                jiGangTime.setCreateUserName(user.getUserName());
            }else{
                jiGangTime.setCreateUserName(user.getEmployee().getEmpName());
            }
        }
        jiGangTimeService.insert(jiGangTime);
        return "保存成功";
    }

    /**
     * 保存集港船名航次
     * @return
     */
    @RequestMapping( params ={"cmd=saveShip"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String saveShip(@RequestBody JiGangTrip jiGangTrip,TransportInfo transportInfo,@ModelAttribute("currentUser")User user){
        if(StringUtils.isEmpty(jiGangTrip.getJiGangTripId())){
            jiGangTrip.setCreateTime(new Date());
            jiGangTrip.setCreateUserId(user.getUserId());
            if(null == user.getEmployee()){
                jiGangTrip.setCreateUserName(user.getUserName());
            }else{
                jiGangTrip.setCreateUserName(user.getEmployee().getEmpName());
            }
        }
        jiGangTripService.saveJiGangTrip(jiGangTrip,transportInfo,user);
        return "保存成功";
    }

    /**
     * 删除
     */
    @RequestMapping( params = {"cmd=delete"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String deleteMaterialInspect(String transportInfoId) throws IOException {
//        materialInspectService.delete(materiaInspectId);
//        qualityInspectDetailService.delete(materiaInspectId);
        return "删除完成";
    }

    /*
    * 保存运输单日期
    * */
    @RequestMapping( params ={"cmd=saveDate"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String saveDate(@RequestBody TransportInfoDetail transportInfoDetail,@ModelAttribute("currentUser")User user){
        if(StringUtils.isEmpty(transportInfoDetail.getTransportInfoId())){
            transportInfoDetail.setCreateTime(new Date());
            transportInfoDetail.setCreateUserId(user.getUserId());
            if(null == user.getEmployee()){
                transportInfoDetail.setCreateUserName(user.getUserName());
            }else{
                transportInfoDetail.setCreateUserName(user.getEmployee().getEmpName());
            }
            transportInfoDetailService.insert(transportInfoDetail);
        }
        transportInfoDetailService.update(transportInfoDetail);
        return "保存成功";
    }

}
