<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>列表页</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
	<body>
		<div class="easyui-panel" title="首页->系统管理->用户授权管理->人员列表" data-options="fit:true,border:false">
			<form id="query" method="post">
				<div style="margin:20px">
					<input class="easyui-textbox" id="empName"  style="width:30%" data-options="label:'人员姓名:'">
					<input class="easyui-textbox" id="orgName"  style="width:30%" data-options="label:'所属机构:'">
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				</div>
			</form>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="人员列表" width="100%">
			</table>
		</div>
	<script type="text/javascript">
			$(function(){
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/system/userRole.do?cmd=selectEmployeeByOrgData&orgId=${orgId}',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
					singleSelect:true,
				    queryParams: {
				    	"empName" : $("#empName").val(),
				    	"orgName" : $("#orgName").val()
					},
				    columns:[[
				        {field:'EMP_NAME',sortable:true,title:'人员姓名',align:'center',width:'30%'},
				        {field:'ORG_NAME',sortable:true,title:'所属机构',align:'center',width:'30%'},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'40%',
							formatter: function(value,row,index){
								show = "<a href=\"javascript:authorizeEmp(\'"+ row.USER_ID + "\')\">授权</a>&nbsp;&nbsp;&nbsp;";
								return show;
							}}
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
			function authorizeEmp(userId){
				window.parent.frames["centerFrame"].location.href = "${emms}/system/userRole.do?cmd=selectRoleByOrgPage&userId="+userId+"&orgId=${orgId}";
			}
		</script>
	</body>
</html>
