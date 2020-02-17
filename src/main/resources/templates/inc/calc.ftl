<div class="row">
<div class="col-md-6 col-md-offset-3">
<form id="userform" method="post" action="${springMacroRequestContext.contextPath}/calc">
 <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Расчет пенсии</div>
                    </div>
<div style="padding:15px;">                
<div class="control-group">
                            <label for="login-username">Введите СНИЛС</label>   	
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-hand-right" aria-hidden="true"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="snils" value="" placeholder="000-000-000 00">                                        
                            </div>
                       </div>
<div class="panel panel-default">
<div class="panel-heading">Для расчета фиксированной части пенсии укажите:</div>
<div class="panel-body">
<div class="form-group col-md-8">
    <label for="IJselect">Количество иждивенцев</label>
    <select class="form-control" id="IJselect" name="IJselect" >
      <option value="0" <#if ijd=="0">selected</#if> >Нет</option>
      <option value="1" <#if ijd=="1">selected</#if> >Один</option>
      <option value="2" <#if ijd=="2">selected</#if> >Два</option>
      <option value="3" <#if ijd=="3">selected</#if> >Три и более</option>
    </select>
  </div>
  <div class="form-group col-md-8">
    <label for="KOEselect">Коэффициент</label>
    <select class="form-control" id="KOEselect" name="KOEselect">
      <option value="0" <#if koeff=="0">selected</#if> >Нет</option>
      <option value="1" <#if koeff=="1">selected</#if> >1,3</option>
      <option value="2" <#if koeff=="2">selected</#if> >1,5</option>
    </select>
  </div>
  </div>
</div>

                                              
<button type="submit" id="btn_calc"  class="btn btn-info btn-mini"><i class="glyphicon glyphicon-flash"></i> Расчитать</button>
<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
</div>
 </div>
</form>
</div>
</div>