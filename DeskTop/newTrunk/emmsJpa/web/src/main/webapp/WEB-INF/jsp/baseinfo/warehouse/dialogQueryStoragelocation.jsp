<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
		<div class="easyui-panel" title="公共仓库库区树>储位查询" data-options="fit:true,border:false">
			<form id="query" method="post">
				<div style="padding:10px">
					<input class="easyui-textbox" id="storagelocationCode"  style="width:30%" data-options="label:'储位编码:'">
					<input class="easyui-textbox" id="storagelocationName" style="width:30%" data-options="label:'储位名称:'">
				    <select class="easyui-combobox" id="storagelocationType" style="width:30%" data-options="label:'储位类型:'"></select>
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
				</div>
			</form>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="仓库列表">
			</table>
		</div> 
		<script type="text/javascript">
			$(function(){
				$('#storagelocationType').combobox({
			        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=storagelocationtype',
			        valueField: 'dictionaryCode',
			        textField: 'dictionaryName',
			        multiple:false
			    });
				query();
			});
			function query(){
				url='${emms}/baseinfo/warehouse.do?cmd=dialogSelectStoragelocation&parentId=${parentId}'
				$('#table').datagrid({
				    url:url,
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    queryParams: {
				    	"storagelocationCode" : $("#storagelocationCode").val(),
				    	"storagelocationName" : $("#storagelocationName").val(),
				    	"storagelocationType" : $('#storagelocationType').combobox('getValue')
					},
				    columns:[[
				        {field:'storagelocationCode',sortable:true,title:'储位编码',align:'center',width:'42%'},
				        {field:'storagelocationName',sortable:true,title:'储位名称',align:'center',width:'30%'},
				        {field:'storagelocationType',sortable:true,title:'储位类型',align:'center',width:'30%'},
				    ]]
				});
			}
			function confirm(){
				var rows = $('#table').datagrid('getSelections');
				if(rows.length == 0){
					$.messager.alert("操作提示", "请选择储位！","warning");
					return false;
				}else if(rows.length >1){
					$.messager.alert("操作提示", "请选择一条储位！","warning");
					return false;
				}
				else{
					window.parent.parent.frames["mainFrame"].checkStoragelocation(rows,'${index}');
					top.$("#dialog").dialog("close");
				}
			}
			function clearForm(){
				$('#query').form('clear');
			}
		</script>
	</body>
</html>
