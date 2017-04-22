<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/right/right.js"></script>
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
		      <th>代码</th>
		      <th>名称</th>
		      <th>顺序</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as right>
		    <tr>
		      <td>${right_index+1}</td>
		      <td>${right.code}</td>
		      <td>${right.name}</td>
		      <td>${right.seq}</td>
		      <td>${(right.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${right.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${right.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'pid,type,level' />
	</div>
</body>
</html>