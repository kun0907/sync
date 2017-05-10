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
    <title>编辑质检单</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body>
<div class="easyui-panel" title="首页->实物入库管理->直达质检管理->编辑质检单" data-options="fit:true,border:false">
    <form id="formT" method="post" enctype="multipart/form-data">
        <input type="hidden" id="materiaInspectId" name="materiaInspectId"/>
        <input type="hidden" id="materiaInspectStute" name="materiaInspectStute" value="${materiaInspectStuate}"/>
        <input type="hidden" id="dataSource_direct" name="dataSource_direct" value="direct"/>
        <div style="padding:10px;text-align:-moz-left">
            <input class="easyui-textbox" id="inspectNo" name="inspectNo" style="width:30%" data-options="readonly:true,label:'质检单编号:',disabled:true">
            <input class="easyui-combobox" id="supplierId" name="supplierId" style="width:30%" data-options="label:'供应商名称:',required:true,editable:false">
        </div>
        <div style="padding:10px;text-align:-moz-center">
            <input class="easyui-textbox" id="createUserName" name="createUserName" style="width:30%" data-options="label:'创建人:',disabled:true">
            <input class="easyui-datebox" id="createTime" name="createTime" style="width:30%" data-options="label:'创建时间:',disabled:true">
        </div>
        <div style="padding:10px;text-align:-moz-left">
            <input class="easyui-textbox" id="textArea" name="inspector" data-options="multiline:true,label:'质检员:'" style="height:70px;width:300px">
        </div>
        <a href="javascript:void(0)" iconCls='icon-add' class="easyui-linkbutton" onclick="addDetail()">添加质检明细</a>
        <a href="javascript:void(0)" iconCls='icon-remove' class="easyui-linkbutton" onclick="delVehicleInfo()">删除质检明细</a>
    </form>
    <table id="tableM" auto-resize="true" class="easyui-datagrid" style="width:100%;height:220px" title="物资明细" >
    </table>
    <table id="tableT" auto-resize="true" class="easyui-datagrid" style="width:100%;height:250px" title="图片文件展示">
    </table>
    <div style="text-align: center;">
        <div style="text-align:right;float: left;width: 50%" id="ToSave">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-save' onclick="save('billStaus_a')">保存</a>
        </div>
        <div style="text-align:right;float: left;" id="complete">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="ajaxCommit()">质检完成</a></div>
        </div>
        <div style="text-align:left;">
            <a href="${emms}/instorage/materialManag.do?cmd=queryDirect" iconCls='icon-back' class="easyui-linkbutton" >返回</a>
        </div>
    </div>
    </form>
    <form method="post" style="displaqy: none;" enctype="multipart/form-data" id="fileFrom"></form>
