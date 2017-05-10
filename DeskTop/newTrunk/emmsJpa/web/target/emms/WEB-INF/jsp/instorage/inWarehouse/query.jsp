<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/13
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>列表页</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->实物入库管理->实物入库管理->查询" data-options="fit:true,border:false">
  <form id="query" method="post">
    <div style="padding:10px">
      <input class="easyui-textbox" id="inWarehouseCode" style="width:30%" data-options="label:'入库单编号:'">
      <input class="easyui-textbox" id="supplierName" style="width:30%" data-options="label:'供应商:'">
      <select class="easyui-combobox" editable="false" id="inWarehouseState"  style="width:30%" data-options="label:'单据状态:'"></select>
    </div>
    <div style="padding:10px">
      <input class="easyui-textbox" id="createUserName" style="width:30%" data-options="label:'创建人:'">
      <input class="easyui-datebox" editable="false" id="createStartTime" style="width:19%" data-options="label:'创建日期:'">-
      <input class="easyui-datebox" editable="false" id="createEndTime"  style="width:11%" >
      <input class="easyui-datebox" editable="false" id="inWarehouseStartTime" style="width:19%" data-options="label:'入库日期:'">-
      <input class="easyui-datebox" editable="false" id="inWarehouseEndTime" style="width:11%">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
      <a href="${emms}/instorage/inWarehouse.do?cmd=edit" iconCls='icon-add'
         class="easyui-linkbutton">新建</a>
    </div>
  </form>
  <table id="table" auto-resize="true" class="easyui-datagrid" title="入库单列表">
  </table>
</div>
<script type="text/javascript">
  $('#inWarehouseState').combobox({
    url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=inWarehouseState',
    valueField: 'dictionaryCode',
    textField: 'dictionaryName',
    multiple:false
  });
  $(function(){
    query();
  });
  function query(){
    $('#table').datagrid({
      url:'${emms}/instorage/inWarehouse.do?cmd=loadInWarehouseListData',
      method: 'POST',
      pagination: true,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:false,
      queryParams: {
        "inWarehouseCode" : $("#inWarehouseCode").val(),
        "supplierName" : $("#supplierName").val(),
        "inWarehouseState" : $("#inWarehouseState").combobox('getValue'),
        "createUserName" : $("#createUserName").val(),
        "createStartTime" : $('#createStartTime').datebox('getValue'),
        "createEndTime" : $('#createEndTime').datebox('getValue'),
        "inWarehouseStartTime":$("#inWarehouseStartTime").datebox('getValue'),
        "inWarehouseEndTime":$("#inWarehouseEndTime").datebox('getValue')
      },
      columns:[[
        {field:'inWarehouseCode',sortable:true,title:'入库单编号',align:'center',width:'15%',
          formatter: function(value,row,index){
          return '<a class="easyui-linkbutton" style="color:blue" href="${emms}/instorage/inWarehouse.do?cmd=view&inWarehouseId='+row.inWarehouseId+'" target="_self">'+row.inWarehouseCode+'</a>';
        }},
        {field:'orderId',hidden:true},
//        {field:'orderCode',sortable:true,title:'采购单编号',align:'center',width:'15%'},
        {field:'supplierName',sortable:true,title:'供应商名称',align:'center',width:'15%'},
        {field:'createUserName',sortable:true,title:'创建人',align:'center',width:'15%'},
        {field:'createTime',sortable:true,title:'创建时间',align:'center',width:'15%'},
        {field:'inWarehouseTime',sortable:true,title:'入库时间',align:'center',width:'15%'},
        {field:'inWarehouseState',sortable:true,title:'入库状态',align:'center',width:'15%'},
        {field:'aaa',title:'操作',sortable:true,align:'center',width:'11%',
          formatter: function(value,row,index){
            show ="";
            if(row.inWarehouseState=='入库未完成'){
              show += "<a class='easyui-linkbutton' href='${emms}/instorage/inWarehouse.do?cmd=edit&inWarehouseId="
              + row.inWarehouseId
              + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
              show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\""+row.inWarehouseId+"\")'"
              + " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
              show += "<a class='easyui-linkbutton' onclick='ajaxCommit(\""+row.inWarehouseId+"\")'"
              + " target='_self'>入库</a>&nbsp;&nbsp;&nbsp;";
            }

            return show;
          }}
      ]]
    });
  }
  function clearForm() {
    $('#query').form('clear');
  }

  function ajaxDelete(id) {
    $.messager.confirm("操作提示", "确定要删除当前记录吗？", function (data) {
      if(data){
      $.ajax({
        type: "POST",
        url: "${emms}/instorage/inWarehouse.do?cmd=delete&inWarehouseId=" + id,
        async: false,
        success: function (data) {
          if(data=='删除完成'){
            $.messager.alert("操作提示","删除成功","info",function(){
              window.location = "${emms}/instorage/inWarehouse.do?cmd=query";
            });
          }
          else{
            $.messager.alert("操作提示",data,"warning");
          }

        }
      });
      }
    });
  }

  function ajaxCommit(id) {
    var state="yiruku";
    $.messager.confirm("操作提示", "确定要对当前记录进行入库处理吗？", function (data) {
      if(data){
      $.ajax({
        type: "POST",
        url:"${emms}/instorage/inWarehouse.do?cmd=updateInWarehouseState&inWarehouseId="+id+"&state="+state,
        async: false,
        success: function(data) {
          console.log(data);
          $.messager.alert("操作提示","入库成功","info",function(){
            window.location = "${emms}/instorage/inWarehouse.do?cmd=query";
          });
        },
        error: function (data) {
          $.messager.alert("操作提示",data.responseText,"warning");
        }
      });
      }
    });
  }

  </script>
</body>
</html>
