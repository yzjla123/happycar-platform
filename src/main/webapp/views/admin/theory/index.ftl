<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>理论题管理</title> 
<#include "/common/head.ftl"> 
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">理论题管理</a></li>
		<li class="active">理论题列表</li>
	</ol>
	<form action="">
	<div class="search">
		<div class="form-group">
			<div class="col-sm-3">
				<input type="text" class="form-control" placeholder="题目" name="title" value="${(title)!''}">
			</div>
			<div class="col-sm-3">
				<@select data=dict_theory_subjectType value=subjectType! attrs='name="subjectType" class="form-control"' empty='请选择科目类型'  ></@select>
			</div>
			<div class="col-sm-3">
				<@select data=dict_theory_questionType value=questionType! attrs='name="questionType" class="form-control"' empty='请选择题目类型'  ></@select>
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
		  <a type="button" class="btn btn-primary" href="add.do?questionType=1">新增单选题</a>
		  <a type="button" class="btn btn-info" href="add.do?questionType=2">新增多选题</a>
		  <a type="button" class="btn btn-success" href="add.do?questionType=3">新增判断题</a>
		</div>
		<table class="table table-striped table-hover table-bordered">
		  <thead>
		    <tr>
		      <th>序号</th>
		      <th>题目</th>
		      <th>科目</th>
		      <th>题目类型</th>
		      <th>选项</th>
		      <th>答案</th>
		      <th>时间</th>
		      <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list page.content as theory>
		    <tr>
		      <td>${theory_index+1}</td>
		      <td>${theory.title}</td>
		      <td>${dict_theory_subjectType[theory.subjectType?string]}</td>
		      <td>${dict_theory_questionType[theory.questionType?string]}</td>
		      <td>
		      	  A:${(theory.option1)!}<br>
		      	  B:${(theory.option2)!}<br>
		      	  C:${(theory.option3)!}<br>
		      	  D:${(theory.option4)!}<br>
		      </td>
		      <td>
		      		<#if theory.questionType?string=='3'>
		      	   		${((theory.answer?string=='A')?string('对','错'))!}
		      	   	<#else>
		      	   		${(theory.answer)!}
		      	   	</#if>
		      	   <br>
		      	   ${(theory.answerDesc)!}
		      </td>
		      <td>${(theory.addTime?string('yyyy-MM-dd'))!''}</td>
		      <td>
		      	<a href="edit.do?id=${theory.id}" class="btn btn-xs">编辑</a>
		      	<a href="javascript:void(0)" onclick="del('${theory.id}')" class="btn btn-xs">删除</a>
		      </td>
		    </tr>
		    </#list>
		  </tbody>
		</table>
		<@page_bar page 'title,subjectType,questionType' />
	</div>
</body>
</html>