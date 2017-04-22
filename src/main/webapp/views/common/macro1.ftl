<#macro alertMsg>
    <#if msg??>
    	<script>
    		alertAlertMsg('${msg}');
    	</script>
    </#if>
     <#if errors??>
    	<script>
    		alertErrorMsg('${errors[0].defaultMessage}');
    	</script>
    </#if>
    <#if back??>
    	<script>
    		history.back();
    	</script>
    </#if>
</#macro>

<#macro page_bar page pars=''>
	<ul class="pager pull-right">
	<#assign param='?sort=' + (page.sort!'')?string?replace(': ',',')/>
	<#if pars??>
		<#list pars?split(',') as key>
			<#if key?index_of("Time") == -1>
				<#assign param = param + "&" + key + "=" + (request.model[key]?string)!/>
			<#else>
				<#assign param = param + "&" + key + "=" + (request.model[key]?date)!/>
			</#if>
		</#list>
	</#if>
	<#assign from=page.number * page.size + 1 />
	<#assign to=from + page.numberOfElements - 1 />
	<li><a>共  ${page.totalElements} 条记录</a></li>
	   <#if !page.firstPage>
	   		<li><a href="${param}&page=0">首页</a></li>
	   		<li><a href="${param}&page=${page.number - 1}">« 上一页</a></li>
	   </#if>	   
	   <#assign bn = page.number - 5 />
	   <#assign offset = 0 />
	   <#if bn lt 0><#assign bn = 0 /><#assign offset = 5 - page.number /></#if>	   
	   <#assign en = page.number + 5 + offset />
	   <#if en gt page.totalPages - 1><#assign en = page.totalPages - 1/></#if>
	   <#if page.totalPages != 1>
	   <#if from lt to>
	   <#list bn..en as num>
	   		<#if page.number == num>
	   			<li class="active" ><a>${num + 1}</a></li>
	   		<#else>
	   			<li><a class="num" href="${param}&page=${num}">${num + 1}</a></li>
	   		</#if>
	   </#list>
	   </#if>
	   </#if>
	   <#if !page.lastPage>
	   		<li><a class="next" href="${param}&page=${page.number + 1}">下一页 »</a></li>
	   		<li><a href="${param}&page=${page.totalPages - 1}">末 页 </a></li>
	   </#if>
	</ul>
</#macro>
<#macro minuteFormat minute>
	<#if minute lt 60>
		${minute}分钟
	<#elseif minute>
		${minute/(60)}小时${minute%(60)}分钟
	</#if>
</#macro>

<#macro select data value='' attrs='' key='' val='' empty='' data_type=2 hidden='' multiple=false>
	<select ${attrs}>
	<#if empty != ''>
		<option value="">${empty}</option>
	</#if>
	<#if key != ''>
		<#list data as obj>
			<option value="${obj[key]}" <#if value?string==obj[key]?string>selected="selected"</#if>>${obj[val]}</option>
		</#list>
	<#else>
		
		<#if data_type?number = 2>
			<#list data?keys as key>
					<option value="${key}" <#if value?string?contains(key?string)>selected="selected"</#if>>${data[key]}</option>
			</#list>
		<#else>
			<#list data as s>
					<option value="${s}" <#if value?string?contains(s?string)>selected="selected"</#if>>${s}</option>
			</#list>
		</#if>
	</#if>
	</select>
</#macro>

<#macro radio data value='' attrs=''>
	<#list data?keys as key>
		<input type="radio" ${attrs} value="${key}" <#if key == value?string>checked="checked"</#if>>${data[key]} 
	</#list>
</#macro>
<#macro checkbox data value='' attrs=''>
	<#list data?keys as key>
		<input type="checkbox" ${attrs} value="${key}" <#if value?contains(data[key])>checked="checked"</#if>>${data[key]} 
	</#list>
</#macro>