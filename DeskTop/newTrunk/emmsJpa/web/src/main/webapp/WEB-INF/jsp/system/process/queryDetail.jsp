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
  <a href="${emms}/system/process.do?cmd=editDetail&parentId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
  <table id="table" auto-resize="true" class="easyui-datagrid" title="审批列表" width="100%"></table>
</div>
<script type="text/javascript">
  $(function(){
    query();
  });
  function query(){
    $('#table').datagrid({
      url:'${emms}/system/process.do?cmd=loadDetailListData',
      method: 'POST',
      pagination: true,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:true,
      queryParams: {
        "parentId" : '${parentId}'
      },
      columns:[[
        {field:'processDetailLevel',sortable:true,title:'审批级别',align:'center',width:'30%'},
        {field:'roleId',sortable:true,title:'角色名称',align:'center',width:'30%'},
        {field:'aaa',title:'操作',sortable:true,align:'center',width:'30%',
          formatter: function(value,row,index){
            show = "<a class='easyui-linkbutton' href='${emms}/system/process.do?cmd=editDetail&parentId=${parentId}&processDetailId="
            + row.processDetailId
            + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
           /* show += "<a href=\"javascript:delDetail(\'"+ row.processDetailId + "\')\">删除</a>&nbsp;&nbsp;&nbsp;";*/
            return show;
          }}
      ]]
    });
  }
  function clearForm(){
    $('#query').form('clear');
  }

  function delDetail(processDetailId){
    if(window.confirm('确定要删除当前记录吗？')){
      $.ajax({
        url:"${emms}/system/process.do?cmd=deleteDetail&id="+processDetailId,
        type:"POST",
        success: function(data) {
          if(data == 'true'){
            $.messager.alert("操作提示", "删除成功！",function(){
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
