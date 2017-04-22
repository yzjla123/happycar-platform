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
				<input type="text" class="form-control" placeholder="日期" name="date" value="${(date)!''}">
			</div>
			<div class="col-sm-3">
				<@select data=coachs value=coachId! attrs='name="coachId" class="form-control"' key='id' val='name' empty='请选择教练'  ></@select>
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
		      <th>日期</th>
		      <th>时间段</th>
		      <th>教练</th>
		      <th>车辆数</th>
		      <th>可学学员数</th>
		      <th>已预约人数</th>
		      <th>状态</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as schedule>
		    <tr>
		      <td>${schedule_index+1}</td>
		      <td>${schedule.date?string('yyyy-MM-dd')}</td>
		      <td>${schedule.time1}-${schedule.time2}</td>
		      <td>${(schedule.coach.name)!}</td>
		      <td>${schedule.carNum}</td>
		      <td>${schedule.memberNum}</td>
		      <td>${schedule.status==1?string('启用','禁用')}</td>
		      <td>${(schedule.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${schedule.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${schedule.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'date,coachId' />
	</div>
</body>
</html>