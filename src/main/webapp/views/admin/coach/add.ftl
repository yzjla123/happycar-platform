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
		<li class="active">增加教练</li>
	</ol>
	<div class="data">
		<form class="form-horizontal" id="addForm" action="save.do" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="name" class="col-sm-2">姓名</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" value="${(coach.name)!}" placeholder="姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">驾校</label>
				<div class="col-md-6 col-sm-10">
					<@select data=schools key='id' val='name' empty='请选择驾校' attrs='name="schoolId" class="form-control required"'></@select>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">电话</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="phone" name="phone" value="${(coach.phone)!}" placeholder="电话">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">教龄</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="schoolAge" name="schoolAge" value="${(coach.schoolAge)!}" placeholder="教龄">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">车辆数</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="carNum" name="carNum" value="${(coach.carNum)!}" placeholder="车辆数">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-5 bot-btn">
					<button type="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default " id="back">返回上一页</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>