<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>管理</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
  	
  	<body  class="easyui-layout">
		<div data-options="region:'west',split:true" style="width:600px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<iframe id="centerFrame" name="centerFrame" src="${emms}/${centerFrameUrl}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>	
			</div>
		</div>
		<div data-options="region:'center'"> 
			<iframe id="westFrame" name="westFrame" src="${emms}/${westFrameUrl}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>	
		</div>
	</body>
	<script type="text/javascript">
	</script>
</html>
