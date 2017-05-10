<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <title>流程编辑</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->系统管理->流程定义->编辑" data-options="fit:true,border:false">
  <form id="ff" method="post">
    <input class="easyui-textbox" type="hidden" id="processId" name="processId" value="${processId}"/>
    <div style="margin:20px">
      <input class="easyui-textbox" id="processName" name="processName" maxlength="50" style="width:40%" data-options="label:'流程名称:',required:true">
      <input class="easyui-combobox" id="processType" name="processType" maxlength="50" style="width:40%" data-options="label:'流程类型:',required:true,editable:false">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
      <a href="javascript:void(0)"iconCls='icon-back' class="easyui-linkbutton" onclick="back()">返回</a>
    </div>
  </form>
</div>
<script type="text/javascript">
  $(function(){
    $('#processType').combobox({
      url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=CHECKTYPE',
      valueField: 'dictionaryCode',
      textField: 'dictionaryName'
    });
    $('#ff').form('load', '${emms}/system/process.do?cmd=loadProcessData&processId=${processId}');
  });
  function ajaxSubmitForm(){
    $("#ff").form("submit", {
      url: "${emms}/system/process.do?cmd=save&parentId=${parentId}",
      onsubmit: function () {
        return $(this).form("validate");
      },
      success: function (result) {
        if(result == 'true'){
          $.messager.alert("操作提示", "保存成功！","info",function(){
            window.parent.frames["westFrame"].initTree();
            window.location.href = "${emms}/system/process.do?cmd=query";
          });
        }else{
          $.messager.alert("操作提示",result,"warning");
        }
      }
    });
  }
  function back(){
    window.location.href = "${emms}/system/process.do?cmd=query";
  }
</script>
</body>
</html>
