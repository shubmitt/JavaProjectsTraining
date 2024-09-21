package com.learn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.ConnectionWrapper;
import com.mysql.cj.jdbc.Driver;

/**
 * Servlet implementation class Servletjdbc
 */
@WebServlet("/Servletjdbc")
public class Servletjdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbUrl = "jdbc:mysql://127.0.0.1:3306/StudentLog";
			String dbName = "StudentLog";
			String username = "root";
			String password = "Helloworld@123";
			try {
				Connection conn = DriverManager.getConnection(dbUrl,username,password);
				Statement statement = conn.createStatement();
				statement.executeUpdate("insert into studentinfo values(1,'Ram','Mohan','13',2)");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
