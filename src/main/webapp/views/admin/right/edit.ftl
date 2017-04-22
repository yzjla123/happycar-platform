<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/right/right.js"></script>
</head>
	<div class="data">
		<form class="form-horizontal" id="updateForm" action="update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${(right.id)!}">
			<div class="form-group">
				<label for="userName" class="col-sm-2">权限代码</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="code" name="code" value="${(right.code)!}" placeholder="权限代码">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">权限名称</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" value="${(right.name)!}" placeholder="权限名称">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">序号</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="seq" name="seq" value="${(right.seq)!}" placeholder="序号" value="${(seq)!}">
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