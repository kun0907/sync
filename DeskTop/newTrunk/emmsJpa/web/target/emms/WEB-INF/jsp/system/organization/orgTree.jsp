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
			<ul class="easyui-tree" id="tt">
			</ul>
		</div>
		<script type="text/javascript">
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
							isLeaf:row.isLeaf
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
							var child = {id:row.id,text:row.name,level:row.level,isLeaf:row.isLeaf};
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
						var flag = true;
						for(var i=0;i<rows.length;i++){
							if(rows[i].level == '0' || rows[i].level =='1'){
								flag = true;
								break;
							}else{
								flag = false;
							}
						}
						if(flag){
							rows.push({"id":0, "parentId":-1, "name":"结构树型图","level":0});
						}
						return convert(rows);
					},
					onClick: function(node){
						if(null != '${navUrl}' && '${navUrl}' !='' ){
							window.parent.frames["centerFrame"].location.href="${emms}/"+'${navUrl}'+ node.id + "&selfId=" + node.id + "&isLeaf=" + node.isLeaf+"&level="+node.level;
						}
					}
				});
			});

			function reload(){
				window.location.reload();
			}
		</script>
	</body>
</html>
