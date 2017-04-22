<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/right/right.js"></script>
</head>
	<div class="data">
		<form class="form-horizontal" id="addForm" action="save.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pid" value="${(pid)!}">
			<input type="hidden" name="type" value="${(type)!}">
			<div class="form-group">
				<label for="userName" class="col-sm-2">权限代码</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="code" name="code" placeholder="权限代码">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">权限名称</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" placeholder="权限名称">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">序号</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="seq" name="seq" placeholder="序号" value="${(seq)!}">
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