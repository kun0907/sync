<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>编辑页</title>
    <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
    <meta http-equiv="Content-Type" content="application/json;charset=utf-8">
</head>
<body>
<div class="easyui-panel" title="首页->采购管理->供应商发货管理->编辑" data-options="fit:true,border:false">
    <form id="ff" method="post">
        <input type="hidden" class="easyui-textbox" name="deliveryId" id="deliveryId" value="${deliveryId}"/>

        <div style="margin:20px">
            <input class="easyui-textbox" id="deliveryNo" name="deliveryNo" editable="false" style="width:20%"
                   data-options="label:'发货单号:'">
            <input class="easyui-combobox" id="deliveryState" readonly="true" name="deliveryState" style="width:20%"
                   data-options="label:'单据状态:'">
            <input class="easyui-datebox date_field" id="expectedArrivalDate" readonly="true" name="expectedArrivalDate"
                   style="width:20%" data-options="label:'预到货时间:'">
            <input class="easyui-combobox" id="supplier" readonly="true" style="width:20%" data-options="label:'供应商:'">
        </div>
        <div style="text-left: center;width:90%">
            <a href="${emms}/purchase/supplierDelivery.do?cmd=query" iconCls='icon-back'
               class="easyui-linkbutton">返回</a>
        </div>
    </form>
        <div class="easyui-layout" style="width:100%;height: 100%">
            <div data-options="region:'west',split:true" style="width:50%;">
                <div class="easyui-panel" title="包装发货明细" data-options="fit:true,border:false">
                    <table title="包装明细" class="easyui-treegrid" id="table"></table>
                </div>
            </div>
            <div data-options="region:'center'" title="包装物料列表">
                <table id="detail" auto-resize="true" class="easyui-datagrid" width="100%"></table>
            </div>
            <div data-options="region:'south',split:true" style="width:100%;height: 500px">
                <div class="easyui-panel" title="运输明细" data-options="fit:true,border:false">
                    <table id="vehicle" auto-resize="true" class="easyui-datagrid" title="运输明细列表" width="100%"></table>
                </div>
            </div>
        </div>

