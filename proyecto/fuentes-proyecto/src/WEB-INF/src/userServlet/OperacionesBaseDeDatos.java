package userServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;

/**
* Clase que implementa todas las operaciones encargadas
* de realizar consultas sobre la base de datos.
*/
public class OperacionesBaseDeDatos {

	private OracleTemplate ot;
	public OperacionesBaseDeDatos(OracleTemplate ot) {
		this.ot = ot;
	}

	/**
	* Método encargado de rellenar la base de datos para poder realizar consultas
	*/
	public void rellenarDatos(){
		ot.executeSentence("INSERT INTO LOCALIZACION (ID, PAIS, LOCALIDAD, DIRECCION, CAUTONOMA, CIUDAD) VALUES ('1', 'Spain', 'Zaragoza','C/Inventada', 'Aragon', 'Zaragoza')");
		ot.executeSentence("INSERT INTO LOCALIZACION (ID, PAIS, LOCALIDAD, DIRECCION, CAUTONOMA, CIUDAD) VALUES ('2', 'Spain', 'Madrid', 'C/Inventada2', 'Madrid', 'Madrid')");
		ot.executeSentence("INSERT INTO FECHA (ID, DIA, MES, AC1O) VALUES ('101215','10','12','2015')");
		ot.executeSentence("INSERT INTO FECHA (ID, DIA, MES, AC1O) VALUES ('111215','11','12','2015')");
		ot.executeSentence("INSERT INTO FECHA (ID, DIA, MES, AC1O) VALUES ('121215','12','12','2015')");
		ot.executeSentence("INSERT INTO HOTEL (ID, NOMBRE, ESTRELLAS, DESCRIPCION, LOCALIZACION_ID) VALUES ('1','Hotel Unizar', '4','Hotel de la universidad de Zaragoza','1')");
		ot.executeSentence("INSERT INTO HOTEL (ID, NOMBRE, ESTRELLAS, DESCRIPCION, LOCALIZACION_ID) VALUES ('2','Hotel Expo', '5','Hotel de lujo en la Expo','1')");
		ot.executeSentence("INSERT INTO HOTEL (ID, NOMBRE, ESTRELLAS, DESCRIPCION, LOCALIZACION_ID) VALUES ('3','Hotel Pilar', '3','Hotel en el casco viejo de Zaragoza','1')");
		ot.executeSentence("INSERT INTO HOTEL (ID, NOMBRE, ESTRELLAS, DESCRIPCION, LOCALIZACION_ID) VALUES ('4','Hotel Centro Madrid', '4','Hotel en el centro de Madrid','2')");
		ot.executeSentence("INSERT INTO TIPO_HABITACION (TIPO) VALUES ('individual')");
		ot.executeSentence("INSERT INTO TIPO_HABITACION (TIPO) VALUES ('doble')");
		ot.executeSentence("INSERT INTO TIPO_HABITACION (TIPO) VALUES ('triple')");
		ot.executeSentence("INSERT INTO TIPO_HABITACION (TIPO) VALUES ('cuadruple')");
		ot.executeSentence("INSERT INTO DISPONE (ID, HOTEL_ID, TIPO_HABITACION, FECHA, PRECIO) VALUES ('1','1','doble','101215','47')");
		ot.executeSentence("INSERT INTO DISPONE (ID, HOTEL_ID, TIPO_HABITACION, FECHA, PRECIO) VALUES ('2','1','doble','101215','49')");
		ot.executeSentence("INSERT INTO DISPONE (ID, HOTEL_ID, TIPO_HABITACION, FECHA, PRECIO) VALUES ('3','2','doble','101215','106')");
		ot.executeSentence("INSERT INTO DISPONE (ID, HOTEL_ID, TIPO_HABITACION, FECHA, PRECIO) VALUES ('4','4','doble','101215','75')");
		ot.executeSentence("INSERT INTO DISPONE (ID, HOTEL_ID, TIPO_HABITACION, FECHA, PRECIO) VALUES ('5','4','individual','111215','77')");
	}

