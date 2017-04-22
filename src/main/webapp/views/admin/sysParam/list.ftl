<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统参数管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/sysParam/sysParam.js"></script>
</head>
<body>
	<form action="">
	<input type="hidden" name="pid" value="${pid}">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="参数名" name="name" value="${(name)!''}">
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="参数键" name="key" value="${(key)!''}">
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
		  <a type="button" class="btn btn-primary" href="add.do?type=${(type)!}&pid=${(pid)!}">新增</a>
		</div>
		<table class="table table-striped table-hover table-bordered">
		  <thead>
		    <tr>
		      <th>序号</th>
		      <th>名称</th>
		      <th>参数键</th>
		      <th>参数值</th>
		      <th>扩展值1</th>
		      <th>扩展值2</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as sysParam>
		    <tr>
		      <td>${sysParam_index+1}</td>
		      <td>${sysParam.name}</td>
		      <td>${sysParam.key}</td>
		      <td>${sysParam.value}</td>
		      <td>${sysParam.ext1}</td>
		      <td>${sysParam.ext2}</td>
		      <td>
		      	<a href="edit.do?id=${sysParam.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${sysParam.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'pid,key,name' />
	</div>
</body>
</html>