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
 * Servlet encargado de obtener las actividades ordenadas pertenecientes
 * a un usuario en concreto (usuario de las sesion)
 */
public class ActividadesOrdenadas extends HttpServlet {
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
		/**
		* Obtenemos los valores de la fecha de inicio y fin
		* de la búsqueda introducidos por el usuario
		*/
		String usuario = request.getParameter("usuario");
		String diaEnt = request.getParameter("DiaEnt");
		String mesEnt = request.getParameter("MesEnt");
		String anoEnt = request.getParameter("AnoEnt");
		String diaEnt2 = request.getParameter("DiaEnt2");
		String mesEnt2 = request.getParameter("MesEnt2");
		String anoEnt2 = request.getParameter("AnoEnt2");

		try {
			// Conectamos con la base de datos
			ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
					"1521","vicious","a682531","13145gkJ");
			ot.connect();

			OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
			// Invocamos el método que devuelve las actividades entre 2 fechas dadas
			ArrayList<ResultadoBusqueda> resultados = ecd.listaActividadesOrdenadas(
				usuario, diaEnt, mesEnt, anoEnt, diaEnt2, mesEnt2, anoEnt2);
			// Guardamos el resultado para mostrarlo con el JSP
			request.setAttribute("resultadosOrdenados", resultados);
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
