<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>管理</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<link rel="stylesheet" href="${emms}/css/indexStyle.css">
</head>

<body style="width:958px; height:450px;">
<table id="design" auto-resize="true" class="easyui-datagrid" title="设计院信息">
</table>
<div class="easyui-panel" data-options="fit:true,border:false">
	<form id="query" method="post">
		<div style="padding:10px">
			<input class="easyui-textbox" id="materialsCode"   style="width:25%" data-options="label:'物资编码:'">
			<input class="easyui-textbox" id="materialsDescribe"  style="width:25%" data-options="label:'物资描述:'">
			<select class="easyui-combobox" id="materialsCategory"  style="width:25%" data-options="label:'物料类别:'"></select>
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="queryMaterials()">查询</a>
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
	$(function(){
		queryDesign();
		queryMaterials();
	});
	function queryMaterials(){
		$('#table').datagrid({
			url:'${emms}/baseinfo/materials.do?cmd=loadMaterialListData',
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
				{field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'12%'},
				{field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'15%'},
				{field:'materialsCategory',sortable:true,title:'类别',align:'center',width:'10%',
					formatter: function(value,row,index){
						if(value=='w'){
							return "材料";
						}else{
							return "设备";
						}
					}
				},
				{field:'additional1',sortable:true,title:'附加1',align:'center',width:'10%'},
				{field:'additional2',sortable:true,title:'附加2',align:'center',width:'10%'},
				{field:'additional3',sortable:true,title:'附加3',align:'center',width:'10%'},
				{field:'additional4',sortable:true,title:'附加4',align:'center',width:'10%'},
				{field:'materialsUnitMain',sortable:true,title:'主计量单位',align:'center',width:'10%'},
				{field:'conversion',sortable:true,title:'计量单位换算',align:'center',width:'10%'},
			]]
		});
	}
	function queryDesign(){
		$('#design').datagrid({
			url:'${emms}/baseinfo/designCode.do?cmd=loadDesignCodeListData&designId=${designId}',
			method: 'POST',
			pagination: false,
			fitColumns: true,
			rownumbers: false,
			showFooter: true,
			singleSelect:false,
			columns:[[
				{field:'designCode',sortable:true,title:'设计院编码',align:'center',width:'10%'},
				{field:'designDescribe',sortable:true,title:'设计院描述',
					formatter: function(value,row,index){
						if (value.length>10){
							return "<span title='" + value + "'>" + value.substring(0, 10)+"..." + "</span>";
						} else {
							return value;
						}
					},align:'center',width:'16%'},
				{field:'designOrgId',sortable:true,title:'设计院',align:'center',width:'10%'},
				{field:'systemcodeId',sortable:true,title:'系统编码',align:'center',width:'10%'},
				{field:'designUnitMain',sortable:true,title:'主计量单位',align:'center',width:'8%'},
				{field:'additional1',sortable:true,title:'附加1',align:'center',width:'12%'},
				{field:'additional2',sortable:true,title:'附加2',align:'center',width:'12%'},
				{field:'additional3',sortable:true,title:'附加3',align:'center',width:'12%'},
				{field:'additional4',sortable:true,title:'附加4',align:'center',width:'12%'},
			]]
		});
	}
	function confirm(){
		var rows = $('#table').datagrid('getSelections');
		if(rows.length == 0){
			$.messager.alert("操作提示","请选择物料信息","warning");
			return false;
		}else if(rows.length > 1){
			$.messager.alert("操作提示","请选择一条物料信息","warning");
			return false;
		}else{
			window.parent.frames["mainFrame"].checkMaterials(rows[0],'${designId}');
			top.$("#dialog").dialog("close");
		}
	}
	function clearForm(){
		$('#query').form('clear');
	}
</script>
</body>
</html>
