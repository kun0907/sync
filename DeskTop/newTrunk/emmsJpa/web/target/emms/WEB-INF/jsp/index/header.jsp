<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>系统导航</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style type="text/css">
        body{
            background-image: url("${emms}/images/banner.png");
            /*background-size: contain;*/
            background-size: 100% 78px;
            width: 100%;
            padding: 1px;
            overflow: scroll;
            overflow-y: hidden;
            background-repeat:no-repeat;
        }
    </style>
</head>
    <body>
        <div style="color: #ffffff;width: 100%;">
            <div style="width:42%;float:left;height:60px;">
                <div style="padding: 5px;">
                    <span style="background-size:100%;"><img src="${emms}/images/logo.png" alt="logo"></span>
                    <span style="font-size: 20px;margin-top: 27px;padding: 5px;position: absolute"><B>建设工程物资管理平台</B></span>
                </div>
            </div>

            <div style="width:20%;float:left;height:30px;margin-top: 45px">
               <span onclick="changePwd();" style="margin-right: 20%">
                   <img src="${emms}/images/5.png" alt="设置"><a href="#" style="color: #FFFFFF;font-size: 14px">设置</a>
               </span>
               <span onClick="top.location.replace('${emms}/logout.do')" >
                   <img src="${emms}/images/5.png" alt="退出"><a href="#" style="color: #FFFFFF;font-size: 14px">退出</a>
               </span>
            </div>

            <div style="color: #000000;float:left;height:60px;font-size: 14px">
                <apan style="margin-top: 45px;position: absolute">欢迎
                    <c:if test="${empty user.employee}">
                        ${user.userName} 登录本系统&nbsp;&nbsp;您属于系统管理员
                    </c:if>
                    <c:if test="${not empty user.employee}">
                        ${user.employee.empName} 登录本系统&nbsp;&nbsp;您属于${user.employee.rootOrganization.orgName}
                    </c:if>
                </apan>
            </div>

        </div>
        <%--<div class="content">
            <div class="nav">
                <span class="amend"  onclick="changePwd();"><i class="icon_set"></i><a href="#">设置</a></span>
                <span class="quit" onClick="top.location.replace('${emms}/logout.do')"><i class="icon_exit"></i><a href="#">退出</a></span>
                <span class="help"><i class="icon_help"></i><a href="#">使用帮助</a></span>
                        <span class="com">欢迎&nbsp;
                            <c:if test="${empty user.employee}">
                                ${user.userName}
                            </c:if>
                            <c:if test="${not empty user.employee}">
                                ${user.employee.empName}
                            </c:if>
                             登录本系统  &nbsp; &nbsp; 您属于${user.employee.organization.orgTypeName}</span>
            </div>
        </div>--%>
        <script type="text/javascript">
            function changePwd(){
                top.$('#dialog').dialog({
                    title: '修改密码',
                    width: 700,
                    height: 300,
                    closed: false,
                    cache: true,
                    href: '${emms}/system/user.do?cmd=changeUser&userId=${user.userId}'
                });
            }
        </script>
    </body>
</html>
