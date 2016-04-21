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
 * Servlet encargado de listar los hoteles buscados por
 * el usuario entre las fechas, localidad y numero de 
 * personas indiacados.
 */
public class Consultar extends HttpServlet {
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
		response.getWriter().println("consultar");
	}

	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Obtenemos los valores indicados por el usuario en el formulario
		String ciudad = request.getParameter("busqueda");
		String diaEnt = request.getParameter("DiaEnt");
		String mesEnt = request.getParameter("MesEnt");
		String anoEnt = request.getParameter("AnoEnt");
		String numPersonas = request.getParameter("personas");
		if (diaEnt==null | mesEnt==null | anoEnt==null | numPersonas==null){
			diaEnt="01"; mesEnt="01"; anoEnt="2015"; numPersonas="2";
		}
		try {
			// Conexion a la base de datos
			ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
					"1521","vicious","a682531","13145gkJ");
			ot.connect();

			OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
			// Obtenemos los hoteles que cumplan las caracteristicas dadas
			ArrayList<ResultadoBusqueda> resultados = 
				ecd.hacerConsulta(ciudad, Integer.parseInt(diaEnt), Integer.parseInt(mesEnt), Integer.parseInt(anoEnt),Integer.parseInt(numPersonas));
			request.getSession().setAttribute("mesResultado", mesEnt);
			request.getSession().setAttribute("anoResultado", anoEnt);
			request.getSession().setAttribute("resultados", resultados);
			RequestDispatcher respuesta = 
				request.getRequestDispatcher("resultados.jsp?lugar=" + ciudad);
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
	}
}
