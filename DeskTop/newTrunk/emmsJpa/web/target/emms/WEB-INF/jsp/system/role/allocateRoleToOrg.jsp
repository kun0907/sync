<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>编辑页</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
	<body>
		<div class="easyui-panel" title="首页->系统管理->组织机构管理->配置角色信息" data-options="fit:true,border:false">
			<form id="query" method="post">
				<select id='role' multiple='multiple'>
					<c:forEach items="${allRoleList}" var="item">
						<option value='${item.roleId}' <c:if test='${item.checked}'>selected</c:if>>${item.roleName}</option>
					</c:forEach>
				</select>
				<div style="text-align: center;">
					<a class="easyui-linkbutton" href="javascript:void(0)" iconCls='icon-save' onclick="saveRoleToOrg();">保存</a>
				</div>	
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$('#role').multiSelect();
		function saveRoleToOrg(){
			var roleIds = '';
			$('#role option:selected').each(function(){
				if(roleIds == ''){
					roleIds = $(this).val();
				}else{
					roleIds += "," + $(this).val();
				}
			});
			 $.ajax({
		            type: "POST",
		            url:"${emms}/system/organization.do?cmd=saveOrgRole&orgId=${orgId}&roleIds="+roleIds,
		            async: false,
		            success: function(data) {
						if(data == 'true'){
							alert("为组织机构成功分配角色");
							window.parent.location.href = "${emms}/system/organization.do?cmd=query&selfId=${orgId}&parentId=${orgId}&title=角色信息"
						}else{
							alert(data);
						}
		            }
		   });
		}
	</script>
</html>
