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
    <title>segurJava - ${userPrincipal.username}</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
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
          <a class="navbar-brand" href="#">segurJava</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a class="active" href="#about">Sensores</a></li>
            <li><a href="#contact">Tus datos</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
			<li>	
				<a href="https://github.com/arturisimo/segur-java" title="github.com/arturisimo/segur-java"><img class="github" src='<c:url value="/resources/img/github.ico" />'></a>
			</li>
			<li class="separator"><a href="#">${userPrincipal.username}</a></li>
			<li><a href="<c:url value="j_spring_security_logout" />"><span class="glyphicon glyphicon-off"></span> </a></li>
		  </ul>
        </div>
      </div>
    </nav>

    <div class="container"  >
		
		<div id="containerEditCliente" class="container col-xs-12 col-sm-12 col-md-6 col-lg-6 content">
			
			<div id="messageKO" class="alert alert-danger hidden">
				<strong>Error:</strong><span id="errorMessage"></span>
		    </div>
		    <div id="messageOK" class="alert alert-success hidden">
				<strong>OK:</strong><span id="message"></span>
		    </div>
				
			<form id="formCliente" action="" method="POST" role="form">
				<legend>Datos personales</legend>
				<input type="hidden" id="idCliente" value="">
			
				<div class="form-group">
					<label for="nombre">nombre</label>
					<input type="text" class="form-control" disabled id="nombre" value="" placeholder="nombre">
				</div>
				<div class="form-group">
					<label for="email">email</label>
					<input type="text" class="form-control" disabled id="email" value="" placeholder="email">
				</div>
				<div class="form-group">
					<label for="dni">dni</label>
					<input type="text" class="form-control" disabled id="dni" value="" placeholder="dni">
				</div>
				<div class="form-group">
					<label for="cuenta">cuenta</label>
					<input type="text" class="form-control" disabled id="cuenta" value="" placeholder="cuenta">
				</div>
				<div class="form-group">
					<label for="direccion">direccion</label>
					<input type="text" class="form-control" disabled id="direccion" value="" placeholder="direccion">
				</div>
				<div class="form-group checkbox">
					<label>
						<input type="checkbox" disabled id=policia value="">
						Aviso a la policia
					</label>
				</div>
				<a id="editClienteAction" href="#" class="btn btn-primary">Editar</a>
				<a id="saveClienteAction" href="#" class="btn btn-danger" style="display: none;">Alta</a>
			</form>
		
		</div>
		
		<div class="container col-xs-12 col-sm-12 col-md-6 col-lg-6 content">
			
			<div id="listSensorContainer" class="container col-lg-12">
				<legend>Sensores contratados</legend>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>zona</th><th>estado sensor</th><th>Dar de baja</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
				<button id="formSensorAction" type="button" class="btn btn-primary">Contratar nuevo sensor</button>
			</div>
			
			<div id="formSensorContainer" class="container col-lg-12" style="display: none;">
			
				<form action="" method="POST" role="form">
					<legend>Contratación de un nuevo sensor</legend>
					<input type="hidden" id="idCliente" value="">
				
					<div class="form-group">
						<label for="zona">Estancia de la casa</label>
						<select name="zona" id="zona" class="form-control" required="required">
							<c:forEach var="zona" items="${estancias}">
								<option value="${zona}">${zona.nombre}</option>
							</c:forEach>	
						</select>
					</div>
					<a id="altaSensorAction" href="#" class="btn btn-danger">Alta</a>
					<button id="listSensorAction" type="button" class="btn btn-default">Cancelar</button>
				</form>
			
			</div>
			
		</div>
		
	</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    <script type="text/javascript">
		var nombreUsuario = "${userPrincipal.username}";
		var	urlSensores = "${urlSensores}";
		var urlClientes = "${urlClientes}";
		var urlUsuario = "<c:url value='/usuario' />"
	</script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    
    <script src="<c:url value="/resources/js/clientes.js" />"></script>
    
  </body>
</html>
