/**
 * Created by YANLP on 2016/12/5.
 */
$(".collapse").collapse();
$(function(){
    var username = window.location.search.split("=")[1];
    $(".username").html(username);
});
////改变手风琴样式active
$("ul.collapse").delegate("li","click",function(){
    $(this).addClass("active").siblings().removeClass("active");
    $(this).next().show().siblings('ul').hide();
});


