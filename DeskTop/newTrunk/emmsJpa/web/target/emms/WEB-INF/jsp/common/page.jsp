<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String url = request.getParameter("url");
%>
<!-- 分页开始 -->
	<div class="btn-group">
		<button type="button" class="btn btn-primary btn-xs" onclick="showPage('<%=path %><%=url%>?pageNow=1');">首页</button>
		<c:choose>
			<c:when test="${page.pageNow - 1 > 0}">
				<button type="button" class="btn btn-primary btn-xs" onclick="showPage('<%=path %><%=url%>?pageNow=${page.pageNow - 1}');">上一页</button>
			</c:when>
			<c:when test="${page.pageNow - 1 <= 0}">
				<button type="button" class="btn btn-primary btn-xs" onclick="showPage('<%=path %><%=url%>?pageNow=1');">上一页</button>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page.pageNow + 1 < page.totalPageCount}">
				<button type="button" class="btn btn-primary btn-xs" onclick="showPage('<%=path %><%=url%>?pageNow=${page.pageNow + 1}');">下一页</button>
			</c:when>
			<c:when test="${page.pageNow + 1 >= page.totalPageCount}">
				<button type="button" class="btn btn-primary btn-xs" onclick="showPage('<%=path %><%=url%>?pageNow=${page.totalPageCount}');">下一页</button>
			</c:when>
		</c:choose>
		<button type="button" class="btn btn-primary btn-xs" onclick="showPage('<%=path %><%=url%>?pageNow=${page.totalPageCount}');">尾页</button>										
		<button type="button" class="btn btn-primary btn-xs" >共 ${page.totalPageCount} 页  第 ${page.pageNow} 页</button>
		<button type="button" class="btn btn-primary btn-xs" >共 ${page.totalCount}条记录</button>
	</div>													
<!-- 分页结束 -->
	
	<c:if test="${message!=null}">
		<script type="text/javascript" >alert('${message}');</script>
	</c:if> 

	<script type="text/javascript" >
		function showPage(url){
			if(window.parent.document.getElementById("loading")!=null){
				window.parent.document.getElementById("loading").style.display = "";
			}
			if(document.condition!=null){
				console.log(document.condition);
				document.condition.action = url;
				document.condition.submit();
			}else{
				window.location.href=url;
			}
		}
	</script>
