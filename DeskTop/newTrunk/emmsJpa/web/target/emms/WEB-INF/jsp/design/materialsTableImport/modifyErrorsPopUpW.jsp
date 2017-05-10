<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body>

<div class="easyui-panel"  data-options="fit:true,border:false">

	<table id="MT" auto-resize="true" class="easyui-datagrid" title="物资料表头信息" width="100%">
	</table>

	<table id="onlyAandB" auto-resize="true" class="easyui-datagrid" title="完整性错误列表" width="100%">
	</table>

	<table id="containCnotD" auto-resize="true" class="easyui-datagrid" title="本料表内重复" width="100%">
	</table>

	<table id="containD" auto-resize="true" class="easyui-datagrid" title="与历史信息重复" width="100%">
	</table>

	<div style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmit()">保存</a>
	</div>

</div>
<script type="text/javascript">
	var dictionary= {};
	$.ajax({
		url:'${emms}/baseinfo/project.do?cmd=selectToOrderCombotree',
		dataType : 'json',
		type : 'GET',
		async:false,
		success: function (data){
			wbsTree = data;
			getChildren(data);
			console.log(dictionary);
		}
	});
	function getChildren(childrens){
		for(var i=0;i<childrens.length;i++){
			var obj=childrens[i].id;
			dictionary[obj]=childrens[i].text;
			if(childrens[i].children.length>0){
				getChildren(childrens[i].children)
			}
		}
	}
	function getValueByKey(key){
		return dictionary[key];
	}

	var data111;
	var lastIndex1;
	var lastIndex2;
	var lastIndex3;
	var m = ["图号","版次","WBS编码","序号","物资编码","物资描述","计量单位","设计数量","裕量","总量","附加1","附加2","附加3","附加4"];
	$(function(){
		$('#MT').datagrid({
			url:'${emms}/design/materialstableImprot.do?cmd=loadMT',
			method: 'POST',
			pagination: false,
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			singleSelect: true,
			queryParams: {
				"materialsTableId" : "${materialsTableId}"
			},
			columns:[[
				{field:'materialsTableCode',sortable:true,title:'料表名',align:'center',width:'25%'},
				{field:'designOrgName',sortable:true,title:'设计院',align:'center',width:'25%'},
				{field:'materialsTableType',sortable:true,title:'类型',align:'center',width:'25%',
					formatter:function (value, row, index){
						if(value=='w'){
							return '材料';
						}else{
							return '设备';
						}
					}},
				{field:'createTime',sortable:true,title:'导入时间',align:'center',width:'25%'}
			]]
		});

		$('#onlyAandB').datagrid({
			url:'${emms}/design/materialstableImprot.do?cmd=loadErrorTypeOnlyAandB',
			method: 'POST',
			pagination: false,
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			singleSelect: true,
			onLoadSuccess:function(data){
				data111 = data;
			},
			queryParams: {
				"materialsTableId" : "${materialsTableId}"
			},
			toolbar: [{
				iconCls: 'icon-edit',
				handler: function(){
					var table = $('#onlyAandB');
					var data = $("#onlyAandB").datagrid("getData");
					for(var i=0; i<data.total; i++){
						table.datagrid('beginEdit', i);
					}
				}
			},'-',{
				iconCls: 'icon-ok',
				handler: function(){
					var table = $('#onlyAandB');
					var data = $("#onlyAandB").datagrid("getData");
					for(var i=0; i<data.total; i++){
						table.datagrid('endEdit', i);
					}
				}
			},'-',{
				iconCls: 'icon-remove',
				handler: function(){
					deleteRow("onlyAandB");
				}
			}],
			columns:[[
				{field:'drawingNumberCode',sortable:true,title:'设计图编号',align:'center',editor:{type:'validatebox'}},
				{field:'drawingNumberVersion',sortable:true,title:'版次',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'projectId',sortable:true,title:'WBS编码',align:'center',
					formatter: function(value,row,index){
						console.log(value);
						return getValueByKey(value);
					},
					editor:{type:'combotree',options:{editable:false,method:'get',url: '${emms}/baseinfo/project.do?cmd=selectToOrderCombotree'}}
				},
				{field:'drawingDetailedNo',sortable:true,title:'序号',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'designCode',sortable:true,title:'设计院物资编码',align:'center',editor:{type:'validatebox'}},
				{field:'designDescribe',sortable:true,title:'物资描述',
					formatter:function (value, row, index){
						if(value.length>10){
							return "<span title='" + value + "'>" + value.substring(0, 10)+"..." + "</span>";
						}else{
							return value;
						}
					}
					,align:'center',editor:{type:'validatebox'}},
				{field:'designUnit',sortable:true,title:'计量单位',align:'center',editor:{type:'validatebox'}},
				{field:'designCount',sortable:true,title:'设计数量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'overrun',sortable:true,title:'裕量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'totalCount',sortable:true,title:'总量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'extra1',sortable:true,title:'附加1',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'extra2',sortable:true,title:'附加2',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'extra3',sortable:true,title:'附加3',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'extra4',sortable:true,title:'附加4',width:'6%',align:'center',editor:{type:'validatebox'}}
			]],
			view: detailview,
			detailFormatter: function(rowIndex, rowData){
				var str = rowData.errorMessage.split(",");
				var em = str[1].split("|");
				var v = "";
				for(var i=0; i<em.length; i++){
					if(em[i]!=""){
						v += m[i] + "输入有误，";
					}
				}
				return v;
			}
		});


		$('#containCnotD').datagrid({
			url:'${emms}/design/materialstableImprot.do?cmd=loadErrorTypeContainCnotD',
			method: 'POST',
			pagination: false,
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			singleSelect: true,
			queryParams: {
				"materialsTableId" : "${materialsTableId}"
			},
			toolbar: [{
				iconCls: 'icon-edit',
				handler: function(){
					var table = $('#containCnotD');
					var data = $("#containCnotD").datagrid("getData");
					for(var i=0; i<data.total; i++){
						table.datagrid('beginEdit', i);
					}
				}
			},'-',{
				iconCls: 'icon-ok',
				handler: function(){
					var table = $('#containCnotD');
					var data = $("#containCnotD").datagrid("getData");
					for(var i=0; i<data.total; i++){
						table.datagrid('endEdit', i);
					}
					$('#containCnotD').edatagrid('loadData', $("#containCnotD").datagrid("getData"));
				}
			},'-',{
				iconCls: 'icon-remove',
				handler: function(){
					deleteRow("containCnotD");
				}
			}],
			columns:[[
				{field:'drawingNumberCode',sortable:true,title:'设计图编号',align:'center',editor:{type:'validatebox'}},
				{field:'drawingNumberVersion',sortable:true,title:'版次',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'projectId',sortable:true,title:'WBS编码',align:'center',
					formatter: function(value,row,index){
						console.log(value);
						return getValueByKey(value);
					},
					editor:{type:'combotree',options:{editable:false,method:'get',url: '${emms}/baseinfo/project.do?cmd=selectToOrderCombotree'}}
				},
				{field:'drawingDetailedNo',sortable:true,title:'序号',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'designCode',sortable:true,title:'设计院物资编码',align:'center',editor:{type:'validatebox'}},
				{field:'designDescribe',sortable:true,title:'物资描述',align:'center',
					formatter:function (value, row, index){
						if(value.length>10){
							return "<span title='" + value + "'>" + value.substring(0, 10)+"..." + "</span>";
						}else{
							return value;
						}
					}
					,editor:{type:'validatebox'}},
				{field:'designUnit',sortable:true,title:'计量单位',align:'center',editor:{type:'validatebox'}},
				{field:'designCount',sortable:true,title:'设计数量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'overrun',sortable:true,title:'裕量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'totalCount',sortable:true,title:'总量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'extra1',sortable:true,title:'附加1',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'extra2',sortable:true,title:'附加2',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'extra3',sortable:true,title:'附加3',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'extra4',sortable:true,title:'附加4',width:'6%',align:'center',editor:{type:'validatebox'}},
				{field:'errorMessage',sortable:true,title:'错误信息',align:'center',
					formatter:function(value,row,index){
						var str = row.errorMessage.split(",");
						var em = str[1].split("|");
						var v = "";
						for(var i=0; i<em.length; i++){
							if(em[i]!=""){
								v += m[i] + "输入有误，";
							}
						}
						return v;
					}
				}
			]],
			groupField:'drawingNumberCode',
			groupField:'drawingDetailedNo',
			view: groupview,
			groupFormatter:function(value, rows){
				var drawingNumberCode = rows[0].drawingNumberCode;
				var drawingDetailedNo = rows[0].drawingDetailedNo;
				if(drawingNumberCode == null || drawingNumberCode==""){
					drawingNumberCode="空";
				}
				if(drawingDetailedNo == null || drawingDetailedNo==""){
					drawingDetailedNo="空";
				}
				return "重复个数: " + rows.length;
			}
		});


		$('#containD').datagrid({
			url:'${emms}/design/materialstableImprot.do?cmd=loadErrorTypeContainD',
			method: 'POST',
			pagination: false,
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			singleSelect: true,
			queryParams: {
				"materialsTableId" : "${materialsTableId}"
			},
			toolbar: [{
				iconCls: 'icon-edit',
				handler: function(){
					var table = $('#containD');
					var data = $("#containD").datagrid("getData");
					for(var i=0; i<data.total; i++){
						table.datagrid('beginEdit', i);
					}
				}
			},'-',{
				iconCls: 'icon-ok',
				handler: function(){
					var table = $('#containD');
					var data = $("#containD").datagrid("getData");
					for(var i=0; i<data.total; i++){
						table.datagrid('endEdit', i);
					}
				}
			},'-',{
				iconCls: 'icon-remove',
				handler: function(){
					deleteRow("containD");
				}
			}],
			columns:[[
				{field:'drawingNumberCode',sortable:true,title:'设计图编号',align:'center',editor:{type:'validatebox'}},
				{field:'drawingNumberVersion',sortable:true,title:'版次',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'projectId',sortable:true,title:'WBS编码',align:'center',
					formatter: function(value,row,index){
						console.log(value);
						return getValueByKey(value);
					},
					editor:{type:'combotree',options:{editable:false,method:'get',url: '${emms}/baseinfo/project.do?cmd=selectToOrderCombotree'}}
				},
				{field:'drawingDetailedNo',sortable:true,title:'序号',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'designCode',sortable:true,title:'设计院物资编码',align:'center',editor:{type:'validatebox'}},
				{field:'designDescribe',sortable:true,title:'物资描述',align:'center',
					formatter:function (value, row, index){
						if(value.length>10){
							return "<span title='" + value + "'>" + value.substring(0, 10)+"..." + "</span>";
						}else{
							return value;
						}
					}
					,editor:{type:'validatebox'}},
				{field:'designUnit',sortable:true,title:'计量单位',align:'center',editor:{type:'validatebox'}},
				{field:'designCount',sortable:true,title:'设计数量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'overrun',sortable:true,title:'裕量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'totalCount',sortable:true,title:'总量',align:'center',editor:{type:'validatebox'},
					formatter:function (value, row, index){
						if(value==-1){
							return "";
						}else{
							return value;
						}
					}
				},
				{field:'extra1',sortable:true,title:'附加1',align:'center',width:'6%',editor:{type:'validatebox'}},
				{field:'extra2',sortable:true,title:'附加2',align:'center',width:'6%',editor:{type:'validatebox'}},
				{field:'extra3',sortable:true,title:'附加3',align:'center',width:'6%',editor:{type:'validatebox'}},
				{field:'extra4',sortable:true,title:'附加4',align:'center',width:'6%',editor:{type:'validatebox'}}
			]],
			view: detailview,
			detailFormatter: function(rowIndex, rowData){
				var str = rowData.errorMessage.split(",");
				var em = str[1].split("|");
				var v = "";
				for(var i=0; i<em.length; i++){
					if(em[i]!=""){
						v += m[i] + "输入有误，";
					}
				}
				return v;
			}
		});


	});

	//提交
	function ajaxSubmit() {
		var mt = $("#MT").datagrid("getData");
		var table1 =  $("#onlyAandB").datagrid("getData");
		var table2 =  $("#containCnotD").datagrid("getData");
		var table3 =  $("#containD").datagrid("getData");
		var ddl = {
			"detailList":table1.rows.concat(table2.rows.concat(table3.rows)),
			"designOrgId":mt.rows[0].designOrgId
		};
		$.ajax({
			type: 'POST',
			url: "${emms}/design/materialstableImprot.do?cmd=saveModifyErrors",
			data: JSON.stringify(ddl),
			dataType: 'json',
			contentType: "application/json;charset=utf-8",
			success: function (result) {
				console.log(result);
				if(result =="true"){
					$.messager.alert("提示","保存成功");
					top.$("#dialog").dialog("close");
					window.parent.frames["mainFrame"].query();
				}
			}
		});
	}

	//删除
	function deleteRow(tableName){
		console.log(tableName);
		var row = $('#'+tableName).datagrid('getRowIndex',$('#'+tableName).datagrid('getSelected'));
		console.log(row);
		if (row>-1){
			var drawingDetailedId = $('#'+tableName).datagrid('getSelected').drawingDetailedId;
			if(confirm('是否确认删除？')){
				$.ajax({
					type: 'POST',
					url: "${emms}/design/materialstableImprot.do?cmd=deleteRow&drawingDetailedId=" + drawingDetailedId,
					contentType: "application/json;charset=utf-8",
					success: function (result) {
						if(result==true){
							$('#'+tableName).edatagrid('deleteRow',row);
							$.messager.alert("提示","删除成功");
						}else{
							$.messager.alert("提示","删除失败");
						}
					}
				});
			}
		}else{
			$.messager.alert('提示','请选择一行');
		}
	}


</script>
</body>
</html>
