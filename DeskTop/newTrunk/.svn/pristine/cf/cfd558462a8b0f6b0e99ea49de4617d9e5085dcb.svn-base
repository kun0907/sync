<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <title>权限管理</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->系统管理->流程定义->查询" data-options="fit:true,border:false">
  <a href="${emms}/system/process.do?cmd=edit&parentId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
  <table id="table" auto-resize="true" class="easyui-datagrid" title="流程类型" width="100%"></table>
</div>
<script type="text/javascript">
  $(function(){
    query();
  });
  function query(){
    $('#table').datagrid({
      url:'${emms}/system/process.do?cmd=loadProcessListData',
      method: 'POST',
      pagination: true,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:true,
      columns:[[
        {field:'processName',sortable:true,title:'流程名称',align:'center',width:'20%'},
        {field:'processType',sortable:true,title:'流程类型',align:'center',width:'20%'},
        {field:'createTime',sortable:true,title:'创建时间',align:'center',width:'20%'},
        {field:'createUserName',sortable:true,title:'创建人',align:'center',width:'20%'},
        {field:'aaa',title:'操作',sortable:true,align:'center',width:'20%',
          formatter: function(value,row,index){
            show = "<a class='easyui-linkbutton' href='${emms}/system/process.do?cmd=edit&processId="
            + row.processId
            + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
            show += "<a href=\"javascript:delProcess(\'"+ row.processId + "\')\">删除</a>&nbsp;&nbsp;&nbsp;";
            return show;
          }}
      ]]
    });
  }
  function clearForm(){
    $('#query').form('clear');
  }

  function delProcess(processId){
    if(window.confirm('确定要删除当前记录吗？')){
      $.ajax({
        url:"${emms}/system/process.do?cmd=deleteProcess&id="+processId,
        type:"POST",
        success: function(data) {
          if(data == 'true'){
            $.messager.alert("操作提示", "删除成功！",function(){
              window.parent.frames["westFrame"].initTree();
              query();
            });
          }else{
            $.messager.alert("操作提示", "删除失败！","warning");
          }

        }

      });
    }
  }
</script>
</body>
</html>
