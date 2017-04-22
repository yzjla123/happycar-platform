<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>报名管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">报名管理</a></li>
		<li class="active">报名列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="报名人姓名" name="name" value="${(name)!''}">
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
		</div>
		<table class="table table-striped table-hover table-bordered">
		  <thead>
		    <tr>
		      <th>序号</th>
		      <th>套餐名称</th>
		      <th>学员姓名</th>
		      <th>推荐人手机号</th>
		      <th>总金额</th>
		      <th>已付金额</th>
		      <th>付款方式</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as signup>
		    <tr>
		      <td>${signup_index+1}</td>
		      <td>${(signup.tuition.name)!}</td>
		      <td>${(signup.member.name)!}</td>
		      <td>${signup.refereePhone}</td>
		      <td>${signup.amount}</td>
		      <td>${signup.payAmount}</td>
		      <td>${dict_singup_payType[signup.payType?string]}</td>
		      <td>${(signup.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="detail.do?id=${signup.id}" class="btn btn-xs">详情</a>
		      	<a href="edit.do?id=${signup.id}" class="btn btn-xs hide">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${signup.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'name,schoolId' />
	</div>
</body>
</html>