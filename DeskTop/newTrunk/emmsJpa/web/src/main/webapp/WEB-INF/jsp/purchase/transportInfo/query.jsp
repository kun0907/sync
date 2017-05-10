<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/4/24
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <title>集港信息</title>
</head>
<body>
<div class="easyui-panel" title="首页->采购管理->集港信息管理->集港信息列表" data-options="fit:true,border:false">
  <form id="query" method="post">
      <input type="hidden" id="deliveryId" name="deliveryId" value="${deliveryId}"/>
    <div style="padding:10px">
      <input class="easyui-textbox" id="deliveryNo" name="deliveryNo" style="width:30%"
             data-options="label:'发货单编号:'">
      <input class="easyui-combobox" id="supplierId" name="supplierId" style="width:30%"
             data-options="label:'供应商名称:'">
    </div>
    <div style="padding:10px">
      <input class="easyui-textbox" id="createUserName" name="createUserName" style="width:30%"
             data-options="label:'创建人:'">
      <input class="easyui-datebox date_field" id="createStartTime" name="createStartTime" style="width:16%"
             data-options="label:'创建时间:',editable:false">～
      <input class="easyui-datebox date_field" id="createEndTime" name="createEndTime" style="width:12%" data-options="editable:false">
    </div>
    <div style="padding:10px">
      <input class="easyui-combobox" id="inspectStaus" name="inspectStaus" style="width:30%"
             data-options="label:'单据状态:'">
      <input class="easyui-textbox" id="jigang" name="jigang" style="width:30%"
             data-options="label:'集港地点:'">
    </div>
    <div style="text-align:center">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
      <%--<a href="${emms}/instorage/materialManag.do?cmd=modal" iconCls='icon-add' class="easyui-linkbutton">新建</a>--%>
    </div>
  </form>
    <a href="javascript:void(0)" iconCls='icon-add' class="easyui-linkbutton" onclick="dialog()">添加明细</a>
    <a href="javascript:void(0)" iconCls='icon-remove' class="easyui-linkbutton" onclick="delMaterials()">删除明细</a>
    <a href="javascript:void(0)" iconCls='' class="easyui-linkbutton" onclick="addDialog()">集港</a>
    <a href="javascript:void(0)" iconCls='' class="easyui-linkbutton" onclick="addDialogs()">海运配载</a>
  <table id="table" auto-resize="true" class="easyui-datagrid" title="集港信息列表">
  </table>
 </div>
 <script type="text/javascript">
     //校验选物资不可重复
     var checkPacking=[];
     $(function(){
         query();
     });

     function query(){
         $("#table").datagrid({
             url: '${emms}/purchase/transportInfo.do?cmd=loadJigangInfoListData',
             method: 'POST',
             pagination: true,
             fitColumns: true,
             rownumbers: true,
             showFooter: true,
             singleSelect: false,
             queryParams:{

             },
             columns:[[
                 {field:'deliveryId',hidden:true},
                 {
                     field: 'inspectNo', sortable: true, title: '发货单编号', align: 'center', width: '20%',
                     formatter: function (value, row, index) {
                         return '<a class="easyui-linkbutton" style="color:blue" href="${emms}/purchase/transportInfo.do?cmd=modal&jigangInfoId=' + row.jigangInfoId + '" target="_self">' + row.inspectNo + '</a>';
                     }
                 },
                 {field: 'createUserName', sortable: true, title: '创建人', align: 'center', width: '15%'},
                 {field: 'createTime', sortable: true, title: '创建时间', align: 'center', width: '15%'},
                 {field: 'inspectStaus', sortable: true, title: '单据状态', align: 'center', width: '15%'},
                 {field: 'jigangSite', sortable: true, title: '集港地点', align: 'center', width: '15%'},
             ]]
         });
     }

     $('#supplierId').combobox({
         url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
         valueField: 'orgId',
         textField: 'orgName',
         multiple:false
     });

     function dialog(){
         var code = $("#deliveryNo").val();
         var supplier = $("#supplierId").combobox("getValue");

         if(!supplier){
             alert("请选择供应商");
             return false;
         }
         var flag=true;
         if(code){
             $.ajax({
                 type: "POST",
                 url:"${emms}/purchase/supplierDelivery.do?cmd=checkCode&code="+code,
                 async: false,
                 contentType: "application/json;charset=utf-8",
                 success: function(data) {
                     if(data){
                         $.messager.alert("操作提示","发货单不存在","warning");
                         flag=false;
                     }
                 }
             });
         }
         if(flag){
             top.$('#dialog').dialog({
                 title: '供应商发货弹出框',
                 width: 900,
                 height: 540,
                 closed: false,
                 cache: true,
                 href: '${emms}/purchase/delivery/package.do?cmd=modal&supplierId='+supplier+'&deliveryNo='+code
             });
         }
     }

     //删除添加明细方法
     function delMaterials(){
         var rows = $('#table').datagrid("getSelections");
         for (var i = rows.length - 1; i >= 0; i--) {
             var index = $('#table').datagrid('getRowIndex',rows[i]);
             $('#table').datagrid('deleteRow', index);

             //将存储的收货单ID清除
             for(var z=0;z<checkPacking.length;){
                 if(checkPacking[index]==checkPacking[z]){
                     checkPacking.splice(z,1)
                 }else{
                     z++;
                 }
             }
         }
     }

     function addDialog(){
         var deliveryId =$("#deliveryId").val();
         top.$('#dialog').dialog({
             title: '编辑时间地点',
             width: 1000,
             height: 540,
             closed: false,
             cache: true,
             href: '${emms}/purchase/transportInfo.do?cmd=dialogJiGangTime&deliveryId='+deliveryId
         });
     }

     function addDialogs(){
         var deliveryId =$("#deliveryId").val();
         top.$('#dialog').dialog({
             title: '编辑时间地点',
             width: 1000,
             height: 540,
             closed: false,
             cache: true,
             href: '${emms}/purchase/transportInfo.do?cmd=dialogJiGangShip&deliveryId='+deliveryId
         });
     }
 </script>
</body>
</html>
