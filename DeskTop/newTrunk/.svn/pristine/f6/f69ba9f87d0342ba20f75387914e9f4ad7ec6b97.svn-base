<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
	<title>用户管理</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
	</head>
	<body>
		<div class="easyui-panel" title="首页->系统管理->主项目管理->查询" data-options="fit:true,border:false">
			<form id="ff" method="post">
				<div style="margin:20px">
					<input class="easyui-textbox" name="projectCode" style="width:30%" data-options="label:'项目编号:'">
					<input class="easyui-textbox" name="projectName" style="width:30%" data-options="label:'项目名称:'">
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="loadLocal()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
				</div>
			</form>
			<table id="table" class="easyui-datagrid" title="主项目列表" data-options="singleSelect:true,collapsible:true,url:'',method:'post'">
			</table>
		</div> 
		<script type="text/javascript">
			$(function(){
				$('#table').datagrid({
				    url:'${emms}/system/mainProject.do?cmd=query',
				    columns:[[
				        {field:'PROJECT_CODE',sortable:true,title:'项目编号',width:'25%'},
				        {field:'PROJECT_NAME',sortable:true,title:'项目名称',width:'25%'},
				        {field:'CREATE_TIME',sortable:true,title:'创建时间',width:'25%'},
				        {field:'aa',title:'操作',sortable:true,align:'center',width:'25%',
							formatter: function(value,row,index){
								show = "<a href='${emms}/system/mainProject.do?cmd=edit&projectId="
										+ row.PROJECT_ID
										+ "' target='_self'>查看</a>&nbsp;&nbsp;&nbsp;";
								return show;
							}}
				    ]]
				});
			});
			
			/* function startProject(projectId){
				$.ajax({
					type: "POST",
		            url:"${emms}/mainProject/startProject.do",
		            data:{projectId : projectId},
		            async: false,
		            success: function(data) {
		            	alert(data);
		            	window.location= "${emms}/mainProject/query.do";
		            }
				});
				
			} */
		</script>
	</body>
</html>
