<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <title>列表页</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" data-options="fit:true,border:false">
  <input type="hidden" id="supplierId" value="${supplierId}">
  <input type="hidden" id="dataSource" value="${dataSource}">
  <form id="query" method="post">
    <div style="padding:10px">
      <input class="easyui-textbox" id="receiptCode"   style="width:30%" data-options="label:'收货单编号:'">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
    </div>
  </form>
  <table id="table" class="easyui-datagrid" title="明细列表"  style="height:360px">
  </table>
</div>
<script type="text/javascript">
  $(function(){
    query();
  });
  function query(){
    $('#table').datagrid({
      url:'${emms}/instorage/receiptGoods.do?cmd=loadReceiptDetailListData',
      method: 'POST',
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      selectOnCheck:true,
      checkOnSelect:true,
      queryParams: {
        "receiptCode" : $("#receiptCode").val(),
        "supplierId" : $("#supplierId").val(),
        "dataSource":$("#dataSource").val()
      },
      onLoadSuccess: function(){
        $(this).datagrid('freezeRow',-1).datagrid('freezeRow',-1);
      },
      columns:[[
        {field:'packingDetailId',checkbox:true},
        {field:'packingId',hidden:true},
        {field:'receiptId',hidden:true},
        {field:'wbsId',hidden:true},
        {field:'materialsId',hidden:true},
        {field:'packingCode',sortable:true,title:'包装编号',align:'center',width:'10%'},
        {field:'receiptCode',sortable:true,title:'收货单编号',align:'center',width:'10%'},
        {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'10%'},
        {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
        {field:'additional1',sortable:true,title:'附加1',align:'center',width:'10%'},
        {field:'additional2',sortable:true,title:'附加2',align:'center',width:'10%'},
        {field:'additional3',sortable:true,title:'附加3',align:'center',width:'10%'},
        {field:'additional4',sortable:true,title:'附加4',align:'center',width:'10%'},
        {field:'wbsCode',sortable:true,title:'工程（WBS）编码',align:'center',width:'10%'},
        {field:'deMainUnit',sortable:true,title:'采购计量单位',align:'center',width:'10%'},
        {field:'purchaseCount',sortable:true,title:'采购数量',align:'center',width:'10%'},
        {field:'dianshouCount',sortable:true,title:'点收数量',align:'center',width:'10%'},
      ]]
    });
  }
  function confirm(){
    var rows = $('#table').datagrid('getSelections');
    if(rows.length == 0){
      $.messager.alert("操作提示","请选择明细信息","warning");
      return false;
    }else{
      window.parent.frames["mainFrame"].checkReceiptGoods(rows);
      top.$("#dialog").dialog("close");
    }
  }
  function clearForm(){
    $('#query').form('clear');
  }
</script>
</body>
</html>
