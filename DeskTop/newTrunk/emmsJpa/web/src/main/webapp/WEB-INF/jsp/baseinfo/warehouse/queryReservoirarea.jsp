<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
		<div class="easyui-panel" title="首页->基本信息管理->仓库管理->库区查询" data-options="fit:true,border:false">
			<a href="${emms}/baseinfo/reservoirarea.do?cmd=queryreservoirareaInfo&checkedNodeId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
			<table id="table" auto-resize="true" class="easyui-datagrid" title="库区列表">
			</table>
		</div> 
		<script type="text/javascript">
			$(function(){
				query();
			});
			function query(){
				$('#table').datagrid({
				    url:'${emms}/baseinfo/warehouse.do?cmd=selectReservoirarea&parentId=${parentId}',
				    method: 'POST',
				    pagination: true,
				    fitColumns: true,
				    rownumbers: true,
				    showFooter: true,
				    columns:[[
				        {field:'reservoirareaCode',sortable:true,title:'区域编码',align:'center',width:'22%'},
				        {field:'reservoirareaName',sortable:true,title:'区域名称',align:'center',width:'20%'},
				        {field:'reservoirareaType',sortable:true,title:'区域类型',align:'center',width:'20%'},
				        {field:'acreage',sortable:true,title:'面积',align:'center',width:'20%'},
				        {field:'aaa',title:'操作',sortable:true,align:'center',width:'20%',
							formatter: function(value,row,index){
								show = "<a class='easyui-linkbutton' href='${emms}/baseinfo/reservoirarea.do?cmd=queryreservoirareaInfo&reservoirarea_id="
										+ row.reservoirareaId+"&checkedNodeId=${parentId}"
										+ "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
								show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\""+row.reservoirareaId+"\")'"
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
				            url:"${emms}/baseinfo/reservoirarea.do?cmd=deleteReservoirarea&parentId=${parentId }&id="+id,
				            async: false,
				            success: function(data) {
//				            	alert(data);
								if(data=='删除完成'){
									$.messager.alert("操作提示","删除成功","info",function(){
										parent.document.getElementById('westFrame').src=parent.document.getElementById('westFrame').src;
										window.location = "${emms}/baseinfo/warehouse.do?cmd=queryReservoirarea&parentId=${parentId }";
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
