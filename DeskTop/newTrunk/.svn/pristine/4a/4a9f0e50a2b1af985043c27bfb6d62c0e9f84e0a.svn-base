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
			<c:if test="${not empty deliveryNo}">
			    <input class="easyui-textbox" id="deliveryNo"   style="width:25%" data-options="label:'发货单号:',readonly:true">
			</c:if>
			<c:if test="${empty deliveryNo}">
				<input class="easyui-textbox" id="deliveryNo"   style="width:25%" data-options="label:'发货单号:'">
			</c:if>
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
		</div>
	</form>
	<table id="packageTable" class="easyui-datagrid" title="发货单包装列表"  style="height:350px">
	</table>
</div>
<script type="text/javascript">
	var orgType = {};
	$(function(){
		console.log(1,'${deliveryNo}');
		if('${deliveryNo}'){
			$("#deliveryNo").val('${deliveryNo}');
		}
		$.ajax({
			url:'${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=package_type',
			dataType : 'json',
			type : 'POST',
			async:false,
			success: function (data){
				for(var i=0;i<data.length;i++){
					var obj=data[i].dictionaryCode;
					orgType[obj]=data[i].dictionaryName;
				}
			}
		});
		query();
	});
	function getValueByKey(key){
		if(null != key){
			return orgType[key];
		}else{
			return '';
		}
	}
	function query(){
		$('#packageTable').datagrid({
			url:'${emms}/purchase/delivery/package.do?cmd=queryPackageModal',
			method: 'POST',
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			selectOnCheck:true,
			checkOnSelect:true,
			queryParams: {
				"deliveryNo" : $("#deliveryNo").val(),
				"supplierId" : '${supplierId}'
			},
			onLoadSuccess: function(data){
				$(this).datagrid('freezeRow',-1).datagrid('freezeRow',-1);
			},
			columns:[[
				{field:'packingId',checkbox:true},
				{field:'packingNo',sortable:true,title:'包装号',align:'center',width:'20%'},
				{field:'packingType',sortable:true,title:'包装类型',align:'center',width:'20%',
					formatter: function(value,row,index){
						return getValueByKey(value);
					}},
				{field:'packingWeight',sortable:true,title:'包装重量',align:'center',width:'20%'},
				{field:'packingSize',sortable:true,title:'包装尺寸',align:'center',width:'20%'},
				{field:'deliveryNo',sortable:true,title:'发货单号',align:'center',width:'20%'}
			]]
		});
	}
	function confirm(){
		var rows = $('#packageTable').datagrid('getSelections');
		var arr=[];
		if(rows.length == 0){
			$.messager.confirm("操作提示","请选择包装信息","warning");
			return false;
		}else{
			arr.push(rows[0].deliveryId);
			for(var i=1;i<rows.length;i++){
				if(arr.indexOf(rows[i].deliveryId)!=-1){
					arr.push(rows[i].deliveryId);
				}else{
					$.messager.confirm("操作提示","请选择同一发货单记录","warning");
					return false;
					break;
				}
			}
			console.log(rows);
			window.parent.frames["mainFrame"].checkDelivery(rows);
			top.$("#dialog").dialog("close");
		}
	}
	function clearForm(){
		$('#query').form('clear');
	}
</script>
</body>
</html>
