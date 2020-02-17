<!DOCTYPE html>
<html>
<#include "/inc/incheader.ftl">

<#if name??>
<title>Смена пароля ${name}</title>
</#if>
                    
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                    <#if name??>
                        <div class="panel-title">Смена пароля пользователя ${name}</div>
                    </#if>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        
						<#if messa??>
						 <div id="ok" class="alert alert-success" role="alert">${messa}</div>
						  <form id="chgpassOKform" class="form-horizontal" role="form" action="${springMacroRequestContext.contextPath}/" method="post">
						   <div style="margin-bottom: 25px" class="input-group" >
						    <button type="submit" id="btn-login" class="btn btn-success btn-block"><i class="glyphicon glyphicon-ok"></i> OK </button>
						    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
						   </div>
						  </form>
						<#else>                        
                        
						 <#if error??><div id="error" class="alert alert-danger" role="alert">${error}</div></#if>
						 
                        <form id="chgpassform" class="form-horizontal" role="form" action="${springMacroRequestContext.contextPath}/chgpwd" method="post">
                        
                            <div class="control-group">
                                			<label for="login-password-old">Старый пароль</label>
                            <div style="margin-bottom: 25px" class="input-group" >
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password-old" type="password" class="form-control" name="password_old" placeholder="старый пароль"  onChange="onCh()">
                                    </div>
                                    </div>
                                <div class="control-group">
                                <label for="login-password-new">Новый пароль</label>
                            <div style="margin-bottom: 25px" class="input-group" >
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input id="login-password-new" type="password" class="form-control" name="password_new" placeholder="новый пароль"  onChange="onCh()">
                                   </div>
                                    </div>
                                    
							<div class="control-group">
										<label for="login-password-new">Новый пароль повтор</label>                                    
                            <div style="margin-bottom: 25px" class="input-group" >
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password-new2" type="password" class="form-control" name="password_new2" placeholder="новый пароль повтор"  onChange="onCh()">
                                    </div>
                                    </div>
                                <div width="100%" style="border-top: 1px solid#888; padding-top:15px"></div>    

								<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->

                                    <div class="controls col-md-8 col-md-offset-2">
                                      <button type="submit" id="btn-login"  class="btn btn-success"><i class="glyphicon glyphicon-random"></i> Сменить пароль</button>
                                      <button type="button" id="btn-cancel" class="btn btn-default" onclick="onClk(3)"><i class="glyphicon glyphicon-remove"></i> Отмена</button>
                                    </div>
                                </div>
                                
                            </form>  
                         </#if>      
                        </div>                     
                    </div>  
        </div>
</div>
    
<script language="Javascript">
function onCh()
{
$('#error').hide();
}

function onClk(v)
{
if (v==3)
 {
  document.location.href ="${springMacroRequestContext.contextPath}/";
 }
}

</script>
</body>
</html>