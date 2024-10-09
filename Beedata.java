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
@WebServlet("/beedata")
public class Beedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String Month = request.getParameter("mon");
		String Production = request.getParameter("pro");
		String DateofExtraction = request.getParameter("doe");
		String TypeofHoney = request.getParameter("toh");
		String NetProfit = request.getParameter("np");
		String NetProfitAnnually = request.getParameter("npa");
		RequestDispatcher dispatcher = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/goatfarming","root","root");
			PreparedStatement ust=con.prepareStatement("insert into beeinfo(Month,Production,DateofExtraction,TypeofHoney,NetProfit,NetProfitAnnually) value(?,?,?,?,?,?)");
			ust.setString(1, Month);
			ust.setString(2, Production);
			ust.setString(3, DateofExtraction);
			ust.setString(4, TypeofHoney);
			ust.setString(5, NetProfit);
			ust.setString(6, NetProfitAnnually);
			
			int rowCount =	ust.executeUpdate();
			dispatcher  = request.getRequestDispatcher("beedata.jsp");
			if(rowCount > 0) {
				request.setAttribute("static", "successfullly");
				
			}else {
				request.setAttribute("static", "failet");
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

