<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
		<div class="easyui-panel" title="首页->基本信息管理->仓库管理->储位查询" data-options="fit:true,border:false">
			<form id="query" method="post">
				<div style="padding:10px">
					<input class="easyui-textbox" id="storagelocationCode"  style="width:30%" data-options="label:'储位编码:'">
					<input class="easyui-textbox" id="storagelocationName" style="width:30%" data-options="label:'储位名称:'">
				    <select class="easyui-combobox" id="storagelocationType" style="width:30%" data-options="label:'储位类型:',editable:false"></select>
				</div>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
					<a href="${emms}/baseinfo/storagelocation.do?cmd=queryStoragelocationInfo&checkedNodeId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
					<a href="${emms}/baseinfo/storagelocation.do?cmd=addBatchStoragelocation&checkedNodeId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">批量添加</a>
				</div>
			</form>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="仓库列表">
			</table>
		</div> 
		<script type="text/javascript">
			$(function(){
				$('#storagelocationType').combobox({
			        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=storagelocationtype',
			        valueField: 'dictionaryCode',
			        textField: 'dictionaryName',
			        multiple:false
			    });
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/baseinfo/warehouse.do?cmd=selectStoragelocation&parentId=${parentId}',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    queryParams: {
				    	"storagelocationCode" : $("#storagelocationCode").val(),
				    	"storagelocationName" : $("#storagelocationName").val(),
				    	"storagelocationType" : $('#storagelocationType').combobox('getValue')
					},
				    columns:[[
				        {field:'storagelocationCode',sortable:true,title:'储位编码',align:'center',width:'27%'},
				        {field:'storagelocationName',sortable:true,title:'储位名称',align:'center',width:'25%'},
				        {field:'storagelocationType',sortable:true,title:'储位类型',align:'center',width:'25%'},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'25%',
							formatter: function(value,row,index){
								show = "<a class='easyui-linkbutton' href='${emms}/baseinfo/storagelocation.do?cmd=queryStoragelocationInfo&storagelocation_id="
										+ row.storagelocationId+"&checkedNodeId=${parentId}"
										+ "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
								show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\""+row.storagelocationId+"\")'"
									+ "' target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
								return show;
							}}
				    ]]
				});
			}
			function clearForm(){
				$('#query').form('clear');
			}
			function ajaxDelete(id) {
				$.messager.confirm("操作提示", "确定要删除当前记录吗？", function (data) {
					if(data){
					$.ajax({
			            type: "POST",
			            url:"${emms}/baseinfo/storagelocation.do?cmd=deleteStoragelocation&parentId=${parentId }&id="+id,
			            async: false,
			            success: function(data) {
//			            	alert(data);
							if(data=='删除完成'){
								$.messager.alert("操作提示","保存成功","info",function(){
									parent.document.getElementById('westFrame').src=parent.document.getElementById('westFrame').src;
									window.location = "${emms}/baseinfo/warehouse.do?cmd=queryStoragelocation&parentId=${parentId }";
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
		</script>
	</body>
</html>
