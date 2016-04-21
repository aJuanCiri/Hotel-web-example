<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="userServlet.ResultadoBusqueda"%>
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
      <p class="cabecera"><a href="index.jsp"><img 
      src="images/logo.png"></a></p>
    </div>
    <div id="filtrosCabecera">
			<form action="http://localhost:8090/HotelLink/ActividadesOrdenadas" method="post">
					<table class="filtrosPrincipales" align="center">					
						<td>
							<a>Fecha inicio:</a>
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

							<a>Fecha final:</a>
							<select name="DiaEnt2">  
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
							<select name="MesEnt2">  
									<option value="1">01</option><option value="2">02</option><option value="3">03</option>
									<option value="4">04</option><option value="5">05</option><option value="6">06</option>
									<option value="7">07</option><option value="8">08</option><option value="9">09</option>
									<option value="10">10</option><option value="11">11</option><option value="12">12</option>
							</select>
							<select name="AnoEnt2">  
									<option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option>
							</select>
						</td>						
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="Buscar">
						</td>
					</table>
				</form>
			</div>			
		</div>
    <div id="formularioRegistro">
      <div id="textoRegistro">
        <br/>       
        <table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 300px;">
          <td><a href="perfil.jsp"><input class="botonesInicio" type="botton" id="botonCabecera" value="Perfil" readonly/></a></td>
          <td>
			  <form action="http://localhost:8090/HotelLink/Actividades" method="post">
			  <input type="hidden" value=<%=session.getAttribute("email").toString()%> name="usuario" />
			  <input class="botonesInicio" type="submit" id="botonCabecera" value="Mis Actividades" readonly/>
			  </form>
		  </td>
        </table><hr/>
    </div>
		<div id="tablaResultados">
			<table class="tablaResultados">
				<col width="240">
				<col width="400">
				<%
				ArrayList<ResultadoBusqueda> resultados = (ArrayList<ResultadoBusqueda>) request.getAttribute("resultadosOrdenados");
				if(resultados!=null){
					int numResultados = resultados.size();
					if (numResultados==0) %> No se han obtenido resultados. <%
					for(int i=0; i<numResultados; i++){
					%>
					<tr>
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
					</tr>
				<%}
				} else { %>
					No se han obtenido resultados.
				<%}				
				%>
			</table>
		</div>
  </div>
</body>
</html>
