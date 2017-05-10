<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>编辑页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
	
<body>
	<div class="easyui-panel" title="首页->系统管理->业务字典管理->新建字典" data-options="fit:true,border:false">
		<form id="dictionary" name="dictionary" method="post">
			<input class="easyui-textbox" type="hidden" name="parentId"/>
			<input class="easyui-textbox" type="hidden" name="dictionaryId"/>
			<table class="table">
				<tr>
					<td>
						<label style="font-weight:700;text-overflow:ellipsis;width:80px;font-family:verdana,helvetica,arial,sans-serif;font-size:12px;line-height:27px;">
							字典编码:
						</label>
					</td>
					<td class=" col-xs-8">
						<input class="easyui-validatebox textbox" style="height: 27px;font-size:12px;font-weight:400;writing-mode:horizontal-tb;"
						name="dictionaryCode" id="dictionaryCode" data-options="required:true,validType:'length[1,25]'">
					</td>
					<td>
						<label style="font-weight:700;text-overflow:ellipsis;width:80px;font-family:verdana,helvetica,arial,sans-serif;font-size:12px;line-height:27px;">
							字典名称:
						</label>
					<td class=" col-xs-8">
						<div>
							<input class="easyui-validatebox textbox" style="height: 27px;font-size:12px;font-weight:400;writing-mode:horizontal-tb;"
						 	name="dictionaryName" id="dictionaryName"data-options="required:true,validType:'length[1,50]'">
						</div>
					</td>
				</tr>
			</table>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="doSubmit()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-back' onclick="dack()"> 返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	
		var dicType;
		var checkUrl;
		var editUrl;
		var selectUrl;
	
		$(function(){
			$('#dictionary').form('load', '${emms}/system/businessDictionary.do?cmd=getDictionaryInfo&parentId=${parentId}&dictionaryId=${dictionaryId}');
			$('#dictionary').form({
				onLoadSuccess:function(data){
					dicType = '${dicType}';
					if(dicType == 0){
						checkUrl = "${emms}/system/businessDictionary.do?cmd=checkCode&dictionaryCode=";
						editUrl = "${emms}/system/businessDictionary.do?cmd=dictionaryEdit";
						selectUrl = "${emms}/system/businessDictionary.do?cmd=query&parentId=" + data.parentId;
					} else if(dicType == 1){
						checkUrl = "${emms}/system/dataDictionary.do?cmd=checkCode&dictionaryCode=";
						editUrl = "${emms}/system/dataDictionary.do?cmd=dictionaryEdit";
						selectUrl = "${emms}/system/dataDictionary.do?cmd=query&parentId=" + data.parentId;
					} else if(dicType == 2){
						checkUrl = "${emms}/unit/checkCode.do";
						editUrl = "${emms}/unit/dictionaryEdit.do";
						selectUrl = "${emms}/unit/selectDictionaryByParentId.do?parentId="+$("#parentId").val();
					}
					if(data.dictionaryCode!=null){
						$('.col-xs-8').eq(0).empty();
						//var label = $('<label class="text-right">字典编码：</th>');
						var span = $('<span style="margin-right:19px;font-size:12px;line-height:27px;"></span>');
						span.html(data.dictionaryCode);
						//$('.aa').append(label);
						$('.col-xs-8').eq(0).append(span);
					}
				}
			});
		});
		
		function doSubmit() {
			$.ajax({
				type: "POST",
				url: checkUrl + $("#dictionaryCode").val(),
				async: false,
				success: function(data) {
					if(data==true){
						ajaxSubmitForm();//调用验证方法
					}else{
						$.messager.alert("操作提示","编码重复");
					}
				}
			});
	 	}

		function ajaxSubmitForm() {
			$("#dictionary").form("submit", {
				url: editUrl,
				onSubmit: function () {
					return $(this).form("validate");
				},
				success: function (result) {
					$.messager.alert("操作提示",result,"info",function(){
						window.parent.westFrame.location.reload();
						window.location = selectUrl;
					});
				}
			});
		}

		function dack(){
			window.location = selectUrl;
		}
	</script>
</body>
</html>
