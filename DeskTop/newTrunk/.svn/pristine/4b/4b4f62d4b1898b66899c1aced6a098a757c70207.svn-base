<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/24
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>列表页</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>

<div class="easyui-panel" title="首页->采购管理->框架协议管理->框架协议编辑" data-options="fit:true,border:false">
    <form id="edit" method="post">
        <input type="hidden" id="agreementId" name="agreementId" value="${agreementId}" />
        <div style="padding:10px">
            <input class="easyui-textbox" id="agreementCode" name="agreementCode" style="width:30%" data-options="disabled:true,label:'编号:'">
            <B>合同执行期限:</B>
            <input class="easyui-datebox " id="performStartDate" name="performStartDate" style="width:12%" >-
            <input class="easyui-datebox " id="performEndDate" name="performEndDate" style="width:12%" >
        </div>

        <div style="text-align: left;">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-add'
               onclick="create()">新建供应商</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="modal()">新建物资明细</a>
        </div>

    </form>
    <table id="table1" auto-resize="true" class="easyui-edatagrid" style="width:100%;height:200px" title="供应商列表">
    </table>
    <table id="table2" auto-resize="true" class="easyui-edatagrid" style="width:100%;height:200px" title="物资明细列表" >

    </table>
    <div style="text-align: center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="ajaxSubmitForm('agreementNotCommit')">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-add' onclick="ajaxCommit()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-back' onclick="exitLayout()">返回</a>
    </div>

</div>


