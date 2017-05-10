<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="javax.servlet.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>建设工程项目物资管理平台</title>
        <meta charset="utf-8">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1"/>
        <link rel="stylesheet" href="${emms}/css/bootstrap/bootstrap.min.css">
        <style type="text/css">
            @font-face {
                font-family: 'Glyphicons Halflings';
                src: url('../fonts/glyphicons-halflings-regular.eot');
                src: url('../fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'), url('../fonts/glyphicons-halflings-regular.woff') format('woff'), url('../fonts/glyphicons-halflings-regular.ttf') format('truetype'), url('../fonts/glyphicons-halflings-regular.svg#glyphicons-halflingsregular') format('svg')
            }
        </style>
        <link rel="stylesheet" href="${emms}/css/main.css">
    </head>
    <body onload="checkParent();">
        <div class="container main" style="padding: 0px; margin: 0px;">
            <div class="row login_content">
                <div class="col-md-6 left_img">
                    <img src="${emms}/images/logoNew.png" alt="">
                </div>
                <div class="col-md-6 right_form">
                    <form class="form-horizontal" role="form" id="login" method="post" action="${emms}/jsp/loginSuccess.do">
                        <div class="form-group col-md-12">
                            <label for="userName" class="col-md-3 control-label"> 用户名：</label>
                                <span class="col-md-6">
                                    <input type="text" class="form-control col-md-12" id="username" name="username"
                                           id="userName">
                                </span>
                            <span class="col-md-3"></span>
                        </div>
                        <div class="form-group col-md-12">
                            <label for="password" class="col-md-3 control-label">密码：</label>
                                <span class="col-md-6">
                                    <input type="password" class="form-control col-md-12" id="password" name="password"
                                           id="password">
                                </span>
                        </div>
                        <c:if test="${not empty errorMessage}">
                            <div class="col col-md-4"></div>
                            <div class="col col-md-6" style="color: red">
                                <c:out value="${errorMessage}"/>
                            </div>
                        </c:if>
                        <div class="form-group col-md-12" id="checkdiv">
                            <button type="button" id="checkLogin" class="btn btn-primary col-md-6 col-md-offset-3 loginButton">
                                登录
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row login_footer">
                <div class="col col-md-12">
                    <p>管理和技术支持：中石化第四建设有限公司</p>

                    <p>电话：022-63863791</p>
                </div>
            </div>
        </div>
        <script src="${emms}/js/jquery.min.js" type="text/javascript"></script>
        <script src="${emms}/js/respond.js" type="text/javascript"></script>
        <script src="${emms}/js/loginScript.js" charset="UTF-8"></script>
        <script src="${emms}/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
        <script src="${emms}/js/placeholder.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).keydown(function (e) {
                if (e.keyCode == 13) {
                    $('#checkLogin').trigger("click");
                }
            });
            $('input').placeholder();
            function checkParent() {
                if (window.parent.length > 0) {
                    window.parent.location = "${emms}/login.jsp";
                }
                window.moveTo(0, 0);
                window.resizeTo(screen.availWidth, screen.availHeight);
            }
        </script>
    </body>
</html>
