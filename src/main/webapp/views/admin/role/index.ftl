<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统角色管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/role/role.js"></script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">角色管理</a></li>
		<li class="active">角色列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="角色名" name="name" value="${(name)!''}">
			</div>
		 	<div class="col-sm-3">
				<span class="input-group-btn">
	         		<button class="btn btn-default" type="submit">搜索</button>
	        	</span>
         	</div>
		</div>
	</div>
	</form>
	<div class="data">
		<div class="btn-group">
		  <a type="button" class="btn btn-primary" href="add.do">新增</a>
		</div>
		<table class="table table-striped table-hover table-bordered">
		  <thead>
		    <tr>
		      <th>序号</th>
		      <th>帐号</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as role>
		    <tr>
		      <td>${role_index+1}</td>
		      <td>${role.name}</td>
		      <td>${(role.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${role.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${role.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'name,companyeId' />
	</div>
</body>
</html>