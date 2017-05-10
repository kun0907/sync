<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/28
  Time: 14:16
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
<div class="easyui-panel" data-options="fit:true,border:false">
  <form id="query" method="post">
    <div style="padding:10px">
      <input class="easyui-textbox" id="materialsCode"   style="width:25%" data-options="label:'物资编码:'">
      <input class="easyui-textbox" id="materialsDescribe"  style="width:25%" data-options="label:'物资描述:'">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="confirm()">保存</a>
    </div>

  <table id="table" class="easyui-datagrid" title="物资列表"  style="height:350px">
  </table>
    </form>
</div>

<script type="text/javascript">
  $(function(){
    query();
  })

  function query(){
    $('#table').datagrid({
      url:'${emms}/baseinfo/materials.do?cmd=loadMaterialListData',
      method: 'POST',
      pagination: true,
      fitColumns: true,
      rownumbers: true,
      showFooter: true,
      selectOnCheck:true,
      checkOnSelect:true,
      queryParams: {
        "materialsCode" : $("#materialsCode").val(),
        "materialsDescribe" : $("#materialsDescribe").val()

      },
      onLoadSuccess: function(){
        $(this).datagrid('freezeRow',-1).datagrid('freezeRow',-1);
      },
      columns:[[
        {field:'materialsId',checkbox:true},
        {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'20%'},
        {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'20%'},
        {field:'additional1',sortable:true,title:'附加1',align:'center',width:'10%'},
        {field:'additional2',sortable:true,title:'附加2',align:'center',width:'10%'},
        {field:'additional3',sortable:true,title:'附加3',align:'center',width:'10%'},
        {field:'additional4',sortable:true,title:'附加4',align:'center',width:'10%'},
        {field:'materialsUnitMain',sortable:true,title:'主计量单位',align:'center',width:'20%'}
      ]]
    });
  }
  function clearForm(){
    $('#query').form('clear');
  }

  function confirm(){
    var rows = $('#table').datagrid('getSelections');
    if(rows.length == 0){
      $.messager.alert("操作提示","请选择物料信息","warning");
      return false;
    }else{
      window.parent.frames["mainFrame"].checkMaterials(rows);
      top.$("#dialog").dialog("close");
    }
  }
  <%--function ajaxSubmitForm() {--%>
    <%--$("#query").form("submit", {--%>
      <%--url: "${emms}/purchase/agreement.do?cmd=materialsEdit",--%>
      <%--onsubmit: function () {--%>
        <%--return $(this).form("validate");--%>
      <%--},--%>
      <%--success: function (result) {--%>
        <%--alert(result);--%>
        <%--if(result=='保存成功'){--%>
          <%--window.location = "${emms}/purchase/agreement.do?cmd=queryAgreementDetail";--%>
        <%--}--%>
        <%--else {--%>
          <%--alert(result);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

</script>

</body>
</html>
