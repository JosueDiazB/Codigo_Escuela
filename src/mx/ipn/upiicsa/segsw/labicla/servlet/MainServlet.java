package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/main.view")
public class MainServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html"); // // Establece tipo de contenido a enviar al cliente (browser)
		
		PrintWriter out = response.getWriter(); // regresa una refencia al stream de salida hacia el cliente
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>La Bicla</title>");
		out.println("</head>");
		
		
		out.println("<body>");
		
		String email = (String) request.getSession().getAttribute("email");
		
		if(email == null)
		{
			// No hay usuario autenticado
			
			out.println("<form action=\"autenticar.controller\" method=\"GET\">");
			out.println("Email ");
			out.println("<input type=\"text\" name=\"email\">");
			out.println("Password ");
			out.println("<input type=\"text\" name=\"password\">");
			out.println("<input type=\"submit\" value=\"Entrar\">");
			out.println("</form>");
			
		}
		else
		{
			// SI hay usuario autenticado
			out.println("<div>" + email + " <a href=\"logout.controller\">Salir</a></div>");
		}
		
		
		
		out.println("<h1>La Bicla</h1>");
		
		out.println("<form action=\"buscar.controller\" method=\"GET\">");
		out.println("<input type=\"text\" name=\"criterio\">");
		out.println("<input type=\"submit\" value=\"Buscar\">");
		out.println("</form>");
		
		out.println("<br>");
		out.println("</body>");
		out.println("</html>");

		out.close();
		
	}



}
