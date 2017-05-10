<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
        <title>权限管理</title>
        <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    </head>
    <body>
        <div class="easyui-panel" title="首页->系统管理->权限管理->查询" data-options="fit:true,border:false">
            <a href="${emms}/system/resource.do?cmd=editResourcePage&parentId=${parentId}" iconCls='icon-add' class="easyui-linkbutton">新建</a>
            <table id="table" auto-resize="true" class="easyui-datagrid" title="权限列表" width="100%"></table>
        </div>
    <script type="text/javascript">
        $(function(){
            query();
        });
        function query(){
            $('#table').datagrid({
                url:'${emms}/system/resource.do?cmd=loadResourceListData',
                method: 'POST',
                pagination: true,
                fitColumns: true,
                rownumbers: true,
                showFooter: true,
                singleSelect:true,
                queryParams: {
                    "parentId" : '${parentId}'
                },
                columns:[[
                    {field:'resourceCode',sortable:true,title:'权限编码',align:'center',width:'25%'},
                    {field:'resourceName',sortable:true,title:'模块名称',align:'center',width:'25%'},
                    {field:'resourceType',sortable:true,title:'类型',align:'center',width:'25%'},
                    {field:'aaa',title:'资源',sortable:true,align:'center',width:'25%',
                        formatter: function(value,row,index){
                            show = "<a class='easyui-linkbutton' href='${emms}/system/resource.do?cmd=editResourcePage&parentId=${parentId}&resourceId="
                            + row.resourceId
                            + "' target='_self'>编辑</a>&nbsp;&nbsp;&nbsp;";
                            show += "<a href=\"javascript:delResource(\'"+ row.resourceId + "\')\">删除</a>&nbsp;&nbsp;&nbsp;";
                            return show;
                        }}
                ]]
            });
        }
        function clearForm(){
            $('#query').form('clear');
        }

        function delResource(resourceId){
            if(window.confirm('确定要删除当前记录吗？')){
                $.ajax({
                    url:"${emms}/system/resource.do?cmd=deleteResource&resourceId="+resourceId,
                    type:"GET",
                    success: function(data) {
                        if(data == 'true'){
                            alert("删除成功");
                            window.parent.frames["westFrame"].initTree();
                            query();
                        }else{
                            alert(data);
                        }

                    }

                });
            }
        }
    </script>
    </body>
</html>
