<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>角色编辑</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
	<body>
		<div class="easyui-panel" title="首页->系统管理->角色管理->新建" data-options="fit:true,border:false">
			<form id="ff" method="post">
				<input class="easyui-textbox" type="hidden" name="roleId"/>
				<div style="margin:20px">
					<c:if test="${empty roleId}">
						<input class="easyui-textbox" id="roleCode" name="roleCode"  style="width:30%" data-options="label:'角色编码:',required:true,
							validType:{
								length:[1,50],
								remote:['${emms}/system/role.do?cmd=checkCode','roleCode']
						   	}
						" >
					</c:if>
					<c:if test="${not empty roleId}">
						<input class="easyui-textbox" id="roleCode" editable="false" name="roleCode"  style="width:30%" data-options="label:'角色编码:'" >
					</c:if>
					<input class="easyui-textbox" name="roleName" name="roleName" maxlength="50" style="width:30%" data-options="label:'角色名称:'">
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
					<a href="javascript:void(0)"iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
				</div>
			</form>
		</div>
		<script type="text/javascript">
			$(function(){
				$('#ff').form('load', '${emms}/system/role.do?cmd=loadRoleData&roleId=${roleId}');
				$('#ff').form({
					onLoadSuccess:function(data){
						if(null != data.roleId){
							$("#roleCode").textbox({disabled:true});
						}
					}
				});
			});
		function ajaxSubmitForm() {
			var nodes = window.parent.frames["centerFrame"].$('#tt').tree('getChecked');
			var idValues = '';
			if(nodes.length==0){
				$.messager.alert("操作提示", "没有可配置的权限！","warning");
				return;
			}
			//[使用%5B代替,{使用%7B代替，}使用%7D,]使用%5D代替,""%22代替
			var json = '%5B';
			for (var i = 0; i < nodes.length; i++) {
				if(json == '%5B'){
					json += '%7B%22resourceId%22:%22'+nodes[i].id+'%22,%22roleIds%22:%22'+nodes[i].code+'%22%7D';
				}else{
					json += ',%7B%22resourceId%22:%22'+nodes[i].id+'%22,%22roleIds%22:%22'+nodes[i].code+'%22%7D';
				}
			}
			json +="%5D"
			  $("#ff").form("submit", {
	             url: "${emms}/system/role.do?cmd=saveRole&resourceIds="+json,
	             onsubmit: function () {
	                 return $(this).form("validate");
	             },
	             success: function (result) {
					 if(result=='true'){
						 $.messager.alert("操作提示", "角色保存成功！","info",function(){
							 $("#dlg").dialog("close");
							 $("#dg").datagrid("load");
							 window.parent.location.href="${emms}/system/role.do?cmd=roleFrame"
						 });
					 }else{
						 $.messager.alert("操作提示", result,"warning");
					 }
	             }
	         }); 
		 } 
			function back(){
				window.parent.location.href="${emms}/system/role.do?cmd=roleFrame"
			}
		
		</script>
	</body>
</html>
