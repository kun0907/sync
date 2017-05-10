<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->系统管理->组织机构管理->人员列表" data-options="fit:true,border:false">
		<form id="query" method="post">
			<div style="margin:20px">
				<input class="easyui-textbox" id="empCode"  style="width:30%" data-options="label:'人员编码:'">
				<input class="easyui-textbox" id="employeeName" style="width:30%" data-options="label:'人员姓名:'">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				<a href="${emms}/system/user.do?cmd=editUserPage&orgId=${orgId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
			</div>
		</form>
		<table id="table" auto-resize="true" class="easyui-datagrid" title="人员列表" width="100%">
		</table>
	</div>
	<script type="text/javascript">
			$(function(){
				queryEmp();
			});
			function queryEmp(){
				$('#table').datagrid({
				    url:'${emms}/system/employee.do?cmd=loadEmpListData&orgId=${orgId}',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
					singleSelect:true,
				    queryParams: {
				    	"empCode" : $("#empCode").val(),
				    	"empName" : $("#employeeName").val()
					},
				    columns:[[
				        {field:'EMP_NO',sortable:true,title:'人员编码',align:'center',width:'17%',
							formatter: function(value, row, index){
								return "<a style='color:blue' href=\"javascript:viewEmployee(\'"+ row.USER_ID + "\')\">"+row.EMP_NO+"</a>";
							}
						},
				        {field:'EMP_NAME',sortable:true,title:'人员姓名',align:'center',width:'17%'},
				        {field:'SEX',sortable:true,title:'性别',align:'center',width:'16%',
				        	formatter: function(value, row, index){
								 if (value == "female") {
							            return "女";
							     }else if (value == "male") {
							            return "男";
							     }
							}},
				        {field:'EMP_STATE',sortable:true,title:'人员状态',align:'center',width:'16%',
					        	formatter: function(value, row, index){
									 if (value == "1") {
								            return "在岗";
								     }else if (value == "0") {
								            return "离职";
								     }
								}},
				        {field:'ORG_NAME',sortable:true,title:'所属机构',align:'center',width:'16%'},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'20%',
							formatter: function(value,row,index){
								show = "<a class='easyui-linkbutton' href=\"javascript:viewEmployee(\'"+ row.USER_ID + "\')\">编辑</a>&nbsp;&nbsp;&nbsp;";
								if(row.EMP_STATE == '1'){
									show += "<a href=\"javascript:forbidUser(\'"+ row.USER_ID + "\')\">禁用</a>&nbsp;&nbsp;&nbsp;";
								}else if(row.EMP_STATE == '0'){
									show += "<a href=\"javascript:reuseUser(\'"+ row.USER_ID + "\')\">启用</a>&nbsp;&nbsp;&nbsp;";
								}
								return show;
							}}
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
			function forbidUser(userId){
				if(window.confirm('确定要禁用此用户吗？')){
					$.ajax({
						type: "GET",
			            url:"${emms}/system/user.do?cmd=forbidUser&userId="+userId,
			            success: function(data) {
							$.messager.alert("操作提示",data,"info",function(){
								window.location= "${emms}/system/employee.do?cmd=query&orgId=${orgId}";
							});
						}
					});
				}	
			}
			function reuseUser(userId){
				$.ajax({
					type: "GET",
		            url:"${emms}/system/user.do?cmd=reuseUser&userId="+userId,
		            success: function(data) {
						$.messager.alert("操作提示",data,"info",function(){
							window.location= "${emms}/system/employee.do?cmd=query&orgId=${orgId}";
						});
		            }
				});
			}
			function viewEmployee(userId){
				$('#dialog').dialog({
					title: '人员信息编辑',
					width: 800,
					height: 400,
					closed: false,
					cache: true,
					href: '${emms}/system/user.do?cmd=editUserPage&userId='+userId
				});
			}
		</script>
</body>
</html>
