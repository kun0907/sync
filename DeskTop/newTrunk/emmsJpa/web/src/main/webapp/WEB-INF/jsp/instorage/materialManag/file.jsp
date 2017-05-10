<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>编辑页</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    <style>
        .textbox-label{
            width: 100px;
        }
    </style>
</head>
<body>
<div class="easyui-panel" title="首页->基础信息管理->物资编码管理->查看" data-options="fit:true,border:false">
    <form id="ff" method="post">
        <div style="margin-left:auto; margin-right:auto;width: 400px;">
            <table id="table" auto-resize="true" title="常用计量单位换算（换算关系=单位统计计量数量/常用计量数量）" style="width: 400px;">
            </table>
        </div>
        <div style="text-align: center;">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
        </div>
    </form>
    <form method="post" style="display: none;" id="fileFrom"></form>
</div>
<script type="text/javascript">

    var lastIndex;
    var files = new Array();
    $("#table").datagrid({
        pagination: false,
        fitColumns: true,
        rownumbers: true,
        showFooter: true,
        fitColumns:true,
        onDblClickRow:function(index){//运用双击事件实现对一行的编辑
            if(files[rowIndex]!=null){
                files[rowIndex]
            }
        },
        onClickRow:function(index) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
            $('#table').edatagrid('endEdit', lastIndex);
            $('#table').edatagrid('endEdit', index);
            lastIndex = index;
        },
        toolbar: [{
            iconCls: 'icon-add',
            handler: function(){
                addRow();
            }
        }],
        onExpandRow: function(index,row){
            var dv = $(this).datagrid('getRowDetail',index).find('div.dv');
            if(files[index]==null){
                if(dv.children().length==0){
                    var input = $('<input class="' + index + '" name="picList" type="file" onchange="recordFile(this)"/>');
                    dv.append(input);
                }
            }else{
                dv.append(files[index]);
            }
            $('#table').datagrid('fixDetailRowHeight',index);
            //files[index] = $('<input type="file" id="' + "file." + index + '">');
        },
        columns:[[
            {field:'fileName',sortable:true,title:'文件名',align:'center',width:'100%'}
        ]],
        view: detailview,
        detailFormatter: function(rowIndex, rowData){
            return '<div class="dv"></div>';
        }
    });

    function recordFile(input){
        files[$(input).attr("class")] = $(input);
        var tempData = $('#table').datagrid('getData');
        var fileName = $(input).val();
        tempData.rows[$(input).attr("class")].fileName = fileName.split("\\")[fileName.split("\\").length-1];
        $('#table').datagrid('loadData', tempData);
    }
    //加入新行
    function addRow(){
        $('#table').datagrid('appendRow',{
            "fileName": null
        });
        $('#table').datagrid('loadData', $('#table').datagrid('getData'));
        $('#table').edatagrid('beginEdit',$('#table').datagrid('getData').total-1);
        var lastIndex = $('#table').datagrid('getData').total - 1;
        files[lastIndex] = null;
        $('.datagrid-row-expander').eq(lastIndex).click();
    }

    var updateResult = new Array();
    var n = 0;
    function ajaxSubmitForm() {
        var picForm = $("#fileFrom");
        for(var i=0; i<files.length; i++){
            if(files[i] != null){
                picForm.empty();
                picForm.append(files[i]);
                console.log(files[i].get(0).files[0]);
                $("#fileFrom").ajaxSubmit({
                    type:"post",  //提交方式
                    //async: false,//同步提交
                    url:"${emms}/instorage/materialManag.do?cmd=savePic", //请求url
                    success:function(data){ //提交成功的回调函数
                        updateResult[n] = data;
                        n++;
                    }
                });
            }
        }
        for(var i=0; i<updateResult.length; i++){
            if(updateResult[i] != false){
                $.messager.alert("提示","上传失败");
                return;
            }
        }
        $.messager.alert("提示","上传成功");
    }
</script>
</body>
</html>
