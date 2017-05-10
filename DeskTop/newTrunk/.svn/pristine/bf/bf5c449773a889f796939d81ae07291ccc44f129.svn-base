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
     checkOldPwd : {
         validator : function(value, param) {
             var  userId= $("#userId").val();
             var haha = "";
             $.ajax({
                 type : 'get',
                 async : false,
                 url : '../system/user.do?cmd=checkOldPwd',
                 data : {
                     "userId" : userId,
                     "oldPwd" : value
                 },
                 success : function(data) {
                     haha = data;
                 }
             });
             return haha.indexOf("true");
         },
         message : '原密码错误'
     }
    
});