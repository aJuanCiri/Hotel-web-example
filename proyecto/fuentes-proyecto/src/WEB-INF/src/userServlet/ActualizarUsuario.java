package userServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 * Servlet encargado de actualizar los valores del perfil
 * del usuario de la sesion actual.
 */
public class ActualizarUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;

	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Obtenemos los valores de cada parametro del perfil del usuario
		String email = request.getSession().getAttribute("email").toString();
		String nombre = request.getParameter("name");
		String apellidos = request.getParameter("surName");
		String sexo = request.getParameter("sex");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		String direccion = request.getParameter("direccion");
		String localidad = request.getParameter("localidad");
		String postal = request.getParameter("cPostal");
			
		try {
			// Conectamos con la base de datos
			ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
					"1521","vicious","*****","*****");
			ot.connect();
			OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
			// Ejecuta el codigo encargado de actualizar el perfil de usuario
			ecd.actualizarUsuario(nombre, apellidos, sexo, 
					email, password, localidad,
					postal, direccion);	
			// Reedirecionamos a la página de inicio
			response.sendRedirect("index.jsp");				
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
	
	/**
	 * Método doGet encargado de gestionar las peticiones de tipo GET
	 */
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello friend!");
    }
}
