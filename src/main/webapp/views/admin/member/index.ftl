<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">学员管理</a></li>
		<li class="active">学员列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="名称" name="name" value="${(name)!''}">
			</div>
			<div class="col-sm-3">
				<@select data=coachs attrs='name="coachId" class="form-control required"' value=(coachId)! key='id' val='name' empty='请选择'></@select>
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
		      <th>名称</th>
		      <th>手机号</th>
		      <th>身价证号</th>
		      <th>学习进度</th>
		      <th>教练</th>
		      <th>驾校</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as member>
		    <tr>
		      <td>${member_index+1}</td>
		      <td>${member.name}</td>
		      <td>${member.phone}</td>
		      <td>${member.idcard}</td>
		      <td>${dict_member_progress[member.progress?string]}</td>
		      <td>${(member.coach.name)!}</td>
		      <td>${(member.coach.school.name)!}</td>
		      <td>${(member.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${member.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${member.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'name' />
	</div>
</body>
</html>