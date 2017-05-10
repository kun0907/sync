<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->基础信息管理->仓库管理->储位编辑" data-options="fit:true,border:false">
		<form id="ff" method="post">
			<input class="easyui-textbox" type="hidden" name="reservoirareaId"/>
			<input class="easyui-textbox" type="hidden" name="warehouseId"/>
			<input class="easyui-textbox" type="hidden" name="storagelocationId"/>
			<div style="padding:10px">
			   <c:if test="${empty storagelocation_id}">
				  <input class="easyui-textbox" id="storagelocationCode" name="storagelocationCode"  style="width:40%" data-options="label:'储位编码:',required:true,
				  validType:{
				            checkString:true,
							length:[0,20],
							remote:['${emms}/baseinfo/storagelocation.do?cmd=checkStoNo&parentId=${checkedNodeId}','storagelocationCode']
						}">
			   </c:if>
			   <c:if test="${not empty storagelocation_id}">
				  <input class="easyui-textbox" id="storagelocationCode" name="storagelocationCode"  style="width:40%" data-options="label:'储位编码:',required:true,readonly:true" >
			   </c:if>	
				<input class="easyui-textbox" id="storagelocationName" name="storagelocationName" style="width:40%" data-options="label:'储位名称:',required:true,validType:'length[0,50]'">
			</div>
			<div style="padding:10px">
				<input class="easyui-combobox" id="storagelocationType" style="width:40%" name="storagelocationType" data-options="label:'储位类型:',required:true,editable:false">
				<input class="easyui-textbox" name="longs" style="width:40%" data-options="label:'长:',validType:['length[0,20]','intOrFloat']" >
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" name="widths" style="width:40%" data-options="label:'宽:',validType:['length[0,20]','intOrFloat']" >
				<input class="easyui-textbox" name="height" style="width:40%" data-options="label:'高:',validType:['length[0,20]','intOrFloat']" >
			</div>
			<div style="padding:10px">
                <b>体积(立方米)限额:</b><input class="easyui-textbox" name="volume" style="width:25%" data-options="validType:['length[0,20]','intOrFloat']" >
				<b>称重(吨)限额:</b><input class="easyui-textbox" name="bearing" style="width:29%" data-options="validType:['length[0,20]','intOrFloat']" >
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
				<a href="${emms}/baseinfo/warehouse.do?cmd=queryStoragelocation&parentId=${checkedNodeId}"iconCls='icon-back' class="easyui-linkbutton">返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#ff').form('load', '${emms}/baseinfo/storagelocation.do?cmd=storagelocationInfo&checkedNodeId=${checkedNodeId}&storagelocation_id=${storagelocation_id}');
			 $('#ff').form({
					onLoadSuccess:function(data){
						if(null != data.storagelocationId){
							$("#storagelocationName").textbox({readonly:true});
						}
					}
				}); 
			$('#storagelocationType').combobox({
		        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=storagelocationtype',
		        valueField: 'dictionaryCode',
		        textField: 'dictionaryName',
		        multiple:false
			});
		});
	function ajaxSubmitForm() {
		  $("#ff").form("submit", {
             url: "${emms}/baseinfo/storagelocation.do?cmd=storagelocationEdit",
             onsubmit: function () {
                 return $(this).form("validate");
             },
             success: function (result) {
                /* $.messager.alert("操作提示",result);
                $("#dlg").dialog("close");
                $("#dg").datagrid("load");  */
//                alert(result);
                if(result=='保存成功'){
					$.messager.alert("操作提示","保存成功","info",function(){
						parent.document.getElementById('westFrame').src=parent.document.getElementById('westFrame').src;
						window.location = "${emms}/baseinfo/warehouse.do?cmd=queryStoragelocation&parentId=${checkedNodeId}";
					});
                }
				else{
					$.messager.alert("操作提示",result,"warning");
				}
			 }
         }); 
	 } 
	</script>
</body>		
</html>
