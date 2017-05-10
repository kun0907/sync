<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<link rel="stylesheet" href="${emms}/css/indexStyle.css">
</head>
	
<body>
	<div class="easyui-panel"  data-options="fit:true,border:false">

		<table id="MT" auto-resize="true" class="easyui-datagrid" title="物资料表头信息" width="100%">
		</table>

		<table id="test" title="设备部件表" class="easyui-treegrid"
			   url="${emms}/design/materialstableImprot.do?cmd=loadMTdetailed&materialsTableId=${materialsTableId}"
			   rownumbers="true"
			   idField="drawingDetailedId" treeField="designCode">
			<thead>
			<tr>
				<th field="designCode" width="150" align="center" >设备编号</th>
				<th field="drawingNumberCode" width="120" align="center" >设计图编号</th>
				<th field="drawingNumberVersion" width="60" align="center">版次</th>
				<th field="projectCodeSeq" width="120" align="center">WBS编码</th>
				<th field="drawingDetailedNo" width="60" align="center">序号</th>

				<th field="drawingNumberDeviceNo" width="100" align="center">位号</th>
				<th field="designDescribe" width="150" align="center" >设备描述</th>
				<th field="totalCount" width="60" align="center">设备数量</th>
				<th field="designUnit" width="100" align="center">设备计量单位</th>
			</tr>
			</thead>
		</table>
		
	</div>
	<script type="text/javascript">
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
	</script>
</body>
</html>
