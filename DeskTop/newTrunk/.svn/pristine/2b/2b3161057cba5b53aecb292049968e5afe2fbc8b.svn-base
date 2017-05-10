<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<link rel="stylesheet" href="${emms}/css/commonStyle.css">
</head>
	
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<span class="label label-primary"><span class="glyphicon glyphicon-chevron-right"></span> 新建计量单位换算率</span>
					</div>
					<div class="panel-body">
						<form:form id="UnitConversion" commandName="UnitConversion" action="" method="post">
							<table class="table table-bordered table-condensed">
								<tr>
									<th class="text-right">主计量单位：</th>
									<td>	
										<div class=" col-xs-8">
											<html:select id="unitCodeMain" name="unitCodeMain" classes="form-control input-sm" hideName="" style="">     
						       					<html:sysOptions condition="mainMeasurement" checkValue="${UnitConversion.unitCodeMain}" readonly="false"></html:sysOptions>    
						   					</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<th class="text-right">辅计量单位：</th>
									<td>	
										<div class=" col-xs-8">
											<html:select id="unitCodeSecondary" name="unitCodeSecondary" classes="form-control input-sm" hideName="" style="">     
						       					<html:sysOptions condition="helpMeasurement" checkValue="${UnitConversion.unitCodeSecondary}" readonly="false"></html:sysOptions>    
						   					</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<th class="text-right">转换率：</th>
									<td>
										<div class="col-xs-8">
											<form:input id="conversion" path="conversion" cssClass="form-control input-sm"/>
										</div>
									</td>
								</tr>
								<tr>
									<th colspan="2"  class="text-center">
										<button type="submit" class="btn btn-primary btn-xs" onclick="doSubmit();"><span class="glyphicon glyphicon-save"></span> 保存</button>
										<a href="${emms}/unitConversion/query.do" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-share-alt"></span> 返回</a>
									</th>
								</tr>
							</table>							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>	
	<script type="text/javascript">
		function doSubmit() {
	        validateForm();//调用验证方法
	 	}
		function validateForm() {
			$("#UnitConversion").validate({
	            submitHandler:function(form) {
	            	if (confirm("确认执行该操作吗？")){
	            		ajaxSubmitForm();//验证通过之后会去调用的方法
	            	}
	            },
	            rules: {
					dictionaryCode: {
						required: true,		
						rangelength:[1,50],
						remote:{
		 					type:"POST",
		 					url:"${emms}/unitConversion/checkrepeated.do",
		 					data:{
			 				}
						}
					}
				}
	        });
	    }
		
		function ajaxSubmitForm() {
			$.ajax({
	            type: "POST",
	            url: "${emms}/unitConversion/checkrepeated.do",
	            data:$('#UnitConversion').serialize(),
	            async: false,
	            success: function(data) {
	            	if(data){
	            		$.ajax({
		    	            type: "POST",
		    	            url: "${emms}/unitConversion/save.do",
		    	            data:$('#UnitConversion').serialize(),
		    	            async: false,
		    	            success: function(data) {
		    	            	alert(data);
		    	            	window.location = "${emms}/unitConversion/query.do" ;
		    	            },
		    	            error:function(XMLHttpRequest, textStatus, errorThrown){
		    	            	if(XMLHttpRequest.status==403){
		    	            		window.location = "${emms}/jsp/403.do";
		    	            	}
		    	            }
		    	        });
	            	}else{
	            		alert("请勿重复定义换算单位");
	            	}
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	            	if(XMLHttpRequest.status==403){
	            		window.location = "${emms}/jsp/403.do";
	            	}
	            }
	        });
	    }
		/*
		function ajaxSubmitForm() {
			$.ajax({
	            type: "POST",
	            url: "${emms}/unitConversion/save.do",
	            data:$('#UnitConversion').serialize(),
	            async: false,
	            success: function(data) {
	            	alert(data);
	            	window.location = "${emms}/unitConversion/query.do" ;
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	            	if(XMLHttpRequest.status==403){
	            		window.location = "${emms}/jsp/403.do";
	            	}
	            }
	        });
	    }
		*/
	</script>
	<script src="${emms}/js/commonScript.js" type="text/javascript"></script>	
</body>
</html>
