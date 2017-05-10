<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
	<title>库存台账</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
	</head>
	<body>
		<div class="easyui-panel" title="首页->库存台账->库存明细查询" data-options="fit:true,border:false">
			<form id="query" method="post">
				<div style="margin:20px">
					<input class="easyui-textbox" id="wbsCode"  style="width:30%" data-options="label:'项目编码:'"/>
					<select class="easyui-combobox" id="stockState" style="width:30%" data-options="label:'库存状态:'"></select>
					<input class="easyui-textbox" id="storagelocationCode" style="width:30%" data-options="label:'储位编码:'"/>
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				</div>
			</form>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="库存台账明细">
			</table>
		</div> 
		<script type="text/javascript">
			$('#stockState').combobox({
				url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=stock_state',
				valueField: 'dictionaryCode',
				textField: 'dictionaryName',
				multiple:false
			});
			$(function(){
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/stock/stock.do?cmd=loadStockListData',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    queryParams: {
				    	"wbsCode" : $("#wbsCode").val(),
				    	"stockState" : $('#stockState').combobox('getValue'),
				    	"storagelocationCode" : $("#storagelocationCode").val()
					},
					loadFilter: function(data) {
						var result = {}
						var resultList = [];
						var rows =  data.rows;
						for (var i = 0; i < rows.length; i++) {
							var result = {};
							if(null != rows[i].materials){
								result.materialsCode = rows[i].materials.materialsCode;
								result.materialsDescribe = rows[i].materials.materialsDescribe;
							}
							if(null != rows[i].wbs){
								result.wbsCode = rows[i].wbs.projectCode;
								result.wbsName = rows[i].wbs.projectName;
							}
							result.stockNum = rows[i].stockNum;
							result.lockNum = rows[i].lockNum;
							if(rows[i].stockState == 'stockUse'){
								result.stockState = '可用';
							}else if(rows[i].stockState == 'outStockFinish'){
								result.stockState = '出库完成';
							}
							if(null != rows[i].storagelocation){
								result.storagelocationCode = rows[i].storagelocation.storagelocationCode;
							}
							resultList.push(result);
						}
						result.total = data.total;
						result.rows = resultList;
						return result;
					},
				    columns:[[
				        {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'13%'},
				        {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'13%'},
				        {field:'wbsCode',sortable:true,title:'项目编码',align:'center',width:'13%'},
						{field:'wbsName',sortable:true,title:'项目名称',align:'center',width:'13%'},
				        {field:'stockNum',sortable:true,title:'库存数量',align:'center',width:'12%'},
						{field:'lockNum',sortable:true,title:'锁定数量',align:'center',width:'12%'},
				        {field:'stockState',sortable:true,title:'库存状态',align:'center',width:'12%'},
				        {field:'storagelocationCode',sortable:true,title:'储位编码',align:'center',width:'13%'}
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
		</script>
	</body>
</html>