<script type="text/javascript">
    var lastIndex;

    var lastIndex1;
    var supplierNo=[];

    $(function() {
        var agreementId = "${agreementId}";
        $('#edit').form('load', '${emms}/purchase/agreement.do?cmd=agreementDetail&agreementId=${agreementId}');
        $('#edit').form({
            onLoadSuccess: function (data) {
                if(null != data.agreementDetailList) {
                    if (data.agreementDetailList.length > 0) {
                        $("#table2").edatagrid("loadData", loadFilter(data.agreementDetailList));
                    } else {
                        var agreementDetailList = [];
                        $("#table2").edatagrid("loadData", loadFilter(agreementDetailList));
                    }
                }
                if(null !=data.agreementSupplierList) {
                    if (data.agreementSupplierList.length > 0) {
                        $("#table1").edatagrid("loadData", loadFilter(data.agreementSupplierList));
                    } else {
                        var agreementSupplierList = [];
                        $("#table1").edatagrid("loadData", loadFilter(agreementSupplierList));
                    }
                }
            }

        })
    })


      $(function(){  $('#table1').edatagrid({
            pagination:  false,
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
          singleSelect:true,

            columns: [[
                {field: 'orgCode', sortable: true, title: '供应商编号', align: 'center', width: '25%'},
                {field: 'orgName', sortable: true, title: '供应商名称', align: 'center', width: '25%'},
                {field: 'supplierAgreementCode', sortable: true, title: '框架协议编码', align: 'center', width: '25%',editor:{type:'textbox',options:{required:true,precision:2,validType:'length[1,30]'}}},
                {field:'supplierOrgId',sortable:true,title:'供应商ID',align:'center',width:'1%',hidden:'true'},
                {field: 'aaa', title: '操作', sortable: true, align: 'center', width: '25%',
                    formatter: function (value, row, index) {
                        show = "<a class='easyui-linkbutton' onclick='aaa()'"
                        + " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
                        return show;
                    }
                }
            ]],onDblClickRow:function(row){//运用双击事件实现对一行的编辑
                      $('#table1').edatagrid('beginEdit', row);
                  },
                  onClickRow:function(row) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
                      $('#table1').edatagrid('endEdit', lastIndex1);
                      $('#table1').edatagrid('endEdit', row);
                      lastIndex1 = row;
                  }
        });
      }
    )

    $(function (){ $('#table2').edatagrid({
            <%--url: '${emms}/purchase/agreement.do?cmd=loadAgreementDetailListData',--%>
            <%--method: 'POST',--%>
            pagination: false,
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
           singleSelect:true,
            columns: [[
                {field:'materialsId',sortable:true,title:'物资Id',align:'center',hidden:'true'},
                {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'10%'},
                {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'10%'},
                {field:'additional1',sortable:true,title:'附加1',align:'center',width:'8%'},
                {field:'additional2',sortable:true,title:'附加2',align:'center',width:'8%'},
                {field:'additional3',sortable:true,title:'附加3',align:'center',width:'8%'},
                {field:'additional4',sortable:true,title:'附加4',align:'center',width:'8%'},
                {field:'materialsUnitMain',sortable:true,title:'采购计量单位',align:'center',width:'8%'},
                {field:'unitPrice',sortable:true,title:'单价',align:'center',width:'10%',editor:{type:'numberbox',options:{required:true,precision:2}}},
                {field:'numberMain',sortable:true,title:'暂估数量',align:'center',width:'10%',editor:{type:'numberbox',options:{required:true,precision:2}}},
                {field:'totalPrice',sortable:true,title:'暂估总价',align:'center',width:'10%'},
                {
                    field: 'aaa', title: '操作', sortable: true, align: 'center', width: '10%',
                    formatter: function (value, row, index) {
                        show = "<a class='easyui-linkbutton' onclick='bbb()'"
                        + " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
                        return show;
                    }
                }
            ]],onDblClickRow:function(row){//运用双击事件实现对一行的编辑
            $('#table2').edatagrid('beginEdit', row);
        },
        onClickRow:function(row) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
            $('#table2').edatagrid('endEdit', lastIndex);
            $('#table2').edatagrid('endEdit', row);
            lastIndex = row;
        }
        });

    })

    //后台List处理为
    function loadFilter(data) {//重新组织datagrid数据，把符合条件的内容加到定义的json字符串中。
        var value = {
            total: data.length,
            rows: data
        };
        return value;
    }

    function create() {
        top.$('#dialog').dialog({
            title: '供应商选择弹出框',
            width: 700,
            height: 420,
            closed: false,
            cache: true,
            href: '${emms}/purchase/agreement.do?cmd=create'
        });

    }
    function modal(){
        top.$('#dialog').dialog({
            title: '公共物料选择弹出框',
            width: 900,
            height: 540,
            closed: false,
            cache: true,
            href: '${emms}/purchase/agreement.do?cmd=modal'
        });
    }

    function checkMaterials(rows){
        for(var i=0;i<rows.length;i++){
            $('#table2').datagrid('appendRow',{
                "materialsId":rows[i].materialsId,
                "materialsCode": rows[i].materialsCode,
                "materialsDescribe": rows[i].materialsDescribe,
                "additional1": rows[i].additional1,
                "additional2": rows[i].additional2,
                "additional3": rows[i].additional3,
                "additional4": rows[i].additional4,
                "materialsUnitMain":rows[i].materialsUnitMain
            });
        }
    }
    function checkOrg(rows){
        for(var i=0;i<rows.length;i++){
            $('#table1').datagrid('appendRow',{
                "orgCode": rows[i].orgCode,
                "orgName": rows[i].orgName,
                "supplierOrgId": rows[i].orgId
            });
        }
    }
