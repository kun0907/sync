<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <title>编辑页</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->采购管理->合同订单管理->合同订单编辑" data-options="fit:true,border:false">
  <form id="ff" method="post">
    <input type="hidden" id="orderId" name="orderId"/>
    <input type="hidden" id="orderDetailJson" name="orderDetailJson" value="${orderDetailJson}" />
    <div style="padding:10px" >
      <B>采购订单编号:</B>
      <input class="easyui-textbox" id="orderCode" name="orderCode" style="width:22%" data-options="readonly:true,required:true,validType:'length[0,50]'">
      <input class="easyui-textbox" id="orderContractNo" name="orderContractNo"  style="width:30%" data-options="label:'采购合同号:',readonly:true,required:true,validType:'length[0,50]'">
      <input class="easyui-combobox" id="supplierId" name="supplierId" style="width:30%" data-options="label:'供应商名称:',readonly:true,editable:false,required:true">
    </div>
    <div style="padding:10px">
      <B>订单生成方式:</B>
      <input  class="easyui-combobox" id="orderWay" name="orderWay" style="width:22%" data-options="readonly:true,editable:false,required:true">
      <input class="easyui-datebox" id="deliveryDate" name="deliveryDate"  style="width:30%" data-options="label:'交货时间:',editable:false,required:true">
      <input  class="easyui-combobox" id="dataToSources" name="dataToSources" style="width:30%" data-options="label:'数据来源:',readonly:true,editable:false,required:true">
    </div>
    <div style="text-left: center;width:90%">
      <a href="${emms}/purchase/order.do?cmd=query" iconCls='icon-back' class="easyui-linkbutton" >返回</a>
    </div>
  </form>
  <table id="tt" style="width:600px;height:200px" class="easyui-datagrid" title="物资列表">
    <thead>
    <tr>
      <th field="materialsCode" width="100" >物资编码</th>
      <th field="materialsDescribe" width="100" >物资描述</th>
      <th field="additional1" width="100" align="center" >附加1</th>
      <th field="additional2" width="100" align="center" >附加2</th>
      <th field="additional3" width="100" align="center" >附加3</th>
      <th field="additional4" width="100" align="center" >附加4</th>
      <th field="wbsCode" width="100" align="center"  >工程(WBS)编码</th>
      <th field="orderDetailUnit" width="100" align="center" >采购计量单位</th>
      <th field="orderDetailUnitPrice" width="100" align="center"  >采购单价</th>
      <th field="orderDetailCount" width="100" align="center">采购数量</th>
      <th field="orderDetailTotalPrice" width="100" align="center">总价</th>
    </tr>
    </thead>
  </table>
</div>
<script type="text/javascript">
  var orderWay="";
  $('#dataToSources').combobox({
    valueField: 'dictionaryCode',
    textField: 'dictionaryName',
    multiple:false
  });
  $(function(){
    $('#ff').form('load', '${emms}/purchase/order.do?cmd=loadOrderData&orderId=${orderId}');
    $('#ff').form({
      onLoadSuccess:function(data){
        orderWay=data.orderWay;
        if(data.orderDetailList.length>0){
          $("#tt").datagrid("loadData", loadFilter(data.orderDetailList));
        }
      }
    });
    $('#tt').edatagrid({
      pagination: false,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      singleSelect:true
    });

    $('#orderWay').combobox({
      url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=orderWay',
      valueField: 'dictionaryCode',
      textField: 'dictionaryName',
      multiple:false,
      onSelect: function(rec){
        if(orderWay !=""){
          url='${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode='+orderWay
          $("#dataToSources").combobox("reload",url);
          orderWay="";
        }else{
          $("#dataToSources").combobox("clear",true);
          url='${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode='+rec.dictionaryCode
          $("#dataToSources").combobox("reload",url);
        }
      }
    });
    $('#supplierId').combobox({
      url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
      valueField: 'orgId',
      textField: 'orgName',
      multiple:false
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
