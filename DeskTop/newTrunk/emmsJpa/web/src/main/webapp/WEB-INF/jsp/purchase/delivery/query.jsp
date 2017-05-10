<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->采购管理->供应商发货管理->发货单列表" data-options="fit:true,border:false">
		<form id="query" method="post">
			<div style="margin:20px">
				<input class="easyui-textbox" id="deliveryNo"  style="width:30%" data-options="label:'发货单号:'">
				<input class="easyui-textbox" id="supplierName" style="width:30%" data-options="label:'供应商名称:'">
				<input class="easyui-combobox" id="deliveryState" style="width:30%" data-options="label:'单据状态:'">
			</div>
			<div style="margin:20px">
				<input class="easyui-textbox" id="createUserName" style="width:30%" data-options="label:'创建人:'">
				<input class="easyui-datebox" id="beginTime" editable="false" style="width:18%" data-options="label:'创建时间:'">~
				<input class="easyui-datebox" id="endTime" editable="false" style="width:12%">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				<a href="${emms}/purchase/supplierDelivery.do?cmd=editDeliveryPage" iconCls='icon-add' class="easyui-linkbutton">新建</a>
			</div>
		</form>
		<table id="table" auto-resize="true" class="easyui-datagrid" title="发货单列表" width="100%">
		</table>
	</div>
	<script type="text/javascript">
		$('#deliveryState').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=delivery_state',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName',
			multiple:false
		});
			$(function(){

				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/purchase/supplierDelivery.do?cmd=loadDeliveryListData&deliveryId=${deliveryId}',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
					singleSelect:true,
				    queryParams: {
				    	"deliveryNo" : $("#deliveryNo").val(),
				    	"supplierName" : $("#supplierName").val(),
						"deliveryState" : $("#deliveryState").val(),
						"createUserName" : $("#createUserName").val(),
						"beginTime" : $("#beginTime").val(),
						"endTime" : $("#endTime").val()
					},
				    columns:[[
				        {field:'deliveryNo',sortable:true,title:'发货单号',align:'center',width:'15%',
							formatter: function(value,row,index){
								return '<a class="easyui-linkbutton" style="color:blue" href="${emms}/purchase/supplierDelivery.do?cmd=viewDeliveryPage&deliveryId='+row.deliveryId+'" target="_self">'+row.deliveryNo+'</a>';
							}},
				        {field:'deliveryState',sortable:true,title:'单据状态',align:'center',width:'10%',
							formatter: function(value, row, index){
							if (value == "deliveryNew") {
								return "新建";
							} else if (value == "deliveryCommit") {
								return "提交";
							}else if (value == "delivery") {
								return "发货";
							}
						}},
				        {field:'supplierName',sortable:true,title:'供应商名称',align:'center',width:'10%'},
						{field:'packageNum',sortable:true,title:'包装数量',align:'center',width:'10%'},
				        {field:'expectedArrivalDate',sortable:true,title:'预到货日期',align:'center',width:'10%'},
						{field:'deliveryDate',sortable:true,title:'发货日期',align:'center',width:'10%'},
						{field:'createTime',sortable:true,title:'创建时间',align:'center',width:'10%'},
						{field:'createUserName',sortable:true,title:'创建人',align:'center',width:'10%'},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'20%',
							formatter: function(value,row,index){
								var show = ''
								if(row.deliveryState == 'deliveryNew'){
									show += "<a class='easyui-linkbutton' href='${emms}/purchase/supplierDelivery.do?cmd=editDeliveryPage&deliveryId="
									+ row.deliveryId
									+ "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
									show += "<a class='easyui-linkbutton' onclick='delivery(\""+row.deliveryId+"\")'"
									+ " target='_self'>发货</a>&nbsp;&nbsp;&nbsp;";
								}
								return show;
							}}
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
			function delivery(deliveryId){
				$.messager.confirm("操作提示", "确定要发货吗？", function (data) {
					if(data){
						$.ajax({
							type: "POST",
							url:"${emms}/purchase/supplierDelivery.do?cmd=delivery&deliveryId="+deliveryId,
							async: false,
							success: function(data) {
								if(data == 'true'){
									$.messager.alert("操作提示", "发货成功","info");
									query();
								}else{
									$.messager.alert("操作提示",data,"warning");
								}
							}
						});
					}
				});
			}
		</script>
</body>
</html>
