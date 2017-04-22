<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统用户管理</title> 
<#include "/common/config.ftl"> 
<#include "/common/head.ftl"> 
<#include "/common/macro1.ftl">
<script type="text/javascript" src="${base}/static/js/admin/user/user.js"></script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">用户管理</a></li>
		<li class="active">用户列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="用户名" name="userName" value="${(userName)!''}">
			</div>
			<div class="col-sm-2">
				<select class="form-control" name="status">
				    <option value="">全部</option>
				    <option value="0" ${((status1?string=='0')?string('selected',''))!}>正常</option>
				    <option value="1" ${((status1?string=='1')?string('selected',''))!}>冻结</option>
				 </select>
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
		      <th>状态</th>
		      <th>是否超管</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as user>
		    <tr>
		      <td>${user_index+1}</td>
		      <td>${user.userName}</td>
		      <td>${dict_user_status[user.status?string]}</td>
		      <td>${(user.isSuper==1)?string('是','否')}</td>
		      <td>${(user.addTime?string('yyyy-MM-dd'))!}</td>
		      <td>
		      	<#if user.status==0>
		      		<a href="javascript:void(0)" onclick="disableUser('${user.id}')" class="btn btn-xs">禁用</a>
		      	<#elseif user.status==1>
		      		<a href="javascript:void(0)" onclick="enableUser('${user.id}')" class="btn btn-xs">启用</a>
		      	</#if>
		      	<a href="edit.do?id=${user.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${user.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page "userName,status1"/>
	</div>
</body>
</html>