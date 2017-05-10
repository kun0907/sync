<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/4/25
  Time: 11:54
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
    <div class="easyui-panel" title="首页->采购管理->运输单信息管理->运输单编辑" data-options="fit:true,border:false">
      <form id="query" method="post">
          <input type="hidden" id="transportInfoId" name="transportInfoId" value="${transportInfoId}"/>
        <div style="padding:10px">
          <input class="easyui-textbox" id="transNo" name="transNo" style="width:30%"
                 data-options="label:'运输单编号:'">
          <input class="easyui-textbox" id="jiSite" name="jiSite" style="width:30%"
                 data-options="label:'集港地点:'">
          <input class="easyui-datebox date_field" id="jiTime" name="jiTime" style="width:30%"
                 data-options="label:'集港时间:',editable:false">
        </div>
        <div style="padding:10px">
          <input class="easyui-textbox" id="shipName" name="shipName" style="width:30%"
                 data-options="label:'船名:'">
          <input class="easyui-textbox" id="oceanVessel" name="oceanVessel" style="width:30%"
                 data-options="label:'航次:'">
        </div>
        <div style="text-align:center">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="outPort()">发运离港</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="inPort()">到港通关</a>
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="donePort()">通关完成</a>
        </div>

        <div style="padding:10px;width: 100%;height: 30%" id="out">
            <input class="easyui-datebox date_field" id="expectDepartureDate" name="expectDepartureDate" style="width:30%"
                   data-options="label:'预计离港时间:',editable:false">
            <input class="easyui-datebox date_field" id="actualDepartureDate" name="actualDepartureDate" style="width:30%"
                   data-options="label:'实际离港时间:',editable:false">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="save()">保存</a>
        </div>

          <div style="padding:10px;width: 100%;height: 30%" id="in">
              <input class="easyui-datebox date_field" id="expectArrivalDate" name="expectArrivalDate" style="width:30%"
                     data-options="label:'预计到港时间:',editable:false">
              <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="save()">保存</a>
          </div>

          <div style="padding:10px;width: 100%;height: 30%" id="done">
              <input class="easyui-datebox date_field" id="actualArrivalDate" name="actualArrivalDate" style="width:30%"
                     data-options="label:'实计到港时间:',editable:false">
              <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="save()">保存</a>
          </div>
      </form>
      <%--<table id="table" auto-resize="true" class="easyui-datagrid" title="运输单列表">--%>
      <%--</table>--%>
    </div>

    <script type="text/javascript">
        var lastIndex;
        var state;
        var dictionary = {};
        var reasonList={};
        $("#out").hide();
        $("#in").hide();
        $("#done").hide();
        //校验选物资不可重复
        var checkPacking=[];
        $(function(){
            query();
        });
        function query(){
            $('#query').form('load', '${emms}/purchase/transportInfo.do?cmd=loadTransportInfoData&transportInfoId=${transportInfoId}');
            $('#query').form({
                onLoadSuccess:function(data) {
                    //从后台为datagrid加载数据
                    if(data.transportInfoDetails!=null && data.transportInfoDetails.length>0){
                        $("#table").edatagrid("loadData", loadFilter(data.transportInfoDetails));
                        for(var i=0;i<data.transportInfoDetails.length;i++){
                            checkPacking.push(data.transportInfoDetails[i].transportInfoDetailsId);
                        }
                    }else{
                        var transportInfoDetailsId=[];
                        $("#table").edatagrid("loadData", loadFilter(transportInfoDetailsId));
                    }
                }
            });
        }



        //以下getValueByKey和$.ajax的方法是解决在datagrid里的combox字段，在选择数据后，关闭编辑时显示英文，下面代码可以解决这个问题。在datagrid
        //d formatter里调用getValueByKey（还需设置两个全局变量来缓存数据字典里的数据）
        function getValueByKey(key){
            return dictionary[key];
        }

        $.ajax({
            url:'${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=appCode',
            dataType : 'json',
            type : 'POST',
            async:false,
            success: function (data){
                for(var i=0;i<data.length;i++){
                    var obj=data[i].dictionaryCode;
                    dictionary[obj]=data[i].dictionaryName;
                }
                reasonList = data;
            }
        });

        $.ajax({
            url:'${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=recheckCode',
            dataType : 'json',
            type : 'POST',
            async:false,
            success: function (data){
                for(var i=0;i<data.length;i++){
                    var obj=data[i].dictionaryCode;
                    dictionary[obj]=data[i].dictionaryName;
                }
                reasonList = data;
            }
        });

        function loadFilter(data) {//重新组织datagrid数据，把符合条件的内容加到定义的json字符串中。
            var value = {
                total: data.length,
                rows: data
            };
            return value;
        }

        function outPort(){
            $("#out").show();
            state = "HaiYunZT";
        }
        function inPort(){
            $("#in").show();
            state = "DaoGangTGZ";
        }

        function donePort(){
            $("#done").show();
            state = "TongGuanWC";
        }

        function save(){
            var transportInfoId = $("#transportInfoId").val();
            var expectDepartureDate = $("#expectDepartureDate").val();
            var actualDepartureDate = $("#actualDepartureDate").val();
            var expectArrivalDate = $("#expectArrivalDate").val();
            var actualArrivalDate =$("#actualArrivalDate").val();
            var date = {
                "transportInfoId":transportInfoId,
                "inspectStaus":state,
                "expectDepartureDate":expectDepartureDate,
                "actualDepartureDate":actualDepartureDate,
                "expectArrivalDate":expectArrivalDate,
                "actualArrivalDate":actualArrivalDate
            }
            $.ajax({
                type: 'POST',
                url: "${emms}/purchase/transportInfo.do?cmd=saveDate",
                data: JSON.stringify(date),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function () {
                    $.messager.alert("操作提示","保存成功","info",function(){
                        window.location = "${emms}/purchase/transportInfo.do?cmd=query";
                    });
                },
                error:function(data){
                    $.messager.alert("操作提示", "保存失败","error");
                }
            });
        }
    </script>
</body>
</html>
