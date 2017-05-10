<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
  
	<body  class="easyui-layout">
		<div data-options="region:'center'"> 
			<iframe id="dialogFrame" name="dialogFrame"  src="${emms}/${dialogFrame}" width="100%" height="470px" frameborder="0" scrolling="no" ></iframe>
		</div>
	</body>
	
	<script type="text/javascript">
	</script>
</html>
