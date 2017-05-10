package com.dkd.emms.web.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dkd.emms.systemManage.bo.Resource;
import com.dkd.emms.systemManage.bo.UserRole;
import com.dkd.emms.web.security.ShiroRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dkd.emms.core.util.menu.MenuBean;
import com.dkd.emms.core.util.menu.SysMenu;
import com.dkd.emms.systemManage.bo.User;



/**
 * 首页框架页面控制器
 * @author SY
 *
 */

@Controller
@RequestMapping(value="/jsp")
@SessionAttributes("currentUser")
public class JspController {
	
	@Autowired
	private MenuBean menuBean; 

	/**
	 * 登录成功
	 * @return
	 */
	
	@RequestMapping("/loginSuccess.do")  
	public ModelAndView loginSuccess(User user){
		Subject subject = SecurityUtils.getSubject();
		String errorMessage = "";
		if(!subject.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
			token.setRememberMe(true);
			try {
				subject.login(token);
				return new ModelAndView(new RedirectView("index.do"));
			} catch (UnknownAccountException uae) {
				errorMessage = "用户名不存在";
				uae.printStackTrace();
			} catch (IncorrectCredentialsException ice) {
				errorMessage = "用户名密码错误";
				ice.printStackTrace();
			} catch (LockedAccountException lae) {
				errorMessage = "用户已锁定";
				lae.printStackTrace();
			} catch (ExcessiveAttemptsException eae) {
				errorMessage = "其它错误";
				eae.printStackTrace();
			} catch (AuthenticationException ae) {
				errorMessage = "其它错误:" + ae.getMessage();
				ae.printStackTrace();
				//unexpected error?
			}
		}
		return new ModelAndView("login", "errorMessage", errorMessage);
	}

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(){
		return"login";
	}


	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index.do")  
	public ModelAndView index(@ModelAttribute("currentUser")User user){
		return new ModelAndView("index","user",user);
	}
	
	/**
	 * 页面头部
	 * @return
	 */
	
	@RequestMapping("/header.do")  
	public ModelAndView header(@ModelAttribute("currentUser")User user){
		return new ModelAndView("index/header","user",user);
	}
	
	/**
	 * welcome
	 * @return
	 */
	
	@RequestMapping("/welcome.do")  
	public ModelAndView welcome(ModelMap model){
		return new ModelAndView("index/welcome",model);
	}
	/**
	 * 左侧菜单树
	 * @param model
	 * @return
	 */
	@RequestMapping("/menu.do")  
	public ModelAndView menu(Model model,@ModelAttribute("currentUser")User user){
		List<SysMenu> list = menuBean.getSysMenuList();//加载所有的用户菜单
		List<SysMenu> userMenu = new ArrayList<SysMenu>();//为当前用户创建一个菜单集合
		Set<String> menu = new HashSet<String>();
		if(!StringUtils.equals(user.getIsSysadmin(),"1")){
			for(UserRole userRole:user.getUserRoleList()){
				for(SysMenu sm : list){
					if(userRole.getResource().getResourceCode().equals(sm.getAuthority())){//用户拥有该菜单权限 添加至userMenu
						if(menu.add(sm.getAuthority())){
							userMenu.add(sm);
						}
					}
				}
			}
			model.addAttribute("sysMenuList", menuSort(userMenu));
		}else{
			model.addAttribute("sysMenuList", list);
		}
		return new ModelAndView("index/menu");
	}
	
	/**
	 * 菜单排序
	 * @param list
	 * @return
	 */
	
	private List<SysMenu> menuSort(List<SysMenu> list) { // 交换排序->冒泡排序  
		SysMenu temp = null;  
        boolean exchange = false;  
        for (int i = 0; i < list.size(); i++) {  
            exchange = false;  
            for (int j = list.size() - 2; j >= i; j--) {  
                if (((SysMenu) list.get(j + 1)).getOrder().compareTo(((SysMenu)list.get(j)).getOrder()) < 0) {  
                    temp = (SysMenu) list.get(j + 1);  
                    list.set(j + 1, (SysMenu) list.get(j));  
                    list.set(j, temp);  
                    exchange = true;  
                }  
            }  
            if (!exchange)  
                break;  
        }  
        return list;  
    } 
	
	@RequestMapping("/403.do")  
	public String error_403(){
		return "common/403";
	}
}
