<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	   	<title>960111</title>
	   	
	   	<meta charset="utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">  
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
		<link rel="stylesheet" href="${emms}/css/bootstrap.min.css">
		<style type="text/css">
		@font-face{
			font-family:'Glyphicons Halflings';
			src:url('../fonts/glyphicons-halflings-regular.eot');
			src:url('../fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'),url('../fonts/glyphicons-halflings-regular.woff') format('woff'),url('../fonts/glyphicons-halflings-regular.ttf') format('truetype'),url('../fonts/glyphicons-halflings-regular.svg#glyphicons-halflingsregular') format('svg')
		}
		</style>
	   	<link rel="stylesheet" href="${emms}/css/loginStyle.css">
	</head>
  
	<body onload="checkParent();">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
  					<img alt="" src="${emms}/images/logo.png" class="img-rounded img-responsive">
				</div>
			  	<div class="col-md-4">
			  		<form class="form-horizontal" id="login" method="post" action="${emms}/<c:url value='j_spring_security_check'/>">
					    <input type="hidden" class="form-control" id="username" name="username" value="${username}">
					    <input type="hidden" class="form-control" id="password" name="password" value="${password}">
						<div class="form-group ">
							<div class="input-group col-md-6"  style="width: 200px;">
					      		<div class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span></div>
					      		<select class="form-control" id="DevNum" name="DevNum" onchange="getAgentID(this);">
								  <option></option>
								  <option value="6001">6001</option>
								  <option value="6002">6002</option>
								  <option value="6003">6003</option>
								  <option value="6004">6004</option>
								  <option value="6005">6005</option>
								  <option value="6006">6006</option>
								</select>
								<input type="hidden" id="AgentID" name="AgentID" />
					    	</div>
						</div>
						<div class="form-group">
							<button type="button" class="btn btn-primary btn-sm" id="checkLogin">登录</button>
							${SPRING_SECURITY_LAST_EXCEPTION.message}
						</div>	
					</form>
			  	</div>
			</div>
		</div>	
		<script src="${emms}/js/jquery-1.10.1.js" type="text/javascript"></script>
		<script src="${emms}/js/respond.js" type="text/javascript" ></script>
		<script src="${emms}/js/jquery.validate.js" type="text/javascript" ></script>
		<script src="${emms}/js/loginScript.js" charset="UTF-8"></script>	
		<script type="text/javascript">
			function getAgentID(obj){
				var selectIndex = obj.selectedIndex;//获得是第几个被选中了
				var selectText = obj.options[selectIndex].text; //获得被选中的项目的文本
				$("#AgentID").val(selectText);
			}
		</script>
	</body>
</html>
