<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>日志查看</title>
   <%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>

<body onload="winload();">
<div class="container"> 
	<div class="row">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="label label-primary">
                    	<span class="glyphicon glyphicon-chevron-right"></span>日志管理
                    	<span class="glyphicon glyphicon-chevron-right"></span>日志查看
                    </span>
                </div>
                <form:form commandName="logForm" action="${emms}/log/query.do?" method="post" >
                	<table class="table table-condensed">
	                    <tr>
		                    <th class="text-center">日志名称</th>
		                    <td><form:input id="log_name" path="log_name" cssClass="form-control input-sm"/></td>
		                    <th class="text-center">日志内容</th>
		                    <td><form:input id="log_context" path="log_context" cssClass="form-control input-sm"/></td>
		                    <th class="text-center">操作人</th>
		                    <td><form:input id="log_user" path="log_user" cssClass="form-control input-sm"/></td>
	                     </tr>
                         <tr>
	                     	<th class="text-center">IP</th>
	                        <td><form:input id="log_IP" path="log_IP" cssClass="form-control input-sm"/></td>
	                        <th class="text-center">日志类型</th>
	                        <td>
	                        	<form:select id="log_type" path="log_type" class="form-control input-sm">
	                         		<option value="">请选择</option>
	                         		<option value="1">业务类型</option>
	                         		<option value="2">系统类型</option>
	                         	</form:select>
	                        </td>
	                        <th class="text-center">日志时间</th>
							<td>
                        		<div class="input-group" style="width:140px;">
									<form:input path="beginTime" cssClass="form-control input-sm form_datetime" readonly="true"/>
									<span class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" name="clearBegin">
											<span class="glyphicon glyphicon-remove"></span>
										</button> 
									</span>
								</div> 
                       			<div class="input-group" style="width:140px;margin-left: 10px;"> 
									<form:input path="endTime" cssClass="form-control input-sm form_datetime" readonly="true"/>
									<span class="input-group-btn">
										<button class="btn btn-primary btn-sm" type="button" name="clearEnd">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
									</span> 
								</div>	
							</td>                         
                       </tr>
						<tr>
						    <th colspan="6"  class="text-center">
						        <button type="submit" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-search"></span> 查询</button>
						        <button type="reset" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-remove"></span> 清空</button>
						    </th>
						</tr>
                	</table>
            	</form:form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
        	<div class="panel panel-default">
        		<div style="overflow-x: scroll;">
				<table class="table table-striped table-bordered table-condensed table-hover">
	                <tr>
	                   	<th>日志序号</th> 
	                    <th>日志名称</th>
	                    <th>日志类型</th>
	                    <th>日志内容</th>
	                    <th>日志时间</th>
	                    <th>IP</th>
	                    <th>操作人</th>
	                </tr>
					<c:forEach items="${list}" var="log">
					<tr>
	                    <td>${log.log_order}</td> 
	                    <td>${log.log_name}</td>
	                    <td>
	                    	<c:if test="${log.log_type eq 1}">业务类型</c:if>
	                    	<c:if test="${log.log_type eq 2}">系统类型</c:if>
	                    </td>
						<td>
                         	<c:set var="desc" value="${log.log_context }"/>
							<c:set var="desc2" value="${fn:substring(log.log_context,0,50)}" />	
							<a href="#" data-toggle="tooltip" title="${log.log_context }" data-original-title="">${desc2}...</a>			
						</td>	
                        <td>${log.log_date}</td>
                        <td>${log.log_IP}</td>
                        <td>${log.log_user}</td>
					</tr>
					</c:forEach>
				</table>
				</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4"> 
			<!------------- 分页开始 ------------->	
			<jsp:include page="../../common/page.jsp">  
				<jsp:param name="url" value="${pageUrl}" />      
			</jsp:include>  
			<!------------- 分页结束 -------------> 
        </div>
    </div>
</div>
<script src="${emms }/js/commonScript.js" type="text/javascript"></script>

</body>
</html>
