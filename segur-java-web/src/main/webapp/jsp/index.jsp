<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">

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
          </ul>
          <ul class="nav navbar-nav navbar-right">
			<li>	
				<a href="https://github.com/arturisimo/segur-java" title="github.com/arturisimo/segur-java"><img class="github" src='<c:url value="/resources/img/github.ico" />'></a>
			</li>
			<li><a href="#" id="actionRegistro">registro</a></li>
			<li><a href="<c:url value="/cliente" />"><span class="glyphicon glyphicon-off"></span> </a></li>
		  </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <div class="container">
		<div class="jumbotron">
			<div class="container">
				<h1>hola pública</h1>
			</div>
		</div>
		
		<div id="containerRegistro" class="hidden">
			
			<div id="messageKO" class="alert alert-danger hidden">
				<strong>Error:</strong><span id="errorMessage"></span>
		    </div>
		    <div id="messageOK" class="alert alert-success hidden">
				<strong>OK:</strong><span id="message"></span>
		    </div>
			
			<form action="" method="POST" role="form">
				<legend>Datos de alta como cliente</legend>
			
				<div class="form-group">
					<label for="usuario">Usuario</label>
					<input type="text" class="form-control" id="usuario" placeholder="usuario">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input type="password" class="form-control" id="password" placeholder="usuario">
				</div>
				<div class="form-group">
					<label for="email">Email</label>
					<input type="text" class="form-control" id="email" placeholder="email">
				</div>
				<div class="form-group">
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" id="nombre" placeholder="nombre">
				</div>
				<a id="altaAction" href="#" class="btn btn-primary">Alta</a>
			</form>
			
		
		</div>
    </div><!-- /.container -->
	
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/clientes.js" />"></script>
  </body>
</html>
