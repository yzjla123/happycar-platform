<!DOCTYPE html>
<head>
<meta charset="utf-8" />
<title>快乐学车管理系统</title>
<#include "/common/config.ftl">
<#include "/common/head.ftl">
<#include "/common/macro1.ftl">
<link href="${base}/static/css/login/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/login/style.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/login/login-soft.css" rel="stylesheet" type="text/css"/>
<script src="${base}/static/assets/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script src="${base}/static/js/login/login.js" type="text/javascript"></script>
</head>
<body>
<body class="login">
<div class="logo">
	<h1 class="" style="color: #fff;">快乐学车管理系统</h1>
</div>
<form action="${base}/login.do" method="post" onsubmit="return check()">
<div class="content">
		<h3 class="form-title">登录系统</h3>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">帐号</label>
			<div class="input-icon">
				<i class="icon icon-user" style="margin:10px;"></i>
				<input type="hidden" name="redirect" id="redirect" value=${redirect!}/>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="输入帐号" name="userName" id="username"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="icon icon-lock" style="margin:10px;"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="输入密码" name="pwd" id="password"/>
			</div>
		</div>
		<div class="form-actions">
			<button id="loginButton" type="submit" class="btn blue pull-right">
			登录 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
</div>
</form>
<div class="copyright">
	 2012-2017 &copy; 中交万乘科技有限公司.
</div>
<@alertMsg/>
	
</body>
</html>
