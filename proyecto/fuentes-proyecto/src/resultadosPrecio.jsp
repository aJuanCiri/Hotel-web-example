<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
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
			<div id="filtrosCabecera">
			<form action="http://localhost:8090/HotelLink/Consultar" method="post">
					<input class="filtroLocalizacion" id="IdBusqueda" name="busqueda" value="<%=request.getParameter("lugar")%>"/><br/>
					<table class="filtrosPrincipales" align="center">					
						<td>
							<a>Fecha de reserva:</a>
							<select name="DiaEnt">  
									<option value="1">01</option><option value="2">02</option><option value="3">03</option>
									<option value="4">04</option><option value="5">05</option><option value="6">06</option>
									<option value="7">07</option><option value="8">08</option><option value="9">09</option>
									<option value="10">10</option><option value="11">11</option><option value="12">12</option>
									<option value="13">13</option><option value="14">14</option><option value="15">15</option>
									<option value="16">16</option><option value="17">17</option><option value="18">18</option>
									<option value="19">19</option><option value="20">20</option><option value="21">21</option>
									<option value="22">22</option><option value="23">23</option><option value="24">24</option>
									<option value="25">25</option><option value="26">26</option><option value="27">27</option>
									<option value="28">28</option><option value="29">29</option><option value="30">30</option>
								<option value="31">31</option>
							</select>
							<select name="MesEnt">  
									<option value="1">01</option><option value="2">02</option><option value="3">03</option>
									<option value="4">04</option><option value="5">05</option><option value="6">06</option>
									<option value="7">07</option><option value="8">08</option><option value="9">09</option>
									<option value="10">10</option><option value="11">11</option><option value="12">12</option>
							</select>
							<select name="AnoEnt">  
									<option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option>
							</select>
						</td>						
						<td>
							&nbsp;&nbsp;<a>|&nbsp;&nbsp;Número de personas:</a>&nbsp;&nbsp;
							<select name="personas">  
									<option value="1">1</option> 
									<option value="2">2</option> 
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
							</select>
							<script type="text/javascript">
								document.getElementById('personas').value = "<?php echo $_GET['personas'];?>";
							</script>
						</td>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="Buscar">
						</td>
					</table>
				</form>
			</div>			
		</div>
		<br/>
		<div id="resultados">
			<table class="ordenacion">
				<td>
					<a href="resultadosPrecio.jsp">Ordenar por precio</a>
				</td>
			</table>
			<br/>
			<form action="http://localhost:8090/HotelLink/Comparar" method="post">
			<input class="buscar" type="submit" value="Comparar"/>
			<div id="tablaResultados">
				<table class="tablaResultados">
					<col width="20">
					<col width="240">
					<col width="400">
					<col width="150">
					<%
					List<ResultadoBusqueda> resultados = (ArrayList<ResultadoBusqueda>) session.getAttribute("resultados");
					Collections.sort(resultados);				
					if(resultados!=null){
						int numResultados = resultados.size();
						if (numResultados==0) %> No se han obtenido resultados. <%
						for(int i=0; i<numResultados; i++){
						%>
						<tr>
							<td>
								<input type="checkbox" name="<%=i%>" value="OK">
							</td>
							<td>				
								<a href="hotelEjemplo.html"><img
								src="images/<%=resultados.get(i).getNombre()%>.jpg" width="230" height="200" border="2" style="border-color:#2F4F4F"></a></p>						
							</td>
							<td>
								<div id="datosResultado">
									<img src="images/<%=resultados.get(i).getEstrellas()%>.png" width="200" height="35"></a>
									<h1><% out.println(resultados.get(i).getNombre());%></h1>
									<h3><% out.println(resultados.get(i).getDescripcion()); %></h3>
								</div>
							</td>
							<td>
								<div id="datosResultado">
									<h4><% out.println(resultados.get(i).getHabDisponibles()); %> habitacion/es disponibles</h4>
									<h1><% out.println(resultados.get(i).getPrecio()); %> €</h1>
									<% 
									try {
										String email = session.getAttribute("email").toString(); 
									%>
									</form>
									<form action="http://localhost:8090/HotelLink/Reservar" method="post">
									<input type="hidden" value="<%=resultados.get(i).getId()%>" name="id" />
									<input type="hidden" value="<%=email%>" name="usuario" />
									<input class="buscar" type="submit" value="Reservar"/>
									</form>
									<%
									} catch(NullPointerException e) { %>
            							<a href="login.html"><input class="buscar" type="button" value="Reservar"</a>
        							<% } %>
								</div>
							</td>
						</tr>
					<%}
					} else { %>
						No se han obtenido resultados.
					<%}				
					%>
				</table>
			</div>
			</form>
		</div>
	</div>
</body>
</html>
