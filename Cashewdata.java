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
@WebServlet("/cashewdata")
public class Cashewdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String Supplier = request.getParameter("sup");
		String EmpName = request.getParameter("empn");
		String EmpId = request.getParameter("empi");
		String Rawcashew = request.getParameter("cp");
		String Peeling = request.getParameter("pee");
		String Grading = request.getParameter("gra");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/goatfarming","root","root");
			PreparedStatement ust=con.prepareStatement("insert into cashew(Supplier,EmpName,EmpId,Rawcashew,Peeling,Grading) value(?,?,?,?,?,?)");
			ust.setString(1, Supplier);
			ust.setString(2, EmpName);
			ust.setString(3, EmpId);
			ust.setString(4, Rawcashew);
			ust.setString(5, Peeling);
			ust.setString(6, Grading);
			
			int rowCount =	ust.executeUpdate();
			dispatcher  = request.getRequestDispatcher("cashewdata.jsp");
			if(rowCount > 0) {
				request.setAttribute("statics", "successfully");
				
			}else {
				request.setAttribute("statics", "failet");
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

