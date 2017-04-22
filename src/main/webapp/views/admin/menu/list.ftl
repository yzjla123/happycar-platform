<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/menu/menu.js"></script>
</head>
<body>
	<div class="data">
		<div class="btn-group">
		  <a type="button" class="btn btn-primary" href="add.do?type=${(type)!}&pid=${(pid)!}">新增</a>
		</div>
		<table class="table table-striped table-hover table-bordered">
		  <thead>
		    <tr>
		      <th>序号</th>
		      <th>名称</th>
		      <th>路径</th>
		      <th>顺序</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as menu>
		    <tr>
		      <td>${menu_index+1}</td>
		      <td>${menu.name}</td>
		      <td>${menu.url}</td>
		      <td>${menu.seq}</td>
		      <td>${(menu.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${menu.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${menu.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'pid,type,level' />
	</div>
</body>
</html>