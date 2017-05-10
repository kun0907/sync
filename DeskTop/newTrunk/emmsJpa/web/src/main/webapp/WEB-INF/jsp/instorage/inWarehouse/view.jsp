<%--
  Created by IntelliJ IDEA.
  User: WANGYING
  Date: 2017/3/29
  Time: 10:01
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
<div class="easyui-panel" title="首页->实物入库管理->实物入库管理->编辑" data-options="fit:true,border:false">
  <form id="edit" method="post">
    <input type="hidden" id="inWarehouseId" name="inWarehouseId" value="${inWarehouseId}" />
    <div style="padding:10px">
      <input class="easyui-textbox" id="inWarehouseCode" name="inWarehouseCode" style="width:30%" data-options="readonly:true,label:'入库单编号:'">
      <input class="easyui-textbox" id="supplierName" name="supplierName" style="width:30%" data-options="readonly:true,label:'供应商:'">
      <input class="easyui-textbox" id="createUserName" name="createUserName" style="width:30%" data-options="readonly:true,label:'创建人:'">
    </div>
    <div style="padding:10px">

      <input class="easyui-datebox" id="inWarehouseTime" name="inWarehouseTime" style="width:30%" data-options="readonly:true,label:'入库日期:'">
      <input class="easyui-textbox" id="inWorker" name="inWorker" style="width:30% " data-options="readonly:true,label:'入库人员:'">
    </div>
  </form>
  <table id="table" auto-resize="true" class="easyui-datagrid" title="质检明细">
  </table>
  <div style="text-align: center;">
    <a href="${emms}/instorage/inWarehouse.do?cmd=query" iconCls='icon-back' class="easyui-linkbutton" >返回</a>
  </div>

</div>
<script type="text/javascript">

  $('#inWarehouseState').combobox({
    url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=inWarehouseState',
    valueField: 'dictionaryCode',
    textField: 'dictionaryName',
    multiple:false
  });


  $('#supplierName').combobox({
    url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
    valueField: 'orgName',
    textField: 'orgName',
    multiple:false
  });

  $(function(){
    $('#edit').form('load', '${emms}/instorage/inWarehouse.do?cmd=loadInWarehouseData&inWarehouseId=${inWarehouseId}');
    $('#edit').form({
      onLoadSuccess: function (data) {
        if(data.inWarehouseDetailList.length>0){
          $("#table").datagrid("loadData", loadFilter(data.inWarehouseDetailList));
        }else{
          var inWarehouseDetailList=[];
          $("#table").datagrid("loadData", loadFilter(inWarehouseDetailList));
        }
      }
    })





    $('#table').datagrid({
      pagination: false,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:true,
      idField:'inWarehouseId',
      columns:[[
        {field:'zhijianId',hidden:true},
        {field:'zhijianCode',sortable:true,title:'质检单编号',align:'center',width:'15%'},
        {field:'materialsId',hidden:true},
        {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'10%'},
        {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
        {field:'additional1',sortable:true,title:'附加1',align:'center',width:'10%'},
        {field:'additional2',sortable:true,title:'附加2',align:'center',width:'10%'},
        {field:'additional3',sortable:true,title:'附加3',align:'center',width:'10%'},
        {field:'additional4',sortable:true,title:'附加4',align:'center',width:'10%'},
        {field:'wbsId',hidden:true},
        {field:'wbsCode',sortable:true,title:'工程（WBS）编码',align:'center',width:'12%'},
        {field:'docSourceDetailId',hidden:true},
        {field:'materialsUnitMain',sortable:true,title:'采购计量单位',align:'center',width:'10%'},
        {field:'productionDate',sortable:true,title:'生产日期',align:'center',width:'10%'},
        {field:'bzq',sortable:true,title:'保质期',align:'center',width:'10%'},
        {field:'orderId',hidden:true},
        {field:'orderCode',hidden:true},
        {field:'purchaseCount',sortable:true,title:'采购数量',align:'center',width:'10%'},
        {field:'deliveryCount',sortable:true,title:'已发货数量',align:'center',width:'10%'},
        {field:'dianshouCount',sortable:true,title:'点收数量',align:'center',width:'10%'},
        {field:'qualifiedCount',sortable:true,title:'合格数量',align:'center',width:'10%'},
        {field:'unqualifiedCount',sortable:true,title:'不合格数量',align:'center',width:'10%'},
        {field:'visualInspection',sortable:true,title:'外观检查',align:'center',width:'10%', formatter: function(value,row,index){
          if(row.appearanceInspect =="y"){
            return "是";
          }else {
            return "否";
          }
        }},
        {field:'review',sortable:true,title:'需要复检',align:'center',width:'10%' ,formatter: function(value,row,index){
          if(row.appearanceInspect =="y"){
            return "是";
          }else {
            return "否";
          }
        }},
        {field: 'inWarehouseCount', sortable: true, title: '本次入库数量', align: 'center', width: '10%'},
        {field: 'alreadyCount', sortable: true, title: '已入库数量', align: 'center', width: '10%'},
        {field:'storagelocationCode',sortable:true,title:'储位',align:'center',width:'15%'},
        {field:'storagelocationId',hidden:true},
        {field:'warehouseId',hidden:true},
        {field:'reservoirareaId',hidden:true}
      ]]
    });
  });




  function loadFilter(data) {//重新组织datagrid数据，把符合条件的内容加到定义的json字符串中。
    var value = {
      total: data.length,
      rows: data
    };
    return value;
  }






</script>

</body>
</html>
