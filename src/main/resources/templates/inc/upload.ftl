<div class="row">
<div class="col-md-12">



    <form method="POST" enctype="multipart/form-data" action="${springMacroRequestContext.contextPath}/upload">
        	Расшифровка: <input type="file" accept=".xlsx, .xls" class="btn btn-info btn-mini" name="file">
        	<br /> 
            <input type="submit" value="Upload" class="btn btn-info btn-mini"> Press here to upload the file!
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
    </form>

	<form method="POST" enctype="multipart/form-data" action="${springMacroRequestContext.contextPath}/uploadtel">
        	Справочник телефонов: <input type="file" accept=".xlsx, .xls" class="btn btn-info btn-mini" name="filetel">
        	<br /> 
            <input type="submit" value="UploadTel" class="btn btn-info btn-mini"> Press here to upload the file!
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
    </form>

</div>
</div>