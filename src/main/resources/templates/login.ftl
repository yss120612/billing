<!DOCTYPE html>
<html>
<head>
<#include "/inc/incheader.ftl">
<title>Вход в систему</title>
</head>
<body style="background-color: #CCCCCC">


<!--h2 class="warning"></h2-->

<div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Вход в систему</div>
                    </div>
                         
                    <div style="padding-top:30px" class="panel-body" >
						 <#if error??><div id="error" class="alert alert-danger" role="alert">${error}</div></#if>
						 <#if messa??><div id="messa" class="alert alert-success" role="alert">${messa}</div></#if>	                            
                        <form id="loginform" class="form-horizontal" role="form" action="${springMacroRequestContext.contextPath}/login" method="post">
                               
                          <div class="control-group">
                            <label for="login-username">Логин</label>   	
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="имя пользователя"  onChange="onCh()">                                        
                            </div>
                          </div>
                                
                          <div class="control-group">
                  			<label for="login-password">Пароль</label>
                            <div style="margin-bottom: 25px" class="input-group" >
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="пароль"  onChange="onCh()">
                             </div>
                           <div width="100%" style="border-top: 1px solid#888; padding-top:15px"></div>    
		 			</div>
                                
                            

								<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
                                    <div class="col-sm-3 controls">
                                      <button type="submit" id="btn-login" class="btn btn-success btn-block"><i class="glyphicon glyphicon-log-in"></i> Войти</button>
                                    </div>
                                </div>
                            </form>     
                        </div>                     
                    </div>  
        </div>
    </div>
    
<script language="Javascript">
function onCh()
{
$('#error,#messa').hide();
}
</script>

</body>
</html>