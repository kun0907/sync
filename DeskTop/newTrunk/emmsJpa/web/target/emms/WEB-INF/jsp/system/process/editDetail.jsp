<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <title>流程编辑</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->系统管理->审批->编辑" data-options="fit:true,border:false">
  <form id="ff" method="post">
    <input class="easyui-textbox" type="hidden" id="processId" name="processId" value="${processId}"/>
    <input class="easyui-textbox" type="hidden" id="processDetailId" name="processDetailId" value="${processDetailId}"/>
    <div style="margin:20px">
      <input class="easyui-textbox" id="processDetailLevel" name="processDetailLevel" maxlength="50" style="width:40%" data-options="label:'审批级别:',readonly:true">
      <input class="easyui-combobox" id="roleId" name="roleId" maxlength="50" style="width:40%" data-options="label:'角色类型:',required:true,editable:false">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
      <a href="javascript:void(0)"iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
    </div>
  </form>
</div>
<script type="text/javascript">
  $(function(){
    $('#roleId').combobox({
      url: '${emms}/system/userRole.do?cmd=selectRoleByOrgData&orgId=${orgId}&userId=${userId}',
      valueField: 'roleId',
      textField: 'roleName'
    });
    $('#ff').form('load', '${emms}/system/process.do?cmd=loadDetailData&processDetailId=${processDetailId}&parentId=${parentId}');
  });
  function ajaxSubmitForm(){
    $("#ff").form("submit", {
      url: "${emms}/system/process.do?cmd=saveDetail&parentId=${parentId}",
      onSubmit: function () {
        return $(this).form("validate");
      },
      success: function (result) {
        if(result == 'true'){
          $.messager.alert("操作提示", "保存成功！","info",function(){
            window.location.href = "${emms}/system/process.do?cmd=queryDetail&parentId=${parentId}";
          });
        }else{
          $.messager.alert("操作提示",result,"warning");
        }
      }
    });
  }
  function back(){
    window.location.href = "${emms}/system/process.do?cmd=queryDetail&parentId=${parentId}";
  }
</script>
</body>
</html>
