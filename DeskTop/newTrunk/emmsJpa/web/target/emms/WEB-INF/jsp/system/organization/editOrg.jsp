<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
	
<body>
	<div class="easyui-panel" title="首页->系统管理->组织机构管理-><c:if test="${empty orgId}">新建</c:if><c:if test="${not empty orgId}">查看</c:if>" data-options="fit:true,border:false">
		<form id="orgForm" method="post">
			<input class="easyui-textbox" type="hidden" id="orgId"  name="orgId"/>
			<input class="easyui-textbox" type="hidden" id="parentId" name="parentId" value="${parentId}"/>
			<div style="margin:20px">
				<c:if test="${empty orgId}">
					<B>社会统一信用代码:</B>
					<input class="easyui-textbox" id="orgCode" name="orgCode" style="width:30%" data-options="required:true,
						validType:{
							length:[1,50],
							remote:['${emms}/system/organization.do?cmd=checkOrgCode','orgCode']
						   }
					">
				</c:if>
				<c:if test="${not empty orgId}">
					<B>社会统一信用代码:</B>
					<input class="easyui-textbox" id="orgCode" name="orgCode" editable="false" style="width:20%">
				</c:if>
				<input class="easyui-textbox" id="orgName" name="orgName"  style="width:45%" data-options="label:'组织名称:',required:true,validType:['length[1,50]']">
			</div>
			<div style="text-left: center;width:90%">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
				<c:if test="${not empty orgId && type=='modal'}">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="cancel()">关闭</a>
				</c:if>
			</div>
			<div class="easyui-layout" style="width:100%;height: 80%">
				<div data-options="region:'west',split:true" style="width:50%;">
					<div class="easyui-panel" title="组织机构类型列表" data-options="fit:true,border:false">
						<a href="javascript:void(0)" onclick="appendOrgType()" class="easyui-linkbutton" iconCls='icon-add'>新增项目角色</a>
						<a href="javascript:void(0)" onclick="delOrgType()" class="easyui-linkbutton" iconCls='icon-remove'>删除项目角色</a>
						<table id="orgTypeTable" auto-resize="true" class="easyui-datagrid" title="机构类型列表" width="100%"></table>
					</div>
				</div>
				<div data-options="region:'center'" title="WBS树">
					<div class="easyui-panel" data-options="fit:true,border:false">
						<ul class="easyui-tree" id="tt" checkbox="true"></ul>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var lastIndex;
		var wbs = [];
		var treeData  = [];
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
		function appendOrgType(){
			$('#orgTypeTable').datagrid('endEdit', $('#orgTypeTable').datagrid('getRowIndex',$('#orgTypeTable').datagrid('getSelected')));
			$('#orgTypeTable').datagrid('appendRow',{
				orgTypeId:uuid()
			});
		}
		function delOrgType(){
			var rows = $('#orgTypeTable').datagrid("getSelections");
			for (var i = rows.length - 1; i >= 0; i--) {
				var index = $('#orgTypeTable').datagrid('getRowIndex',rows[i]);
				$('#orgTypeTable').datagrid('deleteRow', index);
			}
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
				var orgTypeCode = $('#orgTypeTable').datagrid('getSelected').orgType;
				var loadDataUrl = 'system/organization.do?cmd=initWBSTree&orgId=${orgId}&orgTypeCode='+orgTypeCode+'&orgTypeId='+orgTypeId;
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
		//页面初始化加载
		function init(){
			//加载组织机构数据字典
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
			//form表单数据加载
			$('#orgForm').form('load', '${emms}/system/organization.do?cmd=loadOrgData&orgId=${orgId}&parentId=${parentId}');
			$('#orgForm').form({
				onLoadSuccess:function(data){
					if(null != data.orgId){
						$("#orgCode").textbox({editable:false});
					}
				}
			});
			//组织机构类型编辑
			$('#orgTypeTable').datagrid({
				url:'${emms}/system/organization.do?cmd=selectOrgType&orgId=${orgId}',
				method: 'POST',
				fitColumns: true,
				rownumbers: true,
				showFooter: true,
				singleSelect:true,
				selectOnCheck:true,
				checkOnSelect:true,
				onClickRow:function(index,row){
					$('#orgTypeTable').datagrid('endEdit', index);
					$('#orgTypeTable').datagrid('endEdit', lastIndex);
					/*allocateWBS(row.orgTypeId);*/
				},
				onDblClickCell: function(index,field,value){
					$('#orgTypeTable').datagrid('beginEdit', index);
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
		$('#orgTypeTable').datagrid('endEdit', $('#orgTypeTable').datagrid('getRowIndex',$('#orgTypeTable').datagrid('getSelected')));
		var selectRow = $('#orgTypeTable').datagrid('getSelected');
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
		var orgType = $('#orgTypeTable').datagrid('getRows');
		var organization = {
			"orgId":$("#orgId").textbox("getValue"),
			"orgCode":$("#orgCode").textbox("getValue"),
			"parentId":$("#parentId").textbox("getValue"),
			"orgName":$("#orgName").textbox("getValue"),
			"orgTypeList":orgType
		};
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
		organization.orgTypeWBSList = wbs;
		if($("#orgForm").form("validate")){
			$.ajax({
				type: 'POST',
				url: "${emms}/system/organization.do?cmd=saveOrg",
				data: JSON.stringify(organization),
				dataType: 'json',
				contentType: "application/json;charset=utf-8",
				success: function (result) {
					if(result == 'true'){
						alert("组织机构保存成功");
						if('${type}' == 'modal'){
							$("#dialog").dialog("close");
						}else{
							init();
						}
						window.parent.frames["centerFrame"].location = "${emms}/system/organization.do?cmd=query&parentId=${parentId}";
						window.parent.frames["westFrame"].location = "${emms}/system/organization.do?cmd=orgTree";
					}else{
						alert(result);
					}
				}
			});
		}
	 }
		function cancel(){
			$("#dialog").dialog("close");
		}
	</script>	
</body>
</html>