//保存
    function ajaxSubmitForm(state) {
        supplierNo=[];
        $('#table1').edatagrid('endEdit', lastIndex1);
        $('#table2').edatagrid('endEdit', lastIndex);
        var supplierInfo=$("#table1").edatagrid("getData");
            var materialsInfo=$("#table2").edatagrid("getData");
            for(var i=0;i<supplierInfo.rows.length;i++){
                if(supplierInfo.rows[i].supplierAgreementCode == null || supplierInfo.rows[i].supplierAgreementCode == "" || supplierInfo.rows[i].supplierAgreementCode == undefined){
                    $.messager.alert("操作提示","请按指定要求输入框架协议编码，且不能为空","warning");
                    return false;
                }else{
                    if(supplierNo.indexOf(supplierInfo.rows[i].supplierAgreementCode)==-1){
                        supplierNo.push(supplierInfo.rows[i].supplierAgreementCode);
                    }else{
                        $.messager.alert("操作提示",supplierInfo.rows[i].supplierAgreementCode+"重复","warning");
                        return false;
                    }
                }
            }
            for(var i=0;i<materialsInfo.rows.length;i++){
                if(materialsInfo.rows[i].unitPrice == null || materialsInfo.rows[i].unitPrice == "" || materialsInfo.rows[i].numberMain == null || materialsInfo.rows[i].numberMain == "" || materialsInfo.rows[i].unitPrice == undefined || materialsInfo.rows[i].numberMain == undefined){
                    $.messager.alert("操作提示","单价和暂估数量不能为空","warning");
                    return false;
                }
            }

        if(supplierInfo.rows.length == 0){//物资明细列表不能为空
            $.messager.alert("操作提示","供应商列表不能为空","warning");
            return false;
        }

            if(materialsInfo.rows.length == 0){//物资明细列表不能为空
                $.messager.alert("操作提示","物资明细列表不能为空","warning");
                return false;
            }
        var agreement= {
            "agreementId":$("#agreementId").val(),
            "agreementCode":$("#agreementCode").val(),
            "performStartDate":$("#performStartDate").datebox("getValue"),
            "performEndDate":$("#performEndDate").datebox("getValue"),
            "agreementState":state,
            "agreementDetailList":materialsInfo.rows,
            "agreementSupplierList":supplierInfo.rows

        };
        var isValid = $("#edit").form("validate");//验证此form表单中的输入框合理性，不合理：不能提交并页面提示；
        if(isValid){
        $.ajax({
            type: 'POST',
            url: "${emms}/purchase/agreement.do?cmd=saveAgreement",
            data: JSON.stringify(agreement),
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            onsubmit: function () {
                return $(this).form("validate");
            },
            success: function (result) {
                $.messager.alert("操作提示","保存成功","info",function(){
                    window.location = "${emms}/purchase/agreement.do?cmd=query";
                });

            },
            error:function(result){
                $.messager.alert("操作提示",result,"error");
            }
        });
        }
        }

//提交
    //提交
    function ajaxCommit() {
        $('#table1').edatagrid('endEdit', lastIndex);
        $('#table2').edatagrid('endEdit', lastIndex1);
        var state="agreementCommit";
        $.messager.confirm("操作提示", "确定要提交当前记录吗？", function (data) {
            if(data) {
                ajaxSubmitForm(state);
            }
        });
    }
    <%--function ajaxCommit() {--%>
        <%--var id=$("#agreementId").val();--%>
        <%--if (id==null || id==""){alert("请先保存");}--%>
        <%--else{--%>
        <%--var state='agreementCommit';--%>
        <%--if(window.confirm('确定要提交当前记录吗？')){--%>
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--url:"${emms}/purchase/agreement.do?cmd=updateAgreementState&agreementId="+id+"&state="+state,--%>
                <%--async: false,--%>
                <%--success: function(result) {--%>
                    <%--console.log(result);--%>
                    <%--$.messager.alert("操作提示","提交成功","info",function(){--%>
                        <%--window.location = "${emms}/purchase/agreement.do?cmd=query";});--%>

                <%--},--%>
                <%--error: function () {--%>
                    <%--$.messager.alert("操作提示","提交失败","error");--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
        <%--}--%>
    <%--}--%>
    //由编辑页面返回到查询页面
    function exitLayout(){
        window.location = "${emms}/purchase/agreement.do?cmd=query";
    }

//
//    function aaa(e){
//        $(e).parent().parent().parent().remove();
//    }

    function aaa() {
        var rows = $('#table1').edatagrid("getSelections");
        for (var i = rows.length - 1; i >= 0; i--) {
            var index = $('#table1').edatagrid('getRowIndex', rows[i]);
            $('#table1').edatagrid('deleteRow', index);
        }
    }

        function bbb() {
            var rows = $('#table2').edatagrid("getSelections");
            for (var i = rows.length - 1; i >= 0; i--) {
                var index = $('#table2').edatagrid('getRowIndex', rows[i]);
                $('#table2').edatagrid('deleteRow', index);
            }
        }


</script>
</body>
</html>