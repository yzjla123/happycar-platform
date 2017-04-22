<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>理论题管理</title> 
<#include "/common/head.ftl"> 
<link href="${base}/static/assets/zui-1.6/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<script src="${base}/static/assets/zui-1.6/lib/uploader/zui.uploader.min.js"></script>
<script src="${base}/static/assets/qiniu/qiniu.min.js"></script>
<script src="${base}/static/js/admin/theory/theory.js"></script>
</head>
<body>
	<ol class="breadcrumb">
		<li><a href="#">首页</a></li>
		<li><a href="#">理论题管理</a></li>
		<li class="active">增加
			<#if questionType==1>
				单选题
			<#elseif questionType==2>
				多选题
			<#else>
				判断题
			</#if>
		</li>
	</ol>
	<div class="data">
		<form class="form-horizontal" id="addForm" action="save.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="questionType" value="${questionType!'1'}">
			<div class="form-group">
				<label for="name" class="col-sm-2">科目</label>
				<div class="col-md-6 col-sm-10">
					<@select data=dict_theory_subjectType value=subjectType! attrs='name="subjectType" class="form-control required" ' empty='请选择科目类型'  ></@select>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">题目</label>
				<div class="col-md-6 col-sm-10">
					<input type="text" class="form-control required" id="title" name="title" value="${(theory.title)!}" placeholder="题目">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">图片</label>
				<div class="col-md-6 col-sm-10">
					<div id="myUploader" class="uploader">
					  <div class="file-list" data-drag-placeholder="请拖拽文件到此处"></div>
					  <button type="button" class="btn btn-primary uploader-btn-browse"><i class="icon icon-cloud-upload"></i> 选择文件</button>
					</div>
					<input type="hidden" id="imgUrl" name="imgUrl" value="">
				</div>
			</div>
			<#if questionType==1 || questionType==2>
			<div class="form-group">
				<label for="name" class="col-sm-2">选项</label>
				<div class="col-md-6 col-sm-10">
					<div class="input-group">
						<div class="input-group-addon">A</div>
						<input type="text" class="form-control required" id="option1" name="option1" value="${(theory.option1)!}" placeholder="选项1">
					</div>
					<div class="input-group" style="margin-top: 10px">
						<div class="input-group-addon">B</div>
						<input type="text" class="form-control required" id="option2" name="option2" value="${(theory.option2)!}" placeholder="选项2">
					</div>
					<div class="input-group" style="margin-top: 10px">
						<div class="input-group-addon">C</div>
						<input type="text" class="form-control required" id="option3" name="option3" value="${(theory.option3)!}" placeholder="选项3">
					</div>
					<div class="input-group" style="margin-top: 10px">
						<div class="input-group-addon">D</div>
						<input type="text" class="form-control required" id="option4" name="option4" value="${(theory.option4)!}" placeholder="选项4">
					</div>
				</div>
			</div>
			</#if>
			<div class="form-group">
				<label for="name" class="col-sm-2">答案</label>
				<div class="col-md-6 col-sm-10">
				<#if questionType==1>
					<@radio data=dict_theory_answerOption value=(theory.answer)!'' attrs='name="answer" class=""'></@radio>
				<#elseif questionType==2>
					<@checkbox data=dict_theory_answerOption value=(theory.answer)!'' attrs='name="answer" class=""'></@checkbox>
				<#else>
					<@radio data=dict_theory_answerOfJudge value=(theory.answer)!'' attrs='name="answer" class=""'></@radio>
				</#if>
				
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2">答案解释</label>
				<div class="col-md-6 col-sm-10">
					<textarea  class="form-control required" name="answerDesc"  placeholder="答案解释"></textarea>
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