<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>编辑页</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
	<body>
	<div class="easyui-panel" title="首页->系统管理->人员信息管理-><c:if test="${empty userId}">新建</c:if><c:if test="${not empty userId}">查看</c:if>" data-options="fit:true,border:false">
		<form id="ff" method="post">
			<input type="hidden" name="employee.organization.orgId" id="orgId" value="${orgId}"/>
			<input type="hidden" name="userId" value="${userId}"/>
			<input type="hidden" name="employee.empId" id="empId"/>
			<div style="margin:20px">
				<c:if test="${empty userId}">
					<input class="easyui-textbox" id="userName"  name="userName"  style="width:30%" data-options="label:'登录用户名:',required:true,
						validType:{
							length:[1,50],
							remote:['${emms}/system/user.do?cmd=checkUserName','userName']
						   }
					" >
				</c:if>
				<c:if test="${not empty userId}">
					<input class="easyui-textbox" id="userName"  name="userName" editable="false"  style="width:30%" data-options="label:'登录用户名:'">
				</c:if>

				<input class="easyui-textbox" id="empNo" name="employee.empNo" style="width:30%" data-options="label:'人员编码:',required:true,validType:'length[1,50]'">
				<input class="easyui-textbox" id="empName" name="employee.empName" style="width:30%" data-options="label:'人员姓名:',required:true,validType:'length[1,50]'">
			</div>
			<div style="margin:20px">
				<select class="easyui-combobox" id="sex" name="employee.sex"  style="width:30%" data-options="label:'性别:',required:true" ></select>
				<input class="easyui-datebox" id="birthday"  editable="false" name="employee.birthday" style="width:30%" data-options="label:'出生日期:',required:true">
				<input class="easyui-textbox" id="email" name="employee.email" style="width:30%" data-options="label:'办公邮件:',required:true,validType:['email','length[1,100]']">
			</div>
			<div style="margin:20px">
				<input class="easyui-textbox" id="cellPhone" name="employee.cellPhone"  style="width:30%" data-options="label:'手机号码:',required:true,validType:['mobile','length[0,20]']" >
			</div>
			<div style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
				<a href="javascript:void(0)" iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$('#sex').combobox({
			url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=emp_sex',
			valueField: 'dictionaryCode',
			textField: 'dictionaryName'
		});
		$(function(){
			$('#ff').form('load', '${emms}/system/user.do?cmd=editUser&orgId=${orgId}&userId=${userId}');
			$('#ff').form({
				onLoadSuccess:function(data){
					$("#empNo").textbox("setValue", data.employee.empNo);
					$("#empName").textbox("setValue", data.employee.empName);
					if(null!=data.userId) {
						$("#sex").combobox("setValues", (data.employee.sex).split(","));
					}
					$("#birthday").textbox("setValue", data.employee.birthday);
					$("#email").textbox("setValue", data.employee.email);
					$("#cellPhone").textbox("setValue", data.employee.cellPhone);
					$("#empId").val(data.employee.empId);
					if(null != data.userId){
						$("#userName").textbox({readonly:true});
					}
				}
			});

		});
	function ajaxSubmitForm() {
		  $("#ff").form("submit", {
             url: "${emms}/system/user.do?cmd=saveUser",
             onsubmit: function () {
                 return $(this).form("validate");
             },
             success: function (result) {
				 if(result){
					 alert('用户信息保存成功');
//					 $('#dialog').dialog('close');
					 query();
				 }else{
					 alert(result);
				 }
             }
         }); 
	}
	//新建人员信息后返回列表页面
	function query(){
		window.location = "${emms}/system/employee.do?cmd=query&orgId=${orgId}";
	}
	function back(){
		window.location.href = "${emms}/system/employee.do?cmd=query&orgId=${orgId}";
	}
	</script>	
</body>
</html>
