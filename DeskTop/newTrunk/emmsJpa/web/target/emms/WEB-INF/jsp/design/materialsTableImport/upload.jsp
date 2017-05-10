<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
	
<body>
	<div class="easyui-panel">
		<form id="upload" enctype="multipart/form-data" method="post" >
			<div style="padding:10px">
				<select class="easyui-combobox" id="designOrgId" name="designOrgId" style="width:55%" data-options="label:'选择设计院:', required:true" ></select>
				<select class="easyui-combobox" id="materialsTableType" name="materialsTableType" style="width:40%" data-options="label:'物资类别:', required:true"></select>
				<input class="easyui-filebox" id="excel" name="excel" data-options="prompt:'点击选择文件', required:true" style="width:100%">
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="doSubmit()">保存</a>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		
		$('#excel').filebox({
			buttonText: '选择文件',
			buttonAlign: 'right'
		})
		$('#designOrgId').combobox({
			url: '${emms}/design/materialstableImprot.do?cmd=loadOrg',
			valueField: 'orgId',
			textField: 'orgName'
		});
		$('#materialsTableType').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=MaterialsTypeCategory',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName'
		});
		//提交
		function doSubmit(){
			$("#upload").form("submit", {
				url:"${emms}/design/materialstableImprot.do?cmd=poi", //请求url
				onSubmit: function () {
					return $(this).form("validate");
				},
				success: function (result) {
					if(result=='文件上传完毕'){
						$.messager.alert("提示",result,"info",function(){
							window.frames["mainFrame"].location = "${emms}/design/materialstableImprot.do?cmd=queryMatrialsTable";
							top.$("#dialog").dialog("close");
						});
					}else{
							$.messager.alert("提示",result,"error")
					}
				}
			});
		}
	</script>
</body>
</html>
