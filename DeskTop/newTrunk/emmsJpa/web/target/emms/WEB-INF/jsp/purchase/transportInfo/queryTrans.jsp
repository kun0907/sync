<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/4/24
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <title>运输单信息</title>
</head>
<body>
  <div class="easyui-panel" title="首页->采购管理->运输单信息管理->运输单信息列表" data-options="fit:true,border:false">
    <form id="query" method="post">
      <div style="padding:10px">
        <input class="easyui-textbox" id="transNo" name="transNo" style="width:30%"
               data-options="label:'运输单编号:'">
        <input class="easyui-textbox" id="jiSite" name="jiSite" style="width:30%"
               data-options="label:'集港地点:'">
        <input class="easyui-datebox date_field" id="jiTime" name="jiTime" style="width:30%"
               data-options="label:'集港时间:',editable:false">
      </div>
      <div style="padding:10px">
        <input class="easyui-combobox" id="inspectStaus" name="inspectStaus" style="width:30%"
               data-options="label:'单据状态:'">
        <input class="easyui-textbox" id="createUserName" name="createUserName" style="width:30%"
               data-options="label:'创建人:'">
        <input class="easyui-datebox date_field" id="createTime" name="createTime" style="width:30%"
               data-options="label:'创建时间:',editable:false">
      </div>
      <div style="text-align:center">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
        <%--<a href="${emms}/instorage/materialManag.do?cmd=modal" iconCls='icon-add' class="easyui-linkbutton">新建</a>--%>
      </div>
    </form>
    <table id="table" auto-resize="true" class="easyui-datagrid" title="运输单列表">
    </table>
  </div>
  <script type="text/javascript">
    var checkPacking=[];//校验选物资不可重复

    $(function(){
      query();
    });

    function query(){
      $("#table").datagrid({
        url: '${emms}/purchase/transportInfo.do?cmd=loadTransportInfoListData',
        method: 'POST',
        pagination: true,
        fitColumns: true,
        rownumbers: true,
        showFooter: true,
        singleSelect: false,
        queryParams:{

        },
        columns:[[
          {
            field: 'transportNo', sortable: true, title: '运输单编号', align: 'center', width: '20%',
            formatter: function (value, row, index) {
              return '<a class="easyui-linkbutton" style="color:blue" href="${emms}/purchase/transportInfo.do?cmd=dialogTransportInfo&transportInfoId=' + row.transportInfoId + '" target="_self">' + row.transportNo + '</a>';
            }
          },
          {field: 'jigangSite', sortable: true, title: '集港地点', align: 'center', width: '12%'},
          {field: 'jigangTime', sortable: true, title: '集港时间', align: 'center', width: '12%'},
          {field: 'createUserName', sortable: true, title: '创建人', align: 'center', width: '10%'},
          {field: 'createTime', sortable: true, title: '创建时间', align: 'center', width: '12%'},
          {field: 'inspectStaus', sortable: true, title: '单据状态', align: 'center', width: '10%',
           formatter:function(value, row){
              if(row.inspectStaus == 'JiGangPZ'){
                    return '海运配载';
              }
             if(row.inspectStaus == 'HaiYunZT'){
                    return '海运在途';
             }
             if(row.inspectStaus == 'DaoGangTGZ'){
               return '到港通关中';
             }
             if(row.inspectStaus == 'TongGuanWC'){
               return '通关完成';
             }
           }
          },
          {
            field: 'operate', title: '操作', sortable: true, align: 'center', width: '15%',
            formatter: function (value, row) {
              show = "";
              if (row.inspectStaus == 'JiGangPZ') {
                show += "<a class='easyui-linkbutton' href='${emms}/purchase/transportInfo.do?cmd=edit&inspectStaus=JiGangPZ&transportInfoId="
                + row.transportInfoId
                + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
                show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\"" + row.transportInfoId + "\")'"
                + " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
              }
              if (row.inspectStaus == 'HaiYunZT') {
                show += "<a class='easyui-linkbutton' href='${emms}/purchase/transportInfo.do?cmd=edit&inspectStaus=HaiYunZT&transportInfoId="
                + row.transportInfoId
                + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
              }
              if (row.inspectStaus == 'DaoGangTGZ') {
                show += "<a class='easyui-linkbutton' href='${emms}/purchase/transportInfo.do?cmd=edit&inspectStaus=DaoGangTGZ&transportInfoId="
                + row.transportInfoId
                + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
              }
              if (row.inspectStaus == 'TongGuanWC') {
                show += "<a class='easyui-linkbutton'  href='${emms}/purchase/transportInfo.do?cmd=dialogTransportInfo&inspectStaus=TongGuanWC&transportInfoId="
                + row.transportInfoId
                + "' target='_self'>查看</a>&nbsp;&nbsp;&nbsp;";
              }
              return show;
            }
          }
        ]]
      });
    }

    $('#inspectStaus').combobox({//获取业务字典数据
      url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=TransportInfoStaus',
      valueField: 'dictionaryCode',
      textField: 'dictionaryName',
      multiple: false
    });

    function clearForm() {
      $('#query').form('clear');
    }


    function ajaxDelete(id) {
      $.messager.confirm('确认对话框', '确定要删除当前记录吗？', function (result) {
        if (result) {
          $.ajax({
            type: "POST",
            url: "${emms}/purchase/transportInfo.do?cmd=delete&transportInfoId=" + id,
            async: false,
            success: function () {
              $.messager.alert("操作提示", "删除成功！", "info", function () {
                window.location = "${emms}/purchase/transportInfo.do?cmd=query";
              });
            }
          });
        }
        return false;
      });
    }

  </script>
</body>
</html>
