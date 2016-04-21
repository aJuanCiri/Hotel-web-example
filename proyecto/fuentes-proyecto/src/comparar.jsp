<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="userServlet.ResultadoBusqueda"%>
<html>
<head>
	<%@page contentType="text/html" pageEncoding="UTF-8"%>
  	<title>Buscador de Hoteles</title>
	<link rel="stylesheet" href="estilo.css">
	<SCRIPT LANGUAGE="JavaScript">
		function popUp(URL) {
		day = new Date();
		id = day.getTime();
		eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=1000,height=600,left = 460,top = 240');");
		}
	</script>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<p class="cabecera"><a href="index.jsp"><img 
			src="images/logo.png"></a></p><hr/><br/>
		</div>
		<br/>
		<a href="javascript:history.back()"><img src="images/atras.png" title="Volver"></a>
		<br/>
		<table id="tablaComparacion">
			<tr>			
				<td>
				Nombre
				</td>
				<td>
				Calificación
				</td>
				<td>
				Descripción
				</td>
				<td>
				Precio/Noche
				</td>
				<td>
				Hab. disponibles
				</td>
			</tr>
			<%
			ArrayList<ResultadoBusqueda> resultados = (ArrayList<ResultadoBusqueda>) session.getAttribute("resultadosComparar");				
			if(resultados!=null){
				int numResultados = resultados.size();
				if (numResultados==0) %> No se han obtenido resultados. <%
				for(int i=0; i<numResultados; i++){
				%>
				<tr>
					<td>				
						<% out.println(resultados.get(i).getNombre());%>
					</td>
					<td>						
						<% out.println(resultados.get(i).getEstrellas());%>/5
					</td>
					<td>
						<% out.println(resultados.get(i).getDescripcion()); %>
					<td>
						<% out.println(resultados.get(i).getPrecio()); %>
					</td>
					<td>
						<% out.println(resultados.get(i).getHabDisponibles()); %>
					</td>
					<td>
						<% 
						try {
							String email = session.getAttribute("email").toString(); 
						%>
						<form action="http://localhost:8090/HotelLink/Reservar" method="post">
						<input type="hidden" value="<%=resultados.get(i).getId()%>" name="id" />
						<input type="hidden" value="<%=email%>" name="usuario" />
						<input class="buscar" type="submit" value="Reservar"/>
						</form>
						<%
						} catch(NullPointerException e) { %>
							<a href="login.html"><input class="buscar" type="submit" value="Reservar"</a>
						<% } %>
					</td>
				</tr>
			<%}
			} else { %>
				No se han obtenido resultados.
			<%}				
			%>
		</table>
	</div>
</body>
</html>
