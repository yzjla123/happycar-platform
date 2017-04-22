<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统角色管理</title> 
<#include "/common/head.ftl"> 
<script type="text/javascript" src="${base}/static/js/admin/role/role.js"></script>
<script type="text/javascript">
	var backUrl = '${(Session[base+"/admin/role/index.do"])!}';
</script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">角色管理</a></li>
		<li class="active">编辑角色</li>
	</ol>
	<div class="data">
		<form class="form-horizontal" id="updateForm" action="update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${role.id}">
			<div class="form-group">
				<label for="userName" class="col-sm-2">角色名</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" value="${(role.name)!}" placeholder="角色名称">
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