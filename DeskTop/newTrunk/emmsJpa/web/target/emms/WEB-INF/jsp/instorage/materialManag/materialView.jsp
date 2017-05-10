<%--
  Created by IntelliJ IDEA.
  User: YINXP
  Date: 2017/3/7
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >

<html>
<head>
    <title>新建质检单</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->实物入库管理->物资质检管理->查看质检单" data-options="fit:true,border:false">
    <form id="query" method="post">
        <div style="padding:10px">
            <input class="easyui-textbox" id="inspectNo" name="inspectNo" style="width:30%" data-options="readonly:true,label:'质检单编号:'">
            <input class="easyui-textbox" id="supplierId" name="supplierName" style="width:30%" data-options="readonly:true,label:'供应商名称:'">
            <input class="easyui-textbox" id="textArea" name="inspector" data-options="readonly:true,multiline:true,label:'质检员:'" style="height:100px;width:300px">
        </div>
        <div style="padding:10px">
            <input class="easyui-textbox" id="createUserName" name="createUserName" style="width:30%" data-options="readonly:true,label:'创建人:'">
            <input class="easyui-datebox" id="createTime" name="createTime" style="width:30%" data-options="readonly:true,label:'创建时间:'">
        </div>
    <div>
        <a onclick="Goback()" iconCls='icon-back' class="easyui-linkbutton">返回</a>
        <table id="tableM" auto-resize="true" class="easyui-datagrid" title="">
        </table>
    </div>
        <div>
            <table id="tableT" auto-resize="true" class="easyui-datagrid" style="width:100%;height:250px" title="图片文件展示"></table>
        </div>
    </form>
