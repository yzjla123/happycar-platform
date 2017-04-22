<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统用户管理</title> 
<#include "/common/config.ftl"> 
<#include "/common/head.ftl"> 
<#include "/common/macro1.ftl">
<script type="text/javascript" src="${base}/static/js/admin/user/user.js"></script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">用户管理</a></li>
		<li class="active">编辑用户</li>
	</ol>
	<div class="data-con">
		<form class="form-horizontal" id="addForm" action="update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id"  value="${(user.id)!}" autocomplete="off">
			<div class="form-group">
				<label for="userName" class="col-sm-2">账号</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control" name="userName" name="userName" value="${(user.userName)!}" placeholder="电子邮件/手机号/用户名" class="required " minlength="3" autocomplete="off">
				</div>
			</div>
			<div class="form-group">
				<label for="pwd" class="col-sm-2">密码</label>
				<div class="col-md-6 col-sm-10">
					<input type="password" class="form-control" id="pwd" name="pwd" placeholder="密码" autocomplete="off">
				</div>
			</div>
			<div class="form-group">
				<label for="companyId" class="col-sm-2">权限</label>
				<div class="col-md-6 col-sm-10">
					<span id="manageRightSpan" >
					<#list manageRoles  as role>
				    <label class="checkbox-inline">
						<input type="checkbox" name="roleId" value="${role.id}" ${roleIds?seq_contains(role.id)?string('checked','')}> ${role.name}
					</label>
				    </#list>
				    </span>
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