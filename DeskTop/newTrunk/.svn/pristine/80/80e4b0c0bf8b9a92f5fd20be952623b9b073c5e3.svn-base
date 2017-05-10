<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>角色管理</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->系统管理->角色管理" data-options="fit:true,border:false">
		<form id="query" method="post">
			<div style="margin:20px">
				<input class="easyui-textbox" id="roleCode"  style="width:30%" data-options="label:'角色编码:'">
				<input class="easyui-textbox" id="roleName" style="width:30%" data-options="label:'角色名称:'">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				<a href="javascript:void(0)" iconCls='icon-add' class="easyui-linkbutton" onclick="modifyRole('');">新建</a>
			</div>
		</form>
		<table id="table" auto-resize="true" class="easyui-datagrid" title="角色列表" width="100%">
		</table>
	</div>
	<script type="text/javascript">
			$(function(){
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/system/role.do?cmd=loadRoleListData',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    singleSelect:true,
				    queryParams: {
				    	"roleCode" : $("#roleCode").val(),
				    	"roleName" : $("#roleName").val()
					},
				    columns:[[
				        {field:'ROLE_CODE',sortable:true,title:'角色编号',align:'center',width:'25%'},
				        {field:'ROLE_NAME',sortable:true,title:'角色名称',align:'center',width:'25%'},
				        {field:'ROLE_TYPE',sortable:true,title:'角色类型',align:'center',width:'25%',formatter: function(value, row, index){
							 if (value == "app_level") {
						            return "应用级";
						        } else if (value == "sys_level") {
						            return "系统级";
						        }
						}},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'27%',
							formatter: function(value,row,index){
								show = "<a href=\"javascript:configAuth(\'"+ row.ROLE_ID + "\')\">配置权限</a>&nbsp;&nbsp;&nbsp;";
								show += "<a href=\"javascript:modifyRole(\'"+ row.ROLE_ID + "\')\">查看</a>&nbsp;&nbsp;&nbsp;";
								//角色删除暂时不做(周渤涛:2017-02-09 16:46)
								/* show +=	"<a href=\"javascript:delRole(\'"
										+ row.ROLE_ID
										+ "\')\">删除</a>&nbsp;&nbsp;&nbsp;"; */
								return show;
							}}
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
			function configAuth(roleId){
				window.parent.frames["centerFrame"].location.href = "${emms}/system/role.do?cmd=editAuthTree&roleId="+roleId+"&isSave=true";
			}
			function modifyRole(roleId){
				window.parent.location.href = "${emms}/system/role.do?cmd=editFrame&roleId="+roleId;
			}
		</script>
</body>
</html>
