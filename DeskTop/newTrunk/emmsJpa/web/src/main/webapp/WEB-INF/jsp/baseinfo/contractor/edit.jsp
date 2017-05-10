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
			<div style="padding:10px">
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
					<B>社会统一信用代码:</B>
					<input class="easyui-textbox" id="orgCode" name="organization.orgCode" editable="false" style="width:23%">
				</c:if>
				<input class="easyui-textbox" id="orgName" name="organization.orgName" style="width:30%" data-options="label:'组织名称:',required:true,validType:['length[1,50]']">
				<input class="easyui-combobox" id="enterpriseProperty"  style="width:30%" name="enterpriseProperty" data-options="label:'企业性质:'">
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" id="contractNo" name="contractNo" style="width:30%" data-options="label:'合同编号:',validType:['length[0,200]']">
				<input class="easyui-textbox" id="operateArea" name="operateArea" style="width:30%" data-options="label:'经营范围:',validType:['length[0,500]']">
				<input class="easyui-textbox" id="workAddress" name="workAddress" style="width:30%" data-options="label:'办公地址:',validType:['length[0,500]']">
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" id="registerCapital" name="registerCapital" style="width:30%" data-options="label:'注册资金:'" validtype="intOrFloat">
				<input class="easyui-textbox" id="registerAddress" name="registerAddress" style="width:30%" data-options="label:'注册地址:',validType:['length[0,500]']">
				<input class="easyui-textbox" id="linkMan" name="linkMan"  style="width:30%" data-options="label:'联系人:',required:true,validType:['length[0,20]']">
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" id="linkPhone" name="linkPhone"  style="width:30%" data-options="label:'联系人电话:',required:true,validType:['mobile','length[0,20]']">
				<input class="easyui-textbox" id="email" name="email" id="email"  style="width:30%" data-options="label:'邮箱:',required:true,validType:['email','length[0,50]']"/>
				<input class="easyui-textbox" id="legalRepresentative" name="legalRepresentative"  style="width:30%" data-options="label:'法人代表:',validType:['length[0,20]']">
			</div>
			<div style="text-left: center;width:90%">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
				<a href="javascript:void(0)" iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
			</div>
		</form>
		<div class="easyui-layout" style="width:100%;height: 80%">
			<div data-options="region:'west',split:true" style="width:50%;">
				<div class="easyui-panel" title="项目角色列表" data-options="fit:true,border:false">
					<a href="javascript:void(0)" onclick="appendOrgType()" class="easyui-linkbutton" iconCls='icon-add'>新增项目角色</a>
					<a href="javascript:void(0)" onclick="delOrgType()" class="easyui-linkbutton" iconCls='icon-remove'>删除项目角色</a>
					<table id="table" auto-resize="true" class="easyui-datagrid" title="项目角色列表" width="100%"></table>
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
		function uuid() {
			var s = [];
			var hexDigits = "0123456789abcdef";
			for (var i = 0; i < 36; i++) {
				s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
			}
			s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
			s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
			s[8] = s[13] = s[18] = s[23] = "";
			var uuid = s.join("");
			return uuid;
		}
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
				onClickRow:function(index,row){
					$('#table').datagrid('endEdit', index);
					$('#table').datagrid('endEdit', lastIndex);
					/*allocateWBS(row.orgTypeId);*/
				},
				onDblClickCell: function(index,field,value){
					$('#table').datagrid('beginEdit', index);
				},
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
							show = "<a href=\"javascript:allocateWBS(\'"+ row.orgTypeId + "\')\">配置</a>&nbsp;&nbsp;&nbsp;";
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
	function ajaxSubmitForm() {
		$('#table').datagrid('endEdit', $('#table').datagrid('getRowIndex',$('#table').datagrid('getSelected')));
		var selectRow = $('#table').datagrid('getSelected');
		if(null != selectRow){
			var nodes = $('#tt').tree('getChecked', 'checked');
			for (var i = 0; i < nodes.length; i++) {
				var orgWbs = {};
				orgWbs.organizationType = {};
				orgWbs.project = {};
				orgWbs.organizationType.orgTypeId = selectRow.orgTypeId;
				orgWbs.project.projectId = nodes[i].id;
				orgWbs.orgId = '${orgId}';
				wbs.push(orgWbs);
			}
		}
		var orgType = $('#table').datagrid('getRows');
		var contractor = {
			"contractorId":$("#contractorId").textbox("getValue"),//id
			"enterpriseProperty":$("#enterpriseProperty").combobox('getValue'),//企业性质
			"contractNo":$("#contractNo").textbox("getValue"),//合同编号
			"operateArea":$("#operateArea").textbox("getValue"),//经营范围
			"workAddress":$("#workAddress").textbox("getValue"),//办公地址
			"registerCapital":$("#registerCapital").textbox("getValue"),//注册资金
			"registerAddress":$("#registerAddress").textbox("getValue"),//注册地址
			"linkMan":$("#linkMan").textbox("getValue"),//联系人
			"linkPhone":$("#linkPhone").textbox("getValue"),//联系人电话
			"email":$("#email").textbox("getValue"),//邮箱
			"contractorState":"contractorNoCommit",//状态
			"legalRepresentative":$("#legalRepresentative").textbox("getValue")//法人代表
		};
		contractor.organization = {};
		contractor.organization.orgId = $("#orgId").textbox("getValue");
		contractor.organization.orgCode = $("#orgCode").textbox("getValue");
		contractor.organization.orgName = $("#orgName").textbox("getValue");
		contractor.organization.orgTypeList = orgType;
		if(wbs.length  == 0){
			for (var i = 0; i < treeData.length; i++) {
				var orgWbs = {};
				orgWbs.organizationType = {};
				orgWbs.project = {};
				orgWbs.organizationType.orgTypeId = treeData[i].orgType;
				orgWbs.project.projectId = treeData[i].projectId;
				orgWbs.orgId = '${orgId}';
				wbs.push(orgWbs);
			}
		}
		if(orgType.length == 0){
			$.messager.alert("操作提示", "请增加项目角色","warning");
			return false;
		}
		for(var i=0;i<orgType.length;i++){
			if(null == orgType[i].orgType || orgType[i].orgType == ''){
				$.messager.alert("操作提示", "项目角色不能为空","warning");
				return false;
			}
		}
		contractor.organization.orgTypeWBSList = wbs;
		if($("#ff").form("validate")){
			$.ajax({
				type: 'POST',
				url: "${emms}/baseinfo/contractor.do?cmd=save",
				data: JSON.stringify(contractor),
				dataType: 'json',
				contentType: "application/json;charset=utf-8",
				success: function (result) {
					if(result == 'true'){
						$.messager.alert("操作提示", "承包商保存成功","info",function(){
							window.location.href = "${emms}/baseinfo/contractor.do?cmd=query";
						});
					}else{
						$.messager.alert("操作提示",result,"warning");
					}
				}
			});
		}
	 }
	function back(){
		window.location.href = "${emms}/baseinfo/contractor.do?cmd=query";
	}
	</script>
</body>
</html>
