<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/3/7
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >

<html>
<head>
  <title>新建质检单</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="" data-options="fit:true,border:false">
  <form id="formT" method="post" enctype="multipart/form-data">
    <input type="hidden" id="materiaInspectId" name="materiaInspectId"/>
    <div style="margin:20px;text-align:-moz-left">
      <input class="easyui-textbox" id="inspectNo" name="inspectNo" style="width:30%" data-options="label:'质检单编号:',validType:'length[0,50]'">
      <input id="supplierId" type="hidden" value="${supplierId}">
    </div>
      <div style="text-align: center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
      </div>
  </form>
  <div>
    <table id="table" auto-resize="true" class="easyui-datagrid" title="质检单列表" style="height:360px">
    </table>
  </div>
  </form>
  <form method="post" style="display: none;" id="fileFrom"></form>
</div>
<script type="text/javascript">
  $(function(){
    query();
  });

  function query(){
    var e_materiaInspectNo = $('#inspectNo').val();
    $('#table').datagrid({
      url:'${emms}/instorage/materialManag.do?cmd=loadMaterialDetailListData&materiaInspectNo='+e_materiaInspectNo+'',
      method: 'POST',
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:false,
      queryParams: {
        "inspectNo" : $("#inspectNo").val(),
        "supplierId" : $("#supplierId").val()
      },
      onLoadSuccess: function(){
        $(this).datagrid('freezeRow',-1).datagrid('freezeRow',-1);
      },
      columns:[[
        {field:'qualityInspectId',checkbox:true},
        {field:'materiaInspectId',hidden:true},
        {field:'inspectNo',sortable:true,title:'质检单编号',align:'center',width:'10%'},
        {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'10%'},
        {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
        {field:'additional1',sortable:true,title:'附加1',align:'center',width:'10%'},
        {field:'additional2',sortable:true,title:'附加2',align:'center',width:'10%'},
        {field:'additional3',sortable:true,title:'附加3',align:'center',width:'10%'},
        {field:'additional4',sortable:true,title:'附加4',align:'center',width:'10%'},
        {field:'wbsCode',sortable:true,title:'工程（WBS）编码',align:'center',width:'14%'},
        {field:'materialsUnitMain',sortable:true,title:'采购计量单位',align:'center',width:'14%'},
        {field:'productDate',sortable:true,title:'生产日期',align:'center',width:'10%'},
        {field:'qualityDate',sortable:true,title:'保质期',align:'center',width:'10%'},
        {field:'purchaseCount',sortable:true,title:'采购数量',align:'center',width:'10%'},
        {field:'deliveryQty',sortable:true,title:'已发货数量',align:'center',width:'10%'},
        {field:'currentDeliveryQty',sortable:true,title:'本次发货数量',align:'center',width:'12%'},
        {field:'dianshouCount',sortable:true,title:'点收数量',align:'center',width:'10%'},
        {field:'qualifiedQty',sortable:true,title:'合格数量',align:'center',width:'10%'},
        {field:'unQualifiedQty',sortable:true,title:'不合格数量',align:'center',width:'10%'},
        {field:'appearanceInspect',sortable:true,title:'外观检查',align:'center',width:'10%',
          formatter: function(value,row,index){
            if(row.appearanceInspect =="y"){
              return "是";
            }else {
              return "否";
            }
          }
        },
        {field: 'recheckInspect', sortable: true, title: '需要复检', align: 'center', width: '10%',
            formatter: function(value,row,index){
              if(row.recheckInspect =="y"){
                return "是";
              }else if(row.recheckInspect =="n"){
                return "否";
              }
            }
        }
      ]]
    });
  }

  function confirm(){
    var rows = $('#table').datagrid('getSelections');
    if(rows.length == 0){
      alert("请选择明细信息");
      return false;
    }else{
      window.parent.frames["mainFrame"].checkMaterialDetail(rows);
      top.$("#dialog").dialog("close");
    }
  }
  function clearForm(){
    $('#formT').form('clear');
  }

</script>
</body>
</html>
