<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>预约管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">预约管理</a></li>
		<li class="active">预约列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="学员姓名" name="name" value="${(name)!''}">
			</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="预约日期" name="date" value="${(date)!''}">
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
		  <a type="button" class="btn btn-primary hidden" href="add.do">新增</a>
		</div>
		<table class="table table-striped table-hover table-bordered">
		  <thead>
		    <tr>
		      <th>序号</th>
		      <th>学员姓名</th>
		      <th>日期</th>
		      <th>时间段</th>
		      <th>教练</th>
		      <th>状态</th>
		      <th>评价</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as book>
		    <tr>
		      <td>${book_index+1}</td>
		      <td>${(book.member.name)!}</td>
		      <td>${(book.schedule.date?string('yyyy-MM-dd'))!}</td>
		      <td>${(book.schedule.time1)!}-${(book.schedule.time2)!}</td>
		      <td>${(book.schedule.coach.name)!}</td>
		      <td>${(dict_book_status[book.status?string])!}</td>
		      <td>${(book.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<!-- <a href="edit.do?id=${book.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${book.id}')" class="btn btn-xs">删除</a> -->
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'name,date' />
	</div>
</body>
</html>