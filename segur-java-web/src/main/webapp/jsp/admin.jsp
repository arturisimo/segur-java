<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="userPrincipal" property="principal" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>segurJava - admin</title>
    <!-- Bootstrap core CSS 
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="<c:url value="/resources/css/lib/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">admin - segurJava</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Clientes</a></li>
            <li><a id="informesAction" href="#">Informes</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
			<li>	
				<a href="https://github.com/arturisimo/segur-java" title="github.com/arturisimo/segur-java"><img class="github" src='<c:url value="/resources/img/github.ico" />'></a>
			</li>
			<li class="separator"><a href="#">${userPrincipal.username}</a></li>
			<li><a href="<c:url value="j_spring_security_logout" />"><span class="glyphicon glyphicon-off"></span> </a></li>
		  </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
	
		<div id="messageKO" class="alert alert-danger hidden">
			<strong>Error:</strong><span id="errorMessage"></span>
	    </div>
	    <div id="messageOK" class="alert alert-success hidden">
			<strong>OK:</strong><span id="message"></span>
	    </div>
		
		<div id="containerListCliente" class="container content">
			
			<table class="table table-hover">
				<thead>
					<tr>
						<th>nombre</th><th>email</th><th>dni</th><th>cuenta</th><th>direccion</th><th>policia</th><th></th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<a id="formClienteAction" href="#" class="btn btn-primary">Nuevo Cliente</a>
		
		</div>
	
		<div id="containerFormCliente" class="container content" style="display: none;">
				
				<form id="formCliente" action="" method="POST" role="form">
					<legend>Alta de clientes</legend>
					
					<div class="form-group">
						<label for="nombre">nombre</label>
						<input type="text" class="form-control" id="nombre" value="" placeholder="nombre">
					</div>
					<div class="form-group">
						<label for="email">email</label>
						<input type="text" class="form-control" id="email" value="" placeholder="email">
					</div>
					<div class="form-group">
						<label for="usuario">usuario</label>
						<input type="text" class="form-control" id="usuario" value="" placeholder="usuario">
					</div>
					<div class="form-group">
						<label for="password">password</label>
						<input type="password" class="form-control" id="password" value="" placeholder="password">
					</div>
					<div class="form-group">
						<label for="dni">dni</label>
						<input type="text" class="form-control" id="dni" value="" placeholder="dni">
					</div>
					<div class="form-group">
						<label for="cuenta">cuenta</label>
						<input type="text" class="form-control" id="cuenta" value="" placeholder="cuenta">
					</div>
					<div class="form-group">
						<label for="direccion">direccion</label>
						<input type="text" class="form-control" id="direccion" value="" placeholder="direccion">
					</div>
					<div class="form-group checkbox">
						<label>
							<input type="checkbox" id=policia value="">
							Aviso a la policia
						</label>
					</div>
					<a id="saveClienteAction" href="#" class="btn btn-danger">Alta</a>
					<a id="listClienteAction" href="#" class="btn btn-default">Cancelar</a>					
				</form>
			
			</div>
	</div>	
	
	<script type="text/javascript">
		var	urlSensores = "${urlSensores}";
		var urlClientes = "${urlClientes}";
		var urlUsuario = "<c:url value='/alta-usuario' />";
	</script>
	<!-- 	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
    <script src="<c:url value="/resources/js/lib/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/js/lib/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/admin.js" />"></script> 
    
  </body>
</html>
