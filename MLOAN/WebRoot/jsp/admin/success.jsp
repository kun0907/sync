<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <meta http-equiv="refresh" content="2;url=<%=request.getContextPath() %>/jsp/admin/login.jsp?username=${msg}" />  --%>
<meta http-equiv="refresh" content="2;url=<%=request.getContextPath() %>/user/loginPage.do?username=${username}" /> 
</head>
<body>
<center>
			<h1>注册成功</h1>3s后跳转到登录页面<a href="<%=request.getContextPath() %>/user/loginPage.do?username=${username}" >点击跳转到登录页面</a>
			</center>
</body>
</html>