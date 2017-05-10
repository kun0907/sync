<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/10
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >

<html>
<head>
  <title>列表页</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel"  data-options="fit:true,border:false">
  <input type="hidden" id="supplier" value=${supplier}>
  <form id="query" method="post">
    <div style="padding:10px">
      <B>框架协议编码:</B>
      <input class="easyui-textbox" id="supplierAgreementCode" style="width:22%">
      <input class="easyui-textbox" id="materialsCode" style="width:30%" data-options="label:'物资编码:'">
      <input class="easyui-textbox" id="materialsDescribe" style="width:30%" data-options="label:'物资描述:'">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="confirm()">保存</a>
    </div>
  </form>
  <table id="table" auto-resize="true" class="easyui-datagrid" title="物资列表" style="height:350px">
  </table>
</div>
<script type="text/javascript">
  $(function(){
    query();
  });
  function query() {
    $('#table').datagrid({
      url: '${emms}/purchase/agreement.do?cmd=loadAgreementDetailListData',
      method: 'POST',
      pagination: true,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      queryParams: {
        "supplier": $("#supplier").val(),
        "supplierAgreementCode": $("#supplierAgreementCode").val(),
        "materialsCode": $("#materialsCode").val(),
        "materialsDescribe": $("#materialsDescribe").val()
      },
      columns: [[
        {field: 'materialsId', hidden:true},
        {field: 'supplierAgreementCode', sortable: true, title: '框架协议编码', align: 'center', width: '12%'},
        {field: 'materialsCode', sortable: true, title: '物资编码', align: 'center', width: '12%'},
        {field: 'materialsDescribe', sortable: true, title: '物资描述', align: 'center', width: '15%'},
        {field: 'additional1', sortable: true, title: '附加1', align: 'center', width: '13%'},
        {field: 'additional2', sortable: true, title: '附加2', align: 'center', width: '12%'},
        {field: 'additional3', sortable: true, title: '附加3', align: 'center', width: '12%'},
        {field: 'additional4', sortable: true, title: '附加4', align: 'center', width: '13%'},
        {field: 'materialsUnitMain', sortable: true, title: '采购计量单位', align: 'center', width: '12%'},
        {field: 'unitPrice', sortable: true, title: '采购单价', align: 'center', width: '12%'}
      ]]
    });
  }
  function clearForm() {
    $('#query').form('clear');
  }


  function confirm(){
    var rows = $('#table').datagrid('getSelections');
    if(rows.length == 0){
      alert("请框架协议信息");
      return false;
    }else{
      window.parent.frames["mainFrame"].checkAgreement(rows);
      top.$("#dialog").dialog("close");
    }
  }


</script>
</body>
</html>