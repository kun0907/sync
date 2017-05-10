<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body style="padding: 0px;">
	<div class="easyui-tabs">
		<div title="用户基本信息">
			<div class="easyui-panel" title="首页->设置->修改用户信息" data-options="fit:true,border:false">
				<form id="ff" method="post">
					<input type="hidden" name="employee.organization.orgId" id="orgId"  value="${orgId}"/>
					<input type="hidden" name="userId" value="${userId}"/>
					<input type="hidden" name=employee.empId id="empId"/>
					<div style="margin:20px">
						<input class="easyui-textbox" id="userName"  editable="false" name="userName"  style="width:40%" data-options="label:'登录名:',required:true,validType:['length[1,100]']" >
						<input class="easyui-textbox" id="empName" name="employee.empName"  style="width:40%" data-options="label:'用户姓名:',required:true,validType:['length[1,100]']" >
					</div>
					<div style="margin:20px">
						<input class="easyui-textbox" id="cellPhone" name="employee.cellPhone"  style="width:40%" data-options="label:'手机号码:',required:true,validType:['length[1,20]']" >
						<input class="easyui-textbox" id="email" name="employee.email"  style="width:40%" data-options="label:'电子邮箱:',required:true,validType:['length[1,100]']" >
					</div>
				</form>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' id="saveUser">保存</a>
				</div>
			</div>
		</div>
		<div title="用户密码">
			<div class="easyui-panel" title="首页->设置->修改用户信息" data-options="fit:true,border:false">
				<form id="passwordInfo" method="post">
					<input type="hidden" id="userId" name="userId" value="${userId}"/>
					<div style="margin:20px">
						<input class="easyui-textbox" id="oldPwd" type="password"  style="width:80%" data-options="label:'原密码:',required:true" validType='checkOldPwd' >
					</div>
					<div style="margin:20px">
						<input class="easyui-textbox" id="newPassWord" type="password" style="width:40%" data-options="label:'新密码:',required:true" >
						<input class="easyui-textbox" id="confirmPwd" type="password"  style="width:40%" data-options="label:'确认密码:',required:true"  validType="equalTo['#newPassWord']">
					</div>
				</form>
				<div style="text-align: center;">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' id="savePwd">保存</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			$('#ff').form('load', '${emms}/system/user.do?cmd=userInfo&userId=${userId}');
			$('#ff').form({
				onLoadSuccess:function(data){
					if(null != data.employee){
						$("#empName").textbox("setValue", data.employee.empName);
						$("#cellPhone").textbox("setValue", data.employee.cellPhone);
						$("#email").textbox("setValue", data.employee.email);
						$("#empId").val(data.employee.empId);
					}
				}
			});
		$(function(){
			$("#saveUser").click(function(){
				$("#ff").form("submit", {
					url: "${emms}/system/user.do?cmd=saveUser",
					onsubmit: function () {
						return $(this).form("validate");
					},
					success: function (result) {
						if(result){
							$.messager.alert("操作提示", "保存成功！","info",function(){
								top.$("#dialog").dialog('close');
							});
						}else{
							$.messager.alert("操作提示", result,"warning");
						}
					}
				});
			});
			$("#savePwd").click(function(){
				$("#passwordInfo").form("submit", {
					url: "${emms}/system/user.do?cmd=savePwd&newPwd="+$("#newPassWord").val(),
					type:'POST',
					onsubmit: function () {
						return $(this).form("validate");
					},
					success: function (result) {
						if(result){
							$.messager.alert("操作提示", "提交成功！","info",function(){
								top.$("#dialog").dialog('close');
							});
						}else{
							$.messager.alert("操作提示", result,"warning");
						}
					}
				});
			});
		});
	</script>
</body>
</html>
