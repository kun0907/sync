<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->系统管理->组织机构管理->下级机构列表" data-options="fit:true,border:false" style="height:100%">
		<form id="orgQuery" method="post">
			<div style="margin:20px">
				<B>社会统一信用代码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</B><input class="easyui-textbox" id="orgCode" style="width:20%" >
				<input class="easyui-textbox" id="orgaName" style="width:30%" data-options="label:'机构名称:'">

			</div>
			<div style="margin-left:72px">
				<select class="easyui-combobox" id="orgType" style="width:32%" data-options="label:'项目角色:'"></select>
				<input class="easyui-combobox" id="orgState" style="width:32%" name="orgState" data-options="label:'组织状态:'">
			</div>
			<div style="text-align: center;margin-top: 10px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="queryOrg()">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
				<a href="${emms}/system/organization.do?cmd=editOrg&parentId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
			</div>
		</form>
		<table id="orgTable" auto-resize="true" class="easyui-datagrid" title="机构列表" width="100%">
		</table>
	</div>
	<script type="text/javascript">
			$(function(){
				queryOrg();
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
			});
			function queryOrg(){
				$('#orgTable').datagrid({
				    url:'${emms}/system/organization.do?cmd=selectOrg&parentId=${parentId}',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
					singleSelect:true,
				    showFooter: true,
				    queryParams: {
				    	"orgCode" : $("#orgCode").val(),
				    	"orgName" : $("#orgaName").val(),
				    	"orgType" : $('#orgType').combobox('getValue'),
				    	"orgState" : $('#orgState').combobox('getValue')
					},
				    columns:[[
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
			function clearForm(){
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
									$.messager.alert("操作提示", "删除成功！","info");
									window.top.frames["mainFrame"].frames["westFrame"].location= "${emms}/system/organization.do?cmd=orgTree";
									queryOrg();
								}else{
									$.messager.alert("操作提示", data,"warning");
								}
							}
						});
					}
				});
			}
			function forbidOrg(orgId){
				if(window.confirm('确定要禁用此组织吗？')){
					$.ajax({
						type: "GET",
			            url:"${emms}/system/organization.do?cmd=forbidOrg&orgId="+orgId,
			            success: function(data) {
							if(data == 'true'){
								alert("禁用成功");
								window.location= "${emms}/system/organization.do?cmd=selectOrgQuery&parentId=${parentId}";
							}else{
								alert(data);
							}
			            }
					});
				}	
			}
			function reuseOrg(orgId){
				$.ajax({
					type: "GET",
		            url:"${emms}/system/organization.do?cmd=reuseOrg&orgId="+orgId,
		            success: function(data) {
						if(data == 'true'){
							alert("启用成功");
							window.location= "${emms}/system/organization.do?cmd=selectOrgQuery&parentId=${parentId}";
						}else{
							alert(data);
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
		</script>
</body>
</html>
