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
		<li class="active">报名详情</li>
	</ol>
	<div class="data">
		<div class="panel panel-default">
	   <div class="panel-heading">报名详情</div>
	   
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
	   
		<table class="table ">
			<tbody>
				<tr>
					<td>套餐名称</td>
					<td>${(signup.tuition.name)!}</td>
					<td>学员姓名</td>
					<td>${(signup.member.name)!}</td>
				</tr>
				<tr>
					<td>推荐人手机号</td>
					<td>${(signup.refereePhone)!}</td>
					<td>付款方式</td>
					<td>${dict_singup_payType[signup.payType?string]}</td>
				</tr>
				<tr>
					<td>总金额</td>
					<td>${(signup.amount)!}</td>
					<td>已付金额</td>
					<td>${(signup.payAmount)!}</td>
				</tr>
				<tr>
					<td>时间</td>
					<td>${(signup.addTime?string('yyyy-MM-dd'))!''}</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
		
		</div>
	</div>
</body>
</html>