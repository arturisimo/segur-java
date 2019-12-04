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
    <!-- Bootstrap core CSS -->
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
          <a class="navbar-brand" href="#">admin - segurJava</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">Clientes</a></li>
            <li><a href="#contact">Informes</a></li>
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
	
		<div class="jumbotron">
			<div class="container">
				<h1>hola ${userPrincipal.username}</h1>
				<p>Zona administador</p>
			</div>
		</div>
	</div><!-- /.container -->
	
	<div class="container">
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>nombre</th><th>email</th><th>dni</th><th>cuenta</th><th>direccion</th><th>policia</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	
	
	</div>
	

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/js/admin.js" />"></script> 
    
  </body>
</html>
