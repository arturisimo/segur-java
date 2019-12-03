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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>segurJava</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
   	
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
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">Sensores</a></li>
            <li><a href="#contact">Tus datos</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
		
		<div class="jumbotron">
			<div class="container">
				<h1>hola ${userPrincipal.username}</h1>
				<p>Zona cliente</p>
			</div>
		</div>
		
		<div id="listSensorContainer" class="container">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>zona</th><th>estado sensor</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</div>
		<div id="containerEditCliente" class="container hidden">
			
			<form action="" method="POST" role="form">
				<legend>Datos personales</legend>
				<input type="hidden" id="id" value="">
			
				<div class="form-group">
					<label for="nombre">label</label>
					<input type="text" class="form-control" id="nombre" value="" placeholder="nombre">
				</div>
				<div class="form-group">
					<label for="email">label</label>
					<input type="text" class="form-control" id="email" value="" placeholder="nombre">
				</div>
				<div class="form-group">
					<label for="nombre">dni</label>
					<input type="text" class="form-control" id="dni" value="" placeholder="nombre">
				</div>
				<div class="form-group">
					<label for="cuenta">cuenta</label>
					<input type="text" class="form-control" id="cuenta" value="" placeholder="nombre">
				</div>
				<div class="form-group">
					<label for="direccion">direccion</label>
					<input type="text" class="form-control" id="direccion" value="" placeholder="nombre">
				</div>
				<div class="form-group checkbox">
					<label>
						<input type="checkbox" id=policia value="">
						Aviso a la policia
					</label>
				</div>
				
				<a id="editClienteSensor" href="#" class="btn btn-primary">Alta</a>
			</form>
		
		</div>
	
	</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    <script type="text/javascript">
		
		var nombreUsuario = "${userPrincipal.username}";
	
	</script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    
    <script src="<c:url value="/resources/js/sensores.js" />"></script>
    
  </body>
</html>
