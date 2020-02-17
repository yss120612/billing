<div class="row">
<div class="col-md-12">
<form id="userform" method="get" action="">
<table class="table table-bordered">
<thead>
<tr>
<th>id</th>
<th>Логин</th>
<th>Доступ</th>
<th>Роли</th>
<th>Действия</th>
<tr>
</thead>
<tbody>
<#list users as us>
<tr>
<td>${us.id}</td>
<td>${us.username}</td>
<td>
<#if us.enabled>
<i class="glyphicon glyphicon-ok"></i>
<#else>
<i class="glyphicon glyphicon-cancel"></i>
</#if>
</td>
<td>
<#list us.authorities as au>
${au.authority}<#sep>, </#sep>
</#list>
</td>
<td>
<button type="button" id="btn_edit_${us.id}" class="btn btn-info btn-mini" title="изменить пользователя" onclick="clk(1,${us.id},'${us.username}')"><i class="glyphicon glyphicon-edit" ></i></button>
<button type="button" id="btn_del_${us.id}"  class="btn btn-info btn-mini" title="удалить пользователя" onclick="clk(2,${us.id},'${us.username}')"><i class="glyphicon glyphicon-trash" ></i></button>
</td>
</tr>
</#list>
</tbody>
</table>
<a type="button" id="btn_add"  class="btn btn-info btn-mini" title="создать пользователя" href="${springMacroRequestContext.contextPath}/useradd"><i class="glyphicon glyphicon-plus" ></i></a>
<input id="userid" name="userid" type="hidden" value=""/>
</form>
</div>
</div>

                  
<script type="text/javascript">
function clk(ac,id,nm){
if (ac==1)
{
$('#userid').val(id);
$('#userform').attr('action', '${springMacroRequestContext.contextPath}/useredit');
}
else if (ac==2)
{
if (confirm('Удалить пользователя '+nm+'?'))
{
$('#userid').val(id);
$('#userform').attr('action', '${springMacroRequestContext.contextPath}/userdel');
}
else 
{
return;
}
}
else if (ac==3)
{
$('#userform').attr('action', '${springMacroRequestContext.contextPath}/useradd');
}

$('#userform').submit();
}
</script>