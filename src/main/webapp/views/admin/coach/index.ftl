<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教练管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">教练管理</a></li>
		<li class="active">教练列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="名称" name="name" value="${(name)!''}">
			</div>
			<div class="col-sm-3">
				<@select data=schools value=schoolId! attrs='name="schoolId" class="form-control"' key='id' val='name' empty='请选择'  ></@select>
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
		      <th>电话</th>
		      <th>教龄</th>
		      <th>车辆数</th>
		      <th>学员(在学/总数)</th>
		      <th>学校</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as coach>
		    <tr>
		      <td>${coach_index+1}</td>
		      <td>${coach.name}</td>
		      <td>${coach.phone}</td>
		      <td>${coach.schoolAge}</td>
		      <td>${coach.carNum}</td>
		      <td>${coach.learningMember}/${coach.totalMember}</td>
		      <td>${coach.school.name}</td>
		      <td>${(coach.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${coach.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${coach.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'name,schoolId' />
	</div>
</body>
</html>