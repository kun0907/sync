<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>承包商编辑</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body>
<div class="easyui-panel" title="首页->基础信息管理->承包商管理->查看" data-options="fit:true,border:false">
	<form id="ff" method="post">
		<input class="easyui-textbox" type="hidden" id="contractorId" name="contractorId"/>
		<input class="easyui-textbox" type="hidden" id="orgId" name="orgId"/>
		<div style="margin:20px">
			<c:if test="${empty contractorId}">
				<B>社会统一信用代码:</B>
				<input class="easyui-textbox" id="orgCode" name="organization.orgCode" style="width:23%" data-options="required:true,
						validType:{
							length:[1,50],
							remote:['${emms}/system/organization.do?cmd=checkOrgCode','orgCode']
							}
					"  >
			</c:if>
			<c:if test="${not empty contractorId}">
				<input class="easyui-textbox" id="orgCode" name="organization.orgCode" editable="false" style="width:30%" data-options="label:'统一社会信用代码:'"  >
			</c:if>
			<input class="easyui-textbox" id="orgName" name="organization.orgName" style="width:30%" data-options="label:'组织名称:'">
			<input class="easyui-combobox" id="enterpriseProperty"  style="width:30%" name="enterpriseProperty" data-options="label:'企业性质:'">
		</div>
		<div style="margin:20px">
			<input class="easyui-textbox" id="contractNo" name="contractNo" style="width:30%" data-options="label:'合同编号:'">
			<input class="easyui-textbox" id="operateArea" name="operateArea" style="width:30%" data-options="label:'经营范围:'">
			<input class="easyui-textbox" id="workAddress" name="workAddress" style="width:30%" data-options="label:'办公地址:'">
		</div>
		<div style="margin:20px">
			<input class="easyui-textbox" id="registerCapital" name="registerCapital" style="width:30%" data-options="label:'注册资金:'">
			<input class="easyui-textbox" id="registerAddress" name="registerAddress" style="width:30%" data-options="label:'注册地址:'">
			<input class="easyui-textbox" id="linkMan" name="linkMan"  style="width:30%" data-options="label:'联系人:'">
		</div>
		<div style="margin:20px">
			<input class="easyui-textbox" id="linkPhone" name="linkPhone"  style="width:30%" data-options="label:'联系人电话:'">
			<input class="easyui-textbox" id="email" name="email" id="email"  style="width:30%" data-options="label:'邮箱:'"/>
			<input class="easyui-textbox" id="legalRepresentative" name="legalRepresentative"  style="width:30%" data-options="label:'法人代表:'">
		</div>
		<div style="text-left: center;width:90%">
			<a href="javascript:void(0)" iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
		</div>
	</form>
	<div class="easyui-layout" style="width:100%;height: 80%">
		<div data-options="region:'west',split:true" style="width:50%;">
			<div class="easyui-panel" title="机构类型列表" data-options="fit:true,border:false">
				<table id="table" auto-resize="true" class="easyui-datagrid" title="机构类型" width="100%"></table>
			</div>
		</div>
		<div data-options="region:'center'" title="WBS树">
			<div class="easyui-panel" data-options="fit:true,border:false">
				<ul class="easyui-tree" id="tt" checkbox="true"></ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var lastIndex;
	var wbs = [];
	var treeData = [];
	var orgType = {};
	var packageTypeList = [];
	function allocateWBS(orgTypeId){
		var flag = false;
		if (lastIndex != orgTypeId){
			var nodes = $('#tt').tree('getChecked', 'checked');
			//删掉了
			for(var i=0;i<wbs.length;){
				if(wbs[i].organizationType.orgTypeId == orgTypeId){
					var node = $('#tt').tree('find',wbs[i].project.projectId);
					flag = true;
					$('#tt').tree('check', node.target);
					wbs.splice(i,1);
				}else{
					i++;
				}
			}
			for (var i = 0; i < nodes.length; i++) {
				var orgWbs = {};
				orgWbs.organizationType = {};
				orgWbs.project = {};
				orgWbs.organizationType.orgTypeId = lastIndex;
				orgWbs.project.projectId = nodes[i].id;
				orgWbs.orgId = '${orgId}';
				wbs.push(orgWbs);
			}
			lastIndex = orgTypeId;
		}
		if(!flag){
			var loadDataUrl = 'system/organization.do?cmd=initWBSTree&orgId=${orgId}&orgTypeId='+orgTypeId;
			loadWBSTree(loadDataUrl);
		}
	}
	function getValueByKey(key){
		return orgType[key];
	}
	function convert(rows){
		function exists(rows, parentId){
			for(var i=0; i<rows.length; i++){
				if (rows[i].id == parentId) return true;
			}
			return false;
		}

		var nodes = [];
		// get the top level nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (!exists(rows, row.parentId)){
				nodes.push({
					id:row.id,
					text:row.name,
					level:row.level,
					isLeaf:row.isLeaf,
					checked:row.checked,
					chkDisabled:row.chkDisabled
				});
			}
		}
		var toDo = [];
		for(var i=0; i<nodes.length; i++){
			toDo.push(nodes[i]);
		}
		while(toDo.length){
			var node = toDo.shift();	// the parent node
			// get the children nodes
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				if (row.parentId == node.id){
					var child = {id:row.id,text:row.name,level:row.level,isLeaf:row.isLeaf,checked:row.checked,chkDisabled:row.chkDisabled};
					if (node.children){
						node.children.push(child);
					} else {
						node.children = [child];
					}
					toDo.push(child);
				}
			}
		}
		return nodes;
	}
	function init(){
		$('#ff').form('load', '${emms}/baseinfo/contractor.do?cmd=loadContractorData&contractorId=${contractorId}');
		$('#ff').form({
			onLoadSuccess:function(data){
				$("#orgId").textbox("setValue", data.organization.orgId);
				$("#orgCode").textbox("setValue", data.organization.orgCode);
				$("#orgName").textbox("setValue", data.organization.orgName);
			}
		});
		$('#enterpriseProperty').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=enterprise_property',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName'
		});

		$.ajax({
			url:'${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=org_type',
			dataType : 'json',
			type : 'POST',
			async:false,
			success: function (data){
				for(var i=0;i<data.length;i++){
					var obj=data[i].dictionaryCode;
					orgType[obj]=data[i].dictionaryName;
				}
				packageTypeList = data;
			}
		});
		//组织机构类型编辑
		$('#table').datagrid({
			url:'${emms}/system/organization.do?cmd=selectOrgType&orgId=${orgId}',
			method: 'POST',
			fitColumns: true,
			rownumbers: true,
			showFooter: true,
			singleSelect:true,
			selectOnCheck:true,
			checkOnSelect:true,
			columns:[[
				{field:'orgType',sortable:true,title:'项目角色',align:'center',width:'50%',
					editor: {
						type: 'combobox',
						options: {
							valueField: 'dictionaryCode',
							textField: 'dictionaryName',
							data: packageTypeList,
							required: true,
							editable: false
						}
					},
					formatter: function(value,row,index){
						return getValueByKey(value);
					}
				},
				{field:'aaa',title:'操作',sortable:true,align:'center',width:'50%',
					formatter: function(value,row,index){
						show = "<a href=\"javascript:allocateWBS(\'"+ row.orgTypeId + "\')\">查看</a>&nbsp;&nbsp;&nbsp;";
						return show;
					}}
			]]
		});
		loadWBSTree('${initTreeUrl}');
	}
	$(function(){
		init();
	});
	function appendOrgType(){
		$('#table').datagrid('appendRow',{
			orgTypeId:uuid()
		});
	}
	function delOrgType(){
		var rows = $('#table').datagrid("getSelections");
		for (var i = rows.length - 1; i >= 0; i--) {
			var index = $('#table').datagrid('getRowIndex',rows[i]);
			$('#table').datagrid('deleteRow', index);
		}
	}
	function loadWBSTree(url){
		//wbs树加载
		$('#tt').tree({
			url: '${emms}/'+url,
			checkbox:function(node){
				if(node.chkDisabled == false){
					return true;
				}
			},
			loadFilter: function(rows){
				for(var i=0;i<rows.length;i++){
					if(rows[i].checked){
						treeData.push((rows[i]));
					}
				}
				rows.push({"id":0, "parentId":-1, "name":"结构树型图","level":0});
				return convert(rows);
			}
		});
	}
	function back(){
		window.location.href = "${emms}/baseinfo/contractor.do?cmd=query";
	}
</script>
</body>
</html>
