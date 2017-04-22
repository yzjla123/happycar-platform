
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>快乐学车管理系统</title>
<#include "/common/config.ftl">
<#include "/common/head.ftl">
<#include "/common/macro1.ftl">
<style type="text/css">
body,html{
	height: 99%;
	background-color: #f5f5f5;
}
</style>
<script type="text/javascript">
$().ready(function(){
	$("#mainFrame",parent.document.body).attr("src",$('a[target="mainFrame"]:first').attr('href')) ;
});
</script>
</head>
<body>
	<nav class="menu" data-toggle="menu" style="width: 200px">
		<ul class="nav nav-primary">
			<#list menus as menu>
			<li class="nav-parent"><a href="${base+menu.url}"  style="border-radius: 0;border-bottom-right-radius:0;border-bottom-left-radius:0;border-top-left-radius:0;border-top-right-radius:0;"><i class=""></i> ${menu.name}</a>
				<#if menu.childs?size gt 0>
				<ul class="nav">
					<#list menu.childs as submenu>
					<li><a href="${submenu.url}" target="mainFrame"   style="border-radius: 0;border-bottom-right-radius:0;border-bottom-left-radius:0;border-top-left-radius:0;border-top-right-radius:0;">${base+submenu.name}</a></li>
					</#list>
				</ul>
				</#if>
			</li>
			</#list>
		</ul>
	</nav>
</body>
</html>