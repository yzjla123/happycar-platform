<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学费管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">学费管理</a></li>
		<li class="active">编辑学费</li>
	</ol>
	<div class="data">
		<form class="form-horizontal" id="updateForm" action="update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${tuition.id}">
			<div class="form-group">
				<label for="name" class="col-sm-2">名称</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" value="${(tuition.name)!}" placeholder="名称">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">学费</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="amount" name="amount" value="${(tuition.amount)!}" placeholder="学费">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-5 col-sm-5 bot-btn">
					<button type="submit" class="btn btn-primary">更新</button>
					<button type="button" class="btn btn-default " id="back">返回上一页</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>