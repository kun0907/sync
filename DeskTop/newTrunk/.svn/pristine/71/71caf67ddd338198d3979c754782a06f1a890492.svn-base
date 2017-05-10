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
	<form id="modalQuery" method="post">
		<div style="margin:20px">
			领用通知编号:
			<input class="easyui-textbox" id="pickNo"   style="width:22%" >
			<input  class="easyui-combobox" id="supplierId" style="width:30%" data-options="label:'施工单位:',method:'post',url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=construct',valueField: 'orgId',textField: 'orgName'"/>
			<input  class="easyui-combotree" id="wbsId" style="width:30%" data-options="label:'项目名称:',method:'get',url: '${emms}/baseinfo/project.do?cmd=selectToOrderCombotree'">
		</div>
		<div style="margin:20px">
			<input class="easyui-textbox" id="createUserName" style="width:30%" data-options="label:'录入人:'">
			<input class="easyui-datebox" id="beginTime" editable="false" style="width:30%" data-options="label:'录入时间:'">~
			<input class="easyui-datebox" id="endTime" editable="false" style="width:20%">
		</div>
		<div style="margin:20px">
			<input class="easyui-textbox" id="materialsCode" style="width:30%" data-options="label:'物资编码:'">
			<input class="easyui-textbox" id="materialsDescribe" style="width:30%" data-options="label:'物资描述:'">
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearQuery()">重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="confirm()">保存</a>
		</div>
	</form>
	<table id="table" class="easyui-datagrid" title="领料通知明细"  style="height:350px" data-options="
				singleSelect: true,
				method: 'post',
				onClickCell: onClickCell
			">
		<thead>
			<tr>
				<th data-options="field:'pickNo',align:'center'" width="10%">领料通知编码</th>
				<th data-options="field:'wbsName',align:'center'" width="10%">项目名称</th>
				<th data-options="field:'supplierName',align:'center'" width="10%">施工单位</th>
				<th data-options="field:'materialsCode',align:'center'" width="10%">物资编码</th>
				<th data-options="field:'materialsDescribe',align:'center'" width="10%">物资描述</th>
				<th data-options="field:'createUserName',align:'center'" width="9%">录入人</th>
				<th data-options="field:'createTime',align:'center'" width="9%">录入时间</th>
				<th data-options="field:'pickNum',align:'center'" width="10%">领用数量</th>
				<th data-options="field:'allowPickNum',align:'center'" width="10%">可理货数量</th>
				<th data-options="field:'tallyingNum',align:'center',editor:{
					type:'numberbox',
					options:{
						precision:0,
						required:true
					}
				}" width="10%">理货数量</th>
				<th data-options="field:'tallyingTime',align:'center',editor:{
					type:'datebox',
					options:{
						required:true,
						editable:false
					}
				}" width="10%">理货时间</th>
				<th data-options="field:'tallyingUser',align:'center',editor:{
					type:'textbox',
					options:{
						required:true
					}
				}" width="10%">理货人</th>
				<th data-options="field:'storagelocationCode',align:'center'" width="10%">来源储位</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript">
	var editIndex = undefined;
	var lastIndex;
	function formatData(metaDatas){
		console.log(metaDatas);
		var result ={}
		var resultList = [];
		for(var i=0;i<metaDatas.length;i++){
			var result = {};
			result.pickId = metaDatas[i].pickNotice.pickId;
			result.pickDetailId = metaDatas[i].pickDetailId;
			result.pickNo = metaDatas[i].pickNotice.pickNo;
			if(null != metaDatas[i].wbs){
				result.wbsId = metaDatas[i].wbs.projectId;
				result.wbsName = metaDatas[i].wbs.projectName;
			}else{
				result.wbsId =null ;
				result.wbsName = null ;
			}
			result.supplierId = metaDatas[i].pickNotice.supplier.orgId;
			result.supplierName = metaDatas[i].pickNotice.supplier.orgName;
			result.materialsId = metaDatas[i].materials.materialsId;
			result.materialsCode = metaDatas[i].materials.materialsCode;
			result.materialsDescribe = metaDatas[i].materials.materialsDescribe;
			result.pickNum = metaDatas[i].pickNum;
			result.allowPickNum = parseInt(metaDatas[i].pickNum)-parseInt(metaDatas[i].tallyedNum);
			result.createTime = metaDatas[i].pickNotice.createTime;
			result.createUserName = metaDatas[i].pickNotice.createUserName;
			resultList.push(result);
		}
		result.total = resultList.length;
		result.rows = resultList;
		return result;
	}

	function checkStoragelocation(rows,index){
		var row = $('#table').datagrid("selectRow", index).datagrid("getSelected");
		row.storagelocationCode =rows[0].storagelocationCode;
		row.storagelocationId =rows[0].storagelocationId;
		$('#table').datagrid('refreshRow', index);
	}
	function query(){
		var supplierId = $("#supplierId").combo('getValue');
		var wbsId = $("#wbsId").combo('getValue');
		$('#table').datagrid({
			url:'${emms}/outstorage/pickNotice.do?cmd=loadPickDetailListData',
			method: 'POST',
			queryParams: {
				"pickNoticeNo" : $("#pickNo").val(),
				"supplierId" : supplierId,
				"wbsId" : wbsId,
				"createUserName" : $("#createUserName").val(),
				"beginTime" : $('#beginTime').datebox('getValue'),
				"endTime" : $('#endTime').datebox('getValue'),
				"materialsCode":$("#materialsCode").val(),
				"materialsDescribe":$("#materialsDescribe").val()
			},
			loadFilter: function(data){
				return formatData(data);
			},
			onDblClickRow:function(row){//运用双击事件实现对一行的编辑
			$('#table').datagrid('beginEdit', row);
			},
			onClickRow:function(row) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
				$('#table').datagrid('endEdit', lastIndex);
				$('#table').datagrid('endEdit', row);
				lastIndex = row;
			}
		})
	}
	function confirm(){
		$('#table').datagrid('endEdit', $('#table').datagrid('getRowIndex',$('#table').datagrid('getSelected')));
		var rows = $('#table').datagrid('getRows');
		var tallyDetailList = [];
		for(var i=0;i<rows.length;i++){
			var tallyDetail = {};
			tallyDetail.wbs = {};
			tallyDetail.wbs.projectId = rows[i].wbsId;
			tallyDetail.contractor = {};
			tallyDetail.contractor.orgId = rows[i].supplierId;
			tallyDetail.materials = {};
			tallyDetail.materials.materialsId = rows[i].materialsId;
			tallyDetail.tallyingNum = rows[i].tallyingNum;
			tallyDetail.allowPickNum = rows[i].allowPickNum;
			tallyDetail.tallyingDate = rows[i].tallyingTime;
			tallyDetail.tallyingUser = rows[i].tallyingUser;
			tallyDetail.pickNotice = {};
			tallyDetail.pickNotice.pickId = rows[i].pickId;
			tallyDetail.pickNoticeDetail = {};
			tallyDetail.pickNoticeDetail.pickDetailId = rows[i].pickDetailId;
			tallyDetail.storagelocation = {};
			tallyDetail.storagelocation.storagelocationId = rows[i].storagelocationId;
			tallyDetailList.push(tallyDetail);
		}

		//前台页面校验
		for(var i=0;i<tallyDetailList.length;i++){
			if(tallyDetailList[i].tallyingNum == '' || null == tallyDetailList[i].tallyingNum || parseInt(tallyDetailList[i].tallyingNum) == 0){
				$.messager.alert("操作提示","第"+(i+1)+"行明细中:理货数量不能为空或为0","warning");
				return false;
			}
			if(tallyDetailList[i].tallyingNum >tallyDetailList[i].allowPickNum){
				$.messager.alert("操作提示","第"+(i+1)+"行明细中:理货数量不能大于本次可理货数量","warning");
				return false;
			}
			if(tallyDetailList[i].tallyingDate == '' || null == tallyDetailList[i].tallyingDate){
				$.messager.alert("操作提示","第"+(i+1)+"行明细中:理货时间不能为空","warning");
				return false;
			}
			if(tallyDetailList[i].tallyingUser == '' || null == tallyDetailList[i].tallyingUser){
				$.messager.alert("操作提示","第"+(i+1)+"行明细中:理货人不能为空","warning");
				return false;
			}
			if(tallyDetailList[i].storagelocation.storagelocationId == '' || null == tallyDetailList[i].storagelocation.storagelocationId){
				$.messager.alert("操作提示","第"+(i+1)+"行明细中:储位不能为空","warning");
				return false;
			}
			delete tallyDetailList[i].allowPickNum;
		}
		$.ajax({
			type: 'POST',
			url: "${emms}/outstorage/tallying.do?cmd=saveTallying",
			data: JSON.stringify(tallyDetailList),
			dataType: 'json',
			contentType: "application/json;charset=utf-8",
			success: function (result) {
				if(result =='true'){
					$.messager.alert("操作提示", "理货信息保存成功！","info", function () {
						$("#dialog").dialog("close");
						loadTallyDetail();
					});
				}else{
					$.messager.alert("操作提示", result,"warning");
				}
			}
		});
		/*var rows = $('#table').datagrid('getSelections');
		if(rows.length == 0){
			alert("请选择领料通知明细信息");
			return false;
		}else{
			window.parent.frames["mainFrame"].checkPickDetail(rows);
			top.$("#dialog").dialog("close");
		}*/
	}
	function clearQuery(){
		$('#modalQuery').form('clear');
	}
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#table').datagrid('validateRow', editIndex)){
			$('#table').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if(field=='storagelocationCode'){
			top.$('#dialog').dialog({
				title: '公共仓库库区树',
				width: 900,
				height: 510,
				closed: false,
				href: '${emms}/baseinfo/warehouse.do?cmd=dialogWarehouse&index='+index
			});
		}
	}
</script>
</body>
</html>
