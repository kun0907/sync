<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/4/26
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
    <title>编辑集港时间地点</title>
</head>
<body>
  <div class="easyui-panel" title="首页->采购管理->集港信息管理->集港信息列表->编辑时间地点" data-options="fit:true,border:false">
    <form id="query" method="post">
      <div style="padding:10px">
        <input class="easyui-textbox" id="jiGangSite" name="jiGangSite" style="width:30%"
               data-options="label:'集港地点:'">
        <input class="easyui-datebox" id="jiGangTime" name="jiGangTime" style="width:30%"
               data-options="label:'集港时间:'">
      </div>
      <div style="text-align:center">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="save()">保存</a>
      </div>
    </form>
  </div>
  <script type="text/javascript">
      function save(){
        var jiGangSite =$("#jiGangSite").val();
        var jiGangTime =$("#jiGangTime").val();
        var time={
          "jiGangSite":jiGangSite,
          "jiGangTime":jiGangTime
        }
        var isValid = $("#query").form("validate");
        if(isValid){
          $.ajax({
            type: 'POST',
            url: "${emms}/purchase/transportInfo.do?cmd=saveTime",
            data: JSON.stringify(time),
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success: function () {
              $.messager.alert("操作提示","保存成功","info",function(){
//                window.location.reload();
//                window.location.href = window.location.href;
                window.close();
                <%--window.location = "${emms}/purchase/transportInfo.do?cmd=query";--%>
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