</div>
<script type="text/javascript">
    var lastIndex;
    var dictionary = {};
    var reasonList={};
    //校验选物资不可重复
    var checkPacking=[];
    $('#supplierName').combobox({
        url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
        valueField: 'orgId',
        textField: 'orgName',
        multiple:false
    });
    $(function(){
        var materiaInspectStute = $("#materiaInspectStute").val();
        if(materiaInspectStute == "Checking"){
            $("#supplierId").combobox('disable');
        }else{
            $("#supplierId").combobox('enable');
        }
        $('#formT').form('load', '${emms}/instorage/materialManag.do?cmd=loadDirectInspectData&materiaInspectId=${materiaInspectId}');
        $('#formT').form({
            onLoadSuccess:function(data) {
                //从后台为datagrid加载数据
                if(data.qualityInspectDetailList!=null && data.qualityInspectDetailList.length>0){
                    $("#tableM").edatagrid("loadData", loadFilter(data.qualityInspectDetailList));
                    for(var i=0;i<data.qualityInspectDetailList.length;i++){
                        checkPacking.push(data.qualityInspectDetailList[i].packingDetailId);
                    }
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

        $('#tableT').datagrid({
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
            fitColumns:true,
            ctrlSelect:true,
            columns:[[
                {field:'materiaInspectId',hidden:true},
                {field:'inspectPicFileId',hidden:true},
                {field:'realFileName',sortable:true,title:'图片名',align:'center',width:'78%'},
                {
                    field: 'aaa', title: '操作', sortable: true, align: 'center', width: '20%',
                    formatter: function(value,row,index){
                        show ="";
                        if(row.inspectPicFileId == "" || row.inspectPicFileId == null || row.inspectPicFileId == undefined){
                            show ="";
                        }else{
                            show += "<a class='easyui-linkbutton'  href='${emms}/instorage/materialManag.do?cmd=DownPicDirect&InspectPicId="
                            + row.inspectPicFileId
                            + "' target='_self'>下载预览</a>&nbsp;&nbsp;&nbsp;";
                            show += "<a class='easyui-linkbutton' onclick='ajaxDelete(\""+row.inspectPicFileId+"\")'"
                            + " target='_self'>删除</a>&nbsp;&nbsp;&nbsp;";
                        }
                        return show;
                    }
                }
            ]]
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
            {field:'wbsId',hidden:true},
            {field:'materialsId',hidden:true},
            {field:'materiaInspectId',hidden:true},
            {field:'qualityInspectId',hidden:true},
            {field:'supplierId',hidden:true},
            {field:'receiptOrgId',hidden:true},
            {field:'storageId',hidden:true},//储位ID
            {field:'storageCode',hidden:true},//储位
            {field:'deliveryId',hidden:true},//收货单ID
            {field:'packingDetailId',hidden:true},//收货单明细ID
            {field:'receiptCode',sortable:true,title:'收货单编号',align:'center',width:'14%'},
            {field:'packingNo',sortable:true,title:'包装编号',align:'center',width:'14%'},
            {field:'materialsCode',sortable:true,title:'物资编码',align:'center',width:'14%'},
            {field:'materialsDescribe',sortable:true,title:'物资描述',align:'center',width:'14%'},
            {field:'additional1',sortable:true,title:'附加1',align:'center',width:'10%'},
            {field:'additional2',sortable:true,title:'附加2',align:'center',width:'10%'},
            {field:'additional3',sortable:true,title:'附加3',align:'center',width:'10%'},
            {field:'additional4',sortable:true,title:'附加4',align:'center',width:'10%'},
            {field:'wbsCode',sortable:true,title:'工程（WBS）编码',align:'center',width:'12%'},
            {field:'materialsUnitMain',sortable:true,title:'采购计量单位',align:'center',width:'8%'},
            {field:'productDate',sortable:true,title:'生产日期',align:'center',width:'14%',required:true,editor:{type:'datebox'}},
            {field:'qualityDate',sortable:true,title:'保质期',align:'center',width:'14%',required:true,editor:{type:'datebox'}},
            {field:'purchaseCount',sortable:true,title:'采购数量',align:'center',width:'8%'
//                editor:{type:'numberbox',options:{required:true}}
            },
            {field:'deliveryQty',sortable:true,title:'已发货数量',align:'center',width:'8%'
//                editor:{type:'numberbox',options:{required:true}}
            },
            {field:'currentDeliveryQty',sortable:true,title:'本次发货数量',align:'center',width:'8%'
//                editor:{type:'numberbox',options:{required:true}}
            },
            {field:'dianshouCount',sortable:true,title:'点收数量',align:'center',width:'8%',
                editor:{type:'numberbox',options:{precision:4}}
            },
            {field:'qualifiedQty',sortable:true,title:'合格数量',align:'center',width:'8%',
                editor:{type:'numberbox',options:{precision:4}},
                formatter: function(value,row,index){
                    $.ajax({
                        type: 'POST',
                        url: "${emms}/instorage/materialManag.do?cmd=CheckOutDirect",
                        data: JSON.stringify(row),
                        dataType:'json',
                        contentType:"application/json;charset=utf-8",
                        async:false,
                        success: function (data){
                            console.log(data);
                            if(data == '-1'){
                                row.qualifiedQty = '';
                                $('#tableM').datagrid("beginEdit", 0);
                                $.messager.alert("操作提示","累计合格数量超出点收数量，请重新填写合格数量！","error");

                            }
                        }
                    });
                    return value;
                }
            },
            {field:'unQualifiedQty',sortable:true,title:'不合格数量',align:'center',width:'8%',
                editor:{type:'numberbox',options:{precision:4}}
            },
            {field:'appearanceInspect',sortable:true,title:'外观检查',align:'center',width:'8%',
                formatter: function(value,row,index){
                    return getValueByKey(value);
                },
                editor:{type:'combobox',
                    options:{
                        valueField:'dictionaryCode',
                        textField:'dictionaryName',
                        data:reasonList,
//                        required:true,
                        editable:false
                    }
                }
            },
            {field: 'recheckInspect', sortable: true, title: '需要复检', align: 'center', width: '8%',
                formatter: function(value,row,index){
                    return getValueByKey(value);
                },
                editor: {type: 'combobox',
                    options:{
                        valueField:'dictionaryCode',
                        textField:'dictionaryName',
                        data:reasonList,
//                        required:true,
                        editable:false
                    }
                }
            }
        ]],
        onDblClickRow:function(row){//运用双击事件实现对一行的编辑
            $('#tableM').datagrid('beginEdit', row);
        },
        onClickRow:function(row) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
            $('#tableM').datagrid('endEdit', lastIndex);
            $('#tableM').datagrid('endEdit', row);
            lastIndex = row;
        }
    })

    });

    //跳转到添加明细的jsp
    function addDetail(){
        var supplierId = $("#supplierId").combobox("getValue");//id
        var supplierName = $("#supplierId").combobox("getText");//name
        if(!supplierName){
            $.messager.alert("提示信息","请选择供应商","warning");
        }else{
            top.$('#dialog').dialog({
                title: '添加明细',
                width: 1000,
                height: 540,
                closed: false,
                cache: true,
                href: '${emms}/instorage/receiptGoods.do?cmd=dialogReceiptGoods&dataSource=direct&supplierId='+supplierId
            });
        }
    }

    //删除图片
    function ajaxDelete(id) {
        var materiaInspectId = $("#materiaInspectId").val();
        var materiaInspectStute = $("#materiaInspectStute").val();
        $.messager.confirm("操作提示", "确定要删除吗？", function (data) {
            if(data){
                $.ajax({
                    type: "POST",
                    url: "${emms}/instorage/materialManag.do?cmd=DeletePicDirect&InspectPicId=" + id,
                    async: false,
                    success: function (result) {
                        console.log(result);
                        $.messager.alert("操作提示","删除成功","info");//editDirect& materiaInspectStuate =Checking & materiaInspectId ="
                        window.location = "${emms}/instorage/materialManag.do?cmd=editDirect&materiaInspectId="+materiaInspectId+"&materiaInspectStuate="+materiaInspectStute+"";
                    },
                    error: function () {
                        $.messager.alert("操作提示","删除失败","error");
                    }
                });
            }
        });
    }

    //删除质检明细
    function delVehicleInfo(){
        var rows = $('#tableM').datagrid("getSelections");
        for (var i = rows.length - 1; i >= 0; i--) {
            var index = $('#tableM').datagrid('getRowIndex',rows[i]);
            $('#tableM').datagrid('deleteRow', index);

            //将存储的收货单ID清除
            for(var z=0;z<checkPacking.length;){
                if(checkPacking[index]==checkPacking[z]){
                    checkPacking.splice(z,1)
                }else{
                    z++;
                }
            }
        }
    }

    function loadFilter(data) {//重新组织datagrid数据，把符合条件的内容加到定义的json字符串中。
        var value = {
            total: data.length,
            rows: data
        };
        return value;
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

   //接收弹出框数据
    function checkReceiptGoods(rows){
        //console.log(rows);
        for(var i=0;i<rows.length;i++){
            if(checkPacking.indexOf(rows[i].packingDetailId)!=-1){
                continue;
            }else{
                checkPacking.push(rows[i].packingDetailId);
            }
            $('#tableM').datagrid('appendRow',{
                "packingNo": rows[i].packingCode,
                "wbsId":rows[i].wbsId,
                "materialsId":rows[i].materialsId,
                "receiptCode": rows[i].receiptCode,
                "materialsCode":rows[i].materialsCode,
                "additional1": rows[i].additional1,
                "additional2": rows[i].additional2,
                "additional3": rows[i].additional3,
                "additional4": rows[i].additional4,
                "wbsCode":rows[i].wbsCode,
                "materialsUnitMain":rows[i].deMainUnit,
                "purchaseCount":rows[i].purchaseCount,
                "dianshouCount":rows[i].dianshouCount,
                "productDate":rows[i].productionDate,
                "qualityDate":rows[i].bzq,
                "materialsDescribe":rows[i].materialsDescribe,
                "deliveryQty":rows[i].deliveryCount,
                "currentDeliveryQty":rows[i].thisDeliveryCount,
                "storageId":rows[i].storageId,
                "storageCode":rows[i].storageCode,
                "receiptOrgId":rows[i].receiptOrgId,
                "deliveryId":rows[i].receiptId,
                "packingDetailId":rows[i].packingDetailId
            });
        }
    }

    function save(state){
       var inspectDetail=$("#tableM").datagrid("getData");
       var materiaInspectId = $("#materiaInspectId").val();
       var e_supplierName = $("#supplierId").combobox('getText');
       var e_supplierId = $("#supplierId").combobox('getValue');
       var e_dataSource = $("#dataSource_direct").attr("value");

       var inspect = {
            "materiaInspectId":materiaInspectId,
            "supplierId": e_supplierId,
            "dataSource": e_dataSource,
            "inspectNo": $("#inspectNo").val(),
            "supplierName":e_supplierName,
            "inspectStaus":state,
            "inspector": $("#textArea").val(),
            "createUserName": $("#createUserName").val(),
            "createTime": $("#createTime").val(),
            "qualityInspectDetailList": inspectDetail.rows
        }
        if(inspectDetail.rows.length == 0){//质检明细列表不能为空
            $.messager.alert("操作提示","质检明细列表不能为空！","warning");
            return false;
        }
        var detailList=$("#tableM").datagrid("getRows");
        for(var i=0;i<detailList.length;i++) {
            if (detailList[i].unQualifiedQty ==null || detailList[i].unQualifiedQty =="" || detailList[i].unQualifiedQty ==undefined ) {
                if (detailList[i].qualifiedQty ==null || detailList[i].qualifiedQty =="" || detailList[i].qualifiedQty ==undefined ) {
                    $.messager.alert("操作提示","合格数量和不合格数量不能都为空！","error",function(){
                        return false;
                    });
                    return false;
                }else{
                    if (parseInt(detailList[i].qualifiedQty) < 0 ) {
                        $.messager.alert("操作提示","合格数量不能为负数！","error",function(){
                            return false;
                        });
                        return false;
                    }
                }
            }else{
                if (parseInt(detailList[i].qualifiedQty) < 0 ) {
                    $.messager.alert("操作提示","合格数量不能为负数！","error",function(){
                        return false;
                    });
                    return false;
                }
                if (detailList[i].qualifiedQty ==null || detailList[i].qualifiedQty =="" || detailList[i].qualifiedQty ==undefined ) {

                }else{
                    if(parseInt(detailList[i].unQualifiedQty) < 0 ){
                        $.messager.alert("操作提示","不合格数量不能为负数！","error",function(){
                            return false;
                        });
                        return false;
                    }
                    if (parseInt(detailList[i].qualifiedQty) == 0 && parseInt(detailList[i].unQualifiedQty) == 0) {
                        $.messager.alert("操作提示","合格数量和不合格数量不能都为0！","error",function(){
                            return false;
                        });
                        return false;
                    }

                }
            }
            if (parseInt(detailList[i].unQualifiedQty) < 0 ) {
                $.messager.alert("操作提示","不合格数量不能为负数！","error",function(){
                    return false;
                });
                return false;
            }
            if (detailList[i].dianshouCount ==null || detailList[i].dianshouCount =="" || detailList[i].dianshouCount ==undefined ) {
                $.messager.alert("操作提示","点收数量不能为空！","error",function(){
                    return false;
                });
                return false;
            }
            if (parseInt(detailList[i].dianshouCount) < 0) {
                $.messager.alert("操作提示","点收数量不能为负数！","error",function(){
                    return false;
                });
                return false;
            }
            if (detailList[i].qualifiedQty ==null || detailList[i].qualifiedQty =="" || detailList[i].qualifiedQty ==undefined ) {
                if(parseInt(detailList[i].unQualifiedQty) == 0){
                    $.messager.alert("操作提示","不合格数量不合法！","error",function(){
                        return false;
                    });
                    return false;
                }
            }
            if (detailList[i].unQualifiedQty ==null || detailList[i].unQualifiedQty =="" || detailList[i].unQualifiedQty ==undefined ) {
                if(parseInt(detailList[i].qualifiedQty) == 0){
                    $.messager.alert("操作提示","合格数量不合法！","error",function(){
                        return false;
                    });
                    return false;
                }
            }
            if (detailList[i].appearanceInspect ==null || detailList[i].appearanceInspect =="" || detailList[i].appearanceInspect ==undefined ) {
                $.messager.alert("操作提示","外观检查不能为空！","error",function(){
                    return false;
                });
                return false;
            }
            if (detailList[i].recheckInspect ==null || detailList[i].recheckInspect =="" || detailList[i].recheckInspect ==undefined ) {
                $.messager.alert("操作提示","需要复检不能为空！","error",function(){
                    return false;
                });
                return false;
            }
            if (parseInt(detailList[i].dianshouCount)<=0 ) {
                $.messager.alert("操作提示","点收数量不能为负数或0！","error",function(){
                    return false;
                });
                return false;
            }

            if(parseInt(detailList[i].dianshouCount) < parseInt(detailList[i].qualifiedQty)){
                $.messager.alert("操作提示","合格数量不能大于点收数量！","error",function(){
                    return false;
                });
                return false;
            }
            if(parseInt(detailList[i].dianshouCount) < parseInt(detailList[i].unQualifiedQty)){
                $.messager.alert("操作提示","不合格数量不能大于点收数量！","error",function(){
                    return false;
                });
                return false;
            }
            if(parseInt(detailList[i].dianshouCount) < parseInt(detailList[i].unQualifiedQty)+parseInt(detailList[i].qualifiedQty)){
                $.messager.alert("操作提示","合格数量与不合格数量之和不能大于点收数量！","error",function(){
                    return false;
                });
                return false;
            }
//            if(parseInt(detailList[i].purchaseCount ) < parseInt(detailList[i].dianshouCount)){
//                $.messager.alert("操作提示","点收数量不能大于采购数量！","error",function(){
//                    return false;
//                });
//                return false;
//            }
        }
       var isValid = $("#formT").form("validate");
        if(isValid){
            $.ajax({
                type: 'POST',
                url: "${emms}/instorage/materialManag.do?cmd=saveDirectInspect",
                data: JSON.stringify(inspect),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    var picForm = $("#fileFrom");
                    for(var i=0; i<files.length; i++){
                        if(files[i] != null){
                            picForm.empty();
                            picForm.append(files[i]);
                            //console.log(files[i].get(0).files[0]);
                            $("#fileFrom").form("submit",{
                                //type:"post",  //提交方式
                                url:"${emms}/instorage/materialManag.do?cmd=savePic", //请求url
                                onSubmit: function () {
                                    return $(this).form("validate");
                                },
                                success:function(data){ //提交成功的回调函数
                                    updateResult[n] = data;
                                    n++;
                                }
                            });
                        }
                    }
                    error:for(var i=0; i<updateResult.length; i++){
                         if(updateResult[i] != false){
                             $.messager.alert("提示","保存失败","error");
                               return;
                         }
                    }
                    $.messager.alert("操作提示","保存成功！","info",function(){
                        window.location = "${emms}/instorage/materialManag.do?cmd=queryDirect";
                    });
                },
                error:function(){
                    $.messager.alert("操作提示","保存失败！","error");
                }
            });
        }
    }

    //质检完成（提交）
    function ajaxCommit() {
        var materiaInspectStute = $("#materiaInspectStute").val();
        if(materiaInspectStute == null || materiaInspectStute == undefined || materiaInspectStute == ""){
            $.messager.alert("操作提示","请先保存！");
            return false;
        }
        var state = "billStaus_b";
        $("#tableM").edatagrid('endEdit', lastIndex);
        $.messager.confirm('确认对话框', '确定要提交当前记录吗？', function(result){
            if (result){
                save(state);//进行保存
            }
            return false;
        });
    }

    $('#supplierId').combobox({
        url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
        valueField: 'orgId',
        textField: 'orgName',
        multiple:false
    });


    //以下是上传图片的代码
    var lastIndex;
    var files = new Array();
    $("#tableT").datagrid({
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
            $('#tableT').edatagrid('endEdit', lastIndex);
            $('#tableT').edatagrid('endEdit', index);
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
            $('#tableT').datagrid('fixDetailRowHeight',index);
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
        var tempData = $('#tableT').datagrid('getData');
        var fileName = $(input).val();
        tempData.rows[$(input).attr("class")].fileName = fileName.split("\\")[fileName.split("\\").length-1];
        $('#table').datagrid('loadData', tempData);
    }
    //加入新行
    function addRow(){
        $('#tableT').datagrid('appendRow',{
            "fileName": null
        });
        $('#tableT').datagrid('loadData', $('#tableT').datagrid('getData'));
        $('#tableT').edatagrid('beginEdit',$('#tableT').datagrid('getData').total-1);
        var lastIndex = $('#tableT').datagrid('getData').total - 1;
        files[lastIndex] = null;
        $('.datagrid-row-expander').eq(lastIndex).click();
    }

    var updateResult = new Array();
    var n = 0;
    <%--function ajaxSubmitForm() {--%>
        <%--var picForm = $("#fileFrom");--%>
        <%--for(var i=0; i<files.length; i++){--%>
            <%--if(files[i] != null){--%>
                <%--picForm.empty();--%>
                <%--picForm.append(files[i]);--%>
                <%--console.log(files[i].get(0).files[0]);--%>
                <%--$("#fileFrom").ajaxSubmit({--%>
                    <%--type:"post",  //提交方式--%>
                    <%--async: false,//同步提交--%>
                    <%--url:"${emms}/instorage/materialManag.do?cmd=savePic", //请求url--%>
                    <%--success:function(data){ //提交成功的回调函数--%>
                        <%--updateResult[n] = data;--%>
                        <%--n++;--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
        <%--}--%>
        <%--for(var i=0; i<updateResult.length; i++){--%>
            <%--if(updateResult[i] != false){--%>
                <%--$.messager.alert("提示","上传失败");--%>
                <%--return;--%>
            <%--}--%>
        <%--}--%>
        <%--$.messager.alert("提示","上传成功");--%>
    <%--}--%>

</script>

<script language="VBScript">
    Function inputtext()
        Set WshShell = CreateObject("WScript.Shell")
        WshShell.SendKeys("d:\upPic.jpg")
        Set WshShell = Nothing
    End Function
</script>




</body>
</html>
