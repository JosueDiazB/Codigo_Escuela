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
@WebServlet("/products.view")
public class ShowProductListServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductListServlet() {
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
		
		List<ProductValueObject> productList = (List<ProductValueObject>) request.getAttribute("productList");
		
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
				
		if(productList.size() == 0)
		{
			out.println("<font color=\"red\">No se encontraron productos</font>");
		}
		else
		{
			out.println("<table border=\"1\">");
			
			out.println("<tr>");
			out.println("<th>Nombre</th>");
			out.println("<th>Descripcion</th>");
			out.println("<th>Marca</th>");
			out.println("<th>Precio</th>");
			out.println("<th>&nbsp;</th>");
			out.println("</tr>");

			
			for(int i = 0; i < productList.size(); i++ )
			{
				out.println("<tr>");
				out.println("<td>");
				out.println(productList.get(i).getName());
				out.println("</td>");
				out.println("<td>");
				out.println(productList.get(i).getDescription());
				out.println("</td>");
				out.println("<td>");
				out.println(productList.get(i).getBrand());
				out.println("</td>");
				out.println("<td>");
				out.println(productList.get(i).getPrice());
				out.println("</td>");
				
				out.println("<td>");
				out.println("<a href=\"get_product_detail.controller?id=" + productList.get(i).getId() + "\">Ver Detalle</a>");

				out.println("</td>");
				
				out.println("</tr>");
			}
			out.println("</table>");
			
		}

		out.println("<br>");
		out.println("<br>");
		
		out.println("<a href=\"/LaBicla\">Principal</a>");
		
		
		out.println("</body>");
		out.println("</html>");

	}



}
