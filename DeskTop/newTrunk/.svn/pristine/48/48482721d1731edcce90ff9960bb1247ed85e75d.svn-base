<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
  <title>编辑页</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel"  data-options="fit:true,border:false">
  <form id="ff" method="post">
    <input class="easyui-textbox" type="hidden" id="orderId" name="orderId"/>
    <div style="padding:10px" >
      <input class="easyui-textbox" id="orderOpinion" name="orderOpinion"  style="width:100%" data-options="label:'意见:',multiline:true,required:true">
    </div>
    <div style="text-align: center;">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
      <a href="${emms}/purchase/order.do?cmd=query"iconCls='icon-back' class="easyui-linkbutton">返回</a>
    </div>
  </form>
</div>
<script type="text/javascript">
  $(function(){
    $('#ff').form('load', '${emms}/purchase/order.do?cmd=loadOrderData&orderId=${orderId}');
    $('#ff').form({
      onLoadSuccess:function(data){
      }
    });
  });
  function ajaxSubmitForm() {
    $("#ff").form("submit", {
      url: "${emms}/purchase/order.do?cmd=updateOrderStateOpinion",
      onsubmit: function () {
        return $(this).form("validate");
      },
      success: function (result) {
        console.log(result);
        $.messager.alert("操作提示","保存成功","info",function(){
          window.location = "${emms}/purchase/order.do?cmd=query";
        });
      },
      error: function () {
        $.messager.alert("操作提示","保存失败","error");

      }
    });
  }
</script>
</body>
</html>
