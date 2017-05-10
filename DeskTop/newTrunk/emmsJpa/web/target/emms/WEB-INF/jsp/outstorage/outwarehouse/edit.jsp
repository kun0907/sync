<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>领料通知编辑</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
	
<body>
	<div class="easyui-panel" title="首页->实物出库管理->出库管理->编辑" data-options="fit:true,border:false">
		<form id="outWarehouse" method="post">
			<input class="easyui-textbox" type="hidden" id="outWarehouseId" name="outWarehouseId"/>
			<div style="margin:20px">
				<input class="easyui-textbox" id="outWarehouseNo" name="outWarehouseNo" editable="false" style="width:30%" data-options="label:'出库单编号:'"  >
				<input  class="easyui-combobox" id="contractor"  style="width:30%" data-options="label:'施工单位:',">
				<input class="easyui-datebox" id="outTime" name="outTime" editable="false" style="width:30%" data-options="label:'出库时间:'">
			</div>
			<div style="text-left: center;width:90%">
				<a href="${emms}/outstorage/outwarehouse.do?cmd=query" iconCls='icon-back' class="easyui-linkbutton" >返回</a>
			</div>
		</form>
		<table id="outWarehouseDetail"  class="easyui-datagrid" title="出库明细" data-options="
				method: 'get'
			">
			<thead>
				<tr>
					<th data-options="field:'pickNo',align:'center'" width="10%">领料通知编号</th>
					<th data-options="field:'materialsCode',align:'center'" width="10%">物资编码</th>
					<th data-options="field:'materialsDescribe',align:'center'" width="10%">物资描述</th>
					<th data-options="field:'additional1',align:'center'" width="9%">附加1</th>
					<th data-options="field:'additional2',align:'center'" width="8%">附加2</th>
					<th data-options="field:'additional3',align:'center'" width="8%">附加3</th>
					<th data-options="field:'additional4',align:'center'" width="8%">附加4</th>
					<th data-options="field:'equipmentNo',align:'center'" width="8%">位号</th>
					<th data-options="field:'designUnit',align:'center'" width="8%">工程计量单位</th>
					<th data-options="field:'pickNum',align:'center'" width="8%">领料通知数量</th>
					<th data-options="field:'tallyedNum',align:'center'" width="7%">已出库数量</th>
					<th data-options="field:'outNum',align:'center',editor: {
							type: 'numberbox',
							options: {
								precision: 4
							}
						}" width="7%",>出库数量</th>
				</tr>
			</thead>
		</table>
	</div>
	<script type="text/javascript">
		var editIndex = undefined;
		$(function(){
			$('#contractor').combobox({
				url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=construct',
				valueField: 'orgId',
				textField: 'orgName',
				multiple:false
			});
			$('#outWarehouse').form('load', '${emms}/outstorage/outwarehouse.do?cmd=editOutWarehouse&outWarehouseId=${outWarehouseId}');
			$('#outWarehouse').form({
				onLoadSuccess:function(data){
					if(null != data.contractor){
						$("#contractor").combobox("setValues", data.contractor.orgId);
					}
					loadDataGridData(data.outWarehouseDetailList);
				}
			});
		});
		function loadDataGridData(rows){
			var pickNo = null ;
			var drawingNumberDeviceNo = null ;
			var designUnit = null ;
			var pickNum = null ;
			var tallyedNum = null ;
			if(null != rows){
				for(var i=0;i<rows.length;i++){
					if(null != rows[i].pickNoticeDetail){
						pickNo = rows[i].pickNoticeDetail.pickNo;
						drawingNumberDeviceNo = rows[i].pickNoticeDetail.demanddetail.drawingNumberDeviceNo;
						designUnit = rows[i].pickNoticeDetail.demanddetail.designUnit;
						pickNum = rows[i].pickNoticeDetail.pickNum;
						tallyedNum = rows[i].pickNoticeDetail.tallyedNum;
					}

					$('#outWarehouseDetail').datagrid('appendRow',{
						"materialsCode":rows[i].materials.materialsCode,
						"materialsDescribe":rows[i].materials.materialsDescribe,
						"additional1":rows[i].materials.additional1,
						"additional2":rows[i].materials.additional2,
						"additional3":rows[i].materials.additional3,
						"additional4":rows[i].materials.additional4,
						"outNum":rows[i].outNum,
						"pickNo": pickNo,
						"equipmentNo":drawingNumberDeviceNo,
						"designUnit":designUnit,
						"pickNum":pickNum,
						"tallyedNum":tallyedNum
					});
				}
			}
		}
	</script>
</body>
</html>
