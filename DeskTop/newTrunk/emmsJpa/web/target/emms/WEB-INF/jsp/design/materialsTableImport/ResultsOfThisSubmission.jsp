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
		<table id="table" auto-resize="true" class="easyui-datagrid" title="未人工匹配列表" width="100%">
		</table>
	</div>
	<script type="text/javascript">
	$(function(){
		$('#table').datagrid({
		    url:'${emms}/design/materialstableImprot.do?cmd=loadResultsOfThisSubmission',
		    method: 'POST',
		    pagination: false,
		    fitColumns: true,
		    rownumbers: true,
		    showFooter: true,
		    queryParams: {
		    	"materialsTableId" : "${materialsTableId}"
			},
		    columns:[[
		       	{field:'drawingNumberCode',sortable:true,title:'设计图编号',align:'center',width:'10%'},
		       	{field:'drawingNumberVersion',sortable:true,title:'版次',align:'center'},
		       	{field:'projectCodeSeq',sortable:true,title:'WBS编码',align:'center',width:'10%'},
		       	{field:'drawingDetailedNo',sortable:true,title:'序号',align:'center'},
		       	{field:'designCode',sortable:true,title:'设计院物资编码',align:'center',width:'10%'},
		       	{field:'designDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
		       	{field:'designUnit',sortable:true,title:'计量单位',align:'center'},
		       	{field:'designCount',sortable:true,title:'设计数量',align:'center'},
		       	{field:'overrun',sortable:true,title:'裕量',align:'center',
					formatter:function (value, row, index){
						if(value==-1){
							return 0;
						}else{
							return value;
						}
					}},
		       	{field:'totalCount',sortable:true,title:'总量',align:'center'},
		       	{field:'extra1',sortable:true,title:'附加1',align:'center'},
		       	{field:'extra2',sortable:true,title:'附加2',align:'center'},
		       	{field:'extra3',sortable:true,title:'附加3',align:'center'},
		       	{field:'extra4',sortable:true,title:'附加4',align:'center'},
		    ]]
		});
	});
	</script>
</body>
</html>
