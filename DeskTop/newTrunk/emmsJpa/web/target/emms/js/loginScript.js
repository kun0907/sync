$("#username").blur(function(){
	if($("#username").val()==""){
		$("#fg_username").attr("class", "form-group has-error has-feedback");
	}else{
		$("#fg_username").attr("class", "form-group has-success has-feedback");
	}
});
$("#password").blur(function(){
	if($("#password").val()==""){
		$("#fg_password").attr("class", "form-group has-error has-feedback");
	}else{
		$("#fg_password").attr("class", "form-group has-success has-feedback");
	}
});
$("#checkLogin").click(function(){
	if($("#username").val()==""||$("#password").val()==""){
		return false;
	}
	$('#login').submit();
});