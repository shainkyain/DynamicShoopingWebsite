package com.ServletGroup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addNewUser")
public class registerUser extends HttpServlet 
{
	private static final long serialVersionUID = -1552244068618248793L;

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException 
	{
		PrintWriter out=res.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
	        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
	        PreparedStatement stm = con.prepareStatement("insert into UserInfo values (?,?,?,?,?,?);");  
	        stm.setString(1, req.getParameter("email"));
	        stm.setString(2, req.getParameter("firstName")+" "+req.getParameter("lastName"));
	        stm.setLong(3, Long.parseLong(req.getParameter("phone")));
	        stm.setString(4, req.getParameter("addr"));
	        stm.setString(5, req.getParameter("pass"));
	        stm.setString(6, req.getParameter("user"));
	        stm.executeUpdate();
	        out.println("Registered Succesfully !!!");
	        
		} catch (ClassNotFoundException | SQLException e) {
			out.println("Registered Unsuccesfull !!!");
		}

	}
}
