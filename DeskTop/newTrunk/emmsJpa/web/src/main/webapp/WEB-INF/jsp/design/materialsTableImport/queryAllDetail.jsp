<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body>
	<div class="easyui-panel"  data-options="fit:true,border:false">
		<form id="query" method="post">
			<div style="padding:10px">
				<input class="easyui-textbox" id="drawingNumberCode"  style="width:30%" data-options="label:'施工图号:'">
				<select class="easyui-combobox" id="designOrgId1" style="width:30%" data-options="label:'设计院:'"></select>
				<input  id="projectId" type="hidden" value="${wbsId}"></br></br>
				设计院物资编码:<input class="easyui-textbox" id="designCode"  style="width:21%" >
				<input class="easyui-textbox" id="designDescribe" style="width:30%" data-options="label:'物资描述:'">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
			</div>
		</form>
		
		<table id="table" auto-resize="true" class="" title="料表图号明细列表" width="100%" style="height:375px">
		</table>

	</div>
	<script type="text/javascript">
		$(function(){
			$('#designOrgId1').combobox({
				url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=design',
				valueField: 'orgId',
				textField: 'orgName',
				multiple:false
			});
			query();
		});
		function query(){
			$('#table').treegrid({
			    url:'${emms}/design/materialstableImprot.do?cmd=loadDrawingDetailByCondition',
			    method: 'POST',
			    fitColumns: true,
			    rownumbers: true,
			    showFooter: true,
				selectOnCheck:true,
				checkOnSelect:true,
				singleSelect: false,
				idField:'drawingDetailedId',
				treeField:'designCode',
			    queryParams: {
			    	"drawingNumberCode" : $("#drawingNumberCode").val(),
			    	"designOrgId" : $("#designOrgId1").combobox('getValue'),
			    	"projectId" : $('#projectId').val(),
			    	"designCode" : $('#designCode').val(),
			    	"designDescribe" : $('#designDescribe').val()
				},
				onLoadSuccess: function(){
					$(this).datagrid('freezeRow',-1).datagrid('freezeRow',-1);
				},
			    columns:[[
					{field:'drawingDetailedId',checkbox:true},
			       	{field:'drawingNumberCode',sortable:true,title:'施工图号',align:'center'},
					{field:'drawingNumberVersion',sortable:true,title:'版次',align:'center'},
					{field:'projectCodeSeq',sortable:true,title:'WBS编码',align:'center'},
					{field:'drawingDetailedNo',sortable:true,title:'序号',align:'center'},
					{field:'designCode',sortable:true,title:'设计院物资编码',align:'center'},
					{field:'materialsCode',sortable:true,title:'系统物资编码',align:'center'},
					{field:'designDescribe',sortable:true,title:'物资描述',align:'center',width:'15%',
						formatter: function(value,row,index){
							if (value.length>12){
								return "<span title='" + value + "'>" + value.substring(0, 12)+"..." + "</span>";
							} else {
								return value;
							}}
						},
					{field:'extra1',sortable:true,title:'附加1',align:'center'},
					{field:'extra2',sortable:true,title:'附加2',align:'center'},
					{field:'extra3',sortable:true,title:'附加3',align:'center'},
					{field:'extra4',sortable:true,title:'附加4',align:'center'},
					{field:'designUnit',sortable:true,title:'施工结算单位',align:'center'},
					{field:'totalCount',sortable:true,title:'总量',align:'center',
						formatter:function (value, row, index){
							if(value==-1){
								return "";
							}else{
								return value;
							}
						}
					},
			    ]]
			});
		}
		function clearForm(){
			$('#query').form('clear');
		}
		function confirm(){
			var rows = $('#table').datagrid('getSelections');
			if(rows.length == 0){
				$.messager.alert("操作提示","请选择料表明细信息","warning");
				return false;
			}else{
				window.parent.frames["mainFrame"].checkDrawing(rows);
				top.$("#dialog").dialog("close");
			}
		}
	</script>

</body>
</html>
