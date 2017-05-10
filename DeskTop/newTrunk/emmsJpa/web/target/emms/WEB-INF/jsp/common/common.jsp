<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<!-- easyUI css -->
<link rel="stylesheet" href="${emms}/css/easyUI/demo.css" />
<link rel="stylesheet" type="text/css" href="${emms}/css/easyUI/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${emms}/css/easyUI/themes/icon.css">
<!-- jquery 双向选择 css -->
<link rel="stylesheet" type="text/css" href="${emms}/css/jqueryMultiSelect/multi-select.css">

<!-- jquery 库 -->
<script src="${emms}/js/jquery.js" type="text/javascript"></script>
<!-- easy UI js -->
<script src="${emms}/js/easyUI/jquery.easyui.min.js" type="text/javascript" ></script>
<!-- easy UI提示框的国际化 -->
<script src="${emms}/js/easyUI/easyui-lang-zh_CN.js" type="text/javascript" ></script>
<!--可编辑的datagrid -->
<script src="${emms}/js/easyUI/jquery.edatagrid.js" type="text/javascript" ></script>
<!--可分组的datagrid -->
<script src="${emms}/js/easyUI/datagrid-groupview.js" type="text/javascript" ></script>
<!--可显示详细信息的datagrid -->
<script src="${emms}/js/easyUI/datagrid-detailview.js" type="text/javascript" ></script>


<!--[if lt IE 9]>
<script src="${emms}/js/json2.js"></script>
<![endif]-->

<!-- jquery 双向选择 js -->
<script src="${emms}/js/jqueryMultiSelect/jquery.multi-select.js" type="text/javascript" ></script>
<!-- dialog 弹出框 -->
<div id="dialog" data-options="resizable:false,modal:true"></div>

<script type="text/javascript">

    $(document).ready(function () {
        $(".textbox-label").css("text-align","right");
        $.ajaxSetup({
            beforeSend: function () {
                $.messager.progress({
                    msg: '正在加载，请稍候……'
                });
            },
            complete: function () {
                $.messager.progress('close');
            }
        });
        /**
         * 自定义关于form表单的校验
         * @author WANGQ
         */
        $.extend($.fn.validatebox.defaults.rules, {
            checkString: {//输入数字、字母、中划线
                validator: function (value) {
                    return /^[a-zA-Z0-9-]*$/i.test(value);
                },
                message: '请输入数字、字母、中划线'
            },
            intOrFloat: {// 验证整数或小数
                validator: function (value) {
                    return /^\d+(\.\d+)?$/i.test(value);
                },
                message: '请输入数字，并确保格式正确'
            },
            mobile: {// 验证手机号码
                validator: function (value) {
                    return /^(13|15|18)\d{9}$/i.test(value);
                },
                message: '手机号码格式不正确'
            },
            fax: {// 验证传真
                validator: function (value) {
                    //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
                    return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
                },
                message: '传真号码不正确'
            },
            equalTo: {
                validator: function (value, param) {
                    return value == $(param[0]).val();
                },
                message: '新密码与确认密码不一致'
            },
            compareDate: {
                validator: function (value, param) {
                    return dateCompare($(param[0]).datetimebox('getValue'), value); //注意easyui 时间控制获取值的方式
                },
                message: '开始日期不能大于结束日期'
            },
            checkOldPwd: {
                validator: function (value, param) {
                    var userId = $("#userId").val();
                    var haha = "";
                    $.ajax({
                        type: 'get',
                        async: false,
                        url: '${emms}/system/user.do?cmd=checkOldPwd',
                        data: {
                            "userId": userId,
                            "oldPwd": value
                        },
                        success: function (data) {
                            haha = data;
                        }
                    });
                    return !haha;
                },
                message: '原密码错误'
            }

        });

    });

</script>
<style>
    a{
        color:#0000EE;
        text-decoration:none
    }
</style>