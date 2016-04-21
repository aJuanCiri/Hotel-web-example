package userServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
* Recibe los par‡metros introducidos por el usuario
* en el formulario y comprueba si estos son correctos.
* Si lo son, crea un usuario con dichos parametros y lo 
* ncluye en la DB, en caso contrario redirecciona al
* usuario a la pagina de error.
*/
public class InsertUser extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;

	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean validos = true; // Indica si los parametros son validos
		// Guardamos los paramentros introducidos por el usuario
		String nombre = request.getParameter("name");
		String apellidos = request.getParameter("surName");
		String sexo = request.getParameter("sex");
		String correo = request.getParameter("correo");
		String reCorreo = request.getParameter("reCorreo");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String direccion = request.getParameter("direccion");
		String localidad = request.getParameter("localidad");
		String postal = request.getParameter("cPostal");

		//Comprobamos si coinciden los valores de corre y contraseña
		if (!correo.equals(reCorreo) || 
				!password.equals(rePassword)) {
			// Si no coinciden los datos introducidos no son validos
			validos = false;
		}
		//Si el formulario esta correcto procesamos la solicitud
		if (validos) {
			try {			
				try {
					// Conexion a la base de datos
					ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
							"1521","vicious","*****","******");
					ot.connect();

					OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
					// Si se insetar el usuario con exito en la DB
					if(ecd.insertarUsuario(nombre, apellidos, sexo, 
							correo, password, localidad,
							postal, direccion)){
						// Devolvemos mensaje de exito
						response.sendRedirect("index.jsp?msg="+ nombre + ", has sido registrado correctamente");
					} else {
						// En caso contrario devolvemos mensaje de error
						response.sendRedirect("index.jsp?msg=ERROR: Ese correo ya ha sido registrado");
					}
				} catch (InstantiationException e) {
					System.out.println("Error: " + e.getMessage());
				} catch (IllegalAccessException e) {
					System.out.println("Error: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println("Error: " + e.getMessage());
				} catch (SQLException e) {
					System.out.println("Error: " + e.getMessage());
				} finally {
					if (ot != null) {
						ot.disconnect();
					}
				}
			} catch (Exception e) {
				//En caso de error vamos pagina de error
				response.sendRedirect("index.jsp");
			}
		}		
	}
}
