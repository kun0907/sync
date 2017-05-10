package com.dkd.emms.web.exception;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dkd.emms.core.exception.BusinessException;

/**
 * 业务异常拦截器
 * @author WANGQ
 *
 */
public class ExceptionFilter implements Filter {
	
	private String errorPage;//跳转的错误信息页面
	
	private String error404Page;//跳转的错误信息页面
	private String error500Page;//跳转的错误信息页面
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 HttpServletResponse response = (HttpServletResponse) res;
	     HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Content-Type","text/html;charset=UTF-8");
	     //捕获你抛出的业务异常
	     try {
			chain.doFilter(req, res);
//			response.getWriter().println("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getCause() instanceof BusinessException){//如果是你定义的业务异常
				response.getWriter().println(e.getCause().getMessage());
				response.getWriter().flush();
			}
//			
//			
//			/*if(e.getCause() instanceof BusinessException){//如果是你定义的业务异常
//				StringBuffer sb = new StringBuffer();
//				sb.append("<script language='javascript'>history.go(-1);alert('");
//			    sb.append("");
//				sb.append(""+e.getCause().getMessage()+"');</script>");
//				response.setContentType("text/html; charset=utf-8");  
//				response.getWriter().println(sb.toString());
//				response.getWriter().flush();	
//			}
//			e.printStackTrace();*/
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		errorPage = config.getInitParameter("errorPage");
		error404Page = config.getInitParameter("error404Page");
		error500Page = config.getInitParameter("error500Page");
	}

}
