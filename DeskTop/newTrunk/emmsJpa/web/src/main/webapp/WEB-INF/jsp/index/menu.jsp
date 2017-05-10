<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>系统菜单</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style >
		*{font-size: 13px;font-family: Tahoma,Verdana,微软雅黑,新宋体;}
		.selected{font-size: 14px;cursor: default;margin:5px;padding-left: 10px;}
		/*a:hover{ color:Red; text-decoration:underline;}*/
		a:hover{ color:Red;cursor:pointer;}
		.icon-cd{margin: -40px; background: url(${emms}/images/cd12.png) no-repeat;background-position: 0px 2px;}
		div{font-size: 50px;}
		/*div ul{margin-left: -35px;}*/
		li {list-style-type:none;font-size: 15px;}
	</style>
</head>
<%--<body>--%>
<%--<div class="easyui-accordion" data-options="fit:true,border:false">--%>
	<%--<c:forEach var="menu" items="${sysMenuList}">--%>
		<%--<%--<c:if test="${not empty menu.subMenuPermission}">--%>
			<%--<div title="${menu.cname}">--%>
				<%--<ul id="${menu.cname}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">--%>
					<%--<c:forEach items="${menu.items}" var="submenu">--%>
						<%--<shiro:hasPermission name="${submenu.authority}">--%>
							<%--<li><a onclick="menu('${submenu.url}');">${submenu.cname}</a></li>--%>
						<%--</shiro:hasPermission>--%>
					<%--</c:forEach>--%>
				<%--</ul>--%>
			<%--</div>--%>
		<%--<%--</c:if>--%>
	<%--</c:forEach>--%>
<%--</div>--%>
<%--</body>--%>

<body>
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<c:forEach var="menu" items="${sysMenuList}">
			<div title="${menu.cname}">
				<ul id="${menu.cname}" >
					<c:forEach items="${menu.items}" var="submenu">
						<shiro:hasPermission name="${submenu.authority}">
							<li>
								<div class="selected">
									<a onclick="menu('${submenu.url}');"><span class="icon-cd" >&nbsp;&nbsp;&nbsp;&nbsp;${submenu.cname}</span></a>
								</div>
							</li>
						</shiro:hasPermission>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
	function menu(url){
		window.parent.document.getElementById("mainFrame").src = '${emms}'+url;
	}
</script>
</html>
