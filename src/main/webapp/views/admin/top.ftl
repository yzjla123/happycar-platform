
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>快乐学车管理系统</title>
<#include "/common/config.ftl">
<#include "/common/head.ftl">
<#include "/common/macro1.ftl">

</head>
<body>
	<header id="header" class="bg-primary with-shadow">
      <div class="navbar navbar-inverse navbar-fixed-top" id="navbar" role="banner" style="padding-right: 10px;">
        <div class="">
          <div class="navbar-header">
            <a href="javascript:void(0);" class="navbar-brand"><span class="path-zui-36"><i class="path-1"></i><i class="path-2"></i></span> <span class="brand-title">Integer系统管理后台</span> &nbsp;<small class="zui-version">1.5.0</small> <i data-toggle="tooltip" id="compactTogger" data-placement="bottom" class="icon icon-home" data-original-title="" title=""></i></a>
          </div>
          <nav class="collapse navbar-collapse zui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
            	<li><a href="javascript:void(0)" >当前帐户:${(user.userName)!}</a></li>
		        <li><a href="javascript:void(0)" onclick="if(confirm('确认退出?')){top.location.href='${base}/logout.do'}">退出登录</a></li>
          </nav>
        </div>
      </div>
    </header>
</body>
</html>