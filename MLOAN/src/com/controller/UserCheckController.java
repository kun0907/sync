package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.po.Users;
import com.service.UserService;
import com.util.base.ActionBase;

@Controller
@RequestMapping("user")
public class UserCheckController extends ActionBase{
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(value="/registPage.do",method=RequestMethod.GET)
	public String registPage(HttpServletRequest request,String msg){
		request.setAttribute("msg", msg);
		return "jsp/admin/register";
	}
	
	@RequestMapping(value="/loginPage.do",method=RequestMethod.GET)
	public String loginPage(String username,HttpServletRequest request){
		request.setAttribute("username", username);
		return "jsp/admin/login";
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public void login(@Param("username") String username,@Param("password") String password,HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		log.info("用户登录........");

		String sql = "SELECT * FROM SYS_USERS WHERE USERNAME = '"+ username + "'";
		List<Map<String, String>> rs = getQueryList(sql);
		Map<String, String> path_map = new HashMap<String, String>();
		log.info("sql语句输出完毕");
        if(!rs.isEmpty()){
        	log.info("开始判断md5加密密码");
            Map<String, String> map = rs.get(0);
            String db_password = map.get("password");
            
            String pass  = (MD5(password)+username);
            log.info("取出md5加密密码："+pass);
            if(db_password.equals(pass)){
            	
    			Map<String, String> maps = new HashMap<String, String>();
            	maps.put("username", username);
            	log.info("session缓存会话........");
	    		session(maps, request);
	    		execute("UPDATE SYS_USERS SET STATUS=1 , SESSIONID= '"+request.getRequestedSessionId()+"' WHERE USERNAME= '"+ username + "'");
	    		log.info("跳转页面之前");
	    	 	String path = "/MLOAN/jsp/admin/home.jsp"; 
	    	 	log.info("打印页面路径"+path);
	    	 	path_map.put("username",username);
            	
	    	 	res(path, path_map, response);
            }else{
            	log.info("用户名或密码错误........");
    			response.setContentType("text/html;charset=utf-8");
    			PrintWriter out = response.getWriter();
    			out.println("<script>alert('用户名或密码错误');");
    			out.println("history.back();");
    			out.println("</script>");
    			out.close();
            }
        }else{
        	log.info("用户名或密码错误........");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('用户名或密码错误');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
        }
	}
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public String register(@Param("username") String username,
					     @Param("password") String password,
					     @Param("password2") String password2,
					     @Param("usermail") String usermail,
					     @Param("verifyCode") String verifyCode,
					     HttpServletRequest request,
					     HttpServletResponse response,Model model) throws Exception {
			log.info("用户注册........");
			String msg="注册失败，";
			HttpSession session = request.getSession();
			
			String existCode = (String) session.getAttribute("verifyCode");//session中的验证码
			if(existCode==null||existCode.trim().length()!=6||verifyCode==null||verifyCode.trim().length()!=6||!existCode.trim().equals(verifyCode.trim())){
						log.info("验证码错误");
						msg+="验证码错误！";
						model.addAttribute("msg", msg);
						return "redirect:/user/registPage.do"; 
			}
			
			long millis_create =(long) session.getAttribute("time_verifyCodeInit");
			long millis_now = System.currentTimeMillis();
			long seconds_used=(millis_now-millis_create)/1000;
			
			if(seconds_used>90){//验证码过期
				  log.info("验证码过期");
				  msg+="验证码过期";
				  model.addAttribute("msg", msg);
				  return "redirect:/user/registPage.do"; 
			}
			
			
			if(password.equals(password2)){
					if(password.length()<6){
							log.info("密码长度过短........");
							msg="密码长度过短";
							model.addAttribute("msg", msg);
							return "redirect:/user/registPage.do"; 
					}else{
							log.info(username);
							String userPas = (MD5(password)+username);
							
							log.info(userPas+username);
							Users user = new Users();
							user.setUsername(username);
							user.setPassword(userPas);
							user.setUsermail(usermail);
							user.setStatus("0");
							int insertCount=userService.addUser(user);
							if(insertCount==1){
									model.addAttribute("username", username);
									return "jsp/admin/success";
							}else if(insertCount==0){
									msg+="发生未知错误，请重新注册！";
									model.addAttribute("msg", msg);
									return "redirect:/user/registPage.do"; 
							}else if(insertCount==500){
									msg+="邮箱已被占用！";
									model.addAttribute("msg", msg);
									return "redirect:/user/registPage.do"; 
							}else if(insertCount==600){
									msg+="用户名已被占用！";
									model.addAttribute("msg", msg);
									return "redirect:/user/registPage.do"; 
							}
					}
			}
			
			log.info("您输入的两次密码不一样........");
			msg+="您输入的两次密码不一样!";
			return "redirect:/user/registPage.do"; 
	}
	
	
	@RequestMapping(value="/checkLogged.do",method=RequestMethod.GET)
	public void checkLogged(String username,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		log.info("检查用户登录状态 ........");
		Map<String, String> map = new HashMap<String, String>();
		
		String sql = "SELECT STATUS,SESSIONID FROM SYS_USERS WHERE USERNAME = '"+ username + "'";
    	List<Map<String, String>> rs = getQueryList(sql);
    	String status=null;
    	String sessionid=null;
    	if(rs.size()!=0){
    		status = rs.get(0).get("status");
        	sessionid = rs.get(0).get("sessionid");
    	}
    	map.put("msg", status);
    	String data = JSON.toJSONString(map);
    	
    	if(sessionid!=null && sessionid.equals(request.getRequestedSessionId())){
    		map.put("msg", String.valueOf(2));
    		data = JSON.toJSONString(map);
    	}
    	
    	PrintWriter out = null;
	    try {
	    	out = response.getWriter();
	    	out.print(data);
	    } catch (IOException e) {
	      System.err.println("PrintWriter写入响应数据时发生异常！！！");
  		  log.error(e);
	      log.error(e);
	    } finally {
	      if (null != out)
	    	  out.close();
	      }
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/checking.do",method=RequestMethod.GET)
	@ResponseBody
	public Map checking(String username,String usermail,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
				log.info("检查用户/邮箱是否存在 ........");
				log.info("username="+username);
				log.info("usermail="+usermail);
				
				String msg_user = "";	//1有数据，0无数据，-1 查询条件为null
				String msg_mail = "";
				Map<String, String> map = new HashMap<String, String>();
				
				if(username==null){
						msg_user = "2";
				}else{//检查username
						boolean exist=userService.checkUsernameExist(username);
						if(exist){//已被注册
								msg_user="1";
						}else{//未被注册
								msg_user = "2";
						}
				}
				
				
				if(usermail==null){
						msg_mail = "2";
				}else{//检查usermail
						boolean exist=userService.checkEmailExist(usermail);
						if(exist){//已被注册
								msg_mail="1";
						}else{//未被注册
								msg_mail = "2";
						}
				}
				map.put("user", msg_user);
				map.put("mail", msg_mail);
				return map;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	  @RequestMapping("logout.do")
	  public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		log.info("用户注销 ........");
		
		String sql = "SELECT SESSIONID FROM SYS_USERS WHERE USERNAME = '"+ request.getSession().getAttribute("username") + "'";
    	List<Map<String, String>> rs = getQueryList(sql);
    	
    	String sessionid = null;
    	if(rs.size()!=0)
    		sessionid = rs.get(0).get("sessionid");
		if(sessionid !=null && sessionid.equals(request.getRequestedSessionId()))
			execute("UPDATE SYS_USERS SET SESSIONID=null , STATUS=0 WHERE USERNAME= '"+ request.getSession().getAttribute("username") + "' AND SESSIONID='"+sessionid+"'");
		
		List<String> list = new ArrayList<String>();
		list.add("username");
		removeSession(list, request);
		
		String path = "/MLOAN/";
		res(path, null, response);
	  }
	  
	  @RequestMapping(value="updataUser.do",method=RequestMethod.POST)
	  public void updataUser(HttpServletRequest request, HttpServletResponse response,@Param("password1") String password1,@Param("password") String password,@Param("password2") String password2) throws Exception{
		  log.info("修改密码........");
		  String path="/MLOAN/jsp/admin/login.jsp";
		  if(password.length()<6){
			  	log.info("输入的密码长度不够........");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('您输入的新密码有误，请输入6位有效密码');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
		  }
		  else if (!password.equals(password2)) {
		      log.info("两次输入的密码不一致........");
		      response.setContentType("text/html;charset=utf-8");
		      PrintWriter out = response.getWriter();
		      out.println("<script>alert('两次输入的密码不一致');");
		      out.println("history.back();");
		      out.println("</script>");
		      out.close();
		  }else{
			  if(password.equals(password2)){
				  Object username111 = request.getSession().getAttribute("username");
				  String pas=(MD5(password1)+username111);
				  String sql="SELECT * FROM SYS_USERS WHERE USERNAME = '"+ username111 + "'AND PASSWORD= '"+ pas + "'";
				  List<Map<String, String>> rs = getQueryList(sql);
				  if(rs.isEmpty()){
					  	log.info("输入的密码有误........");
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('您输入的密码有误');");
						out.println("history.back();");
						out.println("</script>");
						out.close();
				  }else{
					  String pass =  (MD5(password)+username111);
					  String sql1 = "UPDATE SYS_USERS SET PASSWORD = '"+ pass + "' WHERE USERNAME='"+username111+"'";
					  execute(sql1);
					  
                                          res(path, null, response);
				  } 
			  } 
		  }
	  }
}
