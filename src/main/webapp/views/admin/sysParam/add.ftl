<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统参数管理</title> 
<#include "/common/head.ftl"> 
</head>
	<div class="data">
		<form class="form-horizontal" id="addForm" action="save.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pid" value="${(pid!0)!}">
			<div class="form-group">
				<label for="userName" class="col-sm-2">参数名称</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" placeholder="菜单名称">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">参数键</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="key" name="key" placeholder="参数键">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">参数值</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="value" name="value" placeholder="参数值" value="${(value)!}">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">扩展值1</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control " id="ext1" name="ext1" placeholder="扩展值1" value="${(ext1)!}">
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-sm-2">扩展值2</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control " id="ext2" name="ext2" placeholder="扩展值2" value="${(ext2)!}">
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