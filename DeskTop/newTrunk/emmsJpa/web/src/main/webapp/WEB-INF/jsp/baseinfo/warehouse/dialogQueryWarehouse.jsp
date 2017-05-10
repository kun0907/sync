<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
		<div class="easyui-panel" title="公共仓库库区树->仓库查询" data-options="fit:true,border:false">
			<form id="query" method="post">
				<div style="padding:10px">
					<input class="easyui-textbox" id="warehouseCode"  style="width:30%" data-options="label:'仓库编码:'">
					<input class="easyui-textbox" id="warehouseName" style="width:30%" data-options="label:'仓库名称:'">
				    <select class="easyui-combobox" id="warehouseType" style="width:30%" data-options="label:'仓库类型:'"></select>
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				</div>
			</form>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="仓库列表">
			</table>
		</div> 
		<script type="text/javascript">
			$(function(){
				$('#warehouseType').combobox({
			        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=warehousetype',
			        valueField: 'dictionaryCode',
			        textField: 'dictionaryName',
			        multiple:false
			    });
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/baseinfo/warehouse.do?cmd=selectWareHouse',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    singleSelect:true,
				    queryParams: {
				    	"warehouseCode" : $("#warehouseCode").val(),
				    	"warehouseName" : $("#warehouseName").val(),
				    	"warehouseType" : $('#warehouseType').combobox('getValue')
					},
				    columns:[[
				        {field:'warehouseCode',sortable:true,title:'仓库编码',align:'center',width:'15%'},
				        {field:'warehouseName',sortable:true,title:'仓库名称',align:'center',width:'15%'},
				        {field:'country',sortable:true,title:'国家',align:'center',width:'15%'},
				        {field:'city',sortable:true,title:'市',align:'center',width:'15%'},
				        {field:'area',sortable:true,title:'区',align:'center',width:'15%'},
				        {field:'acreage',sortable:true,title:'面积',align:'center',width:'10%'},
				        {field:'warehouseType',sortable:true,title:'仓库类型',align:'center',width:'15%'},
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
		</script>
	</body>

</html>
