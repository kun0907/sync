<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
	<title>承包商管理</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
	</head>
	<body>
		<div class="easyui-panel" title="首页->基本信息管理->承包商管理->查询" data-options="fit:true,border:false">
			<form id="query" method="post">
				<div style="margin:20px">
					<B>社会统一信用代码:</B>
					<input class="easyui-textbox" id="orgCode"  style="width:20%">
					<input class="easyui-textbox" id="orgName" style="width:25%" data-options="label:'组织名称:'">
					<select class="easyui-combobox" id="orgType" style="width:25%" data-options="label:'项目角色:'"></select>
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
					<a href="${emms}/baseinfo/contractor.do?cmd=edit" iconCls='icon-add' class="easyui-linkbutton">新建</a>
				</div>
			</form>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="承包商列表">
			</table>
		</div> 
		<script type="text/javascript">
			$('#orgType').combobox({
				url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=org_type',
				valueField: 'dictionaryCode',
				textField: 'dictionaryName',
				multiple:true
			});
			$(function(){

				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/baseinfo/contractor.do?cmd=loadContractorListData',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    queryParams: {
				    	"orgCode" : $("#orgCode").val(),
				    	"orgName" : $("#orgName").val(),
				    	"orgType" : $('#orgType').combobox('getValue')
					},
				    columns:[[
				        {field:'organization.orgCode',sortable:true,title:'统一社会信用代码',align:'center',width:'15%',
							formatter:function(value,rec){
								return '<a class="easyui-linkbutton" style="color:blue" href="${emms}/baseinfo/contractor.do?cmd=view&contractorId='+rec.contractorId+'" target="_self">'+rec.organization.orgCode+'</a>';
							}
						},
				        {field:'organization.orgName',sortable:true,title:'组织名称',align:'center',width:'15%',formatter:function(value,rec){return rec.organization.orgName;}},
				        {field:'organization.orgTypeName',sortable:true,title:'项目角色',align:'center',width:'15%',formatter:function(value,rec){return rec.organization.orgTypeName;}},
				        {field:'linkMan',sortable:true,title:'联系人',align:'center',width:'10%'},
				        {field:'linkPhone',sortable:true,title:'联系人电话',align:'center',width:'10%'},
				        {field:'email',sortable:true,title:'邮箱',align:'center',width:'15%'},
						{field:'contractorState',sortable:true,title:'状态',align:'center',width:'10%',
							formatter: function(value,row,index){
								if(row.contractorState =="contractorCheck"){
									return "审批中";
								}else if(row.contractorState =="contractorPass"){
									return "审批通过";
								}else if(row.contractorState =="contractorNoPass"){
									return "审批未通过";
								}else if(row.contractorState =="contractorNoCommit"){
									return "未提交";
								}
							}
						},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'11%',
							formatter: function(value,row,index){
								//-----------------------------------------------
								<%--show = "<a class='easyui-linkbutton' href='${emms}/baseinfo/contractor.do?cmd=edit&contractorId="--%>
										<%--+ row.contractorId--%>
										<%--+ "' target='_self'>查看</a>&nbsp;&nbsp;&nbsp;";--%>
								<%--&lt;%&ndash;show += "<a class='easyui-linkbutton' href='${emms}/baseinfo/contractor.do?cmd=commit&contractorId="&ndash;%&gt;--%>
									<%--&lt;%&ndash;+ row.contractorId&ndash;%&gt;--%>
									<%--&lt;%&ndash;+ "' target='_self'>提交</a>&nbsp;&nbsp;&nbsp;";&ndash;%&gt;--%>
								<%--show += "<a class='easyui-linkbutton' onclick='ajaxCommit(\""+row.contractorId+"\")'"--%>
								<%--+ " target='_self'>提交</a>&nbsp;&nbsp;&nbsp;";--%>
								<%--show += "<a class='easyui-linkbutton' href='${emms}/baseinfo/contractor.do?cmd=apply&contractorId="--%>
									<%--+ row.contractorId--%>
									<%--+ "' target='_self'>审核</a>&nbsp;&nbsp;&nbsp;";--%>
								<%--&lt;%&ndash;return show;&ndash;%&gt;--%>
								//--------------------------------------------
								show ="";
								if(row.contractorState=='contractorNoCommit' || row.contractorState=='contractorNoPass'){
									show += "<a class='easyui-linkbutton' href='${emms}/baseinfo/contractor.do?cmd=edit&contractorId="
									+ row.contractorId
									+ "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
									show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\""+row.contractorId+"\")'"
									+ " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
									show += "<a class='easyui-linkbutton' onclick='ajaxCommit(\""+row.contractorId+"\",\""+row.contractorState+"\")'"
									+ " target='_self'>提交</a>&nbsp;&nbsp;&nbsp;";
								}
								if(row.contractorState=='contractorCheck'){
									show += "<a class='easyui-linkbutton' onclick='ajaxCheckPass(\""+row.contractorId+"\",\""+"contractorPass"+"\")'"
									+ " target='_self'>通过</a>&nbsp;&nbsp;&nbsp;";
									show += "<a class='easyui-linkbutton' onclick='ajaxCheckNotPass(\""+row.contractorId+"\")'"
									+ " target='_self'>不通过</a>&nbsp;&nbsp;&nbsp;";
								}
								return show;
							}}
				    ]]
				});
			}
			//发送通过请求
			function ajaxCheckPass(contractorId,state) {
				$.messager.confirm("操作提示", "确定要提交当前记录吗？", function (data) {
					if(data){
						$.ajax({
							type: "POST",
							url:"${emms}/baseinfo/contractor.do?cmd=commit&contractorId="+contractorId+"&state="+state,
							async: false,
							success: function(data) {
								if(data == 'true'){
									$.messager.alert("操作提示", "提交成功！","info");
									window.location = "${emms}/baseinfo/contractor.do?cmd=query";
								}else{
									$.messager.alert("操作提示", data,"warning");
								}
							}
						});
					}
				});
			}
			//发送删除请求
			function ajaxDelete(id) {
				$.messager.confirm("操作提示", "确定要删除当前记录吗？", function (data) {
					if(data){
						$.ajax({
							type: "POST",
							url:"${emms}/baseinfo/contractor.do?cmd=deleteContractor&contractorId="+id,
							async: false,
							success: function(data) {
								if(data == 'true'){
									$.messager.alert("操作提示", "删除完成！","info",function(){
										query();
									});
								}else{
									$.messager.alert("操作提示", data,"warning");
								}
							}
						});
					}
				});
			}
			//发送审批未通过请求
			function ajaxCheckNotPass(id){
				$.ajax({
					type: "POST",
					url: '${emms}/baseinfo/contractor.do?cmd=CheckNotPass&contractorId='+id,
					async: false,
					success: function(data) {
						if(data == 'true'){
							$.messager.alert("操作提示", "执行成功","info");
							window.location = "${emms}/baseinfo/contractor.do?cmd=query";
						}else{
							$.messager.alert("操作提示",data,"warning");
						}
					}
				});
			}
			//发送提交请求
			function ajaxCommit(contractorId,way) {
				$.messager.confirm("操作提示", "确定要提交当前记录吗？", function (data) {
					if(data){
						if(way == "contractorNoCommit"){
							var state="contractorCheck";
						}else if(way == "contractorNoPass"){
							var state="contractorCheck";
						}else {
							var state="contractorPass";
						}
						$.ajax({
							type: "POST",
							url:"${emms}/baseinfo/contractor.do?cmd=commit&contractorId="+contractorId+"&state="+state,
							async: false,
							success: function(data) {
								if(data == 'true'){
									$.messager.alert("操作提示", "提交成功","info",function(){window.location = "${emms}/baseinfo/contractor.do?cmd=query";});

								}else{
									$.messager.alert("操作提示",data,"warning");
								}
							}
						});
					}
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
		</script>
	</body>
</html>