</div>
<script type="text/javascript">
    var lastIndex;
    var reasonList={};
    var dictionary = {};
    $(function(){
        $('#query').form('load', '${emms}/instorage/materialManag.do?cmd=loadMaterialInspectData&materiaInspectId=${materiaInspectId}');
        $('#query').form({
            onLoadSuccess:function(data) {
                //从后台为datagrid加载数据
                if(data.qualityInspectDetailList!=null && data.qualityInspectDetailList.length>0){
                    $("#tableM").edatagrid("loadData", loadFilter(data.qualityInspectDetailList));
                }else{
                    var qualityInspectDetailList=[];
                    $("#tableM").edatagrid("loadData", loadFilter(qualityInspectDetailList));
                }

                if(data.inspectPicFileList!= null && data.inspectPicFileList.length>0){
                    $("#tableT").edatagrid("loadData", loadFilter(data.inspectPicFileList));
                }else{
                    var inspectPicFileList=[];
                    $("#tableT").edatagrid("loadData", loadFilter(inspectPicFileList));
                }
            }
        });

        $('#tableM').datagrid({
            pagination: true,
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
            fitColumns:true,
            ctrlSelect:true,
            queryParams: {
            },
            columns:[[
                {field:'materiaInspectId',hidden:true},
                {field:'receiptCode',sortable:true,title:'收货单编号',align:'center',width:'14%'},
                {field:'packingNo',sortable:true,title:'包装编号',align:'center',width:'14%'},
                {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'14%'},
                {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'14%'},
                {field:'additional1',sortable:true,title:'附加1',align:'center',width:'8%'},
                {field:'additional2',sortable:true,title:'附加2',align:'center',width:'8%'},
                {field:'additional3',sortable:true,title:'附加3',align:'center',width:'8%'},
                {field:'additional4',sortable:true,title:'附加4',align:'center',width:'8%'},
                {field:'wbsCode',sortable:true,title:'工程（WBS）编码',align:'center',width:'12%'},
                {field:'materialsUnitMain',sortable:true,title:'采购计量单位',align:'center',width:'14%'},
                {field:'productDate',sortable:true,title:'生产日期',align:'center',width:'14%',required:true,editor:{type:'datebox'}},
                {field:'qualityDate',sortable:true,title:'保质期',align:'center',width:'14%',required:true,editor:{type:'datebox'}},
                {field:'purchaseCount',sortable:true,title:'采购数量',align:'center',width:'8%'
                    //editor:{type:'numberbox',options:{required:true}}
                },
                {field:'deliveryQty',sortable:true,title:'已发货数量',align:'center',width:'10%'
                    //editor:{type:'numberbox',options:{required:true}}
                },
                {field:'currentDeliveryQty',sortable:true,title:'本次发货数量',align:'center',width:'14%'
                    //editor:{type:'numberbox',options:{required:true}}
                },
                {field:'dianshouCount',sortable:true,title:'点收数量',align:'center',width:'10%'
                  //  editor:{type:'numberbox',options:{required:true}}
                },
                {field:'qualifiedQty',sortable:true,title:'合格数量',align:'center',width:'10%'
                   // editor:{type:'numberbox',options:{required:true}}
                },
                {field:'unQualifiedQty',sortable:true,title:'不合格数量',align:'center',width:'10%'
                   // editor:{type:'numberbox',options:{required:true}}
                },
                {field:'appearanceInspect',sortable:true,title:'外观检查',align:'center',width:'6%',
                    formatter: function(value,row,index){
                        return getValueByKey(value);
                    }
                },
                {field: 'recheckInspect', sortable: true, title: '需要复检', align: 'center', width: '6%',
                    formatter: function(value,row,index){
                        return getValueByKey(value);
                    }
                }
            ]]
        })
    });

    $('#tableT').datagrid({
        fitColumns: true,
        rownumbers: true,
        showFooter: true,
        fitColumns:true,
        ctrlSelect:true,
        columns:[[
            {field:'materiaInspectId',hidden:true},
            {field:'inspectPicFileId',hidden:true},
            {field:'realFileName',sortable:true,title:'图片名',align:'center',width:'75%'},
            {
                field: 'aaa', title: '操作', sortable: true, align: 'center', width: '20%',
                formatter: function(value,row,index){
                    if(row.inspectPicFileId == "" || row.inspectPicFileId == null || row.inspectPicFileId == undefined){
                        show = "";
                    }else{
                        show = "<a class='easyui-linkbutton'  href='${emms}/instorage/materialManag.do?cmd=DownPicReceipt&InspectPicId="
                        + row.inspectPicFileId
                        + "' target='_self'>下载预览</a>&nbsp;&nbsp;&nbsp;";
                    }
                    return show;
                }
            }
        ]]
    });

    //跳转到添加明细jsp
    function addDetail(){
        top.$('#dialog').dialog({
            title: '添加明细',
            width: 1000,
            height: 540,
            closed: false,
            cache: true,
            href: '${emms}/instorage/receiptGoods.do?cmd=dialogReceiptGoods'
        });
    }

    function loadFilter(data) {//重新组织datagrid数据，把符合条件的内容加到定义的json字符串中。
        var value = {
            total: data.length,
            rows: data
        };
        return value;
    }

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

    //接收数据
    function checkMaterials(rows){
        for(var i=0;i<rows.length;i++){
            $('#tableM').datagrid('appendRow',{
                "packingId": rows[i].packingNo,
                "receiptCode": rows[i].receiptCode,
                "materialsCode":rows[i].materialsCode,
                "materialsDescribe":rows[i].materialsDescribe,
                "additional1": rows[i].additional1,
                "additional2": rows[i].additional2,
                "additional3": rows[i].additional3,
                "additional4": rows[i].additional4,
                "wbsCode":rows[i].wbsCode,
                "materialsUnitMain":rows[i].materialsUnitMain,
                "purchaseCount":rows[i].purchaseCount,
                "dianshouCount":rows[i].dianshouCount
            });
        }
    }
    //返回到查询页
    function Goback(){
        window.location =  "${emms}/instorage/materialManag.do?cmd=query";
    }

    $('#supplierId').combobox({
        url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
        valueField: 'orgId',
        textField: 'orgName',
        multiple:false
    });
    </script>
</body>
</html>
