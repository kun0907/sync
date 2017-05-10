<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>建设工程项目物资管理平台</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
			<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north'" style="height:81px;">
			<iframe id="headerFrame" name="headerFrame" src="${emms}/jsp/header.do" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
		</div>
		<div data-options="region:'west',split:true" style="width:200px;">
			<iframe id="menuFrame" name="menuFrame" src="${emms}/jsp/menu.do" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
		</div>
		<%--<div data-options="region:'south'" style="height:50px;background:#A9FACD;padding:10px;">
			footer
		</div>--%>
		<div data-options="region:'center'">
			<iframe id="mainFrame" name="mainFrame" src="${emms}/jsp/welcome.do" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>	
		</div>
	</body>
	
	
	
	
	
<!-- 		<div  style="height: 90px;width: 100%"> -->
		<%-- <div class="ui-layout-north">
			<iframe id="headerFrame" name="headerFrame" src="${emms}/jsp/header.do" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
		</div>
		<div class="ui-layout-west">		
			<iframe id="menuFrame" name="menuFrame" src="${emms}/jsp/menu.do" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
		</div>	
		<div class="ui-layout-center">
			<div id="loading" style="display:none;z-index: 12000;left: 0%;width: 100%;cursor: wait;position: absolute;top: 0px;height: 100%;background-color: #cccccc;filter:Alpha(opacity=50);">
				<img alt="" src="${emms}/images/loading.gif" width="5%" height="10%" style="left: 45%;top: 45%;position: relative;">
			</div>
			<iframe id="mainFrame" name="mainFrame" src="${emms}/jsp/welcome.do" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>	
		</div> --%>
		<!-- Modal -->
		<!-- <button id="modal" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"></button>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
			<div class="modal-dialog" id="modal-dialog">
		    	<div class="modal-content">
		      		<div class="modal-header">
						<button type="button" class="btn btn-primary btn-xs" id="butclose" data-dismiss="modal" style="display:none">关闭</button>	
					</div>
   					<div class="modal-body" id="modal-body">
      					<iframe id="a" name="a" src="" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>
   					</div>
 				</div>
			</div>
		</div> -->
	
	<script type="text/javascript">
	</script> 
	
</html>
