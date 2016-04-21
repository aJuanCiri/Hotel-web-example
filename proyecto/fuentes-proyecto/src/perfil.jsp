<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
  <%@page contentType="text/html" pageEncoding="UTF-8"%>
  <meta charset="UTF-8">
    <title>Mi Perfil</title>
  <link rel="stylesheet" href="estilo.css">
</head>
<%
  String email = session.getAttribute("email").toString();
  ArrayList<String> datosUsuario = (ArrayList<String>) session.getAttribute("datosUsuario");
%>
<body>
  <div id="wrapper">
    <div id="header">
      <p class="cabecera"><a href="index.jsp"><img 
      src="images/logo.png"></a></p>
    </div>
    <div id="formularioRegistro">
      <div id="textoRegistro">
        <br/>       
        <table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 300px;">
          <td><a href="perfil.jsp"><input class="botonesInicio" type="botton" id="botonCabecera" value="Perfil" readonly/></a></td>
          <td><form action="http://localhost:8090/HotelLink/Actividades" method="post">
		  <input type="hidden" value=<%=session.getAttribute("email").toString()%> name="usuario" />
		  <input class="botonesInicio" type="submit" id="botonCabecera" value="Mis Actividades" readonly/>
		  </form>
		  </td>
          <td>
			<form action="http://localhost:8090/HotelLink/LogOut" method="post">
				<input class="botonesInicio" type="submit" id="botonCabecera" value="Cerrar Sesión" readonly/>
			</form>
		  </a></td>
          <td><input onclick="confirmacion()" class="botonesInicio" type="botton" id="botonCabecera" value="Borrar Cuenta" readonly/></td>
        </table><hr/>
        <table><td width="350">
        <form action="http://localhost:8090/HotelLink/ActualizarUsuario" method="post">
          <label for="IdNombre">Nombre*:</label><br/><input id="IdNombre" type="text" name="name" value=<%=datosUsuario.get(0)%> /><br/>
          <label for="IdApellidos">Apellidos*:</label><br/><input id="IdApellidos" type="text" name="surName" value=<%=datosUsuario.get(1)%> /><br/>

          <fieldset style="border: 0px; padding: 0em;">
              <legend style="padding:0px;border-radius:0px;margin-left:0px;">Sexo:</legend>
            <input id="IdHombre" type="radio" name="sex" checked="checked" value="H"/><label for="IdHombre">Hombre</label>
            <input id="IdMujer" type="radio" name="sex" value="M"/><label for="IdMujer">Mujer</label><br/>
          </fieldset>

          <label for="IdCorreo">Correo electrónico*:</label><br/><input id="IdCorreo" type="email" name="correo" value=<%=email%> /><br/>
          <label for="IdReCorreo">Repetir correo electrónico*:</label><br/><input id="IdReCorreo" type="email" name="reCorreo"/><br/>
          </td><td>
          <label for="IdClave">Contraseña*:</label><br/><input id="IdClave" type="password" name="password" value=<%=datosUsuario.get(4)%> /><br/>
          <label for="IdReClave">Repetir contraseña*:</label><br/><input id="IdReClave" type="password" name=<%=datosUsuario.get(4)%> /><br/>        
          <label for="IdDireccion">Dirección*:</label><br/><input id="IdDireccion" type="text" name="direccion" value=<%=datosUsuario.get(7)%> /><br/>
          <label for="IdLocalidad">Localidad*:</label><br/>
          <select name=”Localidad:">  
                  <option value="zaragoza">Zaragoza</option> 
                  <option value=madrid">Madrid</option> 
                  <option value="barcelona">Barcelona</option>
          </select><br/>
          <label for="IdCpostal">Código postal:</label><br/><input id="IdCpostal" type="text" name="cPostal" value=<%=datosUsuario.get(6)%> />
          </td></table>
          <p class="nota">Nota: Los campos marcados con un asterisco deben rellenarse de forma obligatoria.</p>
          <div class="botonesFormulario">
            <input type="submit"/>
            <br/>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script>
  function confirmacion(){
    var r = confirm("¿Está seguro de que desea borrar su cuenta?");
    if(r==true){
      window.location.href = "http://localhost:8090/HotelLink/EliminarUsuario";
    }
    else{
      window.location.href = "perfil.jsp";
    }
  }
  </script>
</body>
</html>
