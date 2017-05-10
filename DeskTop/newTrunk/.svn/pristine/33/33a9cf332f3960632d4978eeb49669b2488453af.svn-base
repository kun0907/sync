<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/includes.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>树结构</title>
		<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
	</head>
	<body>
		<div class="easyui-panel" title="树结构" data-options="fit:true,border:false">
			<c:if test="${isSave}">
				<a href="#" onclick="saveRoleResource()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			</c:if>
			<ul class="easyui-tree" id="tt" checkbox="true"></ul>
		</div>
		<script type="text/javascript">
			function saveRoleResource(){
				var nodes = $('#tt').tree('getChecked',['checked','indeterminate']);
				console.log(nodes);
				var roleIds = '';
				for(var i=0; i<nodes.length; i++){
					if (roleIds != '') roleIds += ',';
					roleIds += nodes[i].id;
				}
				//[使用%5B代替,{使用%7B代替，}使用%7D,]使用%5D代替,""%22代替
				var json = '%5B';
				for (var i = 0; i < nodes.length; i++) {
					if(nodes[i].id != '0'){
						if(json == '%5B'){
							json += '%7B%22resourceId%22:%22'+nodes[i].id+'%22,%22roleIds%22:%22'+nodes[i].code+'%22%7D';
						}else{
							json += ',%7B%22resourceId%22:%22'+nodes[i].id+'%22,%22roleIds%22:%22'+nodes[i].code+'%22%7D';
						}
					}
				}
				json +="%5D"
				$.messager.confirm("操作提示", "确定配置以下权限吗？", function (data) {
					if(data){
						$.ajax({
							type: "POST",
							url: "${emms}/system/role.do?cmd=saveRoleResource&roleId=${roleId}&resourceIds="+json,
							data:$('#role').serialize(),
							async: false,
							success: function(data) {
								$.messager.alert("操作提示", "角色保存成功！","info",function(){
									window.location= "${emms}/system/role.do?cmd=roleResourceTree";
								});
							}
						});
					}
				});
			}
			function convert(rows){
				function exists(rows, parentId){
					for(var i=0; i<rows.length; i++){
						if (rows[i].id == parentId) return true;
					}
					return false;
				}
				
				var nodes = [];
				// get the top level nodes
				for(var i=0; i<rows.length; i++){
					var row = rows[i];
					if (!exists(rows, row.parentId)){
						nodes.push({
							id:row.id,
							code:row.code,
							text:row.name,
							level:row.level,
							isLeaf:row.isLeaf,
							checked:row.checked
						});
					}
				}
				var toDo = [];
				for(var i=0; i<nodes.length; i++){
					toDo.push(nodes[i]);
				}
				while(toDo.length){
					var node = toDo.shift();	// the parent node
					// get the children nodes
					for(var i=0; i<rows.length; i++){
						var row = rows[i];
						if (row.parentId == node.id){
							var child = {id:row.id,code:row.code,text:row.name,level:row.level,isLeaf:row.isLeaf,checked:row.checked};
							if (node.children){
								node.children.push(child);
							} else {
								node.children = [child];
							}
							toDo.push(child);
						}
					}
				}
				return nodes;
			}
			
			$(function(){
				$('#tt').tree({
					url: '${emms}/'+'${initTreeUrl}',
					loadFilter: function(rows){
						rows.push({"id":0, "parentId":-1, "name":"结构树型图","level":0});
						return convert(rows);
					}
				});
			});
		</script>
	</body>
</html>
