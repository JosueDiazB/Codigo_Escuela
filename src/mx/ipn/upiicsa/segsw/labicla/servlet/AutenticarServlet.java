package mx.ipn.upiicsa.segsw.labicla.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/autenticar.controller")
public class AutenticarServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/la_bicla_db";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		// response.setContentType("text/html"); // // Establece tipo de contenido a enviar al cliente (browser)
		
		PrintWriter out = response.getWriter(); // Regresa una refencia al stream de salida hacia el cliente
												// Es decir, establece un mecanismo para enviar datos al cliente (browser)
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		if(email==null || email.trim().equals("") || password == null || password.trim().equals(""))
		{
			RequestDispatcher rd = request.getRequestDispatcher("error_parametros.html");
			rd.forward(request, response);
			return;
		}
		
		
		String sql = "SELECT * FROM users WHERE email = \'" + email + "\' AND password = \'" + password + "\'";
		
		System.out.println("AutenticarServlet - SQL - " + sql);

		
		// Prepara acceso a Base de Datos
		
		
		try {
			
			Class.forName(DRIVER); // Carga Driver JDBC en memoria
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);		// Establece conexion con base de datos
			
			stmt = con.createStatement();		// Crear un objeto Statement para darle intrucciones a la base de datos
			
			rs = stmt.executeQuery(sql); // Ejecuta query en base de datos para Valida que usario y passwors sean correcto
			
			
			if(rs.next()) // Encontro un registro -- Credenciales validas
			{
				request.getSession().setAttribute("email", email);
				
				RequestDispatcher rd = request.getRequestDispatcher("main.view");
				rd.forward(request, response);
				return;				
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("error_autenticacion.html");
				rd.forward(request, response);
				return;				
				
			}
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try{ if(rs != null) rs.close(); } catch(SQLException exi) { exi.printStackTrace();}
			try{ if(stmt != null) stmt.close(); } catch(SQLException exi) { exi.printStackTrace();}
			try{ if(con != null) con.close(); } catch(SQLException exi) { exi.printStackTrace();}
			
			System.out.println("AutenticarServlet - database resources were closed");
			
		}

		out.close();
		
	}



}
