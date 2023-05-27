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

@WebServlet("/adminAddItem")
public class adminAddProduct extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7531331295637799263L;
	protected void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter out=res.getWriter();
			try 
			{
			Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
            PreparedStatement stm = con.prepareStatement("insert into item values (?,?,?,?,?,?);");    
            stm.setString(1,req.getParameter("path"));
            stm.setString(2,req.getParameter("Name"));
            stm.setInt(3,Integer.parseInt(req.getParameter("price")));
            stm.setInt(4,Integer.parseInt(req.getParameter("item_ID")));
            stm.setString(5,req.getParameter("feature"));
            stm.setInt(6,Integer.parseInt(req.getParameter("quantity")));  
            stm.executeUpdate();
            out.println("item added Succesfully !!!");
			} 
			catch (ClassNotFoundException | SQLException e )
			{
				out.println("item not added Succesfully !!!");
				
			}
	}
	
}
