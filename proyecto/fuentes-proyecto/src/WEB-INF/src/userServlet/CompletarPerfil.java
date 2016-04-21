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

public class CompletarPerfil extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OracleTemplate ot;

	/**
	 * Devuelve los parametros del usuario para mostrarlos
	 * en su perfil de usuario.
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean validos = true;
		String email = request.getSession().getAttribute("email").toString();
		ArrayList<String> datosUsuario;

		//Si el formulario esta correcto procesamos la solicitud
		if (validos) {
			try {
				//Codigo para llamar a la funcion 
				// que inserta usuario en la DB				
				try {
					System.out.printf("Conectando... \n");
					ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
							"1521","vicious","a682531","13145gkJ");
					ot.connect();
					System.out.println("Conectado a " + ot);

					OperacionesBaseDeDatos ecd = new OperacionesBaseDeDatos(ot);
					datosUsuario = ecd.completarPerfil(email);
					request.setAttribute("datosUsuario", datosUsuario);
					request.getServletContext().getRequestDispatcher("perfil.jsp").forward(request, response);	
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
			response.sendRedirect("perfil.jsp");
		}
	}
	
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        		boolean validos = true;
		String email = request.getSession().getAttribute("email").toString();
		ArrayList<String> datosUsuario;

		//Si el formulario esta correcto procesamos la solicitud
		if (validos) {
			try {
				//Codigo para llamar a la funcion 
				// que inserta usuario en la DB				
				try {
					System.out.printf("Conectando... \n");
					ot = new OracleTemplate("hendrix-oracle.cps.unizar.es",
							"1521","vicious","a682531","13145gkJ");
					ot.connect();
					System.out.println("Conectado a " + ot);

					EjemploCargaDatos ecd = new EjemploCargaDatos(ot);
					datosUsuario = ecd.completarPerfil(email);
					request.setAttribute("datosUsuario", datosUsuario);
					request.getServletContext().getRequestDispatcher("perfil.jsp").forward(request, response);
					response.sendRedirect("perfil.jsp");	
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
				response.getWriter().println("Error");
			}
		}
    }
}
