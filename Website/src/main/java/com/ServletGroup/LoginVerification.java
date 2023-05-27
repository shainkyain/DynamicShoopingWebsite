package com.ServletGroup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/loginServlet")
public class LoginVerification extends HttpServlet 
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter out = res.getWriter();
		try 
		{

            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ShopWebsiteDataBase", "postgres", "root");
            PreparedStatement stm = con.prepareStatement("select * from UserInfo where email=? and password=? and mark=?");
            stm.setString(1,req.getParameter("email"));
            stm.setString(2,req.getParameter("pass"));
            stm.setString(3, req.getParameter("first")); 
            ResultSet rs=stm.executeQuery();
            if(rs.next())
            {
            	if(req.getParameter("first").equals("User"))
            	{
                HttpSession hss=req.getSession();
                hss.setAttribute("email",req.getParameter("email"));
                hss.setAttribute("pass", req.getParameter("pass"));
                res.sendRedirect("afterLogin.html");
            	}
            	else
            	{
            		HttpSession hss=req.getSession();
                    hss.setAttribute("email",req.getParameter("email"));
                    hss.setAttribute("pass", req.getParameter("pass"));
                    res.sendRedirect("AfterAdminLogin.html");
            	}
            	
            }
            else
            {
            	out.println("Try Again");
            	out.println(req.getParameter("first"));
            }
            
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
