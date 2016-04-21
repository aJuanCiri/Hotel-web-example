package userServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 * Servlet encargado de comprobar si existe en la base 
 * de datos un usuario con el email y contraseña
 * insertados por el usuario en el formulario. Si existe
 * logea a este en la pagina web, en caso contrario muestra
 * mensaje de error
 */
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;
	
	public void init() throws ServletException {
		super.init();
	}
	
	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Obtenemos los valores insertados por el usuario en el formulario
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
					
		try {
			// Conexion a la BD
			ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
					"1521","vicious","a682531","13145gkJ");
			ot.connect();
			OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
			// Comprobamos si existe un usuario en la BD con ese nombre/password
			if (ecd.consultarUsuario(correo, password)) {
				//CREAMOS LA SESION SI NOS HEMOS LOGEADO
				request.getSession().setAttribute("email", correo);
				ArrayList<String> datosUsuario = ecd.completarPerfil(correo);
				request.getSession().setAttribute("datosUsuario", datosUsuario);
       			response.sendRedirect("index.jsp?msg="+ correo + ", has sido logeado correctamente");
			} else {
				// Si no existe reedirecionamos al login
				response.sendRedirect("login.html?msg=Error en el inicio de sesión. Revise los parametros introducidos");
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
	}
}
