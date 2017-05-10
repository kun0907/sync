<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
	<div class="easyui-panel" title="首页->基础信息管理->仓库管理->仓库编辑" data-options="fit:true,border:false">
		<form id="ff" method="post">
			<input class="easyui-textbox" type="hidden" id="warehouseId" name="warehouseId"/>
			<div style="padding:10px" >
				<c:if test="${empty warehouseId}">
				     <input class="easyui-textbox" id="warehouseCode" name="warehouseCode"  style="width:40%"  data-options="label:'仓库编码:',required:true,
				       validType:{
				            length:[0,10],
				            checkString:true,
							remote:['${emms}/baseinfo/warehouse.do?cmd=checkWareNo','warehouseCode']
						}" >
				</c:if>
				<c:if test="${not empty warehouseId}">
				     <input class="easyui-textbox" id="warehouseCode" name="warehouseCode"  style="width:40%"  data-options="label:'仓库编码:',required:true,readonly:true" >
				</c:if>
				<input class="easyui-textbox" id="warehouseName" name="warehouseName" style="width:40%" data-options="label:'仓库名称:',required:true,validType:'length[0,50]'">
			</div>
			<div style="padding:10px">
				<input class="easyui-combobox" id="country" style="width:40%" name="country" data-options="label:'国家:',editable:false">
			    <input class="easyui-combobox" id="city" style="width:40%" name="city" data-options="label:'市:',editable:false">
			</div>
			<div style="padding:10px">
				<input class="easyui-combobox" id="area" style="width:40%" name="area" data-options="label:'区:',editable:false">
				<input class="easyui-textbox" name="acreage" style="width:40%" data-options="label:'面积:',validType:['length[0,20]','intOrFloat']" >
			</div>
			<div style="padding:10px">
				<input class="easyui-combobox" id="warehouseType" name="warehouseType" style="width:40%" data-options="label:'仓库类型:',required:true,editable:false">
				<input class="easyui-textbox" name="contacts" style="width:40%" data-options="label:'联系人:',validType:'length[0,50]'">
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" name="phone" style="width:40%" data-options="label:'电话:'" validtype="mobile">
                <input class="easyui-textbox" name="fax" style="width:40%" data-options="label:'传真:'" validtype="fax">
			</div>
			<div style="padding:10px">
				<input class="easyui-textbox" name="remark" style="width:40%" data-options="label:'备注:',validType:'length[0,500]'">
			  <%-- <c:if test="${empty warehouseId}">
			    <input class="easyui-combobox" id="orgId" name="orgId" style="width:40%" data-options="label:'组织机构:'">
			   </c:if>--%>
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
				<a href="${emms}/baseinfo/warehouse.do?cmd=queryWareHouse"iconCls='icon-back' class="easyui-linkbutton">返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#ff').form('load', '${emms}/baseinfo/warehouse.do?cmd=wareHouseInfo&warehouseId=${warehouseId}');
			 $('#ff').form({
				onLoadSuccess:function(data){
					 if(null != data.warehouseId){
						 $("#warehouseName").textbox({readonly:true});
					} 
				}
			}); 
			 $('#orgId').combobox({
			        url: '${emms}/system/organization.do?cmd=selectAll',
			        valueField: 'orgId',
			        textField: 'orgName',
			        multiple:true
				});
			 $('#country').combobox({
		        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=country',
		        valueField: 'dictionaryCode',
		        textField: 'dictionaryName',
		        multiple:false
			});
			$('#city').combobox({
		        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=city',
		        valueField: 'dictionaryCode',
		        textField: 'dictionaryName',
		        multiple:false
		    });
			$('#area').combobox({
		        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=area',
		        valueField: 'dictionaryCode',
		        textField: 'dictionaryName',
		        multiple:false
		    });
			$('#warehouseType').combobox({
		        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=warehousetype',
		        valueField: 'dictionaryCode',
		        textField: 'dictionaryName',
		        multiple:false
		    });
		});
	function ajaxSubmitForm() {
		  $("#ff").form("submit", {
             url: "${emms}/baseinfo/warehouse.do?cmd=wareHouseEdit",
             onsubmit: function () {
                 return $(this).form("validate");
             },
             success: function (result) {
//                alert(result);
                if(result=='保存成功'){
					$.messager.alert("操作提示","保存成功","info",function(){
						parent.document.getElementById('westFrame').src=parent.document.getElementById('westFrame').src;
						window.location = "${emms}/baseinfo/warehouse.do?cmd=queryWareHouse";
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
