<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>角色编辑</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->系统管理->权限管理->新建" data-options="fit:true,border:false">
	<form id="ff" method="post">
		<input class="easyui-textbox" type="hidden" id="resourceId" name="resourceId" value="${resourceId}"/>
		<div style="margin:20px">
			<c:if test="${empty resourceId}">
				<input class="easyui-textbox" id="resourceCode" name="resourceCode"  style="width:30%" data-options="label:'权限编码:',required:true,
							validType:{
								length:[1,100],
								remote:['${emms}/system/resource.do?cmd=checkCode','resourceCode']
						   	}
						" >
			</c:if>
			<c:if test="${not empty resourceId}">
				<input class="easyui-textbox" id="resourceCode" editable="false" name="resourceCode"  style="width:30%" data-options="label:'权限编码:'" >
			</c:if>
			<input class="easyui-textbox" id="resourceName" name="resourceName" maxlength="50" style="width:30%" data-options="label:'模块名称:',required:true">
			<input class="easyui-combobox" id="resourceType" name="resourceType" maxlength="50" style="width:30%" data-options="label:'类型:',required:true">
		</div>
		<div style="margin:20px">
			<input class="easyui-textbox" id="url" name="url" maxlength="50" style="width:30%" data-options="label:'资源',required:true">
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
			<a href="javascript:void(0)"iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		$('#resourceType').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=resource_type',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName'
		});
		$('#ff').form('load', '${emms}/system/resource.do?cmd=editResource&resourceId=${resourceId}');
	});
	function ajaxSubmitForm(){
		$("#ff").form("submit", {
			url: "${emms}/system/resource.do?cmd=saveResource&parentId=${parentId}",
			onsubmit: function () {
				return $(this).form("validate");
			},
			success: function (result) {
				if(result == 'true'){
					alert("权限保存成功");
					window.parent.frames["westFrame"].initTree();
				}else{
					alert(result);
				}
			}
		});
	}
	function back(){
		window.location.href = "${emms}/system/resource.do?cmd=query&parentId=${parentId}";
	}
</script>
</body>
</html>
