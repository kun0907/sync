package com.dkd.emms.web.system;

import com.dkd.emms.core.exception.BusinessException;
import com.dkd.emms.systemManage.bo.*;
import com.dkd.emms.systemManage.bo.Process;
import com.dkd.emms.systemManage.service.ProcessDetailService;
import com.dkd.emms.systemManage.service.ProcessRecordService;
import com.dkd.emms.systemManage.service.ProcessService;
import com.dkd.emms.web.system.queryCondition.ProcessCondition;
import com.dkd.emms.web.util.page.PageBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUZH on 2017/4/25.
 */
@Controller
@RequestMapping(value = "/system/process.do")
@SessionAttributes("currentUser")
public class ProcessController {
    @Autowired
    private ProcessService processService;
    @Autowired
    private ProcessDetailService processDetailService;
    @Autowired
    private ProcessRecordService processRecordService;
    /**
     * frame
     */
    @RequestMapping(params = {"cmd=processFrame"}, produces = "text/html", method = RequestMethod.GET)
    public String process(ModelMap model) {
        model.addAttribute("westFrameUrl", "system/process.do?cmd=processTree");
        model.addAttribute("centerFrameUrl", "system/process.do?cmd=query&parentId=0");
        return "system/common/frame";
    }
    /**
     * tree
     * @return 初始化树界面
     */
    @RequestMapping(params = {"cmd=processTree"}, produces = "text/html", method = RequestMethod.GET)
    public String processTree(ModelMap model) {
        model.addAttribute("initTreeUrl", "system/process.do?cmd=initTree");
        model.addAttribute("navUrl", "system/process.do?cmd=judgeNodeType&parentId=");
        return "system/common/tree";
    }
    /**
     * tree初始化
     * @throws Exception 获取整棵权限树数据
     */
    @RequestMapping(params = {"cmd=initTree"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public List<Process> initTree(){
        List<Process> list = processService.selectAll();
        for (Process process : list) {
            process.setId(process.getProcessId());
            process.setName(process.getProcessName());
            process.setParentId("0");
            process.setLevel("1");
        }
        return list;
    }
    /**
     * 查询节点下的权限
     * @param parentId
     * @param model
     * @return 查询以选择节点的ID作为parentId的全部节点
     */

    @RequestMapping(params = {"cmd=query"}, produces = "text/html", method = RequestMethod.GET)
    public String query(ModelMap model, String parentId) {
        model.addAttribute("parentId", parentId);
        return "system/process/query";
    }
    @RequestMapping(params = {"cmd=loadProcessListData"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<Process> loadProcessListData(@ModelAttribute("currentUser")User user, @RequestParam(value = "page") int start,@RequestParam(value = "rows") int length) {
        PageBean<Process> pageBean = new PageBean<Process>();
        ProcessCondition processCondition = new ProcessCondition();
        pageBean.setTotal(processService.countByCondition(processCondition));
        pageBean.setRows(processService.selectByCondition(processCondition,pageBean.getTotal(),start,length));
        return pageBean;
    }
    //判断该节点的类型，给出对应的跳转页
    @RequestMapping( params = {"cmd=judgeNodeType"},produces = "text/html",method = RequestMethod.GET)
    public String judgeNodeType(String selfId,String level,  ModelMap model){
        if(selfId!=null && selfId.equals("0")){	//如果是根节点
            return "redirect:process.do?cmd=query";
        } else if(selfId!=null && level.equals("1")){
            return "redirect:process.do?cmd=queryDetail&parentId="+ selfId;
        }else{
            return "redirect:process.do?cmd=query";
        }
    }
    //跳转编辑页面
    @RequestMapping( params = {"cmd=edit"},method = RequestMethod.GET)
    public String edit(String processId, ModelMap model){
        model.addAttribute("processId", processId);
        return "system/process/edit";
    }
    //跳转到编辑页面并获取相应数据
    @RequestMapping( params = {"cmd=loadProcessData"},produces = "application/json",method = RequestMethod.GET)
    @ResponseBody
    public Process loadProcessData(String processId){
        Process process = new Process();
        if(StringUtils.isNotEmpty(processId)){
            process = processService.selectByPk(processId);
        }
        return process;
    }
    //保存流程信息
    @RequestMapping( params = {"cmd=save"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public String save(@ModelAttribute("currentUser")User user ,Process process) throws IOException{
        processService.save(process, user);
        return "true";
    }
    /**
     * 物理删除流程定义
     */
    @RequestMapping( params = {"cmd=deleteProcess"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String deleteProcess(String id) throws IOException {
        processService.delete(id);
        processDetailService.deleteByParentId(id);
        return "true";
    }
    @RequestMapping(params = {"cmd=queryDetail"}, produces = "text/html", method = RequestMethod.GET)
    public String queryDetail(ModelMap model, String parentId) {
        model.addAttribute("parentId", parentId);
        return "system/process/queryDetail";
    }
    @RequestMapping(params = {"cmd=loadDetailListData"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<ProcessDetail> loadDetailListData(@RequestParam(value = "page") int start,@RequestParam(value = "rows") int length,String parentId) {
        PageBean<ProcessDetail> pageBean = new PageBean<ProcessDetail>();
        ProcessCondition processCondition = new ProcessCondition();
        processCondition.setProcessId(parentId);
        pageBean.setTotal(processDetailService.countByCondition(processCondition));
        pageBean.setRows(processDetailService.selectByCondition(processCondition, pageBean.getTotal(), start, length));
        return pageBean;
    }
    //跳转编辑页面
    @RequestMapping( params = {"cmd=editDetail"},method = RequestMethod.GET)
    public String editDetail(String parentId,String processDetailId, ModelMap model){
        model.addAttribute("parentId", parentId);
        model.addAttribute("processDetailId", processDetailId);
        return "system/process/editDetail";
    }
    //跳转到编辑页面并获取相应数据
    @RequestMapping( params = {"cmd=loadDetailData"},produces = "application/json",method = RequestMethod.GET)
    @ResponseBody
    public ProcessDetail loadDetailData(@ModelAttribute("currentUser")User user,String parentId,String processDetailId, ModelMap model){
        ProcessCondition processCondition = new ProcessCondition();
        processCondition.setProcessId(parentId);
        int count = processDetailService.countByCondition(processCondition);
        ProcessDetail detail = new ProcessDetail();
        if(count==0){
            detail.setProcessDetailLevel("1级审批");
        }else{
            count = count+1;
            detail.setProcessDetailLevel(count + "级审批");
        }
        if(StringUtils.isNotEmpty(processDetailId)){
            detail = processDetailService.selectByPk(processDetailId);
        }
        if(StringUtils.equals(user.getIsSysadmin(), "1")){
            model.addAttribute("userId",user.getUserId());
        }else{
            model.addAttribute("orgId", user.getEmployee().getOrganization().getOrgId());
            model.addAttribute("userId",user.getUserId());
        }
        return detail;
    }
    //保存审批信息
    @RequestMapping( params = {"cmd=saveDetail"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public String saveDetail(@ModelAttribute("currentUser")User user ,ProcessDetail processDetail,String parentId,ModelMap model) throws IOException{
        ProcessCondition processCondition = new ProcessCondition();
        processCondition.setProcessId(parentId);
        processCondition.setRoleId(processDetail.getRoleId());
        int count = processDetailService.countByCondition(processCondition);
        if(StringUtils.isEmpty(processDetail.getProcessDetailId())&& count>0 || StringUtils.isNotEmpty(processDetail.getProcessDetailId())&& count>1){
            throw new BusinessException("该流程中角色审批重复");
        }else{
            processDetailService.save(processDetail, user,parentId);
        }
        model.addAttribute("parentId", parentId);
        return "true";
    }
    /**
     * 物理删除审批
     */
    @RequestMapping( params = {"cmd=deleteDetail"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDetail(String id) throws IOException {
        processDetailService.delete(id);
        return "true";
    }
    /**
     * 查看单据审批记录
     */
    @RequestMapping( params = {"cmd=approveRecord"},method = RequestMethod.GET)
    public String approveRecord(String id,ModelMap model) {
        model.addAttribute("id", id);
        return "system/process/recordView";

    }
    /**
     * 查看单据审批记录
     */
    @RequestMapping( params = {"cmd=loadApproveRecord"},produces = "application/json",method = RequestMethod.POST)
    @ResponseBody
    public List<ProcessRecordDetail> loadApproveRecord(String id) throws IOException {
        return  processRecordService.selectByEntityId(id).getProcessRecordDetailList();

    }
}
