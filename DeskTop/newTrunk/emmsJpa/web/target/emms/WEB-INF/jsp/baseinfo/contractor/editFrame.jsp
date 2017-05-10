<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body style="padding: 0px;">
	<div class="easyui-tabs">
		<div title="编辑承包商">
			<iframe id="editFrame" name="headerFrame" src="${emms}/baseinfo/contractor.do?cmd=edit&contractorId=${contractorId}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>
		</div>
		<c:if test="${not empty contractorId}">
			<div title="配置WBS">
				<iframe id="headerFrame" name="headerFrame" src="${emms}/system/organization.do?cmd=configWBSQuery&contractorId=${contractorId}&orgId=${contractor.organization.orgId}&type=contrac" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>
			</div>
		</c:if>
	</div>
	<script type="text/javascript">
		$(function(){
			var title = '${title}';
			if(null != title && title !=''){
				$(".easyui-tabs").tabs('select', title);
			}
		});
	</script>
</body>
</html>
