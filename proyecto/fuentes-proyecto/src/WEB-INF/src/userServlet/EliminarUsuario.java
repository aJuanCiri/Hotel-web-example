package userServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/*
* Servlet encargado de eliminar el usuario actual
* de la base de datos.
*/
public class EliminarUsuario extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;

	/**
	 * Método doGet encargado de gestionar las peticiones de tipo GET
	 */
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Obtenemos el email del usuario actual
		String email = request.getSession().getAttribute("email").toString();			
		try {
			// Conexion a la base de datos
			ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
					"1521","vicious","*****","*****");
			ot.connect();
			
			OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
			// Ejectuamos el metodo encargado de eliminar usuario de la bd
			ecd.eliminarUsuario(email);
			// Eliminamos la sesion actual
			request.getSession().invalidate();
			// Reedireccionamos a la pagina de inicio
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
}
