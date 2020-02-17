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
						 
                        <form id="roleform" class="form-horizontal" role="form" method="post">
                            <div class="control-group">
                             <label for="rolename">Роль</label>
                             <div style="margin-bottom: 25px" class="input-group" >
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i></span>
                                       	<input id="rolename" type="text" class="form-control" name="rolename" placeholder="наименование роли" value="<#if action?? && action=="edit">${role.roleName}</#if>" onChange="onCh()">
                             </div>
                            </div>
                                <div class="col-md-12" style="border-top: 1px solid#888; margin-top:15px; margin-bottom:15px"></div>
                                <#if action?? && action=="edit">
                                <input name="roleid" type="hidden" value="${role.id}"/>
                                </#if>
                                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>    
                                <div style="margin-top:10px;" class="form-group">
                                
                                    <!-- Button -->

                                    <div class="controls col-md-10 col-md-ofset-1">
                                    <#if action=="add">
                                      	<button type="button" id="btn-adduser" class="btn btn-success" onclick="onClk(1)"><i class="glyphicon glyphicon-ok"></i> Добавить</button>
                                     <#else> 
                                     	<button type="button" id="btn-edituser" class="btn btn-success" onclick="onClk(2)"><i class="glyphicon glyphicon-ok"></i> Применить</button>
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
  $('#roleform').attr("action","${springMacroRequestContext.contextPath}/roleadd");
  $('#roleform').submit(); 
 }
else if (v==2)
 {
  $('#roleform').attr("action","${springMacroRequestContext.contextPath}/roleedit");
  $('#roleform').submit(); 
 }
else if (v==3)
 {
  document.location.href ="${springMacroRequestContext.contextPath}/roleslist";
 }
}
</script>
</body>
</html>