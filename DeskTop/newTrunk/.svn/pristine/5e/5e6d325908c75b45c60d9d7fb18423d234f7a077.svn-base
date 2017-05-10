<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->基础信息管理->项目信息管理->项目编辑" data-options="fit:true,border:false">
		<form id="projectForm" method="post">
			<input class="easyui-textbox" type="hidden" id="projectId" name="projectId"/>
			<input class="easyui-textbox" type="hidden" id="isMain" name="isMain" value="0"/>
			<input class="easyui-textbox" type="hidden" id="parentId" name="parentId" value="${parentId}"/>
			<div style="padding:10px" >
				<B>上级项目编码:</B>
				<input class="easyui-textbox" id="Code"   style="width:25%">
				<B>上级项目名称:</B>
				<input class="easyui-textbox" id="Name"  style="width:25%">
			</div>
			<div style="padding:10px">
				<c:if test="${empty projectId}">
					<input class="easyui-textbox" id="projectCode" style="width:40%" name="projectCode" data-options="label:'项目编码:',
						validType:{
							length:[1,100],
							remote:['${emms}/baseinfo/project.do?cmd=checkProjectCode','projectCode']
						   }">
				</c:if>
				<c:if test="${not empty  projectId}">
					<input class="easyui-textbox" id="projectCode" editable="false" style="width:40%" name="projectCode" data-options="label:'项目编码:'"/>
				</c:if>
			    <input class="easyui-textbox" id="projectName" style="width:40%" name="projectName" data-options="label:'项目名称:',required:true,validType:'length[0,50]'">
			</div>
			<div style="padding:10px">
				<input class="easyui-combobox" id="projectTypeEdit" style="width:40%" name="projectType" data-options="label:'项目类型:'">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$('#projectTypeEdit').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=project_type',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName',
			multiple:false
		});
		$(function(){
			$('#projectForm').form('load', '${emms}/baseinfo/project.do?cmd=editProject&projectId=${projectId}&parentId=${parentId}');
			  $('#projectForm').form({
				onLoadSuccess:function(data){
					$("#projectId").textbox("setValue", data.project.projectId);
					if(null != data.parent){
						$("#Code").textbox("setValue", data.parent.projectCode);
						$("#Name").textbox("setValue", data.parent.projectName);
					}
					$("#projectCode").textbox("setValue", data.project.projectCode);
					$("#projectName").textbox("setValue", data.project.projectName);
					$("#Code").textbox({readonly:true});
					$("#Name").textbox({readonly:true});
					if(null != data.project.projectType){
						$("#projectTypeEdit").combobox("setValues", data.project.projectType);
					}
				}
			});  
		});
	function ajaxSubmitForm() {
		  $("#projectForm").form("submit", {
             url: "${emms}/baseinfo/project.do?cmd=projectSave",
             onSubmit: function () {
                 return $(this).form("validate");
             },
             success: function (result) {
				 if(result == 'true'){
					 $.messager.alert("操作提示","项目保存成功","info",function(){
						 $('#dialog').dialog('close');
						 query();
						 window.parent.frames["westFrame"].initTree();
					 });

				 }else{
					 $.messager.alert("result",result,"warning");
				 }
             }
         }); 
	 } 
	</script>
</body>		
</html>
