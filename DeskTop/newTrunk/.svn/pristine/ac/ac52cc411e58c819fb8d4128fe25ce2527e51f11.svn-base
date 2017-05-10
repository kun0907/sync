package com.dkd.emms.web.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import com.dkd.emms.systemManage.bo.User;
import com.dkd.emms.web.system.queryCondition.ResourceCondition;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dkd.emms.core.util.page.Page;
import com.dkd.emms.systemManage.bo.Resource;
import com.dkd.emms.systemManage.service.ResourceService;
import com.dkd.emms.systemManage.service.RoleService;
import com.dkd.emms.web.util.page.PageBean;


/**
 * 权限controller
 *
 * @author WANGQ
 */

@Controller
@RequestMapping(value = "/system/resource.do")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleService roleServiceImpl;

    /**
     * frame
     *
     * @return 初始化Frame
     */
    @RequestMapping(params = {"cmd=resourceFrame"}, produces = "text/html", method = RequestMethod.GET)
    public String resource(ModelMap model) {
        model.addAttribute("westFrameUrl", "system/resource.do?cmd=resourceTree");
        model.addAttribute("centerFrameUrl", "system/resource.do?cmd=query&parentId=0");
        return "system/common/frame";
    }

    /**
     * tree
     *
     * @return 初始化树界面
     */

    @RequestMapping(params = {"cmd=resourceTree"}, produces = "text/html", method = RequestMethod.GET)
    public String resourceTree(ModelMap model) {
        model.addAttribute("initTreeUrl", "system/resource.do?cmd=initTree");
        model.addAttribute("navUrl", "system/resource.do?cmd=query&parentId=");
        return "system/common/tree";
    }

    /**
     * tree初始化
     *
     * @throws Exception 获取整棵权限树数据
     */

    @RequestMapping(params = {"cmd=initTree"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public List<Resource> initTree() throws Exception {
        List<Resource> list = resourceService.selectAll();
        for (Resource resource : list) {
            resource.setId(resource.getResourceId());
            resource.setName(resource.getResourceName());
        }
        return list;
    }

    /**
     * 查询节点下的权限
     *
     * @param parentId
     * @param model
     * @return 查询以选择节点的ID作为parentId的全部节点
     */

    @RequestMapping(params = {"cmd=query"}, produces = "text/html", method = RequestMethod.GET)
    public String selectResourceByParentId(ModelMap model, String parentId) {
        model.addAttribute("parentId", parentId);
        return "system/resource/query";
    }


    /**
     * 角色分页查询(数据)
     *
     * @return
     */
    @RequestMapping(params = {"cmd=loadResourceListData"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public PageBean<Resource> loadRoleListData(@RequestParam(value = "page", required = false) Integer start, @RequestParam(value = "rows", required = false) Integer length,
                                               ResourceCondition condition) {
        PageBean<Resource> pageBean = new PageBean<Resource>();
        pageBean.setTotal(resourceService.countByCondition(condition));
        pageBean.setRows(resourceService.selectByCondition(condition, pageBean.getTotal(), start, length));
        return pageBean;
    }

    /**
     * 权限编辑页面
     *
     * @param resourceId
     * @param model
     * @return
     */
    @RequestMapping(params = {"cmd=editResourcePage"}, produces = "text/html", method = RequestMethod.GET)
    public String editResourcePage(String resourceId, String parentId, ModelMap model) {
        model.put("resourceId", resourceId);
        model.put("parentId", parentId);
        return "system/resource/edit";
    }

    /**
     * 加载权限编辑页面数据
     *
     * @param resourceId
     * @return
     */
    @RequestMapping(params = {"cmd=editResource"}, produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Resource editResource(String resourceId) {
        return resourceService.editResource(resourceId);
    }


    /**
     * 校验编码是否重复
     *
     * @throws IOException
     */
    @RequestMapping(params = {"cmd=checkCode"}, produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkCode(String resourceCode) {
        return resourceService.checkCode(resourceCode);
    }


    /**
     * 物理删除权限
     *
     * @param resourceId
     * @param model
     * @return
     */

    @RequestMapping(params = {"cmd=deleteResource"}, produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public String deleteResource(String resourceId, ModelMap model) {
        resourceService.deleteResource(resourceId);
        return "true";
    }


    /**
     * 保存主项目信息
     * @param resource
     * @throws IOException
     */
    @RequestMapping( params = {"cmd=saveResource"},produces = "text/html ;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public String  saveResource(Resource resource) throws IOException{
        resourceService.saveResource(resource);
        return "true";
    }
}
