package com.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	ServletContext context = this.getServletContext();
		 
		 String dburl = context.getInitParameter("Db_URL");
		  String username = context.getInitParameter("username");
		  String password = context.getInitParameter("password");
		  System.out.println(dburl);
		  try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(dburl, username, password);
    		System.out.println(conn);
    		}
    		catch(ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
    			
				e.printStackTrace();
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			
			PreparedStatement statement = conn.prepareStatement("insert into student(fname,lname,email,password) values(?,?,?,?)");
			
			statement.setString(1,request.getParameter("firstName"));
			statement.setString(2,request.getParameter("lastName"));
			statement.setString(3,request.getParameter("email"));
			statement.setString(4,request.getParameter("password"));
			int result = statement.executeUpdate();
			response.sendRedirect(request.getContextPath() + "/success.jsp"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		
		
	}

}
