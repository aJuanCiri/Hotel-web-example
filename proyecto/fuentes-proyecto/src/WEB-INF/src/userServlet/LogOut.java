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
 * Servlet encargado de desconectar al usuario
 * de la sesion actual.
 */
public class LogOut extends HttpServlet {
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
			// Invalidamos la sesion actual
			request.getSession().invalidate();
			// Reedireccionamos al index
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			//En caso de error vamos pagina de error
			response.sendRedirect("paginaError.html");
		}
	}

}
