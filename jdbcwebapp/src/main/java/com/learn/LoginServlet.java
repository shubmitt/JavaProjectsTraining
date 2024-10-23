package com.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private Connection conn;
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	ServletContext context = this.getServletContext();
		 
		 String dburl = context.getInitParameter("Db_URL");
		  String username = context.getInitParameter("username");
		  String password = context.getInitParameter("password");
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(dburl, username, password);
    		
    		}
    		catch(ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			PreparedStatement statement = conn.prepareStatement("select * from StudentLog.student where email=? && password=?");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs  = statement.executeQuery();
			
			if(!rs.next()) {
				request.setAttribute("errorMessage", "Invalid username or password");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
				
				//response.sendRedirect(request.getContextPath() + "/login.jsp");
				
			}
			else {
				System.out.println(rs.getInt(1));
				if(rs.getInt(1)>0) {
				HttpSession session = request.getSession();
				session.setAttribute("username", rs.getString("fname")+" " + rs.getString("lname"));
				response.sendRedirect(request.getContextPath() + "/home.jsp");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
