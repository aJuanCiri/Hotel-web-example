<html>
<head>
  <%@page contentType="text/html" pageEncoding="UTF-8"%>
  <meta charset="UTF-8">
    <title>Buscador de Hoteles</title>
  <link rel="stylesheet" href="estilo.css">
</head>

<body>
  <div id="wrapper">
    <%
      try {
      String email = session.getAttribute("email").toString();
      %>
        <div id="header">
          <p class="cabecera"><a href="index.jsp"><img 
          src="images/logo.png" border="0"></a></p>
          <table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 300px;">
		  <form action="http://localhost:8090/HotelLink/LogOut" method="post">
              <td><a href="perfil.jsp"><input class="botonesInicio" type="botton" id="botonCabecera" value="Perfil" readonly/></a></td>
              <td>
				<input class="botonesInicio" type="submit" id="botonCabecera" value="Cerrar Sesión" readonly/>
			  </td>
		  </form>
          </table><br/>
        </div>
      <% } catch (NullPointerException e) { %>
        <div id="header">
          <p class="cabecera"><a href="index.jsp"><img 
          src="images/logo.png" border="0"></a></p>
          <table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 300px;">
              <td><a href="registro.html"><input class="botonesInicio" type="botton" id="botonCabecera" value="Registrarse" readonly/></a></td>
              <td><a href="login.html"><input class="botonesInicio" type="botton" id="botonCabecera" value="Iniciar Sesión" readonly/></a></td>
          </table><br/>
        </div>
        <% } %>
    <hr/><br/>
    <div id="content">
	  <b><a style="color:red"><%= request.getParameter("msg")!=null?request.getParameter("msg"):""%></a></b>
      <h1>¡Buscamos por tí!</h1>
	  <form action="http://localhost:8090/HotelLink/Consultar" method="post">
      <input class="busqueda" id="IdBusqueda" type="text" name="busqueda" size="45" placeholder="Ejemplo: Zaragoza"/>
      <input class="buscar" type="submit" value="Buscar"/></a>
	  </form>
    </div>
    <div id="footer">
      <p class="textoFooter">Copyright 2015 HotelLink</p>
    </div>
  </div>
</body>
</html>
