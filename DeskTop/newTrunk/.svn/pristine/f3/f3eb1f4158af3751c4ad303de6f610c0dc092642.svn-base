<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>列表页</title>
	<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
</head>
<body  style="padding: 0px;">
	<div class="easyui-tabs" id="tab">
        <c:if test="${parentId ne '0'}">
            <div title="本级工程(WBS)">
                <div class="easyui-panel" title="首页->系统管理->主项目管理->查看" data-options="fit:true,border:false">
                    <form id="self" method="post">
                        <div style="padding:10px">
                            <input class="easyui-textbox" name="projectCode" editable="false" style="width:40%" data-options="label:'项目编号:',disabled:true">
                            <input class="easyui-textbox" name="projectName" editable="false" style="width:40%" data-options="label:'项目名称:',disabled:true">
                        </div>
                        <div style="padding:10px">
                            <input class="easyui-textbox" name="owner" editable="false" style="width:40%" data-options="label:'业主:',disabled:true">
                            <input class="easyui-combobox" id="projectTypeView" editable="false" name="projectType" style="width:40%" data-options="label:'项目类型:',disabled:true">
                        </div>
                        <div style="padding:10px">
                            <input class="easyui-textbox" id="orgName" style="width:40%;height:100px" data-options="label:'供应商:',multiline:true,disabled:true" >
                            <input class="easyui-textbox" id="construct" name="construct" style="width:40%;height:100px" data-options="label:'施工单位:',multiline:true,disabled:true" >
                        </div>
                        <div style="padding:10px">
                            <input class="easyui-textbox" id="manager" name="manager" style="width:40%;height:100px" data-options="label:'监理公司:',multiline:true,disabled:true" >
                            <input class="easyui-textbox" id="supervision" name="supervision" style="width:40%;height:100px" data-options="label:'建设单位:',multiline:true,disabled:true" >
                        </div>
                        <div style="padding:10px">
                            <input class="easyui-textbox" id="design" name="design" style="width:40%;height:100px" data-options="label:'设计院:',multiline:true,disabled:true" >
                        </div>
                        <div style="text-align: center;">
                            <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls='icon-save' onclick="editThisProject('${parentId}')">编辑</a>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <div title="下级工程(WBS)">
            <div class="easyui-panel"  title="首页->基本信息管理->工程(WBS)编码管理->下级工程(WBS)查询" data-options="fit:true,border:false">
                <form id="query" method="post">
                    <div style="margin:20px">
                        <input class="easyui-textbox" id="pCode"  style="width:30%" data-options="label:'项目编码:'">
                        <input class="easyui-textbox" id="pName" style="width:30%" data-options="label:'项目名称:'">
                        <select class="easyui-combobox" id="projectState" style="width:30%" data-options="label:'状态:'"></select>
                    </div>
                    <div style="text-align: center;">
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-search' onclick="query()">查询</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-reload' onclick="clearForm()">重置</a>
                        <c:if test="${parentId ne '0'}">
                            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls='icon-add' onclick="addProject()">新建</a>
                        </c:if>
                    </div>
                </form>
                <table id="table" auto-resize="true" class="easyui-datagrid" title="项目查询列表" width="100%" style="height:90%"></table>
            </div>
        </div>
        <c:if test="${parentId eq '0'}">
            <div title="新建主项目">
                <div class="easyui-panel" title="首页->基本信息管理->主项目管理->新建" data-options="fit:true,border:false">
                    <form id="ff" method="post">
                        <input class="easyui-textbox" type="hidden" id="isMain" name="isMain" value="0"/>
                        <input class="easyui-textbox" type="hidden" id="parentId" name="parentId" value="${parentId}"/>
                        <div style="margin:20px">
                            <input class="easyui-textbox" name="projectCode" style="width:40%" data-options="label:'项目编号:',required:true,
                                validType:{
                                    length:[1,100],
                                    remote:['${emms}/baseinfo/project.do?cmd=checkProjectCode','projectCode']
                                }
						   ">
                            <input class="easyui-textbox" name="projectName" style="width:40%" data-options="label:'项目名称:',required:true">
                        </div>
                        <div style="margin:20px">
                            <input class="easyui-combobox" id="projectType" editable="false" name="projectType" style="width:40%" data-options="label:'项目类型:'">
                        </div>
                        <div style="text-align: center;">
                            <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls='icon-save' onclick="ajaxSubmitForm()">保存</a>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
	</div>
	<script type="text/javascript">
        $('#projectState').combobox({
            url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=wbsstate',
            valueField: 'dictionaryCode',
            textField: 'dictionaryName',
            multiple:false
        });
        $('#projectType,#projectTypeView').combobox({
            url: '${emms}/system/businessDictionary.do?cmd=loadDicByCode&dicCode=project_type',
            valueField: 'dictionaryCode',
            textField: 'dictionaryName',
            multiple:false
        });
        $(function(){
            $('#tab').tabs({
                height: "900px"
            });
            $(".panel-body").css("height","100%");
            query();
            loadself();
        });
        function loadself(){
            $('#self').form('load', '${emms}/system/mainProject.do?cmd=loadProject&projectId=${parentId}');
            $('#self').form({
                onLoadSuccess:function(data){
                    console.log(data);
                    if(null != data.orgName){
                        $("#orgName").textbox("setValue",data.orgName+"\n");
                    }
                }
            });
        }
        function query(){
            $('#table').datagrid({
                url:'${emms}/baseinfo/project.do?cmd=selectProject&parentId=${parentId}',
                method: 'POST',
                pagination: false,
                fitColumns: true,
                rownumbers: true,
                showFooter: true,
                singleSelect:true,
                  queryParams: {
                    "projectCode" : $("#pCode").val(),
                    "projectName" : $("#pName").val(),
                    "projectState" : $('#projectState').combobox('getValue')
                },
                columns:[[
                    {field:'PARENTNAME',sortable:true,title:'上级项目名称',align:'center',width:'25%'},
                    {field:'PROJECT_CODE_SEQ',sortable:true,title:'项目编码',align:'center',width:'25%',
                        formatter: function(value, row, index){
                            if(null != value){
                                return "<a style='color:blue' href=\"javascript:editProject(\'"+ row.PROJECT_ID + "\')\">"+value+"</a>";
                            }else{
                                return '';
                            }
                        }},
                    {field:'PROJECT_NAME',sortable:true,title:'项目名称',align:'center',width:'20%'},
                    {field:'PROJECT_STATE',sortable:true,title:'状态',align:'center',width:'17%'},
                    {field:'aaa',title:'操作',sortable:true,align:'center',width:'15%',
                        formatter: function(value,row,index){
                            var show = '';
                            if(row.PROJECT_STATE=='未提交'){
                                show += "<a class='easyui-linkbutton'  href=\"javascript:editProject(\'"+ row.PROJECT_ID + "\')\" target='_self'>修改</a>&nbsp;&nbsp;&nbsp;";
                                show += "<a class='easyui-linkbutton'  href=\"javascript:startProject(\'"+ row.PROJECT_ID + "\')\" target='_self'>提交</a>&nbsp;&nbsp;&nbsp;";
                            }
                            if(row.PROJECT_STATE=='启用'){
                                show += "<a class='easyui-linkbutton' onclick='ajaxFinish(\""+row.PROJECT_ID+"\")'"
                                + " target='_self'>完结</a>&nbsp;&nbsp;&nbsp;";
                            }
                            return show;
                        }}
                ]]
            });
        }
        function clearForm(){
            $('#query').form('clear');
        }
        function addProject(){
            console.log('${parentId}');
            $('#dialog').dialog({
                title: '编辑WBS',
                width: 600,
                height: 400,
                closed: false,
                cache: false,
                href: '${emms}/baseinfo/project.do?cmd=queryProjectInfo&parentId=${parentId}'
            });
        }
        function editProject(projectId){
            $('#dialog').dialog({
                title: '编辑WBS',
                width: 600,
                height: 400,
                closed: false,
                cache: false,
                href: '${emms}/baseinfo/project.do?cmd=queryProjectInfo&projectId='+projectId+'&parentId=${parentId}'
            });
        }
        function editThisProject(projectId){
            $('#dialog').dialog({
                title: '编辑WBS',
                width: 600,
                height: 400,
                closed: false,
                cache: false,
                href: '${emms}/baseinfo/project.do?cmd=queryThisProjectInfo&projectId='+projectId
            });
        }
        function startProject(projectId){
            $.messager.confirm("操作提示", "确定要启用项目吗？", function (data) {
                if(data){
                    $.ajax({
                        type: "POST",
                        url:"${emms}/baseinfo/project.do?cmd=startProject&id="+projectId,
                        type:"GET",
                        success: function(data) {
                            if(data == 'true'){
                                $.messager.alert("操作提示", "启用成功！","info");
                                query();
                            }else{
                                $.messager.alert("操作提示",data,"warning");
                            }
                        }
                    });
                }
            });
        }
        function ajaxFinish(id) {
            $.messager.confirm("操作提示", "当前项目完结是否确认？", function (data) {
                if(data){
                    $.ajax({
                        type: "POST",
                        url:"${emms}/baseinfo/project.do?cmd=finishProject&id="+id,
                        async: false,
                        success: function(data) {
                            if(data == 'true'){
                                $.messager.alert("操作提示", "项目完结成功！","info");
                                query();
                            }else{
                                $.messager.alert("操作提示", data,"warning");
                            }
                        }
                    });
                }
            });
        }
        function ajaxSubmitForm() {
            $("#ff").form("submit", {
                url: "${emms}/system/mainProject.do?cmd=saveProject",
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    if(result == 'true'){
                        $.messager.alert("操作提示","主项目信息保存成功","info",function(){ window.parent.frames["westFrame"].location = "${emms}/baseinfo/project.do?cmd=projectTree";})
                    }else{
                        $.messager.alert("操作提示",result,"warning");
                    }
                }
            });
        }
    </script>
	</body>
</html>
