<div class="row">
<div class="col-md-12">
<form id="roleform" method="get" action="">
<table class="table table-bordered">
<thead>
<tr>
<th>id</th>
<th>Роль</th>
<th>Действия</th>
<tr>
</thead>
<tbody>
<#list roles as ro>
<tr>
<td>${ro.id}</td>
<td>${ro.roleName}</td>
<td>
<button type="button" id="btn_edit_${ro.id}" class="btn btn-info btn-mini" onclick="clk(1,${ro.id},'${ro.roleName}')" title="Изменить роль"><i class="glyphicon glyphicon-edit" ></i></button>
<button type="button" id="btn_del_${ro.id}"  class="btn btn-info btn-mini" onclick="clk(2,${ro.id},'${ro.roleName}')" title="Удалить роль"><i class="glyphicon glyphicon-trash" ></i></button>
</td>
</tr>
</#list>
</tbody>
</table>
<a type="button" id="btn_add"  class="btn btn-info btn-mini" href="${springMacroRequestContext.contextPath}/roleadd" title="Добавить роль"><i class="glyphicon glyphicon-plus" ></i></a>
<input id="roleid" name="roleid" type="hidden" value=""/>
</form>
</div>
</div>

<script type="text/javascript">
function clk(ac,id,nm){
if (ac==1)
{
$('#roleid').val(id);
$('#roleform').attr('action', '${springMacroRequestContext.contextPath}/roleedit');
}
else if (ac==2)
{
if (confirm('Удалить роль '+nm+'?'))
{
$('#roleid').val(id);
$('#roleform').attr('action', '${springMacroRequestContext.contextPath}/roledel');
}
else 
{
return;
}
}
else if (ac==3)
{
$('#roleform').attr('action', '${springMacroRequestContext.contextPath}/roleadd');
}

$('#roleform').submit();
}
</script>