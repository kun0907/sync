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

		<table id="detailTable" auto-resize="true" class="easyui-datagrid" title="物资料表明细" width="100%">
		</table>

	</div>
	<script type="text/javascript">
	$(function(){
		$('#detailTable').datagrid({
		    url:'${emms}/design/materialstableImprot.do?cmd=loadMTdetailed',
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
		       	{field:'drawingNumberCode',sortable:true,title:'设计图编号',align:'center',width:'10%'},
		       	{field:'drawingNumberVersion',sortable:true,title:'版次',align:'center'},
		       	{field:'projectCodeSeq',sortable:true,title:'WBS编码',align:'center',width:'10%'},
		       	{field:'drawingDetailedNo',sortable:true,title:'序号',align:'center'},
		       	{field:'designCode',sortable:true,title:'物资编码',align:'center'},
		       	{field:'designDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
		       	{field:'designUnit',sortable:true,title:'计量单位',align:'center'},
		       	{field:'designCount',sortable:true,title:'设计数量',align:'center'},
		       	{field:'overrun',sortable:true,title:'裕量',align:'center',width:'6%'},
		       	{field:'totalCount',sortable:true,title:'总量',align:'center',width:'6%'},
		       	{field:'extra1',sortable:true,title:'附加1',align:'center',width:'6%'},
		       	{field:'extra2',sortable:true,title:'附加2',align:'center',width:'6%'},
		       	{field:'extra3',sortable:true,title:'附加3',align:'center',width:'6%'},
		       	{field:'extra4',sortable:true,title:'附加4',align:'center',width:'6%'},
		    ]]
		});
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
	});
	</script>
</body>
</html>
