<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
            <b>需用计划编号:</b>
            <input class="easyui-textbox" id="demandCode" style="width:22%" >
            <input class="easyui-textbox" type="hidden" id="constructionId" value="${constructionId}">
        </div>
        <div style="text-align: center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="confirm()">确认</a>
        </div>
    </form>
    <table id="table" class="easyui-datagrid" title="明细列表" style="height:360px">
    </table>
</div>
<script type="text/javascript">
    $(function () {
        query();
    });
    function query() {
        $('#table').datagrid({
            url: '${emms}/outstorage/demandPlan.do?cmd=loadDemandDetailListData&demandOrgId=${constructionId}',
            method: 'POST',
            pagination: false,
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
            selectOnCheck: true,
            checkOnSelect: true,
            queryParams: {
                "demandCode": $("#demandCode").val(),
                "supplierId": $("#supplierId").val()
            },
            onLoadSuccess: function () {
                $(this).datagrid('freezeRow', -1).datagrid('freezeRow', -1);
            },
            columns: [[
                {field: 'demandDetailId', checkbox: true},
                {field: 'demandId', hidden: true},
                {field: 'wbsId', hidden: true},
                {field: 'stockId', hidden: true},
                {field: 'materialsId', hidden: true},
                {field: 'demandCode', sortable: true, title: '需用计划编码', align: 'center', width: '10%'},
                {field: 'designCode', sortable: true, title: '设计院物资编码', align: 'center', width: '10%'},
                {field: 'designDescribe', sortable: true, title: '物资描述', align: 'center', width: '10%'},
                {field: 'extra1', sortable: true, title: '附加1', align: 'center', width: '10%'},
                {field: 'extra2', sortable: true, title: '附加2', align: 'center', width: '10%'},
                {field: 'extra3', sortable: true, title: '附加3', align: 'center', width: '10%'},
                {field: 'extra4', sortable: true, title: '附加4', align: 'center', width: '10%'},
                {field: 'designUnit', sortable: true, title: '计量单位', align: 'center', width: '10%'},
                {field: 'demandDate', sortable: true, title: '需用时间', align: 'center', width: '10%'},
                {field: 'demandCount', sortable: true, title: '需用数量', align: 'center', width: '10%'},
                {field: 'stockNum', sortable: true, title: '库存数量', align: 'center', width: '10%'},
                {field: 'usedCount', sortable: true, title: '已领用数量', align: 'center', width: '10%'},
            ]]
        });
    }
    function confirm() {
        var rows = $('#table').datagrid('getSelections');
        if (rows.length == 0) {
            $.messager.alert("操作提示","请选择明细信息","warning");
            return false;
        } else {
            window.parent.frames["mainFrame"].checkDemand(rows);
            top.$("#dialog").dialog("close");
        }
    }
    function clearForm() {
        $('#query').form('clear');
    }
</script>
</body>
</html>
