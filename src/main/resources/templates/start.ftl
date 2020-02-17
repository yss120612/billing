<!DOCTYPE html>
<html>
<head>

<#if apage??>
<#assign page= apage>
<#else>
<#assign page="home">
</#if>

<#include "/inc/incheader.ftl">


<#if title??>
<title>${title}</title>
</#if>
</head>
<body style="background-color: #CCCCCC">



<nav class="navbar navbar-default navbar-static-top">
  <div class="container">

     <div class="navbar-header">
     	<#if page!="home">
     		<a class="navbar-brand" href="${springMacroRequestContext.contextPath}/">
     		<i class="glyphicon glyphicon-home" aria-hidden="true" style="color: blue;">
     		</i>
     		</a>
     	</#if>
     </div>
     <div class="navbar-inner">
     <ul class="nav navbar-nav">
        <li ><a href="${springMacroRequestContext.contextPath}/reslist">Запросы </a></li>
        <li <#if page=="calc" || page=="calcres">class="active"</#if> ><a href="${springMacroRequestContext.contextPath}/calc">Расчет</a></li>
        <li <#if page=="upload">class="active"</#if> ><a href="${springMacroRequestContext.contextPath}/upload">Загрузить</a></li>
     </ul>
    
       
       <ul class="nav navbar-nav">
        <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Администрирование <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li <#if page=="ulist">class="active"</#if> >
                        <a href="${springMacroRequestContext.contextPath}/userslist">Пользователи</a>
                        </li>
                        <li <#if page=="rlist">class="active"</#if> >
                        <a href="${springMacroRequestContext.contextPath}/roleslist">Роли</a>
                        </li>
                     </ul>
        </li>                
      </ul>
      
       
       <#if name??>
       <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Пользователь:${name} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="${springMacroRequestContext.contextPath}/login?logout">Выйти</a></li>
                        <li><a href="${springMacroRequestContext.contextPath}/chgpwd">Сменить пароль</a></li>
                     </ul>
        </li>                
      </ul>
      </#if>
    </div>
  </div>
</nav>

<div class="container">


<#if rest??>
<div class="alert alert-success" role="alert">
<h4>${rest}</h4>
</div>
</#if>

<#if err??>
<div class="alert alert-danger">
<h3>${err}</h3>
</div>
</#if>


<#if page=="calc">
<#include "/inc/calc.ftl">
</#if>

<#if page=="ulist">
<#include "/inc/ulist.ftl">
</#if>

<#if page=="rlist">
<#include "/inc/rlist.ftl">
</#if>

<#if page=="upload">
<#include "/inc/upload.ftl">
</#if>


</div>
</body>
</html>