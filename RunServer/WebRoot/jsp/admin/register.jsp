<%@ page pageEncoding="utf-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户注册</title>
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/design/css/login/demo.css" />
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/design/css/login/style.css" />
	  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/design/css/login/animate-custom.css" /> 
	  <script src="<%=request.getContextPath()%>/design/js/off/jquery-1.6.2.min.js"></script>	   
	  <script src="<%=request.getContextPath()%>/design/js/admin/register.js"></script>	
<style type="text/css">
		body{
			 margin:0px;
			 padding:0px;
			 font-family:Arial;
			 font-size:12px;
			 padding:10px;
		}
		
		#emailTipDiv, .newemail, .newemailtitle{ 
			 cursor:default;
			 line-height:18px;
		}
		
		input,img{
			vertical-align:middle;
		}
		
		.newemail{
			cursor: pointer;
		}
		
		.suggest-container{border:1px solid #C1C1C1;visibility:hidden;z-index: 1000;margin-left: 110px;background-color: white;cursor: pointer;}
		.suggest-item{padding:3px 2px;}
		.suggest-active {background:#33CCFF;color:white;padding:3px 2px;}	
	
</style>
</head>
</head>
<body>

<!-- todo 提交时做一下判断有错误不能提交(不只是系统插件检测非空:required，才不能提交)，-->
	<div class="container">
    	<div id="container_demo" >
	        <div id="wrapper">
            	<div id="login" class="animate form" style="padding-bottom: 0px;">
	            	<form  action="<%=request.getContextPath()%>/user/register.do"  method="post"  > 
	                    <h1 style="font-size: 30px;">注册信息</h1>
	                    <span id="span_msg" style="color: red">${msg}</span>
	                    
	                    <p id="P1"> 
	                        <label for="usernausermail" class="unausermail" data-icon="u"  >
	                        	<b style="color: red;">*</b>
	                        	用户名   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        	<span id="usernameTip1" hidden="hidden"></span>
	                        </label>
	                        <input id="usernausermail" name="username" 
	                        	   placeholder="用户名必须是以字母开头的字母数字的4-12位组合!" maxlength="12"  minlength="4"
	                        	   required  type="text" value=""
	                        	   onblur="usernameBlur()"
	                        	   onfocus="usernameFocus()"/>
	                        <span id="usernameTip2" style="color:red;" hidden="hidden">用户名必须是以字母开头的字母数字的4-12位组合! </span>
	                    </p>
	                    <p> 
	                        <label for="usermail" class="usermail" data-icon="u" >
	                        	<b style="color: red">*</b> 
	                        	绑定邮箱 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        	<span id="emailTip"  hidden="hidden"></span>
	                        </label>
	                        <input id="usermail" name="usermail"  
	                        	   placeholder="请输入您要绑定的邮箱..."  
	                        	   required type="text"  
	                        	   onfocus="emailFocus()"  
	                        	   onblur="emailBlur()" value=""/><br/>
	                        <button id="btn_verifyCode" type="button"  hidden="hidden"  onclick="receiveVerifyCode()" style="float: right;">免费获取验证码</button>
	                    </p>
	                    
	                     <p  id="p_verifyCode" hidden="hidden"> 
	                        <label for="verifyCode" class="verifyCode" data-icon="u" >
	                        	<b style="color: red">*</b> 
	                        	验证码 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                        </label>
	                        <input id="verifyCode" name="verifyCode"  
	                        	   placeholder="请输入邮箱中收到的6位数字验证码..."  
	                        	   type="text"   
	                        	   value=""/>
	                    </p>
	                    
	                    <p> 
	                        <label for="password" class="youpasswd" data-icon="p">
	                        	<b style="color: red;">*</b> 密码
	                        </label>
	                        <input id="password" name="password" placeholder="请设置密码.使用字母、数字两种组合最多6个字符" maxlength="6" required="required" type="password" ONBLUR="pass()" value="" /> 
	                        <span id="span2"   style="color:red;" hidden="hidden">必须是  数字,字母 ,字母数字的组合 6个字符</span>
	                    </p>
	                    <p> 
	                        <label for="password" class="youpasswd" data-icon="p">
	                        	<b style="color: red;">*</b> 确认密码 
	                        </label>
	                        <input id="password2" name="password2" placeholder="请再次输入密码..." required="required" maxlength="6" type="password" value="" oninput="YanZheng()" /> 
	                        <span id="span4" style="color:red;" hidden="hidden"> 两次输入密码不正确:</span>
	                    </p>
	                    <p class="login button" style="text-align: -webkit-center;"> 
                            <input type="submit" value="提交" onclick="return registFormSubmit();"/>
						</p>
						<p style="text-align: -webkit-right;">
							已有账户？&nbsp;&nbsp;
	                       	<a href="<%=request.getContextPath()%>/jsp/admin/login.jsp" style="margin-top: 20px;font-size: 25px;text-decoration: initial;">直接登录</a>
						</p>
                  	</form>
                </div>
            </div>
        </div>  
      </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/design/js/common/inputSuggest_0.1.js"></script>
<script type="text/javascript">
	window.onload = function(){		
		
		window.setTimeout(function(){
			$("#span_msg").remove();
		}, 5000);
		
		
		var emailSuggest = new InputSuggest({
			input: document.getElementById('usermail'),
			data: ['163.com','126.com','qq.com','sina.com','mail.com','yahoo.com']
		});
		
	}

</script>
</html>