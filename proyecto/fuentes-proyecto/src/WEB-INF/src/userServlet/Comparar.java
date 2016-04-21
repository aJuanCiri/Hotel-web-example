package userServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet encargado de obtener los hoteles marcados por
 * el usuario para comparar de la lista de hoteles que 
 * cumplen las características indicadas por el usuario.
 */
public class Comparar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * Método doPost encargado de gestionar las peticiones de tipo POST
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			@SuppressWarnings("unchecked")
			// Lista de hoteles que cumplen las caracteristicas
			ArrayList<ResultadoBusqueda> resultados = 
				(ArrayList<ResultadoBusqueda>) request.getSession().getAttribute("resultados");
			// Lista de hoteles a comparar
			ArrayList<ResultadoBusqueda> resultadosComparar = 
				new ArrayList<ResultadoBusqueda>();
			// Añadimos los hoteles a comparar en la lista correspondiente
			if(resultados!=null){
				for (int i=0; i<resultados.size(); i++){
					String checkbox = request.getParameter(Integer.toString(i));
					// Comprobamos si el checkbox correspondiente está marcado
					if(checkbox!=null && checkbox.equals("OK")){
						// En caso de estarlo lo añadimos a la lista de hoteles a comparar
						resultadosComparar.add(resultados.get(i));
					}
				}
			}
			// Guardamos la lista de hoteles a comparar para mostrarla en el JSP
			request.getSession().setAttribute("resultadosComparar", resultadosComparar);
			response.sendRedirect("comparar.jsp");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
