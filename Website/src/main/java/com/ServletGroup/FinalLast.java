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
import javax.servlet.http.HttpSession;

@WebServlet("/lastamlast")
public class FinalLast extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		HttpSession hs=req.getSession();
		int id=Integer.parseInt(hs.getAttribute("mostImpItemID")+"");
		String email=(String)hs.getAttribute("email");
		PrintWriter out=res.getWriter();
		System.out.println(email+" "+id);
		try {
			
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
			PreparedStatement stm = con.prepareStatement("insert into cart values(?,?);");
            stm.setString(1, email);
            stm.setInt(2, id);
            stm.executeUpdate();
            out.println("Added to cart Successfully");
        
		} catch (ClassNotFoundException | SQLException e) 
		{
			out.println("Unscessful Attempt ");
			e.printStackTrace();
		}
		
	}
}
