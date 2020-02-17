<!DOCTYPE html>
<html>
<head>
<#include "/inc/incheader.ftl">
<#if titleform??>
<title>${titleform}</title>
</#if>
</head>
<body>
<div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                    <#if titleform??>
                        <div class="panel-title">${titleform}</div>
                    </#if>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        
						             
                        
                        
						<#if err??><div id="error" class="alert alert-danger" role="alert">${err}</div></#if>
						 
                        <form id="userform" class="form-horizontal" role="form" method="post">
                        
                            <div class="control-group">
                             <label for="username">Логин</label>
                             <div style="margin-bottom: 25px" class="input-group" >
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <#if action?? && action=="edit">
                                        	<input id="username" type="text" class="form-control" name="username" disabled="disabled" value="${user.username}">
                                        <#else>	
                                        	<input id="username" type="text" class="form-control" name="username" placeholder="логин"  onChange="onCh()" >
                                        </#if>
                             </div>
                            </div>
                            
							<#if action?? && action=="add">
                            <div class="control-group">
                             <label for="password">Пароль</label>
                             <div style="margin-bottom: 25px" class="input-group" >
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input id="password" type="password" class="form-control" name="password" placeholder="пароль"  onChange="onCh()">
                             </div>
                            </div>
                                   
                             <div class="control-group">
                             <label for="password2">Пароль повтор</label>
                             <div style="margin-bottom: 25px" class="input-group" >
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                    <input id="password2" type="password" class="form-control" name="password2" placeholder="пароль еще раз"  onChange="onCh()">
                             </div>
                            </div>
                            </#if>
                            
							<div class="control-group">                                    
							 <div class="form-check">
							<#if action?? && action=="add">
    							<label class="form-check-label">
    							<input type="checkbox" name="access" id="access" class="form-check-input" checked="true"> Доступ разрешен
    							</label>
    						<#else>
    							<label class="form-check-label">
    							<input type="checkbox" name="access" id="access" class="form-check-input" <#if user.enabled>checked</#if>> Доступ разрешен
    							</label>
    						</#if>	
  							 </div>
  							 </div>
                            
                             <div class="control-group col-md-6 col-md-offset-1">
    							<label for="uroles">Роли (Ctrl+click)</label>
    							<select multiple class="form-control" id="uroles" name="uroles">
    							<#list roles as r>
    							<#if action?? && action=="add">
    								<option>${r.authority}</option>
    							<#else>
      								<option <#if user.roleNames?seq_contains(r.authority) >selected</#if> >${r.authority}</option>
      							</#if>
      							</#list>  
    							</select>
 							  </div>
                             <div class="control-group col-md-6">
                             </div>
                                                        
                            
                                <div class="col-md-12" style="border-top: 1px solid#888; margin-top:15px; margin-bottom:15px"></div>    
								<#if action?? && action=="edit">
                                <input name="userid" type="hidden" value="${user.id}"/>
                                </#if>
								<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
								
                                <div style="margin-top:10px;" class="form-group">
                                    <!-- Button -->

                                    <div class="controls col-md-10 col-md-ofset-1">
                                    <#if action=="add">
                                      	<button type="button" id="btn-adduser" class="btn btn-success" onclick="onClk(1)"><i class="glyphicon glyphicon-ok"></i> Добавить</button>
                                     <#else> 
                                     	<button type="button" id="btn-edituser" class="btn btn-success" onclick="onClk(2)"><i class="glyphicon glyphicon-ok"></i> Принять</button>
                                     </#if>
                                     	<button type="button" id="btn-cancel" class="btn btn-default" onclick="onClk(3)"><i class="glyphicon glyphicon-remove"></i> Отмена</button>
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
 $('#error').hide();
}

function onClk(v)
{
if (v==1)
 {
  $('#userform').attr("action","${springMacroRequestContext.contextPath}/useradd");
  $('#userform').submit(); 
 }
else if (v==2)
 {
  $('#userform').attr("action","${springMacroRequestContext.contextPath}/useredit");
  $('#userform').submit(); 
 }
else if (v==3)
 {
  document.location.href ="${springMacroRequestContext.contextPath}/userslist";
 }
}



</script>
</body>
</html>