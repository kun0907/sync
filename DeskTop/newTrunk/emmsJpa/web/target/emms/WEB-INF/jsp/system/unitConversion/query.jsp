<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<link rel="stylesheet" href="${emms}/css/commonStyle.css">
</head>

<body>
	<div class="container">
	
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<a name="collapse" onclick="setCollapseHeight();" data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="display: block;">
							<span class="label label-primary"><span class="glyphicon glyphicon-chevron-right"></span> 物资查询</span>
						</a>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						<form:form commandName="UnitConversion" action="${emms}/unitConversion/query.do" method="post">
							<table class="table table-condensed">
								<tr>
									<th class="text-right">主计量单位名称：</th>
									<td><form:input id="unitCodeMain" path="unitCodeMain" cssClass="form-control input-sm" value="${unitCodeMain}"/></td>
									<th class="text-right">辅助计量单位名称：</th>
									<td><form:input id="unitCodeSecondary" path="unitCodeSecondary" cssClass="form-control input-sm" value="${unitCodeSecondary}"/></td>
								</tr>
								<tr>
									<th colspan="6" class="text-center">
										<button id="qFromSubmit" type="submit" class="btn btn-primary btn-xs" onclick="window.parent.document.getElementById('loading').style.display = ''">
											<span class="glyphicon glyphicon-search"></span> 查询
										</button>
										<button type="button" class="btn btn-primary btn-xs reset">
											<span class="glyphicon glyphicon-remove"></span> 清空
										</button>
									</th>
								</tr>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<span class="label label-primary"><span class="glyphicon glyphicon-chevron-right"></span> 计量单位换算列表</span>
					</div>
					<div class="panel-body">
						<a class="btn btn-primary btn-xs" href="${emms}/unitConversion/edit.do"><span class="glyphicon glyphicon-plus"></span> 新建</a>						
						<button id="modifyBtu" value="../unitConversion/edit.do?unitConversionId=" class="btn btn-primary btn-xs" ><span class="glyphicon glyphicon-pencil"></span> 编辑</button>
						<button onclick="ajaxDel0();" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span> 删除</button> 
					</div>
					<table class="table table-striped table-bordered table-condensed table-hover">
						<tr>
							<th style="text-align: center;"><input type="checkbox" name="checkAll"  id="checkAll"/></th>
							<th class="text-center">序号</th>
							<th class="text-center">主计量单位编码</th>
							<th class="text-center">辅计量单位编码</th>
						</tr>
						<c:forEach items="${list}" var="unitConversion" varStatus="no">
							<tr>
								<th style="text-align: center;"><input type="checkbox" value="${unitConversion.unitconversionId}" name="ids"/></th>
								<td>${(page.pageNow-1) * 10 + (no.index + 1)}</td>
								<td>${unitConversion.unitCodeMain}</td>
								<td>${unitConversion.unitCodeSecondary}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<!------------- 分页开始 ------------->	
				<jsp:include page="../../common/page.jsp">  
					<jsp:param name="url" value="${pageUrl}" />      
				</jsp:include>  
				<!------------- 分页结束 -------------> 
			</div>
		</div>
	</div>		
	<!-- 隐藏条件 -->
	<form id="condition" name="condition" style="display: none" method="post">
		<input id="unitCodeMain" name="unitCodeMain" value="${UnitConversion.unitCodeMain}"/>
		<input id="unitCodeSecondary" name="unitCodeSecondary" value="${UnitConversion.unitCodeSecondary}"/>
	</form>	
	<script type="text/javascript">
		function ajaxDel0() {
			var j=0;
			var idValues = '';
			$("input[name='ids']").each(function(){
				if(this.checked){
					j++;
					idValues += $(this).val() + ",";
				}
			});
			if(j==0){
				alert("请至少选择一项删除！");
			}else{
				if(window.confirm('确定要删除当前记录吗？')){
					$.ajax({
			            type: "POST",
			            url:"${emms}/unitConversion/delete.do?ids="+idValues,
			            async: false,
			            success: function(data) {
			            	alert(data);
			            	$("#qFromSubmit").click();
			            }
			        });
				}
			}	
	    }
	</script>
	<script src="${emms}/js/commonScript.js" type="text/javascript"></script>	
</body>
</html>