</div>
<script type="text/javascript">
    //缓存数据字典
    var dictionary = {};
    //页面包裹物料数据存放
    var packageDetail = [];
    //公共方法
    //前端生成uuid
    function uuid() {
        var s = [];
        var hexDigits = "0123456789abcdef";
        for (var i = 0; i < 36; i++) {
            s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
        }
        s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
        s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
        s[8] = s[13] = s[18] = s[23] = "";
        var uuid = s.join("");
        return uuid;
    }
    //treegrid 页面转换
    function convert(rows){
        function exists(rows, parentId){
            for(var i=0; i<rows.length; i++){
                if (rows[i].id == parentId) return true;
            }
            return false;
        }
        var nodes = [];
        // get the top level nodes
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (!exists(rows, row._parentId)){
                nodes.push({
                    id:row.id,
                    packingNo:row.packingNo,
                    packingWeight:row.packingWeight,
                    packingType:row.packingType,
                    packingSize:row.packingSize
                });
            }
        }
        var toDo = [];
        for(var i=0; i<nodes.length; i++){
            toDo.push(nodes[i]);
        }
        while(toDo.length){
            var node = toDo.shift();	// the parent node
            // get the children nodes
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                if (row._parentId == node.id){
                    var child = {id:row.id,packingNo:row.packingNo,packingWeight:row.packingWeight,packingType:row.packingType,
                        packingSize:row.packingSize};
                    if (node.children){
                        node.children.push(child);
                    } else {
                        node.children = [child];
                    }
                    toDo.push(child);
                }
            }
        }
        return nodes;
    }
    //数据字典code与名称的转换
    function getValueByKey(key){
        return dictionary[key];
    }
    //初始化页面
    $('#supplier').combobox({
        url: '${emms}/system/organization.do?cmd=selectOrgByType&orgTypeCode=supplier',
        valueField: 'orgId',
        textField: 'orgName',
        multiple:false
    });
    $('#deliveryState').combobox({
        url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=delivery_state',
        valueField: 'dictionaryCode',
        textField: 'dictionaryName'
    });
    $(function(){
        //供应商下拉框加载

        var supplierId = '${supplierId}';
        if(null != supplierId && supplierId != ''){
            $('#supplier').combobox('readonly',true);
            $('#supplier').combobox('select',supplierId);
        }

        $("#deliveryState").combobox('disable');
        $('#ff').form('load', '${emms}/purchase/supplierDelivery.do?cmd=editDelivery&deliveryId=${deliveryId}');
        $('#ff').form({
            onLoadSuccess:function(data){
                $('#supplier').combobox('select',data.supplierId);
            }
        });
        /**
         *数据字典
         */
            //包装类别
        $.ajax({
            url:'${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=package_type',
            dataType : 'json',
            type : 'POST',
            async:false,
            success: function (data){
                for(var i=0;i<data.length;i++){
                    var obj=data[i].dictionaryCode;
                    dictionary[obj]=data[i].dictionaryName;
                }
                packageTypeList = data;
            }
        });
        //运输方式
        $.ajax({
            url:'${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=transport_mode',
            dataType : 'json',
            type : 'POST',
            async:false,
            success: function (data){
                for(var i=0;i<data.length;i++){
                    var obj=data[i].dictionaryCode;
                    dictionary[obj]=data[i].dictionaryName;
                }
                packageTypeList = data;
            }
        });
        /**
         *各个列表加载数据
         */

            //加载包裹treegrid数据
        $('#table').treegrid({
            loadFilter: function(data,parentId){
                if(data.length == 0){
                    data.push({"id":'0', "packingNo":"结构树型图"});
                }
                return convert(data);
            },
            onClickRow:function(row){
                var checkDetail = [];
                for(var i=packageDetail.length-1;i>=0;i--){
                    if(packageDetail[i].packingId == row.id){
                        checkDetail.push(packageDetail[i]);
                        packageDetail.splice(i,1);
                    }
                }
                $('#detail').datagrid('loadData', checkDetail);
            },onDblClickRow:function(row){//运用双击事件实现对一行的编辑
                $('#table').treegrid('beginEdit', row.id);
            },
            animate: true,
            collapsible: true,
            fitColumns: false,
            url:'${emms}/purchase/delivery/package.do?cmd=queryPackage&deliveryId=${deliveryId}',
            idField:'id',
            treeField:'packingNo',
            onContextMenu:onContextMenu,
            singleSelect:true,
            columns:[[
                {field:'packingNo',title:'包装编号',align:'center',width:'25%',editor:'text'},
                {field: 'packingType', title: '包装形式', align: 'center', width: '25%',
                    formatter: function(value,row,index){
                        return getValueByKey(value);
                    },
                    editor: {
                        type: 'combobox',
                        options: {
                            valueField: 'dictionaryCode',
                            textField: 'dictionaryName',
                            data: packageTypeList,
                            editable: false
                        }
                    }
                },
                {field:'packingWeight',title:'包装重量(吨)',align:'center',width:'25%',editor:'numberbox'},
                {field:'packingSize',title:'包装尺寸',align:'center',width:'25%',editor:'numberbox'}
            ]]
        });
        //发货单包装明细
        $('#detail').datagrid({
            url:'${emms}/purchase/delivery/packageDetail.do?cmd=queryPackageByDelivery&deliveryId=${deliveryId}',
            method: 'POST',
            fitColumns: true,
            showFooter: true,
            rownumbers: true,
            singleSelect:true,
            onDblClickRow:function(row){//运用双击事件实现对一行的编辑
                $('#detail').datagrid('beginEdit', row);
            },
            onClickRow:function(row) {//运用单击事件实现一行的编辑结束，在该事件触发前会先执行onAfterEdit事件
            },
            //数据加载成功时需要返回数据缓存
            onLoadSuccess: function (data) {
                for (var i = 0; i < data.total; i++) {
                    packageDetail.push(data.rows[i]);
                }
            },
            columns:[[
                {field:'docSourceNo',title:'采购订单编号',align:'center',width:'11%'},
                {field:'materialsCode',title:'物资编码',align:'center',width:'11%'},
                {field:'wbsCode',title:'WBS',align:'center',width:'11%'},
                {field:'deMainUnit',title:'计量单位',align:'center',width:'11%'},
                {field:'purchaseNum',title:'采购数量',align:'center',width:'11%'},
                {field:'deliveryedNum',title:'已发货数量',align:'center',width:'11%'},
                {field:'deliveryMainCount',title:'预发货数量',align:'center',width:'11%',
                    editor:{type:'numberbox',
                        options:{
                            precision:4
                        }
                    }
                },
                {field:'productionDate',title:'生产日期',align:'center',width:'11%',editor:{type:'datebox'}},
                {field:'bzq',title:'保质期',align:'center',width:'11%',editor:{type:'datebox'}}
            ]]
        });
        //车辆明细信息
        $('#vehicle').datagrid({
            url: '${emms}/purchase/delivery/vehicle.do?cmd=queryVehicleData&deliveryId=${deliveryId}',
            method: 'POST',
            fitColumns: true,
            rownumbers: true,
            showFooter: true,
            singleSelect: false,
            columns: [[
                {field: 'transportMode', title: '运输方式', align: 'center', width: '25%',
                    formatter: function(value,row,index){
                        return getValueByKey(value);
                    },
                    editor:{
                        type:'combobox',
                        options:{
                            valueField:'dictionaryCode',
                            textField:'dictionaryName',
                            data:packageTypeList,
                            required:true,
                            editable:false
                        }
                    }
                },
                {field: 'plateNumber', title: '牌号', align: 'center', width: '25%',editor:'text'},
                {field: 'chargePersonName', title: '联系人姓名', align: 'center', width: '25%',editor:'text'},
                {field: 'chargePersonPhone', title: '联系人电话', align: 'center', width: '25%',editor:{
                    type:'text',
                    options:{
                        validType:'mobile'
                    }
                }}
            ]]
        });
    });


    //包装上的操作
    //包装单击右键时触发事件
    function onContextMenu(e,row){
        e.preventDefault();
        $(this).treegrid('select', row.id);
        $('#mm').menu('show',{
            left: e.pageX,
            top: e.pageY
        });
    }
    //包装增加信息
    function append(){
        var node = $('#table').treegrid('getSelected');
        if(node.packingType == 'parts'){
            $.messager.alert("操作提示", "包装类型为散件的不可再建子包装！","warning");
            return ;
        }
        $('#table').treegrid('append',{
            parent: node.id,
            data: [{
                id:uuid(),
                packingNo: '',
                packingType:'',
                packingWeight: '0',
                packingSize: '0'
            }]
        })
    }
    //移除方法
    function removeIt(){
        var node = $('#table').treegrid('getSelected');
        if(node.id == '0'){
            $.messager.alert("操作提示","此节点为预置节点,不可删除","warning");
            return
        }else{
            if (node){
                $('#table').treegrid('remove', node.id);
            }
        }
    }
    //新建物料
    function newMaterials(){
        var selectRow = $('#table').datagrid("getSelected");
        if($("#supplier").combobox('getValue') == '' ){
            $.messager.alert("操作提示","请选择供应商信息","warning");
            return;
        }
        $('#table').treegrid('endEdit', lastIndex);
        if(selectRow == null || selectRow.size == 0 || selectRow.id == '0'){
            $.messager.alert("操作提示","请选择包装信息","warning");
            return;
        }
        if (null == selectRow.packingNo || selectRow.packingNo == '') {
            $.messager.alert("操作提示", "包装编号不能为空", "warning");
            return false;
        }
        if (null == selectRow.packingType || selectRow.packingType == '') {
            $.messager.alert("操作提示", "包装形式不能为空", "warning");
            return false;
        }
        if(selectRow.size > 1){
            $.messager.alert("操作提示", "请选择一条包装信息","warning");
            return;
        }
        if(typeof (selectRow.children) != 'undefined'){
            if(selectRow.children.length > 0){
                $.messager.alert("操作提示","请选择末节点包装信息添加物料","warning");
                return;
            }
        }
        //添加供应商发货校验过后弹出框
        var supplierId = $('#supplier').combobox('getValue')
        top.$('#dialog').dialog({
            title: '公共采购订单明细选择弹出框',
            width: 900,
            height: 540,
            closed: false,
            href: '${emms}/purchase/order.do?cmd=dialogOrderDetail&supplierId='+supplierId
        });
    }
    //删除物料
    function delMaterials(){
        var rows = $('#detail').datagrid("getSelections");
        for (var i = rows.length - 1; i >= 0; i--) {
            var index = $('#detail').datagrid('getRowIndex',rows[i]);
            $('#detail').datagrid('deleteRow', index);
        }
    }
    //弹出框回调函数
    function checkOrderDetail(rows){
        $("supplierId").combobox("readonly",true);
        for(var i=0;i<rows.length;i++){
            $('#detail').datagrid('appendRow',{
                "packingId":$('#table').treegrid('getSelected').id,
                "wbsId":rows[i].wbsId,
                "wbsCode":rows[i].wbsCode,
                "deliveryMainCount":0,
                "deMainUnit":rows[i].orderDetailUnit,
                "purchaseNum":rows[i].orderDetailCount,
                "supplierId":rows[i].supplierId,
                "supplierName":rows[i].supplierName,
                "materialsId": rows[i].materialsId,
                "materialsCode": rows[i].materialsCode,
                "docSourceNo" : rows[i].orderCode,
                "docSourceDetailRowno" : rows[i].orderCode,
                "docSourceDetailId":rows[i].orderDetailId,
                "docSourceType":'purchase'
            });
        }
    }
    //新增车辆信息
    function newVehicleInfo(){
        $("#table").datagrid('endEdit', lastIndex);
        $('#vehicle').datagrid('appendRow', {
            "deVehicleId":uuid()
        });
        $("#vehicle").datagrid('endEdit', num);
    }
    //删除车辆信息
    function delVehicleInfo(){
        var rows = $('#vehicle').datagrid("getSelections");
        for (var i = rows.length - 1; i >= 0; i--) {
            var index = $('#vehicle').datagrid('getRowIndex',rows[i]);
            $('#vehicle').datagrid('deleteRow', index);
        }
    }
    //页面统一保存方法
    function ajaxSubmitForm() {
        //获取包装数据并转换为json
        var packageInfo = [];
        var package = $("#table").treegrid("getChildren");
        //前台校验
        for (var i = 0; i < packageInfo.length; i++) {
            if (null == packageInfo[i].packingNo || packageInfo[i].packingNo == '') {
                $.messager.alert("操作提示", "第" + (i + 1) + "行包装明细中:包装编号不能为空", "warning");
                return false;
            }
            if (null == packageInfo[i].packingType || packageInfo[i].packingType == '') {
                $.messager.alert("操作提示", "第" + (i + 1) + "行包装明细中:包装形式不能为空", "warning");
                return false;
            }
        }
        var packNo = '';
        //获取车辆数据并转换为json
        var vehicleInfo =  $("#vehicle").datagrid("getData");
        if(vehicleInfo.rows.length == 0){
            $.messager.alert("操作提示","请填写运输明细！");
            return false;
        }
        for(var i=0;i<package.length;i++){
            if(package[i].id !='0'){
                delete package[i].state;
                delete package[i].children;
                packageInfo.push(package[i]);
            }
        }
        //当前包裹下的明细
        for(var i=0;i<$("#detail").datagrid("getData").rows.length;i++){
            packageDetail.push($("#detail").datagrid("getData").rows[i]);
            delete $("#detail").datagrid("getData").rows[i].purchaseNum;
        }
        //组装发货单主表信息
        var delivery = {
            "deliveryId":$("#deliveryId").textbox("getValue"),
            "supplierId":$('#supplier').combobox('getValue'),
            "supplierName":$('#supplier').combobox('getText'),
            "expectedArrivalDate":$("#expectedArrivalDate").val(),
            "deliveryPackingList":packageInfo,
            "deliveryVehicleList":vehicleInfo.rows,
            "packageDetail":packageDetail
        };
        if($("#ff").form("validate")){
            $.ajax({
                type: 'POST',
                url: "${emms}/purchase/supplierDelivery.do?cmd=saveDelivery",
                data: JSON.stringify(delivery),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                success: function (result) {
                    if(result == 'true'){
                        $.messager.alert("操作提示", "发货单保存成功！","info",function(){
                            window.location = '${emms}/purchase/supplierDelivery.do?cmd=query';
                        });
                    }else{
                        $.messager.alert("操作提示", result,"warning");
                    }
                }
            });
        }
    }
</script>
</body>
</html>
