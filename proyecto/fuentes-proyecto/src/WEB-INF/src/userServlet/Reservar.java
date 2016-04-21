package userServlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/*
* Servlet encargado de realizar una reserva del hotel
* indicado por el usuario (la fecha y habitacion indicada).
*/
public class Reservar extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;

	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Obtenemos los valores necesarios para realizar la reserva
		String id = request.getParameter("id");
		String usuario = request.getSession().getAttribute("email").toString();
		String mes = request.getSession().getAttribute("mesResultado").toString();
		String ano = request.getSession().getAttribute("anoResultado").toString();
		try {
			try {
				// Conexion con la BD
				ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
						"1521","vicious","a682531","13145gkJ");
				ot.connect();

				OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
				// Operacion encargada de añadir en la base de datos una nueva reserva
				ecd.reservar(usuario, Integer.parseInt(id), Integer.parseInt(mes), Integer.parseInt(ano));
				// Reedireccionamos la salida al JSP correspondiente
				RequestDispatcher respuesta = request.getRequestDispatcher("confirmar.jsp");
				respuesta.forward(request, response);
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
			response.sendRedirect("paginaError.html");
		}
		response.sendRedirect("index.html");
	}
	
	/**
	 * Método doGet encargado de gestionar las peticiones de tipo GET
	 */
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Hello friend!");
    }
}
