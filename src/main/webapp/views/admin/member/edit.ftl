<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">学员管理</a></li>
		<li class="active">编辑学员</li>
	</ol>
	<div class="data">
		<form class="form-horizontal" id="updateForm" action="update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${member.id}">
			<div class="form-group">
				<label for="name" class="col-sm-2">名称</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="name" name="name" value="${(member.name)!}" placeholder="名称">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">手机号</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="phone" name="phone" value="${(member.phone)!}" placeholder="手机号">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">身份证号</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="idcard" name="idcard" value="${(member.idcard)!}" placeholder="身份证号">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">学习进度</label>
				<div class="col-md-6 col-sm-10">
					<@select data=dict_member_progress attrs='name="progress" class="form-control required"' value=(member.progress)! ></@select>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">教练</label>
				<div class="col-md-6 col-sm-10">
					<@select data=coachs attrs='name="coachId" class="form-control required"' value=(member.coachId)! key='id' val='name' empty='请选择'></@select>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">地址</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="addr" name="addr" value="${(member.addr)!}" placeholder="地址">
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