package com.uniquedevloper.registation;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/showdata")
public class Showdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String AgeCategory = request.getParameter("age");
		String Genetics = request.getParameter("gen");
		String foddermanagement = request.getParameter("fod");
		String NoofGoats = request.getParameter("goat");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/goatfarming","root","root");
			PreparedStatement rst=con.prepareStatement("insert into goatdata(AgeCategory,Genetics,foddermanagement,NoofGoats) value(?,?,?,?)");
			rst.setString(1, AgeCategory);
			rst.setString(2, Genetics);
			rst.setString(3, foddermanagement);
			rst.setString(4, NoofGoats);
			
			int rowCount =	rst.executeUpdate();
			dispatcher  = request.getRequestDispatcher("showdata.jsp");
			if(rowCount > 0) {
				request.setAttribute("state", "successful");
				
			}else {
				request.setAttribute("state", "fail");
			}
			
			dispatcher.forward(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}

