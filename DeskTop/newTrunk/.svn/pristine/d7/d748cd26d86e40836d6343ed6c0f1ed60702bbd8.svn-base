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
			<c:if test="${not empty orgTypeId}">
				<a href="javascript:void(0)" onclick="saveOrgTypeWBS()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			</c:if>
			<ul class="easyui-tree" id="tt" checkbox="true">
			</ul>
		</div>
		<script type="text/javascript">
		function saveOrgTypeWBS(){
			var nodes = $('#tt').tree('getChecked');
			var idValues = '';
			if(nodes.length==0){
				$.messager.alert("操作提示", "没有可配置的WBS！","warning");
				return false;
			}
			var wbsIds = '';
			for (var i = 0; i < nodes.length; i++) {
				if(wbsIds == ''){
					wbsIds = nodes[i].id;
				}else{
					wbsIds += ","+nodes[i].id;
				}
			}
			if(window.confirm('确定配置以下WBS吗？')){
				$.ajax({
		            type: "POST",
		            url: "${emms}/system/organization.do?cmd=saveOrgTypeAndWBS&orgId=${orgId}&orgType=${orgTypeId}&wbsId="+wbsIds,
		            success: function(data) {
		            	if('${type}' == 'contrac'){
		            		window.parent.location.href = "${emms}/baseinfo/contractor.do?cmd=editFrame&contractorId=${contractorId}&title=配置WBS"
		            	}else{
			            	window.parent.location.href = "${emms}/system/organization.do?cmd=configWBSQuery&orgId=${orgId}"
		            	}
		            }
		        });
			}
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
							text:row.name,
							level:row.level,
							isLeaf:row.isLeaf,
							checked:row.checked,
							chkDisabled:row.chkDisabled
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
							var child = {id:row.id,text:row.name,level:row.level,isLeaf:row.isLeaf,checked:row.checked,chkDisabled:row.chkDisabled};
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
					checkbox:function(node){
						console.log(node);
						if(node.chkDisabled == false){
							return true;
						}
					},
					loadFilter: function(rows){
						rows.push({"id":0, "parentId":-1, "name":"结构树型图","level":0});
						return convert(rows);
					}
				});
			});
		</script>
	</body>
</html>
