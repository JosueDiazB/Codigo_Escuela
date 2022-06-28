package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.ipn.upiicsa.segsw.labicla.valueobject.ProductValueObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/product_details.view")
public class ShowProductDetailsServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");  // Establece el tipo de contenido que el servlet va a enviar al cliente
		PrintWriter out = response.getWriter(); // Abre un canal de comunicacion con el cliente   // Portal multidimensional from Toulouse
		
		ProductValueObject product = (ProductValueObject) request.getAttribute("product");
		
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
		out.println("<br>");
				
		if(product == null)
		{
			out.println("<font color=\"red\">No se encontro el producto indicado</font>");
		}
		else
		{
			out.println("<p> Nombre ");
			out.println(product.getName());
			out.println("</p>");
			out.println("<p> Descripcion");
			out.println(product.getDescription());
			out.println("</p>");			
			out.println("<p> Marca");
			out.println(product.getBrand());
			out.println("</p>");
			out.println("<p> Precio");
			out.println(product.getPrice());
			out.println("</p>");
			
		}

		out.println("<br>");
		out.println("<br>");
		
		out.println("<a href=\"/LaBicla\">Principal</a>");
		
		
		out.println("</body>");
		out.println("</html>");

	}



}
