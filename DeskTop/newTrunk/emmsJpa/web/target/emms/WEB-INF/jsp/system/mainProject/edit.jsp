<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>主项目编辑</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
	
<body>
	<div class="easyui-panel" title="首页->基本信息管理->主项目管理->查看" data-options="fit:true,border:false">
		<form id="ff" method="post">
			<div style="margin:20px">
				<input class="easyui-textbox" name="projectCode" style="width:40%" data-options="label:'主项目编号:',required:true">
				<input class="easyui-textbox" name="projectName" style="width:40%" data-options="label:'主项目名称:',required:true">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="ajaxSubmitForm()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="back()">返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">	
		$(function(){
			$('#ff').form('load', '${emms}/system/mainProject.do?cmd=loadProject&projectId=${projectId}');
			});
		function ajaxSubmitForm() {
		 $("#ff").form("submit", {
             url: "${emms}/system/mainProject.do?cmd=saveProject",
             onsubmit: function () {
                 return $(this).form("validate");
             },
             success: function (result) {
				 if(result == 'true'){
					 $.messager.alert("提示", "主项目信息保存成功", "info");
				 }else{
					 $.messager.alert("操作提示", result, "warning");
				 }
             }
         });
    }
	</script>
</body>
</html>
