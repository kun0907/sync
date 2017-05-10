<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body>
	<div class="easyui-panel" title="首页->系统管理->组织机构管理->组织机构类型列表" data-options="fit:true,border:false">
		<table id="table" auto-resize="true" class="easyui-datagrid" title="机构类型列表" width="100%">
		</table>
	</div>
	<script type="text/javascript">
			$(function(){
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/system/organization.do?cmd=selectOrgType&orgId=${orgId}',
				    method: 'POST',
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    singleSelect:true,
				    columns:[[
				        {field:'orgType',sortable:true,title:'项目角色',align:'center',width:'50%'},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'50%',
							formatter: function(value,row,index){
								show = "<a href=\"javascript:allocateWBS(\'"+ row.orgTypeId + "\')\">配置</a>&nbsp;&nbsp;&nbsp;";
								return show;
							}}
				    ]]
				});
			}
		</script>
	<script type="text/javascript">
		function allocateWBS(orgTypeId){
			window.parent.frames["westFrame"].location.href = "${emms}/system/organization.do?cmd=WBSTree&type=${type}&contractorId=${contractorId}&orgId=${orgId}&orgTypeId="+orgTypeId;
		}
	</script>
</body>
</html>
