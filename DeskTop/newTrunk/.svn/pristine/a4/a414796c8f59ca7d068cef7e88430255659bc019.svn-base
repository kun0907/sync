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
	<div class="datagrid-pager pagination">
		<table cellspacing="0" cellpadding="0" border="0">
			<tbody>
				<tr>
					<td>
						<select class="pagination-page-list">
							<option>10</option>
							<option>20</option>
							<option>30</option>
							<option>40</option>
							<option>50</option>
						</select>
					</td>
					<td>
						<div class="pagination-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:void(0)" class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id="">
							<span class="l-btn-left l-btn-icon-left">
								<span class="l-btn-text l-btn-empty">&nbsp;</span>
								<span class="l-btn-icon pagination-first">&nbsp;</span>
							</span>
						</a>
					</td>
					<td>
						<a href="javascript:void(0)" class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id="">
							<span class="l-btn-left l-btn-icon-left">
								<span class="l-btn-text l-btn-empty">&nbsp;</span>
								<span class="l-btn-icon pagination-prev">&nbsp;</span>
							</span>
						</a>
					</td>
					<td>
						<div class="pagination-btn-separator">
						</div>
					</td>
					<td>
						<span style="padding-left:6px;">第</span>
					</td>
					<td>
						<input class="pagination-num" type="text" value="1" size="2">
					</td>
					<td>
						<span style="padding-right:6px;">共1页</span>
					</td>
					<td>
						<div class="pagination-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:void(0)" class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id="">
							<span class="l-btn-left l-btn-icon-left">
								<span class="l-btn-text l-btn-empty">&nbsp;</span>
								<span class="l-btn-icon pagination-next">&nbsp;</span>
							</span>
						</a>
					</td>
					<td>
						<a href="javascript:void(0)" class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled" group="" id="">
							<span class="l-btn-left l-btn-icon-left">
								<span class="l-btn-text l-btn-empty">&nbsp;</span>
								<span class="l-btn-icon pagination-last">&nbsp;</span>
							</span>
						</a>
					</td>
					<td>
						<div class="pagination-btn-separator"></div>
					</td>
					<td>
						<a href="javascript:void(0)" class="l-btn l-btn-small l-btn-plain" group="" id="">
							<span class="l-btn-left l-btn-icon-left">
								<span class="l-btn-text l-btn-empty">&nbsp;</span>
								<span class="l-btn-icon pagination-load">&nbsp;</span>
							</span>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="pagination-info">显示1到9,共9记录</div>
		<div style="clear:both;">
		</div>
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
