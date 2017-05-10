<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>管理</title>
  <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
  <link rel="stylesheet" href="${emms}/css/indexStyle.css">
</head>

<body style="width:958px; height:450px;">
<table id="test" title="设备部件表" class="easyui-treegrid"
       url="${emms}/baseinfo/designCode.do?cmd=loadEquipmentTree&designId=${designId}"
       rownumbers="true"
       idField="designMId" treeField="designEId">
  <thead>
  <tr>
    <th field="designEId" width="180" align="center">物资编码</th>
    <th field="designType" width="150" align="center">类型</th>
    <th field="wbsId" width="180" align="center">WBS</th>
    <th field="equipmentNo" width="180" align="center">位号</th>
    <th field="attachmentNumber" width="150" align="center">配件数量</th>
  </tr>
  </thead>
</table>
<script type="text/javascript">
</script>
</body>
</html>
