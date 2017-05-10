<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body style="padding: 0px;">
	<div class="easyui-tabs" id="tab">
		<c:if test="${parentId ne '0'}">
			<div title="本级机构">
				<div class="easyui-panel" title="首页->系统管理->组织机构管理-><c:if test="${empty orgId}">新建</c:if><c:if test="${not empty orgId}">查看</c:if>" data-options="fit:true,border:false">
					<form id="orgForm" method="post">
						<input class="easyui-textbox" type="hidden" id="orgId"  name="orgId" />
						<input class="easyui-textbox" type="hidden" id="parentId" name="parentId" value="${parentId}"/>
						<div style="margin:20px">
							<c:if test="${empty orgId}">
								<B>社会统一信用代码:</B>
								<input class="easyui-textbox" id="orgCode" name="orgCode" style="width:25%" data-options="required:true,
									validType:{
										length:[1,50],
										remote:['${emms}/system/organization.do?cmd=checkOrgCode','orgCode']
									   }
								">
							</c:if>
							<c:if test="${not empty orgId}">
								<B>社会统一信用代码:</B><input class="easyui-textbox" id="orgCode" name="orgCode" editable="false" style="width:25%">
							</c:if>
							<input class="easyui-textbox" id="orgName" name="orgName"  style="width:30%" data-options="label:'组织名称:',required:true,validType:['length[1,50]']">
						</div>
						<div style="text-align: center;margin:20px">
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
							<c:if test="${not empty orgId && type=='modal'}">
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="cancel()">关闭</a>
							</c:if>
						</div>
						<div class="easyui-layout" style="width:100%;height: 100%">
							<div data-options="region:'west',split:true" style="width:50%;">
								<div class="easyui-panel" title="组织机构类型列表" data-options="fit:true,border:false">
									<a href="javascript:void(0)" onclick="appendOrgType()" class="easyui-linkbutton" iconCls='icon-add'>新增机构类别</a>
									<a href="javascript:void(0)" onclick="delOrgType()" class="easyui-linkbutton" iconCls='icon-remove'>删除机构类别</a>
									<table id="orgTypeTable" auto-resize="true" class="easyui-datagrid" title="机构类型列表" width="100%"></table>
								</div>
							</div>
							<div data-options="region:'center'" title="WBS树" data-options="fit:true,border:false">
								<div class="easyui-panel" >
									<ul class="easyui-tree" id="tt" checkbox="true"></ul>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
				<%--<iframe id="selfOrg" name="headerFrame" src="${emms}/system/organization.do?cmd=editOrg&orgId=${orgId}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>
			--%>
		</c:if>
		<div title="下级机构">
			<div class="easyui-panel" title="首页->系统管理->组织机构管理->下级机构列表" data-options="fit:true,border:false">
				<form id="orgQuery" method="post">
					<div style="margin:20px">
						<B>社会统一信用代码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</B><input class="easyui-textbox" id="orgCode2" style="width:20%" >
						<input class="easyui-textbox" id="orgaName" style="width:30%" data-options="label:'机构名称:'">

					</div>
					<div style="margin-left:72px">
						<select class="easyui-combobox" id="orgType" style="width:34%" data-options="label:'项目角色:'"></select>
						<input class="easyui-combobox" id="orgState" style="width:31%" name="orgState" data-options="label:'组织状态:'">
					</div>
					<div style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="queryOrg()">查询</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearOrgQuery()">重置</a>
						<c:if test="${isNew}">
							<a href="${emms}/system/organization.do?cmd=editOrg&parentId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
						</c:if>
					</div>
				</form>
				<table id="orgTable" auto-resize="true" class="easyui-datagrid" title="机构列表" width="100%" style="height:500px">
				</table>
			</div>
			<%--<iframe id="childOrg" name="headerFrame" src="${emms}/system/organization.do?cmd=selectOrgQuery&parentId=${orgId}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>--%>
		</div>
		<div title="人员信息">
			<div class="easyui-panel" title="首页->系统管理->组织机构管理->人员列表" data-options="fit:true,border:false">
				<form id="query" method="post">
					<div style="margin:20px">
						<input class="easyui-textbox" id="empCode"  style="width:30%" data-options="label:'人员编码:'">
						<input class="easyui-textbox" id="employeeName" style="width:30%" data-options="label:'人员姓名:'">
					</div>
					<div style="text-align: center;">
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="queryEmp()">查询</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
						<c:if test="${orgId ne '0' && not empty orgId}">
							<a href="${emms}/system/user.do?cmd=editUserPage&orgId=${orgId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
						</c:if>
					</div>
				</form>
				<table id="table" auto-resize="true" class="easyui-datagrid" title="人员列表" width="100%" style="height:80%">
				</table>
			</div>
			<%--<iframe id="childEmp" name="headerFrame" src="${emms}/system/employee.do?cmd=query&orgId=${orgId}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>--%>
		</div>
		<c:if test="${parentId ne '0'}">
			<div title="角色信息">
				<div class="easyui-panel" title="首页->系统管理->组织机构管理->配置角色信息" data-options="fit:true,border:false">
					<form id="queryRole" method="post">
						<select id='role' multiple='multiple'>
							<c:forEach items="${allRoleList}" var="item">
								<option value='${item.roleId}' <c:if test='${item.checked}'>selected</c:if>>${item.roleName}</option>
							</c:forEach>
						</select>
						<div style="text-align: center;">
							<a class="easyui-linkbutton" href="javascript:void(0)" iconCls='icon-save' onclick="saveRoleToOrg();">保存</a>
						</div>
					</form>
				</div>
				<%--<iframe id="roleInfo" name="headerFrame" src="${emms}/system/role.do?cmd=selectRoleByOrgQuery&orgId=${orgId}" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>--%>
			</div>
		</c:if>
	</div>
	<%--<script type="text/javascript">
		$(function(){
			var title = '${title}';
			if(null != title && title !=''){
				$(".easyui-tabs").tabs('select', title);
			}
		});
	</script>--%>
	<script type="text/javascript">
		//本级机构
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
				fitColumns: false,
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
	/*	$(function(){

		});*/
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
			for(var i=0;i<orgType.length;i++){
				 if(null == orgType[i].orgType || orgType[i].orgType == ''){
					 $.messager.alert("操作提示", "项目角色不能为空","warning");
					 return false;
				 }
			 }
			organization.orgTypeWBSList = wbs,
					$.ajax({
						type: 'POST',
						url: "${emms}/system/organization.do?cmd=saveOrg",
						data: JSON.stringify(organization),
						dataType: 'json',
						contentType: "application/json;charset=utf-8",
						success: function (result) {
							if(result == 'true'){
								$.messager.alert("操作提示", "组织机构保存成功！","info",function(){
									if('${type}' == 'modal'){
										$("#dialog").dialog("close");
										query();
									}else{
										init();
									}
									window.parent.parent.frames["westFrame"].location = "${emms}/system/organization.do?cmd=orgTree";
								});
							}else{
								$.messager.alert("操作提示",result,"warning");
							}
						}
					});
		}
		function cancel(){
			$("#dialog").dialog("close");
		}
		//下级机构js代码
		$('#orgType').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=org_type',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName'
		});
		$('#orgState').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=org_state',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName'
		});
		$(function(){
			$(".panel-body").css("height","100%");
			$('#tab').tabs({
				height: "100%"
			});
			//初始化树
			init();
			//查询机构
			queryOrg();

			//查询人员
			queryEmp();
		});
		function queryOrg(){
			$('#orgTable').datagrid({
				url:'${emms}/system/organization.do?cmd=selectOrg&parentId=${parentId}',
				method: 'POST',
				pagination: true,
				fitColumns: false,
				rownumbers: true,
				singleSelect:true,
				showFooter: true,
				queryParams: {
					"orgCode" : $("#orgCode2").val(),
					"orgName" : $("#orgaName").val(),
					"orgType" : $('#orgType').combobox('getValue'),
					"orgState" : $('#orgState').combobox('getValue')
				},
				loadFilter:function(data,parentId){
					return data;
				},columns:[[
					{field:'ORG_CODE',sortable:true,title:'社会统一信用代码',align:'center',width:'17%',
						formatter: function(value, row, index){
							return "<a style='color:blue' href=\"javascript:viewOrg(\'"+ row.ORG_ID + "\')\">"+row.ORG_CODE+"</a>";
						}},
					{field:'ORG_NAME',sortable:true,title:'组织名称',align:'center',width:'17%'},
					{field:'TYPE_NAME',sortable:true,title:'项目角色',align:'center',width:'16%'},
					{field:'IS_VALIDATE',sortable:true,title:'组织状态',align:'center',width:'16%',formatter: function(value, row, index){
						if (value == "1") {
							return "可用";
						} else if (value == "0") {
							return "不可用";
						}
					}
					},
					{field:'ORG_LEVEL',sortable:true,title:'组织等级',align:'center',width:'16%'},
					{field:'aaa',title:'操作',sortable:true,align:'center',width:'20%',
						formatter: function(value,row,index){
							show = "<a class='easyui-linkbutton' href=\"javascript:viewOrg(\'"+ row.ORG_ID + "\')\">编辑</a>&nbsp;&nbsp;";
							show += "<a href=\"javascript:delOrg(\'"+ row.ORG_ID + "\')\">删除</a>&nbsp;&nbsp;&nbsp;";
							if(row.IS_VALIDATE == '1'){
								show += "<a href=\"javascript:forbidOrg(\'"+ row.ORG_ID + "\')\">禁用</a>&nbsp;&nbsp;&nbsp;";
							}else if(row.IS_VALIDATE == '0'){
								show += "<a href=\"javascript:reuseOrg(\'"+ row.ORG_ID + "\')\">启用</a>&nbsp;&nbsp;&nbsp;";
							}
							return show;
						}}
				]]
			});
		}
		function clearOrgQuery(){
			$('#orgQuery').form('clear');
		}
		function delOrg(orgId){
			$.messager.confirm("操作提示", "确定要删除当前记录吗？", function (data) {
				if(data){
					$.ajax({
						type: "POST",
						url:"${emms}/system/organization.do?cmd=deleteOrg&orgId="+orgId,
						type:"GET",
						success: function(data) {
							if(data == 'true'){
								$.messager.alert("操作提示", "删除成功！","info",function(){
									window.top.frames["mainFrame"].frames["westFrame"].location= "${emms}/system/organization.do?cmd=orgTree";
									queryOrg();
								});
							}else{
								$.messager.alert("操作提示", data,"warning");
							}

						}
					});
				}
			});
		}
		function forbidOrg(orgId){
			$.messager.confirm("操作提示", "确定要禁用此组织吗？", function (data) {
				if(data){
					$.ajax({
						type: "GET",
						url:"${emms}/system/organization.do?cmd=forbidOrg&orgId="+orgId,
						success: function(data) {
							if(data == 'true'){
								$.messager.alert("操作提示", "禁用成功！","info",function(){
									window.location= "${emms}/system/organization.do?cmd=selectOrgQuery&parentId=${parentId}";
								});
							}else{
								$.messager.alert("操作提示", data,"warning");
							}
						}
					});
				}
			});
		}
		function reuseOrg(orgId){
			$.ajax({
				type: "GET",
				url:"${emms}/system/organization.do?cmd=reuseOrg&orgId="+orgId,
				success: function(data) {
					if(data == 'true'){
						$.messager.alert("操作提示", "启用成功！","info",function(){
							window.location= "${emms}/system/organization.do?cmd=selectOrgQuery&parentId=${parentId}";
						});
					}else{
						$.messager.alert("操作提示", data,"warning");
					}
				}
			});
		}
		function viewOrg(orgId){
			$('#dialog').dialog({
				title: '组织机构编辑',
				width:900,
				height: 600,
				closed: false,
				cache: true,
				href: '${emms}/system/organization.do?cmd=editOrg&type=modal&orgId='+orgId
			});
		}

		//查询人员
		function queryEmp(){
			$('#table').datagrid({
				url:'${emms}/system/employee.do?cmd=loadEmpListData&orgId=${orgId}',
				method: 'POST',
				pagination: true,
				fitColumns: false,
				rownumbers: true,
				showFooter: true,
				singleSelect:true,
				queryParams: {
					"empCode" : $("#empCode").val(),
					"empName" : $("#employeeName").val()
				},
				columns:[[
					{field:'EMP_NO',sortable:true,title:'人员编码',align:'center',width:'17%',
						formatter: function(value, row, index){
							return "<a style='color:blue' href=\"javascript:viewEmployee(\'"+ row.USER_ID + "\')\">"+row.EMP_NO+"</a>";
						}
					},
					{field:'EMP_NAME',sortable:true,title:'人员姓名',align:'center',width:'17%'},
					{field:'SEX',sortable:true,title:'性别',align:'center',width:'16%',
						formatter: function(value, row, index){
							if (value == "female") {
								return "女";
							}else if (value == "male") {
								return "男";
							}
						}},
					{field:'EMP_STATE',sortable:true,title:'人员状态',align:'center',width:'16%',
						formatter: function(value, row, index){
							if (value == "1") {
								return "在岗";
							}else if (value == "0") {
								return "离职";
							}
						}},
					{field:'ORG_NAME',sortable:true,title:'所属机构',align:'center',width:'16%'},
					{field:'aaa',title:'操作',sortable:true,align:'center',width:'20%',
						formatter: function(value,row,index){
							show = "<a class='easyui-linkbutton' href=\"javascript:viewEmployee(\'"+ row.USER_ID + "\')\">编辑</a>&nbsp;&nbsp;&nbsp;";
							if(row.EMP_STATE == '1'){
								show += "<a href=\"javascript:forbidUser(\'"+ row.USER_ID + "\')\">禁用</a>&nbsp;&nbsp;&nbsp;";
							}else if(row.EMP_STATE == '0'){
								show += "<a href=\"javascript:reuseUser(\'"+ row.USER_ID + "\')\">启用</a>&nbsp;&nbsp;&nbsp;";
							}
							return show;
						}}
				]]
			});
		}
		function clearForm(){
			$('#query').form('clear');
		}
		function forbidUser(userId){
			$.messager.confirm("操作提示", "确定要禁用此用户吗？", function (data) {
				if(data){
					$.ajax({
						type: "GET",
						url:"${emms}/system/user.do?cmd=forbidUser&userId="+userId,
						success: function(data) {
							$.messager.alert("操作提示", data,"info",function(){
								window.location= "${emms}/system/employee.do?cmd=query&orgId=${orgId}";
							});
						}
					});
				}
			});
		}
		function reuseUser(userId){
			$.ajax({
				type: "GET",
				url:"${emms}/system/user.do?cmd=reuseUser&userId="+userId,
				success: function(data) {
					$.messager.alert("操作提示", data,"info",function(){
						window.location= "${emms}/system/employee.do?cmd=query&orgId=${orgId}";
					});
				}
			});
		}
		function viewEmployee(userId){
			$('#dialog').dialog({
				title: '人员信息编辑',
				width: 800,
				height: 400,
				closed: false,
				cache: true,
				href: '${emms}/system/user.do?cmd=editUserPage&userId='+userId
			});
		}

		//角色分配js
		$('#role').multiSelect();
		function saveRoleToOrg(){
			var roleIds = '';
			$('#role option:selected').each(function(){
				if(roleIds == ''){
					roleIds = $(this).val();
				}else{
					roleIds += "," + $(this).val();
				}
			});
			$.ajax({
				type: "POST",
				url:"${emms}/system/organization.do?cmd=saveOrgRole&orgId=${orgId}&roleIds="+roleIds,
				async: false,
				success: function(data) {
					if(data == 'true'){
						$.messager.alert("操作提示", "为组织机构成功分配角色！","info",function(){
							window.location.href = "${emms}/system/organization.do?cmd=query&selfId=${orgId}&parentId=${orgId}&title=角色信息"
						});
					}else{
						$.messager.alert("操作提示", data,"warning");
					}
				}
			});
		}
	</script>
</body>
</html>
