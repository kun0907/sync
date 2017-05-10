

function YanZheng(){
	var psw=$("#password").val();
	var psw2=$("#password2").val();
	if(psw2 != psw)
	{
	$("#span4").show();
	}else{
		$("#span4").hide();
	}

}
function pass(){
	var psw=$("#password").val();
	console.log(psw.length)
	if(psw==""){
		$("#span2").hide();
	}
	else if(psw.length <6 || false ==password(psw))
	{
		$("#span2").show();
	}else if(psw.length ==6 & true== password(psw))
	{
		$("#span2").hide();
		
	}	
	
}



function checkConfirm() {
	var username = $("#usernausermail").val();
	console.log(username)
	if(username == "" ){
		$("#nausermailfont1").hide();
		$("#nausermailfont").hide();
		$("#span").hide();
	}
	else if(username !="" &  true == check(username) )
	{
		var tl=check(username);
		console.log("第二章查询了数;")
		console.log(tl);
		$("#span").hide()
	$.ajax({
		data : {'username':username},
		type : "GET",
		dataType : "json",
		url : "/MLOAN/user/checking.do",
		error: function() {
			alert("服务器异常，请联系管理员！！！");
		},
		success : function(data1) {
			console.info(data1)
			if(data1.msg == "1"){
				$("#nausermailfont1").hide();
				$("#nausermailfont").show();
				$("#span").hide();
			}else{
				$("#nausermailfont1").show();
				$("#nausermailfont").hide();
				$("#span").hide();
			}
				
		}})
	}else if(username !="" & false ==check(username)){
		
		$("#span").show();
		$("#nausermailfont1").hide();
		$("#nausermailfont").hide();
		console.log("8888")
	}
	
	
	
}

//正则;用户的
function check(str){
     var t=/(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{3,15}/.test(str);
     return t;
    }

function password(str){
	return /^[0-9a-zA-Z]{6}$/.test(str);
}


function trim(str){
	 return str.replace(/(^\s*)|(\s*$)/g, "");
}

function usernameFocus(){
	  $("#usernameTip1").hide();
	  $("#usernameTip2").hide();
}

function emailFocus(){
	  $("#emailTip").hide();
}


function receiveVerifyCode(){
	 $("#verifyCode").attr("required","");
	 $("#p_verifyCode").show();
	 //往邮箱发送验证码
	 $.ajax({
			data : {'usermail':$("#usermail").val()},
			type : "GET",
			dataType : "json",
			url : "/MLOAN/email/receiveVerifyCode.do",
			error: function() {
				alert("服务器异常，请联系管理员！！！");
			},
			success : function(data) {
				  if(data=="0"){//发送成功
					  	 $("#btn_verifyCode").attr("disabled",true);
					  	 $("#btn_verifyCode").text("90秒后重试!");
	  					 
	  					 var seconds=90;
	  					 var interval1=window.setInterval(function(){
	  						 if(seconds<1){
	  							  window.clearInterval(interval1);
	  							  $("#btn_verifyCode").text("免费获取验证码");
	  							  $("#btn_verifyCode").attr("disabled",false);
	  						 }else{
	  							 $("#btn_verifyCode").text(seconds+"秒后重试!!");
		  						  seconds--;
	  						 }
	  					 }, 1000);
	  					 
	  					 alert("成功发送验证码，请登录邮箱查询并输入验证码(验证码有效期为90秒)!");
					     
				  }else{//发送失败，提示用户重新点击"发送验证码"
					  	  alert("发送验证码失败，请重新点击\"免费获取验证码\"!");
				  }
			}});
}


function usernameBlur(){
	  var username = $("#usernausermail").val();
	  username=trim(username);
	  if(username==""){
			return;
	  }
	  var usernamereg = /^[a-zA-Z][a-zA-Z0-9_]{3,11}$/;
	  if(!usernamereg.test(username)){//格式非法
		  $("#usernameTip2").show();
	  }else{
		  $.ajax({
	  			data : {'username':username},
	  			type : "GET",
	  			dataType : "json",
	  			url : "/MLOAN/user/checking.do",
	  			error: function() {
	  				alert("服务器异常，请联系管理员！！！");
	  			},
	  			success : function(data) {
	  				  if(data.user=="1"){//已被注册
		  					  $("#usernameTip1").css("color","red");
		  			    	  $("#usernameTip1").text("对不起，该用户名已被使用！");
		  			    	  $("#usernameTip1").show();
	  				  }else{
		  					  $("#usernameTip1").css("color","green");
		  			    	  $("#usernameTip1").text("恭喜您，该用户名可用！");
		  			    	  $("#usernameTip1").show();
	  				  }
	  			}});
	  }
	
}

function emailBlur() {
		var usermail = $("#usermail").val();
		usermail=trim(usermail);
		if(usermail==""){
			return;
		}
		//对电子邮件的验证
      var emaireg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if(!emaireg.test(usermail)){//格式非法
    	  $("#emailTip").css("color","red");
    	  $("#emailTip").text("邮箱格式非法！");
    	  //隐藏"接收验证码"按钮
    	  $("#btn_verifyCode").hide();
    	  $("#emailTip").show();
      }else{
    	  $.ajax({
	  			data : {'usermail':usermail},
	  			type : "GET",
	  			dataType : "json",
	  			url : "/MLOAN/user/checking.do",
	  			error: function() {
	  				alert("服务器异常，请联系管理员！！！");
	  			},
	  			success : function(data) {
	  				  if(data.mail=="1"){//已被注册
		  					  $("#emailTip").css("color","red");
		  			    	  $("#emailTip").text("对不起，该邮箱已被注册！");
		  			    	  $("#emailTip").show();
	  				  }else{
		  					  $("#emailTip").css("color","green");
		  			    	  $("#emailTip").text("恭喜你，该邮箱可以注册！");
		  			    	  $("#emailTip").show();
		  			    	  $("#btn_verifyCode").show();
	  				  }
	  			}});
      }
}



function registFormSubmit(){
	if((!$("#usernameTip1").is(":hidden")&&$("#usernameTip1").css("color")=="rgb(255, 0, 0)")||
	   (!$("#usernameTip2").is(":hidden")&&$("#usernameTip2").css("color")=="rgb(255, 0, 0)")||
	   (!$("#emailTip").is(":hidden")&&$("#emailTip").css("color")=="rgb(255, 0, 0)")||
	   (!$("#span2").is(":hidden")&&$("#span2").css("color")=="rgb(255, 0, 0)")||
	   (!$("#span4").is(":hidden")&&$("#span4").css("color")=="rgb(255, 0, 0)")){
				return false;
	}
	
	return true;
}
