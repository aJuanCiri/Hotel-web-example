<!DOCTYPE html>
<html>
<head>
	<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<meta charset="UTF-8">
  	<title>Buscador de Hoteles</title>
	<link rel="stylesheet" href="estilo.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<p class="cabecera"><a href="index.html"><img 
			src="images/logo.png"></a></p>
		</div>
		<h1>¡Su reserva se ha realizado satisfactoriamente!</h1>
		<form action="http://localhost:8090/HotelLink/Actividades" method="post">
			<input type="hidden" value="<%=session.getAttribute("email").toString()%>" name="usuario" />
			<input class="buscar" type="submit" value="Ver Reserva"/></a>
		</form>
	</div>
</body>
</html>
