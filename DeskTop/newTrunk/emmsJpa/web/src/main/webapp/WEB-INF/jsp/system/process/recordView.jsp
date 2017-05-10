<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title></title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
  <link rel="stylesheet" href="${emms}/css/indexStyle.css">
</head>

<body style="width:958px; height:450px;">
<div class="easyui-panel" title="" data-options="fit:true,border:false">
  <table id="table1" auto-resize="true" class="easyui-datagrid" title="审批记录" width="100%"></table>
</div>
<script type="text/javascript">
  $(function(){
    query();
  });
  function query(){
    $('#table1').datagrid({
      url:'${emms}/system/process.do?cmd=loadApproveRecord&id=${id}',
      method: 'POST',
      pagination: false,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:true,
      columns:[[
        {field:'sequence',sortable:true,title:'审批级别',align:'center',width:'20%',
          formatter:function(value){
            return value+"级审批"
          }
         },
        {field:'roleName',sortable:true,title:'角色名称',align:'center',width:'20%'},
        {field:'approveTime',sortable:true,title:'审批时间',align:'center',width:'20%'},
        {field:'approveUserName',sortable:true,title:'审批人',align:'center',width:'20%'},
        {field:'isApprove',sortable:true,title:'是否审批',align:'center',width:'20%',
          formatter:function(value){
            if(value){
              return "是"
            }else{
              return "否"
            }
        }}
      ]]
    });
  }
</script>
</body>
</html>
