<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title> 
<#include "/common/head.ftl"> 
<link rel="stylesheet" href="${base}/static/assets/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/static/assets/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${base}/static/js/admin/menu/menu.js"></script>
<script type="text/javascript">
	var type = '0';
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},callback: {
				onClick: zTreeOnClick
			}
		};
	$().ready(function(){
		loadMenuTree();
		$('#listFrame').attr('src','list.do?pid=0&type='+type);
	});
	function loadMenuTree(){
		$.getJSON("ztree.do?type="+type,function(json){
			$.fn.zTree.init($("#dataTree"), setting, json.tree);
		});
	}
	function zTreeOnClick(event, treeId, treeNode) {
		var id = treeNode.id;
		$('#listFrame').attr('src','list.do?pid='+id+'&type='+treeNode.ext);
		$('#treeDiv').find(".toggler").trigger("click");
	};
</script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">菜单管理</a></li>
		<li class="active">菜单列表</li>
	</ol>
	<div class="con1">
		<div class="col-sm-2">
			<div id="treeDiv">
				<ul id="dataTree" class="ztree"></ul>
			</div>
		</div>
		<div class="col-sm-9" style="height: 100%">
			<iframe id="listFrame"  frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
		</div>
	</div>
</body>
</html>