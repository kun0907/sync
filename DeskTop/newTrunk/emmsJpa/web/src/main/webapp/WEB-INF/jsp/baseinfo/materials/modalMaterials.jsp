<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" data-options="fit:true,border:false">
	<form id="query" method="post">
		<div style="margin:20px">
			<input class="easyui-textbox" id="materialsCode"   style="width:25%" data-options="label:'物资编码:'">
			<input class="easyui-textbox" id="materialsDescribe"  style="width:25%" data-options="label:'物资描述:'">
			<select class="easyui-combobox" id="materialsCategory"  style="width:25%" data-options="label:'物料类别:'"></select>
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
		</div>
	</form>
	<table id="table" class="easyui-datagrid" title="物资列表"  style="height:350px">
	</table>
</div>
<script type="text/javascript">
	$('#materialsCategory').combobox({
		url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=MaterialsTypeCategory',
		valueField: 'dictionaryCode',
		textField: 'dictionaryName',
		multiple:true
	});
	$('#materialsState').combobox({
		url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=materialsState',
		valueField: 'dictionaryCode',
		textField: 'dictionaryName',
		multiple:true
	});
	$(function(){
		query();
	});
	function query(){
		$('#table').datagrid({
			url:'${emms}/baseinfo/materials.do?cmd=loadModalMaterial',
			method: 'POST',
			pagination: true,
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			selectOnCheck:true,
			checkOnSelect:true,
			queryParams: {
				"materialsCode" : $("#materialsCode").val(),
				"materialsDescribe" : $("#materialsDescribe").val(),
				"materialsCategory" : $('#materialsCategory').combobox('getValue')
			},
			onLoadSuccess: function(){
				$(this).datagrid('freezeRow',-1).datagrid('freezeRow',-1);
			},
			columns:[[
				{field:'materialsId',checkbox:true},
				{field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'20%'},
				{field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'20%'},
				{field:'materialsCategory',sortable:true,title:'是否设备',align:'center',width:'20%',
					formatter: function(value,row,index){
						if (value == "w") {
							return "N";
						} else if (value == "s") {
							return "Y";
						}
					}},
				{field:'materialsUnitMain',sortable:true,title:'主计量单位',align:'center',width:'20%'},
				{field:'materialsState',sortable:true,title:'物资状态',align:'center',width:'18%',
					formatter: function(value,row,index){
						if (value == "new") {
							return "未提交";
						} else if (value == "commit") {
							return "已提交";
						}else if (value == "approving") {
							return "审批中";
						}else if (value == "success") {
							return "审批通过";
						}else if (value == "failure") {
							return "审批不通过";
						}
					}}
			]]
		});
	}
	function confirm(){
		var rows = $('#table').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert("操作提示","请选择物料信息","warning");
			return false;
		}else{
			window.parent.frames["mainFrame"].checkMaterials(rows);
			top.$("#dialog").dialog("close");
		}
	}
	function clearForm(){
		$('#query').form('clear');
	}
</script>
</body>
</html>
