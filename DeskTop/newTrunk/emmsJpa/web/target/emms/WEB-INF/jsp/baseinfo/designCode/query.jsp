<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->基本信息管理->设计院编码管理->设计院编码查询" data-options="fit:true,border:false">
	<form id="query" method="post">
		<div style="padding:10px">
			<select class="easyui-combobox" id="designOrgId" style="width:30%" data-options="label:'设计院:'"></select>
			<input class="easyui-textbox" id="designCode"  style="width:30%" data-options="label:'物资编码:'">
			<select class="easyui-combobox" id="designType" style="width:30%" data-options="label:'物资类型:'"></select>
		</div>
		<div style="padding:10px">
			<input class="easyui-textbox" id="designDescribe"  style="width:30%" data-options="label:'物资描述:',multiline:true">
			<select class="easyui-combobox" id="isMatching" name="isMatching" style="width:30%" data-options="label:'是否匹配:'">
				<option value="">请选择<option>
				<option value="0">否<option>
				<option value="1">是<option>
			</select>
		</div>
		<div style="text-align: center;">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
		</div>
	</form>
	<table id="table" auto-resize="true" class="easyui-datagrid" title="设计院编码列表">
	</table>
</div>
<script type="text/javascript">
	$('#designType').combobox({
		url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=MaterialsTypeCategory',
		valueField: 'dictionaryCode',
		textField: 'dictionaryName',
		multiple:false
	});
	$('#designOrgId').combobox({
		url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=design',
		valueField: 'orgId',
		textField: 'orgName',
		multiple:false
	});
	$(function(){
		query();
	});
	function query(){
		$('#table').datagrid({
			url:'${emms}/baseinfo/designCode.do?cmd=loadDesignCodeListData',
			method: 'POST',
			pagination: true,
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			singleSelect:false,
			queryParams: {
				"designCode" : $("#designCode").val(),
				"designType" : $("#designType").combobox('getValue'),
				"designOrgId" : $('#designOrgId').combobox('getValue'),
				"designDescribe" : $("#designDescribe").val(),
				"isMatching" : $('#isMatching').combobox('getValue')
			},
			columns:[[
				{field:'designCode',sortable:true,title:'设计院物资编码',align:'center',width:'9%'},
				{field:'designOrgId',sortable:true,title:'设计院',align:'center',width:'8%'},
				{field:'systemcodeId',sortable:true,title:'系统物资编码',align:'center',width:'10%'},
				{field:'designDescribe',sortable:true,title:'物资描述',width:'10%',
					formatter: function(value,row,index){
						if (value.length>10){
							return "<span title='" + value + "'>" + value.substring(0, 10)+"..." + "</span>";
						} else {
							return value;
						}
					},align:'center',width:'15%'},
				{field:'additional1',sortable:true,title:'附加1',align:'center',width:'6%'},
				{field:'additional2',sortable:true,title:'附加2',align:'center',width:'6%'},
				{field:'additional3',sortable:true,title:'附加3',align:'center'},
				{field:'additional4',sortable:true,title:'附加4',align:'center'},
				{field:'designUnitMain',sortable:true,title:'统计计量单位',align:'center'},
				{field:'designType',sortable:true,title:'物资属性',align:'center',
					formatter: function(value,row,index){
						if (value=='w'){
							return "材料";
						} else {
							return "设备";
						}
					}
				},
				{field:'aaa',title:'操作',sortable:true,align:'center',width:'18%',
					formatter: function(value,row,index){
						var show='';
						if(row.designType=='s'){
							show += "<a class='easyui-linkbutton' onclick='designPopUp(\"" + row.designId + "\")'"
							+ " target='_self'>查看</a>&nbsp;&nbsp;&nbsp;";
						}
						if(row.designOrgId !='洛阳院' && row.isMatching=='0') {
							show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\""+row.designId+"\")'"
							+ " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
							show += "<a class='easyui-linkbutton' onclick='editPopUp(\"" + row.designId + "\")'"
							+ " target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
							show += "<a class='easyui-linkbutton' onclick='materialsPopUp(\"" + row.designId + "\")'"
							+ " target='_self'>编码对照</a>&nbsp;&nbsp;&nbsp;";
						}
						return show;
					}}
			]]
		});
	}
	function clearForm(){
		$('#query').form('clear');
	}
	function ajaxDelete(id) {
		$.messager.confirm("操作提示", "确定要删除当前记录吗？", function (data){
			if(data){
				$.ajax({
					type: "POST",
					url:"${emms}/baseinfo/designCode.do?cmd=deleteDesignCode&designId="+id,
					async: false,
					success: function(data) {
//					alert(data);
						if(data=='删除完成'){
							$.messager.alert("操作提示","删除成功","info",function(){
								window.location = "${emms}/baseinfo/designCode.do?cmd=query";
							});
						}
						else{
							$.messager.alert("操作提示",data,"warning");
						}

					}
				});
			}
		});
	}
	//设计院匹配时选择系统物资弹出框
	function materialsPopUp(designId){
		top.$('#dialog').dialog({
			title: '系统物资',
			width: 900,
			height: 580,
			closed: false,
			href: '${emms}/baseinfo/designCode.do?cmd=dialogMatchingMaterials&designId='+designId
		});
	}
	//查看设备部件弹出框
	function designPopUp(designId){
		top.$('#dialog').dialog({
			title: '设备部件表',
			width: 900,
			height: 640,
			closed: false,
			href: '${emms}/baseinfo/designCode.do?cmd=dialogEquipmentTree&designId='+designId
		});
	}
	//编辑设备部件弹出框
	function editPopUp(designId){
		top.$('#dialog').dialog({
			title: '编辑',
			width: 900,
			height: 640,
			closed: false,
			href: '${emms}/baseinfo/designCode.do?cmd=edit&designId='+designId
		});
	}
	function checkMaterials(materials,designId){
		console.log(materials);
		console.log(designId);
		$.ajax({
			type: "POST",
			url:"${emms}/baseinfo/designCode.do?cmd=manualMatching&designId="+designId+"&systemcodeId="+materials.materialsId,
			async: false,
			success: function(data) {
				if(data == '保存成功'){
					$.messager.alert("操作提示", "保存成功","info",function(){window.location = "${emms}/baseinfo/designCode.do?cmd=query";});
				}else{
					$.messager.alert("操作提示",data,"warning");
				}

			}
		});
	}
</script>
</body>

</html>