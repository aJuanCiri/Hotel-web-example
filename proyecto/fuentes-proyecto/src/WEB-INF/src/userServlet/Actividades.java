package userServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de obtener las actividades pertenecientes
 * a un usuario en concreto.
 */
public class Actividades extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;
	
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * Método doGet encargado de gestionar las peticiones de tipo GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("actividades");
	}

	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Obtenemos el email del usuario actual (sesion)
		String usuario = request.getParameter("usuario");
		try {
			// Conectamos con la base de datos para realizar la consulta
			ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
					"1521","vicious","*****","*****");
			ot.connect();

			OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
			// Invocamos al metodo encargado de listar las actividades
			ArrayList<ResultadoBusqueda> resultados = 
				ecd.listaActividades(usuario);
			// Añadimos las actividades obtenidas con la busqueda
			request.setAttribute("resultados", resultados);
			RequestDispatcher respuesta = 
				request.getRequestDispatcher("actividadesOrdenadas.jsp");
			respuesta.forward(request, response);
		} catch (InstantiationException e) {
			System.out.println("Error: " + e.getMessage());
			response.sendRedirect("paginaError.html");
		} catch (IllegalAccessException e) {
			System.out.println("Error: " + e.getMessage());
			response.sendRedirect("paginaError.html");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			response.sendRedirect("paginaError.html");
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			response.sendRedirect("paginaError.html");
		} finally {
			if (ot != null) {
				ot.disconnect();
			}
		}
	}
}
