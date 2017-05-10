<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>列表页</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
	<body>
		<div class="easyui-panel" title="首页->系统管理->用户授权管理->角色列表" data-options="fit:true,border:false">
			<c:if test="${not empty userId}">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="saveUserRole()">保存</a>
			</c:if>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="角色列表" width="100%"></table>
		</div>
		<script type="text/javascript">
				$(function(){
					query();
				});
				function query(){
					$('#table').datagrid({
					    url:'${emms}/system/userRole.do?cmd=selectRoleByOrgData&orgId=${orgId}&userId=${userId}',
					    method: 'POST',
					    selectOnCheck:true,
					    checkOnSelect:true,
					    columns:[[
							{field:'roleId',checkbox:true},
					        {field:'roleName',sortable:true,title:'角色名称',align:'center',width:'100%'}
					    ]],
					    onLoadSuccess:function(row){
					    	var rowData = row.rows;
					    	$.each(rowData,function(idx,val){
					    		if(val.checked){
					    			 $("#table").datagrid("selectRow", idx);
					    		}
					    	});
					    }
					});
				}
				function saveUserRole(){
					var checkedItems = $('#table').datagrid('getChecked');
					var roleId = [];
					$.each(checkedItems, function(index, item){
						roleId.push(item.roleId);
					});               
					 $.ajax({
			            type: "POST",
			            url:"${emms}/system/userRole.do?cmd=saveAuthorizeUser&userId=${userId}&roleIds="+roleId.join(","),
			            data:$('#department').serialize(),
			            async: false,
			            success: function(data) {
			            	alert(data);
			            	window.top.frames["mainFrame"].location= "${emms}/system/userRole.do?cmd=frame";
			            }
			        });
				}
		</script>
	</body>
</html>
