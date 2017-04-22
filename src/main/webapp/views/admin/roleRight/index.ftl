<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统用户权限管理</title> 
<#include "/common/config.ftl"> 
<#include "/common/head.ftl"> 
<#include "/common/macro1.ftl">
<link rel="stylesheet" href="${base}/static/assets/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${base}/static/assets/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${base}/static/assets/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${base}/static/js/admin/roleRight/roleRight.js"></script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">用户权限管理</a></li>
		<li class="active">用户权限列表</li>
	</ol>
	<div class="data">
		<input type="hidden" id="roleId" value="${role.id}">
		<input type="hidden" id="type" value="0">
		<div class="panel panel-default">
		  <div class="panel-heading ">
		  		<div class="row">
				  <div class="col-xs-10">
				  	<div class="dropdown inline-block">
					  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					   		 ${role.name}
					    <span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					  	<#list roles as role>
					  		<li><a href="index.do?roleId=${role.id!''}&type=0">${role.name!''}</a></li>
					  	</#list>
					  </ul>
					</div>
				  </div>
				  <div class="col-xs-2">
				    <button class="btn btn-primary pull-right" id="save">保存</button>
				  </div>
				</div>
				
				
		  </div>
		  <div class="panel-body">
		    <div class="row">
		    	<div class="col-xs-6">
		    		<div id="menuTree" class="ztree"></div>
		    	</div>
		    	<div class="col-xs-6">
		    		<div id="rightTree" class="ztree"></div>
		    	</div>
		    </div>
		  </div>
		</div>
	</div>
	
	
</body>
</html>