	/**
	* Método encargado de actualizar la información de perfil del usuario.
	* Si recibe algun parametro con valo NULL no lo modifica.
	*/
	public void actualizarUsuario(String nombre, String apellidos, String sexo,
			String correo, String password, String localidad, String postal, 
			String direccion) {
		if (nombre!=null) {
			// Si nombre es distinto de null lo cambia
			String sentenciaSQL = "UPDATE USUARIO SET NOMBRE='" +
			nombre + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
		if (apellidos!=null) {
			// Si apellidos es distinto de null lo cambia
			String sentenciaSQL = "UPDATE USUARIO SET APELLIDOS='" +
			apellidos + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
		if (sexo!=null) {
			// Si sexo es distinto de null lo cambia
			String sentenciaSQL = "UPDATE USUARIO SET SEXO='" +
			sexo + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
		if (password!=null) {
			// Si password es distinto de null lo cambia
			String sentenciaSQL = "UPDATE USUARIO SET PASSWORD='" +
			password + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
		if (localidad!=null) {
			// Si la localidad es distinta de null la cambia
			String sentenciaSQL = "UPDATE USUARIO SET LOCALIDAD='" +
			localidad + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
		if (postal!=null) {
			// Si el cPostal es distinto de null lo cambia
			String sentenciaSQL = "UPDATE USUARIO SET CODIGO_POSTAL='" +
			postal + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
		if (direccion!=null) {
			// SI la direccion es distinta de null la cambia
			String sentenciaSQL = "UPDATE USUARIO SET DIRECCION='" +
			direccion + "' WHERE EMAIL='" + correo + "'";
			ot.executeSentence(sentenciaSQL);
		}
	}
	
	/**
	* Método encargado de consultar en la base de datos si existe
	* un usuario con el correo y la password dadas. En caso de existir
	* devuelve true, en caso contrario devuelve false.
	*/
	public boolean consultarUsuario(String correo, String password) {
		String sentenciaSQL = "SELECT COUNT(*) FROM USUARIO WHERE EMAIL='" +
		correo + "' AND PASSWORD='" + password + "'";
		int resultado = Integer.parseInt(ot.executeQueryReturn(sentenciaSQL).get(0));
		if (resultado==1) return true;
		else return false;
	}
	
	/**
	* Método encargado de realizar una consulta en la base de datos sobre los hoteles
	* disponibles en las fechas, ciudad y para el numero de personas
	* seleccionadas por el usuario en la búsqueda. Devuelve una lista vacia si no 
	* existe ningun hotel que cumpla las caracteristicas dadas.
	*/
	public ArrayList<ResultadoBusqueda> hacerConsulta(String ciudad, int dia, int mes, int ano, int numPersonas){
		// Seleccion del tipo de habitacion
		String tipo_habitacion = "familiar";
		switch(numPersonas){
		case 1 : tipo_habitacion = "individual"; break;
		case 2 : tipo_habitacion = "doble"; break;
		case 3 : tipo_habitacion = "triple"; break;
		case 4 : tipo_habitacion = "cuadruple"; break;
		}
		// Sentencia SQL a realizr en la base de datos
		String sentenciaSQL = "SELECT DISTINCT hotel.nombre FROM HOTEL,localizacion,dispone,fecha WHERE "+
								"localizacion.ciudad='"+ciudad+"' AND "+
								"localizacion.id=hotel.localizacion_id AND "+
								"hotel.id=dispone.hotel_id AND "+
								"dispone.tipo_habitacion='"+tipo_habitacion+"' AND "+
								"dispone.fecha=fecha.id AND "+
								"fecha.dia='"+dia+"' AND fecha.mes='"+mes+"' AND fecha.ac1o='"+ano+"'";
		// ArrayList en el que se guardan los resultados
		ArrayList<ResultadoBusqueda> resultados = new ArrayList<ResultadoBusqueda>();
		// Arraylist en el que se guardan unicamente los nombres de los hoteles
		ArrayList<String> resultadosString = ot.executeQueryReturn(sentenciaSQL);
		int numResultados = resultadosString.size();
		while(numResultados>0){
			// Recorremos los nombres de los hoteles y guardamos toda su informacion asociada
			sentenciaSQL = "SELECT hotel.nombre, estrellas, descripcion, tipo_habitacion, precio, hotel.id FROM hotel, localizacion, fecha, dispone WHERE " + 
							"hotel.nombre='" + resultadosString.get(numResultados-1) + "' AND localizacion.id=hotel.localizacion_id AND"
									+ " dispone.hotel_id=hotel.id AND"
									+ " fecha.dia='"+dia+"' AND fecha.mes='"+mes+"' AND fecha.ac1o='"+ano+"'";
			ArrayList<String> res = ot.executeQueryReturn(sentenciaSQL);		
			resultados.add(new ResultadoBusqueda(res.get(0),Integer.parseInt(res.get(1)),res.get(2),ciudad,
					dia + "-" + mes + "-" + ano, res.get(3),
					Integer.parseInt(ot.executeQueryReturn("SELECT COUNT(*) FROM DISPONE,HOTEL WHERE hotel.nombre='" + res.get(0) + "' AND dispone.hotel_id=hotel.id").get(0)),
					Double.parseDouble(res.get(4)),Integer.parseInt(res.get(5))));
			numResultados--;
		}
		return resultados;
	}

	/**
	* Método encargado de listar los usuarios registrados en la base de datos
	*/
	private void listarUsuarios() {
		ot.executeQuery("SELECT * FROM USUARIO");
	}
	
	/**
	* Método encargado de consultar en la base de datos las actividades
	* realizadas por un usuario en concreto durante todo su periodo
	* de actividad. Devuelve null en caso de no tener reservas.
	*/
	public ArrayList<ResultadoBusqueda> listaActividades (String usuario){
		// Sentencia SQL que se realiza en la base de datos
		String sentenciaSQL = "SELECT hotel.nombre FROM actividad,usuario,hotel WHERE "+
								"email='"+usuario+"' AND actividad.usuario=usuario.email AND "
								+ "hotel.id=actividad.hotel_id";
		// ArrayList donde se guardan los resultados obtenidos
		ArrayList<ResultadoBusqueda> resultados = new ArrayList<ResultadoBusqueda>();
		// ArrayList donde se guardan los nombres de los hoteles reservados
		ArrayList<String> resultadosString = ot.executeQueryReturn(sentenciaSQL);
		int numResultados = resultadosString.size();
		while(numResultados>0){
			// Recorremos el arrayList de los nombres de hoteles para obtener toda su informacion
			sentenciaSQL = "SELECT hotel.nombre, estrellas, descripcion, tipo_habitacion FROM hotel, actividad WHERE " + 
							"hotel.nombre='" + resultadosString.get(numResultados-1) + "' AND hotel.id=actividad.hotel_id"; 
			ArrayList<String> res = ot.executeQueryReturn(sentenciaSQL);
			// Añadimos la información al ArrayList resultados		
			resultados.add(new ResultadoBusqueda(res.get(0),Integer.parseInt(res.get(1)),res.get(2),null,
					null, res.get(3),-1,-1,-1));
			numResultados--;
		}
		return resultados;
	}

	/**
	* Método encargado de consultar en la base de datos las actividades
	* realizadas por un usuario en concreto durante el periodod de tiempp
	* que este señalice. Devuelve null en caso de no tener reservas.
	*/
	public ArrayList<ResultadoBusqueda> listaActividadesOrdenadas (String usuario, String diaEnt, String mesEnt, String anoEnt,
		String diaEnt2, String mesEnt2, String anoEnt2){
		// Setencia SQL a ejecutar en la base de datos
		String sentenciaSQL = "SELECT hotel.nombre FROM actividad,usuario,hotel WHERE email='javier@correo.com' AND " +
		"actividad.usuario=usuario.email AND hotel.id=actividad.hotel_id AND " +
		"(((" + anoEnt + "<actividad.fecha2) AND (actividad.fecha2<" + anoEnt2 + ")) OR ((" + anoEnt + "=actividad.fecha2) AND " +
		"(" + anoEnt2 + "!=actividad.fecha2) AND (" + mesEnt + "<=actividad.fecha1)) OR ((" + anoEnt + "=fecha2) AND " +
		"(" + anoEnt2 + "=fecha2) AND (" + mesEnt + "<=fecha1) AND (fecha1<=" + mesEnt2 + ")) OR ((" + anoEnt2 + "=fecha2) AND " +
		"(fecha2!=" + anoEnt + ") AND (fecha1<" + mesEnt2 + ")))";
		// ArrayList donde se guardan los resultados de la busqueda
		ArrayList<ResultadoBusqueda> resultados = new ArrayList<ResultadoBusqueda>();
		// ArrayList donde se guardan los nombres de los hoteles reservados en el periodo de tiempo marcado
		ArrayList<String> resultadosString = ot.executeQueryReturn(sentenciaSQL);
		int numResultados = resultadosString.size();
		while(numResultados>0){
			// Recorremos ArrayList de nombres para obtener toda su informacion y guardarla en resultados.
			sentenciaSQL = "SELECT hotel.nombre, estrellas, descripcion, tipo_habitacion FROM hotel, actividad WHERE " + 
							"hotel.nombre='" + resultadosString.get(numResultados-1) + "' AND hotel.id=actividad.hotel_id"; 
			ArrayList<String> res = ot.executeQueryReturn(sentenciaSQL);		
			resultados.add(new ResultadoBusqueda(res.get(0),Integer.parseInt(res.get(1)),res.get(2),null,
					null, res.get(3),-1,-1,-1));
			numResultados--;
		}
		return resultados;
	}
	
	/**
	* Método encargado de realizar una reserva en la base de datos
	* con la id del hotel, nombre de usuario y fecha pasados por parametros.
	*/
	public void reservar(String usuario, int id, int mes, int ano){
		Random r = new Random();  //Simula un posible generador de ids automaticas de cada reserva
		ot.executeSentence("INSERT INTO ACTIVIDAD(ID,USUARIO,HOTEL_ID,TIPO_HABITACION,FECHA1,FECHA2,METODO_PAGO)"
				+ " VALUES (?,?,?,?,?,?,?)", 
				r.nextInt(10000), usuario,id, "doble", mes, ano, "pago" );  //TODO En el futuro ofrecera mas información
	}

	/**
	* Método encargado de devolver un arrayList con todos los valores
	* de los parámetros de perfil del usuario. Si un parametro no tiene
	* valor devuelve valor null.
	*/
	public ArrayList<String> completarPerfil(String correo) {
		ArrayList<String> datosUsuario = new ArrayList<String>();
		// Obtenemos nombre del usuario
		String sentenciaSQL = "SELECT NOMBRE FROM USUARIO WHERE EMAIL='" + correo + "'";
		ArrayList<String> res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos apellidos del usuario
		sentenciaSQL = "SELECT APELLIDOS FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos apellidos del usuario
		sentenciaSQL = "SELECT APELLIDOS FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos sexo del usuario
		sentenciaSQL = "SELECT SEXO FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos password del usuario
		sentenciaSQL = "SELECT PASSWORD FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos localidad del usuario
		sentenciaSQL = "SELECT LOCALIDAD FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos cPostal del usuario
		sentenciaSQL = "SELECT CODIGO_POSTAL FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Obtenemos la direccion del usuario
		sentenciaSQL = "SELECT DIRECCION FROM USUARIO WHERE EMAIL='" + correo + "'";
		res = ot.executeQueryReturn(sentenciaSQL);
		datosUsuario.add(res.get(0));
		// Devolvemos arrayList con todos los valores
		return datosUsuario;
	}

	/**
	* Método encargado de eliminar de la base de datos el usuario 
	* cuyo email sea igual al pasado por parametros.
	*/
	public void eliminarUsuario(String email) {
		ot.executeSentence("DELETE FROM USUARIO WHERE EMAIL='"+email+"'");
	}

	/**
	* Método encargado de insertar un nuevo usuario en la base de datos
	* con los valores pasados como parámetros. Si ya existe un usuario
	* con email dado se cancela la operación y se le notifica al usuario.
	*/
	public boolean insertarUsuario(String nombre, String apellidos, String sexo,
			String correo, String password, String localidad, String postal, 
			String direccion) {
		// Comprobamos si ya existe un usuario con el mismo email
		if(Integer.parseInt((ot.executeQueryReturn("SELECT COUNT(*) FROM USUARIO WHERE EMAIL='"+correo+"'").get(0)))==0){
			// En caos de no existir lo añadimos a la base de datos
					ot.executeSentence("INSERT INTO USUARIO(EMAIL,NOMBRE,APELLIDOS,SEXO,PASSWORD,"
				+ "PAIS,LOCALIDAD,DIRECCION,CODIGO_POSTAL) VALUES (?,?,?,?,?,?,?,?,?)", 
				correo, nombre, apellidos, sexo, password, localidad, localidad,
				direccion, postal);
			    return true;
		} return false;
	}	
}
