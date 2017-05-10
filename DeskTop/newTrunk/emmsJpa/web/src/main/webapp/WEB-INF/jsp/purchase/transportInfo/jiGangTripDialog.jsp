<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/4/26
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <title>编辑船名航次</title>
</head>
<body>
    <div class="easyui-panel" title="首页->采购管理->集港信息管理->集港信息列表->编辑船名航次" data-options="fit:true,border:false">
      <form id="query" method="post">
        <div style="padding:10px">
          <input class="easyui-textbox" id="transNo" name="transNo" style="width:25%"
                 data-options="label:'运输单编号:'">
          <input class="easyui-textbox" id="shipName" name="shipName" style="width:25%"
                 data-options="label:'船名:'">
          <input class="easyui-textbox" id="oceanVessel" name="oceanVessel" style="width:25%"
                 data-options="label:'航次:'">
        </div>
        <div style="text-align:center">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="save()">保存</a>
        </div>
      </form>
    </div>
    <script type="text/javascript">
      function save(){
        var shipName =$("#shipName").val();
        var oceanVessel =$("#oceanVessel").val();
        var ship={
          "shipName":shipName,
          "oceanVessel":oceanVessel
        }
        var isValid = $("#query").form("validate");
        if(isValid){
          $.ajax({
            type: 'POST',
            url: "${emms}/purchase/transportInfo.do?cmd=saveShip",
            data: JSON.stringify(ship),
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function () {
              $.messager.alert("操作提示","保存成功","info",function(){
                <%--window.location = "${emms}/purchase/transportInfo.do?cmd=query";--%>
                window.close();
              });
            },
            error:function(){
              $.messager.alert("操作提示", "保存失败","error");
            }
          });
        }
      }
    </script>
</body>
</html>
