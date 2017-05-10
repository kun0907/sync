<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->基础信息管理->仓库管理->库区编辑" data-options="fit:true,border:false">
		<form id="ff" method="post">
			<input class="easyui-textbox" type="hidden" name="reservoirareaId"/>
			<input class="easyui-textbox" type="hidden" name="warehouseId"/>
			<div style="padding:10px">
			   <c:if test="${empty reservoirarea_id}">
				   <input class="easyui-textbox" id="reservoirareaCode" name="reservoirareaCode"  style="width:40%" data-options="label:'库区编码:',required:true,
				   validType:{
				            checkString:true,
							length:[0,10],
							remote:['${emms}/baseinfo/reservoirarea.do?cmd=checkStoNo&parentId=${checkedNodeId}','reservoirareaCode']
						}" >
				</c:if>
				<c:if test="${not empty reservoirarea_id}">
				   <input class="easyui-textbox" id="reservoirareaCode" name="reservoirareaCode"  style="width:40%" data-options="label:'库区编码:',required:true,readonly:true" >
				</c:if>
				<input class="easyui-textbox" id="reservoirareaName" name="reservoirareaName" style="width:40%" data-options="label:'库区名称:',required:true,validType:'length[0,50]'">
			</div>
			<div style="padding:10px">
				<input class="easyui-combobox" id="reservoirareaType" style="width:40%" name="reservoirareaType" data-options="label:'库区类型:',required:true,editable:false">
				<input class="easyui-textbox" name="acreage" style="width:40%" data-options="label:'面积:',validType:['length[0,20]','intOrFloat']" >
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" name="contacts" style="width:40%" data-options="label:'联系人:',validType:'length[0,50]'">
				<input class="easyui-textbox" name="phone" style="width:40%" data-options="label:'电话:'" validtype="mobile">
			</div>
			<div style="padding:10px">
                <input class="easyui-textbox" name="fax" style="width:40%" data-options="label:'传真:'" validtype="fax">
			    <input class="easyui-textbox" name="remark" style="width:40%" data-options="label:'备注:',validType:'length[0,500]'">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
				<a href="${emms}/baseinfo/warehouse.do?cmd=queryReservoirarea&parentId=${checkedNodeId}"iconCls='icon-back' class="easyui-linkbutton">返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#ff').form('load', '${emms}/baseinfo/reservoirarea.do?cmd=reservoirareaInfo&checkedNodeId=${checkedNodeId}&reservoirarea_id=${reservoirarea_id}');
			 $('#ff').form({
					onLoadSuccess:function(data){
						if(null != data.reservoirareaId){
							$("#reservoirareaCode").textbox({readonly:true});
							$("#reservoirareaName").textbox({readonly:true});
						}
					}
				}); 
			$('#reservoirareaType').combobox({
		        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=reservoirareatype',
		        valueField: 'dictionaryCode',
		        textField: 'dictionaryName',
		        multiple:false
			});
		});
	function ajaxSubmitForm() {
		  $("#ff").form("submit", {
             url: "${emms}/baseinfo/reservoirarea.do?cmd=reservoirareaEdit",
             onsubmit: function () {
                 return $(this).form("validate");
             },
             success: function (result) {
//               alert(result);
               if(result=='保存成功'){
				   $.messager.alert("操作提示","保存成功","info",function(){
					   parent.document.getElementById('westFrame').src=parent.document.getElementById('westFrame').src;
					   window.location = "${emms}/baseinfo/warehouse.do?cmd=queryReservoirarea&parentId=${checkedNodeId}";});

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
